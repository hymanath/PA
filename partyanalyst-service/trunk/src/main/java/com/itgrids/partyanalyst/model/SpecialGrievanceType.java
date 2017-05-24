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
@Table(name = "special_grievance_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SpecialGrievanceType extends BaseModel implements Serializable{

	private Long specialGrievanceTypeId;
	private String type;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "special_grievance_type_id", unique = true, nullable = false)
	public Long getSpecialGrievanceTypeId() {
		return specialGrievanceTypeId;
	}
	public void setSpecialGrievanceTypeId(Long specialGrievanceTypeId) {
		this.specialGrievanceTypeId = specialGrievanceTypeId;
	}
	
	@Column(name = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
