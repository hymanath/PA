package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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
@Table(name="voter_category_value")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VoterCategoryValue extends BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5996019172386252750L;
	private Long voterCategoryValueId;
	private UserVoterCategoryValue userVoterCategoryValue;
	private Voter voter;
	private User user;
	
	public VoterCategoryValue(){
		
	}
	
	public VoterCategoryValue(Long voterCategoryValueId,UserVoterCategoryValue userVoterCategoryValue,Voter voter){
		this.voterCategoryValueId = voterCategoryValueId;
		this.userVoterCategoryValue = userVoterCategoryValue;
		this.voter=voter;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "voter_category_value_id", unique = true, nullable = false)
	public Long getVoterCategoryValueId() {
		return voterCategoryValueId;
	}

	public void setVoterCategoryValueId(Long voterCategoryValueId) {
		this.voterCategoryValueId = voterCategoryValueId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_voter_category_value_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserVoterCategoryValue getUserVoterCategoryValue() {
		return userVoterCategoryValue;
	}

	public void setUserVoterCategoryValue(
			UserVoterCategoryValue userVoterCategoryValue) {
		this.userVoterCategoryValue = userVoterCategoryValue;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="voter_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Voter getVoter() {
		return voter;
	}
	public void setVoter(Voter voter) {
		this.voter = voter;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
