package in.assessment.test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.assessment.controller.LeadController;
import in.assessment.entity.Lead;
import in.assessment.exception.LeadAlreadyExistsException;
import in.assessment.service.LeadService;

@WebMvcTest(value = LeadController.class)
public class LeadControllerTest {
	
	@InjectMocks
    private LeadController leadController;

    @Mock
    private LeadService leadService;
    
    @Test
    public void testCreateLead() throws Exception {
        Lead lead = 
        when(leadService.createLead(any())).thenReturn(lead);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(leadController).build();

        mockMvc.perform(post("/api/leads")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(lead)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("success")))
                .andExpect(jsonPath("$.data", is("Created Leads Successfully")));
    }

    @Test
    public void testCreateLeadLeadAlreadyExists() throws Exception {
        doThrow(new LeadAlreadyExistsException("Lead Already Exists"))
                .when(leadService).createLead(any());

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(leadController).build();

        mockMvc.perform(post("/api/leads")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(new Lead())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is("error")))
                .andExpect(jsonPath("$.errorResponse.code", is("E10010")))
                .andExpect(jsonPath("$.errorResponse.messages[0]", is("Lead Already Exists")));
    }

    @Test
    public void testGetLeadsByMobileNumber() throws Exception {
        List<Lead> leads = // Create a list of leads for testing
        when(leadService.getLeadsByMobileNumber(anyString())).thenReturn(leads);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(leadController).build();

        mockMvc.perform(get("/api/leads/by-mobile-number/{mobileNumber}", "8877887788"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("success")))
                .andExpect(jsonPath("$.data", hasSize(2)));
    }

    @Test
    public void testGetLeadsByMobileNumberNoLeadsFound() throws Exception {
        when(leadService.getLeadsByMobileNumber(anyString())).thenReturn(Collections.emptyList());

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(leadController).build();

        mockMvc.perform(get("/api/leads/by-mobile-number/{mobileNumber}", "8877887788"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is("error")))
                .andExpect(jsonPath("$.errorResponse.code", is("E10011")))
                .andExpect(jsonPath("$.errorResponse.messages[0]", is("No Lead found with the Mobile Number")));
    }

    // Utility method to convert object to JSON string
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    

}
