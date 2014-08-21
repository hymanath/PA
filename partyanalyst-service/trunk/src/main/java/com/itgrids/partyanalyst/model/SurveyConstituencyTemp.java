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
@Table(name = "survey_constituency_temp")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SurveyConstituencyTemp extends BaseModel implements Serializable{

	private Long surveyConstituencyTempId;
	private Constituency constituency;
	private Long totalVoters;
	private Long totalBooths;
	private Long boothsTodo;
	private String surveyType;
	private SurveyConstituencyStatus surveyConstituencyStatusId ;
	private String state;
	
	public SurveyConstituencyTemp()
	{
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "survey_constituency_temp_id", unique = true, nullable = false)
	public Long getSurveyConstituencyTempId() {
		return surveyConstituencyTempId;
	}
	public void setSurveyConstituencyTempId(Long surveyConstituencyTempId) {
		this.surveyConstituencyTempId = surveyConstituencyTempId;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="constituency_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}
	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}
	@Column(name = "total_voters")
	public Long getTotalVoters() {
		return totalVoters;
	}
	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}
	@Column(name = "total_booths")
	public Long getTotalBooths() {
		return totalBooths;
	}
	public void setTotalBooths(Long totalBooths) {
		this.totalBooths = totalBooths;
	}
	@Column(name = "booths_todo")
	public Long getBoothsTodo() {
		return boothsTodo;
	}
	public void setBoothsTodo(Long boothsTodo) {
		this.boothsTodo = boothsTodo;
	}
	@Column(name = "survey_type")
	public String getSurveyType() {
		return surveyType;
	}
	public void setSurveyType(String surveyType) {
		this.surveyType = surveyType;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="survey_constituency_status_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SurveyConstituencyStatus getSurveyConstituencyStatusId() {
		return surveyConstituencyStatusId;
	}
	public void setSurveyConstituencyStatusId(
			SurveyConstituencyStatus surveyConstituencyStatusId) {
		this.surveyConstituencyStatusId = surveyConstituencyStatusId;
	}
	@Column(name = "state")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
	
}
