package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "ivr_question")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IvrQuestion extends BaseModel implements Serializable{
	
	private Long ivrQuestionId;
	private String question;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ivr_question_id", unique = true, nullable = false)
	public Long getIvrQuestionId() {
		return ivrQuestionId;
	}
	public void setIvrQuestionId(Long ivrQuestionId) {
		this.ivrQuestionId = ivrQuestionId;
	}
	@Column(name = "question")
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	
}
