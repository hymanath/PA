package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class SurveyDashBoardVO {
	
	private String locationName;
	private Long locationId;
	private Long count;
	
	private int startedCount;
	private int processingCount;
	private int completedCount;
	private int totalCount;
	private int notStartedCount;

	private List<SurveyDashBoardVO> completed = new ArrayList<SurveyDashBoardVO>();
	private List<SurveyDashBoardVO> process = new ArrayList<SurveyDashBoardVO>();
	private List<SurveyDashBoardVO> notStarted = new ArrayList<SurveyDashBoardVO>();
	private List<SurveyDashBoardVO> started = new ArrayList<SurveyDashBoardVO>();

	
	public List<SurveyDashBoardVO> getStarted() {
		return started;
	}
	public void setStarted(List<SurveyDashBoardVO> started) {
		this.started = started;
	}
	public List<SurveyDashBoardVO> getCompleted() {
		return completed;
	}
	public void setCompleted(List<SurveyDashBoardVO> completed) {
		this.completed = completed;
	}
	public List<SurveyDashBoardVO> getProcess() {
		return process;
	}
	public void setProcess(List<SurveyDashBoardVO> process) {
		this.process = process;
	}
	public List<SurveyDashBoardVO> getNotStarted() {
		return notStarted;
	}
	public void setNotStarted(List<SurveyDashBoardVO> notStarted) {
		this.notStarted = notStarted;
	}
	public int getStartedCount() {
		return startedCount;
	}
	public void setStartedCount(int startedCount) {
		this.startedCount = startedCount;
	}
	public int getProcessingCount() {
		return processingCount;
	}
	public void setProcessingCount(int processingCount) {
		this.processingCount = processingCount;
	}
	public int getCompletedCount() {
		return completedCount;
	}
	public void setCompletedCount(int completedCount) {
		this.completedCount = completedCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
	
	public int getNotStartedCount() {
		return notStartedCount;
	}
	public void setNotStartedCount(int notStartedCount) {
		this.notStartedCount = notStartedCount;
	}

}
