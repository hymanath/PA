package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import com.itgrids.partyanalyst.dao.IVoterModificationDAO;
import com.itgrids.partyanalyst.utils.IConstants;

public class VoterModificationDAOHibernateTest extends BaseDaoTestCase{

	private IVoterModificationDAO voterModificationDAO;

	public void setVoterModificationDAO(IVoterModificationDAO voterModificationDAO) {
		this.voterModificationDAO = voterModificationDAO;
	}
	
	/*public void test()
	{
		voterModificationDAO.getAll();
	}*/
	
	/*public void testGetAddedAndDeletedVotersCountInBetweenPublicationsInALocation()
	{	
		List<Long>  publicationIdsList = new ArrayList<Long>(0);
		 publicationIdsList.add(8l);
		List<Object[]> list = voterModificationDAO.getAddedAndDeletedVotersCountInBetweenPublicationsInALocation("constituency",241l,241l,publicationIdsList);
		System.out.println(list.size());
		System.out.println(list.get(0)[0]+"\t"+list.get(0)[1]);
		System.out.println(list.get(1)[0]+"\t"+list.get(1)[1]);
	}*/
	
	/*public void testGetAgeWiseAddedAndDeletedVotersCountInBetweenPublicationsInALocation()
	{
		List<Long>  publicationIdsList = new ArrayList<Long>(0);
		publicationIdsList.add(8l);
		List<Object[]> list = voterModificationDAO.getAgeWiseAddedAndDeletedVotersCountInBetweenPublicationsInALocation("constituency",232l,232l, publicationIdsList, 18l, 25l);
		System.out.println(list.size());
		System.out.println(list.get(0)[0]+"\t"+list.get(0)[1]);
		System.out.println(list.get(1)[0]+"\t"+list.get(1)[1]);
	}*/
	
	/*public void testGetGenderWiseVoterModificationsBetweenPublications()
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
	}*/
	
	public void testGetModifiedVotersInALocationBetweenPublucations()
	{
		List<Long>  publicationIdsList = new ArrayList<Long>(0);
		publicationIdsList.add(8l);
		List<Object[]> list = voterModificationDAO.getModifiedVotersInALocationBetweenPublucations("localElectionBody",83l,232l, publicationIdsList, "All");
		
		System.out.println(list.size());
		for(Object[] params : list)
		{
			System.out.println();
			for(Object obj : params)
				System.out.print("\t"+obj);
		}
	}
	
	/*public void testGetModifiedVotersByPartNo()
	{
		List<Long> list = voterModificationDAO.getModifiedVotersByPartNo("1",241l,8l,IConstants.STATUS_ADDED);
		System.out.println(list.size());
	}*/
	
	/*public void testGetGenderWiseVoterModificationByPublicationId()
	{
		List<Long> locationValuesList = new ArrayList<Long>(0);
		locationValuesList.add(1l);
		String locationType = "panchayat";
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select count(model.voter.voterId),model.status,model.voter.gender ");
		 
		 if(locationType.equalsIgnoreCase("constituency"))
			 queryStr.append(" ,model2.booth.constituency.constituencyId ");
			else if(locationType.equalsIgnoreCase("mandal"))
				queryStr.append(", model2.booth.tehsil.tehsilId ");
			else if(locationType.equalsIgnoreCase("panchayat"))
				queryStr.append(" ,model2.booth.panchayat.panchayatId ");
			else if(locationType.equalsIgnoreCase("booth"))
				queryStr.append(" ,model2.booth.boothId ");
			else if(locationType.equalsIgnoreCase("localElectionBody") || locationType.equalsIgnoreCase("Local Election Body"))
				queryStr.append(" ,model.booth.localBody.localElectionBodyId ");
			else if(locationType.equalsIgnoreCase("ward"))
				queryStr.append(" ,model2.localBodyWard.constituencyId ");
		
		List<Object[]> list = voterModificationDAO.getGenderWiseVoterModificationByPublicationId(locationType, locationValuesList, 232l, 7l,queryStr.toString());
		
		System.out.println(list.size());
		if(list != null && list.size() > 0)
		{
			for(Object[] params : list)
				System.out.println(params[0]); 
				
		}
	}
	*/
	
	/*public void testGetAgeWiseAddedAndDeletedVotersCountByPublicationDateIdInALocation()
	{
		List<Long> locationValuesList = new ArrayList<Long>(0);
		locationValuesList.add(232l);
		List<Object[]> list = voterModificationDAO.getAgeWiseAddedAndDeletedVotersCountByPublicationDateIdInALocation("constituency", locationValuesList, 232l, 7l, 18l, 25l);
		
		if(list != null && list.size() > 0)
		{
			for(Object[] params : list)
				System.out.println(params[1]);
		}
	}*/
}
