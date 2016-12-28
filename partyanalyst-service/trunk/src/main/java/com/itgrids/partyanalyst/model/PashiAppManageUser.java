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
@Table(name="pashi_app_manage_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PashiAppManageUser implements java.io.Serializable{

	private Long pashiAppManageUserId;
	private Long pashiAppUserId;
	private Long appointmentUserId;
	private String isDeleted;
	
	private PashiAppUser pashiAppUser;
	private AppointmentUser appointmentUser;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="pashi_app_manage_user_id" , unique=true, nullable=false)
	public Long getPashiAppManageUserId() {
		return pashiAppManageUserId;
	}
	public void setPashiAppManageUserId(Long pashiAppManageUserId) {
		this.pashiAppManageUserId = pashiAppManageUserId;
	}
	
	@Column(name="pashi_app_user_id")
	public Long getPashiAppUserId() {
		return pashiAppUserId;
	}
	public void setPashiAppUserId(Long pashiAppUserId) {
		this.pashiAppUserId = pashiAppUserId;
	}
	
	@Column(name="appointment_user_id")
	public Long getAppointmentUserId() {
		return appointmentUserId;
	}
	public void setAppointmentUserId(Long appointmentUserId) {
		this.appointmentUserId = appointmentUserId;
	}
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "pashi_app_user_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PashiAppUser getPashiAppUser() {
		return pashiAppUser;
	}
	public void setPashiAppUser(PashiAppUser pashiAppUser) {
		this.pashiAppUser = pashiAppUser;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "appointment_user_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AppointmentUser getAppointmentUser() {
		return appointmentUser;
	}
	public void setAppointmentUser(AppointmentUser appointmentUser) {
		this.appointmentUser = appointmentUser;
	}

}
