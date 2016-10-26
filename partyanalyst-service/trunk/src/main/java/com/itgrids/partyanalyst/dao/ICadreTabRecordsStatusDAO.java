package com.itgrids.partyanalyst.dao;

import java.util.Date;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreTabRecordsStatus;

public interface ICadreTabRecordsStatusDAO extends GenericDao<CadreTabRecordsStatus, Long>{

	public Integer deleteExstngCadreTdpRecords(Long cadreSurveyUserId,Long tabUserInfoId,Date surveyDate);
}
