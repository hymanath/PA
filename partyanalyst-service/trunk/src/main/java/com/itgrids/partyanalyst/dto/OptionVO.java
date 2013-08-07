package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OptionVO implements Serializable,Comparable<OptionVO> {
	private Long optionId;
	private String option;
	private Double percentage;
	private Long votesObtained;
	private Boolean hasRemark;
	private Boolean hasSubQuestion;
	private String subquestion;
	private String subquestionType;
	private List<OptionVO> subOptionList = new ArrayList<OptionVO>(0);
	private String question;
	private Long questionId;
	private Long goodBoothCount =0L;
	private Long veryGoodBoothCount = 0L;
	private Long badBoothCount = 0L;
	private Long veryBadBoothCount = 0L;
	private Long averageBoothCount = 0L;
	private List<Long> goodBoothIdsList;
	private List<Long> veryGoodBoothIdsList;
	private List<Long> badBoothIdsList;
	private List<Long> veryBadBoothIdsList;
	private List<Long> averageBoothIdsList;
	private Long locationId;
	private String locationName;
	private Long total;
	private List<String> optionsList = new ArrayList<String>(0);
	public OptionVO() {
	}

	/**
	 * @param optionId
	 * @param option
	 */
	public OptionVO(Long optionId, String option,Double percentage) {
		super();
		this.optionId = optionId;
		this.option = option;
		this.percentage = percentage;
	}

	
	

	public List<String> getOptionsList() {
		return optionsList;
	}

	public void setOptionsList(List<String> optionsList) {
		this.optionsList = optionsList;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
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

	public Boolean getHasRemark() {
		return hasRemark;
	}

	public void setHasRemark(Boolean hasRemark) {
		this.hasRemark = hasRemark;
	}

	public Boolean getHasSubQuestion() {
		return hasSubQuestion;
	}

	public void setHasSubQuestion(Boolean hasSubQuestion) {
		this.hasSubQuestion = hasSubQuestion;
	}

	public String getSubquestion() {
		return subquestion;
	}

	public void setSubquestion(String subquestion) {
		this.subquestion = subquestion;
	}

	public String getSubquestionType() {
		return subquestionType;
	}

	public void setSubquestionType(String subquestionType) {
		this.subquestionType = subquestionType;
	}

	public Long getVotesObtained() {
		return votesObtained;
	}

	public void setVotesObtained(Long votesObtained) {
		this.votesObtained = votesObtained;
	}

	public Double getPercentage() {
		return percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
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

	public List<OptionVO> getSubOptionList() {
		return subOptionList;
	}

	public void setSubOptionList(List<OptionVO> subOptionList) {
		this.subOptionList = subOptionList;
	}

	public int compareTo(OptionVO obj) {
		if(obj instanceof OptionVO)
		{
			OptionVO vo = (OptionVO)obj;
			return option.compareToIgnoreCase(vo.getOption());
		}
		return 0;
	}

	public Long getGoodBoothCount() {
		return goodBoothCount;
	}

	public void setGoodBoothCount(Long goodBoothCount) {
		this.goodBoothCount = goodBoothCount;
	}

	public Long getVeryGoodBoothCount() {
		return veryGoodBoothCount;
	}

	public void setVeryGoodBoothCount(Long veryGoodBoothCount) {
		this.veryGoodBoothCount = veryGoodBoothCount;
	}

	public Long getBadBoothCount() {
		return badBoothCount;
	}

	public void setBadBoothCount(Long badBoothCount) {
		this.badBoothCount = badBoothCount;
	}

	public Long getVeryBadBoothCount() {
		return veryBadBoothCount;
	}

	public void setVeryBadBoothCount(Long veryBadBoothCount) {
		this.veryBadBoothCount = veryBadBoothCount;
	}

	public Long getAverageBoothCount() {
		return averageBoothCount;
	}

	public void setAverageBoothCount(Long averageBoothCount) {
		this.averageBoothCount = averageBoothCount;
	}

	public List<Long> getGoodBoothIdsList() {
		return goodBoothIdsList;
	}

	public void setGoodBoothIdsList(List<Long> goodBoothIdsList) {
		this.goodBoothIdsList = goodBoothIdsList;
	}

	public List<Long> getVeryGoodBoothIdsList() {
		return veryGoodBoothIdsList;
	}

	public void setVeryGoodBoothIdsList(List<Long> veryGoodBoothIdsList) {
		this.veryGoodBoothIdsList = veryGoodBoothIdsList;
	}

	public List<Long> getBadBoothIdsList() {
		return badBoothIdsList;
	}

	public void setBadBoothIdsList(List<Long> badBoothIdsList) {
		this.badBoothIdsList = badBoothIdsList;
	}

	public List<Long> getVeryBadBoothIdsList() {
		return veryBadBoothIdsList;
	}

	public void setVeryBadBoothIdsList(List<Long> veryBadBoothIdsList) {
		this.veryBadBoothIdsList = veryBadBoothIdsList;
	}

	public List<Long> getAverageBoothIdsList() {
		return averageBoothIdsList;
	}

	public void setAverageBoothIdsList(List<Long> averageBoothIdsList) {
		this.averageBoothIdsList = averageBoothIdsList;
	}
	
		
	
	

}
