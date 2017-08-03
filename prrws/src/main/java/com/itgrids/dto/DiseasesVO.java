package com.itgrids.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DiseasesVO implements Serializable {
	private Long id;
	private String name;
	
	private Long districtId;
	private String districtName;
	
	private Long parliamentId;
	private String parliamentName;
	
	private Long constituencyId;
	private String constituencyName;
	
	private Long mandalId;
	private String mandalName;
	
	private Long panchayatId;
	private String panchayatName;
	
	private Long locationId;
	private String locationName;
	private Long count;
	private Double percent;
	private String fromDate;
	private String toDate;
	private Long todayCount;
	
	private Long total;
	private Long todayTotal;
	private Double totalPercent;
	
	private Long dengueToday;
	private Long dengueTotal;
	private Double denguePercent;
	
	private Long malariaToday;
	private Long malariaTotal;
	private Double malariaPercent;
	
	private String color;
	
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
	public Long getTodayCount() {
		return todayCount;
	}
	public void setTodayCount(Long todayCount) {
		this.todayCount = todayCount;
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
	public Long getParliamentId() {
		return parliamentId;
	}
	public void setParliamentId(Long parliamentId) {
		this.parliamentId = parliamentId;
	}
	public String getParliamentName() {
		return parliamentName;
	}
	public void setParliamentName(String parliamentName) {
		this.parliamentName = parliamentName;
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
	public Long getMandalId() {
		return mandalId;
	}
	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	public String getPanchayatName() {
		return panchayatName;
	}
	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Long getTodayTotal() {
		return todayTotal;
	}
	public void setTodayTotal(Long todayTotal) {
		this.todayTotal = todayTotal;
	}
	public Double getTotalPercent() {
		return totalPercent;
	}
	public void setTotalPercent(Double totalPercent) {
		this.totalPercent = totalPercent;
	}
	public Long getDengueToday() {
		return dengueToday;
	}
	public void setDengueToday(Long dengueToday) {
		this.dengueToday = dengueToday;
	}
	public Long getDengueTotal() {
		return dengueTotal;
	}
	public void setDengueTotal(Long dengueTotal) {
		this.dengueTotal = dengueTotal;
	}
	public Double getDenguePercent() {
		return denguePercent;
	}
	public void setDenguePercent(Double denguePercent) {
		this.denguePercent = denguePercent;
	}
	public Long getMalariaToday() {
		return malariaToday;
	}
	public void setMalariaToday(Long malariaToday) {
		this.malariaToday = malariaToday;
	}
	public Long getMalariaTotal() {
		return malariaTotal;
	}
	public void setMalariaTotal(Long malariaTotal) {
		this.malariaTotal = malariaTotal;
	}
	public Double getMalariaPercent() {
		return malariaPercent;
	}
	public void setMalariaPercent(Double malariaPercent) {
		this.malariaPercent = malariaPercent;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	
}
