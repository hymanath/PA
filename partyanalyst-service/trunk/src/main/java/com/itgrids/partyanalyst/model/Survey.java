package com.itgrids.partyanalyst.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "survey")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Survey extends BaseModel implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long surveyId;
	private String name;
	private RegionScopes locationScopes;
	private Long locationScopeValue;
	private String description;
	private UpdationDetails updationDetails;
	private Date startTime;
	private Date endTime;
	private String isDeleted;
	private UserAddress userAddress;
	
	private Set<SurveyAccessUsers> surveyAccessUsers = new HashSet<SurveyAccessUsers>(0);
	private Set<SurveyQuestion> surveyQuestion = new HashSet<SurveyQuestion>(0);
	public Survey() {
	}

	public Survey(Long surveyId,String name,RegionScopes locationScopes,Long locationScopeValue,String description,UpdationDetails updationDetails,Date startTime,Date endTime,String isDeleted){
		this.surveyId = surveyId;
		this.name = name;
		this.locationScopes = locationScopes;
		this.locationScopeValue = locationScopeValue;
		this.description = description;
		this.updationDetails = updationDetails;
		this.startTime = startTime;
		this.endTime = endTime;
		this.isDeleted = isDeleted;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "survey_id", unique = true, nullable = false)
	public Long getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Long surveyId) {
		this.surveyId = surveyId;
	}

	@Column(name = "name", length = 10)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="location_scope_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public RegionScopes getLocationScopes() {
		return locationScopes;
	}

	public void setLocationScopes(RegionScopes locationScopes) {
		this.locationScopes = locationScopes;
	}

	@Column(name = "location_scope_value", length = 10)
	public Long getLocationScopeValue() {
		return locationScopeValue;
	}

	public void setLocationScopeValue(Long locationScopeValue) {
		this.locationScopeValue = locationScopeValue;
	}

	@Column(name = "description", length = 10)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="updation_details_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UpdationDetails getUpdationDetails() {
		return updationDetails;
	}

	public void setUpdationDetails(UpdationDetails updationDetails) {
		this.updationDetails = updationDetails;
	}

	@Column(name="start_time")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Column(name="end_time")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "is_deleted", length = 10)
	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "survey")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<SurveyAccessUsers> getSurveyAccessUsers() {
		return surveyAccessUsers;
	}

	public void setSurveyAccessUsers(Set<SurveyAccessUsers> surveyAccessUsers) {
		this.surveyAccessUsers = surveyAccessUsers;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "survey")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<SurveyQuestion> getSurveyQuestion() {
		return surveyQuestion;
	}

	public void setSurveyQuestion(Set<SurveyQuestion> surveyQuestion) {
		this.surveyQuestion = surveyQuestion;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_address_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserAddress getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}
	
	
}
