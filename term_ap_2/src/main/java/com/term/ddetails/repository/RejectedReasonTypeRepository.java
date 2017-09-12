package com.term.ddetails.repository;

import com.term.ddetails.domain.RejectedReasonType;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the RejectedReasonType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RejectedReasonTypeRepository extends JpaRepository<RejectedReasonType,Long> {
    
}
