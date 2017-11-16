package com.itgrids.partyanalyst.model;

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

@Entity
@Table(name = "jb_committee_member")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class JbCommitteeMember extends BaseModel implements java.io.Serializable {
	
	private Long jbCommitteeMemberId;
	private Long jbCommitteeRoleId; 
	private String memberName;
	private Long age;
	private Long  casteStateId; 
	private Long casteCategoryId; 
	private String mobileNo;
	private String descripation;
	private Long tdpCadreId ;
	private Date startDate;
	private Date endDate;
	private String  isActive;
	private String status;
	private Long  jbCommitteeEnrollmentId; 
	private Long insertedUserId;
	private Long updatedUserId;
	private Date insertedTime;
	private Date updatedTime;
	private Long partyId ;
	
	private  JbCommitteeRole jbCommitteeRole;
	private CasteState casteState;
	private CasteCategory casteCategory;
	private TdpCadre tdpCadre;
	private JbCommitteeEnrollment jbCommitteeEnrollment;
	private User insertedUser;
	private User updatedUser;
	private Party party ;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "jb_committee_member_id", unique = true, nullable = false)
	public Long getJbCommitteeMemberId() {
		return jbCommitteeMemberId;
	}
	public void setJbCommitteeMemberId(Long jbCommitteeMemberId) {
		this.jbCommitteeMemberId = jbCommitteeMemberId;
	}
	@Column(name="jb_committee_role_id")
	public Long getJbCommitteeRoleId() {
		return jbCommitteeRoleId;
	}
	public void setJbCommitteeRoleId(Long jbCommitteeRoleId) {
		this.jbCommitteeRoleId = jbCommitteeRoleId;
	}
	@Column(name="member_name")
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	@Column(name="age")
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	@Column(name="caste_state_id")
	public Long getCasteStateId() {
		return casteStateId;
	}
	public void setCasteStateId(Long casteStateId) {
		this.casteStateId = casteStateId;
	}
	@Column(name="caste_category_id")
	public Long getCasteCategoryId() {
		return casteCategoryId;
	}
	public void setCasteCategoryId(Long casteCategoryId) {
		this.casteCategoryId = casteCategoryId;
	}
	@Column(name="mobile_no")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	@Column(name="descripation")
	public String getDescripation() {
		return descripation;
	}
	public void setDescripation(String descripation) {
		this.descripation = descripation;
	}
	@Column(name="tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	@Column(name="start_date")
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@Column(name="end_date")
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Column(name="is_active")
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name="jb_committee_enrollment_id")
	public Long getJbCommitteeEnrollmentId() {
		return jbCommitteeEnrollmentId;
	}
	public void setJbCommitteeEnrollmentId(Long jbCommitteeEnrollmentId) {
		this.jbCommitteeEnrollmentId = jbCommitteeEnrollmentId;
	}
	@Column(name="inserted_user_id")
	public Long getInsertedUserId() {
		return insertedUserId;
	}
	public void setInsertedUserId(Long insertedUserId) {
		this.insertedUserId = insertedUserId;
	}
	@Column(name="updated_user_id")
	public Long getUpdatedUserId() {
		return updatedUserId;
	}
	public void setUpdatedUserId(Long updatedUserId) {
		this.updatedUserId = updatedUserId;
	}
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "jb_committee_role_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public JbCommitteeRole getJbCommitteeRole() {
		return jbCommitteeRole;
	}
	public void setJbCommitteeRole(JbCommitteeRole jbCommitteeRole) {
		this.jbCommitteeRole = jbCommitteeRole;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "caste_state_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CasteState getCasteState() {
		return casteState;
	}
	public void setCasteState(CasteState casteState) {
		this.casteState = casteState;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "caste_category_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CasteCategory getCasteCategory() {
		return casteCategory;
	}
	public void setCasteCategory(CasteCategory casteCategory) {
		this.casteCategory = casteCategory;
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
	@JoinColumn(name = "jb_committee_enrollment_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public JbCommitteeEnrollment getJbCommitteeEnrollment() {
		return jbCommitteeEnrollment;
	}
	public void setJbCommitteeEnrollment(JbCommitteeEnrollment jbCommitteeEnrollment) {
		this.jbCommitteeEnrollment = jbCommitteeEnrollment;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "inserted_user_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getInsertedUser() {
		return insertedUser;
	}
	public void setInsertedUser(User insertedUser) {
		this.insertedUser = insertedUser;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "updated_user_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUpdatedUser() {
		return updatedUser;
	}
	public void setUpdatedUser(User updatedUser) {
		this.updatedUser = updatedUser;
	}
	
	@Column(name="party_id")
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "party_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Party getParty() {
		return party;
	}
	public void setParty(Party party) {
		this.party = party;
	}
	
	
	
	
	

}
