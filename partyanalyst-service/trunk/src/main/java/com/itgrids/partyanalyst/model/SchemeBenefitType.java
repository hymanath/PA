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
@Table(name = "scheme_benefit_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SchemeBenefitType extends BaseModel implements Serializable {
	
	private Long schemeBenefitTypeId;
	private String benefitType;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "scheme_benefit_type_id", unique = true, nullable = false)
	public Long getSchemeBenefitTypeId() {
		return schemeBenefitTypeId;
	}
	public void setSchemeBenefitTypeId(Long schemeBenefitTypeId) {
		this.schemeBenefitTypeId = schemeBenefitTypeId;
	}
	
	@Column(name="benefit_type")
	public String getBenefitType() {
		return benefitType;
	}
	public void setBenefitType(String benefitType) {
		this.benefitType = benefitType;
	}
	
	

}
