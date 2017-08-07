package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.PartyMeetingType;

public interface IPartyMeetingTypeDAO extends GenericDao<PartyMeetingType,Long>{
	public List<Object[]> getMeetingTypesBasedOnLocationLevel(Long locationLevel);
	public List<Object[]> getMeetingTypesBasedOnLocationLevelNew(List<Long> locationLevels);
	public List<Object[]> getPartyMeetingTypeByPartyMeetingMainType(Long partyMeetingMainTypeId);
	public List<Object[]> getPartyMeetingTypeIds(List<Long> mainMeetingIdsList);
	public List<Object[]> getPartyMeetingLevels(Long partyMeetingTypeId);
}
