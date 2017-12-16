package com.itgrids.model;

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
@Table(name = "page_component")
public class PageComponent {

	private Long pageComponentId;
	private String component;
	private Long pageId;
	private Long orderNo;
	private String isDeleted;
	private Page page;
	private String url;
	
	
	@Id
	@Column(name = "page_component_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getPageComponentId() {
		return pageComponentId;
	}
	public void setPageComponentId(Long pageComponentId) {
		this.pageComponentId = pageComponentId;
	}
	
	@Column(name = "component")
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	
	@Column(name = "page_id")
	public Long getPageId() {
		return pageId;
	}
	public void setPageId(Long pageId) {
		this.pageId = pageId;
	}
	
	@Column(name = "order_no")
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "page_id", insertable = false, updatable = false)
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	
	@Column(name = "url")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
