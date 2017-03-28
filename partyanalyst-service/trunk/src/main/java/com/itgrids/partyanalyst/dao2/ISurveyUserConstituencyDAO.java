package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SurveyUserConstituency;

public interface ISurveyUserConstituencyDAO extends GenericDao<SurveyUserConstituency, Long>{
	
	public List<Object[]> getSurveyConstituencyList();
	
	public List<Object[]> getSurveyConstituencyLeadersList(Long constituencyId);
	
	public List<Long> getAlreadyAssignedUsers();
	
	public List<Long> getAlreadyAssignedConstituencies(Long surveyUserTypeId);
	public List<Object[]> getSurveyUserConstituency(Long surveyUserId);
	
	public int updateActiveStatusByList(List<Long> Ids);
	public List<Object[]> getExistedConstituenciesDetailsByUserId(Long userId);
	public List<Object[]> getLeadersByConstituency();
	public List<Object[]> getSurveyConstituencies();
	public List<Object[]> getSurveyLeaderByConstituency(Long constituencyId);
	public int updateUserConstituencies(Long surveyUserId,Long constituencyId);
	public List<Object[]> getSurveyUserConstituencyDetails(Long surveyUserId);
	public List<Object[]> getSurveyLeadersForAllConstituency();
	public List<Object[]> getSurveyConstituencyLeadersList(List<Long> constituencyIds);
}
