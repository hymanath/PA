package com.itgrids.partyanalyst.model;

import java.util.Date;
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
import com.itgrids.partyanalyst.model.BaseModel;


@Entity
@Table(name="category")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Category extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1902840178686322806L;
	private Long categoryId;
	private String categoryType;
	private Long orderNo;
	private Set<File> files = new HashSet<File>(0);
	private Set<Gallary> gallaries = new HashSet<Gallary>(0);
	private Long userId;
	private String isDelete;
	private String isPrivate;
	private Date createdDate;
	private Date updateddate;
	private MainCategory maincategory;
	

	public Category(){
		
	}
	public Category(String categoryType,Long orderNo,String isDelete,String isPrivate,Date createdDate,Date updateddate,MainCategory maincategory){
		this.categoryType=categoryType;
		this.orderNo=orderNo;
		this.createdDate = createdDate;
		this.updateddate = updateddate;
		this.isPrivate = isPrivate;
		this.isDelete = isDelete;
		this.maincategory=maincategory;
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "category_id",length=15, unique = true, nullable = false)
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	@Column(name="category_type",length=50)
	public String getCategoryType() {
		return categoryType;
	}
	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}
	@Column(name="order_no")
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "category")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<File> getFiles() {
		return files;
	}
	public void setFiles(Set<File> files) {
		this.files = files;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "category")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<Gallary> getGallaries() {
		return gallaries;
	}
	public void setGallaries(Set<Gallary> gallaries) {
		this.gallaries = gallaries;
	}
	
	@Column(name = "user_id",length = 15)
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name = "is_delete",length = 10)
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	
	@Column(name = "is_private",length = 10)
	public String getIsPrivate() {
		return isPrivate;
	}
	public void setIsPrivate(String isPrivate) {
		this.isPrivate = isPrivate;
	}
	
	@Column(name = "created_date",length = 10)
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@Column(name = "updated_date",length = 10)
	public Date getUpdateddate() {
		return updateddate;
	}
	public void setUpdateddate(Date updateddate) {
		this.updateddate = updateddate;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "main_category_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public MainCategory getMaincategory() {
		return maincategory;
	}
	public void setMaincategory(MainCategory maincategory) {
		this.maincategory = maincategory;
	}
}
