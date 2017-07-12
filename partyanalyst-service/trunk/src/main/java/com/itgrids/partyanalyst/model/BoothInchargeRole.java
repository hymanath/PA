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
@Table(name = "booth_incharge_role")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BoothInchargeRole {

	private Long boothInchargeRoleId;
	private String roleName;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "booth_incharge_role_id", unique = true, nullable = false)
	public Long getBoothInchargeRoleId() {
		return boothInchargeRoleId;
	}
	public void setBoothInchargeRoleId(Long boothInchargeRoleId) {
		this.boothInchargeRoleId = boothInchargeRoleId;
	}
	
	@Column(name="role_name")
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
}
