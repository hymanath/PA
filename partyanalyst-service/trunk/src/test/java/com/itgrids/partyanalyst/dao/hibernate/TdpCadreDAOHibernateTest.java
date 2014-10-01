package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ITdpCadreDAO;

public class TdpCadreDAOHibernateTest extends BaseDaoTestCase {
	private ITdpCadreDAO tdpCadreDAO;

	
	
	public ITdpCadreDAO getTdpCadreDAO() {
		return tdpCadreDAO;
	}



	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}



	public void testgetWorkStartedConstituencyCount(){
		Long tsCount=tdpCadreDAO.getWorkStartedConstituencyCount("TS");
		Long apCount=tdpCadreDAO.getWorkStartedConstituencyCount("AP");
		Long count_2012=tdpCadreDAO.getWorkStartedConstituencyYearCount(2012L);
		Long count_2014=tdpCadreDAO.getWorkStartedConstituencyYearCount(2014L);
		System.out.println("AP Count is:"+apCount);
		System.out.println("TS Count is:"+tsCount);
		System.out.println("2012 Count is:"+count_2012);
		System.out.println("2014 Count is:"+count_2014);
	}

}
