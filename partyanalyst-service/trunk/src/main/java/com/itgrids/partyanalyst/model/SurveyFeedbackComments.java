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
@Table(name = "survey_feedback_comments")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SurveyFeedbackComments {
	
	
	private Long surveyFeedbackCommentsId;
	private Long locationScopeId;
	private Long locationValue;
	private String comment ;
	
	private RegionScopes regionSCopes;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "survey_feedback_comments_id", unique = true, nullable = false)
	public Long getSurveyFeedbackCommentsId() {
		return surveyFeedbackCommentsId;
	}
	public void setSurveyFeedbackCommentsId(Long surveyFeedbackCommentsId) {
		this.surveyFeedbackCommentsId = surveyFeedbackCommentsId;
	}
	
	@Column(name="location_scope_id")
	public Long getLocationScopeId() {
		return locationScopeId;
	}
	public void setLocationScopeId(Long locationScopeId) {
		this.locationScopeId = locationScopeId;
	}
	
	@Column(name="location_value")
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	
	@Column(name="comment")
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "location_scope_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public RegionScopes getRegionSCopes() {
		return regionSCopes;
	}
	public void setRegionSCopes(RegionScopes regionSCopes) {
		this.regionSCopes = regionSCopes;
	}
	
	
	
}
