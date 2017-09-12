package com.term.ddetails.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the RejectedReasons entity.
 */
public class RejectedReasonsDTO implements Serializable {

    private Long id;

    private Integer fkreasonType;

    private String rejectReasons;

    private Long rejectedReasonTypeId;

    private Long reasonTypesId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFkreasonType() {
        return fkreasonType;
    }

    public void setFkreasonType(Integer fkreasonType) {
        this.fkreasonType = fkreasonType;
    }

    public String getRejectReasons() {
        return rejectReasons;
    }

    public void setRejectReasons(String rejectReasons) {
        this.rejectReasons = rejectReasons;
    }

    public Long getRejectedReasonTypeId() {
        return rejectedReasonTypeId;
    }

    public void setRejectedReasonTypeId(Long rejectedReasonTypeId) {
        this.rejectedReasonTypeId = rejectedReasonTypeId;
    }

    public Long getReasonTypesId() {
        return reasonTypesId;
    }

    public void setReasonTypesId(Long rejectedReasonTypeId) {
        this.reasonTypesId = rejectedReasonTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RejectedReasonsDTO rejectedReasonsDTO = (RejectedReasonsDTO) o;
        if(rejectedReasonsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rejectedReasonsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RejectedReasonsDTO{" +
            "id=" + getId() +
            ", fkreasonType='" + getFkreasonType() + "'" +
            ", rejectReasons='" + getRejectReasons() + "'" +
            "}";
    }
}
