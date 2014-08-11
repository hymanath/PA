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
@Table(name="survey_call_status" )
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SurveyCallStatus implements java.io.Serializable{

	private Long surveyCallStatusId;
	private User user;
	private SurveyUser surveyUser;
	private Voter voter;
	private String mobileNoStatus;
	private String matchedStatus;
	private String hamletStatus;
	private Long hamletId;
	private Hamlet hamlet;
	private Date insertedDate;
	private Date updatedDate;
	private Booth booth;
	private CasteState casteState;
	
	private Long voterId;
	private Long surveyUserId;
	private Long boothId;
	private Long casteStateId;
	private Long userId;
	
	private User dvWebMoniteruser;
	private Long dvWebMonterId;
	
	private SurveyUser dvSurveyUser;
	private Long dvSurveyUserId;
	
	private CasteState dvCasteState;
	private Long dvCasteStateId;
	
	private String dvMobileNoStatus;
	private String dvMatchedStatus ;
	private String dvhamletStatus;
	private Hamlet dvHamlet;
	private Long dvHamletId;	

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="survey_call_status_id")
	public Long getSurveyCallStatusId() {
		return surveyCallStatusId;
	}
	public void setSurveyCallStatusId(Long surveyCallStatusId) {
		this.surveyCallStatusId = surveyCallStatusId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id",insertable = false , updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "survey_user_id" ,insertable = false , updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SurveyUser getSurveyUser() {
		return surveyUser;
	}
	public void setSurveyUser(SurveyUser surveyUser) {
		this.surveyUser = surveyUser;
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
	
	@Column(name="mobile_no_status")
	public String getMobileNoStatus() {
		return mobileNoStatus;
	}
	public void setMobileNoStatus(String mobileNoStatus) {
		this.mobileNoStatus = mobileNoStatus;
	}
	
	@Column(name="caste_status")
	public String getMatchedStatus() {
		return matchedStatus;
	}
	public void setMatchedStatus(String matchedStatus) {
		this.matchedStatus = matchedStatus;
	}
	
	@Column(name="inserted_date")
	public Date getInsertedDate() {
		return insertedDate;
	}
	public void setInsertedDate(Date insertedDate) {
		this.insertedDate = insertedDate;
	}
	
	@Column(name="updated_date")
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_caste_id",insertable = false , updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CasteState getCasteState() {
		return casteState;
	}
	public void setCasteState(CasteState casteState) {
		this.casteState = casteState;
	}
	
	@Column(name="voter_id")
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	
	@Column(name="survey_user_id")
	public Long getSurveyUserId() {
		return surveyUserId;
	}
	public void setSurveyUserId(Long surveyUserId) {
		this.surveyUserId = surveyUserId;
	}
	
	@Column(name="booth_id")
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	
	@Column(name="updated_caste_id")
	public Long getCasteStateId() {
		return casteStateId;
	}
	public void setCasteStateId(Long casteStateId) {
		this.casteStateId = casteStateId;
	}
	
	@Column(name="user_id")
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dv_web_moniter_user_id",insertable = false , updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getDvWebMoniteruser() {
		return dvWebMoniteruser;
	}
	public void setDvWebMoniteruser(User dvWebMoniteruser) {
		this.dvWebMoniteruser = dvWebMoniteruser;
	}
	
	@Column(name="dv_web_moniter_user_id")
	public Long getDvWebMonterId() {
		return dvWebMonterId;
	}
	public void setDvWebMonterId(Long dvWebMonterId) {
		this.dvWebMonterId = dvWebMonterId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dv_survey_user_id" ,insertable = false , updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SurveyUser getDvSurveyUser() {
		return dvSurveyUser;
	}
	public void setDvSurveyUser(SurveyUser dvSurveyUser) {
		this.dvSurveyUser = dvSurveyUser;
	}
	
	@Column(name="dv_survey_user_id")
	public Long getDvSurveyUserId() {
		return dvSurveyUserId;
	}
	public void setDvSurveyUserId(Long dvSurveyUserId) {
		this.dvSurveyUserId = dvSurveyUserId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dv_updated_caste_status_id",insertable = false , updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CasteState getDvCasteState() {
		return dvCasteState;
	}
	public void setDvCasteState(CasteState dvCasteState) {
		this.dvCasteState = dvCasteState;
	}
	
	@Column(name = "dv_updated_caste_status_id")
	public Long getDvCasteStateId() {
		return dvCasteStateId;
	}
	public void setDvCasteStateId(Long dvCasteStateId) {
		this.dvCasteStateId = dvCasteStateId;
	}
	
	@Column(name = "dv_mobile_no_status")
	public String getDvMobileNoStatus() {
		return dvMobileNoStatus;
	}
	public void setDvMobileNoStatus(String dvMobileNoStatus) {
		this.dvMobileNoStatus = dvMobileNoStatus;
	}
	
	@Column(name = "dv_caste_status")
	public String getDvMatchedStatus() {
		return dvMatchedStatus;
	}
	public void setDvMatchedStatus(String dvMatchedStatus) {
		this.dvMatchedStatus = dvMatchedStatus;
	}
	
	@Column(name = "hamlet_status")
	public String getHamletStatus() {
		return hamletStatus;
	}
	public void setHamletStatus(String hamletStatus) {
		this.hamletStatus = hamletStatus;
	}
	
	@Column(name="updated_hamlet_id")
	public Long getHamletId() {
		return hamletId;
	}
	public void setHamletId(Long hamletId) {
		this.hamletId = hamletId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_hamlet_id",insertable = false , updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Hamlet getHamlet() {
		return hamlet;
	}
	
	public void setHamlet(Hamlet hamlet) {
		this.hamlet = hamlet;
	}
	
	@Column(name = "dv_hamlet_status")
	public String getDvhamletStatus() {
		return dvhamletStatus;
	}
	public void setDvhamletStatus(String dvhamletStatus) {
		this.dvhamletStatus = dvhamletStatus;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dv_updated_hamlet_id",insertable = false , updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)	
	public Hamlet getDvHamlet() {
		return dvHamlet;
	}
	public void setDvHamlet(Hamlet dvHamlet) {
		this.dvHamlet = dvHamlet;
	}
	
	@Column(name="dv_updated_hamlet_id")
	public Long getDvHamletId() {
		return dvHamletId;
	}
	public void setDvHamletId(Long dvHamletId) {
		this.dvHamletId = dvHamletId;
	}

	
	
	
}
