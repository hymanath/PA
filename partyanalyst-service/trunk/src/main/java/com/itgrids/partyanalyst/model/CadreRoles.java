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
@Table(name = "cadre_roles")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadreRoles implements Serializable
{

	private Long 		cadreRolesId;
	private String 		role;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "cadre_roles_id", unique = true, nullable = false)
	public Long getCadreRolesId() {
		return cadreRolesId;
	}
	public void setCadreRolesId(Long cadreRolesId) {
		this.cadreRolesId = cadreRolesId;
	}
	
	@Column(name="role")
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	
}
