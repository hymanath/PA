package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartyManifestoDAO;

public class PartyManifestoDAOHibernateTest extends BaseDaoTestCase {

	private IPartyManifestoDAO partyManifestoDAO;

	public void setPartyManifestoDAO(IPartyManifestoDAO partyManifestoDAO) {
		this.partyManifestoDAO = partyManifestoDAO;
	}

	public void test() {
		partyManifestoDAO.getAll();
	}
	//public List<Object[]> getPartyManifestoInfo(Long partyId) {
	/*public void testGetPartyManifestoInfo()
	{
		 List<Object[]> result = partyManifestoDAO.getPartyManifestoInfo(163l);
		 System.out.println(result.size());
		 for (Object[] objects : result) {
			System.out.println(objects[0].toString());
			System.err.println(objects[1].toString());
			System.out.println(objects[2].toString());
			System.out.println(objects[3].toString());
			System.out.println(objects[4].toString());
		}
	}*/
}
