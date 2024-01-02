package in.assessment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.assessment.entity.Lead;

public interface LeadRepository extends JpaRepository<Lead, Integer>{

	boolean existsByLeadId(Integer leadId);

    List<Lead> findByMobileNumber(String mobileNumber);
}
