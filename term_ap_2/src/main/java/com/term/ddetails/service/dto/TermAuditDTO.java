package com.term.ddetails.service.dto;


import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.term.ddetails.domain.enumeration.Typeofdoc;

/**
 * A DTO for the TermAudit entity.
 */
public class TermAuditDTO implements Serializable {

    private Long id;

    @NotNull
    private String mobilenumber;

    @NotNull
    private String termno;

    private String state;

    private String connection;

    private String connectiontype;

    private String barcode;

    private String nameofcustomer;

    private String fathername;

    private String doa;

    private String aoname;

    //private String checkavailabiltyofcaf;

    private Boolean photo;

    private String identityproof;

    private String addressproof;

    private String address;

    private LocalDate auditdate;

    private String auditby;

    private String caf;

   // private String activationdate;

    private String activationsource;

    private String vendor;

    private String dob;

    private Boolean customernamestatus;

    private Boolean fathernamestatus;

    private Boolean doastatus;

    private Boolean aonamestatus;

    private Boolean identityproofstatus;

    private Boolean addressproofstatus;

    private String rejectedreason;

    private Typeofdoc typeofdoc;

    private Boolean pointsalecode;

    private Boolean pointsalenamestatus;

    private Boolean pointsaleaddressstatus;

    private Boolean checkcafstatus;

    private LocalDate secauditdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getTermno() {
        return termno;
    }

