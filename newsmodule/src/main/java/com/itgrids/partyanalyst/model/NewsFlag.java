package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import com.itgrids.partyanalyst.model.BaseModel;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;
@Entity
@Table(name = "news_flag")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NewsFlag  extends BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long newsFlagId;
	private FileGallary fileGallary;	
	private Candidate candidate;
	private User user;
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "news_flag_id", unique = true, nullable = false)
	public Long getNewsFlagId() {
		return newsFlagId;
	}
	public void setNewsFlagId(Long newsFlagId) {
		this.newsFlagId = newsFlagId;
	}
	


	//@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fileGallary")
	@OneToOne(fetch =FetchType.LAZY)
	@JoinColumn(name = "file_gallary_id")
	public FileGallary getFileGallary() {
		return fileGallary;
	}
	public void setFileGallary(FileGallary fileGallary) {
		this.fileGallary = fileGallary;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "candidate_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Candidate getCandidate() {
		return candidate;
	}
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}

