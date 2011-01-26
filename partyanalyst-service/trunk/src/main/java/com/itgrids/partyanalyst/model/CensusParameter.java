/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on January 26, 2011
 */

package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * census_parameter entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name="census_parameter")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CensusParameter extends BaseModel implements java.io.Serializable{

	private static final long serialVersionUID = 9158365603757397192L;
	
	private Long censusParameterId;
	private String parameterName;

	/* default constructor */  
	public CensusParameter(){
	}
	
	 /* full constructor */
	
	public CensusParameter(String parameterName){
		this.parameterName = parameterName;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="census_parameter_id", unique=true, nullable=false)
	public Long getCensusParameterId() {
		return censusParameterId;
	}

	public void setCensusParameterId(Long censusParameterId) {
		this.censusParameterId = censusParameterId;
	}

	@Column(name="parameter_name",length=20)
	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
}
