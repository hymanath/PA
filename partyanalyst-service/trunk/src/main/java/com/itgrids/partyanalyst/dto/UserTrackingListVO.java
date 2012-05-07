package com.itgrids.partyanalyst.dto;

import java.util.List;

public class UserTrackingListVO {
	private List<UserTrackingReportVO> userTrackingReportVOList;
	private Long totalRowCount;
	private List<AccessedPageLoginTimeVO> urlTimeVOList;
	
	public List<UserTrackingReportVO> getUserTrackingReportVOList() {
		return userTrackingReportVOList;
	}

	public void setUserTrackingReportVOList(
			List<UserTrackingReportVO> userTrackingReportVOList) {
		this.userTrackingReportVOList = userTrackingReportVOList;
	}

	public Long getTotalRowCount() {
		return totalRowCount;
	}

	public void setTotalRowCount(Long totalRowCount) {
		this.totalRowCount = totalRowCount;
	}

	public List<AccessedPageLoginTimeVO> getUrlTimeVOList() {
		return urlTimeVOList;
	}

	public void setUrlTimeVOList(List<AccessedPageLoginTimeVO> urlTimeVOList) {
		this.urlTimeVOList = urlTimeVOList;
	}	
}
