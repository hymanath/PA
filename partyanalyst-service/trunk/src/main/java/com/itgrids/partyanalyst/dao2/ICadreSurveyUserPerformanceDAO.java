package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreMissedCallCampaign;
import com.itgrids.partyanalyst.model.CadreSurveyUserPerformance;

public interface ICadreSurveyUserPerformanceDAO extends GenericDao<CadreSurveyUserPerformance, Long> {

	public List<CadreSurveyUserPerformance> getCadreSurveyUserPerformanceDetails(Long cadreSurveyUserId,Date currentDate);
	public List<Object[]> getUserWisePerformanceByDate(Long cadreSurveyUserId,Date today);
	public List<Object[]> getCadreSurveyUserId(Date today);
}
