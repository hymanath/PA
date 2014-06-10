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
@Table(name = "field_voter_data")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FieldVoterData extends BaseModel implements Serializable{
	
	private Long fieldVoterDataId;
	private Voter voter;
	private String isCade;
	private String isInfluencingPeople;
	private String tags;
	private String mobileNo;
	private Party party;
	private CasteState casteState;
	private String latitude;
	private String longitude;
	private Date surveyTime;
	private Date insertTime;
	private String isDelete;
	private String uniqueCode;
	private Hamlet hamlet;
	private Ward ward;
	private User user;
	private Locality locality;
	private Long verifiedCasteStateId;
	private Long verifiedHamletId;
	private Long verifiedLocalityId;
	private String isNewCaste;
	private String isNewHamlet;
	private Date updatedTime;
	private String isSync;
	private String syncTime;
	private Long verifierId;
	
	public FieldVoterData()
	{
		
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="field_voter_data_id", unique=true, nullable=false)
	public Long getFieldVoterDataId() {
		return fieldVoterDataId;
	}
	public void setFieldVoterDataId(Long fieldVoterDataId) {
		this.fieldVoterDataId = fieldVoterDataId;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="voter_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Voter getVoter() {
		return voter;
	}
	public void setVoter(Voter voter) {
		this.voter = voter;
	}
	@Column(name = "is_cade")
	public String getIsCade() {
		return isCade;
	}
	public void setIsCade(String isCade) {
		this.isCade = isCade;
	}
	@Column(name = "is_influencing_people")
	public String getIsInfluencingPeople() {
		return isInfluencingPeople;
	}
	public void setIsInfluencingPeople(String isInfluencingPeople) {
		this.isInfluencingPeople = isInfluencingPeople;
	}
	@Column(name = "tags")
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	@Column(name = "mobile_no")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="party_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Party getParty() {
		return party;
	}
	public void setParty(Party party) {
		this.party = party;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="caste_state_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CasteState getCasteState() {
		return casteState;
	}
	public void setCasteState(CasteState casteState) {
		this.casteState = casteState;
	}
	@Column(name = "latitude")
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	@Column(name = "longitude")
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	@Column(name = "survey_time")
	public Date getSurveyTime() {
		return surveyTime;
	}
	public void setSurveyTime(Date surveyTime) {
		this.surveyTime = surveyTime;
	}
	@Column(name = "insert_time")
	public Date getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	@Column(name = "is_delete")
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	@Column(name = "unique_code")
	public String getUniqueCode() {
		return uniqueCode;
	}
	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
	}
	public Hamlet getHamlet() {
		return hamlet;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="hamlet_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public void setHamlet(Hamlet hamlet) {
		this.hamlet = hamlet;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="ward_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Ward getWard() {
		return ward;
	}
	public void setWard(Ward ward) {
		this.ward = ward;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="data_collector_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="locality_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Locality getLocality() {
		return locality;
	}
	public void setLocality(Locality locality) {
		this.locality = locality;
	}
	@Column(name = "verified_caste_state_id")
	public Long getVerifiedCasteStateId() {
		return verifiedCasteStateId;
	}
	public void setVerifiedCasteStateId(Long verifiedCasteStateId) {
		this.verifiedCasteStateId = verifiedCasteStateId;
	}
	@Column(name = "verified_hamlet_id")
	public Long getVerifiedHamletId() {
		return verifiedHamletId;
	}
	public void setVerifiedHamletId(Long verifiedHamletId) {
		this.verifiedHamletId = verifiedHamletId;
	}
	@Column(name = "verified_locality_id")
	public Long getVerifiedLocalityId() {
		return verifiedLocalityId;
	}
	public void setVerifiedLocalityId(Long verifiedLocalityId) {
		this.verifiedLocalityId = verifiedLocalityId;
	}
	@Column(name = "is_new_caste")
	public String getIsNewCaste() {
		return isNewCaste;
	}
	public void setIsNewCaste(String isNewCaste) {
		this.isNewCaste = isNewCaste;
	}
	@Column(name = "is_new_hamlet")
	public String getIsNewHamlet() {
		return isNewHamlet;
	}
	public void setIsNewHamlet(String isNewHamlet) {
		this.isNewHamlet = isNewHamlet;
	}
	@Column(name = "updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	@Column(name = "is_sync")
	public String getIsSync() {
		return isSync;
	}
	public void setIsSync(String isSync) {
		this.isSync = isSync;
	}
	@Column(name = "sync_time")
	public String getSyncTime() {
		return syncTime;
	}
	public void setSyncTime(String syncTime) {
		this.syncTime = syncTime;
	}
	@Column(name = "verifier_id")
	public Long getVerifierId() {
		return verifierId;
	}
	public void setVerifierId(Long verifierId) {
		this.verifierId = verifierId;
	}
	
	
	

}
