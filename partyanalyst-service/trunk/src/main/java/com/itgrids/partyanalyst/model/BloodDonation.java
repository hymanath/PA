package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
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

@Entity
@Table(name = "blood_donation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BloodDonation extends BaseModel implements Serializable{

	private Long bloodDonationId;
	private Long bloodDonationCampId;
	private Long bloodDonorInfoId;
	private String bagNo;
	private Long bloodBagTypeId;
	private Long donorAge;
	private Long bloodDonationAgeGroupId;
	private Long quantity;
	private Long bloodComponentId;
	private Long donationsInBloodBank;
	private String donationsInOtherPlaces;
	private String lastDonationDate;
	private String emergencyDonation;
	private String willingToCallDonation;
	private Date donationDate;
	private Long acceptanceStatusId;
	private String printStatus;
	private Date printTime;
	private String remarks;
	private Date insertedTime;
	private Long insertedBy;
	private Date updatedTime;
	private Long updatedBy;
	
	private BloodDonationCamp bloodDonationCamp;
	private BloodDonorInfo bloodDonorInfo;
	private BloodBagType bloodBagType;
	private BloodDonationAgeGroup bloodDonationAgeGroup;
	private BloodComponent bloodComponent;
	private AcceptanceStatus acceptanceStatus;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "blood_donation_id", unique = true, nullable = false)
	public Long getBloodDonationId() {
		return bloodDonationId;
	}
	public void setBloodDonationId(Long bloodDonationId) {
		this.bloodDonationId = bloodDonationId;
	}
	
	@Column(name="blood_donation_camp_id")
	public Long getBloodDonationCampId() {
		return bloodDonationCampId;
	}
	public void setBloodDonationCampId(Long bloodDonationCampId) {
		this.bloodDonationCampId = bloodDonationCampId;
	}
	
	@Column(name="blood_donor_info_id")
	public Long getBloodDonorInfoId() {
		return bloodDonorInfoId;
	}
	public void setBloodDonorInfoId(Long bloodDonorInfoId) {
		this.bloodDonorInfoId = bloodDonorInfoId;
	}
	
	@Column(name="bag_no")
	public String getBagNo() {
		return bagNo;
	}
	public void setBagNo(String bagNo) {
		this.bagNo = bagNo;
	}
	
	@Column(name="blood_bag_type_id")
	public Long getBloodBagTypeId() {
		return bloodBagTypeId;
	}
	public void setBloodBagTypeId(Long bloodBagTypeId) {
		this.bloodBagTypeId = bloodBagTypeId;
	}
	
	@Column(name="donor_age")
	public Long getDonorAge() {
		return donorAge;
	}
	public void setDonorAge(Long donorAge) {
		this.donorAge = donorAge;
	}
	
	@Column(name="blood_donation_age_group_id")
	public Long getBloodDonationAgeGroupId() {
		return bloodDonationAgeGroupId;
	}
	public void setBloodDonationAgeGroupId(Long bloodDonationAgeGroupId) {
		this.bloodDonationAgeGroupId = bloodDonationAgeGroupId;
	}
	
	@Column(name="quantity")
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
	@Column(name="blood_component_id")
	public Long getBloodComponentId() {
		return bloodComponentId;
	}
	public void setBloodComponentId(Long bloodComponentId) {
		this.bloodComponentId = bloodComponentId;
	}
	
	@Column(name="donations_in_blood_bank")
	public Long getDonationsInBloodBank() {
		return donationsInBloodBank;
	}
	public void setDonationsInBloodBank(Long donationsInBloodBank) {
		this.donationsInBloodBank = donationsInBloodBank;
	}
	
	@Column(name="donations_in_other_places")
	public String getDonationsInOtherPlaces() {
		return donationsInOtherPlaces;
	}
	public void setDonationsInOtherPlaces(String donationsInOtherPlaces) {
		this.donationsInOtherPlaces = donationsInOtherPlaces;
	}
	
	@Column(name="last_donation_date")
	public String getLastDonationDate() {
		return lastDonationDate;
	}
	public void setLastDonationDate(String lastDonationDate) {
		this.lastDonationDate = lastDonationDate;
	}
	
	@Column(name="emergency_donation")
	public String getEmergencyDonation() {
		return emergencyDonation;
	}	
	public void setEmergencyDonation(String emergencyDonation) {
		this.emergencyDonation = emergencyDonation;
	}
	
	@Column(name="willing_to_call_donation")
	public String getWillingToCallDonation() {
		return willingToCallDonation;
	}
	public void setWillingToCallDonation(String willingToCallDonation) {
		this.willingToCallDonation = willingToCallDonation;
	}
	
	@Column(name="donation_date")
	public Date getDonationDate() {
		return donationDate;
	}
	public void setDonationDate(Date donationDate) {
		this.donationDate = donationDate;
	}
	
	@Column(name="acceptance_status_id")
	public Long getAcceptanceStatusId() {
		return acceptanceStatusId;
	}
	public void setAcceptanceStatusId(Long acceptanceStatusId) {
		this.acceptanceStatusId = acceptanceStatusId;
	}
	
	@Column(name="print_status")
	public String getPrintStatus() {
		return printStatus;
	}
	public void setPrintStatus(String printStatus) {
		this.printStatus = printStatus;
	}
	
	@Column(name="print_time")
	public Date getPrintTime() {
		return printTime;
	}
	public void setPrintTime(Date printTime) {
		this.printTime = printTime;
	}
	
	@Column(name="remarks")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name="inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	
	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	@Column(name="updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="blood_donation_camp_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public BloodDonationCamp getBloodDonationCamp() {
		return bloodDonationCamp;
	}
	public void setBloodDonationCamp(BloodDonationCamp bloodDonationCamp) {
		this.bloodDonationCamp = bloodDonationCamp;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="blood_donor_info_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public BloodDonorInfo getBloodDonorInfo() {
		return bloodDonorInfo;
	}
	public void setBloodDonorInfo(BloodDonorInfo bloodDonorInfo) {
		this.bloodDonorInfo = bloodDonorInfo;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="blood_bag_type_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public BloodBagType getBloodBagType() {
		return bloodBagType;
	}
	public void setBloodBagType(BloodBagType bloodBagType) {
		this.bloodBagType = bloodBagType;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="blood_donation_age_group_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public BloodDonationAgeGroup getBloodDonationAgeGroup() {
		return bloodDonationAgeGroup;
	}
	public void setBloodDonationAgeGroup(BloodDonationAgeGroup bloodDonationAgeGroup) {
		this.bloodDonationAgeGroup = bloodDonationAgeGroup;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="blood_component_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public BloodComponent getBloodComponent() {
		return bloodComponent;
	}
	public void setBloodComponent(BloodComponent bloodComponent) {
		this.bloodComponent = bloodComponent;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="acceptance_status_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AcceptanceStatus getAcceptanceStatus() {
		return acceptanceStatus;
	}
	public void setAcceptanceStatus(AcceptanceStatus acceptanceStatus) {
		this.acceptanceStatus = acceptanceStatus;
	}
}
