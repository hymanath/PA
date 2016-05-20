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
@Table(name = "blood_component")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BloodComponent extends BaseModel implements Serializable{

	private Long bloodComponentId;
	private String component;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "blood_component_id", unique = true, nullable = false)
	public Long getBloodComponentId() {
		return bloodComponentId;
	}
	public void setBloodComponentId(Long bloodComponentId) {
		this.bloodComponentId = bloodComponentId;
	}
	
	@Column(name="component")
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
}
