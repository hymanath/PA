package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuestionAnswerVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3017308197970407148L;

	public Long questionId;
	public Long questionType;
	public String optionId;
	public Long subQuestionType;
	public boolean selected;
	public boolean hasSubQuestion;
	public List<Long> optionIds;
	public String optionVal;
	public String remarks;
	public List<QuestionAnswerVO> options;
	public QuestionAnswerVO option;
	public String question;
	
	private Long surveyId;
	private String surveyName;
	private Long optionValue;
	private  String percentage;
	private Long userId;
	private Long count = 0l;
	private List<QuestionAnswerVO> questions;
	private Long id;
	private String name;
	private String candidateName;
	private Long candidateId;
	private String startDate;
	private String endDate;
	private Long locationScopeId;
	private Long surveyTypeId;
	private String marks;
	private List<QuestionAnswerVO> subList = new ArrayList<QuestionAnswerVO>(0);
	
	public List<QuestionAnswerVO> getSubList() {
		return subList;
	}

	public void setSubList(List<QuestionAnswerVO> subList) {
		this.subList = subList;
	}

	public String getMarks() {
		return marks;
	}

	public void setMarks(String marks) {
		this.marks = marks;
	}

	public Long getSurveyTypeId() {
		return surveyTypeId;
	}

	public void setSurveyTypeId(Long surveyTypeId) {
		this.surveyTypeId = surveyTypeId;
	}

	public Long getLocationScopeId() {
		return locationScopeId;
	}

	public void setLocationScopeId(Long locationScopeId) {
		this.locationScopeId = locationScopeId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
	
	
	
	
	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
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

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public Long getQuestionType() {
		return questionType;
	}

	public void setQuestionType(Long questionType) {
		this.questionType = questionType;
	}

	public Long getSubQuestionType() {
		return subQuestionType;
	}

	

	public String getOptionId() {
		return optionId;
	}

	public void setOptionId(String optionId) {
		this.optionId = optionId;
	}

	public void setSubQuestionType(Long subQuestionType) {
		this.subQuestionType = subQuestionType;
	}

	public boolean isHasSubQuestion() {
		return hasSubQuestion;
	}

	public void setHasSubQuestion(boolean hasSubQuestion) {
		this.hasSubQuestion = hasSubQuestion;
	}

	public List<QuestionAnswerVO> getOptions() {
		return options;
	}

	public void setOptions(List<QuestionAnswerVO> options) {
		this.options = options;
	}

	public List<Long> getOptionIds() {
		return optionIds;
	}

	public void setOptionIds(List<Long> optionIds) {
		this.optionIds = optionIds;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public QuestionAnswerVO getOption() {
		return option;
	}

	public void setOption(QuestionAnswerVO option) {
		this.option = option;
	}

	public String getOptionVal() {
		return optionVal;
	}

	public void setOptionVal(String optionVal) {
		this.optionVal = optionVal;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Long getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Long surveyId) {
		this.surveyId = surveyId;
	}

	public String getSurveyName() {
		return surveyName;
	}

	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}

	public Long getOptionValue() {
		return optionValue;
	}

	public void setOptionValue(Long optionValue) {
		this.optionValue = optionValue;
	}

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public List<QuestionAnswerVO> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionAnswerVO> questions) {
		this.questions = questions;
	}
	
   	
}
