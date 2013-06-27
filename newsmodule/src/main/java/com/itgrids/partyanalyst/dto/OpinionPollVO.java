package com.itgrids.partyanalyst.dto;

import java.util.Date;
import java.util.List;

public class OpinionPollVO {
	private Long opinionPollId;
	private Long registration_id;
	private String title;
	private String description;
	private Date opinionPollStartDate;
	private Date opinionPollEndDate;
	//private List<QuestionsOptionsVO> quesitons;
	private QuestionsOptionsVO questionsOptionsVO;
	private ResultStatus resultStatus;
	private boolean avaliability;
	
	

	public boolean isAvaliability() {
		return avaliability;
	}

	public void setAvaliability(boolean avaliability) {
		this.avaliability = avaliability;
	}

	public OpinionPollVO() {
	}
	
	public OpinionPollVO(Long opinionPollId, Long registration_id,
    	  String title, String description,
		  Date opinionPollStartDate,
          Date opinionPollEndDate,
          ResultStatus resultStatus)
		{

		this.opinionPollId = opinionPollId;
		this.registration_id = registration_id;
		this.title = title;
		this.description = description;
		this.opinionPollStartDate = opinionPollStartDate;
		this.opinionPollEndDate = opinionPollEndDate;
		//this.quesitons = quesitons;
		this.resultStatus = resultStatus;
		}
	
	public QuestionsOptionsVO getQuestionsOptionsVO() {
		return questionsOptionsVO;
	}

	public void setQuestionsOptionsVO(QuestionsOptionsVO questionsOptionsVO) {
		this.questionsOptionsVO = questionsOptionsVO;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public Long getOpinionPollId() {
		return opinionPollId;
	}

	public Long getRegistration_id() {
		return registration_id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Date getOpinionPollStartDate() {
		return opinionPollStartDate;
	}

	public Date getOpinionPollEndDate() {
		return opinionPollEndDate;
	}

	/*public List<QuestionsOptionsVO> getQuesitons() {
		return quesitons;
	}
*/
	public void setOpinionPollId(Long opinionPollId) {
		this.opinionPollId = opinionPollId;
	}

	public void setRegistration_id(Long registration_id) {
		this.registration_id = registration_id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setOpinionPollStartDate(Date opinionPollStartDate) {
		this.opinionPollStartDate = opinionPollStartDate;
	}

	public void setOpinionPollEndDate(Date opinionPollEndDate) {
		this.opinionPollEndDate = opinionPollEndDate;
	}

	/*public void setQuesitons(List<QuestionsOptionsVO> quesitons) {
		this.quesitons = quesitons;
	}
	*/
}
