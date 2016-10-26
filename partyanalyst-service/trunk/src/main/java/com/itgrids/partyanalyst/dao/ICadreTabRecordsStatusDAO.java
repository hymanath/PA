package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreTabRecordsStatus;

public interface ICadreTabRecordsStatusDAO extends GenericDao<CadreTabRecordsStatus, Long>{
	public List<Object[]> dataReConsalationOverView(Long constistuencyId,
			Date fromDate, Date toDate);
	public List<Object[]> dataReConsalationTotalOverView(Long constistuencyId,
			Date fromDate, Date toDate);
	public Integer deleteExstngCadreTdpRecords(Long cadreSurveyUserId,Long tabUserInfoId,Date surveyDate);
}
