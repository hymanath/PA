package com.itgrids.partyanalyst.meeting.service;

import java.io.File;
import java.util.List;

import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.KeyValueVO;
import com.itgrids.partyanalyst.dto.PartyMeetingVO;
import com.itgrids.partyanalyst.dto.PartyMeetingsVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.PartyMeeting;

public interface ICadrePartyMeetingManagementService {
	public List<KeyValueVO> getDistrictsBasedOnStateId(Long stateId);
	public List<KeyValueVO> getConstituencyBasedOnDistrictId(Long districtId);
	//public PartyMeeting saveMeetingDetails(final PartyMeetingVO inputvo);
	public String saveInvitiesDetails(final PartyMeetingVO partyMeetingVO,final File file,final Long partyMeetingId);
	
	public List<PartyMeetingsVO> getMeetingMainType();
	public List<PartyMeetingsVO> getMeetingSubType(Long partyMeetingMainTypeId);
	public List<PartyMeetingsVO> getCadrePartyMeetngDeatils(String startDateStr,String endDateStr,Long meetigLevelId,int startIndex,int maxIndex);
	public List<PartyMeetingsVO> getPartyMeetingLevels();
	public List<PartyMeetingsVO> getAllSessionType();
	public List<PartyMeetingsVO> getPartyMeetingsTabUserNameByDistrict();
	public List<PartyMeetingsVO> getPartyMeetingDeatilesForMeetingEditByMeetingId(Long meetingId);
	public List<PartyMeetingsVO>  getPartyMeetingSession(Long partyMeetingSessionId);
	public List<String> getInvitiesDetails(final File file);
	public List<IdAndNameVO> getTdpCadreDetailsForInveetMeeting(List<String> memberShipIds);
	 public PartyMeeting saveMeetingDetails(final PartyMeetingVO inputvo);
	 public List<PartyMeetingsVO> getPartyMeetingTabUserDetails(Long partyMeetingId);
	 public ResultStatus deletePartyMeetingDatails(Long meetingId);
	 public List<PartyMeetingVO> getSessionsDetailsByMeetingId(Long meetingId);
	 public IdAndNameVO getPartyMeetingInviteesDetailsAttendence(Long meetingId);
	

}
