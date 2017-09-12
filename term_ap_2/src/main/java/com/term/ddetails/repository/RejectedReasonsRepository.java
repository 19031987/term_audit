package com.term.ddetails.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.term.ddetails.domain.RejectedReasons;

/**
 * Spring Data JPA repository for the RejectedReasons entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RejectedReasonsRepository extends JpaRepository<RejectedReasons,Long> {
	
	List<RejectedReasons>findByRejectedReasonTypeId(Long id);
    
}
