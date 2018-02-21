package com.itgrids.model;

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

@Entity
@Table(name = "mobile_app_user_work_type")
public class MobileAppUserWorkType {

	private Long mobileAppUserWorkTypeId;
	private Long mobileAppUserId;
	private Long govtWorkTypeId;
	
	
	private MobileAppUser mobileAppUser;
	private GovtWorkType govtWorkType;

	@Id
	@Column(name="mobile_app_user_work_type_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getMobileAppUserWorkTypeId() {
		return mobileAppUserWorkTypeId;
	}

	public void setMobileAppUserWorkTypeId(Long mobileAppUserWorkTypeId) {
		this.mobileAppUserWorkTypeId = mobileAppUserWorkTypeId;
	}

	@Column(name="mobile_app_user_id")
	public Long getMobileAppUserId() {
		return mobileAppUserId;
	}

	public void setMobileAppUserId(Long mobileAppUserId) {
		this.mobileAppUserId = mobileAppUserId;
	}

	@Column(name="govt_work_type_id")
	public Long getGovtWorkTypeId() {
		return govtWorkTypeId;
	}

	public void setGovtWorkTypeId(Long govtWorkTypeId) {
		this.govtWorkTypeId = govtWorkTypeId;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "mobile_app_user_id", insertable = false, updatable = false)
	public MobileAppUser getMobileAppUser() {
		return mobileAppUser;
	}

	public void setMobileAppUser(MobileAppUser mobileAppUser) {
		this.mobileAppUser = mobileAppUser;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "govt_work_type_id", insertable = false, updatable = false)
	public GovtWorkType getGovtWorkType() {
		return govtWorkType;
	}

	public void setGovtWorkType(GovtWorkType govtWorkType) {
		this.govtWorkType = govtWorkType;
	}
}
