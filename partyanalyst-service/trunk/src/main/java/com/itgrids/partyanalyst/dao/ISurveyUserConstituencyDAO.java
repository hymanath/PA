package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SurveyUserConstituency;

public interface ISurveyUserConstituencyDAO extends GenericDao<SurveyUserConstituency, Long>{
	
	public List<Object[]> getSurveyConstituencyList();
	
	public List<Object[]> getSurveyConstituencyLeadersList(Long constituencyId);
	
	public List<Long> getAlreadyAssignedUsers();
	
	public List<Long> getAlreadyAssignedConstituencies();
	public List<Object[]> getSurveyUserConstituency(Long surveyUserId);
	
	public int updateActiveStatusByList(List<Long> Ids);
	public List<Object[]> getExistedConstituenciesDetailsByUserId(Long userId);
	public List<Object[]> getLeadersByConstituency();
	public List<Object[]> getSurveyConstituencies();

}
