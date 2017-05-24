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
@Table(name = "janmabhoomi_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class JanmabhoomiType extends BaseModel implements Serializable{

	private Long janmabhoomiTypeId;
	private String type;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "janmabhoomi_type_id", unique = true, nullable = false)
	public Long getJanmabhoomiTypeId() {
		return janmabhoomiTypeId;
	}
	public void setJanmabhoomiTypeId(Long janmabhoomiTypeId) {
		this.janmabhoomiTypeId = janmabhoomiTypeId;
	}
	
	@Column(name = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
