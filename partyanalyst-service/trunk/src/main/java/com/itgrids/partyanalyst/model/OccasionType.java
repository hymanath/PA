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
@Table(name = "occasion_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OccasionType extends BaseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long occasionTypeId;
	private String occasionType;
	private String description;
	private String isactive;
	
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "occasion_type_id", unique = true, nullable = false)
	public Long getOccasionTypeId() {
		return occasionTypeId;
	}
	public void setOccasionTypeId(Long occasionTypeId) {
		this.occasionTypeId = occasionTypeId;
	}
	
	@Column(name="type")
	public String getOccasionType() {
		return occasionType;
	}
	public void setOccasionType(String occasionType) {
		this.occasionType = occasionType;
	}
	
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="is_active")
	public String getIsactive() {
		return isactive;
	}
	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

}
