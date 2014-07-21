package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IWebMonitorCompletedLocationsDetailsDAO;
import com.itgrids.partyanalyst.model.WebMonitorCompletedLocationsDetails;

public class WebMonitorCompletedLocationsDetailsDAO extends GenericDaoHibernate<WebMonitorCompletedLocationsDetails, Long> implements IWebMonitorCompletedLocationsDetailsDAO{
	
	public WebMonitorCompletedLocationsDetailsDAO() {
		super(WebMonitorCompletedLocationsDetails.class);
	}

}
