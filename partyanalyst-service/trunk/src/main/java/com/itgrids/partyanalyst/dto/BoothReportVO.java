package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class BoothReportVO implements Serializable{

	private static final long serialVersionUID = 7500310596826031890L;

	private Long boothId;
	private String partNo;
	private String location;
	private Long totalVoters;
	private Long noOfFamilies;
	private Long totalCadre;
	private Long cadreWithVoterId;
	private Long cadreWithFamilyVoterId;
	private Integer cadreAvailableFamilies;
	private Integer cadreFamilyVoters;
	private Integer cadreAvailableRefFamilies;
	private Integer cadreFamilyRefVoters;
	private String cadreAvailableFamiliesPer;
	private String cadreFamilyVotersPer;
	private String cadreAvailableRefFamiliesPer;
	private String cadreFamilyRefVotersPer;
	private List<BoothRangeVO> familyRangeList;
	
	public List<BoothRangeVO> getFamilyRangeList() {
		return familyRangeList;
	}
	public void setFamilyRangeList(List<BoothRangeVO> familyRangeList) {
		this.familyRangeList = familyRangeList;
	}
	public String getCadreAvailableFamiliesPer() {
		return cadreAvailableFamiliesPer;
	}
	public void setCadreAvailableFamiliesPer(String cadreAvailableFamiliesPer) {
		this.cadreAvailableFamiliesPer = cadreAvailableFamiliesPer;
	}
	public String getCadreFamilyVotersPer() {
		return cadreFamilyVotersPer;
	}
	public void setCadreFamilyVotersPer(String cadreFamilyVotersPer) {
		this.cadreFamilyVotersPer = cadreFamilyVotersPer;
	}
	public String getCadreAvailableRefFamiliesPer() {
		return cadreAvailableRefFamiliesPer;
	}
	public void setCadreAvailableRefFamiliesPer(String cadreAvailableRefFamiliesPer) {
		this.cadreAvailableRefFamiliesPer = cadreAvailableRefFamiliesPer;
	}
	public String getCadreFamilyRefVotersPer() {
		return cadreFamilyRefVotersPer;
	}
	public void setCadreFamilyRefVotersPer(String cadreFamilyRefVotersPer) {
		this.cadreFamilyRefVotersPer = cadreFamilyRefVotersPer;
	}
	public Long getTotalCadre() {
		return totalCadre;
	}
	public void setTotalCadre(Long totalCadre) {
		this.totalCadre = totalCadre;
	}
	public Long getCadreWithVoterId() {
		return cadreWithVoterId;
	}
	public void setCadreWithVoterId(Long cadreWithVoterId) {
		this.cadreWithVoterId = cadreWithVoterId;
	}
	public Long getCadreWithFamilyVoterId() {
		return cadreWithFamilyVoterId;
	}
	public void setCadreWithFamilyVoterId(Long cadreWithFamilyVoterId) {
		this.cadreWithFamilyVoterId = cadreWithFamilyVoterId;
	}
	public Integer getCadreAvailableFamilies() {
		return cadreAvailableFamilies;
	}
	public void setCadreAvailableFamilies(Integer cadreAvailableFamilies) {
		this.cadreAvailableFamilies = cadreAvailableFamilies;
	}
	public Integer getCadreFamilyVoters() {
		return cadreFamilyVoters;
	}
	public void setCadreFamilyVoters(Integer cadreFamilyVoters) {
		this.cadreFamilyVoters = cadreFamilyVoters;
	}
	public Integer getCadreAvailableRefFamilies() {
		return cadreAvailableRefFamilies;
	}
	public void setCadreAvailableRefFamilies(Integer cadreAvailableRefFamilies) {
		this.cadreAvailableRefFamilies = cadreAvailableRefFamilies;
	}
	public Integer getCadreFamilyRefVoters() {
		return cadreFamilyRefVoters;
	}
	public void setCadreFamilyRefVoters(Integer cadreFamilyRefVoters) {
		this.cadreFamilyRefVoters = cadreFamilyRefVoters;
	}
	public Long getTotalVoters() {
		return totalVoters;
	}
	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}
	public Long getNoOfFamilies() {
		return noOfFamilies;
	}
	public void setNoOfFamilies(Long noOfFamilies) {
		this.noOfFamilies = noOfFamilies;
	}
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	
}
