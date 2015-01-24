package com.itgrids.partyanalyst.dto;

import java.util.List;

public class CadreIVRResponseVO {
   
	private List<CadreIVRResponseVO> apList;
	private List<CadreIVRResponseVO> tgList;
	
	private String name;
	private Long id;
	private String locationName;
	private String areaName;
	private Long totalCalls =0l;
	private Long totalCallsPerc;
	private Long received = 0l;
	private Long receivedPerc;
	private Long notReceived =0l;
	private Long notReceivedPerc;
	private Long notMember =0l;
	private Long notMemberPerc;
	private Long registeredCount;
	private Long printedCount = 0l;
	private String jobCode;
	private Long totalIvrCalls;
	private Long totalAnswerdCalls;
	private Long totalAnswerdPerc;
	private Long errorCalls;
	private Long errorCallsPerc;
	private Long wrongOptionSel;
	private Long wrongOptionSelPerc;
	private Long noOptionSel;
	private Long noOptionSelPerc;
	private String date;
	private Long ivrEnqReceived;
	private Long ivrEnqDelivered;
	private Long localbodyPrintedCnt;
	private Long mandalPrintedCnt;
	private Long localbodyReceivedCount;
	
	private Double constiReceivedPerc;
	private Double constiDeliveredPerc;
	private Double urbanPerc;
	private Double mandalPerc;
	private Double deliveredPerc;
	
	
	
	public List<CadreIVRResponseVO> getApList() {
		return apList;
	}
	
	public void setApList(List<CadreIVRResponseVO> apList) {
		this.apList = apList;
	}
	
	public List<CadreIVRResponseVO> getTgList() {
		return tgList;
	}
	
