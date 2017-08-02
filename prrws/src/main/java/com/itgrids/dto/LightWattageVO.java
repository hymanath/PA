package com.itgrids.dto;

public class LightWattageVO {
	
	public Long getWattage() {
		return Wattage;
	}
	public void setWattage(Long wattage) {
		Wattage = wattage;
	}
	public Long getLightCount() {
		return lightCount;
	}
	public void setLightCount(Long lightCount) {
		this.lightCount = lightCount;
	}
	private  Long Wattage;
	private Long lightCount;
	
	
	

}
