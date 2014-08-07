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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.CasteState;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.model.SurveyWmThirdPartyStatus;
import com.itgrids.partyanalyst.model.Voter;
@Entity
@Table(name = "survey_final_data")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SurveyFinalData implements Serializable
{

	
	private static final long serialVersionUID = -7007064771731428343L;
	private Long surveyFinalDataIid;
	private Long voterId;
	private Voter voter;
	
	private String mobileNo;
	
	private String isCadre;
	private String isInfluencingPeople;
	
	
	private Long casteStateId;
	private CasteState casteState;
	private String casteName;
	
	private Long hamletId;
	private Hamlet hamlet;
	private String hamletName;
	
	private Long wardId;
	private Constituency ward;
	
	private String localArea;
	
	private Date insertedTime;
	private Date updatedTime;
	
	private SurveyWmThirdPartyStatus surveyWmThirdPartyStatus;
	private Long surveyWmThirdPartyStatusId;
	
	private Long boothId;
	private Booth booth;
	
	private Long surveyUserId;
	private SurveyUser surveyUser;
	
	private String uuid;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "survey_final_data_id", unique = true, nullable = false)
	public Long getSurveyFinalDataIid() {
		return surveyFinalDataIid;
	}
	public void setSurveyFinalDataIid(Long surveyFinalDataIid) {
		this.surveyFinalDataIid = surveyFinalDataIid;
	}
	
	@Column(name = "voter_id")
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "voter_id" ,insertable = false , updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Voter getVoter() {
		return voter;
	}
	public void setVoter(Voter voter) {
		this.voter = voter;
	}
	
	@Column(name = "mobile_number")
	public String getMobileNo() {
		return mobileNo;
	}
	
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	@Column(name = "is_cadre")
	public String getIsCadre() {
		return isCadre;
	}
	public void setIsCadre(String isCadre) {
		this.isCadre = isCadre;
	}
	
	@Column(name = "is_influence_people")
	public String getIsInfluencingPeople() {
		return isInfluencingPeople;
	}
	public void setIsInfluencingPeople(String isInfluencingPeople) {
		this.isInfluencingPeople = isInfluencingPeople;
	}
	
	@Column(name = "caste_state_id")
	public Long getCasteStateId() {
		return casteStateId;
	}
	public void setCasteStateId(Long casteStateId) {
		this.casteStateId = casteStateId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "caste_state_id" ,insertable = false , updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CasteState getCasteState() {
		return casteState;
	}
	public void setCasteState(CasteState casteState) {
		this.casteState = casteState;
	}
	
	@Column(name = "caste_name")
	public String getCasteName() {
		return casteName;
	}
	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}
	
	@Column(name = "hamlet_id")
	public Long getHamletId() {
		return hamletId;
	}
	public void setHamletId(Long hamletId) {
		this.hamletId = hamletId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hamlet_id" ,insertable = false , updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Hamlet getHamlet() {
		return hamlet;
	}
	public void setHamlet(Hamlet hamlet) {
		this.hamlet = hamlet;
	}
	
	@Column(name = "hamlet_name")
	public String getHamletName() {
		return hamletName;
	}
	public void setHamletName(String hamletName) {
		this.hamletName = hamletName;
	}
	
	@Column(name = "ward_id")
	public Long getWardId() {
		return wardId;
	}
	public void setWardId(Long wardId) {
		this.wardId = wardId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ward_id" ,insertable = false , updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getWard() {
		return ward;
	}
	public void setWard(Constituency ward) {
		this.ward = ward;
	}
	
	@Column(name="local_area")
	public String getLocalArea() {
		return localArea;
	}
	public void setLocalArea(String localArea) {
		this.localArea = localArea;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "survey_wm_third_party_status_id" ,insertable = false , updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SurveyWmThirdPartyStatus getSurveyWmThirdPartyStatus() {
		return surveyWmThirdPartyStatus;
	}
	public void setSurveyWmThirdPartyStatus(
			SurveyWmThirdPartyStatus surveyWmThirdPartyStatus) {
		this.surveyWmThirdPartyStatus = surveyWmThirdPartyStatus;
	}
	
	@Column(name="survey_wm_third_party_status_id")
	public Long getSurveyWmThirdPartyStatusId() {
		return surveyWmThirdPartyStatusId;
	}
	public void setSurveyWmThirdPartyStatusId(Long surveyWmThirdPartyStatusId) {
		this.surveyWmThirdPartyStatusId = surveyWmThirdPartyStatusId;
	}
	
	@Column(name="booth_id")
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "booth_id", insertable = false , updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Booth getBooth() {
		return booth;
	}
	public void setBooth(Booth booth) {
		this.booth = booth;
	}
	
	@Column(name="survey_user_id")
	public Long getSurveyUserId() {
		return surveyUserId;
	}
	public void setSurveyUserId(Long surveyUserId) {
		this.surveyUserId = surveyUserId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "survey_user_id", insertable = false , updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SurveyUser getSurveyUser() {
		return surveyUser;
	}
	public void setSurveyUser(SurveyUser surveyUser) {
		this.surveyUser = surveyUser;
	}
	
	@Column(name="uuid")
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	
	
	
	
}
