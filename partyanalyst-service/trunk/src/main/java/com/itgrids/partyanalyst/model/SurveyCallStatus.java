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
	private Date insertedDate;
	private Date updatedDate;
	private Booth booth;
	
	
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
	@JoinColumn(name = "user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
	@JoinColumn(name = "voter_id")
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
	@JoinColumn(name = "booth_id" )
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Booth getBooth() {
		return booth;
	}
	public void setBooth(Booth booth) {
		this.booth = booth;
	}
	
}
