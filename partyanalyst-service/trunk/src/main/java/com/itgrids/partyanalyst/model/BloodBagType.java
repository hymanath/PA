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
@Table(name = "blood_bag_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BloodBagType extends BaseModel implements Serializable{

	private Long bloodBagTypeId;
	private String bagType;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "blood_bag_type_id", unique = true, nullable = false)
	public Long getBloodBagTypeId() {
		return bloodBagTypeId;
	}
	public void setBloodBagTypeId(Long bloodBagTypeId) {
		this.bloodBagTypeId = bloodBagTypeId;
	}
	
	@Column(name="bag_type")
	public String getBagType() {
		return bagType;
	}
	public void setBagType(String bagType) {
		this.bagType = bagType;
	}
}
