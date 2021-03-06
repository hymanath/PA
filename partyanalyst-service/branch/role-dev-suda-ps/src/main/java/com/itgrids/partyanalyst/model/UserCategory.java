package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user_category")
public class UserCategory extends BaseModel implements Serializable{

	
	private Long userCatagoryId;
	private String userCatagory;
	private Set<ProblemSourceScope> problemSourceScopes = new HashSet<ProblemSourceScope>(0);
	 
	public UserCategory(){
		
	}
	  
	public UserCategory(Long userCatagoryId) {
		this.userCatagoryId = userCatagoryId;
	}
	
	
	public UserCategory(String userCatagory, Set<ProblemSourceScope> problemSourceScopes) {
		this.userCatagory = userCatagory;
		this.problemSourceScopes = problemSourceScopes;
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
	 
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userCategory")
	public Set<ProblemSourceScope> getProblemSourceScopes() {
		return problemSourceScopes;
	}
	
	public void setProblemSourceScopes(Set<ProblemSourceScope> problemSourceScopes) {
		this.problemSourceScopes = problemSourceScopes;
	}

	 
}
