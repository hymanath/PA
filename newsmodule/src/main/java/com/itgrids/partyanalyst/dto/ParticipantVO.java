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
	private List<String> roleList;
	private List<String> expRoleList;
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
	public List<String> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<String> roleList) {
		this.roleList = roleList;
	}
	public List<String> getExpRoleList() {
		return expRoleList;
	}
	public void setExpRoleList(List<String> expRoleList) {
		this.expRoleList = expRoleList;
	}
	
	
}
