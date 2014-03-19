package com.itgrids.partyanalyst.dao.hibernate;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterAgeInfoDAO;
import com.itgrids.partyanalyst.dto.VoterStratagicReportVo;

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
	
	/*public void testDeleteVoterAgeInfoByReportLevelIdAndReportLevelValue()
	{
		List<Long> reportLevelValue = new ArrayList<Long>(0);
		reportLevelValue.add(232l);
		System.out.println(voterAgeInfoDAO.deleteVoterAgeInfoByReportLevelIdAndReportLevelValue(1l, reportLevelValue, 7l));
	}*/
	
	/*public void testgetVoterAgeInfoByConstituencyId()
	{
		List<Long> list = voterAgeInfoDAO.getVoterAgeInfoByConstituencyId(232l, 8l, 2l);
		System.out.println(list.size());
	}*/
	
	/*public void testgetVoterAgeDetails()
	{
		List<Object[]> list = voterAgeInfoDAO.getVoterAgeDetails(232l, 8l, 2l);
		System.out.println(list.size()); 
		for(Object[] params : list)
			System.out.println(params[0] +" "+params[1]);
		
	}*/
	
	/*public void testgetVoterAgeInfoList()
	{
		voterAgeInfoDAO.getVoterAgeInfoList(232l);
	}*/
	
	/*public void testgetPanchayatWiseVoterDetailsForSuggestiveModel()
	{
		List<Object[]> values = voterAgeInfoDAO.getPanchayatWiseVoterDetailsForSuggestiveModel(228l, 8l,3l,1l);
		for (Object[] params : values) {
			System.out.println(params[0] +" "+params[1] +":"+ params[2] +":"+ params[3] +":"+ params[4]);
		}
	}
*/
	
	public void testgetVoterAgeInfoListByconstituency()
	{

		VoterStratagicReportVo voterStratagicReportVo = null;
		List<VoterStratagicReportVo> ageWiseReportVOList = null;
		try {
			DecimalFormat decimalFormat = new DecimalFormat("##.##");
			List<Object[]> voterAgeInfoList = voterAgeInfoDAO.getVoterAgeInfoListByconstituency(228l, 8l);
		
			if(voterAgeInfoList != null && voterAgeInfoList.size()>0){
				ageWiseReportVOList = new ArrayList<VoterStratagicReportVo>();
				voterStratagicReportVo = new VoterStratagicReportVo();
				Long totalVotersCount = 0L;
				
				for (Object[] voterAgeCount : voterAgeInfoList) {
					totalVotersCount = totalVotersCount + Long.valueOf(voterAgeCount[2].toString());
				}
				
				for (Object[] voterAge : voterAgeInfoList) {
					VoterStratagicReportVo agewiseReportVO = new VoterStratagicReportVo();
					Float totalCount = Float.valueOf(voterAge[3].toString());
					Double percentage = Double.valueOf(decimalFormat.format(totalCount*100/totalVotersCount));
					agewiseReportVO.setMaleVotersCount(totalCount.longValue());
					agewiseReportVO.setMaleTotalPercentage(percentage);
					
					totalCount = Float.valueOf(voterAge[4].toString());
					percentage = Double.valueOf(decimalFormat.format(totalCount*100/totalVotersCount));
					agewiseReportVO.setFemaleVotersCount(totalCount.longValue());
					agewiseReportVO.setFemaleTotalPercentage(percentage);
					
					totalCount = Float.valueOf(voterAge[2].toString());
					percentage = Double.valueOf(decimalFormat.format(totalCount*100/totalVotersCount));
					agewiseReportVO.setTotalVoters(totalCount.longValue());										
					agewiseReportVO.setTotalPercentage(percentage);
					
					ageWiseReportVOList.add(agewiseReportVO);
				}
			}
			
			voterStratagicReportVo.setVoterStategicReportVOList(ageWiseReportVOList);
		} catch (Exception e) {
			log.error(" exception occured in getAgeWiseVotersInfoByConstituency() of StratagicReportsService class. ",e);
		}
		
	}
	
}
