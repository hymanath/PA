/* 
 * Copyright (c) 2009 IT Grids Limited.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on December 23, 2009
 */
package com.itgrids.partyanalyst.model;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "fund_source")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FundSource {
	
	private Long fundSourceID;
	private String fundSource;
	private String description;
	private Set<ProblemFundSource> problemFundSources = new HashSet<ProblemFundSource>(0);
	
	public FundSource()
	{
		
	}
	
	public FundSource(Long fundSourceID) {
		this.fundSourceID = fundSourceID;
	}
	public FundSource(String fundSource, String description,
			Set<ProblemFundSource> problemFundSources) {
		this.fundSource = fundSource;
		this.description = description;
		this.problemFundSources = problemFundSources;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "fund_source_id", unique = true, nullable = false)	
	public Long getFundSourceID() {
		return fundSourceID;
	}
	
	public void setFundSourceID(Long fundSourceID) {
		this.fundSourceID = fundSourceID;
	}
	
	@Column(name = "fund_source", length = 50)
	public String getFundSource() {
		return fundSource;
	}
	
	public void setFundSource(String fundSource) {
		this.fundSource = fundSource;
	}
	
	@Column(name = "description", length = 50)
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fundSource")
	public Set<ProblemFundSource> getProblemFundSource() {
		return problemFundSources;
	}
	
	public void setProblemFundSource(Set<ProblemFundSource> problemFundSources) {
		this.problemFundSources = problemFundSources;
	}
	
}




