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
@Table(name = "debate_sms_question")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DebateSmsQuestion extends BaseModel implements java.io.Serializable{

	private Integer debateSmsQuestionId;
	private String question;
	private String isDeleted;
	private Debate debate;
	//private Set<DebateSmsQuestionOption> DebateSmsQuestionOption = new HashSet<DebateSmsQuestionOption>(0);

	
//default constructor.
	
    public DebateSmsQuestion() {}

    
    @Id
   	@GeneratedValue(strategy = GenerationType.AUTO)
   	@Column(name = "debate_sms_question_id", unique = true, nullable = false)
   public Integer getDebateSmsQuestionId() {
		return debateSmsQuestionId;
	}


	public void setDebateSmsQuestionId(Integer debateSmsQuestionId) {
		this.debateSmsQuestionId = debateSmsQuestionId;
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

	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "DebateSmsQuestion")
   public Set<DebateSmsQuestionOption> getDebateSmsQuestionOption() {
		return DebateSmsQuestionOption;
	}


	public void setDebateSmsQuestionOption(
			Set<DebateSmsQuestionOption> debateSmsQuestionOption) {
		DebateSmsQuestionOption = debateSmsQuestionOption;
	}*/
    
    
    
    
     
    
    
    
    
    
    
    
    
	
	
}
