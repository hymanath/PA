package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.CommitteeInputVO;
import com.itgrids.partyanalyst.model.PartyMeetingStatus;

public interface IPartyMeetingStatusDAO extends GenericDao<PartyMeetingStatus,Long> {

	public List<Object[]> getPartyMeetingCountLevelWise(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> partyMeetingsTypeValues);
	public List<Object[]> getPartyMeetingCountLocationWiseByUserAccess(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> partyMeetingsTypeValues);
	
	public List<Object[]> getLocationWiseMeetingsStatusCountByLocIds(CommitteeInputVO inputBO);
	public List<Object[]> getTopPoorMeetingLocations(CommitteeInputVO committeeBO);
}
