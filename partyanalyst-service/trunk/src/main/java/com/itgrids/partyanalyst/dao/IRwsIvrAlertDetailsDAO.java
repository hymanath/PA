package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.RwsIvrAlertDetails;

public interface IRwsIvrAlertDetailsDAO extends GenericDao<RwsIvrAlertDetails, Long> {
	public List<Object[]> getJalavaniIvrDetailsSummary(Date fromDate,Date toDate);
	public List<Object[]> getJalavaniIvrSummaryGraphDetailsInfo(Date fromDate,Date toDate,String searchType);
	public List<Long> getJalavaniIvrSummaryWiseClick(Date fromDate,Date toDate,Long statusid,Long probTypeId,Long districtId,String satisfiedStatus);
	public List<Object[]> getJalavaniIvrRespondantsGraphDetailsInfo(Date fromDate,Date toDate,String searchType);
}
