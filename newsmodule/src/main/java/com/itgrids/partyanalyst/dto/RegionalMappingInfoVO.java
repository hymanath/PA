package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class RegionalMappingInfoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long regionId;
	private String regionName;
	private Boolean flag;
	private String villagesCovered;
	
	public Long getRegionId() {
		return regionId;
	}
	
	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}
	
	public String getRegionName() {
		return regionName;
	}
	
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	
	public Boolean getFlag() {
		return flag;
	}
	
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}	
	
	public String getVillagesCovered() {
		return villagesCovered;
	}
	
	public void setVillagesCovered(String villagesCovered) {
		this.villagesCovered = villagesCovered;
	}
	
	@Override
	public boolean equals(Object obj){
		if(regionId==null)
			regionId = -1L;
		if(obj instanceof RegionalMappingInfoVO){

			RegionalMappingInfoVO vo = (RegionalMappingInfoVO) obj;
			return this.regionId.equals(vo.getRegionId());
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		if(regionId==null)
			regionId = -1L;
		return this.regionId.intValue();
	}
	
	
}
