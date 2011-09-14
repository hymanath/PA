package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dto.ConstituenciesStatusVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ConstituencyElection;
import com.itgrids.partyanalyst.model.ElectionScope;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.utils.IConstants;


public class ConstituencyDAOHibernateTest extends BaseDaoTestCase {
	
	private IConstituencyDAO constituencyDAO;

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
/*	
 	//@Test
	public void testFindStates() {
		State state = constituencyDAO.get(new Long(1)).getState();
		System.out.println(state.getStateName());
		Assert.assertEquals(state.getStateName(), "Andhra Pradesh");
	} 

	//@Test
	public void testFindByCountryId(){
		List<Constituency> constList = constituencyDAO.findByCountryId(new Long(1));
		Assert.assertEquals(13, constList.size());
		
		Constituency constituency = constList.get(0);
		String actualConstituencyName = constituency.getName();
		Assert.assertEquals("Kavali",actualConstituencyName);
		
	    ElectionScope election = constituency.getElectionScope();
		Assert.assertEquals(new Long(2), election.getElectionScopeId());
	}
	
	public void testFindByStateName(){
		List<Constituency> constituencies = constituencyDAO.findByStateId(new Long(1));
		Assert.assertEquals(13, constituencies.size());
	}
	
	public void testFindByElectionScope(){
		List<Constituency> parlCons = constituencyDAO.findByElectionScope(1L);
		List<Constituency> assemCons = constituencyDAO.findByElectionScope(2L);
		Assert.assertNotNull(parlCons);
		Assert.assertNotNull(assemCons);
	}
	
	public void testGetConstituencies() {
		List<Constituency> constList = constituencyDAO.findByStateId(new Long(1));
		
		for(Constituency constituency: constList){
			System.out.println(constituency.getName());
		}
	}
	*/
	//@Test
	
	/*public void testFindDistrictIdByConstituencyID(){
		List<Long> districtId = constituencyDAO.getDistrictIdByConstituencyId(new Long(1234));
		Assert.assertEquals(1, districtId.size());
	}
	
	public void testFindStateIdByConstituencyID(){
		List<Long> stateId = constituencyDAO.getStateIdByConstituencyId(new Long(1234));
		Assert.assertEquals(1, stateId.size());
	}
	
	public void testFindById(){
		Constituency constituency  = constituencyDAO.get(new Long(1));
		System.out.println("Constituency Name ::" + constituency.getName());
	}
	*/
/*	public void testGetConstituencyTypeAndDelimitationInfoByConstituencyId(){
		List constituencyTypeDetails = constituencyDAO.getConstituencyTypeAndDelimitationInfoByConstituencyId(new Long (340));
		
		String deformDate = "";
		if(constituencyTypeDetails!=null && constituencyTypeDetails.size()>0)
		{
			Object[] obj = (Object[])constituencyTypeDetails.get(0);
			String elecType = (String)obj[0];
			if(obj[1]!=null)
			 deformDate = (String)obj[1].toString();
			
			Assert.assertEquals(2, obj.length);
			Assert.assertEquals("Assembly", elecType);
			Assert.assertEquals("",deformDate);
		}
		else
		{
			System.out.println("Constituency not equal");
		}
		
	}
	*/
	/*public void testGetConstituenciesByElectionTypeAndStateId()
	{
		List constituenciesList  = constituencyDAO.getConstituenciesByElectionTypeAndStateId(new Long(5), new Long(1));
		
		if(constituenciesList!=null && constituenciesList.size()>0)
		{			
			for(int i=0;i<constituenciesList.size();i++)
			{
				Object[] obj = (Object[])constituenciesList.get(i);
				Long constituencyId = (Long) obj[0];
				String constituencyName = (String) obj[1];			
				
			}		
			
		}
	}*/
	
/*
	public void testConstituencyDetails(){
		 List result =  constituencyDAO.getConstituencyInfoByConstituencyIdElectionYearAndElectionType(11010l);
		 Assert.assertEquals(1, result.size());	
		}
	
	public void testFindByLocalElectionBodyAndElectionScope(){
		List<Constituency> list = constituencyDAO.findByLocalElectionBodyAndElectionScope(1l, "Ward-1");
		assertEquals(1, list.size());
	}
	
	public void testFindWardsAndIdsInMuncipality(){
		List list = constituencyDAO.findWardsAndIdsInMuncipality(2l);
		assertEquals(1, list.size());
	}*/
	
	/*@SuppressWarnings("unchecked")
	@Test
	public void testGetconstituencyByTehsil(){
		List consti = constituencyDAO.findByLocalElectionBodyAndElectionScope(1l, "Ward-1");
		if(consti != null){
			Object params[] = (Object[])consti.get(0);
			System.out.println(" Constituency Id   :" + (Long)params[0]);
			System.out.println(" Constituency Name :" + (String)params[1]);
		}
	}*/
	/*
	public void testGetGetAllParliamentConstituenciesInCountry(){
		List<Constituency> constList  = constituencyDAO.getAllParliamentConstituenciesInCountry(1l, 1l);
		System.out.println(constList.size());
		for(Constituency constituency:constList)
		{
			System.out.println("id:"+constituency.getConstituencyId());
			System.out.println("Name:"+constituency.getName());
		}
		
	}
	
	public void testFindByConstituencyNameElectionScopeAndDistrictId(){
		List<Constituency> list = constituencyDAO.findByConstituencyNameElectionScopeAndDistrictId("kavali", 19l, 2l);
		System.out.println(list.size());
	}
	
	*/
	
