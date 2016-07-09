package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VerifierVO implements Serializable{
 
	
	private Long id;
	private String name;
	private String constituencyName;
	private String mandalName;
	private String mobileNO;
	private Long surveyAnswerInfoId;
	private String question;
	private String option;
	private List<VerifierVO> verifierVOList=new ArrayList<VerifierVO>(0);
	private List<VerifierVO> questionsList;
	private String Date;
	private String voterId;
	private String serialNo;
	private String isVerified;
	private Long boothId;
	private String colorCode;
	private Long count = 0l;
	private Long totalCount;
	private List<OptionVO> optionsList;
	private Long optionId;
	private Map<String,VerifierVO> verifierVOMap;
	private String isDeleted;
	private String percentage;
	private String round;
	private String answerType;
	private String isSampleVerified;
	
	
	
	public String getIsSampleVerified() {
		return isSampleVerified;
	}

	public void setIsSampleVerified(String isSampleVerified) {
		this.isSampleVerified = isSampleVerified;
	}

	public String getRound() {
		return round;
	}

	public void setRound(String round) {
		this.round = round;
	}

	public String getAnswerType() {
		return answerType;
	}

	public void setAnswerType(String answerType) {
		this.answerType = answerType;
	}

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	public Long getOptionId() {
		return optionId;
	}

	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}

	public List<OptionVO> getOptionsList() {
		return optionsList;
	}

	public void setOptionsList(List<OptionVO> optionsList) {
		this.optionsList = optionsList;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public VerifierVO()
	{
		
		
	}
	
	public VerifierVO(Long id,String name)
	{
		this.id = id;
		this.name = name;
		
	}
		
	public String getColorCode() {
		return colorCode;
	}
	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	public String getIsVerified() {
		return isVerified;
	}
	public void setIsVerified(String isVerified) {
		this.isVerified = isVerified;
	}
	public String getVoterId() {
		return voterId;
	}
	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
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
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	public String getMobileNO() {
		return mobileNO;
	}
	public void setMobileNO(String mobileNO) {
		this.mobileNO = mobileNO;
	}
	public Long getSurveyAnswerInfoId() {
		return surveyAnswerInfoId;
	}
	public void setSurveyAnswerInfoId(Long surveyAnswerInfoId) {
		this.surveyAnswerInfoId = surveyAnswerInfoId;
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
	public List<VerifierVO> getVerifierVOList() {
		return verifierVOList;
	}
	public void setVerifierVOList(List<VerifierVO> verifierVOList) {
		this.verifierVOList = verifierVOList;
	}
	public List<VerifierVO> getQuestionsList() {
		return questionsList;
	}
	public void setQuestionsList(List<VerifierVO> questionsList) {
		this.questionsList = questionsList;
	}

	public Map<String, VerifierVO> getVerifierVOMap() {
		return verifierVOMap;
	}

	public void setVerifierVOMap(Map<String, VerifierVO> verifierVOMap) {
		this.verifierVOMap = verifierVOMap;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
}
