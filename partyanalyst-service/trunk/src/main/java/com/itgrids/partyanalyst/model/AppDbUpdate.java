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
@Table(name="app_db_update")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppDbUpdate {

	private Long appDbUpdateId;
	private String appName;
	private Double dbVersion;
	private String environment;
	private String scriptFile;
	private Long orderId;
	private String isDeleted;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "app_db_update_id", unique = true, nullable = false)
	public Long getAppDbUpdateId() {
		return appDbUpdateId;
	}
	
	public void setAppDbUpdateId(Long appDbUpdateId) {
		this.appDbUpdateId = appDbUpdateId;
	}
	
	@Column(name = "app_name")
	public String getAppName() {
		return appName;
	}
	
	public void setAppName(String appName) {
		this.appName = appName;
	}
	
	@Column(name = "db_version")
	public Double getDbVersion() {
		return dbVersion;
	}
	
	public void setDbVersion(Double dbVersion) {
		this.dbVersion = dbVersion;
	}
	
	@Column(name = "environment")
	public String getEnvironment() {
		return environment;
	}
	
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	
	@Column(name = "script_file")
	public String getScriptFile() {
		return scriptFile;
	}
	
	public void setScriptFile(String scriptFile) {
		this.scriptFile = scriptFile;
	}
	
	@Column(name = "order_id")
	public Long getOrderId() {
		return orderId;
	}
	
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
}
