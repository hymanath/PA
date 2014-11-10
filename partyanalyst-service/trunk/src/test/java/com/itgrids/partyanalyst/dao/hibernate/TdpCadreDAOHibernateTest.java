package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ITdpCadreDAO;

public class TdpCadreDAOHibernateTest extends BaseDaoTestCase {
	private ITdpCadreDAO tdpCadreDAO;
	
	public ITdpCadreDAO getTdpCadreDAO() {
		return tdpCadreDAO;
	}



	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}

/*	public void testgetConstituencyWiseAgeRangeCount(){
		
		List<List<CadreRegisterInfo>> returnResult = new ArrayList();	
		
		List<CadreRegisterInfo> cadreInfo2012=new ArrayList<CadreRegisterInfo>();		
		List<CadreRegisterInfo> cadreInfo2014=new ArrayList<CadreRegisterInfo>();	
		
		List<Object[]> cadre18to25info = tdpCadreDAO.getConstituencyWiseAgeRangeCadreCount(34l,"18-25");		
		List<Object[]> cadre26to35info = tdpCadreDAO.getConstituencyWiseAgeRangeCadreCount(34l,("26-35"));			
		List<Object[]> cadre36to45info = tdpCadreDAO.getConstituencyWiseAgeRangeCadreCount(34l,("36-45"));			
		List<Object[]> cadre46to60info = tdpCadreDAO.getConstituencyWiseAgeRangeCadreCount(34l,("46-60"));			
		List<Object[]> cadreabove60info = tdpCadreDAO.getConstituencyWiseAgeRangeCadreCount(34l,("above 60"));	
		Long totalConstituencyCount2012= tdpCadreDAO.getConstituencyWiseYearCount(34l, 2012L);	
		Long totalConstituencyCount2014= tdpCadreDAO.getConstituencyWiseYearCount(34l, 2014L);	
	    setAgeWiseRangeCount(cadre18to25info,cadre26to35info,cadre36to45info,cadre46to60info,cadreabove60info,totalConstituencyCount2012,totalConstituencyCount2014,cadreInfo2012,cadreInfo2014);	    
	    returnResult.add(cadreInfo2012);
	    returnResult.add(cadreInfo2014);
	    
	    for(List<CadreRegisterInfo> vo:returnResult){
	    	for(CadreRegisterInfo vo1:vo){
	    		System.out.println(vo1.getTotalCount()+"..."+vo1.getPercentage());
	    	}
	    	
	    }
	    
	    //return returnResult;
	
    }

	public void testgetDistrictWiseAgeRangeCount(){
		
		List<List<CadreRegisterInfo>> returnResult = new ArrayList();	
		
		List<CadreRegisterInfo> cadreInfo2012=new ArrayList<CadreRegisterInfo>();		
		List<CadreRegisterInfo> cadreInfo2014=new ArrayList<CadreRegisterInfo>();	
		
		List<Object[]> cadre18to25info = tdpCadreDAO.getDistrictWiseAgeRangeCadreCount(4l,"18-25");		
		List<Object[]> cadre26to35info = tdpCadreDAO.getDistrictWiseAgeRangeCadreCount(4l,("26-35"));			
		List<Object[]> cadre36to45info = tdpCadreDAO.getDistrictWiseAgeRangeCadreCount(4l,("36-45"));			
		List<Object[]> cadre46to60info = tdpCadreDAO.getDistrictWiseAgeRangeCadreCount(4l,("46-60"));			
		List<Object[]> cadreabove60info = tdpCadreDAO.getDistrictWiseAgeRangeCadreCount(4l,("above 60"));	
		Long totalDistrictCount2012= tdpCadreDAO.getDistrictWiseYearCount(4l, 2012L);	
		Long totalDistrictCount2014= tdpCadreDAO.getDistrictWiseYearCount(4l, 2014L);	
	    setAgeWiseRangeCount(cadre18to25info,cadre26to35info,cadre36to45info,cadre46to60info,cadreabove60info,totalDistrictCount2012,totalDistrictCount2014,cadreInfo2012,cadreInfo2014);	    
	    returnResult.add(cadreInfo2012);
	    returnResult.add(cadreInfo2014);
	    
	    for(List<CadreRegisterInfo> vo:returnResult){
	    	for(CadreRegisterInfo vo1:vo){
	    		System.out.println(vo1.getTotalCount()+"..."+vo1.getPercentage());
	    	}
	    	
	    }
	    
	    //return returnResult;
	
    }

public static void	setAgeWiseRangeCount(List<Object[]> cadre18to25info,List<Object[]> cadre26to35info,List<Object[]> cadre36to45info,List<Object[]> cadre46to60info,List<Object[]> cadreabove60info,Long totalCount2012,Long totalCount2014,List<CadreRegisterInfo> cadreInfo2012,List<CadreRegisterInfo> cadreInfo2014){
	CadreRegisterInfo vo1=null;
	
	if(cadre18to25info.size() > 0){
		for(Object[] cadre18to25info1:cadre18to25info){
			if((Long)cadre18to25info1[1]==2012){				
				vo1=new CadreRegisterInfo();
				Long total=(Long)cadre18to25info1[0];
				vo1.setTotalCount(total);
				String b=new BigDecimal((total.doubleValue()/totalCount2012.doubleValue())*100).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				Double per=new Double(b);
				Long percentage=per.longValue();
				vo1.setPercentage(percentage);
				cadreInfo2012.add(vo1);				
			}
			if((Long)cadre18to25info1[1]==2014){
				vo1=new CadreRegisterInfo();
				Long total=(Long)cadre18to25info1[0];
				vo1.setTotalCount(total);				
				String b=new BigDecimal((total.doubleValue()/totalCount2014.doubleValue())*100).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				Double per=new Double(b);
				Long percentage=per.longValue();
				vo1.setPercentage(percentage);
				cadreInfo2014.add(vo1);				    
			}
		}
	}
	
	if(cadre26to35info.size() > 0){
		for(Object[] cadre26to35info1:cadre26to35info){
			if((Long)cadre26to35info1[1]==2012){				
				vo1=new CadreRegisterInfo();
				Long total=(Long)cadre26to35info1[0];
				vo1.setTotalCount(total);
				String b=new BigDecimal((total.doubleValue()/totalCount2012.doubleValue())*100).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				Double per=new Double(b);
				Long percentage=per.longValue();
				vo1.setPercentage(percentage);
				cadreInfo2012.add(vo1);				
			}
			if((Long)cadre26to35info1[1]==2014){
				vo1=new CadreRegisterInfo();
				Long total=(Long)cadre26to35info1[0];
				vo1.setTotalCount(total);				
				String b=new BigDecimal((total.doubleValue()/totalCount2014.doubleValue())*100).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				Double per=new Double(b);
				Long percentage=per.longValue();
				vo1.setPercentage(percentage);
				cadreInfo2014.add(vo1);				    
			}
		}
	}
	
	
	if(cadre36to45info.size() > 0){
		for(Object[] cadre36to45info1:cadre36to45info){
			if((Long)cadre36to45info1[1]==2012){				
				vo1=new CadreRegisterInfo();
				Long total=(Long)cadre36to45info1[0];
				vo1.setTotalCount(total);
				String b=new BigDecimal((total.doubleValue()/totalCount2012.doubleValue())*100).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				Double per=new Double(b);
				Long percentage=per.longValue();
				vo1.setPercentage(percentage);
				cadreInfo2012.add(vo1);				
			}
			if((Long)cadre36to45info1[1]==2014){
				vo1=new CadreRegisterInfo();
				Long total=(Long)cadre36to45info1[0];
				vo1.setTotalCount(total);				
				String b=new BigDecimal((total.doubleValue()/totalCount2014.doubleValue())*100).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				Double per=new Double(b);
				Long percentage=per.longValue();
				vo1.setPercentage(percentage);
				cadreInfo2014.add(vo1);				    
			}
		}
	}
	
	if(cadre46to60info.size() > 0){
		for(Object[] cadre46to60info1:cadre46to60info){
			if((Long)cadre46to60info1[1]==2012){				
				vo1=new CadreRegisterInfo();
				Long total=(Long)cadre46to60info1[0];
				vo1.setTotalCount(total);
				String b=new BigDecimal((total.doubleValue()/totalCount2012.doubleValue())*100).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				Double per=new Double(b);
				Long percentage=per.longValue();
				vo1.setPercentage(percentage);
				cadreInfo2012.add(vo1);				
			}
			if((Long)cadre46to60info1[1]==2014){
				vo1=new CadreRegisterInfo();
				Long total=(Long)cadre46to60info1[0];
				vo1.setTotalCount(total);				
				String b=new BigDecimal((total.doubleValue()/totalCount2014.doubleValue())*100).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				Double per=new Double(b);
				Long percentage=per.longValue();
				vo1.setPercentage(percentage);
				cadreInfo2014.add(vo1);				    
			}				
		}
	}
	
	
	if(cadreabove60info.size() > 0){
		for(Object[] cadreabove60info1:cadreabove60info){
			if((Long)cadreabove60info1[1]==2012){				
				vo1=new CadreRegisterInfo();
				Long total=(Long)cadreabove60info1[0];
				vo1.setTotalCount(total);
				String b=new BigDecimal((total.doubleValue()/totalCount2012.doubleValue())*100).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				Double per=new Double(b);
				Long percentage=per.longValue();
				vo1.setPercentage(percentage);
				cadreInfo2012.add(vo1);				
			}
			if((Long)cadreabove60info1[1]==2014){
				vo1=new CadreRegisterInfo();
				Long total=(Long)cadreabove60info1[0];
				vo1.setTotalCount(total);				
				String b=new BigDecimal((total.doubleValue()/totalCount2014.doubleValue())*100).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				Double per=new Double(b);
				Long percentage=per.longValue();
				vo1.setPercentage(percentage);
				cadreInfo2014.add(vo1);				    
			}
		}
	}
	
  }*/
	
	/*public void testgetConstituencyWiseAgeRangeCount(){
	
	List<List<CadreRegisterInfo>> returnResult = new ArrayList();	
	
	List<CadreRegisterInfo> cadreInfo2012=new ArrayList<CadreRegisterInfo>();		
	List<CadreRegisterInfo> cadreInfo2014=new ArrayList<CadreRegisterInfo>();	
	
	List<Object[]> cadreMaleInfo = tdpCadreDAO.getConstituencyWiseGenderCadreCount(34l,"Male");		
	List<Object[]> cadreFemaleInfo = tdpCadreDAO.getConstituencyWiseAgeRangeCadreCount(34l,("26-35"));			
	List<Object[]> cadre36to45info = tdpCadreDAO.getConstituencyWiseAgeRangeCadreCount(34l,("36-45"));			
	List<Object[]> cadre46to60info = tdpCadreDAO.getConstituencyWiseAgeRangeCadreCount(34l,("46-60"));			
	List<Object[]> cadreabove60info = tdpCadreDAO.getConstituencyWiseAgeRangeCadreCount(34l,("above 60"));	
	Long totalConstituencyCount2012= tdpCadreDAO.getConstituencyWiseYearCount(34l, 2012L);	
	Long totalConstituencyCount2014= tdpCadreDAO.getConstituencyWiseYearCount(34l, 2014L);	
    //setAgeWiseRangeCount(cadre18to25info,cadre26to35info,cadre36to45info,cadre46to60info,cadreabove60info,totalConstituencyCount2012,totalConstituencyCount2014,cadreInfo2012,cadreInfo2014);	    
    returnResult.add(cadreInfo2012);
    returnResult.add(cadreInfo2014);
    
    for(List<CadreRegisterInfo> vo:returnResult){
    	for(CadreRegisterInfo vo1:vo){
    		System.out.println(vo1.getTotalCount()+"..."+vo1.getPercentage());
    	}
    	
    }
    
    //return returnResult;

}*/

