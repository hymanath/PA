/* 
 * Copyright (c) 2011 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February 07, 2010
 */

package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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
@Table(name = "cadre_role_relation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadreRoleRelation implements Serializable{

	private static final long serialVersionUID = 6143044275129897622L;
	private Long cadreRoleRelationId;
	private Cadre cadre;
	private CadreRole cadreRole;
	
	public CadreRoleRelation(){
		super();
	}
	
	public CadreRoleRelation(Cadre cadre,CadreRole cadreRole){
		this.cadre = cadre;
		this.cadreRole = cadreRole;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cadre_role_relation_id", unique = true, nullable = false)
	public Long getCadreRoleRelationId() {
		return cadreRoleRelationId;
	}

	public void setCadreRoleRelationId(Long cadreRoleRelationId) {
		this.cadreRoleRelationId = cadreRoleRelationId;
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
	@JoinColumn(name="cadre_role_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreRole getCadreRole() {
		return cadreRole;
	}

	public void setCadreRole(CadreRole cadreRole) {
		this.cadreRole = cadreRole;
	}
	
}
