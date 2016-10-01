package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CadreRegistratedCountVO;

public interface ICoreDashboardCadreRegistrationService {
	
	public CadreRegistratedCountVO showCadreRegistreredCount();
	public Object getRegistrationCountDtls(String location,Long constId);
	public Object getCadreRegistrationCountByConstituency(Long constituencyId,String fromDate,String toDate);
	public Object getDaysByCadreRegistrationCount(Long constituencyId,Long cadreSurveyUserId,Long tabUserInfoId,String fromDate,String toDate);
}
