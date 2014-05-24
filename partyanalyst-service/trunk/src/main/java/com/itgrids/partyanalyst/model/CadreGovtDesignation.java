package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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
@Table(name = "cadre_govt_designation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadreGovtDesignation extends BaseModel implements Serializable {
	
private Long cadreGovtDesignationId;
private Cadre cadre;
private GovtDesignation govtDesignation;
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "cadre_govt_designation_id", unique = true, nullable = false)
public Long getCadreGovtDesignationId() {
	return cadreGovtDesignationId;
}
public void setCadreGovtDesignationId(Long cadreGovtDesignationId) {
	this.cadreGovtDesignationId = cadreGovtDesignationId;
}
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "cadre_id")
@LazyToOne(LazyToOneOption.NO_PROXY)
@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
public Cadre getCadre() {
	return cadre;
}
public void setCadre(Cadre cadre) {
	this.cadre = cadre;
}
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "govt_designation_id")
@LazyToOne(LazyToOneOption.NO_PROXY)
@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
public GovtDesignation getGovtDesignation() {
	return govtDesignation;
}
public void setGovtDesignation(GovtDesignation govtDesignation) {
	this.govtDesignation = govtDesignation;
}



}
