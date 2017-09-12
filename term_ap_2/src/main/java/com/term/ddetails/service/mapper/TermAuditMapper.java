package com.term.ddetails.service.mapper;

import com.term.ddetails.domain.*;
import com.term.ddetails.service.dto.TermAuditDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TermAudit and its DTO TermAuditDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TermAuditMapper extends EntityMapper <TermAuditDTO, TermAudit> {
    
    
    default TermAudit fromId(Long id) {
        if (id == null) {
            return null;
        }
        TermAudit termAudit = new TermAudit();
        termAudit.setId(id);
        return termAudit;
    }
}
