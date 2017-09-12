package com.term.ddetails.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.term.ddetails.domain.enumeration.Typeofdoc;

/**
 * A TermAudit.
 */
@Entity
@Table(name = "term_audit")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TermAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "mobilenumber", nullable = false)
    private String mobilenumber;

    @NotNull
    @Column(name = "termno", nullable = false)
    private String termno;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "pointsalecode", nullable = false)
    private String connection;

    @Column(name = "connectiontype", nullable = false)
    private String connectiontype;

    @Column(name = "pointsalename")
    private String barcode;

    @Column(name = "nameofcustomer", nullable = false)
    private String nameofcustomer;

    @Column(name = "fathername")
    private String fathername;

    @Column(name = "doa")
    private String doa;

    @Column(name = "aoname")
    private String aoname;

   /* @Column(name = "checkavailabiltyofcaf")
    private String checkavailabiltyofcaf;*/

    @Column(name = "photo")
    private Boolean photo;

    @Column(name = "identityproof")
    private String identityproof;

    @Column(name = "permanentaddress")
    private String addressproof;

    @Column(name = "address")
    private String address;

    @Column(name = "auditdate")
    private LocalDate auditdate;

    @Column(name = "auditby")
    private String auditby;

    @Column(name = "retailanddistribution")
    private String caf;

   /* @Column(name = "activationdate")
    private String activationdate;
*/
    @Column(name = "activationsource")
    private String activationsource;

    @Column(name = "vendor")
    private String vendor;

    @Column(name = "secaudituser")
    private String dob;

    @Column(name = "customernamestatus")
    private Boolean customernamestatus;

    @Column(name = "fathernamestatus")
    private Boolean fathernamestatus;

    @Column(name = "doastatus")
    private Boolean doastatus;

    @Column(name = "aonamestatus")
    private Boolean aonamestatus;

    @Column(name = "identityproofstatus")
    private Boolean identityproofstatus;

    @Column(name = "addressproofstatus")
    private Boolean addressproofstatus;

    @Column(name = "rejectedreason")
    private String rejectedreason;

    @Enumerated(EnumType.STRING)
    @Column(name = "typeofdoc")
    private Typeofdoc typeofdoc;

    @Column(name = "pointsalecodestatus")
    private Boolean pointsalecode;

    @Column(name = "pointsalenamestatus")
    private Boolean pointsalenamestatus;

    @Column(name = "retaildistributionstatus")
    private Boolean pointsaleaddressstatus;

    @Column(name = "checkcafstatus")
    private Boolean checkcafstatus;

    @Column(name = "secauditdate")
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

    public TermAudit mobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
        return this;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getTermno() {
        return termno;
    }

    public TermAudit termno(String termno) {
        this.termno = termno;
        return this;
    }

    public void setTermno(String termno) {
        this.termno = termno;
    }

    public String getState() {
        return state;
    }

    public TermAudit state(String state) {
        this.state = state;
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getConnection() {
        return connection;
    }

    public TermAudit connection(String connection) {
        this.connection = connection;
        return this;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public String getConnectiontype() {
        return connectiontype;
    }

    public TermAudit connectiontype(String connectiontype) {
        this.connectiontype = connectiontype;
        return this;
    }

    public void setConnectiontype(String connectiontype) {
        this.connectiontype = connectiontype;
    }

    public String getBarcode() {
        return barcode;
    }

    public TermAudit barcode(String barcode) {
        this.barcode = barcode;
        return this;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getNameofcustomer() {
        return nameofcustomer;
    }

    public TermAudit nameofcustomer(String nameofcustomer) {
        this.nameofcustomer = nameofcustomer;
        return this;
    }

    public void setNameofcustomer(String nameofcustomer) {
        this.nameofcustomer = nameofcustomer;
    }

    public String getFathername() {
        return fathername;
    }

    public TermAudit fathername(String fathername) {
        this.fathername = fathername;
        return this;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public String getDoa() {
        return doa;
    }

    public TermAudit doa(String doa) {
        this.doa = doa;
        return this;
    }

    public void setDoa(String doa) {
        this.doa = doa;
    }

    public String getAoname() {
        return aoname;
    }

    public TermAudit aoname(String aoname) {
        this.aoname = aoname;
        return this;
    }

    public void setAoname(String aoname) {
        this.aoname = aoname;
    }

   /* public String getCheckavailabiltyofcaf() {
        return checkavailabiltyofcaf;
    }

    public TermAudit checkavailabiltyofcaf(String checkavailabiltyofcaf) {
        this.checkavailabiltyofcaf = checkavailabiltyofcaf;
        return this;
    }

    public void setCheckavailabiltyofcaf(String checkavailabiltyofcaf) {
        this.checkavailabiltyofcaf = checkavailabiltyofcaf;
    }
*/
    public Boolean isPhoto() {
        return photo;
    }

    public TermAudit photo(Boolean photo) {
        this.photo = photo;
        return this;
    }

    public void setPhoto(Boolean photo) {
        this.photo = photo;
    }

    public String getIdentityproof() {
        return identityproof;
    }

    public TermAudit identityproof(String identityproof) {
        this.identityproof = identityproof;
        return this;
    }

    public void setIdentityproof(String identityproof) {
        this.identityproof = identityproof;
    }

    public String getAddressproof() {
        return addressproof;
    }

    public TermAudit addressproof(String addressproof) {
        this.addressproof = addressproof;
        return this;
    }

    public void setAddressproof(String addressproof) {
        this.addressproof = addressproof;
    }

    public String getAddress() {
        return address;
    }

    public TermAudit address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getAuditdate() {
        return auditdate;
    }

    public TermAudit auditdate(LocalDate auditdate) {
        this.auditdate = auditdate;
        return this;
    }

    public void setAuditdate(LocalDate auditdate) {
        this.auditdate = auditdate;
    }

    public String getAuditby() {
        return auditby;
    }

    public TermAudit auditby(String auditby) {
        this.auditby = auditby;
        return this;
    }

    public void setAuditby(String auditby) {
        this.auditby = auditby;
    }

    public String getCaf() {
        return caf;
    }

    public TermAudit caf(String caf) {
        this.caf = caf;
        return this;
    }

    public void setCaf(String caf) {
        this.caf = caf;
    }

    /*public String getActivationdate() {
        return activationdate;
    }

    public TermAudit activationdate(String activationdate) {
        this.activationdate = activationdate;
        return this;
    }

    public void setActivationdate(String activationdate) {
        this.activationdate = activationdate;
    }*/

    public String getActivationsource() {
        return activationsource;
    }

    public TermAudit activationsource(String activationsource) {
        this.activationsource = activationsource;
        return this;
    }

    public void setActivationsource(String activationsource) {
        this.activationsource = activationsource;
    }

    public String getVendor() {
        return vendor;
    }

    public TermAudit vendor(String vendor) {
        this.vendor = vendor;
        return this;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getDob() {
        return dob;
    }

    public TermAudit dob(String dob) {
        this.dob = dob;
        return this;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Boolean isCustomernamestatus() {
        return customernamestatus;
    }

    public TermAudit customernamestatus(Boolean customernamestatus) {
        this.customernamestatus = customernamestatus;
        return this;
    }

    public void setCustomernamestatus(Boolean customernamestatus) {
        this.customernamestatus = customernamestatus;
    }

    public Boolean isFathernamestatus() {
        return fathernamestatus;
    }

    public TermAudit fathernamestatus(Boolean fathernamestatus) {
        this.fathernamestatus = fathernamestatus;
        return this;
    }

    public void setFathernamestatus(Boolean fathernamestatus) {
        this.fathernamestatus = fathernamestatus;
    }

    public Boolean isDoastatus() {
        return doastatus;
    }

    public TermAudit doastatus(Boolean doastatus) {
        this.doastatus = doastatus;
        return this;
    }

    public void setDoastatus(Boolean doastatus) {
        this.doastatus = doastatus;
    }

    public Boolean isAonamestatus() {
        return aonamestatus;
    }

    public TermAudit aonamestatus(Boolean aonamestatus) {
        this.aonamestatus = aonamestatus;
        return this;
    }

    public void setAonamestatus(Boolean aonamestatus) {
        this.aonamestatus = aonamestatus;
    }

    public Boolean isIdentityproofstatus() {
        return identityproofstatus;
    }

    public TermAudit identityproofstatus(Boolean identityproofstatus) {
        this.identityproofstatus = identityproofstatus;
        return this;
    }

    public void setIdentityproofstatus(Boolean identityproofstatus) {
        this.identityproofstatus = identityproofstatus;
    }

    public Boolean isAddressproofstatus() {
        return addressproofstatus;
    }

    public TermAudit addressproofstatus(Boolean addressproofstatus) {
        this.addressproofstatus = addressproofstatus;
        return this;
    }

    public void setAddressproofstatus(Boolean addressproofstatus) {
        this.addressproofstatus = addressproofstatus;
    }

    public String getRejectedreason() {
        return rejectedreason;
    }

    public TermAudit rejectedreason(String rejectedreason) {
        this.rejectedreason = rejectedreason;
        return this;
    }

    public void setRejectedreason(String rejectedreason) {
        this.rejectedreason = rejectedreason;
    }

    public Typeofdoc getTypeofdoc() {
        return typeofdoc;
    }

    public TermAudit typeofdoc(Typeofdoc typeofdoc) {
        this.typeofdoc = typeofdoc;
        return this;
    }

    public void setTypeofdoc(Typeofdoc typeofdoc) {
        this.typeofdoc = typeofdoc;
    }

    public Boolean isPointsalecode() {
        return pointsalecode;
    }

    public TermAudit pointsalecode(Boolean pointsalecode) {
        this.pointsalecode = pointsalecode;
        return this;
    }

    public void setPointsalecode(Boolean pointsalecode) {
        this.pointsalecode = pointsalecode;
    }

    public Boolean isPointsalenamestatus() {
        return pointsalenamestatus;
    }

    public TermAudit pointsalenamestatus(Boolean pointsalenamestatus) {
        this.pointsalenamestatus = pointsalenamestatus;
        return this;
    }

    public void setPointsalenamestatus(Boolean pointsalenamestatus) {
        this.pointsalenamestatus = pointsalenamestatus;
    }

    public Boolean isPointsaleaddressstatus() {
        return pointsaleaddressstatus;
    }

    public TermAudit pointsaleaddressstatus(Boolean pointsaleaddressstatus) {
        this.pointsaleaddressstatus = pointsaleaddressstatus;
        return this;
    }

    public void setPointsaleaddressstatus(Boolean pointsaleaddressstatus) {
        this.pointsaleaddressstatus = pointsaleaddressstatus;
    }

    public Boolean isCheckcafstatus() {
        return checkcafstatus;
    }

    public TermAudit checkcafstatus(Boolean checkcafstatus) {
        this.checkcafstatus = checkcafstatus;
        return this;
    }

    public void setCheckcafstatus(Boolean checkcafstatus) {
        this.checkcafstatus = checkcafstatus;
    }

    public LocalDate getSecauditdate() {
        return secauditdate;
    }

    public TermAudit secauditdate(LocalDate secauditdate) {
        this.secauditdate = secauditdate;
        return this;
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
        TermAudit termAudit = (TermAudit) o;
        if (termAudit.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), termAudit.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TermAudit{" +
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
      //      ", checkavailabiltyofcaf='" + getCheckavailabiltyofcaf() + "'" +
            ", photo='" + isPhoto() + "'" +
            ", identityproof='" + getIdentityproof() + "'" +
            ", addressproof='" + getAddressproof() + "'" +
            ", address='" + getAddress() + "'" +
            ", auditdate='" + getAuditdate() + "'" +
            ", auditby='" + getAuditby() + "'" +
            ", caf='" + getCaf() + "'" +
           // ", activationdate='" + getActivationdate() + "'" +
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
