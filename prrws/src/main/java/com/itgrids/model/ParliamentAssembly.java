package com.itgrids.model;

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

@Entity
@Table(name = "parliament_assembly")
public class ParliamentAssembly{
		
	private static final long serialVersionUID = -2853930539938433902L;

	private Long parliamentAssemblyId;
	private Long parliamentId;
	
	private Long assemblyId;
	
	private Constituency parliament;
	private Constituency assembly;

	@Id
	@Column(name="parliament_assembly_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
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
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "parliament_id", insertable = false, updatable = false)
	public Constituency getParliament() {
		return parliament;
	}
	public void setParliament(Constituency parliament) {
		this.parliament = parliament;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "assembly_id", insertable = false, updatable = false)
	public Constituency getAssembly() {
		return assembly;
	}
	public void setAssembly(Constituency assembly) {
		this.assembly = assembly;
	}
	
	
}
