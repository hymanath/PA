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

/**
 * custom_page_type entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name = "custom_page_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CustomPageType implements Serializable{

	private static final long serialVersionUID = -2245617856103821358L;
	
	private Long customPageTypeId;
	private String customPageType;
	private Long orderNo;
	private Set<CustomPage> customPages = new HashSet<CustomPage>(0);
	
	public CustomPageType()
	{}
	
	public CustomPageType(String customPageType,Long orderNo)
	{
		this.customPageType = customPageType;
		this.orderNo = orderNo;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "custom_page_type_id", unique = true, nullable = false)
	public Long getCustomPageTypeId() {
		return customPageTypeId;
	}

	public void setCustomPageTypeId(Long customPageTypeId) {
		this.customPageTypeId = customPageTypeId;
	}

	@Column(name = "custom_page_type", length = 50)
	public String getCustomPageType() {
		return customPageType;
	}

	public void setCustomPageType(String customPageType) {
		this.customPageType = customPageType;
	}

	@Column(name = "order_no")
	public Long getOrderNo() {
		return orderNo;
	}
	
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customPageType")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<CustomPage> getCustomPages() {
		return customPages;
	}

	public void setCustomPages(Set<CustomPage> customPages) {
		this.customPages = customPages;
	}
	
	

}
