package com.itgrids.partyanalyst.dto;

import java.util.List;

public class IdAndNameVO {
	private Long id;
	private String name;
	private Long inviteeCount = 0l;
	private Long attenteeCount = 0l;
	private Long inviteeAttendeeCnt = 0l;
	private String imagePathStr;
	private List<IdAndNameVO> issueTypes ;
	
	
	
	public List<IdAndNameVO> getIssueTypes() {
		return issueTypes;
	}
	public void setIssueTypes(List<IdAndNameVO> issueTypes) {
		this.issueTypes = issueTypes;
	}
	
	
	public String getImagePathStr() {
		return imagePathStr;
	}
	public void setImagePathStr(String imagePathStr) {
		this.imagePathStr = imagePathStr;
	}
	public Long getInviteeCount() {
		return inviteeCount;
	}
	public void setInviteeCount(Long inviteeCount) {
		this.inviteeCount = inviteeCount;
	}
	public Long getAttenteeCount() {
		return attenteeCount;
	}
	public void setAttenteeCount(Long attenteeCount) {
		this.attenteeCount = attenteeCount;
	}
	public Long getInviteeAttendeeCnt() {
		return inviteeAttendeeCnt;
	}
	public void setInviteeAttendeeCnt(Long inviteeAttendeeCnt) {
		this.inviteeAttendeeCnt = inviteeAttendeeCnt;
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
	
	

}