/*public void testgetDistrictWiseAgeRangeCount(){
	
	List<List<CadreRegisterInfo>> returnResult = new ArrayList();	
	
	List<CadreRegisterInfo> cadreInfo2012=new ArrayList<CadreRegisterInfo>();		
	List<CadreRegisterInfo> cadreInfo2014=new ArrayList<CadreRegisterInfo>();	
	
	List<Object[]> cadre18to25info = tdpCadreDAO.getDistrictWiseAgeRangeCadreCount(4l,"18-25");		
	List<Object[]> cadre26to35info = tdpCadreDAO.getDistrictWiseAgeRangeCadreCount(4l,("26-35"));			
	List<Object[]> cadre36to45info = tdpCadreDAO.getDistrictWiseAgeRangeCadreCount(4l,("36-45"));			
	List<Object[]> cadre46to60info = tdpCadreDAO.getDistrictWiseAgeRangeCadreCount(4l,("46-60"));			
	List<Object[]> cadreabove60info = tdpCadreDAO.getDistrictWiseAgeRangeCadreCount(4l,("above 60"));	
	Long totalDistrictCount2012= tdpCadreDAO.getDistrictWiseYearCount(4l, 2012L);	
	Long totalDistrictCount2014= tdpCadreDAO.getDistrictWiseYearCount(4l, 2014L);	
    //setAgeWiseRangeCount(cadre18to25info,cadre26to35info,cadre36to45info,cadre46to60info,cadreabove60info,totalDistrictCount2012,totalDistrictCount2014,cadreInfo2012,cadreInfo2014);	    
    returnResult.add(cadreInfo2012);
    returnResult.add(cadreInfo2014);
    
    for(List<CadreRegisterInfo> vo:returnResult){
    	for(CadreRegisterInfo vo1:vo){
    		System.out.println(vo1.getTotalCount()+"..."+vo1.getPercentage());
    	}
    	
    }
    
    //return returnResult;

}	*/
	
