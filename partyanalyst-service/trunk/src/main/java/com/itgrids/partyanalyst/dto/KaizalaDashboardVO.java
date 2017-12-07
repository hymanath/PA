package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class KaizalaDashboardVO {

	private Long id;
	private String name;
	
	private Long districtId;
	private String districtName;
	private Long totalCount = 0L;
	private Long installed = 0L;
	private Long pending = 0L;
	private Long notHavingSmartPhone = 0L;
	private String installedPerc;
	private String pendingPerc;
	private String key;
	
	private List<KaizalaDashboardVO> subList = new ArrayList<KaizalaDashboardVO>(0);
	
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
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
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getInstalled() {
		return installed;
	}
	public void setInstalled(Long installed) {
		this.installed = installed;
	}
	public Long getPending() {
		return pending;
	}
	public void setPending(Long pending) {
		this.pending = pending;
	}
	public Long getNotHavingSmartPhone() {
		return notHavingSmartPhone;
	}
	public void setNotHavingSmartPhone(Long notHavingSmartPhone) {
		this.notHavingSmartPhone = notHavingSmartPhone;
	}
	public List<KaizalaDashboardVO> getSubList() {
		return subList;
	}
	public void setSubList(List<KaizalaDashboardVO> subList) {
		this.subList = subList;
	}
	public String getInstalledPerc() {
		return installedPerc;
	}
	public void setInstalledPerc(String installedPerc) {
		this.installedPerc = installedPerc;
	}
	public String getPendingPerc() {
		return pendingPerc;
	}
	public void setPendingPerc(String pendingPerc) {
		this.pendingPerc = pendingPerc;
	}
}
