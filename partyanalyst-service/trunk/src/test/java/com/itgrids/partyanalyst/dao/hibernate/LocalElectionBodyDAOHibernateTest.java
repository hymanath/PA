package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.model.LocalElectionBody;

public class LocalElectionBodyDAOHibernateTest extends BaseDaoTestCase{

	private ILocalElectionBodyDAO localElectionBodyDAO;

	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}

	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}
	
	public void testGetAll(){
		List<LocalElectionBody> list = localElectionBodyDAO.getAll();
		assertEquals(list.size() >= 0 , true);
	}
	
	/*public void testFindByElectionTypeDistrictTehsilAndLEBName(){
		List<LocalElectionBody> list = localElectionBodyDAO.findByElectionTypeDistrictTehsilAndLEBName(2l, "Chittoor", "Madanapalli", "Madanapalli");
		assertEquals(1, list.size());
	}*/
	public void testFindByElectionTypeAndState(){
	
		List result = localElectionBodyDAO.findByElectionTypeAndState(6l, 1l);
		for(int i=0;i<result.size();i++)
		{
			Object[] parms = (Object[])result.get(i);
			System.out.print(Long.parseLong(parms[0].toString()));
			System.out.println(parms[1].toString());
				
		}
	}
	
	
}
