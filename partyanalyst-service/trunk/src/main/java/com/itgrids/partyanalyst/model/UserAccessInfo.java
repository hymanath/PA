package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "user_access_info")
public class UserAccessInfo extends BaseModel{

	private Long userAccessInfoId;
	private String accessType;//access_value
	private Long accessValue;//access_type
	
	public UserAccessInfo(){
		
	}
	
	public UserAccessInfo(Long userAccessInfoId, String accessType,
			Long accessValue) {
		this.userAccessInfoId = userAccessInfoId;
		this.accessType = accessType;
		this.accessValue = accessValue;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_access_info_id", unique = true, nullable = false)
	public Long getUserAccessInfoId() {
		return userAccessInfoId;
	}
	
	public void setUserAccessInfoId(Long userAccessInfoId) {
		this.userAccessInfoId = userAccessInfoId;
	}
	
	@Column(name = "access_type", length = 25)
	public String getAccessType() {
		return accessType;
	}
	
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}
	
	@Column(name = "access_value", length = 25)
	public Long getAccessValue() {
		return accessValue;
	}
	
	public void setAccessValue(Long accessValue) {
		this.accessValue = accessValue;
	}
	
}
