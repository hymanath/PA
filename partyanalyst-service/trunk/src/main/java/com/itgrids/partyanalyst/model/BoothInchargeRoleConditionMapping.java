package com.itgrids.partyanalyst.model;

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
@Table(name = "booth_incharge_role_condition_mapping")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BoothInchargeRoleConditionMapping {

	private Long boothInchargeRoleConditionMappingId;
	private Long boothId;
	private Long addressId;
	private Long boothInchargeRoleConditionId;
	private Long boothInchargeEnrollmentId;
	private String isConfirmed;
	private Date startDate;
	private Date completedDate;
	
	private Booth booth;
	private BoothInchargeRoleCondition boothInchargeRoleCondition;
	private BoothInchargeEnrollment boothInchargeEnrollment;
	private UserAddress address;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "booth_incharge_role_condition_mapping_id", unique = true, nullable = false)
	public Long getBoothInchargeRoleConditionMappingId() {
		return boothInchargeRoleConditionMappingId;
	}
	public void setBoothInchargeRoleConditionMappingId(
			Long boothInchargeRoleConditionMappingId) {
		this.boothInchargeRoleConditionMappingId = boothInchargeRoleConditionMappingId;
	}
	
	@Column(name="booth_id")
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	
	@Column(name="address_id")
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}	
	
	@Column(name="booth_incharge_role_condition_id")
	public Long getBoothInchargeRoleConditionId() {
		return boothInchargeRoleConditionId;
	}
	public void setBoothInchargeRoleConditionId(Long boothInchargeRoleConditionId) {
		this.boothInchargeRoleConditionId = boothInchargeRoleConditionId;
	}
	
	@Column(name="booth_incharge_enrollment_id")
	public Long getBoothInchargeEnrollmentId() {
		return boothInchargeEnrollmentId;
	}
	public void setBoothInchargeEnrollmentId(Long boothInchargeEnrollmentId) {
		this.boothInchargeEnrollmentId = boothInchargeEnrollmentId;
	}
	
	@Column(name="is_confirmed")
	public String getIsConfirmed() {
		return isConfirmed;
	}
	public void setIsConfirmed(String isConfirmed) {
		this.isConfirmed = isConfirmed;
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
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="booth_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Booth getBooth() {
		return booth;
	}
	public void setBooth(Booth booth) {
		this.booth = booth;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="booth_incharge_role_condition_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public BoothInchargeRoleCondition getBoothInchargeRoleCondition() {
		return boothInchargeRoleCondition;
	}
	public void setBoothInchargeRoleCondition(
			BoothInchargeRoleCondition boothInchargeRoleCondition) {
		this.boothInchargeRoleCondition = boothInchargeRoleCondition;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="booth_incharge_enrollment_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public BoothInchargeEnrollment getBoothInchargeEnrollment() {
		return boothInchargeEnrollment;
	}
	public void setBoothInchargeEnrollment(
			BoothInchargeEnrollment boothInchargeEnrollment) {
		this.boothInchargeEnrollment = boothInchargeEnrollment;
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
	
	
	
	
}
