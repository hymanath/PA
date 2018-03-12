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
@Table(name = "nomination_post_candidate")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NominationPostCandidate extends BaseModel implements Serializable{

	private Long nominationPostCandidateId;
	private Long tdpCadreId;
	private Long voterId;
	private String candidateName;
	private String mobileNo;
	private Long insertedBy;
	private Date insertedTime;
	private Long updatedBy;
	private Date updatedTime;
	private String isDeleted;
	private String houseno;
	private String gender;
	private Long age;
	private Date dob1;
	private String dob;
	private String relativename;
	private String relativetype;
	private String imageurl;
	private Long castestateId;
	private Long addressId;
	private Long nominatedPostAgeRangeId;
	
	private UserAddress address;
	private TdpCadre tdpCadre;
	private Voter voter;
	private CasteState casteState;
	private NominatedPostAgeRange nominatedPostAgeRange;
	private String deletedRemarks;
	private Long cadreDeletedReasonId;
	private CadreDeleteReason cadreDeleteReason;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "nomination_post_candidate_id", unique = true, nullable = false)
	public Long getNominationPostCandidateId() {
		return nominationPostCandidateId;
	}
	public void setNominationPostCandidateId(Long nominationPostCandidateId) {
		this.nominationPostCandidateId = nominationPostCandidateId;
	}
	
	@Column(name = "tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	
	@Column(name = "voter_id")
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	
	@Column(name = "candidate_name")
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	
	@Column(name = "mobile_no")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="tdp_cadre_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}
	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="voter_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Voter getVoter() {
		return voter;
	}
	public void setVoter(Voter voter) {
		this.voter = voter;
	}
	
	@Column(name="inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name="updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name="house_no")
	public String getHouseno() {
		return houseno;
	}
	public void setHouseno(String houseno) {
		this.houseno = houseno;
	}
	@Column(name="age")
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	@Column(name="relative_name")
	public String getRelativename() {
		return relativename;
	}
	public void setRelativename(String relativename) {
		this.relativename = relativename;
	}
	@Column(name="relative_type")
	public String getRelativetype() {
		return relativetype;
	}
	public void setRelativetype(String relativetype) {
		this.relativetype = relativetype;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="address_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserAddress getAddress() {
		return address;
	}
	public void setAddress(UserAddress address) {
		this.address = address;
	}
	
	@Column(name = "address_id")
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	
	@Column(name = "gender")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Column(name = "image_url")
	public String getImageurl() {
		return imageurl;
	}
	@Column(name = "date_of_birth")
	public Date getDob1() {
		return dob1;
	}
	public void setDob1(Date dob1) {
		this.dob1 = dob1;
	}
	public void setImageurl(String imageurl) {     
		this.imageurl = imageurl;
	}
	
	@Column(name = "caste_state_id")
	public Long getCastestateId() {
		return castestateId;
	}
	public void setCastestateId(Long castestateId) {
		this.castestateId = castestateId;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="caste_state_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CasteState getCasteState() {
		return casteState;
	}
	public void setCasteState(CasteState casteState) {
		this.casteState = casteState;
	}
	@Column(name = "nominated_post_age_range_id")
	public Long getNominatedPostAgeRangeId() {  
		return nominatedPostAgeRangeId;
	}
	public void setNominatedPostAgeRangeId(Long nominatedPostAgeRangeId) {
		this.nominatedPostAgeRangeId = nominatedPostAgeRangeId;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="nominated_post_age_range_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)  
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public NominatedPostAgeRange getNominatedPostAgeRange() {
		return nominatedPostAgeRange;
	}
	public void setNominatedPostAgeRange(NominatedPostAgeRange nominatedPostAgeRange) {
		this.nominatedPostAgeRange = nominatedPostAgeRange;
	}
	
	@Column(name = "deleted_remarks")
	public String getDeletedRemarks() {
		return deletedRemarks;
	}
	public void setDeletedRemarks(String deletedRemarks) {
		this.deletedRemarks = deletedRemarks;
	}
	
	@Column(name = "cadre_delete_reason_id")
	public Long getCadreDeletedReasonId() {
		return cadreDeletedReasonId;
	}
	public void setCadreDeletedReasonId(Long cadreDeletedReasonId) {
		this.cadreDeletedReasonId = cadreDeletedReasonId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="cadre_delete_reason_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)  
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreDeleteReason getCadreDeleteReason() {
		return cadreDeleteReason;
	}
	public void setCadreDeleteReason(CadreDeleteReason cadreDeleteReason) {
		this.cadreDeleteReason = cadreDeleteReason;
	}
	
	
}
