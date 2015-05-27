package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICadreMahanaduVisitInfoDAO;
import com.itgrids.partyanalyst.model.CadreMahanaduVisitInfo;

public class CadreMahanaduVisitInfoDAO extends GenericDaoHibernate<CadreMahanaduVisitInfo, Long>   implements ICadreMahanaduVisitInfoDAO {
	public CadreMahanaduVisitInfoDAO( ) {
		super(CadreMahanaduVisitInfo.class);
		
	}
	public void flushAndclearSession(){
		getSession().flush();
		getSession().clear();
	}
}
