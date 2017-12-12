package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.MomDashbaordOverViewDtlsVO;
import com.itgrids.partyanalyst.dto.MomDetailsVO;
import com.itgrids.partyanalyst.dto.PartyMeetingMOMCreationDtlsvO;
import com.itgrids.partyanalyst.dto.PartyMeetingMOMDtlsVO;
import com.itgrids.partyanalyst.dto.PartyMeetingMOMPointsDtlsVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

public interface IPartyMeetingMOMService {

	public PartyMeetingMOMDtlsVO getPartyMeetingMOMDetails(Long userAccessLevel,List<Long> accessValue,String monthYear);
	public PartyMeetingMOMPointsDtlsVO getPartyMeetingMOMPointsDocumentDetails(Long userAccessLevel,List<Long> accessValues,String monthYear,Long parytMeetingId);
	public String updateMOMMeetingDetails(Long meetingId, String conductedDate,String isConducted,String remarks,Long loginUserId);
	public String deleteMOMMeetingDetails(Long id, String deletedType,Long loginUserId);
	public ResultStatus savePartyMeetingMOMDetails(PartyMeetingMOMCreationDtlsvO inputVO);
	public ResultStatus updateMomDetails(PartyMeetingMOMCreationDtlsvO inputVO);
	public MomDetailsVO getMomCompletedDetails(Long partyMeetingMOMId);
	public MomDashbaordOverViewDtlsVO getMomDashboardOverviewDtls(Long userAccessLevel,List<Long> accessValues,String monthYear);
	public List<MomDetailsVO> getMomDetailsBySelectedType(Long userAccessLevel,List<Long> accessValues,String monthYear,String type);
}
