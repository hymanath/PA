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
	private Long assignedCount =0l;
	private Long notAssignedCount =0l;
	private Long maleCnt =0l;
	private Long femaleCnt=0l;
	private Long totalBooths=0l;
	private String boothInchargesAssnd;
	private Set<Long> boothIds = new HashSet<Long>();
	private Double assignedPerc;
	private Double nonAssnedPerc;
	
	
	public Double getAssignedPerc() {
		return assignedPerc;
	}
	public void setAssignedPerc(Double assignedPerc) {
		this.assignedPerc = assignedPerc;
	}
	public Double getNonAssnedPerc() {
		return nonAssnedPerc;
	}
	public void setNonAssnedPerc(Double nonAssnedPerc) {
		this.nonAssnedPerc = nonAssnedPerc;
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
	public Long getAssignedCount() {
		return assignedCount;
	}
	public void setAssignedCount(Long assignedCount) {
		this.assignedCount = assignedCount;
	}
	public Long getNotAssignedCount() {
		return notAssignedCount;
	}
	public void setNotAssignedCount(Long notAssignedCount) {
		this.notAssignedCount = notAssignedCount;
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
