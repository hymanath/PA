package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class BoothInchargesVO implements Serializable {
	
	private Long boothId;
	private Long tdpCadreId;
	private String isActive;
	private String isDeleted;
	private Long boothInchargeEnrollmentId;
	private Long insertedBy;
	private Long updatedBy;
	private String insertedTime;
	private String updatedTime;
	private Long totalCount =0l;
	private Long maleCnt =0l;
	private Long femaleCnt=0l;
	private Long othersCnt=0L;
	private Long totalBooths=0l;
	private String boothInchargesAssnd;
	private Set<Long> boothIds = new HashSet<Long>();
	private Set<Long> tdpCadreIds = new HashSet<Long>();
	private Set<Long> maleTdpCadreIds = new HashSet<Long>();
	private Set<Long> femaleTdpCadreIds = new HashSet<Long>();
	private Set<Long> otherTdpCadreIds = new HashSet<Long>();
	
	private Long startedCount=0L;
	private Long completedCount=0L;
	private Long notStartedCount=0L;
	
	public Set<Long> getMaleTdpCadreIds() {
		return maleTdpCadreIds;
	}
	public void setMaleTdpCadreIds(Set<Long> maleTdpCadreIds) {
		this.maleTdpCadreIds = maleTdpCadreIds;
	}
	public Set<Long> getFemaleTdpCadreIds() {
		return femaleTdpCadreIds;
	}
	public void setFemaleTdpCadreIds(Set<Long> femaleTdpCadreIds) {
		this.femaleTdpCadreIds = femaleTdpCadreIds;
	}
	public Set<Long> getOtherTdpCadreIds() {
		return otherTdpCadreIds;
	}
	public void setOtherTdpCadreIds(Set<Long> otherTdpCadreIds) {
		this.otherTdpCadreIds = otherTdpCadreIds;
	}
	public Set<Long> getTdpCadreIds() {
		return tdpCadreIds;
	}
	public void setTdpCadreIds(Set<Long> tdpCadreIds) {
		this.tdpCadreIds = tdpCadreIds;
	}
	public Long getOthersCnt() {
		return othersCnt;
	}
	public void setOthersCnt(Long othersCnt) {
		this.othersCnt = othersCnt;
	}
	public Long getStartedCount() {
		return startedCount;
	}
	public void setStartedCount(Long startedCount) {
		this.startedCount = startedCount;
	}
	public Long getCompletedCount() {
		return completedCount;
	}
	public void setCompletedCount(Long completedCount) {
		this.completedCount = completedCount;
	}
	public Long getNotStartedCount() {
		return notStartedCount;
	}
	public void setNotStartedCount(Long notStartedCount) {
		this.notStartedCount = notStartedCount;
	}
	public Set<Long> getBoothIds() {
		return boothIds;
	}
	public void setBoothIds(Set<Long> boothIds) {
		this.boothIds = boothIds;
	}
	public String getBoothInchargesAssnd() {
		return boothInchargesAssnd;
	}
	public void setBoothInchargesAssnd(String boothInchargesAssnd) {
		this.boothInchargesAssnd = boothInchargesAssnd;
	}
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Long getBoothInchargeEnrollmentId() {
		return boothInchargeEnrollmentId;
	}
	public void setBoothInchargeEnrollmentId(Long boothInchargeEnrollmentId) {
		this.boothInchargeEnrollmentId = boothInchargeEnrollmentId;
	}
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(String insertedTime) {
		this.insertedTime = insertedTime;
	}
	public String getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
	public Long getMaleCnt() {
		return maleCnt;
	}
	public void setMaleCnt(Long maleCnt) {
		this.maleCnt = maleCnt;
	}
	public Long getFemaleCnt() {
		return femaleCnt;
	}
	public void setFemaleCnt(Long femaleCnt) {
		this.femaleCnt = femaleCnt;
	}
	public Long getTotalBooths() {
		return totalBooths;
	}
	public void setTotalBooths(Long totalBooths) {
		this.totalBooths = totalBooths;
	}
	
	
}
