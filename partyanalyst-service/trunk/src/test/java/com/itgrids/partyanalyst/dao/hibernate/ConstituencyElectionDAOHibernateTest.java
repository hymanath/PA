package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dto.PartyResultsVO;
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
		List elections = constituencyElectionDAO.getLocalBodyElectionsInAState(122l, 1L);
		if(elections !=  null){
			for(int i=0;i<elections.size();i++){
				Object[] params = (Object[])elections.get(i);
				System.out.println(" Election Id :" + (Long)params[0] + " Year :" + (String)params[1]);
			}
		}
	}*/
	
	/*public void testGetReservationZoneForAConstituency(){
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
	}*/
	/*public void testGetConstituenciesHavingMaxSpan()
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
		segregateAllConstituencies(1L,IConstants.ASSEMBLY_ELECTION_TYPE,IConstants.ELECTION_SUBTYPE_MAIN,1l,elecIds,IConstants.OTHERS);
		//segregateAllConstituencies(3l,IConstants.PARLIAMENT_ELECTION_TYPE,IConstants.ELECTION_SUBTYPE_MAIN,1l,elecIds);
	}*/
	
	
/*	public void segregateAllConstituencies(Long selectedNoOfYears,String electionType,String electionSubType,Long stateId,List<Long> elecIds,String type){
		try{
			List result = constituencyElectionDAO.getConstituenciesHavingMaxSpan(electionSubType,electionType,stateId,elecIds,type);	
			//System.out.println(result.size());	
			for(int i=0;i<result.size();i++)
			System.out.println(result.get(i).toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}*/
