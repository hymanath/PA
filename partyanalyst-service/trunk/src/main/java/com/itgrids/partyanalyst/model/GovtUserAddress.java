package com.itgrids.partyanalyst.model;

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
@Table(name="govt_user_address")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GovtUserAddress {

	private Long userAddressId;
	private Long countryId;
	private Long stateId;
	private Long zoneId;
	private Long regionId;
	private Long circleId;
	private Long divisionId;
	private Long subDivisionId;
	private Long rangeId;
	private Long districtId;
	private Long constituencyId;
	private Long tehsilId;
	private Long townshipId;
	private Long hamletId;
	private String houseNo;
	private String street;
	private String pincode;
	private Long localElectionBodyId;
	private Long wardId;
	private Long parliamentConstituencyId;
	private Long boothId;
	private String refId;
	private Long panchayatId;
	private String localArea;
	private Long memberId;
	private Long tdpCadreId;
	private String landMark;
	private String addressLane1;
	private String addressLane2;
	private String deliveryocation;
	
	private TdpCadre tdpCadre;
	private GovtDepartmentWorkLocation state;
	private GovtDepartmentWorkLocation zone;
	private GovtDepartmentWorkLocation region;
	private GovtDepartmentWorkLocation circle;
	private GovtDepartmentWorkLocation division;
	private GovtDepartmentWorkLocation subDivision;
	private GovtDepartmentWorkLocation range;
	private GovtDepartmentWorkLocation district;
	private GovtDepartmentWorkLocation constituency;
	private GovtDepartmentWorkLocation tehsil;
	private GovtDepartmentWorkLocation localElectionBody;
	private GovtDepartmentWorkLocation ward;
	private GovtDepartmentWorkLocation panchayat;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_address_id", unique=true, nullable=false)
	public Long getUserAddressId() {
		return userAddressId;
	}
	public void setUserAddressId(Long userAddressId) {
		this.userAddressId = userAddressId;
	}
	@Column(name = "country_id")
	public Long getCountryId() {
		return countryId;
	}
	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}
	@Column(name = "state_id")
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	@Column(name = "zone_id")
	public Long getZoneId() {
		return zoneId;
	}
	public void setZoneId(Long zoneId) {
		this.zoneId = zoneId;
	}
	@Column(name = "region_id")
	public Long getRegionId() {
		return regionId;
	}
	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}
	@Column(name = "circle_id")
	public Long getCircleId() {
		return circleId;
	}
	public void setCircleId(Long circleId) {
		this.circleId = circleId;
	}
	@Column(name = "division_id")
	public Long getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(Long divisionId) {
		this.divisionId = divisionId;
	}
	@Column(name = "sub_division_id")
	public Long getSubDivisionId() {
		return subDivisionId;
	}
	public void setSubDivisionId(Long subDivisionId) {
		this.subDivisionId = subDivisionId;
	}
	
	@Column(name = "range_id")
	public Long getRangeId() {
		return rangeId;
	}
	
	public void setRangeId(Long rangeId) {
		this.rangeId = rangeId;
	}
	@Column(name = "district_id")
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	@Column(name = "constituency_id")
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	@Column(name = "tehsil_id")
	public Long getTehsilId() {
		return tehsilId;
	}
	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}
	@Column(name = "township_id")
	public Long getTownshipId() {
		return townshipId;
	}
	public void setTownshipId(Long townshipId) {
		this.townshipId = townshipId;
	}
	@Column(name = "hamlet_id")
	public Long getHamletId() {
		return hamletId;
	}
	public void setHamletId(Long hamletId) {
		this.hamletId = hamletId;
	}
	@Column(name = "house_no")
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	@Column(name = "street")
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	@Column(name = "pincode")
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	@Column(name = "local_election_body")
	public Long getLocalElectionBodyId() {
		return localElectionBodyId;
	}
	public void setLocalElectionBodyId(Long localElectionBodyId) {
		this.localElectionBodyId = localElectionBodyId;
	}
	
	@Column(name = "ward")
	public Long getWardId() {
		return wardId;
	}
	public void setWardId(Long wardId) {
		this.wardId = wardId;
	}
	
	@Column(name = "parliament_constituency_id")
	public Long getParliamentConstituencyId() {
		return parliamentConstituencyId;
	}	
	public void setParliamentConstituencyId(Long parliamentConstituencyId) {
		this.parliamentConstituencyId = parliamentConstituencyId;
	}
	@Column(name = "booth_id")
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	@Column(name = "ref_id")
	public String getRefId() {
		return refId;
	}
	public void setRefId(String refId) {
		this.refId = refId;
	}
	@Column(name = "panchayat_id")
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	@Column(name = "local_area")
	public String getLocalArea() {
		return localArea;
	}
	public void setLocalArea(String localArea) {
		this.localArea = localArea;
	}
	@Column(name = "member_id")
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	@Column(name = "tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	@Column(name = "land_mark")
	public String getLandMark() {
		return landMark;
	}
	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}
	@Column(name = "address_lane1")
	public String getAddressLane1() {
		return addressLane1;
	}
	public void setAddressLane1(String addressLane1) {
		this.addressLane1 = addressLane1;
	}
	@Column(name = "address_lane2")
	public String getAddressLane2() {
		return addressLane2;
	}
	public void setAddressLane2(String addressLane2) {
		this.addressLane2 = addressLane2;
	}
	@Column(name = "delivery_location")
	public String getDeliveryocation() {
		return deliveryocation;
	}
	public void setDeliveryocation(String deliveryocation) {
		this.deliveryocation = deliveryocation;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="tdp_cadre_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}
	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="state_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtDepartmentWorkLocation getState() {
		return state;
	}
	public void setState(GovtDepartmentWorkLocation state) {
		this.state = state;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="zone_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtDepartmentWorkLocation getZone() {
		return zone;
	}
	public void setZone(GovtDepartmentWorkLocation zone) {
		this.zone = zone;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="region_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtDepartmentWorkLocation getRegion() {
		return region;
	}
	public void setRegion(GovtDepartmentWorkLocation region) {
		this.region = region;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="circle_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtDepartmentWorkLocation getCircle() {
		return circle;
	}
	public void setCircle(GovtDepartmentWorkLocation circle) {
		this.circle = circle;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="division_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtDepartmentWorkLocation getDivision() {
		return division;
	}
	public void setDivision(GovtDepartmentWorkLocation division) {
		this.division = division;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="sub_division_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtDepartmentWorkLocation getSubDivision() {
		return subDivision;
	}
	public void setSubDivision(GovtDepartmentWorkLocation subDivision) {
		this.subDivision = subDivision;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="range_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtDepartmentWorkLocation getRange() {
		return range;
	}
	public void setRange(GovtDepartmentWorkLocation range) {
		this.range = range;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="district_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtDepartmentWorkLocation getDistrict() {
		return district;
	}
	public void setDistrict(GovtDepartmentWorkLocation district) {
		this.district = district;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="constituency_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtDepartmentWorkLocation getConstituency() {
		return constituency;
	}
	public void setConstituency(GovtDepartmentWorkLocation constituency) {
		this.constituency = constituency;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="tehsil_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtDepartmentWorkLocation getTehsil() {
		return tehsil;
	}
	public void setTehsil(GovtDepartmentWorkLocation tehsil) {
		this.tehsil = tehsil;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="local_election_body", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtDepartmentWorkLocation getLocalElectionBody() {
		return localElectionBody;
	}
	public void setLocalElectionBody(GovtDepartmentWorkLocation localElectionBody) {
		this.localElectionBody = localElectionBody;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="ward", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtDepartmentWorkLocation getWard() {
		return ward;
	}
	public void setWard(GovtDepartmentWorkLocation ward) {
		this.ward = ward;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="panchayat_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtDepartmentWorkLocation getPanchayat() {
		return panchayat;
	}
	public void setPanchayat(GovtDepartmentWorkLocation panchayat) {
		this.panchayat = panchayat;
	}
	
	
}
