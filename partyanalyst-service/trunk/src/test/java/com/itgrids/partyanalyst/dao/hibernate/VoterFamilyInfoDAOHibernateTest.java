package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterFamilyInfoDAO;

public class VoterFamilyInfoDAOHibernateTest extends BaseDaoTestCase{

	private IVoterFamilyInfoDAO voterFamilyInfoDAO;

	public void setVoterFamilyInfoDAO(IVoterFamilyInfoDAO voterFamilyInfoDAO) {
		this.voterFamilyInfoDAO = voterFamilyInfoDAO;
	}

	/*public void test()
	{
		voterFamilyInfoDAO.getAll();
	}*/
	
	/*public void testGetVoterFamilyDetails()
	{
		List<VoterFamilyInfo> voterFamilyInfoList = voterFamilyInfoDAO.getVoterFamilyDetails(1l, 232l, 7l);
		System.out.println(voterFamilyInfoList.size());
		if(voterFamilyInfoList != null && voterFamilyInfoList.size() >0)
		{
			for(VoterFamilyInfo familyInfo : voterFamilyInfoList)
				System.out.println("Total Families - "+familyInfo.getTotalFamilies()+"  "+"Family Percentage - "+familyInfo.getFamiliesPercentage()); 
		}
		
	}*/
	
	public void testDeleteVoterFamilyDetByReportLevelValAndVoterAgeRange()
	{
		List<Long> reportLevelValue = new ArrayList<Long>(0);
		reportLevelValue.add(232l);
		System.out.println(voterFamilyInfoDAO.deleteVoterFamilyDetByReportLevelValAndVoterAgeRange(1l, reportLevelValue, 7l));
	}
	
	/*public void testGetTotalFamiliesCountByReportLevelValue()
	{
		List totalFalies = voterFamilyInfoDAO.getTotalFamiliesCountByReportLevelValue(1l, 232l, 7l);
		
		System.out.println(totalFalies.size());
		if(totalFalies != null && totalFalies.size() > 0)
			System.out.println(totalFalies.get(0));
	}*/
	
}
