package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class ParticipantVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String partyName;
	private Long partyId;
	private List<SelectOptionVO> scaleList;
	private List<SelectOptionVO> roleList;
	private List<SelectOptionVO> expRoleList;
	private String summery;
	private String prtiRoles;
	private String expRoles;
	private Double perc;
	private List<SelectOptionVO> candidatesList;
	private Long locationId;
	
	
	public List<SelectOptionVO> getCandidatesList() {
		return candidatesList;
	}
	public void setCandidatesList(List<SelectOptionVO> candidatesList) {
		this.candidatesList = candidatesList;
	}
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
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public List<SelectOptionVO> getScaleList() {
		return scaleList;
	}
	public void setScaleList(List<SelectOptionVO> scaleList) {
		this.scaleList = scaleList;
	}
	
	public List<SelectOptionVO> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<SelectOptionVO> roleList) {
		this.roleList = roleList;
	}
	public List<SelectOptionVO> getExpRoleList() {
		return expRoleList;
	}
	public String getSummery() {
		return summery;
	}
	public void setSummery(String summery) {
		this.summery = summery;
	}
	public void setExpRoleList(List<SelectOptionVO> expRoleList) {
		this.expRoleList = expRoleList;
	}
	public String getPrtiRoles() {
		return prtiRoles;
	}
	public void setPrtiRoles(String prtiRoles) {
		this.prtiRoles = prtiRoles;
	}
	public String getExpRoles() {
		return expRoles;
	}
	public void setExpRoles(String expRoles) {
		this.expRoles = expRoles;
	}
	public Double getPerc() {
		return perc;
	}
	public void setPerc(Double perc) {
		this.perc = perc;
	}
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	
	
}