/*	public void testgetConstituencyWiseGenderCadreCount(){
		List<Object[]> genderInfoList=tdpCadreDAO.getConstituencyWiseGenderCadreCount(34L);
		Map<Long,List<CadreRegisterInfo>> genderMap = new HashMap<Long,List<CadreRegisterInfo>>();
		
		setGenderWiseCount(genderInfoList,genderMap);
		
		System.out.println(genderMap);
		List<CadreRegisterInfo> genderInfo2012 =genderMap.get(2012L);
		List<CadreRegisterInfo> genderInfo2014 =genderMap.get(2014L);
		System.out.println(genderInfo2012);
		System.out.println(genderInfo2014);
	}*/
	
/*	public void testgetDistrictWiseGenderCadreCount(){
		List<Object[]> genderInfoList=tdpCadreDAO.getDistrictWiseGenderCadreCount(4L);
		Map<Long,List<CadreRegisterInfo>> genderMap = new HashMap<Long,List<CadreRegisterInfo>>();
		
		setGenderWiseCount(genderInfoList,genderMap);
		
		System.out.println(genderMap);
		List<CadreRegisterInfo> genderInfo2012 =genderMap.get(2012L);
		List<CadreRegisterInfo> genderInfo2014 =genderMap.get(2014L);
		System.out.println(genderInfo2012);
		System.out.println(genderInfo2014);
	}
	
	public static void setGenderWiseCount(List<Object[]> genderInfoList,Map<Long,List<CadreRegisterInfo>> genderMap){
		List<CadreRegisterInfo> genderInfo2012 = new ArrayList<CadreRegisterInfo>();
		List<CadreRegisterInfo> genderInfo2014 = new ArrayList<CadreRegisterInfo>();
		CadreRegisterInfo genderVO=null;
		//totalCount for count
		//apCount for year
		//name for Gender
		for(Object[] gender:genderInfoList){
			if((Long)gender[1]==2012){
				genderVO=new CadreRegisterInfo();
				genderVO.setTotalCount((Long)gender[0]);
				genderVO.setApCount((Long)gender[1]);
				genderVO.setName((String)gender[2]);
				genderInfo2012.add(genderVO);
			}
			if((Long)gender[1]==2014){
				genderVO=new CadreRegisterInfo();
				genderVO.setTotalCount((Long)gender[0]);
				genderVO.setApCount((Long)gender[1]);
				genderVO.setName((String)gender[2]);
				genderInfo2014.add(genderVO);				
			}
		}
		genderMap.put(2012L, genderInfo2012);	
		genderMap.put(2014L, genderInfo2014);
	}*/
	
