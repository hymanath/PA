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
@Table(name = "content")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Content implements Serializable{
	private Long contentId ;
	
	private String name;
	private String description;
	private String orderNo;
	private Set<UserPrivacySettings> userPrivacySetting = new HashSet<UserPrivacySettings>(0);
	
	

	//Default Constructor
	public Content()
	{
		
	}
	
	//Full Constructor
	public Content(Long contentId,String name,String description,String orderNo)
	{
	this.contentId = contentId;
	this.name = name;
	this.description = description;
	this.orderNo = orderNo;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "content_id", unique = true, nullable = false)
	public Long getContentId() {
		return contentId;
	}

	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}
	@Column(name="name",length = 20)
	public String getName() {
		return name;
	}
	

	public void setName(String name) {
		this.name = name;
	}
	@Column(name="description",length = 200)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	
	@Column(name="order_no")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "content")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<UserPrivacySettings> getUserPrivacySetting() {
		return userPrivacySetting;
	}

	public void setUserPrivacySetting(Set<UserPrivacySettings> userPrivacySetting) {
		this.userPrivacySetting = userPrivacySetting;
	}

}
