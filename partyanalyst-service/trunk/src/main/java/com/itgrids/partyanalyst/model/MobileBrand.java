package com.itgrids.partyanalyst.model;

import java.io.Serializable;
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
@Table(name="mobile_brand")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MobileBrand extends BaseModel implements Serializable{
	
	private Long mobileBrandId;
	private String brandName;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="mobile_brand_id", unique=true, nullable=false)
	public Long getMobileBrandId() {
		return mobileBrandId;
	}
	public void setMobileBrandId(Long mobileBrandId) {
		this.mobileBrandId = mobileBrandId;
	}
	@Column(name="brand_name")
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	
}
