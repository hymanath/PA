package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="govt_schemes")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GovtSchemes extends BaseModel implements Serializable{

	private Long govtSchemesId;
	private String schemeName;
	private Long schemeBenefitTypeId;
	private String description;
	
	private SchemeBenefitType schemeBenefitType;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "govt_schemes_id", unique = true, nullable = false)
	public Long getGovtSchemesId() {
		return govtSchemesId;
	}

	public void setGovtSchemesId(Long govtSchemesId) {
		this.govtSchemesId = govtSchemesId;
	}

	@Column(name = "scheme_name")
	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

	@Column(name = "scheme_benefit_type_id")
	public Long getSchemeBenefitTypeId() {
		return schemeBenefitTypeId;
	}

	public void setSchemeBenefitTypeId(Long schemeBenefitTypeId) {
		this.schemeBenefitTypeId = schemeBenefitTypeId;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="scheme_benefit_type_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SchemeBenefitType getSchemeBenefitType() {
		return schemeBenefitType;
	}

	public void setSchemeBenefitType(SchemeBenefitType schemeBenefitType) {
		this.schemeBenefitType = schemeBenefitType;
	}
	
	
	
}
