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
@Table(name="attendance")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Attendance extends BaseModel implements Serializable{

	private static final long serialVersionUID = -1488161375914198130L;

	private Long attendanceId;
	private TdpCadre tdpCadre;
	private PublicRepresentative publicRepresentative;
	private Date attendedTime;
	private String rfid;
	private String imei;
	private String uniqueKey;
	private Date insertedTime;
	private String latitude;
	private String longitude;
	private AttendanceTabUser tabUser;
	private AttendanceTabUser currentTabUser;
	private String syncSource;
	private User insertedBy;
	private Long tdpCadreId;
	private Long publicRepresentativeId;
	private Long tabUserId;
	private Long currentTabUserId;
	private Long insertedById;
	private Long tabPrimaryKey;
	
	public Attendance(){}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="attendance_id", unique=true, nullable=false)
	public Long getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(Long attendanceId) {
		this.attendanceId = attendanceId;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="tdp_cadre_id", unique=true, nullable=false)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}

	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="public_representative_id", unique=true, nullable=false)
	public PublicRepresentative getPublicRepresentative() {
		return publicRepresentative;
	}

	public void setPublicRepresentative(PublicRepresentative publicRepresentative) {
		this.publicRepresentative = publicRepresentative;
	}

	@Column(name="attended_time")
	public Date getAttendedTime() {
		return attendedTime;
	}

	public void setAttendedTime(Date attendedTime) {
		this.attendedTime = attendedTime;
	}

	@Column(name="rfid")
	public String getRfid() {
		return rfid;
	}

	public void setRfid(String rfid) {
		this.rfid = rfid;
	}

	@Column(name="imei")
	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	@Column(name="unique_key")
	public String getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}

	@Column(name="latitude")
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@Column(name="longitude")
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="tab_user_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AttendanceTabUser getTabUser() {
		return tabUser;
	}

	public void setTabUser(AttendanceTabUser tabUser) {
		this.tabUser = tabUser;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="current_tab_user_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AttendanceTabUser getCurrentTabUser() {
		return currentTabUser;
	}

	public void setCurrentTabUser(AttendanceTabUser currentTabUser) {
		this.currentTabUser = currentTabUser;
	}

	@Column(name="sync_source")
	public String getSyncSource() {
		return syncSource;
	}

	public void setSyncSource(String syncSource) {
		this.syncSource = syncSource;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="inserted_by",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getInsertedBy() {
		return insertedBy;
	}

	public void setInsertedBy(User insertedBy) {
		this.insertedBy = insertedBy;
	}

	@Column(name="tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}

	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}

	@Column(name="public_representative_id")
	public Long getPublicRepresentativeId() {
		return publicRepresentativeId;
	}

	public void setPublicRepresentativeId(Long publicRepresentativeId) {
		this.publicRepresentativeId = publicRepresentativeId;
	}

	@Column(name="tab_user_id")
	public Long getTabUserId() {
		return tabUserId;
	}

	public void setTabUserId(Long tabUserId) {
		this.tabUserId = tabUserId;
	}

	@Column(name="current_tab_user_id")
	public Long getCurrentTabUserId() {
		return currentTabUserId;
	}

	public void setCurrentTabUserId(Long currentTabUserId) {
		this.currentTabUserId = currentTabUserId;
	}

	@Column(name="inserted_by")
	public Long getInsertedById() {
		return insertedById;
	}

	public void setInsertedById(Long insertedById) {
		this.insertedById = insertedById;
	}

	@Column(name="tab_primary_key")
	public Long getTabPrimaryKey() {
		return tabPrimaryKey;
	}

	public void setTabPrimaryKey(Long tabPrimaryKey) {
		this.tabPrimaryKey = tabPrimaryKey;
	}
	
}
