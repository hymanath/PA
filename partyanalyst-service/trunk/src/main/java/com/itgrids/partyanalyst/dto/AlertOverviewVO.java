package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class AlertOverviewVO {
	
	private Long id = 0L;
	private String name;
	private Long alertCount = 0l;
	
	private Long totalAlertCnt = 0l;
	private Long partyAlertCnt = 0l;
	private Long otherAlertCnt = 0l;
	private Long govtAlertCnt =0l;
	
	private Long statusTypeId;
	private String statusType;
	private Long statusCnt = 0l;
	
	private Long alertTypeId;
	private String alertType;
	private Long alertCnt = 0L;
	
	private Long editionId;
	private String edition;
	private Long editionCnt = 0L;
	
	private Double statusCntPer = 0.0d;
	private Double partyAlertCntPer = 0.0d;
	private Double otherAlertCntPer = 0.0d;
	private Double govtAlertCntPer = 0.0d;
	
	private AlertOverviewVO overAllVO;
	private List<AlertOverviewVO> statusList;
	private List<AlertOverviewVO> categoryList;
	private List<AlertOverviewVO> subList;
	private List<AlertOverviewVO> subList1;
	private List<AlertOverviewVO> editionList;
	private Set<Long> alertIdSet;
    private Long locationTypeId;
	private String locationType;
	
	private Long pendingCnt = 0l;
	private Long notifiedCnt = 0l;
	private Long actionInProgessCnt = 0l;
	private Long completedCnt = 0l;
	private Long unabletoResolveCnt = 0l;
	private Long actionNotRequiredCnt = 0l;
	private Long duplicatesStatusCnt = 0l;
	
	private List<AlertOverviewVO> totalEditionList;
	private List<AlertOverviewVO> totalPartyList;
	private List<AlertOverviewVO> totalGovtList;  
	private List<AlertOverviewVO> totalOtherList;
	
	
	
	public Long getGovtAlertCnt() {
		return govtAlertCnt;
	}
	public void setGovtAlertCnt(Long govtAlertCnt) {
		this.govtAlertCnt = govtAlertCnt;
	}
	public Double getGovtAlertCntPer() {
		return govtAlertCntPer;
	}
	public void setGovtAlertCntPer(Double govtAlertCntPer) {
		this.govtAlertCntPer = govtAlertCntPer;
	}
	public Long getDuplicatesStatusCnt() {
		return duplicatesStatusCnt;
	}
	public void setDuplicatesStatusCnt(Long duplicatesStatusCnt) {
		this.duplicatesStatusCnt = duplicatesStatusCnt;
	}
	private Double alertCountPer=0.0d;
	
	public Long getTotalAlertCnt() {
		return totalAlertCnt;
	}
	public void setTotalAlertCnt(Long totalAlertCnt) {
		this.totalAlertCnt = totalAlertCnt;
	}
	public Long getPartyAlertCnt() {
		return partyAlertCnt;
	}
	public void setPartyAlertCnt(Long partyAlertCnt) {
		this.partyAlertCnt = partyAlertCnt;
	}
	public Long getOtherAlertCnt() {
		return otherAlertCnt;
	}
	public void setOtherAlertCnt(Long otherAlertCnt) {
		this.otherAlertCnt = otherAlertCnt;
	}
	public Long getStatusTypeId() {
		return statusTypeId;
	}
	public void setStatusTypeId(Long statusTypeId) {
		this.statusTypeId = statusTypeId;
	}
	public String getStatusType() {
		return statusType;
	}
	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}
	public Long getStatusCnt() {
		return statusCnt;
	}
	public void setStatusCnt(Long statusCnt) {
		this.statusCnt = statusCnt;
	}
	public Double getStatusCntPer() {
		return statusCntPer;
	}
	public void setStatusCntPer(Double statusCntPer) {
		this.statusCntPer = statusCntPer;
	}
	public Double getPartyAlertCntPer() {
		return partyAlertCntPer;
	}
	public void setPartyAlertCntPer(Double partyAlertCntPer) {
		this.partyAlertCntPer = partyAlertCntPer;
	}
	public Double getOtherAlertCntPer() {
		return otherAlertCntPer;
	}
	public void setOtherAlertCntPer(Double otherAlertCntPer) {
		this.otherAlertCntPer = otherAlertCntPer;
	}
	public AlertOverviewVO getOverAllVO() {
		return overAllVO;
	}
	public void setOverAllVO(AlertOverviewVO overAllVO) {
		this.overAllVO = overAllVO;
	}
	public List<AlertOverviewVO> getStatusList() { 
		if(statusList == null){                               
	   	 statusList = new ArrayList<AlertOverviewVO>();   
        }                                                       
       return statusList;
     }

	public List<AlertOverviewVO> getCategoryList() {	
		if(categoryList == null){
			 categoryList = new ArrayList<AlertOverviewVO>();
		}
		return categoryList;
	}
	public List<AlertOverviewVO> getSubList() {
	if(subList == null){                               
	   	 subList = new CopyOnWriteArrayList<AlertOverviewVO>();  	                 
	 }                                                
      return subList;           
   }
   public Long getLocationTypeId() {		return locationTypeId;	}	public void setLocationTypeId(Long locationTypeId) {		this.locationTypeId = locationTypeId;	}	public String getLocationType() {		return locationType;	}	public void setLocationType(String locationType) {		this.locationType = locationType;	}
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
	public Long getAlertCount() {
		return alertCount;
	}
	public void setAlertCount(Long alertCount) {
		this.alertCount = alertCount;
	}
	public Long getPendingCnt() {
		return pendingCnt;
	}
	public void setPendingCnt(Long pendingCnt) {
		this.pendingCnt = pendingCnt;
	}
	public Long getNotifiedCnt() {
		return notifiedCnt;
	}
	public void setNotifiedCnt(Long notifiedCnt) {
		this.notifiedCnt = notifiedCnt;
	}
	public Long getActionInProgessCnt() {
		return actionInProgessCnt;
	}
	public void setActionInProgessCnt(Long actionInProgessCnt) {
		this.actionInProgessCnt = actionInProgessCnt;
	}
	public Long getCompletedCnt() {
		return completedCnt;
	}
	public void setCompletedCnt(Long completedCnt) {
		this.completedCnt = completedCnt;
	}
	public Long getUnabletoResolveCnt() {
		return unabletoResolveCnt;
	}
	public void setUnabletoResolveCnt(Long unabletoResolveCnt) {
		this.unabletoResolveCnt = unabletoResolveCnt;
	}
	public Long getActionNotRequiredCnt() {
		return actionNotRequiredCnt;
	}
	public void setActionNotRequiredCnt(Long actionNotRequiredCnt) {
		this.actionNotRequiredCnt = actionNotRequiredCnt;
	}
	public Double getAlertCountPer() {
		return alertCountPer;
	}
	public void setAlertCountPer(Double alertCountPer) {
		this.alertCountPer = alertCountPer;
	}
	public List<AlertOverviewVO> getSubList1() {
		return subList1;
	}
	public void setSubList1(List<AlertOverviewVO> subList1) {
		this.subList1 = subList1;
	}
	public Set<Long> getAlertIdSet() {
		if(alertIdSet == null){
			alertIdSet = new HashSet<Long>(0);
		}
		return alertIdSet;
	}
	public List<AlertOverviewVO> getTotalEditionList() {
		return totalEditionList;
	}
	public void setTotalEditionList(List<AlertOverviewVO> totalEditionList) {
		this.totalEditionList = totalEditionList;
	}
	public List<AlertOverviewVO> getTotalPartyList() {
		return totalPartyList;
	}
	public void setTotalPartyList(List<AlertOverviewVO> totalPartyList) {
		this.totalPartyList = totalPartyList;
	}
	public List<AlertOverviewVO> getTotalGovtList() {
		return totalGovtList;
	}
	public void setTotalGovtList(List<AlertOverviewVO> totalGovtList) {
		this.totalGovtList = totalGovtList;
	}
	public List<AlertOverviewVO> getTotalOtherList() {
		return totalOtherList;
	}
	public void setTotalOtherList(List<AlertOverviewVO> totalOtherList) {
		this.totalOtherList = totalOtherList;
	}
	public Long getAlertTypeId() {
		return alertTypeId;
	}
	public void setAlertTypeId(Long alertTypeId) {
		this.alertTypeId = alertTypeId;
	}
	public String getAlertType() {
		return alertType;
	}
	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}
	public Long getAlertCnt() {
		return alertCnt;
	}
	public void setAlertCnt(Long alertCnt) {
		this.alertCnt = alertCnt;
	}
	public Long getEditionId() {
		return editionId;
	}
	public void setEditionId(Long editionId) {
		this.editionId = editionId;
	}
	
	public String getEdition() {
		return edition;
	}
	public void setEdition(String edition) {
		this.edition = edition;
	}
	public Long getEditionCnt() {
		return editionCnt;
	}
	public void setEditionCnt(Long editionCnt) {
		this.editionCnt = editionCnt;
	}
	public List<AlertOverviewVO> getEditionList() {
		if(editionList == null){
			editionList = new ArrayList<AlertOverviewVO>(0);
		}
		return editionList;
	}
	
}                                                          