/*	public void testgetConstituencyWiseCastCadreCount(){
		List<Object[]> casteInfoList = tdpCadreDAO.getConstituencyWiseCastCadreCount(34L);
		Map<Long,CadreRegisterInfo> casteMap = new HashMap<Long,CadreRegisterInfo>();
		List<CadreRegisterInfo> genderInfo2012 = new ArrayList<CadreRegisterInfo>();
		List<CadreRegisterInfo> genderInfo2014 = new ArrayList<CadreRegisterInfo>();
		
		for(Object[] caste:casteInfoList){
			System.out.println(caste[0]+"..."+caste[1]+"..."+caste[2]+"..."+caste[3]);
		}
		
	}*/
	
	/*public void testgetDistrictWiseCastCadreCount(){
		Map<Long,List<CadreRegisterInfo>> casteMap = new HashMap<Long,List<CadreRegisterInfo>>();

			//List<Object[]> casteInfoList = tdpCadreDAO.getDistrictWiseCastCadreCount(4L);
			//setCasteWiseCount(casteInfoList, casteMap);
	
		
	}
	
	public static void setCasteWiseCount(List<Object[]> casteInfoList,Map<Long,List<CadreRegisterInfo>> casteMap){
		List<CadreRegisterInfo> genderInfo2012 = new ArrayList<CadreRegisterInfo>();
		List<CadreRegisterInfo> genderInfo2014 = new ArrayList<CadreRegisterInfo>();
		CadreRegisterInfo genderVO=null;
		//totalCount for count
		//apCount for entrolmentyear
		//tgCount for casteStateId
		//name for casteName
		for(Object[] caste:casteInfoList){
			if((Long)caste[1]==2012){
				genderVO=new CadreRegisterInfo();
				genderVO.setTotalCount((Long)caste[0]);
				genderVO.setApCount((Long)caste[1]);
				genderVO.setTgCount((Long)caste[2]);
				genderVO.setName((String)caste[3]);
				genderInfo2012.add(genderVO);
			}
			if((Long)caste[1]==2014){
				genderVO=new CadreRegisterInfo();
				genderVO.setTotalCount((Long)caste[0]);
				genderVO.setApCount((Long)caste[1]);
				genderVO.setTgCount((Long)caste[2]);
				genderVO.setName((String)caste[3]);
				genderInfo2014.add(genderVO);				
			}
		}
		casteMap.put(2012L, genderInfo2012);	
		casteMap.put(2014L, genderInfo2014);
	}*/
	/*
	public void test(){
		
		List<Long> locationIds = new ArrayList<Long>();
		List<Long> locationIds1 = new ArrayList<Long>();
		
		locationIds.add(7745l);
		locationIds1.add(439807l);
		locationIds1.add(439808l);
		
		List<Object[]> list =  tdpCadreDAO.getPanchayatWiseCadreInfoCount(locationIds);
		List<Object[]> list1 = tdpCadreDAO.getBoothWiseCadreInfoCount(locationIds1);
		
	}
	
*/
	
	/*public void testDteaisl()
	{
	try {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date fromDate =  null;
		Date toDate = null;
		String fromDateStr = "15-10-2014";
		String toDateStr = "24-10-2014";
		
		if(fromDateStr != null && fromDateStr.trim().length()>0)
		{
			fromDate = format.parse(fromDateStr);
		}
		if(toDateStr != null && toDateStr.trim().length()>0)
		{
			toDate = format.parse(toDateStr);
		}
	
	List<Object[] > list = tdpCadreDAO.getEnrollmentYearWiseDetails(282L, fromDate, toDate, 2014L);
	System.out.println(list.size());
	
} catch (Exception e) {
	e.printStackTrace();
}
	
	}*/
	
	/*public void testGetTdpCadreDetailsBySearchCriteria()
	{
		List<Object[]> list = tdpCadreDAO.getTdpCadreDetailsBySearchCriteria( "TR-T-3025-22199", "9666272968");
		System.out.println(list);
		
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String dt = "2014-10-02";
			Date fromDate = format.parse(dt);
			
			String fdt = "2014-10-30";
			Date toDate = format.parse(fdt);
			
			List<Object[]> list = tdpCadreDAO.getCandidateDataCollected(fromDate, toDate,);
			System.out.println(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/
	
	public void test(){
		List<Long> dist = new ArrayList<Long>();
		dist.add(18l);
		dist.add(17l);
		List<Long> dist1 = new ArrayList<Long>();
		dist1.add(217l);
		dist1.add(213l);
		dist1.add(209l);
		dist1.add(228l);
		dist1.add(218l);
		dist1.add(219l);
		dist1.add(229l);
		List<Object[]> list = tdpCadreDAO.getCadreInfoDistrictConstiWise(dist, null, null, 2014l, dist1);
		System.out.println(list.size());
	}
}
