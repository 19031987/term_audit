package com.term.ddetails.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the RejectedReasonType entity.
 */
public class RejectedReasonTypeDTO implements Serializable {

    private Long id;

    private Integer reasonType;

    private String rejectReasonDesc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getReasonType() {
        return reasonType;
    }

    public void setReasonType(Integer reasonType) {
        this.reasonType = reasonType;
    }

    public String getRejectReasonDesc() {
        return rejectReasonDesc;
    }

    public void setRejectReasonDesc(String rejectReasonDesc) {
        this.rejectReasonDesc = rejectReasonDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RejectedReasonTypeDTO rejectedReasonTypeDTO = (RejectedReasonTypeDTO) o;
        if(rejectedReasonTypeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rejectedReasonTypeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RejectedReasonTypeDTO{" +
            "id=" + getId() +
            ", reasonType='" + getReasonType() + "'" +
            ", rejectReasonDesc='" + getRejectReasonDesc() + "'" +
            "}";
    }
}
