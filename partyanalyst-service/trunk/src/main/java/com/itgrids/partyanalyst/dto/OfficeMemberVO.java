package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class OfficeMemberVO implements Serializable{
	
	private static final long serialVersionUID = -761415643641590792L;
	
	private Long officeId;
	private String officeName;
	private Long presentMember;
	public Long getOfficeId() {
		return officeId;
	}
	public void setOfficeId(Long officeId) {
		this.officeId = officeId;
	}
	public String getOfficeName() {
		return officeName;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	public Long getPresentMember() {
		return presentMember;
	}
	public void setPresentMember(Long presentMember) {
		this.presentMember = presentMember;
	}
}
