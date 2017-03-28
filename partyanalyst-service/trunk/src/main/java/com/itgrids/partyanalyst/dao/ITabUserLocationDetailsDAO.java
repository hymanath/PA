package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.GISVisualizationParameterVO;
import com.itgrids.partyanalyst.model.TabUserLocationDetails;

public interface ITabUserLocationDetailsDAO extends GenericDao<TabUserLocationDetails, Long>{

	public List<Object[]> getLattitudeLangitudeOfTabUser(Long locationId,Date startDate,Date endDate,String type);
	public List<Object[]> getLattitudLangitudeOfTabUser(GISVisualizationParameterVO inputVO,Date startDate,Date endDate);
	//public List<Object[]> getLocationWiseTabUserTrackingDetails(GISVisualizationParameterVO inputVO,String type);
	public List<Object[]> getSurveyUserTrackingDtls(Long cadreSurveyUserId,Date fromDate,Date toDate);
	public List<Object[]> getSurveyUserTrackingDtlsByFieldUser(Long fieldUserId,Long cadreSurveyUserId,Date fromDate,Date toDate,Date fromTime,Date toTime);
	public List<Object[]> getSurveyUserLatestTimeLongitudeAndLatituedeLocationWise(String locationType,List<Long> locationValues,Date fromDate,Date toDate);
	public List<Object[]> getLattitudeLangitudeOfTabbUserByIds(List<Long> locationIsdList);
	public List<Object[]> getLatestLattitudeAndLongitude(List<Long> cadreSurveyUserIds);
}
