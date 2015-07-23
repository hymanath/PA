package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

/**
 * 
 * @author Balu
 *
 */
@Entity
@Table(name = "tdp_cadre_insurance_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCadreInsuranceInfo extends BaseModel implements Serializable{
	
	private Long tdpCadreInsuranceInfoId;
	private TdpCadre tdpCadre;
	private InsuranceType insuranceType;
	private InsuranceStatus insuranceStatus;
	private Date appliedOn;
	private Date approvedOn;
	private Long insertedBy;
	private Long updatedBy;
	private Date insertedTime;
	private Date updatedTime;
	
	private String trNo;
	private String membershipId;
	private Date dateOfAccident;
	private String causeOfAccident;
	private String contactNumber; 
	private String nominee;
	private String address;
	private String remarks;

	private Long tdpCadreId;
	private Long insuranceTypeId;
	private Long insuranceStatusId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_cadre_insurance_info_id", unique = true, nullable = false)
	public Long getTdpCadreInsuranceInfoId() {
		return tdpCadreInsuranceInfoId;
	}
	public void setTdpCadreInsuranceInfoId(Long tdpCadreInsuranceInfoId) {
		this.tdpCadreInsuranceInfoId = tdpCadreInsuranceInfoId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_cadre_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}
	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "insurance_type_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public InsuranceType getInsuranceType() {
		return insuranceType;
	}
	public void setInsuranceType(InsuranceType insuranceType) {
		this.insuranceType = insuranceType;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "insurance_status_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public InsuranceStatus getInsuranceStatus() {
		return insuranceStatus;
	}
	public void setInsuranceStatus(InsuranceStatus insuranceStatus) {
		this.insuranceStatus = insuranceStatus;
	}
	
	@Column(name = "applied_on")
	public Date getAppliedOn() {
		return appliedOn;
	}
	public void setAppliedOn(Date appliedOn) {
		this.appliedOn = appliedOn;
	}
	
	@Column(name = "approved_on")
	public Date getApprovedOn() {
		return approvedOn;
	}
	public void setApprovedOn(Date approvedOn) {
		this.approvedOn = approvedOn;
	}
	
	@Column(name = "inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	
	@Column(name = "updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@Column(name = "updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	@Column(name = "tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	@Column(name = "insurance_type_id")
	public Long getInsuranceTypeId() {
		return insuranceTypeId;
	}
	public void setInsuranceTypeId(Long insuranceTypeId) {
		this.insuranceTypeId = insuranceTypeId;
	}
	@Column(name = "insurance_status_id")
	public Long getInsuranceStatusId() {
		return insuranceStatusId;
	}
	public void setInsuranceStatusId(Long insuranceStatusId) {
		this.insuranceStatusId = insuranceStatusId;
	}
	
	
	//date_of_accident,cause_of_accident,contact_number,nominee,address,remarks
	@Column(name="tr_no")
	public String getTrNo() {
		return trNo;
	}
	public void setTrNo(String trNo) {
		this.trNo = trNo;
	}
	@Column(name="membership_id")
	public String getMembershipId() {
		return membershipId;
	}
	public void setMembershipId(String membershipId) {
		this.membershipId = membershipId;
	}
	@Column(name="date_of_accident")
	public Date getDateOfAccident() {
		return dateOfAccident;
	}
	public void setDateOfAccident(Date dateOfAccident) {
		this.dateOfAccident = dateOfAccident;
	}
	
	@Column(name="cause_of_accident")
	public String getCauseOfAccident() {
		return causeOfAccident;
	}
	public void setCauseOfAccident(String causeOfAccident) {
		this.causeOfAccident = causeOfAccident;
	}
	@Column(name="contact_number")
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	@Column(name="nominee")
	public String getNominee() {
		return nominee;
	}
	public void setNominee(String nominee) {
		this.nominee = nominee;
	}
	
	@Column(name="address")
	public String getAddress() { 
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name="remarks")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
}
