/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on November 09, 2010
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
 * CadreFamilyMemberInfo entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">kamalakar</a>
 */
@Entity
@Table(name = "cadre_family_member_info")

public class CadreFamilyMemberInfo implements java.io.Serializable  {
	
	private static final long serialVersionUID = 1L;
	private Long cadreFamilyMemberInfoId;
	private Cadre cadre;
	private UserRelation userRelation;
	private String name;
	private Date dateOfBirth;
	
	public CadreFamilyMemberInfo() {
		super();
	}
	
	public CadreFamilyMemberInfo(Cadre cadre,
			UserRelation userRelation,String name,Date dateOfBirth) {
		super();
		this.cadre = cadre;
		this.userRelation = userRelation;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cadre_family_member_info_id", nullable = false, unique = true)
	public Long getCadreFamilyMemberInfoId() {
		return cadreFamilyMemberInfoId;
	}

	public void setCadreFamilyMemberInfoId(Long cadreFamilyMemberInfoId) {
		this.cadreFamilyMemberInfoId = cadreFamilyMemberInfoId;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_relation_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserRelation getUserRelation() {
		return userRelation;
	}

	public void setUserRelation(UserRelation userRelation) {
		this.userRelation = userRelation;
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
