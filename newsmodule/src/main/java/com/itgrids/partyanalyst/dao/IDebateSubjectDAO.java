package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.DebateSubject;

public interface IDebateSubjectDAO extends GenericDao<DebateSubject, Long>{

	public List<Object[]> getDebateSubjectDetails(Long debateId);
	
	//public List<Object[]> getDebateDetalsForSelectedDates(Date fromDate,Date toDate);
	
	public List<Object[]> getDebateDetalsForSelectedCriteria(Date fromDate,Date toDate,Long channelId,Long partyId,Long candidateId,String sortBy,String sort,int startIndex,int maxIndex,String isCount);
	
	public List<Object[]> searchCriteriaForDebateSearch(String searchString);
}
