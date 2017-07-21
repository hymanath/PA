package com.itgrids.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "convergence_type")
public class ConvergenceType extends  BaseModel implements Serializable {

	private Long convergenceTypeId;
	private String type;
	
	@Id
	@Column(name="convergence_type_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getConvergenceTypeId() {
		return convergenceTypeId;
	}
	public void setConvergenceTypeId(Long convergenceTypeId) {
		this.convergenceTypeId = convergenceTypeId;
	}
	
	@Column(name="type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
