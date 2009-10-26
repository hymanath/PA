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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Township entity. 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */
@Entity
@Table(name = "township")
public class Township implements java.io.Serializable {

	/**
	 * The Serial Version UID.
	 */
	private static final long serialVersionUID = 1525588565707839357L;

	// Fields

	private Long townshipId;
	private Tehsil tehsil;
	private String townshipName;
	private Long townshipCode;
	private String townshipType;
	private Set<Ward> wards = new HashSet<Ward>(0);

	// Constructors

	/** default constructor */
	public Township() {
	}

	/** minimal constructor */
	public Township(Long townshipId) {
		this.townshipId = townshipId;
	}

	/** full constructor */
	public Township(Long townshipId, Tehsil tehsil, String townshipName,
			Long townshipCode, String townshipType, Set<Ward> wards) {
		this.townshipId = townshipId;
		this.tehsil = tehsil;
		this.townshipName = townshipName;
		this.townshipCode = townshipCode;
		this.townshipType = townshipType;
		this.wards = wards;
	}

	// Property accessors
	@Id
	@Column(name = "township_id", unique = true, nullable = false)
	public Long getTownshipId() {
		return this.townshipId;
	}

	public void setTownshipId(Long townshipId) {
		this.townshipId = townshipId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tehsil_id")
	public Tehsil getTehsil() {
		return this.tehsil;
	}

	public void setTehsil(Tehsil tehsil) {
		this.tehsil = tehsil;
	}

	@Column(name = "township_name", length = 100)
	public String getTownshipName() {
		return this.townshipName;
	}

	public void setTownshipName(String townshipName) {
		this.townshipName = townshipName;
	}

	@Column(name = "township_code")
	public Long getTownshipCode() {
		return this.townshipCode;
	}

	public void setTownshipCode(Long townshipCode) {
		this.townshipCode = townshipCode;
	}

	@Column(name = "township_type", length = 25)
	public String getTownshipType() {
		return this.townshipType;
	}

	public void setTownshipType(String townshipType) {
		this.townshipType = townshipType;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "township")
	public Set<Ward> getWards() {
		return this.wards;
	}

	public void setWards(Set<Ward> wards) {
		this.wards = wards;
	}

}