package com.itgrids.partyanalyst.dto;

import java.util.List;

public class HHSurveyVO {
	private Long questionId;
	private String question;
	private String questionCode;
	private List<HHSurveyVO> options;
	private Long optionId;
	private String option;
	private String optionType;
	private Long optionTypeId;
	private String mainQues;
	private String subQues;
	private Long mainQuesId;
	private Long subQuesId;
	private List<HHSurveyVO> questionsList;
	private List<HHSurveyVO> subQuestList;
	private List<HHSurveyVO> directSubQuestList;
	private Long boothId;
	private HHSurveyVO optsSelected;
	private boolean isOptSelected;
	private String optsRemarked;
	
	
	public String getOptsRemarked() {
		return optsRemarked;
	}
	public void setOptsRemarked(String optsRemarked) {
		this.optsRemarked = optsRemarked;
	}
	public boolean isOptSelected() {
		return isOptSelected;
	}
	public void setOptSelected(boolean isOptSelected) {
		this.isOptSelected = isOptSelected;
	}
	public HHSurveyVO getOptsSelected() {
		return optsSelected;
	}
	public void setOptsSelected(HHSurveyVO optsSelected) {
		this.optsSelected = optsSelected;
	}
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	public List<HHSurveyVO> getDirectSubQuestList() {
		return directSubQuestList;
	}
	public void setDirectSubQuestList(List<HHSurveyVO> directSubQuestList) {
		this.directSubQuestList = directSubQuestList;
	}
	public List<HHSurveyVO> getSubQuestList() {
		return subQuestList;
	}
	public void setSubQuestList(List<HHSurveyVO> subQuestList) {
		this.subQuestList = subQuestList;
	}
	public List<HHSurveyVO> getQuestionsList() {
		return questionsList;
	}
	public void setQuestionsList(List<HHSurveyVO> questionsList) {
		this.questionsList = questionsList;
	}
	public String getMainQues() {
		return mainQues;
	}
	public void setMainQues(String mainQues) {
		this.mainQues = mainQues;
	}
	public String getSubQues() {
		return subQues;
	}
	public void setSubQues(String subQues) {
		this.subQues = subQues;
	}
	public Long getMainQuesId() {
		return mainQuesId;
	}
	public void setMainQuesId(Long mainQuesId) {
		this.mainQuesId = mainQuesId;
	}
	public Long getSubQuesId() {
		return subQuesId;
	}
	public void setSubQuesId(Long subQuesId) {
		this.subQuesId = subQuesId;
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
	public String getQuestionCode() {
		return questionCode;
	}
	public void setQuestionCode(String questionCode) {
		this.questionCode = questionCode;
	}
	public List<HHSurveyVO> getOptions() {
		return options;
	}
	public void setOptions(List<HHSurveyVO> options) {
		this.options = options;
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
	public String getOptionType() {
		return optionType;
	}
	public void setOptionType(String optionType) {
		this.optionType = optionType;
	}
	public Long getOptionTypeId() {
		return optionTypeId;
	}
	public void setOptionTypeId(Long optionTypeId) {
		this.optionTypeId = optionTypeId;
	}
	
	
}
