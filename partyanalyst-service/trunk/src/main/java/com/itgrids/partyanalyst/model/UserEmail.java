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
@Table(name = "user_email")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)  
public class UserEmail extends BaseModel {
	private static final long serialVersionUID = -5845484072442581228L;
	
	private Long userEmailId;
	private String name;
	private String email;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_email_id", unique = true, nullable = false)
	public Long getUserEmailId() {
		return userEmailId;
	}
	public void setUserEmailId(Long userEmailId) {
		this.userEmailId = userEmailId;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "email")
	public String getEmail() {
		return email; 
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
