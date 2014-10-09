package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreSurveyUserAssignDetails;

public interface ICadreSurveyUserAssignDetailsDAO extends GenericDao<CadreSurveyUserAssignDetails, Long>{

	public List<CadreSurveyUserAssignDetails> getCadreAssinedDetails(Long cadreSurveyUserId);
	
	public List<Long> getCadreSurveyAssignUsersList();
	
	public List<Object[]> getCadreSurveyAssignUsersListByConstituency(Long constituencyId);
	
	public List<Object[]> getCadreSurveyAssignConstituencyList();
	
	public List<Long> checkIsAlreadyAssigned(Long cadreSurveyUserId, Long levelId,Long levelValue, Long constituencyId);

	public List<Long> isTabAssignedAlready(String tabNo);
	
}
