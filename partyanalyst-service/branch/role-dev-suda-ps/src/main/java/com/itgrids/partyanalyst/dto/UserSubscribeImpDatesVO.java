package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class UserSubscribeImpDatesVO implements Serializable{

	private String subscribeTitle;
	private List<ImportantDatesVO> userImpDates;
	public String getSubscribeTitle() {
		return subscribeTitle;
	}
	public void setSubscribeTitle(String subscribeTitle) {
		this.subscribeTitle = subscribeTitle;
	}
	public List<ImportantDatesVO> getUserImpDates() {
		return userImpDates;
	}
	public void setUserImpDates(List<ImportantDatesVO> userImpDates) {
		this.userImpDates = userImpDates;
	}
	
}
