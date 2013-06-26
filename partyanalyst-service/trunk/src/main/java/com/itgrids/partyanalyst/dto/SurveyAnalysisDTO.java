package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class SurveyAnalysisDTO implements Serializable{

	private List<SurveyAgeWiseDetailsVO> agewiseSurveyAnalysisVO ;
	private List<SurveyAgeWiseDetailsVO> genderWiseSurveyAnalysisVO;
	private List<SurveyAgeWiseDetailsVO> optionWiseSurveyAnalysisVO;
	private List<SurveyAnalysisVO>       casteWiseSurveyAnalysisVO;
	private SurveyAnalysisDTO            ageWiseSurveyVO;
	private SurveyAnalysisDTO            genderWiseSurveyVO;
	private SurveyAnalysisDTO            optionWiseSurveyVO;
	private SurveyAnalysisDTO            casteWiseSurveyVO;
	private Long questionId;
	private String question;
	public List<SurveyAgeWiseDetailsVO> getAgewiseSurveyAnalysisVO() {
		return agewiseSurveyAnalysisVO;
	}
	public void setAgewiseSurveyAnalysisVO(
			List<SurveyAgeWiseDetailsVO> agewiseSurveyAnalysisVO) {
		this.agewiseSurveyAnalysisVO = agewiseSurveyAnalysisVO;
	}
	public List<SurveyAgeWiseDetailsVO> getGenderWiseSurveyAnalysisVO() {
		return genderWiseSurveyAnalysisVO;
	}
	public void setGenderWiseSurveyAnalysisVO(
			List<SurveyAgeWiseDetailsVO> genderWiseSurveyAnalysisVO) {
		this.genderWiseSurveyAnalysisVO = genderWiseSurveyAnalysisVO;
	}
	public List<SurveyAgeWiseDetailsVO> getOptionWiseSurveyAnalysisVO() {
		return optionWiseSurveyAnalysisVO;
	}
	public void setOptionWiseSurveyAnalysisVO(
			List<SurveyAgeWiseDetailsVO> optionWiseSurveyAnalysisVO) {
		this.optionWiseSurveyAnalysisVO = optionWiseSurveyAnalysisVO;
	}
	public List<SurveyAnalysisVO> getCasteWiseSurveyAnalysisVO() {
		return casteWiseSurveyAnalysisVO;
	}
	public void setCasteWiseSurveyAnalysisVO(
			List<SurveyAnalysisVO> casteWiseSurveyAnalysisVO) {
		this.casteWiseSurveyAnalysisVO = casteWiseSurveyAnalysisVO;
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
	public SurveyAnalysisDTO getAgeWiseSurveyVO() {
		return ageWiseSurveyVO;
	}
	public void setAgeWiseSurveyVO(SurveyAnalysisDTO ageWiseSurveyVO) {
		this.ageWiseSurveyVO = ageWiseSurveyVO;
	}
	public SurveyAnalysisDTO getGenderWiseSurveyVO() {
		return genderWiseSurveyVO;
	}
	public void setGenderWiseSurveyVO(SurveyAnalysisDTO genderWiseSurveyVO) {
		this.genderWiseSurveyVO = genderWiseSurveyVO;
	}
	public SurveyAnalysisDTO getOptionWiseSurveyVO() {
		return optionWiseSurveyVO;
	}
	public void setOptionWiseSurveyVO(SurveyAnalysisDTO optionWiseSurveyVO) {
		this.optionWiseSurveyVO = optionWiseSurveyVO;
	}
	public SurveyAnalysisDTO getCasteWiseSurveyVO() {
		return casteWiseSurveyVO;
	}
	public void setCasteWiseSurveyVO(SurveyAnalysisDTO casteWiseSurveyVO) {
		this.casteWiseSurveyVO = casteWiseSurveyVO;
	}
	
	
	
	
	
	
}
