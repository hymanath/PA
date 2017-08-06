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
@Table(name = "kaizala_questions")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class KaizalaQuestions extends BaseModel implements Serializable {

	private Long kaizalaQuestionsId;
	private Long kaizalaActionsId;
	private String question;
	private String type;
	private String isDeleted;
	
	private KaizalaActions kaizalaActions;

	@Id
	@Column(name="kaizala_questions_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getKaizalaQuestionsId() {
		return kaizalaQuestionsId;
	}

	public void setKaizalaQuestionsId(Long kaizalaQuestionsId) {
		this.kaizalaQuestionsId = kaizalaQuestionsId;
	}

	@Column(name="kaizala_actions_id")
	public Long getKaizalaActionsId() {
		return kaizalaActionsId;
	}

	public void setKaizalaActionsId(Long kaizalaActionsId) {
		this.kaizalaActionsId = kaizalaActionsId;
	}
	@Column(name="question")
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	@Column(name="type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "kaizala_actions_id", insertable = false, updatable = false)
	public KaizalaActions getKaizalaActions() {
		return kaizalaActions;
	}

	public void setKaizalaActions(KaizalaActions kaizalaActions) {
		this.kaizalaActions = kaizalaActions;
	}
	
	
}
