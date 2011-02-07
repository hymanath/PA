/* 
 * Copyright (c) 2011 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February 07, 2010
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
import org.hibernate.annotations.NotFoundAction;

/**
 * cadre_role entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */


@Entity
@Table(name = "cadre_role")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadreRole implements java.io.Serializable{

	private static final long serialVersionUID = 4977445778977346938L;
	private Long cadreRoleId;
	private String role;
	private Set<CadreRoleRelation> cadreRoleRelation =  new HashSet<CadreRoleRelation>();
	
	public CadreRole(){
		super();
	}
	
	public CadreRole(String role){
		this.role = role;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cadre_role_id", unique = true, nullable = false)
	public Long getCadreRoleId() {
		return cadreRoleId;
	}

	public void setCadreRoleId(Long cadreRoleId) {
		this.cadreRoleId = cadreRoleId;
	}

	@Column(name = "role", length = 50)
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cadreRole")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<CadreRoleRelation> getCadreRoleRelation() {
		return cadreRoleRelation;
	}

	public void setCadreRoleRelation(Set<CadreRoleRelation> cadreRoleRelation) {
		this.cadreRoleRelation = cadreRoleRelation;
	}

}
