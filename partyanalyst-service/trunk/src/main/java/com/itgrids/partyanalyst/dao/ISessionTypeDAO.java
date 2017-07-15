package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ActivityDocument;
import com.itgrids.partyanalyst.model.SessionType;

public interface ISessionTypeDAO extends GenericDao<SessionType, Long>{
	public List<Object[]> getAllSessionType();
	public List<Object[]> getPartyMeetingSession(Long sessionTypeId);
}
