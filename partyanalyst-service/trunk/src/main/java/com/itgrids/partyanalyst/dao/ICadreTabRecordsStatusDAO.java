package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreTabRecordsStatus;

public interface ICadreTabRecordsStatusDAO extends GenericDao<CadreTabRecordsStatus, Long>{
	public List<Object[]> dataReConsalationOverView(Long stateId,Long constistuencyId,
			Date fromDate, Date toDate,Long districtId);
	public List<Object[]> dataReConsalationTotalOverView(Long stateId,Long constistuencyId,
			Date fromDate, Date toDate, Long districtId);
	public Integer deleteExstngCadreTdpRecords(Long cadreSurveyUserId,Long tabUserInfoId,Date surveyDate);
	public List<Object[]> getCadreSurveyUserWiseRegistrations(Long cadreSrveyUserId,Long constituencyId,Date fromDate,Date toDate);
}
