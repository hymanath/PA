package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "govt_user_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GovtUserType extends BaseModel implements Serializable{
	
	private Long govtUserTypeId;
	private String userType;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "govt_user_type_id", unique = true, nullable = false)
	public Long getGovtUserTypeId() {
		return govtUserTypeId;
	}
	public void setGovtUserTypeId(Long govtUserTypeId) {
		this.govtUserTypeId = govtUserTypeId;
	}
	
	@Column(name = "user_type")
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	

}
