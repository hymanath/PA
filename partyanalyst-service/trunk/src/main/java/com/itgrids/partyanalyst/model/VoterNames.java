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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;


@Entity
@Table(name = "voter_names")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VoterNames extends BaseModel implements Serializable{
	
	private Long voterNamesId;
	private Voter voter;
	private Language language;
	private String firstName;
	private String lastName;
	private String relativeFirstName;
	private String relativeLastName;
	
	
	public VoterNames()
	{
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "voter_names_id", nullable = false, unique = true)
	public Long getVoterNamesId() {
		return voterNamesId;
	}


	public void setVoterNamesId(Long voterNamesId) {
		this.voterNamesId = voterNamesId;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "voterNames")
	@JoinColumn(name = "voter_names_id")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Voter getVoter() {
		return voter;
	}

	public void setVoter(Voter voter) {
		this.voter = voter;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="language_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Language getLanguage() {
		return language;
	}


	public void setLanguage(Language language) {
		this.language = language;
	}
	
	@Column(name = "firstname", length =45 )
	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "lastname", length =45 )
	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "relative_firstname", length =45 )
	public String getRelativeFirstName() {
		return relativeFirstName;
	}


	public void setRelativeFirstName(String relativeFirstName) {
		this.relativeFirstName = relativeFirstName;
	}

	@Column(name = "relative_lastname", length =45 )
	public String getRelativeLastName() {
		return relativeLastName;
	}


	public void setRelativeLastName(String relativeLastName) {
		this.relativeLastName = relativeLastName;
	}
	
	
}
