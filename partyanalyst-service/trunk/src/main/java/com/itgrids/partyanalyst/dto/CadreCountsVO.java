package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class CadreCountsVO implements Serializable{
	
	private static final long serialVersionUID = -1434438112666430567L;
	
	private Long    id;
	private String  name;
	
	private Long   superLocationId;
	private String superlocationName;
	
	private Long    previousCadreCount = 0l;
	private Double  previousCadrePercent = 0.0;
	private Double  previousCadreRenewalPercent = 0.0;
	
	private Long   cadreCount = 0l;
	private Double cadrePercent = 0.0;
	private Long   newCadre=0l;
	private Double newCadrePercent = 0.0;
	private Long   renewalCadre = 0l;
	private Double renewalCadrePercent=0.0;
	
	private Long previousCadreTotalCount = 0l;
	private Long cadreTotalCount = 0l;
	private Long newCadreTotalCount = 0l;
	private Long renewalCadreTotalCount = 0l;
	   
	private List<CadreCountsVO> subList;
	private Map<Long,CadreCountsVO> subMap;
	
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
	public Long getPreviousCadreCount() {
		return previousCadreCount;
	}
	public void setPreviousCadreCount(Long previousCadreCount) {
		this.previousCadreCount = previousCadreCount;
	}
	public Double getPreviousCadrePercent() {
		return previousCadrePercent;
	}
	public void setPreviousCadrePercent(Double previousCadrePercent) {
		this.previousCadrePercent = previousCadrePercent;
	}
	public Long getCadreCount() {
		return cadreCount;
	}
	public void setCadreCount(Long cadreCount) {
		this.cadreCount = cadreCount;
	}
	public Double getCadrePercent() {
		return cadrePercent;
	}
	public void setCadrePercent(Double cadrePercent) {
		this.cadrePercent = cadrePercent;
	}
	public Long getNewCadre() {
		return newCadre;
	}
	public void setNewCadre(Long newCadre) {
		this.newCadre = newCadre;
	}
	public Double getNewCadrePercent() {
		return newCadrePercent;
	}
	public void setNewCadrePercent(Double newCadrePercent) {
		this.newCadrePercent = newCadrePercent;
	}
	public Long getRenewalCadre() {
		return renewalCadre;
	}
	public void setRenewalCadre(Long renewalCadre) {
		this.renewalCadre = renewalCadre;
	}
	public Double getRenewalCadrePercent() {
		return renewalCadrePercent;
	}
	public void setRenewalCadrePercent(Double renewalCadrePercent) {
		this.renewalCadrePercent = renewalCadrePercent;
	}
	public List<CadreCountsVO> getSubList() {
		return subList;
	}
	public void setSubList(List<CadreCountsVO> subList) {
		this.subList = subList;
	}
	public Long getPreviousCadreTotalCount() {
		return previousCadreTotalCount;
	}
	public void setPreviousCadreTotalCount(Long previousCadreTotalCount) {
		this.previousCadreTotalCount = previousCadreTotalCount;
	}
	public Long getCadreTotalCount() {
		return cadreTotalCount;
	}
	public void setCadreTotalCount(Long cadreTotalCount) {
		this.cadreTotalCount = cadreTotalCount;
	}
	public Long getNewCadreTotalCount() {
		return newCadreTotalCount;
	}
	public void setNewCadreTotalCount(Long newCadreTotalCount) {
		this.newCadreTotalCount = newCadreTotalCount;
	}
	public Long getRenewalCadreTotalCount() {
		return renewalCadreTotalCount;
	}
	public void setRenewalCadreTotalCount(Long renewalCadreTotalCount) {
		this.renewalCadreTotalCount = renewalCadreTotalCount;
	}
	public Double getPreviousCadreRenewalPercent() {
		return previousCadreRenewalPercent;
	}
	public void setPreviousCadreRenewalPercent(Double previousCadreRenewalPercent) {
		this.previousCadreRenewalPercent = previousCadreRenewalPercent;
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
	public Map<Long, CadreCountsVO> getSubMap() {
		return subMap;
	}
	public void setSubMap(Map<Long, CadreCountsVO> subMap) {
		this.subMap = subMap;
	}
	
}
