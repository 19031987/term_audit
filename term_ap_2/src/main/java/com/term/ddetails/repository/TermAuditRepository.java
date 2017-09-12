package com.term.ddetails.repository;

import com.term.ddetails.domain.TermAudit;
import com.term.ddetails.service.dto.TermAuditDTO;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the TermAudit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TermAuditRepository extends JpaRepository<TermAudit,Long> {
	
	@Query(value = "SELECT * FROM term_audit_ao.`aug_17_data_prepaid` WHERE MOB_NO = ?1", nativeQuery = true)
	Object getData(Long mobilenumber);
	@Query(value = "SELECT * FROM term_audit_ao.`aug_17_data_postpaid` WHERE MSISDN= ?1", nativeQuery = true)
	Object getDataPostpaid(Long mobilenumber);
	
	TermAudit findBymobilenumber(String findByTermNo);
    
}
