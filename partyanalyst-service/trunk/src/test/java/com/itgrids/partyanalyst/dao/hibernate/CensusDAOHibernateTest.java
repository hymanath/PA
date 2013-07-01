package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICensusDAO;
import com.itgrids.partyanalyst.utils.IConstants;

public class CensusDAOHibernateTest extends BaseDaoTestCase {
	private ICensusDAO censusDAO;
	
	public void setCensusDAO(ICensusDAO censusDao){
		this.censusDAO = censusDao;
	}
	
	/*public void testFindByStateIdAndYear(){
		Set<Census> stateCensus = censusDAO.findByStateIdAndYear(new Long(1), 2001);
		
		for(Census census: stateCensus){
			if(census.getTru().equals("Total")){
				Assert.assertEquals(6.59244658, census.getPercentageST());
			} else if(census.getTru().equals("Urban")){
				Assert.assertEquals(1.812591127, census.getPercentageST());
			} else if(census.getTru().equals("Rural")){
				Assert.assertEquals(8.387786105, census.getPercentageST());
			}
		}
	}
	
	public void testFindByDistrictIdAndYear(){
		Set<Census> districtCensus = censusDAO.findByDistrictIdAndYear(new Long(23), 2001);
		
		for(Census census: districtCensus){
			if(census.getTru().equals("Total")){
				Assert.assertEquals(66.77287396, census.getPopLiteraturePercentage());
			} else if(census.getTru().equals("Rural")){
				Assert.assertEquals(62.99643768, census.getPopLiteraturePercentage());
			} else if(census.getTru().equals("Urban")){
				Assert.assertEquals(80.23567595, census.getPopLiteraturePercentage());
			}
		}
	}
	
	public void testFindByTehsilIdAndYear(){
		Set<Census> tehsilCensus = censusDAO.findByTehsilIdAndYear(new Long(45), 2001);
		
		for(Census census: tehsilCensus){
			if(census.getTru().equals("Total")){
				Assert.assertEquals(new Long(32016), census.getTotalPopulation());
			} else if(census.getTru().equals("Rural")){
				Assert.assertEquals(new Long(32016), census.getTotalPopulation());
			} else if(census.getTru().equals("Urban")){
				Assert.assertEquals(new Long(0), census.getTotalPopulation());
			}
		}
	}
	
	public void testFindByTownshipIdAndYear_Town(){
		Set<Census> townCensus = censusDAO.findByTownshipIdAndYear(new Long(4752), 2001);
		Assert.assertEquals(1, townCensus.size());
		for(Census census: townCensus){
			if(census.getTru().equals("Urban")){
				Assert.assertEquals(new Long(1870068), census.getTotalMalePopulation());
			} 
		}
	}
	
	public void testFindByTownshipIdAndYear_Village(){
		Set<Census> villageCensus = censusDAO.findByTownshipIdAndYear(new Long(4756), 2001);
		Assert.assertEquals(1, villageCensus.size());
		for(Census census: villageCensus){
			if(census.getTru().equals("Rural")){
				Assert.assertEquals(new Long(449), census.getTotalMalePopulation());
			} 
		}
	}
	
	public void testFindByWardIdAndYear(){
		Set<Census> villageCensus = censusDAO.findByWardIdAndYear(new Long(3991), 2001);
		Assert.assertEquals(1, villageCensus.size());
		for(Census census: villageCensus){
			if(census.getTru().equals("Urban")){
				Assert.assertEquals(new Long(2315), census.getNonWorkingPopulation());
			} 
		}
	}*/
	/*
	public void testFindAllRevenueVillagesInfoInMandal(){
		List list = censusDAO.findAllRevenueVillagesInfoInMandal(2001l, 861l, "'"+IConstants.CENSUS_VILLAGE_LEVEL+"','"+IConstants.CENSUS_WARD_LEVEL+"'");
		for(Object[] values:(List<Object[]>)list){
			for(int i=0; i<values.length; i++)
				System.out.print(values[i]+"\t");
			System.out.println();
		}
			
	}
	*/
/*
   public void testFindMandalWiseCensusDetails()
   {
	   List<Object[]> list = censusDAO.findMandalWiseCensusDetails(1l,3l,1115l,2001l,"TEHSIL");
	   System.out.println(list.size());
	   
	   Object[] obj = list.get(0);
	   
	   System.out.println("Total Population ---"+obj[0]);
	   System.out.println("Total SC Population ---"+obj[1]);
	   System.out.println("Total ST Population ---"+obj[2]);
	   System.out.println("Literates---"+obj[3]);
	   System.out.println("Illiterates ----"+obj[4]);
	   System.out.println("Working People---"+obj[5]);
	   System.out.println("Non Working People---"+obj[6]);
   }*/

