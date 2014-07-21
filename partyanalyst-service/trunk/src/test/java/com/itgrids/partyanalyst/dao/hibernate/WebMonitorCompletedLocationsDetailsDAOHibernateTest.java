package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IWebMonitorCompletedLocationsDetailsDAO;

public class WebMonitorCompletedLocationsDetailsDAOHibernateTest extends BaseDaoTestCase{
	
	
 private IWebMonitorCompletedLocationsDetailsDAO webMonitorCompletedLocationsDetailsDAO;

public void setWebMonitorCompletedLocationsDetailsDAO(
		IWebMonitorCompletedLocationsDetailsDAO webMonitorCompletedLocationsDetailsDAO) {
	this.webMonitorCompletedLocationsDetailsDAO = webMonitorCompletedLocationsDetailsDAO;
}
 

/*public void test()
{
	List<Long> list = webMonitorCompletedLocationsDetailsDAO.getPanchayatBoothsByConstituencyId(8l);
	System.out.println(list.size());
}*/
}
