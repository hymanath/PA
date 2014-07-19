package com.itgrids.partyanalyst.model;

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
@Table(name="survey_constituency" )
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SurveyConstituency {
	
	private Long surveyConstituencyId;
	private Constituency constituency;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="survey_constituency_id")
	public Long getSurveyConstituencyId() {
		return surveyConstituencyId;
	}
	public void setSurveyConstituencyId(Long surveyConstituencyId) {
		this.surveyConstituencyId = surveyConstituencyId;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "constituency_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}
	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}
	
	
	

}
