package com.itgrids.dto;

import java.util.ArrayList;
import java.util.List;

public class NTRSujalaOverviewVO {

	private Long id;
	private String name;
	
	private Long totalMotherPlants = 0L;
	private Long activeMotherPlants = 0L;
	private Long inActiveMotherPlants = 0L;
	private Long mpGoodWaterQuality = 0L;
	private Long mpBadWaterQuality = 0L;
	private String mpSafeWaterDispenced;
	private Long totalRDUs = 0L;
	private Long activeRDUs = 0L;
	private Long inActiveRDUs = 0L;
	private Long rduGoodWaterQuality = 0L;
	private Long rduBadWaterQuality = 0L;
	private String rduSellWater;
	
	private String highDispanceRDUName;
	private String highRDUDispanceLtrs;
	private String lowDispanceRDUName;
	private String lowRDUDispanceLtrs;
	
	private Long totalCustomers = 0L;
	private Long activeCustomers = 0L;
	private Long inActiveCustomers = 0L;
	
	private List<NTRSujalaOverviewVO> motherPlantsList = new ArrayList<NTRSujalaOverviewVO>();
	private List<NTRSujalaOverviewVO> districtList = new ArrayList<NTRSujalaOverviewVO>();
	private List<NTRSujalaOverviewVO> subList = new ArrayList<NTRSujalaOverviewVO>();
	
	private String health;
	private String waterQuality;
	private String location;
	private String mobileNo;
	private String tds;
	private String ph;
	private String mandal;
	private String district;
	private String latitude;
	private String longitude;
	
	private Long waterTankCapacity = 0L;
	private Long newCustomers = 0L;
	private Long oldCustomers = 0L;
	
