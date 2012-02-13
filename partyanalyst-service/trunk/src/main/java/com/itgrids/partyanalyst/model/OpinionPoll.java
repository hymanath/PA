package com.itgrids.partyanalyst.model;

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
/**
 * opinion_poll entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name="opinion_poll")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OpinionPoll extends BaseModel implements java.io.Serializable  {
		/**
		 * 
		 */
	 private static final long serialVersionUID = 1L;
	 
	 private Long opinionPollId;
	 private Registration registration;
	 private String title;
	 private Date opinionPollStartDate;
	 private Date opinionPollEndDate;
	 private String description;
	 private String is_delete;

	 private Set<OpinionPollQuestions> opinionPollQuestions = new HashSet<OpinionPollQuestions>(0);

	 /** default constructor */  
	
	 	public OpinionPoll() {
	}
	
	  /** full constructor */
	
	 	public OpinionPoll(Registration registration,
			Date opinionPollStartDate,Date opinionPollEndDate,String description,
			Set<OpinionPollQuestions> opinionPollQuestions) {
	 		this.registration=registration;
	 		this.opinionPollStartDate = opinionPollStartDate;
	 		this.opinionPollEndDate = opinionPollEndDate;
	 		this.description = description;
	 		this.opinionPollQuestions = opinionPollQuestions;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="opinion_poll_id", unique=true, nullable=false)
	public Long getOpinionPollId() {
		return opinionPollId;
	}
	
	public void setOpinionPollId(Long opinionPollId) {
		this.opinionPollId = opinionPollId;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
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
	@Column(name = "opinion_poll_start_date", length = 10)
	public Date getOpinionPollStartDate() {
		return opinionPollStartDate;
	}

	public void setOpinionPollStartDate(Date opinionPollStartDate) {
		this.opinionPollStartDate = opinionPollStartDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "opinion_poll_end_date", length = 10)
	public Date getOpinionPollEndDate() {
		return opinionPollEndDate;
	}

	public void setOpinionPollEndDate(Date opinionPollEndDate) {
		this.opinionPollEndDate = opinionPollEndDate;
	}

	@Column(name="description",length=300)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "opinionPoll")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<OpinionPollQuestions> getOpinionPollQuestions() {
		return opinionPollQuestions;
	}

	public void setOpinionPollQuestions(
			Set<OpinionPollQuestions> opinionPollQuestions) {
		this.opinionPollQuestions = opinionPollQuestions;
	}
	@Column(name="title",length=300)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name="is_delete",length=10)
	public String getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(String is_delete) {
		this.is_delete = is_delete;
	}
	
	
	
	}