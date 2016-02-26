package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class UnionTabUserVO {
	
	private Long unionTabUserId;
	private Long id;
	private String name;
	private String mobileNum;
	private String uname;
	private String pwd;
	private String msg;
	
	private Long memberTypeId;
	private String memberTypeName;
	private List<UnionTabUserVO> memberTypesList = new ArrayList<UnionTabUserVO>(0);
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMemberTypeId() {
		return memberTypeId;
	}
	public void setMemberTypeId(Long memberTypeId) {
		this.memberTypeId = memberTypeId;
	}
	public String getMemberTypeName() {
		return memberTypeName;
	}
	public void setMemberTypeName(String memberTypeName) {
		this.memberTypeName = memberTypeName;
	}
	public List<UnionTabUserVO> getMemberTypesList() {
		return memberTypesList;
	}
	public void setMemberTypesList(List<UnionTabUserVO> memberTypesList) {
		this.memberTypesList = memberTypesList;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getUnionTabUserId() {
		return unionTabUserId;
	}
	public void setUnionTabUserId(Long unionTabUserId) {
		this.unionTabUserId = unionTabUserId;
	}
	public String getMobileNum() {
		return mobileNum;
	}
	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
