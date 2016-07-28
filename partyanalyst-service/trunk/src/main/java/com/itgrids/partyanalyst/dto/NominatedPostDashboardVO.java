package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HYMAVATHI
 * @date July 15, 2016
 */
public class NominatedPostDashboardVO {

	private Long statusId;
	private String statusName;
	private Long statusCount = 0l;
	private List<NominatedPostDashboardVO>  applicatnStatsList= new ArrayList<NominatedPostDashboardVO>();
	private List<NominatedPostDashboardVO>  positinsList= new ArrayList<NominatedPostDashboardVO>();
	private List<NominatedPostDashboardVO>  nominatedStatusList= new ArrayList<NominatedPostDashboardVO>();
	
	
	
	public List<NominatedPostDashboardVO> getApplicatnStatsList() {
		return applicatnStatsList;
	}
	public void setApplicatnStatsList(
			List<NominatedPostDashboardVO> applicatnStatsList) {
		this.applicatnStatsList = applicatnStatsList;
	}
	public List<NominatedPostDashboardVO> getPositinsList() {
		return positinsList;
	}
	public void setPositinsList(List<NominatedPostDashboardVO> positinsList) {
		this.positinsList = positinsList;
	}
	public List<NominatedPostDashboardVO> getNominatedStatusList() {
		return nominatedStatusList;
	}
	public void setNominatedStatusList(
			List<NominatedPostDashboardVO> nominatedStatusList) {
		this.nominatedStatusList = nominatedStatusList;
	}
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public Long getStatusCount() {
		return statusCount;
	}
	public void setStatusCount(Long statusCount) {
		this.statusCount = statusCount;
	}
	
	
}
