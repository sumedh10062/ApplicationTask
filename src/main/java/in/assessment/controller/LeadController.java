package in.assessment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.assessment.entity.Lead;
import in.assessment.exception.ErrorResponse;
import in.assessment.exception.LeadAlreadyExistsException;
import in.assessment.exception.LeadNotFoundException;
import in.assessment.service.LeadService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("leads")
@Validated
public class LeadController {

	@Autowired
    private LeadService leadService;

    @PostMapping
    public ResponseEntity<Object> createLead(@Valid @RequestBody Lead lead) {
        try {
            leadService.createLead(lead);
            return new ResponseEntity<>("Created Leads Successfully", HttpStatus.OK);
        } catch (LeadAlreadyExistsException e) {
            ErrorResponse errorResponse = new ErrorResponse("E10010", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/mobile/{mobileNumber}")
    public ResponseEntity<Object> getLeadsByMobileNumber(@PathVariable String mobileNumber) {
        try {
            List<Lead> leads = leadService.getLeadsByMobileNumber(mobileNumber);
            return new ResponseEntity<>(leads, HttpStatus.OK);
        } catch (LeadNotFoundException e) {
            ErrorResponse errorResponse = new ErrorResponse("E10011", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }
}
