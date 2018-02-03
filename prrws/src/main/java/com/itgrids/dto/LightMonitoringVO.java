package com.itgrids.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LightMonitoringVO implements Serializable{
	
	private static long serialVersionUID = -3940676855454173539L;
	
	private Long panchayatId;
	private Long totalLights = 0L;
	private Long totalPanels = 0L;
	private Long totalPoles = 0L;
	private Long workingLights = 0L;
	private Long onLights = 0L;
	private Long offLights = 0L;
	private Long notWorkingLights = 0L;
	private Long lightCount = 0L;	
	private Long wattage = 0L;
	private Long districtId;
	private String districtName;
	private Long constituencyId;
	private String name;
	private Long parliamentId;
	private Long tehsilId;
	private String tehsilName;
	private Long locationId;
	private String locationName;
	private Long totalMandals;
	private Long totalGps;
	private String panchayatName;
	private String parliamentName;
	private String constituencyName;
	
	private Long surveyStartedtotalDistricts=0l;
	private Long surveyStartedtotalConstituencys=0l;
	private Long surveyStartedtotalMandals=0l;
	private Long surveyStartedtotalGps=0l;
	private Long totalLedLIghtInstalledCount=0l;
	private AddressVO addressVO ;
	
	private LightMonitoringVO nredcapVO;
	private LightMonitoringVO eeslVO;
	private LightMonitoringVO overDtlsVO;
	private Long lightVendorId;
	private String lightVendorName;
	private List<LightMonitoringVO> subList = new ArrayList<LightMonitoringVO>();
    private Long newAddedLightcount = 0l;
    private Long newAddedPanelCount = 0l;
    private String timePeriod;
    private Long lightTarget = 0l;
    private Long panelTarget = 0l;
    private Double lightPercentage = 0.0d;
    private Double panelPercentage = 0.0d;
    private Long teamCount = 0L;
    private Long totalVillages = 0L;
    private String workDate;
    
    public LightMonitoringVO() {
		
	}
	public LightMonitoringVO(Long lightVendorId, String lightVendorName) {
		this.lightVendorId = lightVendorId;
		this.lightVendorName = lightVendorName;
	}
	
	private List<LightWattageVO> WattageList = new ArrayList<LightWattageVO>(0);

	public List<LightWattageVO> getWattageList() {
		return WattageList;
	}
	public void setWattageList(List<LightWattageVO> wattageList) {
		WattageList = wattageList;
	}
	public Long getLightCount() {
		return lightCount;
	}
	public void setLightCount(Long lightCount) {
		this.lightCount = lightCount;
	}
	public Long getWattage() {
		return wattage;
	}
	public void setWattage(Long wattage) {
		this.wattage = wattage;
	}
	public Long getTotalLights() {
		return totalLights;
	}
	public void setTotalLights(Long totalLights) {
		this.totalLights = totalLights;
	}
	public Long getTotalPanels() {
		return totalPanels;
	}
	public void setTotalPanels(Long totalPanels) {
		this.totalPanels = totalPanels;
	}
	public Long getTotalPoles() {
		return totalPoles;
	}
	public void setTotalPoles(Long totalPoles) {
		this.totalPoles = totalPoles;
	}
	
	public Long getOnLights() {
		return onLights;
	}
	public void setOnLights(Long onLights) {
		this.onLights = onLights;
	}
	public Long getOffLights() {
		return offLights;
	}
	public void setOffLights(Long offLights) {
		this.offLights = offLights;
	}
	public Long getWorkingLights() {
		return workingLights;
	}
	public void setWorkingLights(Long workingLights) {
		this.workingLights = workingLights;
	}
	public Long getNotWorkingLights() {
		return notWorkingLights;
	}
	public void setNotWorkingLights(Long notWorkingLights) {
		this.notWorkingLights = notWorkingLights;
	}
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	 
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
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
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public static void setSerialVersionUID(long serialVersionUID) {
		LightMonitoringVO.serialVersionUID = serialVersionUID;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getParliamentId() {
		return parliamentId;
	}
	public void setParliamentId(Long parliamentId) {
		this.parliamentId = parliamentId;
	}
	public Long getTehsilId() {
		return tehsilId;
	}
	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}
	public String getTehsilName() {
		return tehsilName;
	}
	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public Long getTotalMandals() {
		return totalMandals;
	}
	public void setTotalMandals(Long totalMandals) {
		this.totalMandals = totalMandals;
	}
	public Long getTotalGps() {
		return totalGps;
	}
	public void setTotalGps(Long totalGps) {
		this.totalGps = totalGps;
	}
	public String getPanchayatName() {
		return panchayatName;
	}
	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}
	
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public String getParliamentName() {
		return parliamentName;
	}
	public void setParliamentName(String parliamentName) {
		this.parliamentName = parliamentName;
	}
	public Long getSurveyStartedtotalMandals() {
		return surveyStartedtotalMandals;
	}
	public void setSurveyStartedtotalMandals(Long surveyStartedtotalMandals) {
		this.surveyStartedtotalMandals = surveyStartedtotalMandals;
	}
	public Long getSurveyStartedtotalGps() {
		return surveyStartedtotalGps;
	}
	public void setSurveyStartedtotalGps(Long surveyStartedtotalGps) {
		this.surveyStartedtotalGps = surveyStartedtotalGps;
	}
	public Long getTotalLedLIghtInstalledCount() {
		return totalLedLIghtInstalledCount;
	}
	public void setTotalLedLIghtInstalledCount(Long totalLedLIghtInstalledCount) {
		this.totalLedLIghtInstalledCount = totalLedLIghtInstalledCount;
	}
	public AddressVO getAddressVO() {
		return addressVO;
	}
	public void setAddressVO(AddressVO addressVO) {
		this.addressVO = addressVO;
	}
	public Long getSurveyStartedtotalDistricts() {
		return surveyStartedtotalDistricts;
	}
	public void setSurveyStartedtotalDistricts(Long surveyStartedtotalDistricts) {
		this.surveyStartedtotalDistricts = surveyStartedtotalDistricts;
	}
	public Long getSurveyStartedtotalConstituencys() {
		return surveyStartedtotalConstituencys;
	}
	public void setSurveyStartedtotalConstituencys(
			Long surveyStartedtotalConstituencys) {
		this.surveyStartedtotalConstituencys = surveyStartedtotalConstituencys;
	}
	public LightMonitoringVO getNredcapVO() {
		return nredcapVO;
	}
	public void setNredcapVO(LightMonitoringVO nredcapVO) {
		this.nredcapVO = nredcapVO;
	}
	public LightMonitoringVO getEeslVO() {
		return eeslVO;
	}
	public void setEeslVO(LightMonitoringVO eeslVO) {
		this.eeslVO = eeslVO;
	}
	public Long getLightVendorId() {
		return lightVendorId;
	}
	public void setLightVendorId(Long lightVendorId) {
		this.lightVendorId = lightVendorId;
	}
	public String getLightVendorName() {
		return lightVendorName;
	}
	public void setLightVendorName(String lightVendorName) {
		this.lightVendorName = lightVendorName;
	}
	public List<LightMonitoringVO> getSubList() {
		return subList;
	}
	public void setSubList(List<LightMonitoringVO> subList) {
		this.subList = subList;
	}
	public Long getNewAddedLightcount() {
		return newAddedLightcount;
	}
	public void setNewAddedLightcount(Long newAddedLightcount) {
		this.newAddedLightcount = newAddedLightcount;
	}
	public String getTimePeriod() {
		return timePeriod;
	}
	public void setTimePeriod(String timePeriod) {
		this.timePeriod = timePeriod;
	}
	public Long getLightTarget() {
		return lightTarget;
	}
	public void setLightTarget(Long lightTarget) {
		this.lightTarget = lightTarget;
	}
	public Long getPanelTarget() {
		return panelTarget;
	}
	public void setPanelTarget(Long panelTarget) {
		this.panelTarget = panelTarget;
	}
	public Long getNewAddedPanelCount() {
		return newAddedPanelCount;
	}
	public void setNewAddedPanelCount(Long newAddedPanelCount) {
		this.newAddedPanelCount = newAddedPanelCount;
	}
	public Double getLightPercentage() {
		return lightPercentage;
	}
	public void setLightPercentage(Double lightPercentage) {
		this.lightPercentage = lightPercentage;
	}
	public Double getPanelPercentage() {
		return panelPercentage;
	}
	public void setPanelPercentage(Double panelPercentage) {
		this.panelPercentage = panelPercentage;
	}
	public LightMonitoringVO getOverDtlsVO() {
		return overDtlsVO;
	}
	public void setOverDtlsVO(LightMonitoringVO overDtlsVO) {
		this.overDtlsVO = overDtlsVO;
	}
	public Long getTeamCount() {
		return teamCount;
	}
	public void setTeamCount(Long teamCount) {
		this.teamCount = teamCount;
	}
	public Long getTotalVillages() {
		return totalVillages;
	}
	public void setTotalVillages(Long totalVillages) {
		this.totalVillages = totalVillages;
	}
	public String getWorkDate() {
		return workDate;
	}
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	} 
	
	
}
