/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */
package com.itgrids.partyanalyst.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.NotFoundAction;

/**
 * Party entity. 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */
@Entity
@Table(name = "party")
public class Party implements java.io.Serializable {

	/**
	 * The Serial Version UID.
	 */
	private static final long serialVersionUID = 6353823654394427829L;
	
	// Fields
	
	private Long partyId;
	private String longName;
	private String shortName;
	private String symbol;
	private String address;
	private String comments;
	private String partyRecognization;
	private Set<Nomination> nominations = new HashSet<Nomination>(0);
	private Set<Registration> registrations = new HashSet<Registration>(0);
	private Set<PartyImportantDates> partyImportantDates = new HashSet<PartyImportantDates>(0);
	// Constructors

	/** default constructor */
	public Party() {
	}

	/** minimal constructor */
	public Party(Long partyId) {
		this.partyId = partyId;
	}

	/** full constructor */
	public Party(Long partyId, String longName, String shortName,
			String symbol, String address, String comments,
			String partyRecognization, Set<Nomination> nominations) {
		this.partyId = partyId;
		this.longName = longName;
		this.shortName = shortName;
		this.symbol = symbol;
		this.address = address;
		this.comments = comments;
		this.partyRecognization = partyRecognization;
		this.nominations = nominations;
	}
	
	public Party(Long partyId, String longName, String shortName,
			String symbol, String address, String comments,
			String partyRecognization, Set<Nomination> nominations,
			Set<Registration> registrations, Set<PartyImportantDates> partyImportantDates) {
		this.partyId = partyId;
		this.longName = longName;
		this.shortName = shortName;
		this.symbol = symbol;
		this.address = address;
		this.comments = comments;
		this.partyRecognization = partyRecognization;
		this.nominations = nominations;
		this.registrations = registrations;
		this.partyImportantDates = partyImportantDates;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "party_id", unique = true, nullable = false)
	public Long getPartyId() {
		return this.partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	@Column(name = "long_name", length = 100)
	public String getLongName() {
		return this.longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	@Column(name = "short_name", length = 25)
	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Column(name = "symbol", length = 25)
	public String getSymbol() {
		return this.symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	@Column(name = "address", length = 300)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "comments", length = 300)
	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Column(name = "party_recognization", length = 25)
	public String getPartyRecognization() {
		return this.partyRecognization;
	}

	public void setPartyRecognization(String partyRecognization) {
		this.partyRecognization = partyRecognization;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "party")
	public Set<Nomination> getNominations() {
		return this.nominations;
	}

	public void setNominations(Set<Nomination> nominations) {
		this.nominations = nominations;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "party")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
		public Set<Registration> getRegistrations() {
		return registrations;
	}

	public void setRegistrations(Set<Registration> registrations) {
		this.registrations = registrations;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "party")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<PartyImportantDates> getpartyImportantDates() {
		return partyImportantDates;
	}

	public void setpartyImportantDates(Set<PartyImportantDates> partyImportantDates) {
		this.partyImportantDates = partyImportantDates;
	}
	
	


}