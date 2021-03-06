package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Teja
 * @date may 24, 2016
 */
public class AccommodationVO implements Serializable{

	private Long 	id;
	private String 	name;
	private Long 	constituencyId;
	private String 	constituencyName;
	private Long 	districtId;
	private String 	districtName;
	private String 	address;
	private String 	contactPerson;
	private String 	mobileNo;
	private String 	longitude;
	private String 	latitude;
	private String 	status;
	private List<AccommodationVO> locationDetails = new ArrayList<AccommodationVO>(0);
	private List<Long> inActiveAccommadationTrackingIdsList = new ArrayList<Long>(0);
	private String locationName;
	private Long typeId;
	private Long locationTypeId;
	private Long lastAccommodationTrackingId;
	private Long accommodationTrackingId;
	
	
	public Long getAccommodationTrackingId() {
		return accommodationTrackingId;
	}
	public void setAccommodationTrackingId(Long accommodationTrackingId) {
		this.accommodationTrackingId = accommodationTrackingId;
	}
	public List<Long> getInActiveAccommadationTrackingIdsList() {
		return inActiveAccommadationTrackingIdsList;
	}
	public void setInActiveAccommadationTrackingIdsList(
			List<Long> inActiveAccommadationTrackingIdsList) {
		this.inActiveAccommadationTrackingIdsList = inActiveAccommadationTrackingIdsList;
	}
	public Long getLastAccommodationTrackingId() {
		return lastAccommodationTrackingId;
	}
	public void setLastAccommodationTrackingId(Long lastAccommodationTrackingId) {
		this.lastAccommodationTrackingId = lastAccommodationTrackingId;
	}
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	public Long getLocationTypeId() {
		return locationTypeId;
	}
	public void setLocationTypeId(Long locationTypeId) {
		this.locationTypeId = locationTypeId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<AccommodationVO> getLocationDetails() {
		return locationDetails;
	}
	public void setLocationDetails(List<AccommodationVO> locationDetails) {
		this.locationDetails = locationDetails;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
}
