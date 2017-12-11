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
	
	private Long localElectionBodyId;
	private String localElectionBodyName;
	
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
	
	private Long overAll;
	
	private String color;
	private Long rankId;
	
	private String month;
	
	private List<DiseasesVO> subList1;
	
	private List<DiseasesVO> distCountList;
	private List<DiseasesVO> mandalCountList;
	
	private List<DiseasesVO> distCountForMalariaList;
	private List<DiseasesVO> mandalCountForMalariaList;
	
	private List<DiseasesVO> distCountForDengueList;
	private List<DiseasesVO> mandalCountForDengueList;
	private Long size;
	private Long thisWeekCount = 0L;
	private Long previousWeekCount = 0L;
	private Long thisMonthCount = 0L;
	private Long previousMonthCount = 0L;
	private String weekPerc;
	private String mnthPerc;
	private String weekType;
	private String monthType;
	
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
	public Long getRankId() {
		return rankId;
	}
	public void setRankId(Long rankId) {
		this.rankId = rankId;
	}
	public Long getLocalElectionBodyId() {
		return localElectionBodyId;
	}
	public void setLocalElectionBodyId(Long localElectionBodyId) {
		this.localElectionBodyId = localElectionBodyId;
	}
	public String getLocalElectionBodyName() {
		return localElectionBodyName;
	}
	public void setLocalElectionBodyName(String localElectionBodyName) {
		this.localElectionBodyName = localElectionBodyName;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	public Long getOverAll() {
		return overAll;
	}
	public void setOverAll(Long overAll) {
		this.overAll = overAll;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Long getThisWeekCount() {
		return thisWeekCount;
	}
	public void setThisWeekCount(Long thisWeekCount) {
		this.thisWeekCount = thisWeekCount;
	}
	public Long getPreviousWeekCount() {
		return previousWeekCount;
	}
	public void setPreviousWeekCount(Long previousWeekCount) {
		this.previousWeekCount = previousWeekCount;
	}
	public Long getThisMonthCount() {
		return thisMonthCount;
	}
	public void setThisMonthCount(Long thisMonthCount) {
		this.thisMonthCount = thisMonthCount;
	}
	public Long getPreviousMonthCount() {
		return previousMonthCount;
	}
	public void setPreviousMonthCount(Long previousMonthCount) {
		this.previousMonthCount = previousMonthCount;
	}
	public String getWeekPerc() {
		return weekPerc;
	}
	public void setWeekPerc(String weekPerc) {
		this.weekPerc = weekPerc;
	}
	public String getMnthPerc() {
		return mnthPerc;
	}
	public void setMnthPerc(String mnthPerc) {
		this.mnthPerc = mnthPerc;
	}
	public String getWeekType() {
		return weekType;
	}
	public void setWeekType(String weekType) {
		this.weekType = weekType;
	}
	public String getMonthType() {
		return monthType;
	}
	public void setMonthType(String monthType) {
		this.monthType = monthType;
	}
	
	
}
