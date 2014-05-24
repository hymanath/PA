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
@Table(name = "source_income")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SourceIncome extends BaseModel implements Serializable {
	private Long sourceIncomeId;
	private String name;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "source_income_id", unique = true, nullable = false)
	public Long getSourceIncomeId() {
		return sourceIncomeId;
	}
	public void setSourceIncomeId(Long sourceIncomeId) {
		this.sourceIncomeId = sourceIncomeId;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
