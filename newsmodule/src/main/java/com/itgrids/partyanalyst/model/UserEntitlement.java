package com.itgrids.partyanalyst.model;

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
@Table(name= "user_entitlement")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserEntitlement {

	private Long userEntitlementId;
	private Entitlement entitlement;
	private User user;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_entitlement_id", unique = true, nullable = false)
	public Long getUserEntitlementId() {
		return userEntitlementId;
	}
	
	public void setUserEntitlementId(Long userEntitlementId) {
		this.userEntitlementId = userEntitlementId;
	}
	
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "entitlement_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Entitlement getEntitlement() {
		return entitlement;
	}
	
	public void setEntitlement(Entitlement entitlement) {
		this.entitlement = entitlement;
	}
	
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
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