 /*  @SuppressWarnings("unchecked")
   public void testFindCensusDetailsForAPartialMandal()
   {
	   List<Object[]> list = censusDAO.findCensusDetailsForAPartialMandal(1l,19l,2001l,"VILLAGE","21547,21548,21549,21550");
	   System.out.println(list.size());
	   Object[] obj = list.get(0);
	   for(Object y:obj)
	   {
		   System.out.println(y.toString());
	   }
   }*/
  /* 
   public void testFindTownshipWiseCensusDetails()
   {
	   List<Object[]> list = censusDAO.findTownshipWiseCensusDetails(1l,19l,21664l,2001l,"TOWN");
	   System.out.println(list.size());
	   
	   Object[] obj = list.get(0);
	   
	   System.out.println("Total Population ----  "+obj[0]);
	   System.out.println("Total SC Population ---"+obj[1]);
	   System.out.println("Total ST Population ---"+obj[2]);
	   System.out.println("Literates---           "+obj[3]);
	   System.out.println("Illiterates ----       "+obj[4]);
	   System.out.println("Working People---      "+obj[5]);
	   System.out.println("Non Working People---  "+obj[6]);
   }*/
   
  /*@SuppressWarnings("unchecked")
   public void testFindCensusDetailsForAPartialTown()
   {
	   List<Object[]> list = censusDAO.findCensusDetailsForAPartialTown(1l,19l,2001l,"WARD","2957,2958");
	   System.out.println(list.size());
	   Object[] obj = list.get(0);
	   
	   System.out.println("Total Population ----  "+obj[0]);
	   System.out.println("Total SC Population ---"+obj[1]);
	   System.out.println("Total ST Population ---"+obj[2]);
	   System.out.println("Literates---           "+obj[3]);
	   System.out.println("Illiterates ----       "+obj[4]);
	   System.out.println("Working People---      "+obj[5]);
	   System.out.println("Non Working People---  "+obj[6]);
   }*/
   
  /* public void testFindMandalWiseCompleteCensusDetails()
   {
	   List<Object[]> list = censusDAO.findMandalWiseCompleteCensusDetails(1l,19l,844l,2001l,"TEHSIL");
	   
	   System.out.println(list.size());
	   Object[] arr = list.get(0);
	   int i= 0;
	   for(Object obj:arr)
	   {
		   System.out.println("========== "+ ++i +")"+ obj.toString());
	   }
   }*/
	
	/* @SuppressWarnings("unchecked")
	   public void testFindCompleteCensusDetailsForAPartialMandal()
	   {
		   List<Object[]> list = censusDAO.findCompleteCensusDetailsForAPartialMandal(1l,19l,2001l,"VILLAGE","21547,21548,21549,21550");
		   System.out.println(list.size());
		   Object[] obj = list.get(0);
		   
		   int i=0;
		   for(Object y:obj)
		   {
			   System.out.println("======"+ ++i +")"+y.toString());
		   }
	   }*/
	   
	  /* @SuppressWarnings("unchecked")
	   public void testFindCompleteCensusDetailsForAPartialTown()
	   {
		   List<Object[]> list = censusDAO.findCompleteCensusDetailsForAPartialTown(1l,19l,2001l,"WARD","2957,2958");
		   System.out.println(list.size());
		   
		   int i = 0;
		   for(Object obj:list.get(0))
		   {
			   System.out.println("======"+ ++i +")"+obj.toString());
		   }
	   }*/
	   
	   /*public void testFindTownshipWiseCompleteCensusDetails()
	   {
		   List<Object[]> list = censusDAO.findTownshipWiseCompleteCensusDetails(1l,19l,21664l,2001l,"TOWN");
		   System.out.println(list.size());
		   
		   int i = 0;
		   for(Object obj : list.get(0))
		   {
			   System.out.println("======"+ ++i +")"+obj.toString());
		   }
		   
	   }*/
	
	   public void testGetCensusDetailsOfAState()
	   {
		   List<Object[]> list = censusDAO.getCensusDetailsOfAState(1l,2001l);
		   System.out.println(list.size());
		   
		   for(Object[] params : list)
		   {
			   System.out.println();
			   for(Object str : params)
				   System.out.print("\t"+str.toString());
		   }
	   }
}
