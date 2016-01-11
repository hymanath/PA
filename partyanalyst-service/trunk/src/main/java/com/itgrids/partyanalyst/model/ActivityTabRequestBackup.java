package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "activity_tab_request_backup")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActivityTabRequestBackup extends BaseModel implements Serializable{
	
	private Long activityTabRequestBackupId;
	private Long userId;
	private String imeiNo;
	private String uniqueCode;
	private Date insertedTime;
	private String jsonArr;
	private Long apkPrimaryKey;
	
	private ActivityTabUser activityTabUser;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "activity_tab_request_backup_id", unique = true, nullable = false)
	public Long getActivityTabRequestBackupId() {
		return activityTabRequestBackupId;
	}
	public void setActivityTabRequestBackupId(Long activityTabRequestBackupId) {
		this.activityTabRequestBackupId = activityTabRequestBackupId;
	}
	
	@Column(name="user_id")
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name="imei_no")
	public String getImeiNo() {
		return imeiNo;
	}
	public void setImeiNo(String imeiNo) {
		this.imeiNo = imeiNo;
	}
	
	@Column(name="unique_code")
	public String getUniqueCode() {
		return uniqueCode;
	}
	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
	}
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name="json_arr")
	public String getJsonArr() {
		return jsonArr;
	}
	public void setJsonArr(String jsonArr) {
		this.jsonArr = jsonArr;
	}
	
	@Column(name="apk_primaryKey")
	public Long getApkPrimaryKey() {
		return apkPrimaryKey;
	}
	public void setApkPrimaryKey(Long apkPrimaryKey) {
		this.apkPrimaryKey = apkPrimaryKey;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ActivityTabUser getActivityTabUser() {
		return activityTabUser;
	}
	public void setActivityTabUser(ActivityTabUser activityTabUser) {
		this.activityTabUser = activityTabUser;
	}
}
