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
		List<Object[]> list = voterModificationDAO.getAddedAndDeletedVotersCountInBetweenPublicationsInALocation("constituency",241l,241l,publicationIdsList);
		System.out.println(list.size());
		System.out.println(list.get(0)[0]+"\t"+list.get(0)[1]);
		System.out.println(list.get(1)[0]+"\t"+list.get(1)[1]);
	}
	
	/*public void testGetAgeWiseAddedAndDeletedVotersCountInBetweenPublicationsInALocation()
	{
		List<Long>  publicationIdsList = new ArrayList<Long>(0);
		publicationIdsList.add(8l);
		List<Object[]> list = voterModificationDAO.getAgeWiseAddedAndDeletedVotersCountInBetweenPublicationsInALocation("constituency",232l,232l, publicationIdsList, 18l, 25l);
		System.out.println(list.size());
		System.out.println(list.get(0)[0]+"\t"+list.get(0)[1]);
		System.out.println(list.get(1)[0]+"\t"+list.get(1)[1]);
	}*/
	
	public void testGetGenderWiseVoterModificationsBetweenPublications()
	{
		List<Long>  publicationIdsList = new ArrayList<Long>(0);
		publicationIdsList.add(8l);
		List<Object[]> list = voterModificationDAO.getGenderWiseVoterModificationsBetweenPublications("constituency",241l,241l, publicationIdsList);
		System.out.println(list.size());
		for(Object[] params : list)
		{
			System.out.println();
			for(Object obj : params)
				System.out.print("\t"+obj);
		}
	}
}
