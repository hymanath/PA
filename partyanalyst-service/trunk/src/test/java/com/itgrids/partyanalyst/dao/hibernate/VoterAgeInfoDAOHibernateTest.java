package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterAgeInfoDAO;
import com.itgrids.partyanalyst.model.VoterAgeInfo;

public class VoterAgeInfoDAOHibernateTest extends BaseDaoTestCase{
	
	private IVoterAgeInfoDAO voterAgeInfoDAO;

	public void setVoterAgeInfoDAO(IVoterAgeInfoDAO voterAgeInfoDAO) {
		this.voterAgeInfoDAO = voterAgeInfoDAO;
	}
	
	/*public void test()
	{
		voterAgeInfoDAO.getAll();
	}*/
	
	/*public void testGetVoterAgeInfoByPublicationDateAndReportLevelId()
	{
		List<VoterAgeInfo> voterAgeInfoList = voterAgeInfoDAO.getVoterAgeInfoByPublicationDateAndReportLevelId(1l, 232l, 7l);
		System.out.println(voterAgeInfoList.size());
		if(voterAgeInfoList != null && voterAgeInfoList.size() >0)
			for(VoterAgeInfo voterAgeInfo : voterAgeInfoList)
			{
				System.out.println("total Voters - "+voterAgeInfo.getTotalVoters()+"  Total Voters Percentage - "+voterAgeInfo.getTotalVotersPercentage()+"  Male Voters - "+voterAgeInfo.getMaleVoters()
						+"  Male Voter Percentage - "+voterAgeInfo.getMaleVotersPercentage());
				
				System.out.println(voterAgeInfo.getVoterAgeRange().getAgeRange());
			}
	}*/
	
	/*public void testGetAgewiseVoterDetailsInSpecifiedRangeByAgeRangeId()
	{
		List<VoterAgeInfo> voterAgeInfoList = voterAgeInfoDAO.getAgewiseVoterDetailsInSpecifiedRangeByAgeRangeId(1l, 232l, 7l, 1l);
		System.out.println(voterAgeInfoList.size());
		if(voterAgeInfoList != null && voterAgeInfoList.size() >0)
		{
			for(VoterAgeInfo voterAgeInfo : voterAgeInfoList)
			{
				System.out.println("total Voters - "+voterAgeInfo.getTotalVoters()+"  Total Voters Percentage - "+voterAgeInfo.getTotalVotersPercentage()+"  Male Voters - "+voterAgeInfo.getMaleVoters()
						+"  Male Voter Percentage - "+voterAgeInfo.getMaleVotersPercentage());
			}
		}
	}*/
	
	/*public void testGetVoterInfoIdByReportLevelValueAndReportLevelId()
	{
		System.out.println(voterAgeInfoDAO.getVoterInfoIdByReportLevelValueAndReportLevelId(1l, 232l,1l));
	}*/
	
	public void testDeleteVoterAgeInfoByReportLevelIdAndReportLevelValue()
	{
		System.out.println(voterAgeInfoDAO.deleteVoterAgeInfoByReportLevelIdAndReportLevelValue(1l, 232l, 7l, 1l));
	}

}
