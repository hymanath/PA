package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class SurveyAgeWiseDetailsVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2894173201162752708L;
	
	private Long    surveyAgeWiseDetailsid;
	private Long    ageBt18To25Total  = 0l;
	private Long    ageBt26To35Total  = 0l;
	private Long    ageBt36To45Total  = 0l;
	private Long    ageBt46To60Total  = 0l;
	private Long    ageAbove60        = 0l;
	private Long    total             = 0l;
	private Long    questionId        = 0l;
	private Long    optionId          = 0l;
	
	private String   ageBt18To25Perc  = "0.00";
	private String   ageBt26To35Perc  = "0.00";
	private String   ageBt36To45Perc  = "0.00";
	private String   ageBt46To60Perc  = "0.00";
	private String   ageAbove60Perc   = "0.00";
	private String   totalPerc        = "0.00";
	private String   question;
	private String   option;
	
	private List<QuestionAnswerVO> questionAnswerVO;
	private List<SurveyAgeWiseDetailsVO>  surveyAgeWiseDetailsVO;
	
	public Long getSurveyAgeWiseDetailsid() {
		return surveyAgeWiseDetailsid;
	}
	public void setSurveyAgeWiseDetailsid(Long surveyAgeWiseDetailsid) {
		this.surveyAgeWiseDetailsid = surveyAgeWiseDetailsid;
	}
	public Long getAgeBt18To25Total() {
		return ageBt18To25Total;
	}
	public void setAgeBt18To25Total(Long ageBt18To25Total) {
		this.ageBt18To25Total = ageBt18To25Total;
	}
	public Long getAgeBt26To35Total() {
		return ageBt26To35Total;
	}
	public void setAgeBt26To35Total(Long ageBt26To35Total) {
		this.ageBt26To35Total = ageBt26To35Total;
	}
	public Long getAgeBt36To45Total() {
		return ageBt36To45Total;
	}
	public void setAgeBt36To45Total(Long ageBt36To45Total) {
		this.ageBt36To45Total = ageBt36To45Total;
	}
	public Long getAgeBt46To60Total() {
		return ageBt46To60Total;
	}
	public void setAgeBt46To60Total(Long ageBt46To60Total) {
		this.ageBt46To60Total = ageBt46To60Total;
	}
	public Long getAgeAbove60() {
		return ageAbove60;
	}
	public void setAgeAbove60(Long ageAbove60) {
		this.ageAbove60 = ageAbove60;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public String getAgeBt18To25Perc() {
		return ageBt18To25Perc;
	}
	public void setAgeBt18To25Perc(String ageBt18To25Perc) {
		this.ageBt18To25Perc = ageBt18To25Perc;
	}
	public String getAgeBt26To35Perc() {
		return ageBt26To35Perc;
	}
	public void setAgeBt26To35Perc(String ageBt26To35Perc) {
		this.ageBt26To35Perc = ageBt26To35Perc;
	}
	public String getAgeBt36To45Perc() {
		return ageBt36To45Perc;
	}
	public void setAgeBt36To45Perc(String ageBt36To45Perc) {
		this.ageBt36To45Perc = ageBt36To45Perc;
	}
	public String getAgeBt46To60Perc() {
		return ageBt46To60Perc;
	}
	public void setAgeBt46To60Perc(String ageBt46To60Perc) {
		this.ageBt46To60Perc = ageBt46To60Perc;
	}
	public String getAgeAbove60Perc() {
		return ageAbove60Perc;
	}
	public void setAgeAbove60Perc(String ageAbove60Perc) {
		this.ageAbove60Perc = ageAbove60Perc;
	}
	public String getTotalPerc() {
		return totalPerc;
	}
	public void setTotalPerc(String totalPerc) {
		this.totalPerc = totalPerc;
	}
	public List<QuestionAnswerVO> getQuestionAnswerVO() {
		return questionAnswerVO;
	}
	public void setQuestionAnswerVO(List<QuestionAnswerVO> questionAnswerVO) {
		this.questionAnswerVO = questionAnswerVO;
	}
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	public Long getOptionId() {
		return optionId;
	}
	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public List<SurveyAgeWiseDetailsVO> getSurveyAgeWiseDetailsVO() {
		return surveyAgeWiseDetailsVO;
	}
	public void setSurveyAgeWiseDetailsVO(
			List<SurveyAgeWiseDetailsVO> surveyAgeWiseDetailsVO) {
		this.surveyAgeWiseDetailsVO = surveyAgeWiseDetailsVO;
	}
	
	
	
}
