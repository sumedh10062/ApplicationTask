package in.assessment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.assessment.entity.Lead;
import in.assessment.exception.LeadAlreadyExistsException;
import in.assessment.exception.LeadNotFoundException;
import in.assessment.repository.LeadRepository;

@Service
public class LeadServiceImpl implements LeadService {

	@Autowired
    private LeadRepository leadRepository;
	
	@Override
	public Lead createLead(Lead lead) {
		
		if (leadRepository.existsByLeadId(lead.getLeadId())) {
            throw new LeadAlreadyExistsException("Lead Already Exists in the database with the lead id");
        }
        // Perform other validations and save the lead
        return leadRepository.save(lead);
	}

	@Override
	public List<Lead> getLeadsByMobileNumber(String mobileNumber) {
		List<Lead> leads = leadRepository.findByMobileNumber(mobileNumber);
        if (leads.isEmpty()) {
            throw new LeadNotFoundException("No Lead found with the Mobile Number " + mobileNumber);
        }
        return leads;
	}

}
