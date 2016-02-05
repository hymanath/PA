package com.itgrids.partyanalyst.dto;

import java.util.List;

public class RtcUnionVO {

	private Long id;
	private String name;
	
	private Long zoneId;
	private String zoneName;
	

	private Long totalCount;
	private Long tabCount;
	private Long webCount;
	private Long onlineCount;
	
	private Long todayTotalCount;
	private Long todayTabCount;
	private Long todayWebCount;
	private Long todayOnlineCount;
	
	private List<RtcUnionVO> rtcUnionVoList1;
	private List<RtcUnionVO> rtcUnionVoList2;
	
	
	
	
	public Long getOnlineCount() {
		return onlineCount;
	}
	public void setOnlineCount(Long onlineCount) {
		this.onlineCount = onlineCount;
	}
	public Long getTodayOnlineCount() {
		return todayOnlineCount;
	}
	public void setTodayOnlineCount(Long todayOnlineCount) {
		this.todayOnlineCount = todayOnlineCount;
	}
	public Long getTodayTotalCount() {
		return todayTotalCount;
	}
	public void setTodayTotalCount(Long todayTotalCount) {
		this.todayTotalCount = todayTotalCount;
	}
	public Long getTodayTabCount() {
		return todayTabCount;
	}
	public void setTodayTabCount(Long todayTabCount) {
		this.todayTabCount = todayTabCount;
	}
	public Long getTodayWebCount() {
		return todayWebCount;
	}
	public void setTodayWebCount(Long todayWebCount) {
		this.todayWebCount = todayWebCount;
	}
	public Long getZoneId() {
		return zoneId;
	}
	public void setZoneId(Long zoneId) {
		this.zoneId = zoneId;
	}
	public String getZoneName() {
		return zoneName;
	}
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
	public List<RtcUnionVO> getRtcUnionVoList1() {
		return rtcUnionVoList1;
	}
	public void setRtcUnionVoList1(List<RtcUnionVO> rtcUnionVoList1) {
		this.rtcUnionVoList1 = rtcUnionVoList1;
	}
	public List<RtcUnionVO> getRtcUnionVoList2() {
		return rtcUnionVoList2;
	}
	public void setRtcUnionVoList2(List<RtcUnionVO> rtcUnionVoList2) {
		this.rtcUnionVoList2 = rtcUnionVoList2;
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
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getTabCount() {
		return tabCount;
	}
	public void setTabCount(Long tabCount) {
		this.tabCount = tabCount;
	}
	public Long getWebCount() {
		return webCount;
	}
	public void setWebCount(Long webCount) {
		this.webCount = webCount;
	}
	
	
}
