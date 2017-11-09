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
@Table(name = "jb_role")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class JbRole extends BaseModel implements java.io.Serializable {

	
	
	private Long jbRoleId;
	private String roleName;
	private Long order;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "jb_role_id", unique = true, nullable = false)
	public Long getJbRoleId() {
		return jbRoleId;
	}
	public void setJbRoleId(Long jbRoleId) {
		this.jbRoleId = jbRoleId;
	}
	@Column(name="role_name")
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@Column(name="order")
	public Long getOrder() {
		return order;
	}
	public void setOrder(Long order) {
		this.order = order;
	}
	
}
