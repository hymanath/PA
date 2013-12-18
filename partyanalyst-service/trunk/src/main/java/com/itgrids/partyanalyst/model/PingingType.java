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
@Table(name = "pinging_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PingingType extends BaseModel implements Serializable{

	
	private Long pingingTypeId;
	private String type;
	private String description;
	
	public PingingType()
	{
		
	}
	public PingingType(Long pingingTypeId,String type,String description)
	{
	this.pingingTypeId = pingingTypeId;
	this.type = type;
	this.description = description;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="pinging_type_id", unique=true, nullable=false)
	public Long getPingingTypeId() {
		return pingingTypeId;
	}
	public void setPingingTypeId(Long pingingTypeId) {
		this.pingingTypeId = pingingTypeId;
	}
	@Column(name = "type", length = 100)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name = "description", length =100)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
