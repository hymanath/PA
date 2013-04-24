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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="user_voter_category")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserVoterCategory  extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long userVoterCategoryId;
	private String categoryName;
	private String description;
	private User user;
	private Set<UserVoterCategoryValue> userVoterCategoryValue = new HashSet<UserVoterCategoryValue>();
	
	public UserVoterCategory(){
		
	}
	public UserVoterCategory(Long userVoterCategoryId,String categoryName){
		this.userVoterCategoryId = userVoterCategoryId;
		this.categoryName = categoryName;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = " user_voter_category_id", unique = true, nullable = false)
	public Long getUserVoterCategoryId() {
		return userVoterCategoryId;
	}
	
	public void setUserVoterCategoryId(Long userVoterCategoryId) {
		this.userVoterCategoryId = userVoterCategoryId;
	}
	
	@Column(name="category_name")
	public String getCategoryName() {
		return categoryName;
	}
	
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userVoterCategory")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<UserVoterCategoryValue> getUserVoterCategoryValue() {
		return userVoterCategoryValue;
	}
	
	public void setUserVoterCategoryValue(
			Set<UserVoterCategoryValue> userVoterCategoryValue) {
		this.userVoterCategoryValue = userVoterCategoryValue;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	

	
}
