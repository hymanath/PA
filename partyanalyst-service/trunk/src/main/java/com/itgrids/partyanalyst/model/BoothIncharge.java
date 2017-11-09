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
@Table(name = "booth_incharge")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BoothIncharge extends BaseModel implements Serializable{
	
	private Long boothInchargeId;
	private Long tdpCadreId;
	private String isActive;
	private String isDeleted;
	private Long boothInchargeEnrollmentId;
	private Long insertedBy;
	private Long updatedBy;
	private Date insertedTime;
	private Date updatedTime;
	private Long boothInchargeRoleConditionMappingId;
	private Long boothInchargeSerialNoRangeId;
	private Long addedBoothInhcargeConditionId;
	private TdpCadre tdpCadre;
	private BoothInchargeEnrollment boothInchargeEnrollment;
	private User user;
	private BoothInchargeRoleConditionMapping boothInchargeRoleConditionMapping;
	private BoothInchargeSerialNoRange boothInchargeSerialNoRange;
	private BoothInchargeCondition addedBoothInchargeCondition;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "booth_incharge", unique = true, nullable = false)
	public Long getBoothInchargeId() {
		return boothInchargeId;
	}
	public void setBoothInchargeId(Long boothInchargeId) {
		this.boothInchargeId = boothInchargeId;
	}
			
	@Column(name="tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	
	@Column(name="is_active")
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@Column(name="booth_incharge_enrollment_id")
	public Long getBoothInchargeEnrollmentId() {
		return boothInchargeEnrollmentId;
	}
	public void setBoothInchargeEnrollmentId(Long boothInchargeEnrollmentId) {
		this.boothInchargeEnrollmentId = boothInchargeEnrollmentId;
	}
	
	@Column(name="inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	
	@Column(name="updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
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
	@Column(name = "booth_incharge_role_condition_mapping_id")
	public Long getBoothInchargeRoleConditionMappingId() {
		return boothInchargeRoleConditionMappingId;
	}
	public void setBoothInchargeRoleConditionMappingId(
			Long boothInchargeRoleConditionMappingId) {
		this.boothInchargeRoleConditionMappingId = boothInchargeRoleConditionMappingId;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="booth_incharge_role_condition_mapping_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public BoothInchargeRoleConditionMapping getBoothInchargeRoleConditionMapping() {
		return boothInchargeRoleConditionMapping;
	}
	public void setBoothInchargeRoleConditionMapping(
			BoothInchargeRoleConditionMapping boothInchargeRoleConditionMapping) {
		this.boothInchargeRoleConditionMapping = boothInchargeRoleConditionMapping;
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
	@JoinColumn(name="booth_incharge_enrollment_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public BoothInchargeEnrollment getBoothInchargeEnrollment() {
		return boothInchargeEnrollment;
	}
	public void setBoothInchargeEnrollment(BoothInchargeEnrollment boothInchargeEnrollment) {
		this.boothInchargeEnrollment = boothInchargeEnrollment;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="inserted_by", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Column(name="booth_incharge_serial_no_range_id")
	public Long getBoothInchargeSerialNoRangeId() {
		return boothInchargeSerialNoRangeId;
	}
	public void setBoothInchargeSerialNoRangeId(Long boothInchargeSerialNoRangeId) {
		this.boothInchargeSerialNoRangeId = boothInchargeSerialNoRangeId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="booth_incharge_serial_no_range_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public BoothInchargeSerialNoRange getBoothInchargeSerialNoRange() {
		return boothInchargeSerialNoRange;
	}
	public void setBoothInchargeSerialNoRange(
			BoothInchargeSerialNoRange boothInchargeSerialNoRange) {
		this.boothInchargeSerialNoRange = boothInchargeSerialNoRange;
	}
	
	@Column(name="added_booth_incharge_condition_id")
	public Long getAddedBoothInhcargeConditionId() {
		return addedBoothInhcargeConditionId;
	}
	public void setAddedBoothInhcargeConditionId(Long addedBoothInhcargeConditionId) {
		this.addedBoothInhcargeConditionId = addedBoothInhcargeConditionId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="added_booth_incharge_condition_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public BoothInchargeCondition getAddedBoothInchargeCondition() {
		return addedBoothInchargeCondition;
	}
	public void setAddedBoothInchargeCondition(
			BoothInchargeCondition addedBoothInchargeCondition) {
		this.addedBoothInchargeCondition = addedBoothInchargeCondition;
	}
	
	
}
