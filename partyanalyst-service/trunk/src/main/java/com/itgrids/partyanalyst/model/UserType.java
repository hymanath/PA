package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "user_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserType extends BaseModel{
	
	private static final long serialVersionUID = 5470642663781504485L;
	
	private Long   userTypeId;
	private String type;
	 
	 public UserType() {}
	 
	 public UserType(Long userTypeId, String type) {
		this.userTypeId = userTypeId;
		this.type = type;
	 }
	 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_type_id", unique = true, nullable = false)
	public Long getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(Long userTypeId) {
		this.userTypeId = userTypeId;
	}
	
	@Column(name = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	 
}
