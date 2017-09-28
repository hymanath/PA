package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class CommitteeBasicVO {
	
	private Long mainCommTotalCount = 0l;
	private Long mainCommStartedCount = 0l;
	private Long mainCommCompletedCount = 0l;
	private Long mainCommTotalMembers = 0l;
	
	private Long affiCommTotalCount = 0l;
	private Long affiCommStartedCount= 0l;
	private Long affiCommCompletedCount= 0l;
	private Long affiCommTotalMembers= 0l;
	
	
	private String locationType;
	private String areaType="";
	
	private Long mainCommNotYetStarted =0l;
	private Long mainCommVillageNotStarted = 0l;
	private Long affiCommMandalNotStarted = 0l;
	private Long affiCommVillageNotStarted = 0l;
	private Long mainMandalTotal = 0l;
	private Long mainVillageTotal = 0l;
	private Long affMandalTotal = 0l;
	private Long affVillageTotal = 0l;
	private String commiteeName;
	private double mainMandalCompletePer = 0.0;
	private double mainVillageCompletePer = 0.0;
	private double mainMandalStartPer = 0.0;
	private double mainVillageStartPer = 0.0;
	private double mainMandalNotPer = 0.0;
	private double mainVillageNotPer = 0.0;
	private double affMandalCompletePer = 0.0;
	private double affVillageCompletePer = 0.0;
	private double affMandalStartPer = 0.0;
	private double affVillageStartPer = 0.0;
	private double affMandalNotPer = 0.0;
	private double affVillageNotPer = 0.0;
	
	private long mainMandalCompletedCount = 0l;
	private long mainMandalStartedCount = 0l;
	private long mainvillageCompletedCount = 0l;
	private long mainVillageStartedCount = 0l;
	private long mainMandalNotYetStartedCount = 0l;
	private long mainVillageNotYetStartedCount = 0l;
	
	private long affliatedMandalCompletedCount = 0l;
	private long affliatedMandalStartedCount = 0l;
	private long affliatedVillageCompletedCount = 0l;
	private long affliatedVillageStartedCount = 0l;
	private List<CommitteeBasicVO> subList = new ArrayList<CommitteeBasicVO>();
	
	
	public long getMainMandalCompletedCount() {
		return mainMandalCompletedCount;
	}
	public void setMainMandalCompletedCount(long mainMandalCompletedCount) {
		this.mainMandalCompletedCount = mainMandalCompletedCount;
	}
	public long getMainMandalStartedCount() {
		return mainMandalStartedCount;
	}
	public void setMainMandalStartedCount(long mainMandalStartedCount) {
		this.mainMandalStartedCount = mainMandalStartedCount;
	}
	public long getMainvillageCompletedCount() {
		return mainvillageCompletedCount;
	}
	public void setMainvillageCompletedCount(long mainvillageCompletedCount) {
		this.mainvillageCompletedCount = mainvillageCompletedCount;
	}
	public long getMainVillageStartedCount() {
		return mainVillageStartedCount;
	}
	public void setMainVillageStartedCount(long mainVillageStartedCount) {
		this.mainVillageStartedCount = mainVillageStartedCount;
	}
	public long getMainMandalNotYetStartedCount() {
		return mainMandalNotYetStartedCount;
	}
	public void setMainMandalNotYetStartedCount(long mainMandalNotYetStartedCount) {
		this.mainMandalNotYetStartedCount = mainMandalNotYetStartedCount;
	}
	public long getMainVillageNotYetStartedCount() {
		return mainVillageNotYetStartedCount;
	}
	public void setMainVillageNotYetStartedCount(long mainVillageNotYetStartedCount) {
		this.mainVillageNotYetStartedCount = mainVillageNotYetStartedCount;
	}
	public long getAffliatedMandalCompletedCount() {
		return affliatedMandalCompletedCount;
	}
	public void setAffliatedMandalCompletedCount(long affliatedMandalCompletedCount) {
		this.affliatedMandalCompletedCount = affliatedMandalCompletedCount;
	}
	public long getAffliatedMandalStartedCount() {
		return affliatedMandalStartedCount;
	}
	public void setAffliatedMandalStartedCount(long affliatedMandalStartedCount) {
		this.affliatedMandalStartedCount = affliatedMandalStartedCount;
	}
	public long getAffliatedVillageCompletedCount() {
		return affliatedVillageCompletedCount;
	}
	public void setAffliatedVillageCompletedCount(long affliatedVillageCompletedCount) {
		this.affliatedVillageCompletedCount = affliatedVillageCompletedCount;
	}
	public long getAffliatedVillageStartedCount() {
		return affliatedVillageStartedCount;
	}
	public void setAffliatedVillageStartedCount(long affliatedVillageStartedCount) {
		this.affliatedVillageStartedCount = affliatedVillageStartedCount;
	}
	public double getMainMandalNotPer() {
		return mainMandalNotPer;
	}
	public void setMainMandalNotPer(double mainMandalNotPer) {
		this.mainMandalNotPer = mainMandalNotPer;
	}
	public double getMainVillageNotPer() {
		return mainVillageNotPer;
	}
	public void setMainVillageNotPer(double mainVillageNotPer) {
		this.mainVillageNotPer = mainVillageNotPer;
	}
	public double getAffMandalNotPer() {
		return affMandalNotPer;
	}
	public void setAffMandalNotPer(double affMandalNotPer) {
		this.affMandalNotPer = affMandalNotPer;
	}
	public double getAffVillageNotPer() {
		return affVillageNotPer;
	}
	public void setAffVillageNotPer(double affVillageNotPer) {
		this.affVillageNotPer = affVillageNotPer;
	}
	public double getMainMandalCompletePer() {
		return mainMandalCompletePer;
	}
	public void setMainMandalCompletePer(double mainMandalCompletePer) {
		this.mainMandalCompletePer = mainMandalCompletePer;
	}
	public double getMainVillageCompletePer() {
		return mainVillageCompletePer;
	}
	public void setMainVillageCompletePer(double mainVillageCompletePer) {
		this.mainVillageCompletePer = mainVillageCompletePer;
	}
	public double getMainMandalStartPer() {
		return mainMandalStartPer;
	}
	public void setMainMandalStartPer(double mainMandalStartPer) {
		this.mainMandalStartPer = mainMandalStartPer;
	}
	public double getMainVillageStartPer() {
		return mainVillageStartPer;
	}
	public void setMainVillageStartPer(double mainVillageStartPer) {
		this.mainVillageStartPer = mainVillageStartPer;
	}
	public double getAffMandalCompletePer() {
		return affMandalCompletePer;
	}
	public void setAffMandalCompletePer(double affMandalCompletePer) {
		this.affMandalCompletePer = affMandalCompletePer;
	}
	public double getAffVillageCompletePer() {
		return affVillageCompletePer;
	}
	public void setAffVillageCompletePer(double affVillageCompletePer) {
		this.affVillageCompletePer = affVillageCompletePer;
	}
	public double getAffMandalStartPer() {
		return affMandalStartPer;
	}
	public void setAffMandalStartPer(double affMandalStartPer) {
		this.affMandalStartPer = affMandalStartPer;
	}
	public double getAffVillageStartPer() {
		return affVillageStartPer;
	}
	public void setAffVillageStartPer(double affVillageStartPer) {
		this.affVillageStartPer = affVillageStartPer;
	}
	public Long getMainCommNotYetStarted() {
		return mainCommNotYetStarted;
	}
	public void setMainCommNotYetStarted(Long mainCommNotYetStarted) {
		this.mainCommNotYetStarted = mainCommNotYetStarted;
	}
	public String getCommiteeName() {
		return commiteeName;
	}
	public void setCommiteeName(String commiteeName) {
		this.commiteeName = commiteeName;
	}
	public Long getMainCommTotalCount() {
		return mainCommTotalCount;
	}
	public void setMainCommTotalCount(Long mainCommTotalCount) {
		this.mainCommTotalCount = mainCommTotalCount;
	}
	public Long getMainCommStartedCount() {
		return mainCommStartedCount;
	}
	public void setMainCommStartedCount(Long mainCommStartedCount) {
		this.mainCommStartedCount = mainCommStartedCount;
	}
	public Long getMainCommCompletedCount() {
		return mainCommCompletedCount;
	}
	public void setMainCommCompletedCount(Long mainCommCompletedCount) {
		this.mainCommCompletedCount = mainCommCompletedCount;
	}
	public Long getMainCommTotalMembers() {
		return mainCommTotalMembers;
	}
	public void setMainCommTotalMembers(Long mainCommTotalMembers) {
		this.mainCommTotalMembers = mainCommTotalMembers;
	}
	public Long getAffiCommTotalCount() {
		return affiCommTotalCount;
	}
	public void setAffiCommTotalCount(Long affiCommTotalCount) {
		this.affiCommTotalCount = affiCommTotalCount;
	}
	public Long getAffiCommStartedCount() {
		return affiCommStartedCount;
	}
	public void setAffiCommStartedCount(Long affiCommStartedCount) {
		this.affiCommStartedCount = affiCommStartedCount;
	}
	public Long getAffiCommCompletedCount() {
		return affiCommCompletedCount;
	}
	public void setAffiCommCompletedCount(Long affiCommCompletedCount) {
		this.affiCommCompletedCount = affiCommCompletedCount;
	}
	public Long getAffiCommTotalMembers() {
		return affiCommTotalMembers;
	}
	public void setAffiCommTotalMembers(Long affiCommTotalMembers) {
		this.affiCommTotalMembers = affiCommTotalMembers;
	}
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	public String getAreaType() {
		return areaType;
	}
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	public Long getMainCommVillageNotStarted() {
		return mainCommVillageNotStarted;
	}
	public void setMainCommVillageNotStarted(Long mainCommVillageNotStarted) {
		this.mainCommVillageNotStarted = mainCommVillageNotStarted;
	}
	public Long getAffiCommMandalNotStarted() {
		return affiCommMandalNotStarted;
	}
	public void setAffiCommMandalNotStarted(Long affiCommMandalNotStarted) {
		this.affiCommMandalNotStarted = affiCommMandalNotStarted;
	}
	public Long getAffiCommVillageNotStarted() {
		return affiCommVillageNotStarted;
	}
	public void setAffiCommVillageNotStarted(Long affiCommVillageNotStarted) {
		this.affiCommVillageNotStarted = affiCommVillageNotStarted;
	}
	public Long getMainMandalTotal() {
		return mainMandalTotal;
	}
	public void setMainMandalTotal(Long mainMandalTotal) {
		this.mainMandalTotal = mainMandalTotal;
	}
	public Long getMainVillageTotal() {
		return mainVillageTotal;
	}
	public void setMainVillageTotal(Long mainVillageTotal) {
		this.mainVillageTotal = mainVillageTotal;
	}
	public Long getAffMandalTotal() {
		return affMandalTotal;
	}
	public void setAffMandalTotal(Long affMandalTotal) {
		this.affMandalTotal = affMandalTotal;
	}
	public Long getAffVillageTotal() {
		return affVillageTotal;
	}
	public void setAffVillageTotal(Long affVillageTotal) {
		this.affVillageTotal = affVillageTotal;
	}
	public List<CommitteeBasicVO> getSubList() {
		return subList;
	}
	public void setSubList(List<CommitteeBasicVO> subList) {
		this.subList = subList;
	}
	
	
}
