package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.model.VoterInfo;
import com.itgrids.partyanalyst.utils.IConstants;

public class VoterInfoDAOHibernateTest extends BaseDaoTestCase{

	private IVoterInfoDAO voterInfoDAO;

	public void setVoterInfoDAO(IVoterInfoDAO voterInfoDAO) {
		this.voterInfoDAO = voterInfoDAO;
	}
	
	/*public void test()
	{
		voterInfoDAO.getAll();
	}*/
	
	
	/*public void testGetVotersCount1()
	{
		List<VoterInfo> list = voterInfoDAO.getVotersCount(1l, 232l, 7l);
		if(list != null && list.size() > 0)
		{
			for(VoterInfo params : list)
			{
				System.out.println(params.getMaleVoters());
			}
		}
	}*/
	
	/*public void testDeleteVotersInfoByReportLevelValue()
	{
		List<Long> reportLevelValue = new ArrayList<Long>(0);
		reportLevelValue.add(232l);
		System.out.println(voterInfoDAO.deleteVotersInfoByReportLevelValue(1l, reportLevelValue, 7l));
		
	}*/
	
	/*public void testGetTotalVotersByReportLevelValue()
	{
		System.out.println(voterInfoDAO.getTotalVotersByReportLevelValue(1l, 232l, 7l));
	}*/
	
	/*public void testgetVoterInfoByPublicationDateIds()
	{
		List<Long> publicationDateIds = new ArrayList<Long>(0);
		publicationDateIds.add(7l);
		List<Object[]> list = voterInfoDAO.getVoterInfoByPublicationDateIds(1l, 232l, publicationDateIds);
		if(list != null && list.size() > 0)
		{
			for(Object[] params : list)
				System.out.println(params[0]);
		}
		
	}
	*/
	
	/*public void testGetTotalVotersByPublicationDateIdsList()
	{
		List<Long> publicationDateIds = new ArrayList<Long>(0);
		publicationDateIds.add(7l);
		publicationDateIds.add(8l);
		List<Object[]> list = voterInfoDAO.getTotalVotersByPublicationDateIdsList(publicationDateIds, 1l, 232l, 232l);
		if(list != null && list.size() > 0)
		{
			for(Object[] params : list)
				System.out.println(params[0]+" "+params[1]);
		}
	}*/
	
	/*public void testGetPublicationListFromVoterInfoByConstituencyId()
	{
		List<Object[]> list = voterInfoDAO.getPublicationDetailsBasedOnConstituencyId(7l);
		if(list != null && list.size() > 0)
		{
			for(Object[] params : list)
				System.out.println(params[0]+" "+params[1]);
		}
		
	}*/
	
	/*public void testGetVoterPublicationIdsBetweenTwoPublications()
	{
		List<Long> list = voterInfoDAO.getVoterPublicationIdsBetweenTwoPublications(7l, 8l);
		if(list != null && list.size() > 0)
		  for(Long id : list)
			  System.out.println(id);
	}*/
	
	/*public void testgetPreviousPublicationIds()
	{
		List<Long> list = voterInfoDAO.getPreviousPublicationIds(8l);
		
		if(list != null && list.size() > 0)
			for(Long id : list)
				System.out.println(id);
	}*/
	
	public void testGetPanchayatWiseVotersCount()
	{
		List<Object[]> list = voterInfoDAO.getPanchayatWiseVotersCount(232l,8l);
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println();
			for(Object object :params)
				System.out.print("\t"+object.toString());
		}
	}
	
}
