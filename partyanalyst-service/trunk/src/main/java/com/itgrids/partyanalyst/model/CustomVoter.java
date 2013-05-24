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
@Table(name = "custom_voter")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CustomVoter extends BaseModel implements Serializable{


	private static final long serialVersionUID = -9150872003511725992L;
	
	
	private Long customVoterId;
	private CustomVoterGroup customVoterGroup;
	private Voter voter;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "custom_voter_id", nullable = false, unique = true)
	public Long getCustomVoterId() {
		return customVoterId;
	}
	public void setCustomVoterId(Long customVoterId) {
		this.customVoterId = customVoterId;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "custom_voter_group_id" ,insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CustomVoterGroup getCustomVoterGroup() {
		return customVoterGroup;
	}
	public void setCustomVoterGroup(CustomVoterGroup customVoterGroup) {
		this.customVoterGroup = customVoterGroup;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "voter_id" ,insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Voter getVoter() {
		return voter;
	}
	public void setVoter(Voter voter) {
		this.voter = voter;
	}
	
	
	
}
