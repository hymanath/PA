package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreMahanaduVisitDetails;

public interface ICadreMahanaduVisitDetailsDAO   extends GenericDao<CadreMahanaduVisitDetails, Long>{
	public List<Object[]> getLatestInfoRecord(Date currentDate,Long parentEventId);
	public List<Object[]> getLatestRecords(Long cadreMahanaduVisitInfoId);
}
