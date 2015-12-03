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
@Table(name="activity_question")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActivityQuestion extends BaseModel implements Serializable{
	
	private Long activityQuestionId;
	private String question;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "activity_question_id", unique = true, nullable = false)
	public Long getActivityQuestionId() {
		return activityQuestionId;
	}
	public void setActivityQuestionId(Long activityQuestionId) {
		this.activityQuestionId = activityQuestionId;
	}
	
	@Column(name = "question")
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
}
