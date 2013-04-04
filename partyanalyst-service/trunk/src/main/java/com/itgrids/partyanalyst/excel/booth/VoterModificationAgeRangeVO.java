package com.itgrids.partyanalyst.excel.booth;

import java.io.Serializable;

public class VoterModificationAgeRangeVO implements Serializable{

	private static final long serialVersionUID = -2052313326620421530L;
	
	private String range;
	private Long addedCount = 0L;
	private Long deletedCount = 0L;
	private Long addedMale = 0L;
	private Long addedFemale = 0L;
	private Long deletedMale = 0L;
	private Long deletedFemale = 0L;
	private Long reportLevelValue;
	
	private Long movedCount = 0L;
	private Long movedMale = 0L;
	private Long movedFemale = 0L;
	private Long relocatedCount = 0L;
	private Long relocatedMale = 0L;
	private Long relocatedFemale = 0L;
	
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	public Long getAddedCount() {
		return addedCount;
	}
	public void setAddedCount(Long addedCount) {
		this.addedCount = addedCount;
	}
	public Long getDeletedCount() {
		return deletedCount;
	}
	public void setDeletedCount(Long deletedCount) {
		this.deletedCount = deletedCount;
	}
	public Long getAddedMale() {
		return addedMale;
	}
	public void setAddedMale(Long addedMale) {
		this.addedMale = addedMale;
	}
	public Long getAddedFemale() {
		return addedFemale;
	}
	public void setAddedFemale(Long addedFemale) {
		this.addedFemale = addedFemale;
	}
	public Long getDeletedMale() {
		return deletedMale;
	}
	public void setDeletedMale(Long deletedMale) {
		this.deletedMale = deletedMale;
	}
	public Long getDeletedFemale() {
		return deletedFemale;
	}
	public void setDeletedFemale(Long deletedFemale) {
		this.deletedFemale = deletedFemale;
	}
	public Long getReportLevelValue() {
		return reportLevelValue;
	}
	public void setReportLevelValue(Long reportLevelValue) {
		this.reportLevelValue = reportLevelValue;
	}
	public Long getMovedCount() {
		return movedCount;
	}
	public void setMovedCount(Long movedCount) {
		this.movedCount = movedCount;
	}
	public Long getMovedMale() {
		return movedMale;
	}
	public void setMovedMale(Long movedMale) {
		this.movedMale = movedMale;
	}
	public Long getMovedFemale() {
		return movedFemale;
	}
	public void setMovedFemale(Long movedFemale) {
		this.movedFemale = movedFemale;
	}
	public Long getRelocatedCount() {
		return relocatedCount;
	}
	public void setRelocatedCount(Long relocatedCount) {
		this.relocatedCount = relocatedCount;
	}
	public Long getRelocatedMale() {
		return relocatedMale;
	}
	public void setRelocatedMale(Long relocatedMale) {
		this.relocatedMale = relocatedMale;
	}
	public Long getRelocatedFemale() {
		return relocatedFemale;
	}
	public void setRelocatedFemale(Long relocatedFemale) {
		this.relocatedFemale = relocatedFemale;
	}
	

}
