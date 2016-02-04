package com.itgrids.partyanalyst.dto;

public class UnionTabUserVO {
	private Long unionTabUserId;
	private String name;
	private String mobileNum;
	private String uname;
	private String pwd;
	private String msg;
	
	
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
