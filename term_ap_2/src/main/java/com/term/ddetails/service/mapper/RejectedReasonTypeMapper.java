package com.term.ddetails.service.mapper;

import com.term.ddetails.domain.*;
import com.term.ddetails.service.dto.RejectedReasonTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RejectedReasonType and its DTO RejectedReasonTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RejectedReasonTypeMapper extends EntityMapper <RejectedReasonTypeDTO, RejectedReasonType> {
    
    @Mapping(target = "reasonTypes", ignore = true)
    RejectedReasonType toEntity(RejectedReasonTypeDTO rejectedReasonTypeDTO); 
    default RejectedReasonType fromId(Long id) {
        if (id == null) {
            return null;
        }
        RejectedReasonType rejectedReasonType = new RejectedReasonType();
        rejectedReasonType.setId(id);
        return rejectedReasonType;
    }
}
