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
@Table(name="user_category_values")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserCategoryValues  extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long userCategoryValuesId;
	private String userCategoryName;
	private String description;
	
	private Set<CategoryValues> categoryValues=new HashSet<CategoryValues>();
	
	public UserCategoryValues(){
		
	}
	public UserCategoryValues(Long userCategoryValuesId,String userCategoryName){
		this.userCategoryValuesId=userCategoryValuesId;
		this.userCategoryName=userCategoryName;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_category_values_id", unique = true, nullable = false)
	public Long getUserCategoryValuesId() {
		return userCategoryValuesId;
	}
	public void setUserCategoryValuesId(Long userCategoryValuesId) {
		this.userCategoryValuesId = userCategoryValuesId;
	}
	
	@Column(name="user_category_name")
	public String getUserCategoryName() {
		return userCategoryName;
	}
	public void setUserCategoryName(String userCategoryName) {
		this.userCategoryName = userCategoryName;
	}
	
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userCategoryValues")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<CategoryValues> getCategoryValues() {
		return categoryValues;
	}
	public void setCategoryValues(Set<CategoryValues> categoryValues) {
		this.categoryValues = categoryValues;
	}

	
}
