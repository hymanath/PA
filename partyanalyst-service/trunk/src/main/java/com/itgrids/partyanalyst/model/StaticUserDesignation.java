/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 25, 2010
 */
package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

/**
 * @author Sai Krishna
 *
 */
@Entity
@Table(name = "static_user_designation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StaticUserDesignation extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long staticUserDesignationId;
	private String designationType;
	private String description;
	private Date updatedDate;
	private StaticLocalGroup staticLocalGroup;
	
	//Default Constructor
	public StaticUserDesignation(){
		
	}
	
	//parameterized constructor
	public StaticUserDesignation(String designationType, String description,
			Date updatedDate, StaticLocalGroup staticLocalGroup) {
		super();
		this.designationType = designationType;
		this.description = description;
		this.updatedDate = updatedDate;
		this.staticLocalGroup = staticLocalGroup;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "static_user_designation_id", unique = true, nullable = false)
	public Long getStaticUserDesignationId() {
		return staticUserDesignationId;
	}
	public void setStaticUserDesignationId(Long staticUserDesignationId) {
		this.staticUserDesignationId = staticUserDesignationId;
	}
	
	@Column(name = "designation_type", length = 50)
	public String getDesignationType() {
		return designationType;
	}
	public void setDesignationType(String designationType) {
		this.designationType = designationType;
	}
	
	@Column(name = "description", length = 50)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "updated_date")
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="static_local_group_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public StaticLocalGroup getStaticLocalGroup() {
		return staticLocalGroup;
	}
	public void setStaticLocalGroup(StaticLocalGroup staticLocalGroup) {
		this.staticLocalGroup = staticLocalGroup;
	}
	

}
