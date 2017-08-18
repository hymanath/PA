package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

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

import com.itgrids.partyanalyst.dao.hibernate.KaizalaAnswerInfoDAO;

@Entity
@Table(name = "kaizala_answers")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class KaizalaAnswers extends BaseModel implements Serializable {

	private Long kaizalaAnswersId;
	private Long kaizalaQuestionsId;
	private String answer;
	private String eventId;
	private Long kaizalaAnswerInfoId;
	private Date insertedTime;
	private Long kaizalaOptionsId;
	
	private KaizalaQuestions kaizalaQuestions;
	private KaizalaAnswerInfo kaizalaAnswerInfo;
	private KaizalaOptions kaizalaOptions;
	
	
	@Id
	@Column(name="kaizala_answers_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getKaizalaAnswersId() {
		return kaizalaAnswersId;
	}
	public void setKaizalaAnswersId(Long kaizalaAnswersId) {
		this.kaizalaAnswersId = kaizalaAnswersId;
	}
	
	@Column(name="kaizala_questions_id")
	public Long getKaizalaQuestionsId() {
		return kaizalaQuestionsId;
	}
	public void setKaizalaQuestionsId(Long kaizalaQuestionsId) {
		this.kaizalaQuestionsId = kaizalaQuestionsId;
	}
	
	@Column(name="answer")
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	@Column(name="event_id")
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "kaizala_questions_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public KaizalaQuestions getKaizalaQuestions() {
		return kaizalaQuestions;
	}
	public void setKaizalaQuestions(KaizalaQuestions kaizalaQuestions) {
		this.kaizalaQuestions = kaizalaQuestions;
	}
	
	@Column(name="kaizala_answer_info_id")
	public Long getKaizalaAnswerInfoId() {
		return kaizalaAnswerInfoId;
	}
	public void setKaizalaAnswerInfoId(Long kaizalaAnswerInfoId) {
		this.kaizalaAnswerInfoId = kaizalaAnswerInfoId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "kaizala_answer_info_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public KaizalaAnswerInfo getKaizalaAnswerInfo() {
		return kaizalaAnswerInfo;
	}
	public void setKaizalaAnswerInfo(KaizalaAnswerInfo kaizalaAnswerInfo) {
		this.kaizalaAnswerInfo = kaizalaAnswerInfo;
	}
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name="kaizala_options_id")
	public Long getKaizalaOptionsId() {
		return kaizalaOptionsId;
	}
	public void setKaizalaOptionsId(Long kaizalaOptionsId) {
		this.kaizalaOptionsId = kaizalaOptionsId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "kaizala_options_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public KaizalaOptions getKaizalaOptions() {
		return kaizalaOptions;
	}
	public void setKaizalaOptions(KaizalaOptions kaizalaOptions) {
		this.kaizalaOptions = kaizalaOptions;
	}	
	
	
}
