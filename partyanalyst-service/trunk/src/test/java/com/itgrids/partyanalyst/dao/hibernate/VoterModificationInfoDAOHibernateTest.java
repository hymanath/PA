package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterModificationInfoDAO;

public class VoterModificationInfoDAOHibernateTest extends BaseDaoTestCase{

	private IVoterModificationInfoDAO voterModificationInfoDAO;

	public void setVoterModificationInfoDAO(
			IVoterModificationInfoDAO voterModificationInfoDAO) {
		this.voterModificationInfoDAO = voterModificationInfoDAO;
	}
	
	/*public void test()
	{
		voterModificationInfoDAO.getAll();
	}*/
	/*public void test1()
	{
		List<Long> list1 = new ArrayList<Long>();
		list1.add(1l);
		voterModificationInfoDAO.getGenderWiseVoterModificationsBetweenPublications(1l,2l,3l,list1);
	}
	public void test2()
	{
		List<Long> list1 = new ArrayList<Long>();
		list1.add(1l);
		voterModificationInfoDAO.getGenderWiseVoterModificationsForEachPublication(1l,2l,3l,list1);
	}
	public void testgetVoterModificationInfoIdByReportLevelValue()
	{
		Long id = voterModificationInfoDAO.getVoterModificationInfoIdByReportLevelValue(1l, 232l, 7l, 1l, 232l);
		System.out.println(id); 
		
	}*/
	
	/*public void testgetVoterModificationGenderDetailsByLocationValuesList()
	{
		List<Long> publicationIdsList = new ArrayList<Long>(0);
		publicationIdsList.add(8l);
		List<Long> locationValuesList = new ArrayList<Long>(0);
		locationValuesList.add(1007l);
		locationValuesList.add(999l);
		locationValuesList.add(1024l);
		locationValuesList.add(10212l);
		locationValuesList.add(1025l);
		locationValuesList.add(1008l);
		
		List<Object[]> list = voterModificationInfoDAO.getVoterModificationGenderDetailsByLocationValuesList(locationValuesList, publicationIdsList, 299l, 3l);
		
		if(list != null && list.size() > 0)
		{
			for(Object[] params : list)
				System.out.println(params[0]+" "+params[1]);
		}
	}*/
	
	/*public void testGetReportLevelValueByReportLevelId()
	{
		List<Long> list = voterModificationInfoDAO.getReportLevelValueByReportLevelId(232l, 8l, 2l);
		System.out.println(list.size());
	}*/
	
	/*public void testgetModificationDetailsByConstituencyId()
	{
		List<Object[]> list = voterModificationInfoDAO.getModificationDetailsByConstituencyId(232l, 8l, 2l);
		System.out.println(list.size());
		for(Object[] params : list)
			System.out.println(params[0]+" "+params[1]);
		
	}*/
	
	/*public void testGetVoterModificationIdsByReportLevelValue()
	{
		List<Long> reportLevelValueList = new ArrayList<Long>(0);
		reportLevelValueList.add(232l);
		List<Long> list = voterModificationInfoDAO.getVoterModificationIdsByReportLevelValue(232l, 8l, 1l, reportLevelValueList);
		System.out.println(list.size());
	
	}*/
	
	/*public void testGetVoterModificationDetailsByModificationIdsList()
	{
		List<Long> modificationIdsList = new ArrayList<Long>(0);
		modificationIdsList.add(1l);
		modificationIdsList.add(2l);
		modificationIdsList.add(413l);
		modificationIdsList.add(414l);
		List<Object[]> list = voterModificationInfoDAO.getVoterModificationDetailsByModificationIdsList(modificationIdsList);
		System.out.println(list.size());
		for(Object[] params : list)
			System.out.println(params[0]+" "+params[1]);
		
	}*/
	
	public void testGetVoterModificationInfoOfAConstituencyForAPublication()
	{
		List<Object[]> list = voterModificationInfoDAO.getVoterModificationInfoOfAConstituencyForAPublication(232l,8l);
		System.out.println(list.size());
	}
}
