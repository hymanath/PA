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
@Table(name = "referal_designation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ReferalDesignation extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long referalDesignationId;
	private String designation;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "referal_designation_id", unique = true, nullable = false)
	public Long getReferalDesignationId() {
		return referalDesignationId;
	}
	public void setReferalDesignationId(Long referalDesignationId) {
		this.referalDesignationId = referalDesignationId;
	}
	
	@Column(name = "designation")
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
}
