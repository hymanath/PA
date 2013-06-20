package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
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
	
	
}
