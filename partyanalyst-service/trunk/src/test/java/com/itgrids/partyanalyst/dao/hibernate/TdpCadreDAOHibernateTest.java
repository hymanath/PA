package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ICadreRegAmountDetailsDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IPartyPresidentsDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeElectrolsDAO;
import com.itgrids.partyanalyst.service.ICadreSurveyTransactionService;
import com.itgrids.partyanalyst.service.impl.CadreDashBoardService;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class TdpCadreDAOHibernateTest extends BaseDaoTestCase {
	private ITdpCadreDAO tdpCadreDAO;
	private CadreDashBoardService cadreDashBoardService;
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	private ICadreRegAmountDetailsDAO cadreRegAmountDetailsDAO;
	private IBoothDAO boothDAO;
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	private DateUtilService dateService = new DateUtilService();
	private IConstituencyDAO constituencyDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private IPartyPresidentsDAO partyPresidentsDAO;
	private ICadreSurveyTransactionService cadreSurveyTransactionService;
	private ITdpCommitteeElectrolsDAO tdpCommitteeElectrolsDAO;
	
	
	
	
	public ITdpCommitteeElectrolsDAO getTdpCommitteeElectrolsDAO() {
		return tdpCommitteeElectrolsDAO;
	}

	public void setTdpCommitteeElectrolsDAO(
			ITdpCommitteeElectrolsDAO tdpCommitteeElectrolsDAO) {
		this.tdpCommitteeElectrolsDAO = tdpCommitteeElectrolsDAO;
	}

	public void setCadreSurveyTransactionService(
			ICadreSurveyTransactionService cadreSurveyTransactionService) {
		this.cadreSurveyTransactionService = cadreSurveyTransactionService;
	}

	public void setPartyPresidentsDAO(IPartyPresidentsDAO partyPresidentsDAO) {
		this.partyPresidentsDAO = partyPresidentsDAO;
	}

	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public void setBoothPublicationVoterDAO(
			IBoothPublicationVoterDAO boothPublicationVoterDAO) {
		this.boothPublicationVoterDAO = boothPublicationVoterDAO;
	}

	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}

	public void setCadreRegAmountDetailsDAO(
			ICadreRegAmountDetailsDAO cadreRegAmountDetailsDAO) {
		this.cadreRegAmountDetailsDAO = cadreRegAmountDetailsDAO;
	}

	public IDelimitationConstituencyAssemblyDetailsDAO getDelimitationConstituencyAssemblyDetailsDAO() {
		return delimitationConstituencyAssemblyDetailsDAO;
	}

	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}

	public ITdpCadreDAO getTdpCadreDAO() {
		return tdpCadreDAO;
	}



	public CadreDashBoardService getCadreDashBoardService() {
		return cadreDashBoardService;
	}



	public void setCadreDashBoardService(CadreDashBoardService cadreDashBoardService) {
		this.cadreDashBoardService = cadreDashBoardService;
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
	
	/*public void test(){
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
	}*/
	
/*	public void testgetAnalysisData(){
		List<Long> dist = new ArrayList<Long>();
		dist.add(18l);
		dist.add(17l);
		
		List<Object[]> list = tdpCadreDAO.getTotalRecords(dist,"DISTRICT");
		System.out.println(list.size());
	}
	*/
	/*
	public void testgetAnalysisData(){
		List<Long> locationIds = new ArrayList<Long>();
		/*locationIds.add(315L);
		locationIds.add(168L);
		locationIds.add(339L);
		locationIds.add(229L);
		locationIds.add(199L);*/
	/*	
		locationIds.add(15L);
		locationIds.add(16L);
		locationIds.add(17L);
		locationIds.add(18L);
		locationIds.add(5L);
		*/
		
	/*	locationIds.add(461L);
		locationIds.add(463L);
		locationIds.add(464L);
		locationIds.add(465L);
		locationIds.add(466L);
		locationIds.add(467L);
		locationIds.add(469L);
		locationIds.add(471L);
		locationIds.add(472L);
		locationIds.add(473L);
		locationIds.add(474L);
		locationIds.add(476L);
		locationIds.add(477L);
		locationIds.add(478L);
		locationIds.add(479L);
		locationIds.add(480L);
		locationIds.add(481L);
		locationIds.add(482L);
		locationIds.add(483L);
		locationIds.add(484L);
		locationIds.add(485L);
		locationIds.add(486L);
		locationIds.add(487L);
		locationIds.add(489L);
		locationIds.add(490L);
		locationIds.add(491L);
		locationIds.add(493L);
		locationIds.add(494L);
		locationIds.add(495L);
		locationIds.add(496L);
		locationIds.add(497L);
		locationIds.add(499L);
		locationIds.add(500L);
		locationIds.add(501L);
		locationIds.add(502L);
		locationIds.add(504L);
		locationIds.add(506L);
		locationIds.add(507L);
		locationIds.add(508L);
		locationIds.add(509L);
		locationIds.add(510L);
		locationIds.add(511L);

		
		List<Object[]> list = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date fdate = format.parse("2014-11-12");
			Date tDate = format.parse("2014-11-12");
			
			StringBuilder queryStr = new StringBuilder();
			
			/*queryStr.append(" select  count(model.tdpCadreId),min(model.surveyTime),max(model.surveyTime),date(model.surveyTime),model2.constituency.constituencyId,model2.constituency.name ");		
			queryStr.append(" from TdpCadre model,UserAddress model2 where model.enrollmentYear = 2014  " );
			queryStr.append(" and model.isDeleted = 'N' and ( date(model.surveyTime) >=:fromDate and date(model.surveyTime) <=:toDate  ) and ");
			queryStr.append(" model.userAddress.userAddressId = model2.userAddressId ");
			queryStr.append(" and model.dataSourceType = :sourceType and model2.constituency.constituencyId in (:locationIds)  ");
			queryStr.append(" group by date(model.surveyTime), model2.constituency.constituencyId order by date(model.surveyTime) ");
			*/
			
			/*queryStr.append(" select  count(model.tdpCadreId),min(model.surveyTime),max(model.surveyTime),date(model.surveyTime),model2.district.districtId,model2.district.districtName ");		
			queryStr.append(" from TdpCadre model,UserAddress model2 where model.enrollmentYear = 2014  " );
			queryStr.append(" and model.isDeleted = 'N' and ( date(model.surveyTime) >=:fromDate and date(model.surveyTime) <=:toDate  ) and ");
			queryStr.append(" model.userAddress.userAddressId = model2.userAddressId ");
			queryStr.append(" and model.dataSourceType = :sourceType and model2.district.districtId in (:locationIds)  ");
			queryStr.append(" group by date(model.surveyTime), model2.district.districtId order by date(model.surveyTime) "); 
			*/
			
			//	locationIds = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesByParliamentList(locationIds);
				
			 //dataCollectedInfo = tdpCadreDAO.getCandidateDataCollectionInfo1(3L,assemblyIdsList,fromDate, toDate,sourceType);
			 					
			/* 	queryStr.append(" select  count(model.tdpCadreId), min(model.surveyTime), max(model.surveyTime), date(model.surveyTime), " );
			 	queryStr.append(" model3.delimitationConstituency.constituency.constituencyId, model3.delimitationConstituency.constituency.name ");		
				queryStr.append(" from TdpCadre model, UserAddress model2, DelimitationConstituencyAssemblyDetails model3 where model.enrollmentYear = 2014  " );
				queryStr.append(" and model.isDeleted = 'N' and ( date(model.surveyTime) >=:fromDate and date(model.surveyTime) <=:toDate  ) ");						
				queryStr.append(" and model.dataSourceType = :sourceType and model3.delimitationConstituency.constituency.constituencyId in (:locationIds)  and ");
				queryStr.append(" model.userAddress.userAddressId = model2.userAddressId and model2.constituency.constituencyId = model3.delimitationConstituency.constituency.constituencyId ");
				queryStr.append(" and model3.delimitationConstituency.year = 2009 ");						
				queryStr.append(" group by date(model.surveyTime), model3.delimitationConstituency.constituency.constituencyId order by date(model.surveyTime) desc "); 
				
	
				
			list = tdpCadreDAO.getCandidateDataCollectionInfoForOnline(0L,locationIds,fdate,tDate,"ONLINE",queryStr.toString());
			
			System.out.println(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(list);
		
	}
	*/
	/*
	public void testgetDaywiseWebuserDetailsByUser()
	{
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String FdateStr ="01-11-2014";
		String TdateStr = "15-11-2014";
		SurveyTransactionVO returnVO = new SurveyTransactionVO();
		
		try {
			List<SurveyTransactionVO> finalList = new ArrayList<SurveyTransactionVO>();
			List<SurveyTransactionVO> returnList = new ArrayList<SurveyTransactionVO>();
			Date fromDate = format.parse(FdateStr);
			Date toDate = format.parse(TdateStr);
			Map<String,Long> dateWiseAmountMap = new LinkedHashMap<String, Long>();
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(fromDate);
			while (cal.getTime().before(toDate)) {
			    cal.add(Calendar.DATE, 1);
			    dateWiseAmountMap.put(format.format(cal.getTime()), 0L);
			}
			
			
			List<Object[]> acountInfo = cadreRegAmountDetailsDAO.getPaidAmountDetailsOfWebUserByDateANDType(2189L, fromDate, toDate,"TAB");
			
			
			if(acountInfo != null && acountInfo.size()>0)
			{
				for (Object[] amount : acountInfo) 
				{
					String Date = amount[0] != null ? format.format(format1.parse(amount[0].toString().trim())):"";					
					
						dateWiseAmountMap.put(Date, amount[1] != null ? Long.valueOf(amount[1].toString().trim()):0L);
				
				}
			}
			
			List<Object[]> webUserRecordsList = tdpCadreDAO.getDaywiseWebuserDetailsByUserANDType(4197L, fromDate, toDate,"WEB");
			
			Long recordsCount = 0L;
			Long actualAmount = 0L;
			Long depositedAmount = 0L;
			Long remainingAmount = 0L;
					
			if(webUserRecordsList != null && webUserRecordsList.size()>0)
			{
				
				for (Object[] param : webUserRecordsList)
				{
					String Date = param[0] != null ? format.format(format1.parse(param[0].toString().trim())):"";
					SurveyTransactionVO vo = new SurveyTransactionVO();
					
					vo.setSurveyDate(Date);
					vo.setRecordsCount(param[1] != null ? Long.valueOf(param[1].toString()):0L);
					vo.setActualAmount(vo.getRecordsCount() * 100);					
					vo.setDepositedAmount(dateWiseAmountMap.get(Date));
					vo.setRemainingAmount(vo.getActualAmount() - vo.getDepositedAmount());
					
					returnList.add(vo);
					
					recordsCount = recordsCount + vo.getRecordsCount();
					actualAmount = actualAmount + vo.getActualAmount();
					depositedAmount = depositedAmount + vo.getDepositedAmount();
					remainingAmount = remainingAmount + vo.getRemainingAmount();
					
				}
			}
						
			if(dateWiseAmountMap != null && dateWiseAmountMap.size()>0)
			{
				for (String date : dateWiseAmountMap.keySet()) 
				{
					SurveyTransactionVO vo = getMatchedVOForDate(returnList,date);
					
					if(vo == null)
					{
						vo = new SurveyTransactionVO();							
						vo.setSurveyDate(date);
						vo.setRecordsCount(0L);
						vo.setActualAmount(0L);					
						vo.setDepositedAmount(0L);
						vo.setRemainingAmount(0L);
					}
					
					finalList.add(vo);
				}
			}
			
			returnVO.setRecordsCount(recordsCount);
			returnVO.setActualAmount(actualAmount);
			returnVO.setDepositedAmount(depositedAmount);
			returnVO.setRemainingAmount(remainingAmount);
			
			returnVO.setSurveyTransactionVOList(finalList);
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	

	private SurveyTransactionVO getMatchedVOForDate(List<SurveyTransactionVO> list, String date)
	{
		SurveyTransactionVO returnVO = null;
		
		try {
			if(list != null && list.size()>0)
			{
				for (SurveyTransactionVO vo : list) 
				{
					if(vo.getSurveyDate().trim().equalsIgnoreCase(date.trim()))
					{
						return vo;
					}
				}
			}
		} catch (Exception e) {
			
		}
		
		return returnVO;
	}*/
	
/*	public void test()
	{
		List<Object[]> list = tdpCadreDAO.getCadreCountInMinorities(282l,"constituency");
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println();
			for(Object obj : params)
				System.out.print("\t"+obj);
		}
	}*/
	
	/*public void testgetTotalRegisteredCadreCountByLocation()
	{
		List<Long> locationIds = new ArrayList<Long>();
		locationIds.add(209L);
		locationIds.add(213L);
		locationIds.add(217L);
		locationIds.add(228L);
		locationIds.add(229L);
		locationIds.add(218L);
		locationIds.add(219L);
		
		
		List<Object[]> list =  tdpCadreDAO.getTotalRecordsByAccessType(locationIds, "", null, null);
		System.out.println(list.size());
		
		Long count = tdpCadreDAO.getTotalRegisteredCadreCountByLocation(locationIds," select count(TC.tdpCadreId) from TdpCadre TC where TC.userAddress.district.districtId in (:locationIds) and TC.isDeleted='N' and TC.enrollmentYear = 2014 ");
		System.out.println(count);
		
		
	}*/

	/*public void testDetails()
	{
		try {
			List<Long> districtIds = new ArrayList<Long>();
			districtIds.add(1L);
			districtIds.add(2L);
			districtIds.add(3L);
			districtIds.add(4L);
			districtIds.add(5L);
			//districtIds.add(6L);
			//districtIds.add(7L);
			//districtIds.add(8L);
			//districtIds.add(9L);
			//districtIds.add(10L);
			cadreSurveyTransactionService.sendTargetBasedSMSforLocationWiseManagers(districtIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}*/
	
	/*public void testGetMembershipNoByTdpCadreId()
	{
		//System.out.println(tdpCadreDAO.getMembershipNoByTdpCadreId(1610965l));
		List<Long> locationIds = new ArrayList<Long>();
		locationIds.add(1l);
		List<Object[]> list = tdpCommitteeElectrolsDAO.getElectrolsForPanchayatsWards(locationIds, "panchayat");
		System.out.println(list.size());
	}*/
	
	/*public void testGetTdpCadreIdByMembershipId()
	{
		List<Long> list = tdpCadreDAO.getTdpCadreIdByMembershipId("12345678");
		System.out.println(list.get(0));
	}*/
	
	public void testGetAffliatedCadreCountDetails()
	{
		tdpCadreDAO.getAffliatedCadreCountDetails("web",new Date());
	}
}
