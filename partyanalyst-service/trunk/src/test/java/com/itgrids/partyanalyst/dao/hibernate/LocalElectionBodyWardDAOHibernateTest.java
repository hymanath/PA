package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ILocalElectionBodyWardDAO;

public class LocalElectionBodyWardDAOHibernateTest extends BaseDaoTestCase {

	public ILocalElectionBodyWardDAO localElectionBodyWardDAO;

	public ILocalElectionBodyWardDAO getLocalElectionBodyWardDAO() {
		return localElectionBodyWardDAO;
	}

	public void setLocalElectionBodyWardDAO(
			ILocalElectionBodyWardDAO localElectionBodyWardDAO) {
		this.localElectionBodyWardDAO = localElectionBodyWardDAO;
	}
	
	public void testGetAll(){
		localElectionBodyWardDAO.getAll();
	}
	
	/*public void testgetWardsByConstituency()
	{
		List<Long> ids = new ArrayList<Long>();
		ids.add(31804l);
		ids.add(31805l);
		ids.add(31806l);
		ids.add(31807l);
		ids.add(31808l);
		ids.add(31809l);
		ids.add(31810l);
		ids.add(31937l);
		List<Object[]> values = localElectionBodyWardDAO.getWardsByConstituency(ids);
		for (Object[] parms : values) {
			System.out.println(parms[0] +":"+ parms[1] +":"+ parms[2]);
		}
	}*/
}
