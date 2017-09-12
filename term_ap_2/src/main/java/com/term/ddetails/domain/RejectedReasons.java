package com.term.ddetails.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A RejectedReasons.
 */
@Entity
@Table(name = "rejected_reasons")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RejectedReasons implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@Column(name = "fkreason_type")
    private Integer fkreasonType;*/

    @Column(name = "reject_reasons")
    private String rejectReasons;

    @ManyToOne
    private RejectedReasonType rejectedReasonType;

    @ManyToOne
    private RejectedReasonType reasonTypes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   /* public Integer getFkreasonType() {
        return fkreasonType;
    }

    public RejectedReasons fkreasonType(Integer fkreasonType) {
        this.fkreasonType = fkreasonType;
        return this;
    }

    public void setFkreasonType(Integer fkreasonType) {
        this.fkreasonType = fkreasonType;
    }*/

    public String getRejectReasons() {
        return rejectReasons;
    }

    public RejectedReasons rejectReasons(String rejectReasons) {
        this.rejectReasons = rejectReasons;
        return this;
    }

    public void setRejectReasons(String rejectReasons) {
        this.rejectReasons = rejectReasons;
    }

    public RejectedReasonType getRejectedReasonType() {
        return rejectedReasonType;
    }

    public RejectedReasons rejectedReasonType(RejectedReasonType rejectedReasonType) {
        this.rejectedReasonType = rejectedReasonType;
        return this;
    }

    public void setRejectedReasonType(RejectedReasonType rejectedReasonType) {
        this.rejectedReasonType = rejectedReasonType;
    }

    public RejectedReasonType getReasonTypes() {
        return reasonTypes;
    }

    public RejectedReasons reasonTypes(RejectedReasonType rejectedReasonType) {
        this.reasonTypes = rejectedReasonType;
        return this;
    }

    public void setReasonTypes(RejectedReasonType rejectedReasonType) {
        this.reasonTypes = rejectedReasonType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RejectedReasons rejectedReasons = (RejectedReasons) o;
        if (rejectedReasons.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rejectedReasons.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RejectedReasons{" +
            "id=" + getId() +
            ", rejectReasons='" + getRejectReasons() + "'" +
            "}";
    }
}
