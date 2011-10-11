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
 * content_type entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name = "content_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ContentType implements Serializable{

	private static final long serialVersionUID = 5018928282136852150L;
	private Long contentTypeId;
	private String contentType;
	private Integer orderNo;
	private Set<Gallary> gallaries = new HashSet<Gallary>(0);
	
	public ContentType()
	{}
	
	public ContentType(String contentType,Integer orderNo)
	{
		this.contentType = contentType;
		this.orderNo = orderNo;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "content_type_id", unique = true, nullable = false)
	public Long getContentTypeId() {
		return contentTypeId;
	}

	public void setContentTypeId(Long contentTypeId) {
		this.contentTypeId = contentTypeId;
	}

	@Column(name = "content_type", length = 50)
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@Column(name = "order_no")
	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "contentType")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<Gallary> getGallaries() {
		return gallaries;
	}

	public void setGallaries(Set<Gallary> gallaries) {
		this.gallaries = gallaries;
	}
	
}
