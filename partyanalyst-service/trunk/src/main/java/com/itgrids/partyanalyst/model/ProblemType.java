/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on September 06, 2011
 */
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

/**
 * problem_type entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name="problem_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProblemType implements Serializable{

	private static final long serialVersionUID = -8823951304231897782L;
	
	private Long problemTypeId;
	private String problemType;
	private String description;
	
	public ProblemType()
	{
		// default constructor
	}
	public ProblemType(String problemType,String description)
	{
		this.problemType = problemType;
		this.description = description;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="problem_type_id", unique=true, nullable=false)
	public Long getProblemTypeId() {
		return problemTypeId;
	}
	public void setProblemTypeId(Long problemTypeId) {
		this.problemTypeId = problemTypeId;
	}
	
	@Column(name="problem_type",length=50)
	public String getProblemType() {
		return problemType;
	}
	public void setProblemType(String problemType) {
		this.problemType = problemType;
	}
	
	@Column(name="description",length=200)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
