package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.DebateSubject;

public interface IDebateSubjectDAO extends GenericDao<DebateSubject, Long>{

	public List<Object[]> getDebateSubjectDetails(Long debateId);
	
	public List<Object[]> getDebateDetalsForSelectedDates(Date fromDate,Date toDate);
}
