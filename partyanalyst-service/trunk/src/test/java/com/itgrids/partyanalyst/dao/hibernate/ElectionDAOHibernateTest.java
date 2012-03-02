package com.itgrids.partyanalyst.dao.hibernate;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Assert;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.utils.IConstants;

public class ElectionDAOHibernateTest extends BaseDaoTestCase {
	
	private IElectionDAO electionDAO; 
	
	//mock data for add and remove test cases
	Election election1 = new Election(4L,null,new Date(27-8-2009),new Date(27-8-2010),"27-08-2009","2010",null,null,null,null,null,null);
	
	
	public void setElectionDAO(IElectionDAO electionDAO){
		this.electionDAO = electionDAO;
	}
	/*
	//@Test
	public void testfindByEndDate(){
		List<Election> election = electionDAO.findByEndDate("2010-10-01");
		Assert.assertEquals(election.get(0).getEndDate(),"2010-10-01");
		
	}
	//@Test
	public void testfindByElectionYear(){
		List<Election> election = electionDAO.findByElectionYear("2010");
		Assert.assertEquals(election.get(0).getEndDate(),"2010-10-01");
		assertEquals(2,2);
		
	}
	//@Test
	public void testGetAll(){
		List<Election> election1 = electionDAO.getAll();
		System.out.println(election1.size());
		Assert.assertEquals(election1.size(), 1);
	}
	//@Test
	public void testSaveDetails(){
		electionDAO.save(election1);
	    setComplete();
	}
	//@Test
	public void testRemoveDetails(){
		electionDAO.remove(new Long(4));
		setComplete();
	}*/

	/*@Test
	public void test(){
		Assert.assertEquals(1, 1);
	}*/
	
	/*@Test
	public void testFindByPropertyTypeIdCountry_StateId(){
		List<Election> list = electionDAO.findByPropertyTypeId_CountryId_StateId(new Long(1), new Long(1), new Long(1));
		for(Election e: list){
			System.out.println(e.getElectionYear());
		}
		Assert.assertEquals(2, list.size());
		list = electionDAO.findByPropertyTypeId_CountryId_StateId(new Long(1), new Long(1),null);
		Assert.assertEquals(0, list.size());
	}*/

	/*@Test
	public void testFindPreviousYear(){
		String prevYear = electionDAO.findPreviousElectionYear("2009", new Long(2), new Long(1), new Long(1));
		System.out.println(prevYear);
	}
	
	@Test
	public void testFindElection(){
		List<Election> election = electionDAO.findByElectionTypeYearAndState(new Long(2), "2009", new Long(1), new Long(1));
		System.out.println("Election ::"  + election.get(0).getElectionId());
	}
	@Test
	public void testFindElectionType(){
		List result = electionDAO.findElectionIdAndYear(1l,1l);
		Assert.assertEquals(8, result.size());
	}
	@Test
	public void testFindAllElectionYearsForAnElectionType(){
		List result = electionDAO.findElectionAndYearForElectionTypeAndState(3l,1l);
		Assert.assertEquals(2, result.size());
	}
	
	public void testElectionYears(){
		List result = electionDAO.findElectionAndYearForElectionTypeAndState(3l,1l);
		Assert.assertEquals(2, result.size());		
	}*/
	
	/*public void testYears(){
		List<Election> result = electionDAO.findByElectionScopeId(1l, IConstants.ELECTION_SUBTYPE_BYE);
		for(Election ele:result)
			System.out.println(ele.getElectionYear());
	}
	
	@Test
	public void testFindGetElections(){
		String prevYear = electionDAO.findPreviousParliamentElectionYear("2009", 1l, 1l);
		
		System.out.println(" Previous Year :" + prevYear);
		
	}*/
	
	/*public void testFindLatestElectionIdAndYear(){
		System.out.println(electionDAO.findLatestElectionAssemblyElectionYearForState(IConstants.ASSEMBLY_ELECTION_TYPE, 12l));
		
	}*/
	
	/*public void testFindStatesByElectionType(){
		List result = electionDAO.findStatesByElectionType(6l);
		
		for(int i=0;i<result.size();i++)
		{
			Object[] parms = (Object[])result.get(i);
			System.out.print(Long.parseLong(parms[0].toString()));
			System.out.print(parms[1].toString());
				
		}
			
	}*/
	
/*	@SuppressWarnings("unchecked")
	@Test
	public void testGetElections(){
		List elections = electionDAO.getLocalBodyElectionsInAState(IConstants.MUNCIPLE_ELECTION_TYPE, 1L);
		if(elections !=  null){
			for(int i=0;i<elections.size();i++){
				Object[] params = (Object[])elections.get(i);
				System.out.println(" Election Id :" + (Long)params[0] + " Year :" + (String)params[1]);
			}
		}
	}*/
	
	/*public void testGet(){
		List elections = electionDAO.findLatestElectionYearForGHMC(IConstants.GREATER_ELECTION_TYPE);
		for(int i=0;i<elections.size();i++){
			Object[] params = (Object[])elections.get(i);
			System.out.println(" Election Id :" + (Long)params[0] + " Year :" + (String)params[1]);
		}
	}*/
	
	/*@Test
	public void testFindElectionObjects(){
		List<Election> elections =  electionDAO.findElections(new Long(1));
		if(elections!=null){
			for(Election election:elections){
				if(election.getElectionId().equals(new Long(1)))
					Assert.assertEquals("2004",election.getElectionYear());
				else if(election.getElectionId().equals(new Long(2)))
					Assert.assertEquals("2009", election.getElectionYear());
				else if(election.getElectionId().equals(new Long(3)))
					Assert.assertEquals("2004",election.getElectionYear());
				else if(election.getElectionId().equals(new Long(4)))
					Assert.assertEquals("2009", election.getElectionYear());
				
			}
			
		}
	}
	
	public void testFindElectionsByState() {
		List list = electionDAO.findElectionsByState(1l);
		System.out.println(list.size());
	}*/
	
