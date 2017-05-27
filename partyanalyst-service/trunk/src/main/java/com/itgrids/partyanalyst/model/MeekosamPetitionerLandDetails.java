package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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
@Table(name = "meekosam_petitioner_land_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MeekosamPetitionerLandDetails extends BaseModel implements Serializable {
	private Long meekosamPetitionerLandDetailsId;
	private Long alertId;
	private Long meekosamPetitionerId;
	private Long locationLevelId;
	private Long locationValue;
	private Long userAddressId;
	private Long surveyNo;
	private Long landInAcres;
	private Long landInCents;
	
	private Alert alert;
	private MeekosamPetitioner meekosamPetitioner;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "meekosam_petitioner_land_details_id", unique = true, nullable = false)
	public Long getMeekosamPetitionerLandDetailsId() {
		return meekosamPetitionerLandDetailsId;
	}
	public void setMeekosamPetitionerLandDetailsId(
			Long meekosamPetitionerLandDetailsId) {
		this.meekosamPetitionerLandDetailsId = meekosamPetitionerLandDetailsId;
	}
	@Column(name = "alert_id")
	public Long getAlertId() {
		return alertId;
	}
	public void setAlertId(Long alertId) {
		this.alertId = alertId;
	}
	@Column(name = "meekosam_petitioner_id")
	public Long getMeekosamPetitionerId() {
		return meekosamPetitionerId;
	}
	public void setMeekosamPetitionerId(Long meekosamPetitionerId) {
		this.meekosamPetitionerId = meekosamPetitionerId;
	}
	@Column(name = "location_level_id")
	public Long getLocationLevelId() {
		return locationLevelId;
	}
	public void setLocationLevelId(Long locationLevelId) {
		this.locationLevelId = locationLevelId;
	}
	@Column(name = "location_value")
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	@Column(name = "user_address_id")
	public Long getUserAddressId() {
		return userAddressId;
	}
	public void setUserAddressId(Long userAddressId) {
		this.userAddressId = userAddressId;
	}
	@Column(name = "survey_no")
	public Long getSurveyNo() {
		return surveyNo;
	}
	public void setSurveyNo(Long surveyNo) {
		this.surveyNo = surveyNo;
	}
	@Column(name = "land_in_acres")
	public Long getLandInAcres() {
		return landInAcres;
	}
	public void setLandInAcres(Long landInAcres) {
		this.landInAcres = landInAcres;
	}
	@Column(name = "land_in_cents")
	public Long getLandInCents() {
		return landInCents;
	}
	public void setLandInCents(Long landInCents) {
		this.landInCents = landInCents;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="alert_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Alert getAlert() {
		return alert;
	}
	public void setAlert(Alert alert) {
		this.alert = alert;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="meekosam_petitioner_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public MeekosamPetitioner getMeekosamPetitioner() {
		return meekosamPetitioner;
	}
	public void setMeekosamPetitioner(MeekosamPetitioner meekosamPetitioner) {
		this.meekosamPetitioner = meekosamPetitioner;
	}
}
