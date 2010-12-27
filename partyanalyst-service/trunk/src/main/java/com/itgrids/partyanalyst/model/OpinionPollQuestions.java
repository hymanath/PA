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
@Table (name="opinion_poll_questions")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OpinionPollQuestions extends BaseModel implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long opinionPollQuestionsId;
	private OpinionPoll opinionPoll;
	private QuestionsRepository questionsRepository;
	
	private Set<OpinionPollResult> opinionPollResult = new HashSet<OpinionPollResult>(0);
	
	/** Default Constructor */
	public OpinionPollQuestions() {
			}
		
	public OpinionPollQuestions(OpinionPoll opinionPoll,
			QuestionsRepository questionsRepository,
			Set<OpinionPollResult> opinionPollResult) {
		this.opinionPoll = opinionPoll;
		this.questionsRepository = questionsRepository;
		this.opinionPollResult = opinionPollResult;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="opinion_poll_questions_id", unique=true, nullable=false)
	public Long getOpinionPollQuestionsId() {
		return opinionPollQuestionsId;
	}
	
	public void setOpinionPollQuestionsId(Long opinionPollQuestionsId) {
		this.opinionPollQuestionsId = opinionPollQuestionsId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="opinion_poll_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public OpinionPoll getOpinionPoll() {
		return opinionPoll;
	}

	public void setOpinionPoll(OpinionPoll opinionPoll) {
		this.opinionPoll = opinionPoll;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="questions_repository_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public QuestionsRepository getQuestionsRepository() {
		return questionsRepository;
	}

	public void setQuestionsRepository(QuestionsRepository questionsRepository) {
		this.questionsRepository = questionsRepository;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "opinionPollQuestions")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<OpinionPollResult> getOpinionPollResult() {
		return opinionPollResult;
	}

	public void setOpinionPollResult(Set<OpinionPollResult> opinionPollResult) {
		this.opinionPollResult = opinionPollResult;
	}
	
}


