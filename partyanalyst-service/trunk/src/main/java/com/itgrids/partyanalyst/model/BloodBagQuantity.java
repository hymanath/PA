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
@Table(name = "blood_bag_quantity")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BloodBagQuantity extends BaseModel implements Serializable{

	private Long bloodBagQuantityId;
	private String type;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "blood_bag_quantity_id", unique = true, nullable = false)
	public Long getBloodBagQuantityId() {
		return bloodBagQuantityId;
	}
	public void setBloodBagQuantityId(Long bloodBagQuantityId) {
		this.bloodBagQuantityId = bloodBagQuantityId;
	}
	
	@Column(name="type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
