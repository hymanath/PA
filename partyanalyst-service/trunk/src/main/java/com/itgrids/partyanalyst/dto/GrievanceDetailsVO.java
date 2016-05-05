package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Teja
 * @date may 3, 2016
 */
/**
 * @author Client
 *
 */

public class GrievanceDetailsVO implements Serializable{

	private Long id;
	private String name;
	private Long Count;
	private String status;
	private String locationName;
	private Long partyCount=0l;
	private Long govtCount=0l;
	private Long welfareCount=0l;
	private String idStr;
	
	private Long deathCount = 0l;
	private Long hospitCount = 0l;
	private List<GrievanceDetailsVO> subList = new LinkedList<GrievanceDetailsVO>();	
	private List<IdNameVO> locationList = new ArrayList<IdNameVO>();
	
	
	public List<IdNameVO> getLocationList() {
		return locationList;
	}
	public void setLocationList(List<IdNameVO> locationList) {
		this.locationList = locationList;
	}
	public String getIdStr() {
		return idStr;
	}
	public void setIdStr(String idStr) {
		this.idStr = idStr;
	}
	public List<GrievanceDetailsVO> getSubList() {
		return subList;
	}
	public void setSubList(List<GrievanceDetailsVO> subList) {
		this.subList = subList;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public Long getDeathCount() {
		return deathCount;
	}
	public void setDeathCount(Long deathCount) {
		this.deathCount = deathCount;
	}
	public Long getHospitCount() {
		return hospitCount;
	}
	public void setHospitCount(Long hospitCount) {
		this.hospitCount = hospitCount;
	}
	public Long getPartyCount() {
		return partyCount;
	}
	public void setPartyCount(Long partyCount) {
		this.partyCount = partyCount;
	}
	public Long getGovtCount() {
		return govtCount;
	}
	public void setGovtCount(Long govtCount) {
		this.govtCount = govtCount;
	}
	public Long getWelfareCount() {
		return welfareCount;
	}
	public void setWelfareCount(Long welfareCount) {
		this.welfareCount = welfareCount;
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
	public Long getCount() {
		return Count;
	}
	public void setCount(Long count) {
		Count = count;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
