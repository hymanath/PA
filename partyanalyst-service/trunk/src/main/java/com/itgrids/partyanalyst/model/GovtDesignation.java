package com.itgrids.partyanalyst.model;

import java.io.Serializable;
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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFoundAction;
@Entity
@Table(name = "govt_designation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GovtDesignation extends BaseModel implements Serializable {
	
	private Long govtDesignationId;
	private String govtDesignationName;
	 private Set<CadreGovtDesignation> cadreGovtDesignation = new HashSet<CadreGovtDesignation>(0);
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "govt_designation_id", unique = true, nullable = false)
	public Long getGovtDesignationId() {
		return govtDesignationId;
	}
	public void setGovtDesignationId(Long govtDesignationId) {
		this.govtDesignationId = govtDesignationId;
	}
	@Column(name = "Govt_designation_name")
	public String getGovtDesignationName() {
		return govtDesignationName;
	}
	public void setGovtDesignationName(String govtDesignationName) {
		this.govtDesignationName = govtDesignationName;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "govtDesignation")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<CadreGovtDesignation> getCadreGovtDesignation() {
		return cadreGovtDesignation;
	}
	public void setCadreGovtDesignation(
			Set<CadreGovtDesignation> cadreGovtDesignation) {
		this.cadreGovtDesignation = cadreGovtDesignation;
	}
	
	

}
