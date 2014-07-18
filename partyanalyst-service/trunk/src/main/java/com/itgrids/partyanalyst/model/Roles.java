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
@Table(name = "roles")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Roles {
private Long roleId;
private String role;
private Long orderNo;
public Roles()
{
	
}
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="committee_id", unique=true, nullable=false)


public Long getRoleId() {
	return roleId;
}
public void setRoleId(Long roleId) {
	this.roleId = roleId;
}
@Column(name="role")
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
@Column(name="order_no")
public Long getOrderNo() {
	return orderNo;
}
public void setOrderNo(Long orderNo) {
	this.orderNo = orderNo;
}


}
