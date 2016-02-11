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
@Table(name="ivr_survey_round")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IvrSurveyRound extends BaseModel implements Serializable{

	
	private Long ivrSurveyRoundId;
	private String roundName;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ivr_survey_round_id", unique=true, nullable=false)
	public Long getIvrSurveyRoundId() {
		return ivrSurveyRoundId;
	}
	public void setIvrSurveyRoundId(Long ivrSurveyRoundId) {
		this.ivrSurveyRoundId = ivrSurveyRoundId;
	}
	
	@Column(name="round_name")
	public String getRoundName() {
		return roundName;
	}
	public void setRoundName(String roundName) {
		this.roundName = roundName;
	}
	
}
