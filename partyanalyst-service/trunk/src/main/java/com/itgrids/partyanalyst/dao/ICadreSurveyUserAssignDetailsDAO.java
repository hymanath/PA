package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.CadreRegisterInfo;
import com.itgrids.partyanalyst.dto.GISVisualizationParameterVO;
import com.itgrids.partyanalyst.model.CadreSurveyUserAssignDetails;

public interface ICadreSurveyUserAssignDetailsDAO extends GenericDao<CadreSurveyUserAssignDetails, Long>{

	public List<Long> getConstituencyIdByUserId(Long cadreSurveyUserId);
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
	public List<Object[]> getMapPowerLocationWise(Long locationScopeId, String locationType);
	//public Long getTabUserTrackingDetails(GISVisualizationParameterVO inputVO);
	public List<Object[]> getCadreSurveyUserDtlsLocationWise(String locationType,List<Long> locationValues,Date fromDate,Date toDate);
	public List<Object[]> getLocationWiseSmartDevicesCount(Long stateId,Long districtId,Long constituencyId,Date startDate,Date endDate);
	public List<Object[]> getActualCountOfCadreSurveyUser(Set<Long> locationIds,Long districtId,Date startDate,Date endDate);
	public List<Object[]> getLocationWiseCadreSurveyUserIds(Long districtId,Long stateId,Date startDate,Date endDate);
	public List<Object[]> getNewUserTrackingDetails(GISVisualizationParameterVO inputVO);
	public List<Long> getAssignedConstiteuncyListByUserId(Long cadreSurveyUserId);
}
