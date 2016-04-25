package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class AppointmentSlotsVO implements Serializable {
	
	private List<String> dateList;
	private Collection<List<List<String>>> listOfTimePairPerDate;
	private String labelDate;
	
	private String startDate;
	private String endDate;
	private String date;
	
	public String getLabelDate() {
		return labelDate;
	}
	public void setLabelDate(String labelDate) {
		this.labelDate = labelDate;
	}
	public List<String> getDateList() {
		return dateList;
	}
	public void setDateList(List<String> dateList) {
		this.dateList = dateList;
	}
	public Collection<List<List<String>>> getListOfTimePairPerDate() {
		return listOfTimePairPerDate;
	}
	public void setListOfTimePairPerDate(
			Collection<List<List<String>>> listOfTimePairPerDate) {
		this.listOfTimePairPerDate = listOfTimePairPerDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
}
