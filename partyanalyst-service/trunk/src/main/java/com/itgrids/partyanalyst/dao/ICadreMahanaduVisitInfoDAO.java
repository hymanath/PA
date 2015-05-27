package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreMahanaduVisitInfo;

public interface ICadreMahanaduVisitInfoDAO   extends GenericDao<CadreMahanaduVisitInfo, Long>{
	public void flushAndclearSession();
}
