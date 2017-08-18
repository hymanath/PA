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

@Entity
@Table(name = "kaizala_options")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class KaizalaOptions extends BaseModel implements Serializable {

	private Long kaizalaOptionsId;
	private Long kaizalaQuestionsId;
	private String title;
	
	private KaizalaQuestions kaizalaQuestions;
	
	@Id
	@Column(name="kaizala_options_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getKaizalaOptionsId() {
		return kaizalaOptionsId;
	}
	public void setKaizalaOptionsId(Long kaizalaOptionsId) {
		this.kaizalaOptionsId = kaizalaOptionsId;
	}
	
	@Column(name="kaizala_questions_id")
	public Long getKaizalaQuestionsId() {
		return kaizalaQuestionsId;
	}
	public void setKaizalaQuestionsId(Long kaizalaQuestionsId) {
		this.kaizalaQuestionsId = kaizalaQuestionsId;
	}
	
	@Column(name="title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
}
