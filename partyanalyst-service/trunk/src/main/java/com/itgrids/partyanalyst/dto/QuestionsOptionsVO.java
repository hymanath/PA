package com.itgrids.partyanalyst.dto;
import java.util.List;

public class QuestionsOptionsVO {
	
	private Long questionId;
	private String question;
	private List<OptionVO> options;

	public QuestionsOptionsVO() {
			}

	
	public QuestionsOptionsVO(Long questionId, String question,
			List<OptionVO> options) {
		super();
		this.questionId = questionId;
		this.question = question;
		this.options = options;
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

	public List<OptionVO> getOptions() {
		return options;
	}

	public void setOptions(List<OptionVO> options) {
		this.options = options;
	}
}
