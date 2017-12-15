package com.itgrids.model;

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

@Entity
@Table(name = "favourite_component")
public class FavouriteComponent {

	private Long favouriteComponentId;
	private String name;
	private String url;
	private Date insertedTime;
	private Date updatedTime;
	private String isDeleted;
	private Long orderNo;
	private Long pageId;
	private Page page;
	
	@Id
	@Column(name = "favourite_component_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getFavouriteComponentId() {
		return favouriteComponentId;
	}
	public void setFavouriteComponentId(Long favouriteComponentId) {
		this.favouriteComponentId = favouriteComponentId;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "url")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@Column(name = "updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name = "order_no")
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	@Column(name = "page_id")
	public Long getPageId() {
		return pageId;
	}
	public void setPageId(Long pageId) {
		this.pageId = pageId;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "page_id", insertable = false, updatable = false)
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
}
