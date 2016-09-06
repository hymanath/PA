package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PartyMeetingStatus;

public interface IPartyMeetingStatusDAO extends GenericDao<PartyMeetingStatus,Long> {

	public List<Object[]> getPartyMeetingCountLevelWise(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate);
	public List<Object[]> getPartyMeetingCountLocationWiseByUserAccess(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate);
}