    public void setTermno(String termno) {
        this.termno = termno;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public String getConnectiontype() {
        return connectiontype;
    }

    public void setConnectiontype(String connectiontype) {
        this.connectiontype = connectiontype;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getNameofcustomer() {
        return nameofcustomer;
    }

    public void setNameofcustomer(String nameofcustomer) {
        this.nameofcustomer = nameofcustomer;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public String getDoa() {
        return doa;
    }

    public void setDoa(String doa) {
        this.doa = doa;
    }

    public String getAoname() {
        return aoname;
    }

    public void setAoname(String aoname) {
        this.aoname = aoname;
    }

    /*public String getCheckavailabiltyofcaf() {
        return checkavailabiltyofcaf;
    }

    public void setCheckavailabiltyofcaf(String checkavailabiltyofcaf) {
        this.checkavailabiltyofcaf = checkavailabiltyofcaf;
    }*/

    public Boolean isPhoto() {
        return photo;
    }

    public void setPhoto(Boolean photo) {
        this.photo = photo;
    }

    public String getIdentityproof() {
        return identityproof;
    }

    public void setIdentityproof(String identityproof) {
        this.identityproof = identityproof;
    }

    public String getAddressproof() {
        return addressproof;
    }

    public void setAddressproof(String addressproof) {
        this.addressproof = addressproof;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getAuditdate() {
        return auditdate;
    }

    public void setAuditdate(LocalDate auditdate) {
        this.auditdate = auditdate;
    }

    public String getAuditby() {
        return auditby;
    }

    public void setAuditby(String auditby) {
        this.auditby = auditby;
    }

    public String getCaf() {
        return caf;
    }

    public void setCaf(String caf) {
        this.caf = caf;
    }

  /*  public String getActivationdate() {
        return activationdate;
    }

    public void setActivationdate(String activationdate) {
        this.activationdate = activationdate;
    }*/

    public String getActivationsource() {
        return activationsource;
    }

    public void setActivationsource(String activationsource) {
        this.activationsource = activationsource;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Boolean isCustomernamestatus() {
        return customernamestatus;
    }

    public void setCustomernamestatus(Boolean customernamestatus) {
        this.customernamestatus = customernamestatus;
    }

    public Boolean isFathernamestatus() {
        return fathernamestatus;
    }

    public void setFathernamestatus(Boolean fathernamestatus) {
        this.fathernamestatus = fathernamestatus;
    }

    public Boolean isDoastatus() {
        return doastatus;
    }

    public void setDoastatus(Boolean doastatus) {
        this.doastatus = doastatus;
    }

    public Boolean isAonamestatus() {
        return aonamestatus;
    }

    public void setAonamestatus(Boolean aonamestatus) {
        this.aonamestatus = aonamestatus;
    }

    public Boolean isIdentityproofstatus() {
        return identityproofstatus;
    }

    public void setIdentityproofstatus(Boolean identityproofstatus) {
        this.identityproofstatus = identityproofstatus;
    }

    public Boolean isAddressproofstatus() {
        return addressproofstatus;
    }

    public void setAddressproofstatus(Boolean addressproofstatus) {
        this.addressproofstatus = addressproofstatus;
    }

    public String getRejectedreason() {
        return rejectedreason;
    }

    public void setRejectedreason(String rejectedreason) {
        this.rejectedreason = rejectedreason;
    }

    public Typeofdoc getTypeofdoc() {
        return typeofdoc;
    }

    public void setTypeofdoc(Typeofdoc typeofdoc) {
        this.typeofdoc = typeofdoc;
    }

    public Boolean isPointsalecode() {
        return pointsalecode;
    }

    public void setPointsalecode(Boolean pointsalecode) {
        this.pointsalecode = pointsalecode;
    }

    public Boolean isPointsalenamestatus() {
        return pointsalenamestatus;
    }

    public void setPointsalenamestatus(Boolean pointsalenamestatus) {
        this.pointsalenamestatus = pointsalenamestatus;
    }

    public Boolean isPointsaleaddressstatus() {
        return pointsaleaddressstatus;
    }

    public void setPointsaleaddressstatus(Boolean pointsaleaddressstatus) {
        this.pointsaleaddressstatus = pointsaleaddressstatus;
    }

    public Boolean isCheckcafstatus() {
        return checkcafstatus;
    }

    public void setCheckcafstatus(Boolean checkcafstatus) {
        this.checkcafstatus = checkcafstatus;
    }

    public LocalDate getSecauditdate() {
        return secauditdate;
    }

    public void setSecauditdate(LocalDate secauditdate) {
        this.secauditdate = secauditdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TermAuditDTO termAuditDTO = (TermAuditDTO) o;
        if(termAuditDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), termAuditDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TermAuditDTO{" +
            "id=" + getId() +
            ", mobilenumber='" + getMobilenumber() + "'" +
            ", termno='" + getTermno() + "'" +
            ", state='" + getState() + "'" +
            ", connection='" + getConnection() + "'" +
            ", connectiontype='" + getConnectiontype() + "'" +
            ", barcode='" + getBarcode() + "'" +
            ", nameofcustomer='" + getNameofcustomer() + "'" +
            ", fathername='" + getFathername() + "'" +
            ", doa='" + getDoa() + "'" +
            ", aoname='" + getAoname() + "'" +
        //    ", checkavailabiltyofcaf='" + getCheckavailabiltyofcaf() + "'" +
            ", photo='" + isPhoto() + "'" +
            ", identityproof='" + getIdentityproof() + "'" +
            ", addressproof='" + getAddressproof() + "'" +
            ", address='" + getAddress() + "'" +
            ", auditdate='" + getAuditdate() + "'" +
            ", auditby='" + getAuditby() + "'" +
            ", caf='" + getCaf() + "'" +
         //   ", activationdate='" + getActivationdate() + "'" +
            ", activationsource='" + getActivationsource() + "'" +
            ", vendor='" + getVendor() + "'" +
            ", dob='" + getDob() + "'" +
            ", customernamestatus='" + isCustomernamestatus() + "'" +
            ", fathernamestatus='" + isFathernamestatus() + "'" +
            ", doastatus='" + isDoastatus() + "'" +
            ", aonamestatus='" + isAonamestatus() + "'" +
            ", identityproofstatus='" + isIdentityproofstatus() + "'" +
            ", addressproofstatus='" + isAddressproofstatus() + "'" +
            ", rejectedreason='" + getRejectedreason() + "'" +
            ", typeofdoc='" + getTypeofdoc() + "'" +
            ", pointsalecode='" + isPointsalecode() + "'" +
            ", pointsalenamestatus='" + isPointsalenamestatus() + "'" +
            ", pointsaleaddressstatus='" + isPointsaleaddressstatus() + "'" +
            ", checkcafstatus='" + isCheckcafstatus() + "'" +
            ", secauditdate='" + getSecauditdate() + "'" +
            "}";
    }
}
