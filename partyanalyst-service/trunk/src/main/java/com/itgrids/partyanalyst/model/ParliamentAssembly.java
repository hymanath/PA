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
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="parliament_assembly")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ParliamentAssembly {

	private Long parliamentAssemblyId;
	private Long parliamentId;
	private Long assemblyId;
	private Long stateId;
	
	private Constituency parliamentAssembly;
	private Constituency assembly;
	private State state;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="parliament_assembly_id", unique=true, nullable=false)
	public Long getParliamentAssemblyId() {
		return parliamentAssemblyId;
	}
	public void setParliamentAssemblyId(Long parliamentAssemblyId) {
		this.parliamentAssemblyId = parliamentAssemblyId;
	}
	@Column(name="parliament_id")
	public Long getParliamentId() {
		return parliamentId;
	}
	public void setParliamentId(Long parliamentId) {
		this.parliamentId = parliamentId;
	}
	@Column(name="assembly_id")
	public Long getAssemblyId() {
		return assemblyId;
	}
	public void setAssemblyId(Long assemblyId) {
		this.assemblyId = assemblyId;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="parliament_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getParliamentAssembly() {
		return parliamentAssembly;
	}
	public void setParliamentAssembly(Constituency parliamentAssembly) {
		this.parliamentAssembly = parliamentAssembly;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="assembly_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getAssembly() {
		return assembly;
	}
	public void setAssembly(Constituency assembly) {
		this.assembly = assembly;
	}
	
	@Column(name="state_id")
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="state_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	
	
	
}
