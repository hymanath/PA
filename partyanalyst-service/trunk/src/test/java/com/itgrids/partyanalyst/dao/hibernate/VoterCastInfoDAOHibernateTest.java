package com.itgrids.partyanalyst.dao.hibernate;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterCastInfoDAO;
import com.itgrids.partyanalyst.dto.CasteStratagicReportVO;
import com.itgrids.partyanalyst.dto.VoterCastInfoVO;
import com.itgrids.partyanalyst.model.VoterCastInfo;

public class VoterCastInfoDAOHibernateTest extends BaseDaoTestCase{

	private IVoterCastInfoDAO voterCastInfoDAO;

	public void setVoterCastInfoDAO(IVoterCastInfoDAO voterCastInfoDAO) {
		this.voterCastInfoDAO = voterCastInfoDAO;
	}
	
	/*public void test()
	{
		List<Long> ids = new ArrayList<Long>();
		ids.add(844l);
		List<Object[]> values = voterCastInfoDAO.getCastAndPartyForSelectedLevel(1l,2l,ids,8l);
		for (Object[] parms : values) {
			System.out.println(parms[0] +":"+ parms[1]);
		}
		
	}*/
	/*public void test()
	{
		
		List<Object[]> values = voterCastInfoDAO.getAllCasteInfoByUserId(1l);
		for (Object[] parms : values) {
			System.out.println(parms[0] +":"+ parms[1]);
		}
		
	}*/
	
	/*public void test()
	{
		List<Long> ids = new ArrayList<Long>();
		Set<Long> casteIds = new HashSet<Long>();
		ids.add(11l);
		ids.add(12l);
		ids.add(1l);
		ids.add(7l);
		ids.add(4l);
		ids.add(13l);
		ids.add(10l);
		ids.add(6l);
		ids.add(9l);
		ids.add(8l);
		ids.add(3l);
		ids.add(2l);
		ids.add(5l);
		casteIds.add(296l);
		casteIds.add(290l);
		casteIds.add(211l);
		casteIds.add(189l);
		casteIds.add(244l);
		List<Object[]> values = voterCastInfoDAO.getTopCasteFoeSelctedLevel(ids,3l,8l,1l,casteIds);
		for (Object[] parms : values) {
			System.out.println(parms[0]  +":"+ parms[1] +":"+ parms[2]);
		}
	}*/
	/*public void test()
	{
		List ids = new ArrayList<Long>();
		ids.add(835l);

		List<VoterCastInfo> values = voterCastInfoDAO.getVotersCastInfo(5l,83l,232l,8l,1l);
		System.out.println(values.size());
		for(VoterCastInfo voterCastInfo:values)
		{
			System.out.println(voterCastInfo.getCasteState().getCaste().getCasteName());
			System.out.println(voterCastInfo.getCasteVoters());
			System.out.println(voterCastInfo.getCastePercentage().toString());
		}
	}*/
	/*
	public void testgetTopThreeCasteForPanchayat(){
		
		List<Object[]> values = voterCastInfoDAO.getTopThreeCasteForPanchayat(5304l,3l,8l,1l);
		for (Object[] parms : values) {
			System.out.println(parms[0]  +":"+ parms[1] +":"+ parms[2]+":"+ parms[3] );
		}
	}
	*/
	/*	public void testgetVoterCasteInfoList()
	{
		List<VoterCastInfo> list = voterCastInfoDAO.getVoterCasteInfoList(232l,8l,1l);
		System.out.println(list.size());
		StringBuilder str = new StringBuilder();
		for(VoterCastInfo vCastInfo:list)
		{
			if(vCastInfo.getSubLeveCastePercentage() != null)
			str.append(""+vCastInfo.getSubLeveCastePercentage()+"");
		}
		System.out.println(str);
	}*/
	
	/*public void testgetCasteWiseCountDetails()
	{
		List<Object[]> values = voterCastInfoDAO.getCasteWiseCountDetails(5l,83l,8l,232l,296l,1l);
		for (Object[] parms : values) {
			System.out.println(parms[0]  +":"+ parms[1]  );
		}
	}*/
	
