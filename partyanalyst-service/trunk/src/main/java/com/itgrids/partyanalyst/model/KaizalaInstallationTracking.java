package com.itgrids.partyanalyst.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "kaizala_installation_tracking")
public class KaizalaInstallationTracking {

	private Long kaizalaInstallationTrackingId;
	private String responderName;
	private String responderMobileNo;
	private String installedMobileNo;
	private String name;
	private String url;
	private String location;
	private String objectId;
	private String objectType;
	private String eventId;
	private String eventType;
	private Date insertedTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "kaizala_installation_tracking_id", unique = true, nullable = false)
	public Long getKaizalaInstallationTrackingId() {
		return kaizalaInstallationTrackingId;
	}
	public void setKaizalaInstallationTrackingId(Long kaizalaInstallationTrackingId) {
		this.kaizalaInstallationTrackingId = kaizalaInstallationTrackingId;
	}
	@Column(name="responder_name")
	public String getResponderName() {
		return responderName;
	}
	public void setResponderName(String responderName) {
		this.responderName = responderName;
	}
	@Column(name="responder_mobileNo")
	public String getResponderMobileNo() {
		return responderMobileNo;
	}
	public void setResponderMobileNo(String responderMobileNo) {
		this.responderMobileNo = responderMobileNo;
	}
	@Column(name="installed_mobileNo")
	public String getInstalledMobileNo() {
		return installedMobileNo;
	}
	public void setInstalledMobileNo(String installedMobileNo) {
		this.installedMobileNo = installedMobileNo;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="url")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Column(name="location")
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Column(name="object_id")
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	@Column(name="object_type")
	public String getObjectType() {
		return objectType;
	}
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	@Column(name="event_id")
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	@Column(name="event_type")
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	
	@Column(name="inserted_time")	
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
}
