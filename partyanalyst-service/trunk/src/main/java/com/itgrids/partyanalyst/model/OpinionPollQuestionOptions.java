package com.itgrids.partyanalyst.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;


@Entity
@Table(name="opinion_poll_question_options")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OpinionPollQuestionOptions extends BaseModel implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long opinionPollQuestionOptionsId;
	private QuestionsRepository questionsRepository;
	private String questionOption;
	
	private Set<OpinionPollResult> opinionPollResult = new HashSet<OpinionPollResult>(0);
	
	public OpinionPollQuestionOptions() {
	}

	public OpinionPollQuestionOptions(QuestionsRepository questionsRepository,
			String questionOption,
			Set<OpinionPollResult> opinionPollResult) {
		this.questionsRepository = questionsRepository;
		this.questionOption = questionOption;
		this.opinionPollResult = opinionPollResult;
	}

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="opinion_poll_question_options_id", unique=true, nullable=false)
	public Long getOpinionPollQuestionOptionsId() {
		return opinionPollQuestionOptionsId;
	}

	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="questions_repository_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public QuestionsRepository getQuestionsRepository() {
		return questionsRepository;
	}

	@Column(name="question_option", length=300)
	public String getQuestionOption() {
		return questionOption;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "opinionPollQuestionOptions")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<OpinionPollResult> getOpinionPollResult() {
		return opinionPollResult;
	}

	public void setOpinionPollQuestionOptionsId(Long opinionPollQuestionOptionsId) {
		this.opinionPollQuestionOptionsId = opinionPollQuestionOptionsId;
	}

	public void setQuestionsRepository(QuestionsRepository questionsRepository) {
		this.questionsRepository = questionsRepository;
	}

	public void setQuestionOption(String questionOption) {
		this.questionOption = questionOption;
	}

	public void setOpinionPollResult(Set<OpinionPollResult> opinionPollResult) {
		this.opinionPollResult = opinionPollResult;
	}

	
}
