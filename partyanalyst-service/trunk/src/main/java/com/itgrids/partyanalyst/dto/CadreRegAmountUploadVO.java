package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class CadreRegAmountUploadVO implements Serializable{

	private static final long serialVersionUID = 2837961446337439869L;
	
	private String branch;
	private String username;
	private Integer amount;
	
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}
