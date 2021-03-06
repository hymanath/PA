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
@Table(name = "appointment_preferable_time")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppointmentPreferableTime extends BaseModel {
	private Long appointmentPreferableTimeId;
	private String preferableTime;
	private String preferabelTimeTxt;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "appointment_preferable_time_id", unique = true, nullable = false)
	public Long getAppointmentPreferableTimeId() {
		return appointmentPreferableTimeId;
	}
	public void setAppointmentPreferableTimeId(Long appointmentPreferableTimeId) {
		this.appointmentPreferableTimeId = appointmentPreferableTimeId;
	}
	
	@Column(name = "preferable_time")
	public String getPreferableTime() {
		return preferableTime;
	}
	public void setPreferableTime(String preferableTime) {
		this.preferableTime = preferableTime;
	}
	
	@Column(name = "preferable_time_txt")
	public String getPreferabelTimeTxt() {
		return preferabelTimeTxt;
	}
	public void setPreferabelTimeTxt(String preferabelTimeTxt) {
		this.preferabelTimeTxt = preferabelTimeTxt;
	}
	
}
