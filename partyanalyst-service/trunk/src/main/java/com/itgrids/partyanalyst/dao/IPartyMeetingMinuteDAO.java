package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.PartyMeetingMinute;

public interface IPartyMeetingMinuteDAO extends GenericDao<PartyMeetingMinute,Long>{
	public List<Object[]> getPartyMeetingsMinutsDetlsByCadreIds(Long partyMeetingTypeId);
}
