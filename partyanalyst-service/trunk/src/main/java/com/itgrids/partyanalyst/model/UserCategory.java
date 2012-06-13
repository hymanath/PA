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
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "user_category")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserCategory extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long userCatagoryId;
	private String userCatagory;
	private Set<User> users = new HashSet<User>(0);
	
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userCategory")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}	 
}
