package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class CadreIVRVO {
	
	private Long id;
	private String name;
	private String mobileNo;
	private String responseKey;
	private Long constituencyId;
	private String constituencyName;
	private Long total = 0l;
	private Long tgtotal = 0l;
	private Long received = 0l;
	private Long notReceived = 0l;
	private Long notRegistered = 0l;
	private Long responseCnt = 0l;
	private String currentStatus;
	private String strDate;
	private String endDate;
	private Long locationId;
	private String locationName;
	private Long panchayatId;
	private String panchayatName;
	private Long localbody;
	private String localbodyName;
	private List<CadreIVRVO> subList = new ArrayList<CadreIVRVO>();
	private Long apCount =0l;
	private Long tgCount = 0l;
	private Long printingCompleted = 0l;
	private Long ivrReady = 0l;
	private Long tgprintingCompleted = 0l;
	private Long tgivrReady = 0l;
	private Long tgReceived = 0l;
	private Long tgnotReceived = 0l;
	private Long tgnotRegistered = 0l;
	private Long tgResponseCnt = 0l;
	
	
	public Long getTgtotal() {
		return tgtotal;
	}
	public void setTgtotal(Long tgtotal) {
		this.tgtotal = tgtotal;
	}
	public Long getTgprintingCompleted() {
		return tgprintingCompleted;
	}
	public void setTgprintingCompleted(Long tgprintingCompleted) {
		this.tgprintingCompleted = tgprintingCompleted;
	}
	public Long getTgivrReady() {
		return tgivrReady;
	}
	public void setTgivrReady(Long tgivrReady) {
		this.tgivrReady = tgivrReady;
	}
	public Long getTgResponseCnt() {
		return tgResponseCnt;
	}
	public void setTgResponseCnt(Long tgResponseCnt) {
		this.tgResponseCnt = tgResponseCnt;
	}
	public Long getTgReceived() {
		return tgReceived;
	}
	public void setTgReceived(Long tgReceived) {
		this.tgReceived = tgReceived;
	}
	public Long getTgnotReceived() {
		return tgnotReceived;
	}
	public void setTgnotReceived(Long tgnotReceived) {
		this.tgnotReceived = tgnotReceived;
	}
	public Long getTgnotRegistered() {
		return tgnotRegistered;
	}
	public void setTgnotRegistered(Long tgnotRegistered) {
		this.tgnotRegistered = tgnotRegistered;
	}
	public Long getPrintingCompleted() {
		return printingCompleted;
	}
	public void setPrintingCompleted(Long printingCompleted) {
		this.printingCompleted = printingCompleted;
	}
	public Long getIvrReady() {
		return ivrReady;
	}
	public void setIvrReady(Long ivrReady) {
		this.ivrReady = ivrReady;
	}
	public Long getApCount() {
		return apCount;
	}
	public void setApCount(Long apCount) {
		this.apCount = apCount;
	}
	public Long getTgCount() {
		return tgCount;
	}
	public void setTgCount(Long tgCount) {
		this.tgCount = tgCount;
	}
	public Long getLocalbody() {
		return localbody;
	}
	public void setLocalbody(Long localbody) {
		this.localbody = localbody;
	}
	public String getLocalbodyName() {
		return localbodyName;
	}
	public void setLocalbodyName(String localbodyName) {
		this.localbodyName = localbodyName;
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
	public String getStrDate() {
		return strDate;
	}
	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public List<CadreIVRVO> getSubList() {
		return subList;
	}
	public void setSubList(List<CadreIVRVO> subList) {
		this.subList = subList;
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
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getResponseKey() {
		return responseKey;
	}
	public void setResponseKey(String responseKey) {
		this.responseKey = responseKey;
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
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Long getReceived() {
		return received;
	}
	public void setReceived(Long received) {
		this.received = received;
	}
	public Long getNotReceived() {
		return notReceived;
	}
	public void setNotReceived(Long notReceived) {
		this.notReceived = notReceived;
	}
	public Long getNotRegistered() {
		return notRegistered;
	}
	public void setNotRegistered(Long notRegistered) {
		this.notRegistered = notRegistered;
	}
	public String getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	public Long getResponseCnt() {
		return responseCnt;
	}
	public void setResponseCnt(Long responseCnt) {
		this.responseCnt = responseCnt;
	}
	
			
			
	

}
