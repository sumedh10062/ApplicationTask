package in.assessment.service;

import java.util.List;

import in.assessment.entity.Lead;


public interface LeadService {

	Lead createLead(Lead lead);
	
	List<Lead> getLeadsByMobileNumber(String MobileNumber);
}
