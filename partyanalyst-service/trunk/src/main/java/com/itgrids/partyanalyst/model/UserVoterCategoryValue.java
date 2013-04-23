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
@Table(name="user_voter_category_value")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserVoterCategoryValue extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long userVoterCategoryValueId ;
	private UserVoterCategory userVoterCategory;
	private String categoryValue;
	private User user;
	private Long orderNo;
	
	private Set<VoterCategoryValue> voterCategoryValue = new HashSet<VoterCategoryValue>();
	
	public UserVoterCategoryValue(){
		
	}
	public UserVoterCategoryValue(Long userVoterCategoryValueId,UserVoterCategory userVoterCategory,String categoryValue){
		this.userVoterCategoryValueId = userVoterCategoryValueId;
		this.userVoterCategory = userVoterCategory;
		this.categoryValue = categoryValue;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_voter_category_value_id ", unique = true, nullable = false)
	public Long getUserVoterCategoryValueId() {
		return userVoterCategoryValueId;
	}
	
	public void setUserVoterCategoryValueId(Long userVoterCategoryValueId) {
		this.userVoterCategoryValueId = userVoterCategoryValueId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_voter_category_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserVoterCategory getUserVoterCategory() {
		return userVoterCategory;
	}
	
	public void setUserVoterCategory(UserVoterCategory userVoterCategory) {
		this.userVoterCategory = userVoterCategory;
	}

	
	@Column(name="category_value")
	public String getCategoryValue() {
		return categoryValue;
	}
	public void setCategoryValue(String categoryValue) {
		this.categoryValue = categoryValue;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userVoterCategoryValue")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<VoterCategoryValue> getVoterCategoryValue() {
		return voterCategoryValue;
	}
	public void setVoterCategoryValue(Set<VoterCategoryValue> voterCategoryValue) {
		this.voterCategoryValue = voterCategoryValue;
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
	
	@Column(name = "order_no", length = 3)
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	
	
	
}
