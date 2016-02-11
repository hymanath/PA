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
@Table(name = "ivr_survey_scope")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IvrSurveyScope extends BaseModel implements Serializable{
	
	private Long ivrSurveyScopeId;
	private Long ivrSurveyId;
	private Long ivregionScopesId;;
	
	private IvrSurvey ivrSurvey;
	private IvrRegionScopes ivrRegionScopes;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ivr_survey_scope_id", unique = true, nullable = false)
	public Long getIvrSurveyScopeId() {
		return ivrSurveyScopeId;
	}
	public void setIvrSurveyScopeId(Long ivrSurveyScopeId) {
		this.ivrSurveyScopeId = ivrSurveyScopeId;
	}
	@Column(name="ivr_survey_id")
	public Long getIvrSurveyId() {
		return ivrSurveyId;
	}
	public void setIvrSurveyId(Long ivrSurveyId) {
		this.ivrSurveyId = ivrSurveyId;
	}
	@Column(name="ivr_region_scopes_id")
	public Long getIvregionScopesId() {
		return ivregionScopesId;
	}
	public void setIvregionScopesId(Long ivregionScopesId) {
		this.ivregionScopesId = ivregionScopesId;
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
