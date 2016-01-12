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
	private Long						userId;
	private List<ActivityScopeVO>  		activityScopeList = new ArrayList<ActivityScopeVO>(0);
	private List<ActivityQuestionVO> 	questionList = new ArrayList<ActivityQuestionVO>(0);
	private List<ActivityOptionVO>		optionsList  = new ArrayList<ActivityOptionVO>(0);
	private List<ActivityLocationVO>  	activityLocationsList = new ArrayList<ActivityLocationVO>(0);
	private List<ActivityQuestionnairVO> questnairList  = new ArrayList<ActivityQuestionnairVO>(0);
	private List<ActivityQuestionnairOptionVO>  	questnairOptnsList = new ArrayList<ActivityQuestionnairOptionVO>(0);
	private List<Long>					reqAttrList = new ArrayList<Long>(0);
	private List<ActivityMainVO>		activities = new ArrayList<ActivityMainVO>();
	private List<ActivityReqAttributesVO> attributes = new ArrayList<ActivityReqAttributesVO>();
	
	
	public List<ActivityMainVO> getActivities() {
		return activities;
	}
	public void setActivities(List<ActivityMainVO> activities) {
		this.activities = activities;
	}
	public List<ActivityReqAttributesVO> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<ActivityReqAttributesVO> attributes) {
		this.attributes = attributes;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public List<ActivityScopeVO> getActivityScopeList() {
		return activityScopeList;
	}
	public void setActivityScopeList(List<ActivityScopeVO> activityScopeList) {
		this.activityScopeList = activityScopeList;
	}
	public List<ActivityQuestionVO> getQuestionList() {
		return questionList;
	}
	public void setQuestionList(List<ActivityQuestionVO> questionList) {
		this.questionList = questionList;
	}
	public List<ActivityOptionVO> getOptionsList() {
		return optionsList;
	}
	public void setOptionsList(List<ActivityOptionVO> optionsList) {
		this.optionsList = optionsList;
	}
	
	public List<ActivityLocationVO> getActivityLocationsList() {
		return activityLocationsList;
	}
	public void setActivityLocationsList(
			List<ActivityLocationVO> activityLocationsList) {
		this.activityLocationsList = activityLocationsList;
	}
	public List<Long> getReqAttrList() {
		return reqAttrList;
	}
	public void setReqAttrList(List<Long> reqAttrList) {
		this.reqAttrList = reqAttrList;
	}
	public List<ActivityQuestionnairVO> getQuestnairList() {
		return questnairList;
	}
	public void setQuestnairList(List<ActivityQuestionnairVO> questnairList) {
		this.questnairList = questnairList;
	}
	public List<ActivityQuestionnairOptionVO> getQuestnairOptnsList() {
		return questnairOptnsList;
	}
	public void setQuestnairOptnsList(
			List<ActivityQuestionnairOptionVO> questnairOptnsList) {
		this.questnairOptnsList = questnairOptnsList;
	}
	
}
