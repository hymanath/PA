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
import org.hibernate.annotations.NotFoundAction;

/**
 * Ward entity. 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */
@Entity
@Table(name = "ward")
public class Ward implements java.io.Serializable {

	/**
	 * The Serial Version UID.
	 */
	private static final long serialVersionUID = -2036446525734366674L;
	
	// Fields
	
	private Long wardId;
	private Township township;
	private String wardName;
	private Long wardCode;
	private Set<ProblemLocation> problemLocations = new HashSet<ProblemLocation>(0);
	private Set<DelimitationWard> delimitationWard = new HashSet<DelimitationWard>(0);
	private Set<Block> block = new HashSet<Block>(0);

	// Constructors
	
	/** default constructor */
	public Ward() {
	}

	/** minimal constructor */
	public Ward(Long wardId) {
		this.wardId = wardId;
	}

	/** full constructor */
	public Ward(Long wardId, Township township, String wardName, Long wardCode,
			Set<ProblemLocation> problemLocations) {
		this.wardId = wardId;
		this.township = township;
		this.wardName = wardName;
		this.wardCode = wardCode;
		this.problemLocations = problemLocations;
	}

	// Property accessors
	@Id
	@Column(name = "ward_id", unique = true, nullable = false)
	public Long getWardId() {
		return this.wardId;
	}

	public void setWardId(Long wardId) {
		this.wardId = wardId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "township_id")
	public Township getTownship() {
		return this.township;
	}

	public void setTownship(Township township) {
		this.township = township;
	}

	@Column(name = "ward_name", length = 100)
	public String getWardName() {
		return this.wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
	}

	@Column(name = "ward_code")
	public Long getWardCode() {
		return this.wardCode;
	}

	public void setWardCode(Long wardCode) {
		this.wardCode = wardCode;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ward")
	public Set<ProblemLocation> getProblemLocations() {
		return problemLocations;
	}

	public void setProblemLocations(Set<ProblemLocation> problemLocations) {
		this.problemLocations = problemLocations;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ward")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<DelimitationWard> getDelimitationWard() {
		return delimitationWard;
	}

	public void setDelimitationWard(Set<DelimitationWard> delimitationWard) {
		this.delimitationWard = delimitationWard;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ward")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<Block> getBlock() {
		return block;
	}

	public void setBlock(Set<Block> block) {
		this.block = block;
	}

	
}