package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterTempDAO;
import com.itgrids.partyanalyst.model.VoterTemp;

public class VoterTempDAOHibernateTest extends BaseDaoTestCase{

	private IVoterTempDAO voterTempDAO;

	public void setVoterTempDAO(IVoterTempDAO voterTempDAO) {
		this.voterTempDAO = voterTempDAO;
	}
	
	/*public void test()
	{
		voterTempDAO.get(9l);
	}*/
	
	public void testGetVotersInAConstituency()
	{
		List<VoterTemp> list = voterTempDAO.getVotersInAConstituency(78l,0,10);
		System.out.println(list.size());
	}
}
