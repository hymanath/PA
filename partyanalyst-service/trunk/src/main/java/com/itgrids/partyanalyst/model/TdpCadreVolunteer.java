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
@Table(name = "tdp_cadre_volunteer")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCadreVolunteer extends BaseModel implements java.io.Serializable {
	
	
	private static final long serialVersionUID = -8069040016334181698L;
	private Long tdpCadreVolunteerId;
	private String mobileNo;
	private String name;
	private String email;
	private String address;
	private String laptop;
	private String internet;
	private String tablet;
	private String smartPhone3G;
	private String smartPhone2G;
	private Date insertedTime;
	private Date updateTime;
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_cadre_volunteer_id", unique = true, nullable = false)	
	public Long getTdpCadreVolunteerId() {
		return tdpCadreVolunteerId;
	}
	public void setTdpCadreVolunteerId(Long tdpCadreVolunteerId) {
		this.tdpCadreVolunteerId = tdpCadreVolunteerId;
	}
	
	@Column(name="mobile_no")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name="laptop")
	public String getLaptop() {
		return laptop;
	}
	public void setLaptop(String laptop) {
		this.laptop = laptop;
	}
	
	@Column(name="internet")
	public String getInternet() {
		return internet;
	}
	public void setInternet(String internet) {
		this.internet = internet;
	}
	
	@Column(name="tablet")
	public String getTablet() {
		return tablet;
	}
	public void setTablet(String tablet) {
		this.tablet = tablet;
	}
	
	
	@Column(name="smart_phone_3G")
	public String getSmartPhone3G() {
		return smartPhone3G;
	}
	public void setSmartPhone3G(String smartPhone3G) {
		this.smartPhone3G = smartPhone3G;
	}
	
	@Column(name="smart_phone_2G")
	public String getSmartPhone2G() {
		return smartPhone2G;
	}
	public void setSmartPhone2G(String smartPhone2G) {
		this.smartPhone2G = smartPhone2G;
	}
	
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name="update_time")
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
	
	
}
