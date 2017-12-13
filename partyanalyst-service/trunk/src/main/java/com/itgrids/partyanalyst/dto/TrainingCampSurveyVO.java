package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class TrainingCampSurveyVO implements java.io.Serializable{

	private Long poor;
	private Long average;
	private Long good;
	private Long veryGood;
	private Long excellent;
	private Long userId;
	private Long id;
	private String name;
	private String startDateStr;
	private String endDateStr;
	private List<Long> surveyIdsList = new ArrayList<Long>(0);
	List<TrainingCampSurveyVO> programsList = new ArrayList<TrainingCampSurveyVO>();
	List<TrainingCampSurveyVO> optionList = new ArrayList<TrainingCampSurveyVO>();
	private Long surveyQuestionId;
	private String question;
	private Long campId;
	private String campName;
	private Long trainingCampId;
	private String options;
	private Long optionId;
	private Long surveyAnswerInfoId;
	private Long tdpCadreId;
	private Long totalMemberAnswered;
	private Long totalCorrectAnswer;
	private Double correctAnswerPercent;
	List<TrainingCampSurveyVO> centerList = new ArrayList<TrainingCampSurveyVO>();
	
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
	public String getStartDateStr() {
		return startDateStr;
	}
	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}
	public String getEndDateStr() {
		return endDateStr;
	}
	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}
	public List<Long> getSurveyIdsList() {
		return surveyIdsList;
	}
	public void setSurveyIdsList(List<Long> surveyIdsList) {
		this.surveyIdsList = surveyIdsList;
	}
	public List<TrainingCampSurveyVO> getProgramsList() {
		return programsList;
	}
	public void setProgramsList(List<TrainingCampSurveyVO> programsList) {
		this.programsList = programsList;
	}
	public Long getSurveyQuestionId() {
		return surveyQuestionId;
	}
	public void setSurveyQuestionId(Long surveyQuestionId) {
		this.surveyQuestionId = surveyQuestionId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Long getTrainingCampId() {
		return trainingCampId;
	}
	public void setTrainingCampId(Long trainingCampId) {
		this.trainingCampId = trainingCampId;
	}
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	public Long getOptionId() {
		return optionId;
	}
	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}
	public Long getSurveyAnswerInfoId() {
		return surveyAnswerInfoId;
	}
	public void setSurveyAnswerInfoId(Long surveyAnswerInfoId) {
		this.surveyAnswerInfoId = surveyAnswerInfoId;
	}
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	public List<TrainingCampSurveyVO> getOptionList() {
		return optionList;
	}
	public void setOptionList(List<TrainingCampSurveyVO> optionList) {
		this.optionList = optionList;
	}
	public Long getTotalMemberAnswered() {
		return totalMemberAnswered;
	}
	public void setTotalMemberAnswered(Long totalMemberAnswered) {
		this.totalMemberAnswered = totalMemberAnswered;
	}
	public Long getTotalCorrectAnswer() {
		return totalCorrectAnswer;
	}
	public void setTotalCorrectAnswer(Long totalCorrectAnswer) {
		this.totalCorrectAnswer = totalCorrectAnswer;
	}
	public Double getCorrectAnswerPercent() {
		return correctAnswerPercent;
	}
	public void setCorrectAnswerPercent(Double correctAnswerPercent) {
		this.correctAnswerPercent = correctAnswerPercent;
	}
	public List<TrainingCampSurveyVO> getCenterList() {
		return centerList;
	}
	public void setCenterList(List<TrainingCampSurveyVO> centerList) {
		this.centerList = centerList;
	}
	public Long getCampId() {
		return campId;
	}
	public void setCampId(Long campId) {
		this.campId = campId;
	}
	public String getCampName() {
		return campName;
	}
	public void setCampName(String campName) {
		this.campName = campName;
	}
	public Long getPoor() {
		return poor;
	}
	public void setPoor(Long poor) {
		this.poor = poor;
	}
	public Long getAverage() {
		return average;
	}
	public void setAverage(Long average) {
		this.average = average;
	}
	public Long getGood() {
		return good;
	}
	public void setGood(Long good) {
		this.good = good;
	}
	public Long getVeryGood() {
		return veryGood;
	}
	public void setVeryGood(Long veryGood) {
		this.veryGood = veryGood;
	}
	public Long getExcellent() {
		return excellent;
	}
	public void setExcellent(Long excellent) {
		this.excellent = excellent;
	}
	
	
}
