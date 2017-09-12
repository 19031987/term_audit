package com.term.ddetails.service.mapper;

import com.term.ddetails.domain.*;
import com.term.ddetails.service.dto.RejectedReasonsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RejectedReasons and its DTO RejectedReasonsDTO.
 */
@Mapper(componentModel = "spring", uses = {RejectedReasonTypeMapper.class, })
public interface RejectedReasonsMapper extends EntityMapper <RejectedReasonsDTO, RejectedReasons> {

    @Mapping(source = "rejectedReasonType.id", target = "rejectedReasonTypeId")

    @Mapping(source = "reasonTypes.id", target = "reasonTypesId")
    RejectedReasonsDTO toDto(RejectedReasons rejectedReasons); 

    @Mapping(source = "reasonTypesId", target = "rejectedReasonType")
    RejectedReasons toEntity(RejectedReasonsDTO rejectedReasonsDTO); 
    default RejectedReasons fromId(Long id) {
        if (id == null) {
            return null;
        }
        RejectedReasons rejectedReasons = new RejectedReasons();
        rejectedReasons.setId(id);
        return rejectedReasons;
    }
}
