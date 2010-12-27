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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "questions_repository")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class QuestionsRepository extends BaseModel implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long questionsRepositoryId;
	private String question;
	
	private Set<OpinionPollQuestions> opinionPollQuestions = new HashSet<OpinionPollQuestions>(0); 
	private Set<OpinionPollQuestionOptions> opinionPollQuestionOptions = new HashSet<OpinionPollQuestionOptions>(0); 
	/** Default Constructor */
	
	public QuestionsRepository() {
	}
	
	/** Constructor with all Parameters */
	
	public QuestionsRepository(String question,
			Set<OpinionPollQuestions> opinionPollQuestions,
			Set<OpinionPollQuestionOptions> opinionPollQuestionOptions) {
				this.question = question;
				this.opinionPollQuestions = opinionPollQuestions;
				this.opinionPollQuestionOptions = opinionPollQuestionOptions;
	}

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="questions_repository_id", unique=true, nullable=false)
	public Long getQuestionsRepositoryId() {
		return questionsRepositoryId;
	}

	public void setQuestionsRepositoryId(Long questionsRepositoryId) {
		this.questionsRepositoryId = questionsRepositoryId;
	}

	@Column(name="question", length=300)
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "questionsRepository")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<OpinionPollQuestions> getOpinionPollQuestions() {
		return opinionPollQuestions;
	}

	public void setOpinionPollQuestions(
			Set<OpinionPollQuestions> opinionPollQuestions) {
		this.opinionPollQuestions = opinionPollQuestions;
	}
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "questionsRepository")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<OpinionPollQuestionOptions> getOpinionPollQuestionOptions() {
		return opinionPollQuestionOptions;
	}

	public void setOpinionPollQuestionOptions(
			Set<OpinionPollQuestionOptions> opinionPollQuestionOptions) {
		this.opinionPollQuestionOptions = opinionPollQuestionOptions;
	}
	
	
}
