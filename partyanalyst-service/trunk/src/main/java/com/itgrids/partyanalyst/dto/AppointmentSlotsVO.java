package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class AppointmentSlotsVO implements Serializable {
	private List<String> dateList;
	private Collection<List<List<String>>> listOfTimePairPerDate;
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
	

}
