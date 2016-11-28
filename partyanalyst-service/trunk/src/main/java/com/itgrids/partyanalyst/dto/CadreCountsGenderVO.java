package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class CadreCountsGenderVO  implements Serializable{
	
	private static final long serialVersionUID = -5712561275525541377L;

	private Long id;
	private String name;
	
	private Long   superLocationId;
	private String superlocationName;
	
	private Long previousCadreTotalCount = 0l;
	private Long previousCadreMaleCount = 0l;
	private Long previousCadreFemaleCount = 0l;
	private Double previousCadreMalePerc = 0.0;
	private Double previousCadreFemalePerc = 0.0;
	
	private Long cadreTotalCount = 0l;
	private Long cadreMaleCount = 0l;
	private Long cadreFemaleCount = 0l;
	private Double cadreMalePerc = 0.0;
	private Double cadreFemalePerc = 0.0;
	
	
	private Long newCadreTotalCount = 0l;
	private Long newCadreMaleCount = 0l;
	private Long newCadreFemaleCount = 0l;
	private Double newCadreMalePerc = 0.0;
	private Double newCadreFemalePerc = 0.0;
	
	
	private Long renewalCadreTotalCount = 0l;
	private Long renewalCadreMaleCount = 0l;
	private Long renewalCadreFemaleCount = 0l;
	private Double renewalCadreMalePerc = 0.0;
	private Double renewalCadreFemalePerc = 0.0;
	
	private Double newCadrePerc = 0.0;
	private Double renewalCadrePerc = 0.0;
	
	private Double previousCadreRenewalPerc = 0.0; 
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getPreviousCadreTotalCount() {
		return previousCadreTotalCount;
	}
	public void setPreviousCadreTotalCount(Long previousCadreTotalCount) {
		this.previousCadreTotalCount = previousCadreTotalCount;
	}
	public Long getPreviousCadreMaleCount() {
		return previousCadreMaleCount;
	}
	public void setPreviousCadreMaleCount(Long previousCadreMaleCount) {
		this.previousCadreMaleCount = previousCadreMaleCount;
	}
	public Long getPreviousCadreFemaleCount() {
		return previousCadreFemaleCount;
	}
	public void setPreviousCadreFemaleCount(Long previousCadreFemaleCount) {
		this.previousCadreFemaleCount = previousCadreFemaleCount;
	}
	public Double getPreviousCadreMalePerc() {
		return previousCadreMalePerc;
	}
	public void setPreviousCadreMalePerc(Double previousCadreMalePerc) {
		this.previousCadreMalePerc = previousCadreMalePerc;
	}
	public Double getPreviousCadreFemalePerc() {
		return previousCadreFemalePerc;
	}
	public void setPreviousCadreFemalePerc(Double previousCadreFemalePerc) {
		this.previousCadreFemalePerc = previousCadreFemalePerc;
	}
	public Long getCadreTotalCount() {
		return cadreTotalCount;
	}
	public void setCadreTotalCount(Long cadreTotalCount) {
		this.cadreTotalCount = cadreTotalCount;
	}
	public Long getCadreMaleCount() {
		return cadreMaleCount;
	}
	public void setCadreMaleCount(Long cadreMaleCount) {
		this.cadreMaleCount = cadreMaleCount;
	}
	public Long getCadreFemaleCount() {
		return cadreFemaleCount;
	}
	public void setCadreFemaleCount(Long cadreFemaleCount) {
		this.cadreFemaleCount = cadreFemaleCount;
	}
	public Double getCadreMalePerc() {
		return cadreMalePerc;
	}
	public void setCadreMalePerc(Double cadreMalePerc) {
		this.cadreMalePerc = cadreMalePerc;
	}
	public Double getCadreFemalePerc() {
		return cadreFemalePerc;
	}
	public void setCadreFemalePerc(Double cadreFemalePerc) {
		this.cadreFemalePerc = cadreFemalePerc;
	}
	public Long getNewCadreTotalCount() {
		return newCadreTotalCount;
	}
	public void setNewCadreTotalCount(Long newCadreTotalCount) {
		this.newCadreTotalCount = newCadreTotalCount;
	}
	public Long getNewCadreMaleCount() {
		return newCadreMaleCount;
	}
	public void setNewCadreMaleCount(Long newCadreMaleCount) {
		this.newCadreMaleCount = newCadreMaleCount;
	}
	public Long getNewCadreFemaleCount() {
		return newCadreFemaleCount;
	}
	public void setNewCadreFemaleCount(Long newCadreFemaleCount) {
		this.newCadreFemaleCount = newCadreFemaleCount;
	}
	public Double getNewCadreMalePerc() {
		return newCadreMalePerc;
	}
	public void setNewCadreMalePerc(Double newCadreMalePerc) {
		this.newCadreMalePerc = newCadreMalePerc;
	}
	public Double getNewCadreFemalePerc() {
		return newCadreFemalePerc;
	}
	public void setNewCadreFemalePerc(Double newCadreFemalePerc) {
		this.newCadreFemalePerc = newCadreFemalePerc;
	}
	public Long getRenewalCadreTotalCount() {
		return renewalCadreTotalCount;
	}
	public void setRenewalCadreTotalCount(Long renewalCadreTotalCount) {
		this.renewalCadreTotalCount = renewalCadreTotalCount;
	}
	public Long getRenewalCadreMaleCount() {
		return renewalCadreMaleCount;
	}
	public void setRenewalCadreMaleCount(Long renewalCadreMaleCount) {
		this.renewalCadreMaleCount = renewalCadreMaleCount;
	}
	public Long getRenewalCadreFemaleCount() {
		return renewalCadreFemaleCount;
	}
	public void setRenewalCadreFemaleCount(Long renewalCadreFemaleCount) {
		this.renewalCadreFemaleCount = renewalCadreFemaleCount;
	}
	public Double getRenewalCadreMalePerc() {
		return renewalCadreMalePerc;
	}
	public void setRenewalCadreMalePerc(Double renewalCadreMalePerc) {
		this.renewalCadreMalePerc = renewalCadreMalePerc;
	}
	public Double getRenewalCadreFemalePerc() {
		return renewalCadreFemalePerc;
	}
	public void setRenewalCadreFemalePerc(Double renewalCadreFemalePerc) {
		this.renewalCadreFemalePerc = renewalCadreFemalePerc;
	}
	public Double getNewCadrePerc() {
		return newCadrePerc;
	}
	public void setNewCadrePerc(Double newCadrePerc) {
		this.newCadrePerc = newCadrePerc;
	}
	public Double getRenewalCadrePerc() {
		return renewalCadrePerc;
	}
	public void setRenewalCadrePerc(Double renewalCadrePerc) {
		this.renewalCadrePerc = renewalCadrePerc;
	}
	public Double getPreviousCadreRenewalPerc() {
		return previousCadreRenewalPerc;
	}
	public void setPreviousCadreRenewalPerc(Double previousCadreRenewalPerc) {
		this.previousCadreRenewalPerc = previousCadreRenewalPerc;
	}
	public Long getSuperLocationId() {
		return superLocationId;
	}
	public void setSuperLocationId(Long superLocationId) {
		this.superLocationId = superLocationId;
	}
	public String getSuperlocationName() {
		return superlocationName;
	}
	public void setSuperlocationName(String superlocationName) {
		this.superlocationName = superlocationName;
	}
	
}
