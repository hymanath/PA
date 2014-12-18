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
@Table(name = "cadre_reg_sync_access_users")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadreRegSyncAccessUsers {
   
	private Long cadreRegSyncAccessUsersId;
	private Long userId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cadre_reg_sync_access_users_id", unique = true, nullable = false)
	public Long getCadreRegSyncAccessUsersId() {
		return cadreRegSyncAccessUsersId;
	}
	
	public void setCadreRegSyncAccessUsersId(Long cadreRegSyncAccessUsersId) {
		this.cadreRegSyncAccessUsersId = cadreRegSyncAccessUsersId;
	}
	
	@Column(name = "user_id")
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}
