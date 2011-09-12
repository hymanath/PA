/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on September 06, 2011
 */
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

/**
 * region_scopes_problem_type entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name="region_scopes_problem_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RegionScopesProblemType implements Serializable{
	
	private static final long serialVersionUID = -4944513184973019774L;
	
	private Long regionSCopesProblemTypeId;
	private RegionScopes regionScopes;
	private ProblemType problemType;
	private Long orderNo;
	
	public RegionScopesProblemType()
	{
		//default Constructor
	}
	
	public RegionScopesProblemType(RegionScopes regionScopes,
			ProblemType problemType,Long orderNo)
	{
		this.regionScopes = regionScopes;
		this.problemType = problemType;
		this.orderNo = orderNo;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="region_scopes_problem_type_id", unique=true, nullable=false)
	public Long getRegionSCopesProblemTypeId() {
		return regionSCopesProblemTypeId;
	}

	public void setRegionSCopesProblemTypeId(Long regionSCopesProblemTypeId) {
		this.regionSCopesProblemTypeId = regionSCopesProblemTypeId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="region_scopes_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public RegionScopes getRegionScopes() {
		return regionScopes;
	}

	public void setRegionScopes(RegionScopes regionScopes) {
		this.regionScopes = regionScopes;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="problem_type_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ProblemType getProblemType() {
		return problemType;
	}

	public void setProblemType(ProblemType problemType) {
		this.problemType = problemType;
	}

	@Column(name = "order_no")
	public Long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	
	
	
}
