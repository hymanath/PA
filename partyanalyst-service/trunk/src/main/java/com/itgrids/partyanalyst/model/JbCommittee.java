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

@Entity
@Table(name = "jb_committee")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class JbCommittee extends BaseModel implements java.io.Serializable {


	private Long jbCommitteeId;
	private String committeeName;
	private Long jbCommitteeLevelId;
	private Long jbCommitteeLevelValue;
	private String isCommitteeConfirmed;
	private Date startDate;
	private Date completedDate;
	private Long addressId;
	private Long jbCommitteeEnrollmentId;
	private Long jbCommitteeConfirmRuleId;
	private String isDeleted;
	
	private JbCommitteeLevel jbCommitteeLevel;
	private JbCommitteeEnrollment jbCommitteeEnrollment;
	private JbCommitteeConfirmRule jbCommitteeConfirmRule;
	private UserAddress userAddress;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "jb_committee_id", unique = true, nullable = false)
	public Long getJbCommitteeId() {
		return jbCommitteeId;
	}
	public void setJbCommitteeId(Long jbCommitteeId) {
		this.jbCommitteeId = jbCommitteeId;
	}
	@Column(name="committee_name")
	public String getCommitteeName() {
		return committeeName;
	}
	public void setCommitteeName(String committeeName) {
		this.committeeName = committeeName;
	}
	@Column(name="jb_committee_level_id")
	public Long getJbCommitteeLevelId() {
		return jbCommitteeLevelId;
	}
	public void setJbCommitteeLevelId(Long jbCommitteeLevelId) {
		this.jbCommitteeLevelId = jbCommitteeLevelId;
	}
	@Column(name="jb_committee_level_value")
	public Long getJbCommitteeLevelValue() {
		return jbCommitteeLevelValue;
	}
	public void setJbCommitteeLevelValue(Long jbCommitteeLevelValue) {
		this.jbCommitteeLevelValue = jbCommitteeLevelValue;
	}
	@Column(name="is_committee_confirmed")
	public String getIsCommitteeConfirmed() {
		return isCommitteeConfirmed;
	}
	public void setIsCommitteeConfirmed(String isCommitteeConfirmed) {
		this.isCommitteeConfirmed = isCommitteeConfirmed;
	}
	@Column(name="start_date")
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@Column(name="completed_date")
	public Date getCompletedDate() {
		return completedDate;
	}
	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}
	@Column(name="address_id")
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	@Column(name="jb_committee_enrollment_id")
	public Long getJbCommitteeEnrollmentId() {
		return jbCommitteeEnrollmentId;
	}
	public void setJbCommitteeEnrollmentId(Long jbCommitteeEnrollmentId) {
		this.jbCommitteeEnrollmentId = jbCommitteeEnrollmentId;
	}
	@Column(name="jb_committee_confirm_rule_id")
	public Long getJbCommitteeConfirmRuleId() {
		return jbCommitteeConfirmRuleId;
	}
	public void setJbCommitteeConfirmRuleId(Long jbCommitteeConfirmRuleId) {
		this.jbCommitteeConfirmRuleId = jbCommitteeConfirmRuleId;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "jb_committee_level_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public JbCommitteeLevel getJbCommitteeLevel() {
		return jbCommitteeLevel;
	}
	public void setJbCommitteeLevel(JbCommitteeLevel jbCommitteeLevel) {
		this.jbCommitteeLevel = jbCommitteeLevel;
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
	@JoinColumn(name = "jb_committee_confirm_rule_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public JbCommitteeConfirmRule getJbCommitteeConfirmRule() {
		return jbCommitteeConfirmRule;
	}
	public void setJbCommitteeConfirmRule(
			JbCommitteeConfirmRule jbCommitteeConfirmRule) {
		this.jbCommitteeConfirmRule = jbCommitteeConfirmRule;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "address_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserAddress getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}
	
	
	
}
