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
@Table(name = "registration_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RegistrationStatus {

	private Long registrationStatusId;
	private String registrationCount;
	private TwoWaySmsMobile twoWaySmsMobile;
	private Booth booth;
	private String mobile;
	private String boothType;
	private String isDeleted;
	private Date insertedTime;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "registration_status_id", unique = true, nullable = false)
	public Long getRegistrationStatusId() {
		return registrationStatusId;
	}

	public void setRegistrationStatusId(Long registrationStatusId) {
		this.registrationStatusId = registrationStatusId;
	}

	@Column(name = "registrationCount")
	public String getRegistrationCount() {
		return registrationCount;
	}

	public void setRegistrationCount(String registrationCount) {
		this.registrationCount = registrationCount;
	}

	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "twoway_sms_mobile_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TwoWaySmsMobile getTwoWaySmsMobile() {
		return twoWaySmsMobile;
	}

	public void setTwoWaySmsMobile(TwoWaySmsMobile twoWaySmsMobile) {
		this.twoWaySmsMobile = twoWaySmsMobile;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "booth_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Booth getBooth() {
		return booth;
	}

	public void setBooth(Booth booth) {
		this.booth = booth;
	}

	@Column(name = "mobile_no")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Column(name = "booth_type")
	public String getBoothType() {
		return boothType;
	}

	public void setBoothType(String boothType) {
		this.boothType = boothType;
	}
	
	
}