	/*public void testFindByConstituencyNamePattern()
	{
		List<Constituency> list = constituencyDAO.findByConstituencyNamePattern("Assembly", "a");
		System.out.println(list.size());
		for(Constituency constituency:list)
		{
			System.out.println("name:"+constituency.getName());			
		}	
	}*/
	/*public void testGetConstituenciesByElectionTypeAndStateId()
	{
		List constituenciesList  = constituencyDAO.getConstituenciesBySearchString(new Long(2), new Long(1), "kav");
		
		if(constituenciesList!=null && constituenciesList.size()>0)
		{			
			for(int i=0;i<constituenciesList.size();i++)
			{
				Object[] obj = (Object[])constituenciesList.get(i);
				Long constituencyId = (Long) obj[0];
				String constituencyName = (String) obj[1];			
				
				System.out.println(constituencyId);
				System.out.println(constituencyName);
				
				
					
			}		
			
		}
	}*/
	
	/*public void testFindByElectionScopeState()
	{
		List<Constituency> list = constituencyDAO.findByElectionScopeState(1l, 1l);
		System.out.println("Size:"+list.size());
		for(Constituency constituency:list)
		{
			System.out.println("name:"+constituency.getName());			
		}	
	}*/
	
	/*public void testFindWardsInLocalElectionBodies(){
		List list = constituencyDAO.findWardsInLocalElectionBodies("488, 562");
		System.out.println("size:"+list.size());
		for(int i=0;i<list.size();i++)
		{
			Object[] obj = (Object[])list.get(i);
			
			System.out.println("id:"+Long.parseLong(obj[0].toString()));
			System.out.println("name:"+obj[1].toString());				
		}
	}*/
	/*public void testGetStateToWardByWard()
	{
		List list = constituencyDAO.getStateToWardByWard("7404,7405");
		System.out.println("size:"+list.size());
		for(int i=0;i<list.size();i++)
		{
			Object[] obj = (Object[])list.get(i);
			
			System.out.println("id:"+Long.parseLong(obj[0].toString()));
			System.out.println("name:"+obj[1].toString());
			System.out.println("id:"+Long.parseLong(obj[2].toString()));
			System.out.println("name:"+obj[3].toString());
			System.out.println("id:"+Long.parseLong(obj[4].toString()));
			System.out.println("name:"+obj[5].toString());
			System.out.println("id:"+Long.parseLong(obj[6].toString()));
			System.out.println("name:"+obj[7].toString());
		}
	}*/
	
	/*public void testFindConstituenciesByNameScopeAndStateId(){
		List<Constituency> list = constituencyDAO.findConstituenciesByNameScopeAndStateId("Atmakur", 2l, 1l);
		System.out.println(list.size());
	}*/
	
/*	public void testFindConstituenciesByNameScopeAndCountryId(){
		List<Constituency> list = constituencyDAO.findConstituenciesByNameScopeAndCountryId("Nellore", 1l, 1l);
		System.out.println(list.size());
		Float val = new Float(10.01234);
		System.out.println(Math.round(val*100)/100.0);
	}
	*/   //String searchText,String sortoption,String order,Integr startINdex,Integer maxResult,String ids

	/*String stateStr =  "and model.state.stateId = 1";	
	public void testFindByConstituencyNames(){
		List<Object[]> list = constituencyDAO.findByConstituencyNamesForPalriament("v","","model.name","asc",0,100);
		//List<Object[]> list = constituencyDAO.findByConstituencyNamesForAssembly("v","","model.name","asc",0,100);
		System.out.println(list.size());
		
		Iterator lstItr = list.listIterator();
		while(lstItr.hasNext()){
			Object[] values  =(Object[])lstItr.next();
			
			System.out.println(" .................................... ");
			System.out.println(" Constituency Id : " + (Long)values[0]);
			System.out.println(" Constituency    : " + (String)values[1]);
			System.out.println(" Election Type   : " + (String)values[2]);
			//System.out.println(" District   	 : " + (String)values[3]);
			System.out.println(" State			 : " + (String)values[3]);
			System.out.println(" Deform date     : " +  values[4]);
		}
		
	}
	*/
/*
	//String searchText, String ids
	@Test
	public void testTotalSearchCount()
	{
		String state = "and model.state.stateId = 1";	
		Object obj=constituencyDAO.totalSearchCount("v",1l,"");
		System.out.println(state);
	
	}
	public static void main(String args[]){
		
	System.out.println("tested---");}
	}
	*/
	/*public void testGetAssConstituenciesByElectionTypeAndStateIdAndDistrictAccess()
	{
		List result = constituencyDAO.getAssConstituenciesByElectionTypeAndStateIdAndDistrictAccess(2L, 1L, 1L);
		System.out.println(result.size());
	}*/
	
	public void testGetAllParliamentConstituenciesInCountryByStateAccessAndCountryAccess(){
		List result = constituencyDAO.getAllParliamentConstituenciesInCountryByStateAccessAndCountryAccess(1L, 1L, 1L);
		System.out.println(result.size());
	}
}
