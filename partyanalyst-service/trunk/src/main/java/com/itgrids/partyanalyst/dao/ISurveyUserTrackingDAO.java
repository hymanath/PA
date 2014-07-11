package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SurveyUserTracking;

public interface ISurveyUserTrackingDAO extends GenericDao<SurveyUserTracking, Long>
{
	public List<Object[]> getLatLongForUserTracking(Long surveyUserId,Date date);
	public void sessionFlush();
	public Long checkWhetherRecordExistingOrNot(String uuid,String imei, Date date);


}
