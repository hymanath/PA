package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterModificationTempDAO;

public class VoterModificationTempDAOHibernateTest extends BaseDaoTestCase{

	private IVoterModificationTempDAO voterModificationTempDAO;

	public void setVoterModificationTempDAO(
			IVoterModificationTempDAO voterModificationTempDAO) {
		this.voterModificationTempDAO = voterModificationTempDAO;
	}
	
	/*public void test()
	{
		voterModificationTempDAO.getAll();
	}*/
	
	public void testGetVoterIDAndStatusFromVoterModificationTempByConstituencyId()
	{
		List<Object[]> list = voterModificationTempDAO.getVoterIDAndStatusFromVoterModificationTempByConstituencyId(232l);
		System.out.println(list.size());
		
		for(Object[] params :list)
			System.out.println(params[0]+"\t"+params[1]);
	}
}
