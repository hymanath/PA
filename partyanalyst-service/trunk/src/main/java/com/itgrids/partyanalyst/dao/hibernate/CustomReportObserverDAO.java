package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICustomReportObserverDAO;
import com.itgrids.partyanalyst.model.CustomReportObserver;
import com.itgrids.partyanalyst.model.CustomReportProgram;

public class CustomReportObserverDAO extends GenericDaoHibernate<CustomReportObserver, Long> implements ICustomReportObserverDAO {

	public CustomReportObserverDAO(){
		super(CustomReportObserver.class);
	}

}
