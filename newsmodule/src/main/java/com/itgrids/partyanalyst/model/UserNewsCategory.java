package com.itgrids.partyanalyst.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "user_news_category")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserNewsCategory extends BaseModel{
	
	private Long userNewsCategoryId;
	private Category category;
	private User user;
	private Date createdDate;
	private Date updatedDate;
	private String isDelete;
	private String isPrivate;
	
	public UserNewsCategory(){}
	
	public UserNewsCategory(Category category,User user,String isDelete,String isPrivate,
			Date createdDate,Date updatedDate)
	{
		this.category = category;
		this.user = user;
		this.isDelete = isDelete;
		this.isPrivate = isPrivate;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "user_news_category_id", unique = true, nullable = false)
	public Long getUserNewsCategoryId() {
		return userNewsCategoryId;
	}
	public void setUserNewsCategoryId(Long userNewsCategoryId) {
		this.userNewsCategoryId = userNewsCategoryId;
	}
	
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Column(name = "created_date")
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	@Column(name = "updated_date")
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	@Column(name = "is_delete")
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	
	@Column(name = "is_private")
	public String getIsPrivate() {
		return isPrivate;
	}
	public void setIsPrivate(String isPrivate) {
		this.isPrivate = isPrivate;
	}

}
