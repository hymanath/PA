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
@Table(name ="appointment_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppointmentUser extends BaseModel {
	
	private Long 	appointmenUserId;
	private String name;
	private String mobile;
	private String uniqueIdPrefix;
	private Long tdpCadreId;
	private TdpCadre tdpCadre;
  
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "appointment_user_id", unique = true, nullable = false)
	public Long getAppointmenUserId() {
		return appointmenUserId;
	}
	public void setAppointmenUserId(Long appointmenUserId) {
		this.appointmenUserId = appointmenUserId;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "mobile")
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Column(name = "unique_id_prefix")
	public String getUniqueIdPrefix() {
		return uniqueIdPrefix;
	}
	public void setUniqueIdPrefix(String uniqueIdPrefix) {
		this.uniqueIdPrefix = uniqueIdPrefix;
	}
	
	@Column(name = "tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_cadre_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}
	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}
}
