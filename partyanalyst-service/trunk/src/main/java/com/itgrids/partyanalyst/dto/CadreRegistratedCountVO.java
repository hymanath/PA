package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

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
	private Double totalPercent ;
	private Double totalPercentToday;
	private Long APTotalCount = 0l;;
	private Long TSTotalCount = 0l;
	private Long APTotalCountToday = 0l;
	private Long TSTotalCountToday = 0l;
	private Long totalContStartedAp = 0l;
	private Long totalConstStartedTs = 0l;
	private Long inField = 0l;
	private String sourceName;
	private Double inFieldPercent;
	
	private Long constStartedCount = 0l;
	private Double constStartedCountPer;
	
	private Long totalSubmittedToday = 0l;
	
	private Long target = 0l;
	private Long id = 0l;
	private Long target2014 = 0l;
	private Long target2016 = 0l;
	private Long totalCount2014 = 0l;
	private Double cadreCountPer2014 ;
	private Long todayStartedConsttuncyCnt=0l;
	private Long todayNotStartedConsttuncyCnt=0l;
	private List<Long> locationIdsList;
	
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
	
	public Double getTotalPercent() {
		return totalPercent;
	}

	public void setTotalPercent(Double totalPercent) {
		this.totalPercent = totalPercent;
	}

	public Double getTotalPercentToday() {
		return totalPercentToday;
	}

	public void setTotalPercentToday(Double totalPercentToday) {
		this.totalPercentToday = totalPercentToday;
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
	
	public Long getTotalSubmittedToday() {
		return totalSubmittedToday;
	}

	public void setTotalSubmittedToday(Long totalSubmittedToday) {
		this.totalSubmittedToday = totalSubmittedToday;
	}

	public Long getTarget() {
		return target;
	}

	public void setTarget(Long target) {
		this.target = target;
	}

	public Long getConstStartedCount() {
		return constStartedCount;
	}

	public void setConstStartedCount(Long constStartedCount) {
		this.constStartedCount = constStartedCount;
	}

	public Double getConstStartedCountPer() {
		return constStartedCountPer;
	}

	public void setConstStartedCountPer(Double constStartedCountPer) {
		this.constStartedCountPer = constStartedCountPer;
	}

	public Long getInField() {
		return inField;
	}

	public void setInField(Long inField) {
		this.inField = inField;
	}

	public Double getInFieldPercent() {
		return inFieldPercent;
	}

	public void setInFieldPercent(Double inFieldPercent) {
		this.inFieldPercent = inFieldPercent;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTarget2014() {
		return target2014;
	}

	public void setTarget2014(Long target2014) {
		this.target2014 = target2014;
	}

	public Long getTarget2016() {
		return target2016;
	}

	public void setTarget2016(Long target2016) {
		this.target2016 = target2016;
	}

	public Long getTotalCount2014() {
		return totalCount2014;
	}
	public void setTotalCount2014(Long totalCount2014) {
		this.totalCount2014 = totalCount2014;
	}

	public Double getCadreCountPer2014() {
		return cadreCountPer2014;
	}

	public void setCadreCountPer2014(Double cadreCountPer2014) {
		this.cadreCountPer2014 = cadreCountPer2014;
	}

	public Long getTodayStartedConsttuncyCnt() {
		return todayStartedConsttuncyCnt;
	}

	public void setTodayStartedConsttuncyCnt(Long todayStartedConsttuncyCnt) {
		this.todayStartedConsttuncyCnt = todayStartedConsttuncyCnt;
	}

	public Long getTodayNotStartedConsttuncyCnt() {
		return todayNotStartedConsttuncyCnt;
	}

	public void setTodayNotStartedConsttuncyCnt(Long todayNotStartedConsttuncyCnt) {
		this.todayNotStartedConsttuncyCnt = todayNotStartedConsttuncyCnt;
	}

	public List<Long> getLocationIdsList() {
		return locationIdsList;
	}

	public void setLocationIdsList(List<Long> locationIdsList) {
		this.locationIdsList = locationIdsList;
	}	
}
