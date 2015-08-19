package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class PartyMeetingInviteeVO implements Serializable{

	private static final long serialVersionUID = -8243209516131767029L;

	private Long partyMeetingId;
	private List<String> inviteeList;

	public Long getPartyMeetingId() {
		return partyMeetingId;
	}

	public void setPartyMeetingId(Long partyMeetingId) {
		this.partyMeetingId = partyMeetingId;
	}

	public List<String> getInviteeList() {
		return inviteeList;
	}

	public void setInviteeList(List<String> inviteeList) {
		this.inviteeList = inviteeList;
	}
	
	
}
