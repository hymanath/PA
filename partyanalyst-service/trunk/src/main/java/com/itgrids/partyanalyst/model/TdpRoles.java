package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "tdp_roles")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpRoles {
	private Long tdpRolesId;
	private String role;
	private Long order;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_roles_id", unique = true, nullable = false)
	public Long getTdpRolesId() {
		return tdpRolesId;
	}
	
	public void setTdpRolesId(Long tdpRolesId) {
		this.tdpRolesId = tdpRolesId;
	}
	
	@Column(name = "role")
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	@Column(name = "order")
	public Long getOrder() {
		return order;
	}
	
	public void setOrder(Long order) {
		this.order = order;
	}
	
	
}