	private List<NTRSujalaOverviewVO> rdusList = new ArrayList<NTRSujalaOverviewVO>();
	private Long mpId;
	private String mpName;
	
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
	public Long getTotalMotherPlants() {
		return totalMotherPlants;
	}
	public void setTotalMotherPlants(Long totalMotherPlants) {
		this.totalMotherPlants = totalMotherPlants;
	}
	public Long getActiveMotherPlants() {
		return activeMotherPlants;
	}
	public void setActiveMotherPlants(Long activeMotherPlants) {
		this.activeMotherPlants = activeMotherPlants;
	}
	public Long getInActiveMotherPlants() {
		return inActiveMotherPlants;
	}
	public void setInActiveMotherPlants(Long inActiveMotherPlants) {
		this.inActiveMotherPlants = inActiveMotherPlants;
	}
	public Long getMpGoodWaterQuality() {
		return mpGoodWaterQuality;
	}
	public void setMpGoodWaterQuality(Long mpGoodWaterQuality) {
		this.mpGoodWaterQuality = mpGoodWaterQuality;
	}
	public Long getMpBadWaterQuality() {
		return mpBadWaterQuality;
	}
	public void setMpBadWaterQuality(Long mpBadWaterQuality) {
		this.mpBadWaterQuality = mpBadWaterQuality;
	}
	public String getMpSafeWaterDispenced() {
		return mpSafeWaterDispenced;
	}
	public void setMpSafeWaterDispenced(String mpSafeWaterDispenced) {
		this.mpSafeWaterDispenced = mpSafeWaterDispenced;
	}
	public Long getTotalRDUs() {
		return totalRDUs;
	}
	public void setTotalRDUs(Long totalRDUs) {
		this.totalRDUs = totalRDUs;
	}
	public Long getActiveRDUs() {
		return activeRDUs;
	}
	public void setActiveRDUs(Long activeRDUs) {
		this.activeRDUs = activeRDUs;
	}
	public Long getInActiveRDUs() {
		return inActiveRDUs;
	}
	public void setInActiveRDUs(Long inActiveRDUs) {
		this.inActiveRDUs = inActiveRDUs;
	}
	public Long getRduGoodWaterQuality() {
		return rduGoodWaterQuality;
	}
	public void setRduGoodWaterQuality(Long rduGoodWaterQuality) {
		this.rduGoodWaterQuality = rduGoodWaterQuality;
	}
	public Long getRduBadWaterQuality() {
		return rduBadWaterQuality;
	}
	public void setRduBadWaterQuality(Long rduBadWaterQuality) {
		this.rduBadWaterQuality = rduBadWaterQuality;
	}
	public String getRduSellWater() {
		return rduSellWater;
	}
	public void setRduSellWater(String rduSellWater) {
		this.rduSellWater = rduSellWater;
	}
	public String getHighDispanceRDUName() {
		return highDispanceRDUName;
	}
	public void setHighDispanceRDUName(String highDispanceRDUName) {
		this.highDispanceRDUName = highDispanceRDUName;
	}
	public String getHighRDUDispanceLtrs() {
		return highRDUDispanceLtrs;
	}
	public void setHighRDUDispanceLtrs(String highRDUDispanceLtrs) {
		this.highRDUDispanceLtrs = highRDUDispanceLtrs;
	}
	public String getLowDispanceRDUName() {
		return lowDispanceRDUName;
	}
	public void setLowDispanceRDUName(String lowDispanceRDUName) {
		this.lowDispanceRDUName = lowDispanceRDUName;
	}
	public String getLowRDUDispanceLtrs() {
		return lowRDUDispanceLtrs;
	}
	public void setLowRDUDispanceLtrs(String lowRDUDispanceLtrs) {
		this.lowRDUDispanceLtrs = lowRDUDispanceLtrs;
	}
	public Long getTotalCustomers() {
		return totalCustomers;
	}
	public void setTotalCustomers(Long totalCustomers) {
		this.totalCustomers = totalCustomers;
	}
	public Long getActiveCustomers() {
		return activeCustomers;
	}
	public void setActiveCustomers(Long activeCustomers) {
		this.activeCustomers = activeCustomers;
	}
	public Long getInActiveCustomers() {
		return inActiveCustomers;
	}
	public void setInActiveCustomers(Long inActiveCustomers) {
		this.inActiveCustomers = inActiveCustomers;
	}
	public List<NTRSujalaOverviewVO> getMotherPlantsList() {
		return motherPlantsList;
	}
	public void setMotherPlantsList(List<NTRSujalaOverviewVO> motherPlantsList) {
		this.motherPlantsList = motherPlantsList;
	}
	public List<NTRSujalaOverviewVO> getDistrictList() {
		return districtList;
	}
	public void setDistrictList(List<NTRSujalaOverviewVO> districtList) {
		this.districtList = districtList;
	}
	public List<NTRSujalaOverviewVO> getSubList() {
		return subList;
	}
	public void setSubList(List<NTRSujalaOverviewVO> subList) {
		this.subList = subList;
	}
	public String getHealth() {
		return health;
	}
	public void setHealth(String health) {
		this.health = health;
	}
	public String getWaterQuality() {
		return waterQuality;
	}
	public void setWaterQuality(String waterQuality) {
		this.waterQuality = waterQuality;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getTds() {
		return tds;
	}
	public void setTds(String tds) {
		this.tds = tds;
	}
	public String getPh() {
		return ph;
	}
	public void setPh(String ph) {
		this.ph = ph;
	}
	public String getMandal() {
		return mandal;
	}
	public void setMandal(String mandal) {
		this.mandal = mandal;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public Long getWaterTankCapacity() {
		return waterTankCapacity;
	}
	public void setWaterTankCapacity(Long waterTankCapacity) {
		this.waterTankCapacity = waterTankCapacity;
	}
	public Long getNewCustomers() {
		return newCustomers;
	}
	public void setNewCustomers(Long newCustomers) {
		this.newCustomers = newCustomers;
	}
	public Long getOldCustomers() {
		return oldCustomers;
	}
	public void setOldCustomers(Long oldCustomers) {
		this.oldCustomers = oldCustomers;
	}
	public List<NTRSujalaOverviewVO> getRdusList() {
		return rdusList;
	}
	public void setRdusList(List<NTRSujalaOverviewVO> rdusList) {
		this.rdusList = rdusList;
	}
	public Long getMpId() {
		return mpId;
	}
	public void setMpId(Long mpId) {
		this.mpId = mpId;
	}
	public String getMpName() {
		return mpName;
	}
	public void setMpName(String mpName) {
		this.mpName = mpName;
	}
}
