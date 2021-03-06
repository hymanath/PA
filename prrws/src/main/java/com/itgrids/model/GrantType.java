package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "grant_type")
public class GrantType{
	
	private static final long serialVersionUID = -2853930539938433902L;

	private Long grantTypeId;
	private String type;

	@Id
	@Column(name="grant_type_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getGrantTypeId() {
		return grantTypeId;
	}
	public void setGrantTypeId(Long grantTypeId) {
		this.grantTypeId = grantTypeId;
	}

	@Column(name="grant_type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
