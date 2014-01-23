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
@Table(name = "debate_questions")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DebateQuestions extends BaseModel implements java.io.Serializable{

	private Integer debateQuestionsId;
	private String question;
	private String isDeleted;
	private Set<DebateQuestionAnswer> debateQuestionAnswer = new HashSet<DebateQuestionAnswer>(0);

	//default constructor.
	
     public DebateQuestions() {}

     
     
     
     @Id
 	@GeneratedValue(strategy = GenerationType.AUTO)
 	@Column(name = "debate_questions_id", unique = true, nullable = false)
	public Integer getDebateQuestionsId() {
		return debateQuestionsId;
	}

	public void setDebateQuestionsId(Integer debateQuestionsId) {
		this.debateQuestionsId = debateQuestionsId;
	}
	@Column(name = "question")
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	@Column(name ="is_deleted",length = 1,nullable=true,updatable=true)
    public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}



	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "debateQuestions")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<DebateQuestionAnswer> getDebateQuestionAnswer() {
		return debateQuestionAnswer;
	}




	public void setDebateQuestionAnswer(
			Set<DebateQuestionAnswer> debateQuestionAnswer) {
		this.debateQuestionAnswer = debateQuestionAnswer;
	}	
	
	
	
	
	
}
