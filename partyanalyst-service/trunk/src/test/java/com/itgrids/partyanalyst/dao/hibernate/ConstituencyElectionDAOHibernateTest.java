package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.utils.IConstants;

public class ConstituencyElectionDAOHibernateTest extends BaseDaoTestCase {
	
	private IConstituencyElectionDAO constituencyElectionDAO;
	//ConstituencyElection constElec = new ConstituencyElection(new Long(4),null,null,new Date(27-8-2009),null,null);
	
	public IConstituencyElectionDAO getConstituencyElectionDAO() {
		return constituencyElectionDAO;
	}
	
	public void setConstituencyElectionDAO(IConstituencyElectionDAO constituencyElectionDAO){
		this.constituencyElectionDAO = constituencyElectionDAO;
	}
	
	
	/*public void testFindConstituency(){
		Constituency constituency = constituencyElectionDAO.get(new Long(1)).getConstituency();
		Assert.assertEquals("nellore", constituency.getName());
	}*/
	
	
	/*public void testFindByConstituencyElectionAndDistrict(){
		List<ConstituencyElection> list = constituencyElectionDAO.findByConstituencyElectionAndDistrict("2004", "Atmakur", new Long(2), new Long(19));
		for(ConstituencyElection obj:list){
			System.out.println(obj.getConstiElecId());
			System.out.println(obj.getConstituency().getName());
			System.out.println(obj.getConstituency().getDistrict().getDistrictId());
		}
		assertEquals(list.size(),2);
	}
	
	@Test
	public void testFindConstituencyFromConstituencyElection(){
		List<Constituency> constituencys = constituencyElectionDAO.findConstituencyByElectionAndDistrict(new Long(9), new Long(14));
		System.out.println("Count ::" + constituencys.size());
		for(Constituency con:constituencys){
			System.out.println("Constituency ::" + con.getName());
		}
	}*/
	/*@SuppressWarnings("unchecked")

	public void testFindValidVotedForAnElectionForAState(){
		List list = constituencyElectionDAO.findTotalValidVotesForAnElectionForAState(new Long(12));
		Object params = (Object)list.get(0);
		Double validVotes = (Double)params;
		System.out.println("Total ValidVotes For Assembly 2009(A.P) ::" + validVotes.longValue());
	}
	
	@SuppressWarnings("unchecked")
	public void testFindConstituenciesCount(){
		List list = constituencyElectionDAO.findTotalValidVotesForAnElectionForAStateAndDistrict(new Long(9),new Long(19));
		Object params = (Object)list.get(0);
		Double validVotes = (Double)params;
		System.out.println("Total ValidVotes For Assembly 2009(A.P) Nellore Dist ::" + validVotes.longValue());
	}
	
	@Test
	public void testByElectionType(){
		List list = constituencyElectionDAO.findTotalAssemblyConstituencies(9l,1l);
		for(int i=0;i<list.size();i++){
			Object[] parms = (Object[])list.get(i);
			System.out.println(parms[1]);
		}
		System.out.println(list.size());		
	}*/
	
	/*public void testFindByDistrictElectionConstituency(){
		List list = constituencyElectionDAO.findByDistrictElectionConstituency(2l, 19l, "Kavali");
		assertEquals(1, list.size());
	}

	@Test
	public void testTotalValidVotesForMandal(){
		List list = constituencyElectionDAO.getTotalValidVotesParticularElectionYear(IConstants.ZPTC_ELECTION_TYPE,"2001",19l);
		assertEquals(1, list.size());
	}
	
	public void testConstituenciesForADistrictForAnElectionYear(){
		List  list = constituencyElectionDAO.findConstituencyByDistrictAndStateIds(19l,1l,"2009");
		assertEquals(1, list.size());	
	}*/
	
	/*@SuppressWarnings("unchecked")
	@Test
	public void testFindElection(){
		List election = constituencyElectionDAO.findElectionIdForAParticularElectionTypeAndYearAndConstituency("Assembly", "2009", new Long(232));
		if(election != null){
			Object params = (Object)election.get(0);
			Election elec = (Election)params;
			
			System.out.println("Election Id   :" + elec.getElectionId());
			System.out.println("Election Scope:" + elec.getElectionScope().getElectionType().getElectionType());
			System.out.println("Election Year :" + elec.getElectionYear());
		}
	}*/
	
	
	/*public void testFindTotalValidVotesInConstituencyElection(){
		List list = constituencyElectionDAO.findTotalValidVotesInConstituencyElection(403l, "2009");
		System.out.println(list.get(0));
	}*/
	
	
	/*public void testFindConstituenciesByElectionGroupByDistrict(){
		List list = constituencyElectionDAO.findConstituenciesByElectionGroupByDistrict(4l);
		for(Object[] values:(List<Object[]>)list)
			System.out.println(values[0]+"\t"+values[1]+"\t"+values[2]);
		assertEquals(23, list.size());
	}
	*/
	/*public void testFindConstituenciesByElectionGroupByState(){
		List list = constituencyElectionDAO.findConstituenciesByElectionGroupByState(11l);
		for(Object[] values:(List<Object[]>)list)
			System.out.println(values[0]+"\t"+values[1]);
		assertEquals(35, list.size());
	}
	
	public void testGet(){
		List totalValidVotes = constituencyElectionDAO.getTotalValidVotesParticularElectionYear(IConstants.MPTC_ELECTION_TYPE,"2006",22l);
		assertEquals(1, totalValidVotes.size());		
	}*/
	
