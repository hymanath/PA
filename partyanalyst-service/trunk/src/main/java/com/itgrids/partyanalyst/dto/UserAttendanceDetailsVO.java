package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class UserAttendanceDetailsVO implements Serializable{

	private static final long serialVersionUID = 5494362465519173906L;
	
	private List<UserPartyMeetingVO> partyMeetings;
	private List<UserTrainingCampScheduleVO> trainingSchedules;

	public List<UserPartyMeetingVO> getPartyMeetings() {
		return partyMeetings;
	}

	public void setPartyMeetings(List<UserPartyMeetingVO> partyMeetings) {
		this.partyMeetings = partyMeetings;
	}

	public List<UserTrainingCampScheduleVO> getTrainingSchedules() {
		return trainingSchedules;
	}

	public void setTrainingSchedules(
			List<UserTrainingCampScheduleVO> trainingSchedules) {
		this.trainingSchedules = trainingSchedules;
	}
	
	
}
