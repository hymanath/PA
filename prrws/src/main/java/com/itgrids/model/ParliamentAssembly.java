package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parliament_assembly")
public class ParliamentAssembly{
		
	private static final long serialVersionUID = -2853930539938433902L;

	private Long parliamentAssemblyId;
	private Long parliamentId;
	
	private Long assemblyId;

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
	
}
