package com.itgrids.electoralconnect.model;

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
@Table(name="roles")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Roles extends BaseModel implements Serializable{
	private Long roleId;
	private String role;
	
	
	public Roles(Long roleId,String role){
		this.roleId=roleId;
		this.role=role;
	}
	public Roles(){}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="role_id", unique=true, nullable=false)

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@Column(name = "role", length = 70)
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
}
