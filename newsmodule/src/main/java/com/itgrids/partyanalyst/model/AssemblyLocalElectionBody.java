/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on September 28, 2010
 */
package com.itgrids.partyanalyst.model;

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
import org.hibernate.annotations.NotFoundAction;
/**
 * AssemblyLocalElectionBody entity. 
 * @author <a href="mailto:raghavenderprasad@gmail.com">Raghav</a>
 */
@Entity
@Table(name = "assembly_local_election_body")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AssemblyLocalElectionBody extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long assemblyLocalElectionBodyId;
	private LocalElectionBody localElectionBody;
	private Constituency constituency;
	private String isPartial;
	private String year;
	
	public AssemblyLocalElectionBody() {
		super();		
	}

	public AssemblyLocalElectionBody(Long assemblyLocalElectionBodyId,
			LocalElectionBody localElectionBody, String isPartial, String year) {
		super();
		this.assemblyLocalElectionBodyId = assemblyLocalElectionBodyId;
		this.localElectionBody = localElectionBody;
		this.isPartial = isPartial;
		this.year = year;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "assembly_local_election_body_id", nullable = false, unique = true)
	public Long getAssemblyLocalElectionBodyId() {
		return assemblyLocalElectionBodyId;
	}

	public void setAssemblyLocalElectionBodyId(Long assemblyLocalElectionBodyId) {
		this.assemblyLocalElectionBodyId = assemblyLocalElectionBodyId;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "local_election_body_id")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public LocalElectionBody getLocalElectionBody() {
		return localElectionBody;
	}

	public void setLocalElectionBody(LocalElectionBody localElectionBody) {
		this.localElectionBody = localElectionBody;
	}

	@Column(name="is_partial", length=1)
	public String getIsPartial() {
		return isPartial;
	}

	public void setIsPartial(String isPartial) {
		this.isPartial = isPartial;
	}

	@Column(name="year", length=4)
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "constituency_id")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}

	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}
	
	
	
	
	
	
	
	
}
