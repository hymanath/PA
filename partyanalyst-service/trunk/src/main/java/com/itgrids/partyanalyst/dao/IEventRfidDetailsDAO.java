package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.EventRfidDetails;

public interface IEventRfidDetailsDAO extends GenericDao<EventRfidDetails,Long>{
	public List<Object[]> getEventRFIDDetailsByEventIds(List<Long> eventIds);
}
