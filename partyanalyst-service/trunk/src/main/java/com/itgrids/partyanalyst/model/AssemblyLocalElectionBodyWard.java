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
 * AssemblyLocaElectionBodyWard entity. 
 * @author <a href="mailto:raghavenderprasad@gmail.com">Raghav</a>
 */
@Entity
@Table(name = "assembly_local_election_body_ward")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AssemblyLocalElectionBodyWard extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long assemblyLocalElectionBodyWardId;
	private AssemblyLocalElectionBody assemblyLocalElectionBody;
	private Constituency constituency;
	private String year;
	
	public AssemblyLocalElectionBodyWard() {
		super();		
	}

	public AssemblyLocalElectionBodyWard(Long assemblyLocalElectionBodyWardId,
			AssemblyLocalElectionBody assemblyLocalElectionBody, String year) {
		super();
		this.assemblyLocalElectionBodyWardId = assemblyLocalElectionBodyWardId;
		this.assemblyLocalElectionBody = assemblyLocalElectionBody;
		this.year = year;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "assembly_local_election_body_ward_id", nullable = false, unique = true)
	public Long getAssemblyLocalElectionBodyWardId() {
		return assemblyLocalElectionBodyWardId;
	}

	public void setAssemblyLocalElectionBodyWardId(
			Long assemblyLocalElectionBodyWardId) {
		this.assemblyLocalElectionBodyWardId = assemblyLocalElectionBodyWardId;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "assembly_local_election_body_id")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AssemblyLocalElectionBody getAssemblyLocalElectionBody() {
		return assemblyLocalElectionBody;
	}

	public void setAssemblyLocalElectionBody(
			AssemblyLocalElectionBody assemblyLocalElectionBody) {
		this.assemblyLocalElectionBody = assemblyLocalElectionBody;
	}

	@Column(name="year", length=4)
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "local_election_body_ward_id")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}

	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}

}
