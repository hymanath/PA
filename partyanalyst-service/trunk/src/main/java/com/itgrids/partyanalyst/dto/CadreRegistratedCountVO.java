package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class CadreRegistratedCountVO implements Serializable {
	private Long totalCount = 0l;
	private Long renewalCount = 0l;
	private Long newCount = 0l;
	private Long todayTotalCount;
	private Long todayRenewalCount;
	private Long todayNewCount;
	private String renewalPerCount;
	private String todayPercenCount;
	private String totalPercenCount;
	private String totalStartConstituPer;
	private Long totalStartConstituCount;
	private Long inFieldCount;
	private Long todaySubmittedCount;
	private String newPercCnt;
	private String todyNewPercCnt;
	private String todyRenewalPercCnt;
	private Long   todayFieldMembersCount;
	private Double totalPercentAP ;
	private Double totalPercentTS ;
	private Double totalPercentAPToday;
	private Double totalPercentTSToday;
	private Long APTotalCount = 0l;;
	private Long TSTotalCount = 0l;
	private Long APTotalCountToday = 0l;
	private Long TSTotalCountToday = 0l;
	private Long totalContStartedAp = 0l;
	private Long totalConstStartedTs = 0l;
	private Long inFieldAP = 0l;
	private Long inFieldTs = 0l;
	private Double inFieldApPercent;
	private Double inFieldTsPercent;
	private Long constStartedCountAp = 0l;
	private Double constStartedCountPerAp;
	private Long constStartedCountTs = 0l;
	private Double constStartedCountPerTs;
	private Long totalSubmittedTodayAp = 0l;
	private Long totalSubmittedTodayTs = 0l;
	
	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Long getRenewalCount() {
		return renewalCount;
	}

	public void setRenewalCount(Long renewalCount) {
		this.renewalCount = renewalCount;
	}

	public Long getNewCount() {
		return newCount;
	}

	public void setNewCount(Long newCount) {
		this.newCount = newCount;
	}

	public Long getTodayTotalCount() {
		return todayTotalCount;
	}

	public void setTodayTotalCount(Long todayTotalCount) {
		this.todayTotalCount = todayTotalCount;
	}

	public Long getTodayRenewalCount() {
		return todayRenewalCount;
	}

	public void setTodayRenewalCount(Long todayRenewalCount) {
		this.todayRenewalCount = todayRenewalCount;
	}

	public Long getTodayNewCount() {
		return todayNewCount;
	}

	public void setTodayNewCount(Long todayNewCount) {
		this.todayNewCount = todayNewCount;
	}

	public String getTodayPercenCount() {
		return todayPercenCount;
	}

	public void setTodayPercenCount(String todayPercenCount) {
		this.todayPercenCount = todayPercenCount;
	}

	public String getTotalPercenCount() {
		return totalPercenCount;
	}

	public void setTotalPercenCount(String totalPercenCount) {
		this.totalPercenCount = totalPercenCount;
	}

	public String getTotalStartConstituPer() {
		return totalStartConstituPer;
	}

	public void setTotalStartConstituPer(String totalStartConstituPer) {
		this.totalStartConstituPer = totalStartConstituPer;
	}

	public Long getTotalStartConstituCount() {
		return totalStartConstituCount;
	}

	public void setTotalStartConstituCount(Long totalStartConstituCount) {
		this.totalStartConstituCount = totalStartConstituCount;
	}

	public Long getInFieldCount() {
		return inFieldCount;
	}

	public void setInFieldCount(Long inFieldCount) {
		this.inFieldCount = inFieldCount;
	}

	public Long getTodaySubmittedCount() {
		return todaySubmittedCount;
	}

	public void setTodaySubmittedCount(Long todaySubmittedCount) {
		this.todaySubmittedCount = todaySubmittedCount;
	}

	public String getRenewalPerCount() {
		return renewalPerCount;
	}

	public void setRenewalPerCount(String renewalPerCount) {
		this.renewalPerCount = renewalPerCount;
	}

	public String getNewPercCnt() {
		return newPercCnt;
	}

	public void setNewPercCnt(String newPercCnt) {
		this.newPercCnt = newPercCnt;
	}

	public String getTodyNewPercCnt() {
		return todyNewPercCnt;
	}

	public void setTodyNewPercCnt(String todyNewPercCnt) {
		this.todyNewPercCnt = todyNewPercCnt;
	}

	public String getTodyRenewalPercCnt() {
		return todyRenewalPercCnt;
	}

	public void setTodyRenewalPercCnt(String todyRenewalPercCnt) {
		this.todyRenewalPercCnt = todyRenewalPercCnt;
	}

	public Long getTodayFieldMembersCount() {
		return todayFieldMembersCount;
	}

	public void setTodayFieldMembersCount(Long todayFieldMembersCount) {
		this.todayFieldMembersCount = todayFieldMembersCount;
	}

	public Double getTotalPercentAP() {
		return totalPercentAP;
	}

	public void setTotalPercentAP(Double totalPercentAP) {
		this.totalPercentAP = totalPercentAP;
	}

	public Double getTotalPercentTS() {
		return totalPercentTS;
	}

	public void setTotalPercentTS(Double totalPercentTS) {
		this.totalPercentTS = totalPercentTS;
	}

	public Double getTotalPercentAPToday() {
		return totalPercentAPToday;
	}

	public void setTotalPercentAPToday(Double totalPercentAPToday) {
		this.totalPercentAPToday = totalPercentAPToday;
	}

	public Double getTotalPercentTSToday() {
		return totalPercentTSToday;
	}

	public void setTotalPercentTSToday(Double totalPercentTSToday) {
		this.totalPercentTSToday = totalPercentTSToday;
	}

	public Long getAPTotalCount() {
		return APTotalCount;
	}

	public void setAPTotalCount(Long aPTotalCount) {
		APTotalCount = aPTotalCount;
	}

	public Long getTSTotalCount() {
		return TSTotalCount;
	}

	public void setTSTotalCount(Long tSTotalCount) {
		TSTotalCount = tSTotalCount;
	}

	public Long getAPTotalCountToday() {
		return APTotalCountToday;
	}

	public void setAPTotalCountToday(Long aPTotalCountToday) {
		APTotalCountToday = aPTotalCountToday;
	}

	public Long getTSTotalCountToday() {
		return TSTotalCountToday;
	}

	public void setTSTotalCountToday(Long tSTotalCountToday) {
		TSTotalCountToday = tSTotalCountToday;
	}

	public Long getTotalContStartedAp() {
		return totalContStartedAp;
	}

	public void setTotalContStartedAp(Long totalContStartedAp) {
		this.totalContStartedAp = totalContStartedAp;
	}

	public Long getTotalConstStartedTs() {
		return totalConstStartedTs;
	}

	public void setTotalConstStartedTs(Long totalConstStartedTs) {
		this.totalConstStartedTs = totalConstStartedTs;
	}

	public Long getInFieldAP() {
		return inFieldAP;
	}

	public void setInFieldAP(Long inFieldAP) {
		this.inFieldAP = inFieldAP;
	}

	public Long getInFieldTs() {
		return inFieldTs;
	}

	public void setInFieldTs(Long inFieldTs) {
		this.inFieldTs = inFieldTs;
	}

	public Long getTotalSubmittedTodayAp() {
		return totalSubmittedTodayAp;
	}

	public void setTotalSubmittedTodayAp(Long totalSubmittedTodayAp) {
		this.totalSubmittedTodayAp = totalSubmittedTodayAp;
	}

	public Long getTotalSubmittedTodayTs() {
		return totalSubmittedTodayTs;
	}

	public void setTotalSubmittedTodayTs(Long totalSubmittedTodayTs) {
		this.totalSubmittedTodayTs = totalSubmittedTodayTs;
	}

	public Double getInFieldApPercent() {
		return inFieldApPercent;
	}

	public void setInFieldApPercent(Double inFieldApPercent) {
		this.inFieldApPercent = inFieldApPercent;
	}

	public Double getInFieldTsPercent() {
		return inFieldTsPercent;
	}

	public void setInFieldTsPercent(Double inFieldTsPercent) {
		this.inFieldTsPercent = inFieldTsPercent;
	}

	public Long getConstStartedCountAp() {
		return constStartedCountAp;
	}

	public void setConstStartedCountAp(Long constStartedCountAp) {
		this.constStartedCountAp = constStartedCountAp;
	}

	public Double getConstStartedCountPerAp() {
		return constStartedCountPerAp;
	}

	public void setConstStartedCountPerAp(Double constStartedCountPerAp) {
		this.constStartedCountPerAp = constStartedCountPerAp;
	}

	public Long getConstStartedCountTs() {
		return constStartedCountTs;
	}

	public void setConstStartedCountTs(Long constStartedCountTs) {
		this.constStartedCountTs = constStartedCountTs;
	}

	public Double getConstStartedCountPerTs() {
		return constStartedCountPerTs;
	}

	public void setConstStartedCountPerTs(Double constStartedCountPerTs) {
		this.constStartedCountPerTs = constStartedCountPerTs;
	}
	
	
	

}
