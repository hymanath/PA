package com.itgrids.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DiseasesVO implements Serializable {
	private Long id;
	private String name;
	private Long locationId;
	private String locationName;
	private Long count;
	private Double percent;
	private String fromDate;
	private String toDate;
	private List<DiseasesVO> subList1;
	
	private List<DiseasesVO> distCountList;
	private List<DiseasesVO> mandalCountList;
	
	private List<DiseasesVO> distCountForMalariaList;
	private List<DiseasesVO> mandalCountForMalariaList;
	
	private List<DiseasesVO> distCountForDengueList;
	private List<DiseasesVO> mandalCountForDengueList;
	
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
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
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
	public List<DiseasesVO> getSubList1() {
		if(subList1 == null){
			subList1 = new ArrayList<DiseasesVO>();
		}
		return subList1;
	}
	public Double getPercent() {
		return percent;
	}
	public void setPercent(Double percent) {
		this.percent = percent;
	}
	public List<DiseasesVO> getDistCountList() {
		if(distCountList == null){
			distCountList = new ArrayList<DiseasesVO>();
		}
		return distCountList;
	}
	public List<DiseasesVO> getMandalCountList() {
		if(mandalCountList == null){
			mandalCountList = new ArrayList<DiseasesVO>();
		}
		return mandalCountList;
	}
	public List<DiseasesVO> getDistCountForMalariaList() {
		if(distCountForMalariaList == null){
			distCountForMalariaList = new ArrayList<DiseasesVO>();
		}
		return distCountForMalariaList;
	}
	public List<DiseasesVO> getMandalCountForMalariaList() {
		if(mandalCountForMalariaList == null){
			mandalCountForMalariaList = new ArrayList<DiseasesVO>();
		}
		return mandalCountForMalariaList;
	}
	public List<DiseasesVO> getDistCountForDengueList() {
		if(distCountForDengueList == null){
			distCountForDengueList = new ArrayList<DiseasesVO>();
		}
		return distCountForDengueList;
	}
	public List<DiseasesVO> getMandalCountForDengueList() {
		if(mandalCountForDengueList == null){
			mandalCountForDengueList = new ArrayList<DiseasesVO>();
		}
		return mandalCountForDengueList;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
	
}
