package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @author Administrator
 *
 */
@Entity
@Table(name = "tdp_cadre_online")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCadreOnline {

	private Long tdpCadreOnlineId;
	private String orderId;
	private String area;
	private String address;
	private String pincode;
	private String deliveryMode;
	private String shipCountry;
	private String shipAddress;
	private String email;
	private String permanentAddress;
	private String onlineId;
	private Long tdpCadreId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_cadre_online_id", unique = true, nullable = false)
	public Long getTdpCadreOnlineId() {
		return tdpCadreOnlineId;
	}
	
	
	public void setTdpCadreOnlineId(Long tdpCadreOnlineId) {
		this.tdpCadreOnlineId = tdpCadreOnlineId;
	}
	
	@Column(name = "order_id")
	public String getOrderId() {
		return orderId;
	}
	
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@Column(name = "area")
	public String getArea() {
		return area;
	}
	
	public void setArea(String area) {
		this.area = area;
	}
	
	@Column(name = "address")
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name = "pincode")
	public String getPincode() {
		return pincode;
	}
	
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
	@Column(name = "delivery_mode")
	public String getDeliveryMode() {
		return deliveryMode;
	}
	
	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
	}
	
	@Column(name = "ship_country")
	public String getShipCountry() {
		return shipCountry;
	}
	
	public void setShipCountry(String shipCountry) {
		this.shipCountry = shipCountry;
	}
	
	@Column(name = "ship_address")
	public String getShipAddress() {
		return shipAddress;
	}
	
	public void setShipAddress(String shipAddress) {
		this.shipAddress = shipAddress;
	}
	
	@Column(name = "email")
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "permanent_address")
	public String getPermanentAddress() {
		return permanentAddress;
	}
	
	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	@Column(name = "online_id")
	public String getOnlineId() {
		return onlineId;
	}

	public void setOnlineId(String onlineId) {
		this.onlineId = onlineId;
	}

	@Column(name = "tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}

	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	
}
