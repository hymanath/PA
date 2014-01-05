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
@Table(name = "benefit")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Benefit implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6096479897711861362L;
	private Long benefitId;
	private String name;
	
	public Benefit(){}
	
	public Benefit(String name)
	{
	  this.name = name;	
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "benefit_id", unique=true, nullable= false)
	public Long getBenefitId() {
		return benefitId;
	}

	public void setBenefitId(Long benefitId) {
		this.benefitId = benefitId;
	}

	@Column(name = "name", length = 20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
