package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class LoginResponceVO implements Serializable{
	
	
	private static final long serialVersionUID = 836729585832866312L;
	private Long constituencyId;
	private String constituencyName;
	private String statusMsg;
	private Long userId;
	private List<LoginStatusVO> statusList;
	
	
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public String getStatusMsg() {
		return statusMsg;
	}
	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}
	public List<LoginStatusVO> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<LoginStatusVO> statusList) {
		this.statusList = statusList;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	
	

}
