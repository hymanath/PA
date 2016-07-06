package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DepartmentVO implements Serializable {
	private static final long serialVersionUID = -5396409245120560090L;
	
	private Long departmentId;
	private String departmentName;
	private List<OfficeMemberVO> officeMemberList;
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public List<OfficeMemberVO> getOfficeMemberList() {
		if(officeMemberList==null){
			officeMemberList = new ArrayList<OfficeMemberVO>();
		}
		return officeMemberList;
	}
}
