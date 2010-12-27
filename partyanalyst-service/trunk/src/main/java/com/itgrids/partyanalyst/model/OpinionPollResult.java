package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="opinion_poll_result")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OpinionPollResult extends BaseModel implements java.io.Serializable  {

	private static final long serialVersionUID = 1L;
	
	private Long opinionPollResultID;
	private OpinionPollQuestions opinionPollQuestions;
	private OpinionPollQuestionOptions opinionPollQuestionOptions;
	private Long count;
	
	public OpinionPollResult() {
	}
	
	
	public OpinionPollResult(OpinionPollQuestions opinionPollQuestions,
			OpinionPollQuestionOptions opinionPollQuestionOptions,Long count) {
		this.opinionPollQuestions = opinionPollQuestions;
		this.opinionPollQuestionOptions = opinionPollQuestionOptions;
		this.count = count;
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="opinion_poll_result_id", unique=true, nullable=false)
	public Long getOpinionPollResultID() {
		return opinionPollResultID;
	}

	@Column(name = "count")
	public Long getCount() {
		return count;
	}


	public void setCount(Long count) {
		this.count = count;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "opinion_poll_questions_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public OpinionPollQuestions getOpinionPollQuestions() {
		return opinionPollQuestions;
	}


	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "opinion_poll_question_options_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public OpinionPollQuestionOptions getOpinionPollQuestionOptions() {
		return opinionPollQuestionOptions;
	}


	public void setOpinionPollResultID(Long opinionPollResultID) {
		this.opinionPollResultID = opinionPollResultID;
	}


	public void setOpinionPollQuestions(OpinionPollQuestions opinionPollQuestions) {
		this.opinionPollQuestions = opinionPollQuestions;
	}


	public void setOpinionPollQuestionOptions(
			OpinionPollQuestionOptions opinionPollQuestionOptions) {
		this.opinionPollQuestionOptions = opinionPollQuestionOptions;
	}
	

	

}
