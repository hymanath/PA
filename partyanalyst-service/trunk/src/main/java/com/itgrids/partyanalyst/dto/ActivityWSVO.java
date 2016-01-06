/**
 * @author Sasi
 * Jan 6, 2016
 * ActivityWSVO.java
 * PA - SERV - com.itgrids.partyanalyst.dto
 */
package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sasi
 * @date Jan 6, 2016
 */
public class ActivityWSVO {
	private Long				userId;
	private Long				id;
	private String				name;
	private Long				locationId;
	private String				location;
	private List<ActivityWSVO> 	questionList = new ArrayList<ActivityWSVO>(0);
	private List<ActivityWSVO>	optionsList  = new ArrayList<ActivityWSVO>(0);
	
	private Long				activityScopeId;
	private String				activityName;
	private Long 				questionId;
	private String 				question;
	private Long 				optionId;
	private String 				option;
	private Long 				optionTypeId;
	private String 				optionType;
	
	private Long				respondentTypeId;
	private String				respondentType;
	private List<ActivityWSVO>	acitivityQuesList =new ArrayList<ActivityWSVO>(0);
	
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public List<ActivityWSVO> getQuestionList() {
		return questionList;
	}
	public void setQuestionList(List<ActivityWSVO> questionList) {
		this.questionList = questionList;
	}
	public List<ActivityWSVO> getOptionsList() {
		return optionsList;
	}
	public void setOptionsList(List<ActivityWSVO> optionsList) {
		this.optionsList = optionsList;
	}
	public Long getActivityScopeId() {
		return activityScopeId;
	}
	public void setActivityScopeId(Long activityScopeId) {
		this.activityScopeId = activityScopeId;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Long getOptionId() {
		return optionId;
	}
	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public Long getOptionTypeId() {
		return optionTypeId;
	}
	public void setOptionTypeId(Long optionTypeId) {
		this.optionTypeId = optionTypeId;
	}
	public String getOptionType() {
		return optionType;
	}
	public void setOptionType(String optionType) {
		this.optionType = optionType;
	}
	public Long getRespondentTypeId() {
		return respondentTypeId;
	}
	public void setRespondentTypeId(Long respondentTypeId) {
		this.respondentTypeId = respondentTypeId;
	}
	public String getRespondentType() {
		return respondentType;
	}
	public void setRespondentType(String respondentType) {
		this.respondentType = respondentType;
	}
	public List<ActivityWSVO> getAcitivityQuesList() {
		return acitivityQuesList;
	}
	public void setAcitivityQuesList(List<ActivityWSVO> acitivityQuesList) {
		this.acitivityQuesList = acitivityQuesList;
	}
	
	
	
}
