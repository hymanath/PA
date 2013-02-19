package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import com.itgrids.partyanalyst.dao.IVoterModificationDAO;

public class VoterModificationDAOHibernateTest extends BaseDaoTestCase{

	private IVoterModificationDAO voterModificationDAO;

	public void setVoterModificationDAO(IVoterModificationDAO voterModificationDAO) {
		this.voterModificationDAO = voterModificationDAO;
	}
	
	/*public void test()
	{
		voterModificationDAO.getAll();
	}*/
	
	public void testGetAddedAndDeletedVotersCountInBetweenPublicationsInALocation()
	{	
		List<Long>  publicationIdsList = new ArrayList<Long>(0);
		 publicationIdsList.add(8l);
		List<Object[]> list = voterModificationDAO.getAddedAndDeletedVotersCountInBetweenPublicationsInALocation("panchayat",1l,232l,publicationIdsList);
		System.out.println(list.size());
		System.out.println(list.get(0)[0]+"\t"+list.get(0)[1]);
		System.out.println(list.get(1)[0]+"\t"+list.get(1)[1]);
	}
}
