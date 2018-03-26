package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.RwsIvrAlertDetails;

public interface IRwsIvrAlertDetailsDAO extends GenericDao<RwsIvrAlertDetails, Long> {
	public List<Object[]> getJalavaniIvrDetailsSummary(Date fromDate,Date toDate);

}
