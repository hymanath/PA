package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SurveyUserConstituency;

public interface ISurveyUserConstituencyDAO extends GenericDao<SurveyUserConstituency, Long>{
	
	public List<Object[]> getSurveyConstituencyList();
	
	public List<Object[]> getSurveyConstituencyLeadersList(Long constituencyId);
	
	public List<Long> getAlreadyAssignedUsers();
	
	public List<Long> getAlreadyAssignedConstituencies();
}
