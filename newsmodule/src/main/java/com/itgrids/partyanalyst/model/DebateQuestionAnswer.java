package com.itgrids.partyanalyst.model;

import javax.persistence.CascadeType;
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
@Table(name = "debate_question_answer")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DebateQuestionAnswer extends BaseModel implements java.io.Serializable{

	private Integer debateQuestionAnswerId;
	private String answer;
	private Debate debate;
	private DebateQuestions debateQuestions;
	
	
	//default constructor.
	
    public DebateQuestionAnswer() {}


    @Id
   	@GeneratedValue(strategy = GenerationType.AUTO)
   	@Column(name = "debate_question_answer_id", unique = true, nullable = false)
	public Integer getDebateQuestionAnswerId() {
		return debateQuestionAnswerId;
	}


	public void setDebateQuestionAnswerId(Integer debateQuestionAnswerId) {
		this.debateQuestionAnswerId = debateQuestionAnswerId;
	}

	@Column(name = "answer")
   public String getAnswer() {
		return answer;
	}


	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "debate_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Debate getDebate() {
		return debate;
	}


	public void setDebate(Debate debate) {
		this.debate = debate;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "debate_questions_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public DebateQuestions getDebateQuestions() {
		return debateQuestions;
	}


	public void setDebateQuestions(DebateQuestions debateQuestions) {
		this.debateQuestions = debateQuestions;
	}
	
	
    
    
    
    
	
	
	
	
	
}
