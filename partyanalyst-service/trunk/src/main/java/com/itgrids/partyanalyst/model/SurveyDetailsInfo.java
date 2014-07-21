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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;
import javax.persistence.UniqueConstraint;

/**
 * 
 * @author Prasad Thiragabathina
 * 
 * This Model Describes About Stores all survey details .
 *
 */
@Entity
@Table(name = "survey_details_info",uniqueConstraints=@UniqueConstraint(columnNames={"voter_id" , "survey_user_id" ,"uuid"}))
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SurveyDetailsInfo
{

	private Long surveyDetailsInfoId;
	private SurveyUser surveyUser;
	private SurveySurveyorType surveySurveyorType;
	private Voter voter;
	private String mobileNumber;
	private String isCadre;
	private String isInfluencingPeople;
	private CasteState caste;
	private Hamlet hamlet;
	private String localArea;
	private String casteName;
	private String hamletName;
	private Date date;
	private String longitude;
	private String latitude;
	private Date insertedTime;
	private Date updatedTime;
	private Booth booth;
	private Long boothId;
	private String verified;
	private String uuid;
	private Integer statusId;
	private Long surveySurveyorTypeId;
	//is_houseNO_point
	private String HouseNoPoint;
	private String isDelete;
	
	
	
	
	
	
	
	
	
	
	


	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "survey_details_info_id", unique = true, nullable = false)
	public Long getSurveyDetailsInfoId() {
		return surveyDetailsInfoId;
	}
	public void setSurveyDetailsInfoId(Long surveyDetailsInfoId) {
		this.surveyDetailsInfoId = surveyDetailsInfoId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "survey_user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SurveyUser getSurveyUser() {
		return surveyUser;
	}
	public void setSurveyUser(SurveyUser surveyUser) {
		this.surveyUser = surveyUser;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "survey_surveior_type_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SurveySurveyorType getSurveySurveyorType() {
		return surveySurveyorType;
	}
	public void setSurveySurveyorType(SurveySurveyorType surveySurveyorType) {
		this.surveySurveyorType = surveySurveyorType;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "voter_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Voter getVoter() {
		return voter;
	}
	public void setVoter(Voter voter) {
		this.voter = voter;
	}
	
	@Column(name="mobile_number")
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	@Column(name="cadre")
	public String getIsCadre() {
		return isCadre;
	}
	public void setIsCadre(String isCadre) {
		this.isCadre = isCadre;
	}
	@Column(name="influencing_people")
	public String getIsInfluencingPeople() {
		return isInfluencingPeople;
	}
	public void setIsInfluencingPeople(String isInfluencingPeople) {
		this.isInfluencingPeople = isInfluencingPeople;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "caste_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CasteState getCaste() {
		return caste;
	}
	public void setCaste(CasteState caste) {
		this.caste = caste;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hamlet_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Hamlet getHamlet() {
		return hamlet;
	}
	public void setHamlet(Hamlet hamlet) {
		this.hamlet = hamlet;
	}
	
	@Column(name="local_area")
	public String getLocalArea() {
		return localArea;
	}
	public void setLocalArea(String localArea) {
		this.localArea = localArea;
	}
	
	@Column(name="caste_name")
	public String getCasteName() {
		return casteName;
	}
	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}
	
	@Column(name="hamelt_name")
	public String getHamletName() {
		return hamletName;
	}
	public void setHamletName(String hamletName) {
		this.hamletName = hamletName;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date")
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Column(name="longitude")
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	@Column(name="latitude")
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	@Temporal(TemporalType.TIMESTAMP)
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "booth_id" ,insertable = false , updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Booth getBooth() {
		return booth;
	}
	public void setBooth(Booth booth) {
		this.booth = booth;
	}
	
	@Column(name="booth_id")
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	
	@Column(name="uuid")	
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	@Column(name="status_id")
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	@Column(name="survey_surveior_type_id")
	public Long getSurveySurveyorTypeId() {
		return surveySurveyorTypeId;
	}
	public void setSurveySurveyorTypeId(Long surveySurveyorTypeId) {
		this.surveySurveyorTypeId = surveySurveyorTypeId;
	}
	
	@Column(name="verified")
	public String getVerified() {
		return verified;
	}
	public void setVerified(String verified) {
		this.verified = verified;
	}
	@Column(name="is_houseNO_point")
	public String getHouseNoPoint() {
		return HouseNoPoint;
	}
	public void setHouseNoPoint(String houseNoPoint) {
		HouseNoPoint = houseNoPoint;
	}
	/*public String getIsHouseNoPoint() {
		return isHouseNoPoint;
	}
	public void setIsHouseNoPoint(String isHouseNoPoint) {
		this.isHouseNoPoint = isHouseNoPoint;
	}*/
	@Column(name="is_delete",insertable=false)
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	
	
	
	
	
	
}
