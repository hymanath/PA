package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.CadreRegisterInfo;
import com.itgrids.partyanalyst.dto.GISVisualizationParameterVO;
import com.itgrids.partyanalyst.model.CadreSurveyUserAssignDetails;

public interface ICadreSurveyUserAssignDetailsDAO extends GenericDao<CadreSurveyUserAssignDetails, Long>{

	public List<CadreSurveyUserAssignDetails> getCadreAssinedDetails(Long cadreSurveyUserId);
	
	public List<Long> getCadreSurveyAssignUsersList();
	
	public List<Object[]> getCadreSurveyAssignUsersListByConstituency(Long constituencyId);
	
	public List<Object[]> getCadreSurveyAssignConstituencyList();
	
	public List<Long> checkIsAlreadyAssigned(Long cadreSurveyUserId, Long levelId,Long levelValue, Long constituencyId);
	public List<Object[]> getCandidatesInfo(String queryStr,List<Long> locationIdsList);
	public List<Long> isTabAssignedAlready(String tabNo);
	public List<Object[]> getTabNos(List<Long> cadreSurveyUserIds);
	public List<Object[]> getCadreSurveyUsers(Long constituencyId);
	public List<Object[]> getUsersByConstituencyAndUserId(Long constituencyId,Long userId);
	public List<Object[]> getUserConstituencyDetails(List<Long> userIds);
	public List<Long> getCadreSurveyUserIdsByLocation(String location,Long locationId,String queryString);
	public List<Object[]> getUsersDetails(List<Long> cadreSurveyUserIds);
	public List<Object[]> getTDPCadreAmountDetails(List<Long> districtIds,String type,Date fromDate,Date toDate);
	public List<Object[]> getTDPCadreAmountDetails(List<Long> districtIds,String type,Date fromDate,Date toDate,Long stateId );
	
	public List<Object[]> getUserTrackingDetails(GISVisualizationParameterVO inputVO);
	//public Long getTabUserTrackingDetails(GISVisualizationParameterVO inputVO);
}
