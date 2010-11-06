/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on November 03, 2010
 */
package com.itgrids.partyanalyst.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

/**
 * CadreLanguageEfficiency entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">kamalakar</a>
 */
@Entity
@Table(name = "cadre_children_info")
public class CadreChildrenInfo implements java.io.Serializable  {

	private static final long serialVersionUID = 1L;
	
	private Long cadreChildrenInfoId;
	private Cadre cadre;
	private String name;
	private Date dateOfBirth;

	
	public CadreChildrenInfo() {
		super();
		
	}

	public CadreChildrenInfo(Cadre cadre,
			String name,Date dateOfBirth) {
		super();
		this.cadre = cadre;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cadre_children_info_id", nullable = false, unique = true)
	public Long getCadreChildrenInfoId() {
		return cadreChildrenInfoId;
	}

	public void setCadreChildrenInfoId(Long cadreChildrenInfoId) {
		this.cadreChildrenInfoId = cadreChildrenInfoId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cadre_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Cadre getCadre() {
		return cadre;
	}

	public void setCadre(Cadre cadre) {
		this.cadre = cadre;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "date_of_birth", length = 10)
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
}