	/*public void testFindLatestElectionIdForElectionType(){
		List list = electionDAO.findLatestElectionIdForElectionType(IConstants.PARLIAMENT_ELECTION_TYPE, IConstants.ELECTION_SUBTYPE_MAIN);
		System.out.println(list);
	}*/
	
	/*public void testFindLatestElectionIdForElectionType(){
		List list = electionDAO.findLatestElectionIdForElectionType(IConstants.ASSEMBLY_ELECTION_TYPE, IConstants.ELECTION_SUBTYPE_MAIN,1l);
		System.out.println(list);
		System.out.println(list.size());
		Object[] obj = (Object[] )list.get(0);
		System.out.println(obj[0]);
		System.out.println(obj[1]);
	}
	
	public void testFindByElectionScopeIdElectionYear(){
		Election election = electionDAO.findByElectionScopeIdElectionYear(2l, "2009", null);
		System.out.println(election);
	}
	*/
	
	
	/*public void testGetAllElectionYears(){
		List<Long> allYears = electionDAO.getAllElectionYearsBasedOnElectionType(IConstants.PARLIAMENT_ELECTION_TYPE,IConstants.ELECTION_SUBTYPE_MAIN,1l);
		for(int i=0;i<allYears.size();i++){			
			System.out.println(allYears.get(i));
		}
	}*/
	
	/*public void testGetData(){
		List elecId = electionDAO.getElectionYears(1l,IConstants.PARLIAMENT_ELECTION_TYPE,IConstants.ELECTION_SUBTYPE_MAIN);
		System.out.println(elecId);
	}
	
	public void testGetData(){
		List assElecId = electionDAO.findLatestElectionIdAndYearForAnElection(IConstants.ASSEMBLY_ELECTION_TYPE,24l,IConstants.ELECTION_SUBTYPE_MAIN);
		System.out.println(new Long(assElecId.get(0).toString()));
		
		//List parElecId = electionDAO.findLatestElectionIdAndYearForAnElection(IConstants.PARLIAMENT_ELECTION_TYPE,1l,IConstants.ELECTION_SUBTYPE_MAIN);
	//	System.out.println(new Long(parElecId.get(0).toString()));
	}*/
	
	
	/*@SuppressWarnings("unchecked")
	@Test
	public void testFindRecentElectionIdForElectionType(){
		
		Long stateId = 1L;
		String electionType = IConstants.ASSEMBLY_ELECTION_TYPE;
		
		List resultsList = electionDAO.findRecentElectionIdByElectionTypeAndState(electionType, stateId);
		System.out.println(" Results Size :" + resultsList.size());
		
		
		if(resultsList != null && resultsList.size() > 0){
			
			Object[] values = (Object[])resultsList.get(0);
			//Long electionId = (Long)values[0];
			String electionYear = (String)values[0];
			
			
		}
	}*/
	
	/*@SuppressWarnings("unchecked")
	public void testGetLatestElectionYearForAStateBasedOnElectionType()
	{
		List<String> list = electionDAO.getLatestElectionYearForAStateBasedOnElectionType(24l,IConstants.ASSEMBLY_ELECTION_TYPE,"MAIN");
		
		for(String obj : list)
		{
			System.out.println(obj.toString());
		}
	}*/
	
	/*public void testGetPreviousElectionId()
	{
		List<Object[]> list = electionDAO.getPreviousElectionIdAndYear(18l);
		
		System.out.println(list.get(0)[0]);
		System.out.println(list.get(0)[1]);
	}*/
	
	/*public void testGetElectionYearsBasedOnElectionTypeAndState()
	{
		List<Object[]> list = electionDAO.getElectionYearsBasedOnElectionTypeAndState(1l,IConstants.ASSEMBLY_ELECTION_TYPE);
		
		System.out.println(list.size());
		if(list != null && list.size() > 0)
		for(Object[] id : list)
			System.out.println("Election Id -- "+id[0] + "       Election year  --- "+id[1]);
	}*/
	
	/*public void testGetStatesBasedOnElectionTypeId(){
		
		List<Object[]> statesList = electionDAO.getStatesBasedOnElectionTypeId(2l, IConstants.ASSEMBLY_ELECTION_TYPE);
		System.out.println(statesList.size());
	}
	public void testGetStatesBasedOnElectionTypeId(){
		
		List<Object[]> statesList = electionDAO.getElectionYears(27l, IConstants.PARLIAMENT_ELECTION_TYPE);
		System.out.println(statesList.size());
		System.out.println(statesList.get(1)[1].toString());
	}*/
	/*public void testgetElectionIdsBasedOnStateId(){
		List lst = electionDAO.getElectionIdsBasedOnStateId(24l, "2001");
		System.out.println(lst.size());
	}*/
	
	/*public void testGetCountOfElectionsAfterDelimitation()
	{
		Object obj = electionDAO.getCountOfElectionsAfterDelimitation(20l);
		System.out.println(obj);
	}
	*/
	public void testGetCountOfElectionsAfterDelimitation()
	{
		List<Object[]> obj = electionDAO.getStateDetailsForPartialElec();
		for(Object[] stateData :obj)
		{
			System.out.println("Id:  "+stateData[0].toString()+"  Name:  "+stateData[1].toString());
		}
	}
}

