package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
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
@Table(name = "cadre_reg_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadreRegUser extends BaseModel implements Serializable {
	
   private Long cadreRegUserId;
   private Long userId;
   private String userType;
   
   private User user;
   
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 @Column(name = "cadre_reg_user_id", unique = true, nullable = false)
public Long getCadreRegUserId() {
	return cadreRegUserId;
}
public void setCadreRegUserId(Long cadreRegUserId) {
	this.cadreRegUserId = cadreRegUserId;
}
@Column(name="user_id")
public Long getUserId() {
	return userId;
}
public void setUserId(Long userId) {
	this.userId = userId;
}
@Column(name="user_type")
public String getUserType() {
	return userType;
}
public void setUserType(String userType) {
	this.userType = userType;
}
@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
@JoinColumn(name="user_id", insertable=false, updatable = false)
@LazyToOne(LazyToOneOption.NO_PROXY)
@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
 
}
