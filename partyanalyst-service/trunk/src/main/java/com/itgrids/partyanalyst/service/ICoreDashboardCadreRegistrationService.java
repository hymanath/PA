package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CadreRegistratedCountVO;
import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;

public interface ICoreDashboardCadreRegistrationService {
	
	public CadreRegistratedCountVO showCadreRegistreredCount(String retrieveType);
	public Object getRegistrationCountDtls(String location,Long constId, String scope);
	public Object getCadreRegistrationCountByConstituency(Long constituencyId,String fromDate,String toDate);
	public Object getDaysByCadreRegistrationCount(Long constituencyId,Long cadreSurveyUserId,Long tabUserInfoId,String fromDate,String toDate);
	public CadreRegistratedCountVO getEnumeratorsInfo(String retrieveType);
	public String getCadreLastUpdatedTime();
	public Object getNoRegistrationReceiveTabUserPersonCountByTimeWise(Long constituencyId,String date);
	public Object getTabUserInfoDetails(String tabUserInfoIds);
	public CadreRegistrationVO getRegistrationPersonDetails(Long voterId,Long familyVoterId,Long tdpCadreId,String status);
}
