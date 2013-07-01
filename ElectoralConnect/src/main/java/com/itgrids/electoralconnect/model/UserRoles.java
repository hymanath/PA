package com.itgrids.electoralconnect.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;


@Entity
@Table(name="user_roles")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserRoles extends BaseModel implements Serializable{
	private Long userRoleId;
	private Roles roles;
	private User user;
	
	
	public UserRoles(User user,Roles role){
		this.roles=role;
		this.user=user;
	}
	public UserRoles(){}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_role_id", unique=true, nullable=false)
	public Long getUserRoleId() {
		return userRoleId;
	}


	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
	}


	@OneToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}


	@OneToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}
