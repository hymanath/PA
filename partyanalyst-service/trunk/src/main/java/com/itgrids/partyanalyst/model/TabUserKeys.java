package com.itgrids.partyanalyst.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "tab_user_keys")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class TabUserKeys {

	private Long tabUserKeysId;
	private String imei;
	private Long userId;
	private Long logInUserId;
	private String keys;
	private Date insertedTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tab_user_keys_id" , unique = true , nullable = false)
	public Long getTabUserKeysId() {
		return tabUserKeysId;
	}
	public void setTabUserKeysId(Long tabUserKeysId) {
		this.tabUserKeysId = tabUserKeysId;
	}
	
	@Column(name = "imei")
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	
	@Column(name = "user_id")
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name = "login_user_id")
	public Long getLogInUserId() {
		return logInUserId;
	}
	public void setLogInUserId(Long logInUserId) {
		this.logInUserId = logInUserId;
	}
	
	@Column(name = "ukeys")
	public String getKeys() {
		return keys;
	}
	public void setKeys(String keys) {
		this.keys = keys;
	}
	
	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	
}
