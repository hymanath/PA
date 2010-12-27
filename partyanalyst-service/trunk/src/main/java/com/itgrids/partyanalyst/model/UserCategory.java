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
@Table(name = "user_category")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserCategory extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long userCatagoryId;
	private String userCatagory;
	
	public UserCategory(){
		
	}
	  
	public UserCategory(Long userCatagoryId) {
		this.userCatagoryId = userCatagoryId;
	}
	
	public UserCategory(String userCatagory ) {
		this.userCatagory = userCatagory;		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_category_id", unique = true, nullable = false)
	public Long getUserCatagoryId() {
		return userCatagoryId;
	}
	
	public void setUserCatagoryId(Long userCatagoryId) {
		this.userCatagoryId = userCatagoryId;
	}
	
	@Column(name = "user_category", length = 50)
	public String getUserCatagory() {
		return userCatagory;
	}
	
	public void setUserCatagory(String userCatagory) {
		this.userCatagory = userCatagory;
	}	 
}
