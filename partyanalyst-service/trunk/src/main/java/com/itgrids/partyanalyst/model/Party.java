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
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

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

	// Property accessors
	@Id
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


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).appendSuper(super.toString()).append(
				"partyId", partyId).append("longName", longName).append(
				"shortName", shortName).append("symbol", symbol).append(
				"address", address).append("comments", comments).append(
				"partyRecognization", partyRecognization).append("nominations",
				nominations).toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Party))
			return false;
		Party castOther = (Party) other;
		return new EqualsBuilder().append(partyId, castOther.partyId).append(
				longName, castOther.longName).append(shortName,
				castOther.shortName).append(symbol, castOther.symbol).append(
				address, castOther.address)
				.append(comments, castOther.comments).append(
						partyRecognization, castOther.partyRecognization)
				.append(nominations, castOther.nominations).isEquals();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(partyId).append(longName)
				.append(shortName).append(symbol).append(address).append(
						comments).append(partyRecognization)
				.append(nominations).toHashCode();
	}

}