package com.itgrids.partyanalyst.model;

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
@Table(name = "cadre_ghmc_drive_users")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadreGhmcDriveUsers {
	
	private Long cadreGhmcDriveUsersId;
	private Long userId;	
	private User user;
	
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "cadre_ghmc_drive_users_id", unique = true, nullable = false)
	public Long getCadreGhmcDriveUsersId() {
		return cadreGhmcDriveUsersId;
	}
	public void setCadreGhmcDriveUsersId(Long cadreGhmcDriveUsersId) {
		this.cadreGhmcDriveUsersId = cadreGhmcDriveUsersId;
	}
	
	@Column(name="user_id")
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "user_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
