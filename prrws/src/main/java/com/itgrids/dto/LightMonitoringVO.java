package com.itgrids.dto;

import java.io.Serializable;
import java.util.List;

public class LightMonitoringVO implements Serializable{
	
	private static final long serialVersionUID = -3940676855454173539L;
	
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
	private List<LightWattageVO> WattageList;

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
	
	

}
