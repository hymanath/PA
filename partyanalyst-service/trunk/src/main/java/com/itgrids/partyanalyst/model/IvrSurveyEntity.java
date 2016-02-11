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
@Table(name = "ivr_survey_entity")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IvrSurveyEntity extends BaseModel implements Serializable {
	
	
	private Long ivrSurveyEntityId;
	private Long ivrSurveyEntityTypeId;
	private Long ivrSurveyId;
	private Long entityValue;
	
	private IvrSurveyEntityType ivrSurveyEntityType;
	private IvrSurvey ivrSurvey;
	
	@Column(name="ivr_survey_entity_id")
	public Long getIvrSurveyEntityId() {
		return ivrSurveyEntityId;
	}
	public void setIvrSurveyEntityId(Long ivrSurveyEntityId) {
		this.ivrSurveyEntityId = ivrSurveyEntityId;
	}
	@Column(name="ivr_survey_entity_type_id")
	public Long getIvrSurveyEntityTypeId() {
		return ivrSurveyEntityTypeId;
	}
	public void setIvrSurveyEntityTypeId(Long ivrSurveyEntityTypeId) {
		this.ivrSurveyEntityTypeId = ivrSurveyEntityTypeId;
	}
	@Column(name="ivr_survey_id")
	public Long getIvrSurveyId() {
		return ivrSurveyId;
	}
	public void setIvrSurveyId(Long ivrSurveyId) {
		this.ivrSurveyId = ivrSurveyId;
	}
	@Column(name="entity_value")
	public Long getEntityValue() {
		return entityValue;
	}
	public void setEntityValue(Long entityValue) {
		this.entityValue = entityValue;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="ivr_survey_entity_type_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public IvrSurveyEntityType getIvrSurveyEntityType() {
		return ivrSurveyEntityType;
	}
	public void setIvrSurveyEntityType(IvrSurveyEntityType ivrSurveyEntityType) {
		this.ivrSurveyEntityType = ivrSurveyEntityType;
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
	
	
}
