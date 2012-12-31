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
@Table(name="category_values")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CategoryValues extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long categoryValuesId;
	private UserCategoryValues userCategoryValues;
	private String categoryValue;
	private User user;
	
	private Set<VoterCategoryValues> voterCategoryValues=new HashSet<VoterCategoryValues>();
	
	public CategoryValues(){
		
	}
	public CategoryValues(Long categoryValuesId,UserCategoryValues userCategoryValues,String categoryValue){
		this.categoryValuesId=categoryValuesId;
		this.userCategoryValues=userCategoryValues;
		this.categoryValue=categoryValue;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "category_values_id", unique = true, nullable = false)
	public Long getCategoryValuesId() {
		return categoryValuesId;
	}
	public void setCategoryValuesId(Long categoryValuesId) {
		this.categoryValuesId = categoryValuesId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_category_value_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserCategoryValues getUserCategoryValues() {
		return userCategoryValues;
	}
	public void setUserCategoryValues(UserCategoryValues userCategoryValues) {
		this.userCategoryValues = userCategoryValues;
	}
	
	@Column(name="category_value")
	public String getCategoryValue() {
		return categoryValue;
	}
	public void setCategoryValue(String categoryValue) {
		this.categoryValue = categoryValue;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "categoryValues")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<VoterCategoryValues> getVoterCategoryValues() {
		return voterCategoryValues;
	}
	public void setVoterCategoryValues(Set<VoterCategoryValues> voterCategoryValues) {
		this.voterCategoryValues = voterCategoryValues;
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