/*	
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
	
	/*public void testGetOldAndNewConstituenciesCountInAElection()
	{
		List<Object[]> list = constituencyElectionDAO.getOldAndNewConstituenciesInAElection(38l);
		
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println(params[0]+"---"+params[1]);
		}
	}*/
	
	/*public void testGetNearestPreviousMainElectionIdFromADateInAConstituency()
	{
		System.out.println(constituencyElectionDAO.getNearestPreviousMainElectionIdFromADateInAConstituency(232l,new Date()));
	}*/
	
	/*public void testGetElectionIdAndEleTypeByConstituencyIdAndDate()
	{
		List<Object[]> list = constituencyElectionDAO.getElectionIdAndEleTypeByConstituencyIdAndDate(232l, new Date());
		if(list != null && list.size() >0)
		{
			for(Object[] params : list)
			{
				System.out.println(params[0]+" "+params[1]); 
			}
		}
	}*/
	
	/*public void testgetElectionIdAndEleTypeByConstituencyIdAndDate1()
	{
		List<Object[]> list = constituencyElectionDAO.getElectionIdAndSubTypeByConstituencyIdAndDate(323l,constituencyElectionDAO.get(10507l).getElection().getElectionDate(),new Date());
		System.out.println(list.size());
		if(list != null && list.size() > 0)
		{
			for(Object[] params : list)
			{
				System.out.println(params[0]+" "+params[1]);
			}
		}
	}*/
	
	/*public void testfindAllElectionsHappendInAConstituency()
	{
		List<Object[]> list = constituencyElectionDAO.findAllElectionsHappendInAConstituency(232l);
		if(list != null && list.size() > 0)
		{
			for(Object[] params : list)
				System.out.println(params[0]+" "+params[1]);
		}
	}*/
	
	/*public void testfindAllElectionsHappendInAConstituency()
	{
		List<Long> constituencyId = new ArrayList<Long>(0);
		constituencyId.add(232l);
		constituencyId.add(234l);
		List<Object[]> list = constituencyElectionDAO.findAllEleHappendInAConstituency(constituencyId);
		System.out.println(list.size());
		for(Object[] params : list)
		System.out.println(params[0]+" "+params[1]);
	}*/
	
	/*public void testfindLatestElectionYear()
	{
		List<Long> constituencyId = new ArrayList<Long>(0);
		constituencyId.add(232l);
		constituencyId.add(234l);
		List<String> list = constituencyElectionDAO.findLatestElectionYear("MUNCIPALITY",58l);
		System.out.println(list.size());
		for(String params : list)
		System.out.println(params);
	}*/
	/*public void testFindLatestElectionYearByConstituencyIds()
	{
		List<Long> constituencyId = new ArrayList<Long>(0);
		constituencyId.add(309l);
		List list = constituencyElectionDAO.findLatestElectionYearByConstituencyIds("MUNCIPALITY",1l,constituencyId);
		System.out.println(list.size());
		for(int i=0;i<list.size();i++){
			Object[] data = (Object[])list.get(i);
		   System.out.println(data[0].toString()+"  "+data[1].toString());
		}
	}
	*/
	/*public void testfindAllElectionsHappendInAConstByConstIds()
	{
		List<Long> constituencyIdsList = new ArrayList<Long>(0);
		constituencyIdsList.add(232l);
		constituencyIdsList.add(495l);
		List<Object[]> list = constituencyElectionDAO.findAllElectionsHappendInAConstByConstIds(constituencyIdsList);
		for(Object[] params : list)
		System.out.println(params[0]+" "+params[1]);
	}*/
	
	public void test(){
		List<Long> electionIds=new ArrayList<Long>();
		List<Long> tehsilIds=new ArrayList<Long>();
		electionIds.add(37l);
		electionIds.add(39l);
		electionIds.add(65l);
		electionIds.add(67l);
		
		tehsilIds.add(783l);
		tehsilIds.add(784l);
		tehsilIds.add(785l);
		tehsilIds.add(786l);
		tehsilIds.add(787l);
		tehsilIds.add(802l);
		
		List<Object[]> list=constituencyElectionDAO.getZptcMptcResultsOfConstituency(tehsilIds,electionIds);
		
		Map<Long,PartyResultsVO> electionMap=new HashMap<Long, PartyResultsVO>();
		
		for(Object[] obj:list){
			PartyResultsVO pvo1=electionMap.get(Long.valueOf(obj[7].toString()));
			if(pvo1==null){
				pvo1=new PartyResultsVO();
			}
			PartyResultsVO pvo=new PartyResultsVO();
			pvo.setPartyId(Long.valueOf(obj[4].toString()));
			pvo.setPartyName(obj[3].toString());
			pvo.setValidVotes(((Double)(obj[2])).longValue());
			pvo.setVotesEarned(((Double)(obj[5])).longValue());
			pvo.setElectionId(Long.valueOf(obj[7].toString()));
			
			
			List<PartyResultsVO> pvoList=pvo1.getPartyResultsVOList();
			if(pvoList==null){
				pvoList=new ArrayList<PartyResultsVO>();
			}
			pvoList.add(pvo);
			pvo1.setPartyResultsVOList(pvoList);
			pvo1.setElectionId(Long.valueOf(obj[7].toString()));
			
			electionMap.put(Long.valueOf(obj[7].toString()), pvo1);
			
		}
		

		List<PartyResultsVO> finalVOList=new ArrayList<PartyResultsVO>();
		for (Entry<Long, PartyResultsVO> entry : electionMap.entrySet())
		{
			PartyResultsVO finalVO=new PartyResultsVO();
			
			Map<Long,PartyResultsVO> partyMap=new HashMap<Long, PartyResultsVO>();
			PartyResultsVO pvo=entry.getValue();
			for(PartyResultsVO param:pvo.getPartyResultsVOList()){
				
				PartyResultsVO party=partyMap.get(param.getPartyId());
				Long validVotes=null;
				if(party==null){
					party=new PartyResultsVO();
					party.setValidVotes(0l);
				}
				validVotes=party.getValidVotes();
				
				Long partyEarned=party.getVotesEarned();
				if(partyEarned==null){
					partyEarned=0l;
				}
				Long presCount=param.getVotesEarned()!=null?param.getVotesEarned():0l;
				party.setVotesEarned(partyEarned+presCount);
				party.setPartyId(param.getPartyId());
				partyMap.put(param.getPartyId(), party);
				validVotes+=param.getValidVotes()!=null?param.getValidVotes():0l;
				party.setValidVotes(validVotes);
				finalVO.setValidVotes(validVotes);
			}
			List<PartyResultsVO> pvoList=new ArrayList<PartyResultsVO>();
			for (Entry<Long, PartyResultsVO> entry2 : partyMap.entrySet())
			{
				pvoList.add(entry2.getValue());
			}
			finalVO.setElectionId(entry.getKey());
			finalVO.setPartyResultsVOList(pvoList);
			
			
			finalVOList.add(finalVO);
		}


		
		System.out.println(list.size());
		
	}
}