	public void setTgList(List<CadreIVRResponseVO> tgList) {
		this.tgList = tgList;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLocationName() {
		return locationName;
	}
	
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	
	public String getAreaName() {
		return areaName;
	}
	
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	public Long getTotalCalls() {
		return totalCalls;
	}
	
	public void setTotalCalls(Long totalCalls) {
		this.totalCalls = totalCalls;
	}
	
	public Long getTotalCallsPerc() {
		return totalCallsPerc;
	}
	
	public void setTotalCallsPerc(Long totalCallsPerc) {
		this.totalCallsPerc = totalCallsPerc;
	}
	
	public Long getReceived() {
		return received;
	}
	
	public void setReceived(Long received) {
		this.received = received;
	}
	
	public Long getReceivedPerc() {
		return receivedPerc;
	}
	
	public void setReceivedPerc(Long receivedPerc) {
		this.receivedPerc = receivedPerc;
	}
	
	public Long getNotReceived() {
		return notReceived;
	}
	
	public void setNotReceived(Long notReceived) {
		this.notReceived = notReceived;
	}
	
	public Long getNotReceivedPerc() {
		return notReceivedPerc;
	}
	
	public void setNotReceivedPerc(Long notReceivedPerc) {
		this.notReceivedPerc = notReceivedPerc;
	}
	
	public Long getNotMember() {
		return notMember;
	}
	
	public void setNotMember(Long notMember) {
		this.notMember = notMember;
	}
	
	public Long getNotMemberPerc() {
		return notMemberPerc;
	}
	
	public void setNotMemberPerc(Long notMemberPerc) {
		this.notMemberPerc = notMemberPerc;
	}

	public Long getRegisteredCount() {
		return registeredCount;
	}

	public void setRegisteredCount(Long registeredCount) {
		this.registeredCount = registeredCount;
	}

	public Long getPrintedCount() {
		return printedCount;
	}

	public void setPrintedCount(Long printedCount) {
		this.printedCount = printedCount;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public Long getTotalIvrCalls() {
		return totalIvrCalls;
	}

	public void setTotalIvrCalls(Long totalIvrCalls) {
		this.totalIvrCalls = totalIvrCalls;
	}

	public Long getTotalAnswerdCalls() {
		return totalAnswerdCalls;
	}

	public void setTotalAnswerdCalls(Long totalAnswerdCalls) {
		this.totalAnswerdCalls = totalAnswerdCalls;
	}

	public Long getTotalAnswerdPerc() {
		return totalAnswerdPerc;
	}

	public void setTotalAnswerdPerc(Long totalAnswerdPerc) {
		this.totalAnswerdPerc = totalAnswerdPerc;
	}

	public Long getErrorCalls() {
		return errorCalls;
	}

	public void setErrorCalls(Long errorCalls) {
		this.errorCalls = errorCalls;
	}

	public Long getErrorCallsPerc() {
		return errorCallsPerc;
	}

	public void setErrorCallsPerc(Long errorCallsPerc) {
		this.errorCallsPerc = errorCallsPerc;
	}

	public Long getWrongOptionSel() {
		return wrongOptionSel;
	}

	public void setWrongOptionSel(Long wrongOptionSel) {
		this.wrongOptionSel = wrongOptionSel;
	}

	public Long getWrongOptionSelPerc() {
		return wrongOptionSelPerc;
	}

	public void setWrongOptionSelPerc(Long wrongOptionSelPerc) {
		this.wrongOptionSelPerc = wrongOptionSelPerc;
	}

	public Long getNoOptionSel() {
		return noOptionSel;
	}

	public void setNoOptionSel(Long noOptionSel) {
		this.noOptionSel = noOptionSel;
	}

	public Long getNoOptionSelPerc() {
		return noOptionSelPerc;
	}

	public void setNoOptionSelPerc(Long noOptionSelPerc) {
		this.noOptionSelPerc = noOptionSelPerc;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Long getIvrEnqReceived() {
		return ivrEnqReceived;
	}

	public void setIvrEnqReceived(Long ivrEnqReceived) {
		this.ivrEnqReceived = ivrEnqReceived;
	}

	public Long getIvrEnqDelivered() {
		return ivrEnqDelivered;
	}

	public void setIvrEnqDelivered(Long ivrEnqDelivered) {
		this.ivrEnqDelivered = ivrEnqDelivered;
	}

	public Long getMandalPrintedCnt() {
		return mandalPrintedCnt;
	}

	public void setMandalPrintedCnt(Long mandalPrintedCnt) {
		this.mandalPrintedCnt = mandalPrintedCnt;
	}

	public Long getLocalbodyPrintedCnt() {
		return localbodyPrintedCnt;
	}

	public void setLocalbodyPrintedCnt(Long localbodyPrintedCnt) {
		this.localbodyPrintedCnt = localbodyPrintedCnt;
	}

	public Long getLocalbodyReceivedCount() {
		return localbodyReceivedCount;
	}

	public void setLocalbodyReceivedCount(Long localbodyReceivedCount) {
		this.localbodyReceivedCount = localbodyReceivedCount;
	}

	public Double getConstiReceivedPerc() {
		return constiReceivedPerc;
	}

	public void setConstiReceivedPerc(Double constiReceivedPerc) {
		this.constiReceivedPerc = constiReceivedPerc;
	}

	public Double getConstiDeliveredPerc() {
		return constiDeliveredPerc;
	}

	public void setConstiDeliveredPerc(Double constiDeliveredPerc) {
		this.constiDeliveredPerc = constiDeliveredPerc;
	}

	public Double getUrbanPerc() {
		return urbanPerc;
	}

	public void setUrbanPerc(Double urbanPerc) {
		this.urbanPerc = urbanPerc;
	}

	public Double getMandalPerc() {
		return mandalPerc;
	}

	public void setMandalPerc(Double mandalPerc) {
		this.mandalPerc = mandalPerc;
	}

	public Double getDeliveredPerc() {
		return deliveredPerc;
	}

	public void setDeliveredPerc(Double deliveredPerc) {
		this.deliveredPerc = deliveredPerc;
	}

	
	
	
}
