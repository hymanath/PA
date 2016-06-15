package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "mobile_brand_model")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MobileBrandModel extends BaseModel implements Serializable{
	
	private Long mobileBrandModelId;
	private String modelNumber;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mobile_brand_model_id", unique = true, nullable = false)
	public Long getMobileBrandModelId() {
		return mobileBrandModelId;
	}
	public void setMobileBrandModelId(Long mobileBrandModelId) {
		this.mobileBrandModelId = mobileBrandModelId;
	}
	@Column(name="model_number")
	public String getModelNumber() {
		return modelNumber;
	}
	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
	
}
