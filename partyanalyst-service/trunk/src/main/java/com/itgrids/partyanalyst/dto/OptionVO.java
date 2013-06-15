package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
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
	private List<OptionVO> subOptionList;


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
	
		
	
	

}
