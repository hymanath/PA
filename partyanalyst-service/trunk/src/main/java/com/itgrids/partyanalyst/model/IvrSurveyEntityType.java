package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "ivr_survey_entity_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IvrSurveyEntityType extends BaseModel implements Serializable{
	
	private Long ivrSurveyEntityTypeId;
	private String  type;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ivr_survey_entity_type_id", unique = true, nullable = false)
	public Long getIvrSurveyEntityTypeId() {
		return ivrSurveyEntityTypeId;
	}
	public void setIvrSurveyEntityTypeId(Long ivrSurveyEntityTypeId) {
		this.ivrSurveyEntityTypeId = ivrSurveyEntityTypeId;
	}
	@Column(name="type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
