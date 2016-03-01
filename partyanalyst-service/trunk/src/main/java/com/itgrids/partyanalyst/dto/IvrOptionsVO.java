package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class IvrOptionsVO implements Serializable{

	private Long id;
	private String name;
	private Long count = 0l;
	private String perc;
	private Long total = 0l;
	
	private Long answeredCount=0l;
	private Long unAnsweredCount=0l;
	private Long othersCount=0l;
	
	private Long surveyId;
	private String surveyName;
	private Long eventId;
	private String eventName;
	private String dateStr;
	private Long roundId;
	private String round;
	private Long questionId;
	private String question;
	private Long optionId;
	private String option;
	
	private Long levelId;
	private String levelValue;
	private String startDate;
	private String endDate;
	
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
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String getPerc() {
		return perc;
	}
	public void setPerc(String perc) {
		this.perc = perc;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Long getAnsweredCount() {
		return answeredCount;
	}
	public void setAnsweredCount(Long answeredCount) {
		this.answeredCount = answeredCount;
	}
	public Long getUnAnsweredCount() {
		return unAnsweredCount;
	}
	public void setUnAnsweredCount(Long unAnsweredCount) {
		this.unAnsweredCount = unAnsweredCount;
	}
	public Long getOthersCount() {
		return othersCount;
	}
	public void setOthersCount(Long othersCount) {
		this.othersCount = othersCount;
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
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	public Long getRoundId() {
		return roundId;
	}
	public void setRoundId(Long roundId) {
		this.roundId = roundId;
	}
	public String getRound() {
		return round;
	}
	public void setRound(String round) {
		this.round = round;
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
	public Long getLevelId() {
		return levelId;
	}
	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}
	public String getLevelValue() {
		return levelValue;
	}
	public void setLevelValue(String levelValue) {
		this.levelValue = levelValue;
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
	
}