	/*public void testGetValidVotesForMptcZptcElectionsInMandals(){
		List list = constituencyElectionDAO.getValidVotesForMptcZptcElectionsInMandals("362");
		for(int i=0; i<list.size(); i++){
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]);
		}
	}*/
	/*
	public void testGetValidVotesForMunicipalitiesAndCorporationsInMandals(){
	
	/*public void testGetValidVotesForMunicipalitiesAndCorporationsInMandals(){
		List list = constituencyElectionDAO.getValidVotesForMunicipalitiesAndCorporationsInMandals("384");
		for(int i=0; i<list.size(); i++){
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]);
		}
	}*/
	/*@SuppressWarnings("unchecked")
	public void testGetStates(){
		List results = constituencyElectionDAO.getParticipatedStateDetailsForAnElectionType(new Long(2));
		if(results != null && results.size() > 0){
			Iterator listIt = results.listIterator();
			while(listIt.hasNext()){
				Object[] params = (Object[])listIt.next();
				System.out.println(" State Id :" + (Long)params[0] + " State Name :"  + (String)params[1]);
			}
		}
	}*/
	
	/*public void testGetConstituenciesCountByStateAndElection(){
		List list = constituencyElectionDAO.getConstituenciesCountByDistrictForStateAndElection(1l, 3l);
		System.out.println(list.size());
	}*/
	
	/*public void testGetConstituenciesCountByStateForCountryAndElection(){
		List list = constituencyElectionDAO.getConstituenciesCountByStateForCountryAndElection(1l, 2l);
		System.out.println(list.size());
	}*/

	/*@SuppressWarnings("unchecked")
	@Test
	public void testGetLocalBodyElections(){
		List elections = constituencyElectionDAO.getLocalBodyElectionsInAState(488L, 1L);
		if(elections !=  null){
			for(int i=0;i<elections.size();i++){
				Object[] params = (Object[])elections.get(i);
				System.out.println(" Election Id :" + (Long)params[0] + " Year :" + (String)params[1]);
			}
		}
	}
	
	public void testGetReservationZoneForAConstituency(){
		//List list = constituencyElectionDAO.getLatestReservationZone(21l,IConstants.ELECTION_SUBTYPE_MAIN);
		List<Long> constituencyIds = new ArrayList<Long>(0);
		constituencyIds.add(8436l);		
		
		HashMap<Long,Long> hm = new HashMap<Long,Long>();
		List list = constituencyElectionDAO.getLatestReservationZone(constituencyIds,2006l);
		
		for(int i=0;i<list.size();i++){
			Object[] params = (Object[])list.get(i);
			System.out.println(params[0] + " \t" + params[1]+ " \t" + params[2]);	
		}
	}
	
	public void testfindAllElectionsByConstituencyId()
	{
		
		List list = constituencyElectionDAO.findAllElectionsByConstituencyId(new Long(232));
		
		System.out.println(list.size());
		
		if(list.size() > 0)
		{
			for(int i=0;i<list.size();i++){
				Object[] params = (Object[])list.get(i);
				System.out.println(params[0] + " \t" + params[1]);	
			}
		}
	}
	public void testGetConstituenciesHavingMaxSpan()
	{
		List<Long> elecIds = new ArrayList<Long>(0);
		elecIds.add(4l);
		elecIds.add(5l);
		elecIds.add(23l);
		elecIds.add(24l);
		elecIds.add(25l);
		elecIds.add(28l);
		elecIds.add(29l);
	//4, 5, 23, 24, 25, 28, 29
		segregateAllConstituencies(7L,IConstants.ASSEMBLY_ELECTION_TYPE,IConstants.ELECTION_SUBTYPE_MAIN,1l,elecIds,IConstants.OTHERS);
		//segregateAllConstituencies(3l,IConstants.PARLIAMENT_ELECTION_TYPE,IConstants.ELECTION_SUBTYPE_MAIN,1l,elecIds);
	}
	
	
	public void segregateAllConstituencies(Long selectedNoOfYears,String electionType,String electionSubType,Long stateId,List<Long> elecIds,String type){
		try{
			List result = constituencyElectionDAO.getConstituenciesHavingMaxSpan(electionSubType,electionType,stateId,elecIds,type);	
			System.out.println(result.size());			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetLatestElectionHappenedInConstituency(){
		
		
		List results = constituencyElectionDAO.getLatestResultsElectionYearInAConstituency(478L);
		
		if(results != null)
		{
			Object res = (Object)results.get(0);
			
			String year = (String)res;
			System.out.println(" Latest Election Year = " + year);
		}
	}
	
	public void testGetCountOfOldConstituencies(){
		
		List result = constituencyElectionDAO.getCountOfOldConstituencies(38l);
		System.out.println("Old Constituencies --------"+result.get(0));
	}
	
	public void testGetCountOfNewConstituencies(){
		
		List result = constituencyElectionDAO.getCountOfDelimitedConstituencies(38l);
		System.out.println("New Constituencies --------------"+result.get(0));
	}*/
	/*public void testGetPartyWinningConstituenciesCount(){
		
		List<Object[]> list = constituencyElectionDAO.getPartyWinningConstituenciesCount(38l);
		System.out.println(list.size());
	}*/
	
	/*public void testGetPCCountInAElection()
	{
		Object object = constituencyElectionDAO.getPCCountInAElection(38l);
		System.out.println(object.toString());
	}*/
	
	public void testGetOldAndNewConstituenciesCountInAElection()
	{
		List<Object[]> list = constituencyElectionDAO.getOldAndNewConstituenciesInAElection(38l);
		
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println(params[0]+"---"+params[1]);
		}
	}
}
