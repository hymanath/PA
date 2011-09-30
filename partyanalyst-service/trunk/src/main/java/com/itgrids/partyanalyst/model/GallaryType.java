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
 * gallary_type entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name="gallary_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GallaryType implements Serializable{

	private static final long serialVersionUID = -7752294535276081967L;
	
	private Long gallaryTypeId;
	private String type;
	private Integer orderNo;
		
	/* default Constructor */
	public GallaryType(){
	}
	
	/* full constuctor */
	
	public GallaryType(String type,Integer orderNo)
	{
		this.type = type;
		this.orderNo = orderNo;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="gallary_type_id", unique=true, nullable=false)
	public Long getGallaryTypeId() {
		return gallaryTypeId;
	}
	public void setGallaryTypeId(Long gallaryTypeId) {
		this.gallaryTypeId = gallaryTypeId;
	}
	
	@Column(name="type",length=50)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name="order_no")
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	
}
