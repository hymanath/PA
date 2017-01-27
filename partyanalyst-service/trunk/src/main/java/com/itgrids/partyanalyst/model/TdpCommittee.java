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
@Table(name = "tdp_committee")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCommittee {
	private Long tdpCommitteeId;
	private String name;
	private TdpBasicCommittee tdpBasicCommittee;
	private Long tdpBasicCommitteeId;
	private TdpCommitteeLevel tdpCommitteeLevel;
	private Long tdpCommitteeLevelId;
	private Long tdpCommitteeLevelValue;
	private String isCommitteeConfirmed;
	private String state;
	private Constituency constituency;
	private Date startedDate;
	private Date completedDate;
	private Long districtId;
	private District district;
	private Long tdpCommitteeEnrollmentId;
	
	
	private Long  userAddressId;
	private UserAddress userAddress;
	private TdpCommitteeEnrollment tdpCommitteeEnrollment;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_committee_id", unique = true, nullable = false)
	public Long getTdpCommitteeId() {
		return tdpCommitteeId;
	}
	
	public void setTdpCommitteeId(Long tdpCommitteeId) {
		this.tdpCommitteeId = tdpCommitteeId;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_basic_committee_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpBasicCommittee getTdpBasicCommittee() {
		return tdpBasicCommittee;
	}
	
	public void setTdpBasicCommittee(TdpBasicCommittee tdpBasicCommittee) {
		this.tdpBasicCommittee = tdpBasicCommittee;
	}
	
	@Column(name = "tdp_basic_committee_id")
	public Long getTdpBasicCommitteeId() {
		return tdpBasicCommitteeId;
	}
	
	public void setTdpBasicCommitteeId(Long tdpBasicCommitteeId) {
		this.tdpBasicCommitteeId = tdpBasicCommitteeId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_committee_level_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCommitteeLevel getTdpCommitteeLevel() {
		return tdpCommitteeLevel;
	}
	
	public void setTdpCommitteeLevel(TdpCommitteeLevel tdpCommitteeLevel) {
		this.tdpCommitteeLevel = tdpCommitteeLevel;
	}
	
	@Column(name = "tdp_committee_level_id")
	public Long getTdpCommitteeLevelId() {
		return tdpCommitteeLevelId;
	}
	
	public void setTdpCommitteeLevelId(Long tdpCommitteeLevelId) {
		this.tdpCommitteeLevelId = tdpCommitteeLevelId;
	}
	
	@Column(name = "tdp_committee_level_value")
	public Long getTdpCommitteeLevelValue() {
		return tdpCommitteeLevelValue;
	}
	
	public void setTdpCommitteeLevelValue(Long tdpCommitteeLevelValue) {
		this.tdpCommitteeLevelValue = tdpCommitteeLevelValue;
	}
	
	@Column(name = "is_committee_confirmed")
	public String getIsCommitteeConfirmed() {
		return isCommitteeConfirmed;
	}

	public void setIsCommitteeConfirmed(String isCommitteeConfirmed) {
		this.isCommitteeConfirmed = isCommitteeConfirmed;
	}

	@Column(name = "state")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "constituency_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}

	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}

	@Column(name = "started_date")
	public Date getStartedDate() {
		return startedDate;
	}

	public void setStartedDate(Date startedDate) {
		this.startedDate = startedDate;
	}

	@Column(name = "completed_date")
	public Date getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}

	@Column(name = "district_id")
	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "district_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}
	
	@Column(name = "address_id")
	public Long getUserAddressId() {
		return userAddressId;
	}

	public void setUserAddressId(Long userAddressId) {
		this.userAddressId = userAddressId;
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
	
	@Column(name = "tdp_committee_enrollment_id")
	public Long getTdpCommitteeEnrollmentId() {
		return tdpCommitteeEnrollmentId;
	}
	
	public void setTdpCommitteeEnrollmentId(Long tdpCommitteeEnrollmentId) {
		this.tdpCommitteeEnrollmentId = tdpCommitteeEnrollmentId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_committee_enrollment_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCommitteeEnrollment getTdpCommitteeEnrollment() {
		return tdpCommitteeEnrollment;
	}
	
	public void setTdpCommitteeEnrollment(
			TdpCommitteeEnrollment tdpCommitteeEnrollment) {
		this.tdpCommitteeEnrollment = tdpCommitteeEnrollment;
	}
}

