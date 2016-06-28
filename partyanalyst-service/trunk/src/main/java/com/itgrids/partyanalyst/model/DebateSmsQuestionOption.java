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
@Table(name = "debate_sms_question_option")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DebateSmsQuestionOption extends BaseModel implements java.io.Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 6721208508581072864L;
	private Long debateSmsQuestionOptionId;
	private String option;
	private Double percantage;
	private DebateSmsQuestion debateSmsQuestion;
	
//default constructor.
	
    public DebateSmsQuestionOption() {}

    
  //setters and getters  
    @Id
   	@GeneratedValue(strategy = GenerationType.AUTO)
   	@Column(name = "debate_sms_question_option_id", unique = true, nullable = false)
	public Long getDebateSmsQuestionOptionId() {
		return debateSmsQuestionOptionId;
	}

	public void setDebateSmsQuestionOptionId(Long debateSmsQuestionOptionId) {
		this.debateSmsQuestionOptionId = debateSmsQuestionOptionId;
	}

	
	
	@Column(name = "options", length = 50)
	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	
	@Column(name = "percentage")
	public Double getPercantage() {
		return percantage;
	}

	public void setPercantage(Double percantage) {
		this.percantage = percantage;
	}

	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "debate_sms_question_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public DebateSmsQuestion getDebateSmsQuestion() {
		return debateSmsQuestion;
	}

	public void setDebateSmsQuestion(DebateSmsQuestion debateSmsQuestion) {
		this.debateSmsQuestion = debateSmsQuestion;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
