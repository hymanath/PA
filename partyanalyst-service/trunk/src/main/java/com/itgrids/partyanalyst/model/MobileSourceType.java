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

/*@Entity
@Table(name = "mobile_source_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)*/
public class MobileSourceType implements Serializable{

	
	/*private static final long serialVersionUID = -4306817062121192096L;
	private Long mobileSourceTypeId;
	private String description;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mobile_source_type_id", unique = true, nullable = false)
	public Long getMobileSourceTypeId() {
		return mobileSourceTypeId;
	}
	public void setMobileSourceTypeId(Long mobileSourceTypeId) {
		this.mobileSourceTypeId = mobileSourceTypeId;
	}
	
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}*/
	
	
}