	public void testgetVoterCasteInfoListByConstituency()
	{
		/*List<Object[]> values = voterCastInfoDAO.getVoterCasteInfoListByConstituency(181L,10L,1L);
		for (Object[] parms : values) {
			System.out.println(parms[1]  +"  \n  :   "+parms[8]+ "  :  "+ parms[2] +"  :  "+ parms[3]+" :  "+parms[4]  +":  " +parms[5]+ "  :  "+parms[6]+ "  :  "+ Float.parseFloat(parms[7].toString()) +"\n"  );
		}*/
		
		DecimalFormat decimalFormat = new DecimalFormat("##.##");
		List<Object[]> votersCastInfo = voterCastInfoDAO.getVoterCasteInfoListByConstituency(181L,10L,1L);
		CasteStratagicReportVO stratagicVO = null;
		List<CasteStratagicReportVO> stratagicVOList = null;
		try{
		if(votersCastInfo != null && votersCastInfo.size()>0){
			stratagicVOList = new ArrayList<CasteStratagicReportVO>();
			stratagicVO = new CasteStratagicReportVO();
			Long otherCastesTotalCount = 0L;
			Long otherCastesMaleCount = 0L;
			Long otherCastesFemaleCount = 0L;
			Float totalCastePerce = 0.0F; 
			for(int i = 0 ;i<votersCastInfo.size();i++ ){
				Object[] casteVoter = votersCastInfo.get(i);
				
				System.out.println(casteVoter[1]  +"  \n  :   "+casteVoter[8]+ "  :  "+ casteVoter[2] +"  :  "+ casteVoter[3]+" :  "+casteVoter[4]  +":  " +casteVoter[5]+ "  :  "+casteVoter[6]+ "  :  "+ Float.parseFloat(casteVoter[7].toString()) +"\n"  );
				
				if(i <15){ // top 15 caste Information
									
					CasteStratagicReportVO casteReportVO = new CasteStratagicReportVO();
					
					casteReportVO.setCaste(casteVoter[1].toString());
					casteReportVO.setCasteCategory(casteVoter[3].toString());
					casteReportVO.setTotalCasteVoters(Long.valueOf(casteVoter[4].toString()));
					casteReportVO.setMaleCasteVoters(Long.valueOf(casteVoter[5].toString()));
					casteReportVO.setFemaleCasteVoters(Long.valueOf(casteVoter[6].toString()));
					
					String perc = decimalFormat.format(Double.valueOf(casteVoter[7].toString()));
					casteReportVO.setCastePercentage(Float.parseFloat(perc));
					
					totalCastePerce = totalCastePerce + Float.valueOf(casteVoter[7].toString());
					
					stratagicVOList.add(casteReportVO);
				}
				else{ // other castes Info
					
					otherCastesTotalCount = otherCastesTotalCount + Long.valueOf(casteVoter[4].toString());
					otherCastesMaleCount = otherCastesMaleCount + Long.valueOf(casteVoter[5].toString());
					otherCastesFemaleCount = otherCastesFemaleCount + Long.valueOf(casteVoter[6].toString());
					
					
				}
			}
			
			if(otherCastesTotalCount != 0){
				totalCastePerce = (100.0F - totalCastePerce);
				CasteStratagicReportVO casteReportVO = new CasteStratagicReportVO();
				casteReportVO.setCaste("");
				casteReportVO.setCasteCategory("");
				casteReportVO.setTotalCasteVoters(otherCastesTotalCount);
				casteReportVO.setMaleCasteVoters(Long.valueOf(otherCastesMaleCount));
				casteReportVO.setFemaleCasteVoters(Long.valueOf(otherCastesFemaleCount));
				
				String perc = decimalFormat.format(Double.valueOf(totalCastePerce));
				casteReportVO.setCastePercentage(Float.parseFloat(perc));
				
				stratagicVOList.add(casteReportVO);
			}
			stratagicVO.setStrategicVOList(stratagicVOList);
			stratagicVO.setHeading(" Voters by Caste ");
			}			
			
		} catch (Exception e) {
			//G.error(" exception occured in getCasteWiseVotersInfoByConstituency() of StratagicReportServiceForMLASuccess class. ",e);
		}

	}
	
}
