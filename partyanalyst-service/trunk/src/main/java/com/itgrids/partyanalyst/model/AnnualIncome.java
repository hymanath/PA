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
@Table(name = "annual_income")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AnnualIncome extends BaseModel implements Serializable {
	
private Long annualIncomeId;
private String name;
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "annual_income_id", unique = true, nullable = false)
public Long getAnnualIncomeId() {
	return annualIncomeId;
}
public void setAnnualIncomeId(Long annualIncomeId) {
	this.annualIncomeId = annualIncomeId;
}
@Column(name = "name")
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}


}
