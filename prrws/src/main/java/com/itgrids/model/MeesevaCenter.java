package com.itgrids.model;

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

@Entity
@Table(name = "meeseva_center")
public class MeesevaCenter implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long meesevaCenterId;
	private String uniqueId;
	private Long addressId;
	private String ownerName;
	private String mobileNo;
	private Date establishedDate;
	private String isDeleted;
	
	private LocationAddress address;
	
	@Id
	@Column(name="meeseva_center_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getMeesevaCenterId() {
		return meesevaCenterId;
	}
	public void setMeesevaCenterId(Long meesevaCenterId) {
		this.meesevaCenterId = meesevaCenterId;
	}
	
	@Column(name="unique_id")
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	
	@Column(name="address_id")
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	
	@Column(name="owner_name")
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	
	@Column(name="mobile_no")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	@Column(name="established_date")
	public Date getEstablishedDate() {
		return establishedDate;
	}
	public void setEstablishedDate(Date establishedDate) {
		this.establishedDate = establishedDate;
	}
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id", insertable = false, updatable = false)
	public LocationAddress getAddress() {
		return address;
	}
	public void setAddress(LocationAddress address) {
		this.address = address;
	}
}
