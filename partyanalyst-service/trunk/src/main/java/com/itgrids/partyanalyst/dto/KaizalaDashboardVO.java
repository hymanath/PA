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
	private String notSmartPhonePerc;
	private String parlName;
	private Long cadreInstalledCount = 0L;
	private Long publicInstalledCount = 0L;
	private Long cadreNoSmartPhoneCount = 0L;
	private Long publicNoSmartPhoneCount = 0L;
	
	private Long overAllInstalledCount = 0L;
	private Long overAllNoSmartPhoneCount = 0L;
	private Long committeeInstalled = 0L;
	private Long committeeNoSmartPhone = 0L;
	private String committeeInstalPerc;
	private String committeeNoSmartPerc;
	private String cadreInstallPerc;
	private String cadreNoSmartPerc;
	private String publicInstallPerc;
	private String publicNoSmartPerc;
	
	private List<KaizalaDashboardVO> subList = new ArrayList<KaizalaDashboardVO>(0);
	
	
	public Long getOverAllInstalledCount() {
		return overAllInstalledCount;
	}
	public void setOverAllInstalledCount(Long overAllInstalledCount) {
		this.overAllInstalledCount = overAllInstalledCount;
	}
	public Long getOverAllNoSmartPhoneCount() {
		return overAllNoSmartPhoneCount;
	}
	public void setOverAllNoSmartPhoneCount(Long overAllNoSmartPhoneCount) {
		this.overAllNoSmartPhoneCount = overAllNoSmartPhoneCount;
	}
	public Long getCommitteeInstalled() {
		return committeeInstalled;
	}
	public void setCommitteeInstalled(Long committeeInstalled) {
		this.committeeInstalled = committeeInstalled;
	}
	public Long getCommitteeNoSmartPhone() {
		return committeeNoSmartPhone;
	}
	public void setCommitteeNoSmartPhone(Long committeeNoSmartPhone) {
		this.committeeNoSmartPhone = committeeNoSmartPhone;
	}
	public String getCommitteeInstalPerc() {
		return committeeInstalPerc;
	}
	public void setCommitteeInstalPerc(String committeeInstalPerc) {
		this.committeeInstalPerc = committeeInstalPerc;
	}
	public String getCommitteeNoSmartPerc() {
		return committeeNoSmartPerc;
	}
	public void setCommitteeNoSmartPerc(String committeeNoSmartPerc) {
		this.committeeNoSmartPerc = committeeNoSmartPerc;
	}
	public String getCadreInstallPerc() {
		return cadreInstallPerc;
	}
	public void setCadreInstallPerc(String cadreInstallPerc) {
		this.cadreInstallPerc = cadreInstallPerc;
	}
	public String getCadreNoSmartPerc() {
		return cadreNoSmartPerc;
	}
	public void setCadreNoSmartPerc(String cadreNoSmartPerc) {
		this.cadreNoSmartPerc = cadreNoSmartPerc;
	}
	public String getPublicInstallPerc() {
		return publicInstallPerc;
	}
	public void setPublicInstallPerc(String publicInstallPerc) {
		this.publicInstallPerc = publicInstallPerc;
	}
	public String getPublicNoSmartPerc() {
		return publicNoSmartPerc;
	}
	public void setPublicNoSmartPerc(String publicNoSmartPerc) {
		this.publicNoSmartPerc = publicNoSmartPerc;
	}
	public Long getCadreInstalledCount() {
		return cadreInstalledCount;
	}
	public void setCadreInstalledCount(Long cadreInstalledCount) {
		this.cadreInstalledCount = cadreInstalledCount;
	}
	public Long getPublicInstalledCount() {
		return publicInstalledCount;
	}
	public void setPublicInstalledCount(Long publicInstalledCount) {
		this.publicInstalledCount = publicInstalledCount;
	}
	public Long getCadreNoSmartPhoneCount() {
		return cadreNoSmartPhoneCount;
	}
	public void setCadreNoSmartPhoneCount(Long cadreNoSmartPhoneCount) {
		this.cadreNoSmartPhoneCount = cadreNoSmartPhoneCount;
	}
	public Long getPublicNoSmartPhoneCount() {
		return publicNoSmartPhoneCount;
	}
	public void setPublicNoSmartPhoneCount(Long publicNoSmartPhoneCount) {
		this.publicNoSmartPhoneCount = publicNoSmartPhoneCount;
	}
	public String getParlName() {
		return parlName;
	}
	public void setParlName(String parlName) {
		this.parlName = parlName;
	}
	public String getNotSmartPhonePerc() {
		return notSmartPhonePerc;
	}
	public void setNotSmartPhonePerc(String notSmartPhonePerc) {
		this.notSmartPhonePerc = notSmartPhonePerc;
	}
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
