package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="homepage_question")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class HomePageQuestion implements Serializable{
	
	 private static final long serialVersionUID = 1L;
	 private Long homePageQuestionId;	
	 private Registration registration;
	 private Date questionStartDate;
	 private Date questionEndDate;
	 private String question;
	 private String isDelete;
	 
	 private Set<HomePageQuestionAnswers> homePageQuestionAnswers = new HashSet<HomePageQuestionAnswers>();
	 
	 	
	 /** default constructor */
 	public HomePageQuestion() {
 	}
 	
 	
 	/** full constructor */
 	
 	public HomePageQuestion(Registration registration,
			Date questionStartDate, Date questionEndDate, String question,
			String isDelete) {
		this.registration = registration;
		this.questionStartDate = questionStartDate;
		this.questionEndDate = questionEndDate;
		this.question = question;
		this.isDelete = isDelete;
	}
 	
 	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="homepage_question_id", unique=true, nullable=false)
	public Long getHomePageQuestionId() {
		return homePageQuestionId;
	}
	
	public void setHomePageQuestionId(Long homepageQuestionId) {
		this.homePageQuestionId = homepageQuestionId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="registration_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Registration getRegistration() {
		return registration;
	}
	
	public void setRegistration(Registration registration) {
		this.registration = registration;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "question_start_date", length = 10)
	public Date getQuestionStartDate() {
		return questionStartDate;
	}
	
	public void setQuestionStartDate(Date questionStartDate) {
		this.questionStartDate = questionStartDate;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "question_end_date", length = 10)
	public Date getQuestionEndDate() {
		return questionEndDate;
	}
	
	public void setQuestionEndDate(Date questionEndDate) {
		this.questionEndDate = questionEndDate;
	}
	
	@Column(name="question",length=500)
	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	@Column(name="is_delete",length=10)
	public String getIsDelete() {
		return isDelete;
	}
	
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "homePageQuestion")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<HomePageQuestionAnswers> getHomePageQuestionAnswers() {
		return homePageQuestionAnswers;
	}


	public void setHomePageQuestionAnswers(
			Set<HomePageQuestionAnswers> homePageQuestionAnswers) {
		this.homePageQuestionAnswers = homePageQuestionAnswers;
	}
 
 
}
