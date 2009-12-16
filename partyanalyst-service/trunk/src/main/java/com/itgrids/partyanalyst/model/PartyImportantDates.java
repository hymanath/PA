/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on December 12, 2009
 */
package com.itgrids.partyanalyst.model;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity	
@Table(name="party_important_date")
public class PartyImportantDates extends BaseModel {

	
	private static final long serialVersionUID = 1L;

	private Long partyImportantDateId ;
	private Date date;
	private String importance ;
	private Party party;
	private String recursive;
	private String recursiveFrequency;
	
	public PartyImportantDates() {
		
	}

	public PartyImportantDates(Date date, String importance, Party party,String recursive,String recursiveFrequency) {

		this.date = date;
		this.importance = importance;
		this.party = party;
		this.recursive = recursive;
		this.recursiveFrequency = recursiveFrequency;
	}
	
	@Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "party_important_date_id", unique = true, nullable = false)
	public Long getPartyImportantDateId() {
		return partyImportantDateId;
	}

	public void setPartyImportantDateId(Long partyImportantDateId) {
		this.partyImportantDateId = partyImportantDateId;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="Date",length=10)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	@Column(name = "importance", length = 100)
	public String getImportance() {
		return importance;
	}

	public void setImportance(String importance) {
		this.importance = importance;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="party_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}
	
	@Column(name = "recursiveness", length = 1)
	public String getRecursive() {
		return recursive;
	}

	public void setRecursive(String recursive) {
		this.recursive = recursive;
	}
	
	@Column(name = "recursive_frequency", length = 25)
	public String getRecursiveFrequency() {
		return recursiveFrequency;
	}

	public void setRecursiveFrequency(String recursiveFrequency) {
		this.recursiveFrequency = recursiveFrequency;
	}
	
	
	
	
	
	
}
