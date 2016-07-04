package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class LocationVO implements Serializable {

	private Long districtId;
	private String districtName;
	private Long constituencyId;
	private String constituencyName;
	private Long tehsilId;
	private String tehsilName;
	private Long villageId;
	private String villageName;
	private Long parliamentConstituencyId;
	private Long localBodyId;
	private String localEleBodyName;
	
	
	
	public Long getLocalBodyId() {
		return localBodyId;
	}
	public void setLocalBodyId(Long localBodyId) {
		this.localBodyId = localBodyId;
	}
	public String getLocalEleBodyName() {
		return localEleBodyName;
	}
	public void setLocalEleBodyName(String localEleBodyName) {
		this.localEleBodyName = localEleBodyName;
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
	public Long getVillageId() {
		return villageId;
	}
	public void setVillageId(Long villageId) {
		this.villageId = villageId;
	}
	public String getVillageName() {
		return villageName;
	}
	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	public Long getParliamentConstituencyId() {
		return parliamentConstituencyId;
	}
	public void setParliamentConstituencyId(Long parliamentConstituencyId) {
		this.parliamentConstituencyId = parliamentConstituencyId;
	}
	
	
}
