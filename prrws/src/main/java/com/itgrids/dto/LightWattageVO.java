package com.itgrids.dto;

public class LightWattageVO {
	
	private  Long Wattage;
	private Long lightCount;
	
	private Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	

}
