package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.DebateSubject;

public interface IDebateSubjectDAO extends GenericDao<DebateSubject, Long>{

	public List<Object[]> getDebateSubjectDetails(Long debateId);
	
	//public List<Object[]> getDebateDetalsForSelectedDates(Date fromDate,Date toDate);
	
	//public List<Object[]> getDebateDetalsForSelectedCriteria(Date fromDate,Date toDate,Long channelId,Long partyId,Long candidateId,String sortBy,String sort,int startIndex,int maxIndex,String isCount);
	public List<Object[]> getDebateDetalsForSelectedCriteria(Date fromDate,Date toDate,List<Long> channelIds,List<Long> partyIds,List<Long> candidateIds,String sortBy,String sort,int startIndex,int maxIndex,String isCount,Long stateId);
	
	public List<Object[]> searchCriteriaForDebateSearch(String searchString);
	public List<Object[]> getDebateSubjectDetailsOfList(Set<Long> debateIds,Long stateId);
}
