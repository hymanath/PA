package com.itgrids.partyanalyst.dto;
import java.util.List;

public class QuestionsOptionsVO {
	
	private Long questionId;
	private String question;
	private List<OptionVO> options;
	private String answerKey;
	private String answerValue;
	
	private String imagePath;
	private String hasAlreadyPolled;
	private Long totalVotesObtainedForPoll; 
	private String startDate;
	private Long differenceBetweenCurrentDateAndPolledDate;	
	private String title;
	private boolean pollExpire;
	private boolean availability;
	private Boolean hasRemark;
	private Boolean hasSubQuestion;
	private String questionType;
	
	public String getQuestionType() {
		return questionType;
	}


	public void setQuestionType(String questionType) {
		this.questionType = questionType;
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


	public boolean isAvailability() {
		return availability;
	}


	public void setAvailability(boolean availability) {
		this.availability = availability;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	private ResultStatus resultStatus;
	
	
	public QuestionsOptionsVO() {
	}

	
	public QuestionsOptionsVO(Long questionId, String question,
			List<OptionVO> options) {
		super();
		this.questionId = questionId;
		this.question = question;
		this.options = options;
	}

	
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public Long getTotalVotesObtainedForPoll() {
		return totalVotesObtainedForPoll;
	}

	public void setTotalVotesObtainedForPoll(Long totalVotesObtainedForPoll) {
		this.totalVotesObtainedForPoll = totalVotesObtainedForPoll;
	}

	public String getHasAlreadyPolled() {
		return hasAlreadyPolled;
	}

	public void setHasAlreadyPolled(String hasAlreadyPolled) {
		this.hasAlreadyPolled = hasAlreadyPolled;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
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

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	public Long getDifferenceBetweenCurrentDateAndPolledDate() {
		return differenceBetweenCurrentDateAndPolledDate;
	}
	public void setDifferenceBetweenCurrentDateAndPolledDate(
			Long differenceBetweenCurrentDateAndPolledDate) {
		this.differenceBetweenCurrentDateAndPolledDate = differenceBetweenCurrentDateAndPolledDate;
	}


	public String getAnswerKey() {
		return answerKey;
	}


	public void setAnswerKey(String answerKey) {
		this.answerKey = answerKey;
	}


	public String getAnswerValue() {
		return answerValue;
	}


	public void setAnswerValue(String answerValue) {
		this.answerValue = answerValue;
	}
	
	public boolean isPollExpire() {
		return pollExpire;
	}


	public void setPollExpire(boolean pollExpire) {
		this.pollExpire = pollExpire;
	}


}
