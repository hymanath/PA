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
@Table(name = "ivr_survey_location")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IvrSurveyLocation extends BaseModel implements Serializable {
	
	private Long ivrSurveyLocationId;
	private Long ivrSurveyId;
	private Long ivrSurveyRoundId;
	private Long ivrRegionScopesId;
	private Long scopeValue;
	private String isDeleted;
	private Long insertedBy;
	private Long updatedBy;
	private Date insertedTime;
	private Date updatedTime;
	
	
	private User insertedUser;
	private User updatedUser;
    private IvrSurvey ivrSurvey;
	private IvrSurveyRound ivrSurveyRound;
	private IvrRegionScopes ivrRegionScopes;
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ivr_survey_location_id", unique = true, nullable = false)
	public Long getIvrSurveyLocationId() {
		return ivrSurveyLocationId;
	}
	public void setIvrSurveyLocationId(Long ivrSurveyLocationId) {
		this.ivrSurveyLocationId = ivrSurveyLocationId;
	}
	@Column(name="ivr_survey_id")
	public Long getIvrSurveyId() {
		return ivrSurveyId;
	}
	public void setIvrSurveyId(Long ivrSurveyId) {
		this.ivrSurveyId = ivrSurveyId;
	}
	@Column(name="ivr_survey_round_id")
	public Long getIvrSurveyRoundId() {
		return ivrSurveyRoundId;
	}
	public void setIvrSurveyRoundId(Long ivrSurveyRoundId) {
		this.ivrSurveyRoundId = ivrSurveyRoundId;
	}
	@Column(name="ivr_region_scopes_id")
	public Long getIvrRegionScopesId() {
		return ivrRegionScopesId;
	}
	public void setIvrRegionScopesId(Long ivrRegionScopesId) {
		this.ivrRegionScopesId = ivrRegionScopesId;
	}
	@Column(name="scope_value")
	public Long getScopeValue() {
		return scopeValue;
	}
	public void setScopeValue(Long scopeValue) {
		this.scopeValue = scopeValue;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name = "inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	@Column(name = "updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@Column(name = "updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="inserted_by", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getInsertedUser() {
		return insertedUser;
	}
	public void setInsertedUser(User insertedUser) {
		this.insertedUser = insertedUser;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="updated_by", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUpdatedUser() {
		return updatedUser;
	}
	public void setUpdatedUser(User updatedUser) {
		this.updatedUser = updatedUser;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="ivr_survey_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public IvrSurvey getIvrSurvey() {
		return ivrSurvey;
	}
	public void setIvrSurvey(IvrSurvey ivrSurvey) {
		this.ivrSurvey = ivrSurvey;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="ivr_survey_round_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public IvrSurveyRound getIvrSurveyRound() {
		return ivrSurveyRound;
	}
	public void setIvrSurveyRound(IvrSurveyRound ivrSurveyRound) {
		this.ivrSurveyRound = ivrSurveyRound;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="ivr_region_scopes_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public IvrRegionScopes getIvrRegionScopes() {
		return ivrRegionScopes;
	}
	public void setIvrRegionScopes(IvrRegionScopes ivrRegionScopes) {
		this.ivrRegionScopes = ivrRegionScopes;
	}
}
