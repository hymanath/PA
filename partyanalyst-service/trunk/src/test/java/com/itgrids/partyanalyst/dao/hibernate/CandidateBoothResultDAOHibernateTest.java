package com.itgrids.partyanalyst.dao.hibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dto.CandidatePartyInfoVO;
import com.itgrids.partyanalyst.dto.ConstituencyOrMandalWiseElectionVO;
import com.itgrids.partyanalyst.dto.ConstituencyRevenueVillagesVO;
import com.itgrids.partyanalyst.dto.PartyElectionResultVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.CandidateBoothResult;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.utils.IConstants;

public class CandidateBoothResultDAOHibernateTest extends BaseDaoTestCase{
	
	private ICandidateBoothResultDAO candidateBoothResultDAO;

	public ICandidateBoothResultDAO getCandidateBoothResultDAO() {
		return candidateBoothResultDAO;
	}

	public void setCandidateBoothResultDAO(
			ICandidateBoothResultDAO candidateBoothResultDAO) {
		this.candidateBoothResultDAO = candidateBoothResultDAO;
	}
	
	/*public void testFindByBoothConstituencyElectionAndParty(){
		List<CandidateBoothResult> list = candidateBoothResultDAO.findByBoothConstituencyElectionAndParty(new Long(200), new Long(1));
		assertEquals(1, list.size());
	}*/

	/*public void testFindByConstituencyElection(){
		List<CandidateBoothResult> list = candidateBoothResultDAO.findByConstituencyElection(new Long(125));
		assertEquals(1950, list.size());
	}*/
	
	/*public void testFindByNominationAndBoothConstituencyElection(){
		List<CandidateBoothResult> list = candidateBoothResultDAO.findByNominationAndBoothConstituencyElection(new Long(690), new Long(77));
		assertEquals(1, list.size());
	}*/
	
	/*public void testFidPartiesForConstituencyAndElection(){
		List<Party> list = candidateBoothResultDAO.findPartiesByConstituencyAndElectionYear(new Long(408), "2004");
		assertEquals("INC", list.get(0).getShortName());
	}*/
	
	/*public void testGetPartyGenderWiseBoothVotesForMandal(){
		List list = candidateBoothResultDAO.getPartyGenderWiseBoothVotesForMandal(844l);
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]+"\t"+((Object[])list.get(i))[4]+"\t"+((Object[])list.get(i))[5]+"\t"+((Object[])list.get(i))[6]+"\t"+((Object[])list.get(i))[7]+"\t"+((Object[])list.get(i))[8]+"\t"+((Object[])list.get(i))[9]+"\t"+((Object[])list.get(i))[10]+"\t"+((Object[])list.get(i))[11]+"\t"+((Object[])list.get(i))[12]+"\t"+((Object[])list.get(i))[13]+"\t"+((Object[])list.get(i))[14]+"\t"+((Object[])list.get(i))[15]+"\t"+((Object[])list.get(i))[16]);
	}*/
	
	/*public void testFindMandalWisePartiesResultsForElection(){
		List list = candidateBoothResultDAO.findMandalWisePartiesResultsForElection(844l, 2l);
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]+"\t"+((Object[])list.get(i))[4]+"\t"+((Object[])list.get(i))[5]+"\t"+((Object[])list.get(i))[6]+"\t"+((Object[])list.get(i))[7]+"\t"+((Object[])list.get(i))[8]+"\t"+((Object[])list.get(i))[9]);
	}*/
	
	/*public void testFindTownshipElectionResult(){
		long start = System.nanoTime();
		List list = candidateBoothResultDAO.findTownshipElectionResult(21817l, 2l);
		long end = System.nanoTime();
		System.out.println((end-start)/1000000000);
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]);
	}*/
	
	/*public void testGetBoothsResultsForBooths(){
		List list = candidateBoothResultDAO.findPartyResultsForBooths("221,222");
		System.out.println(list.size());
	}*/
	
	/*public void testFindAllElectionsInfoInRevenueVillage(){
		long start = System.nanoTime();
		List list = candidateBoothResultDAO.findAllElectionsInfoInRevenueVillage(21816l);
		long end = System.nanoTime();
		System.out.println((end-start)/1000000000);
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]+"\t"+((Object[])list.get(i))[4]+"\t"+((Object[])list.get(i))[5]+"\t"+((Object[])list.get(i))[6]+"\t"+((Object[])list.get(i))[7]+"\t"+((Object[])list.get(i))[8]+"\t"+((Object[])list.get(i))[9]+"\t"+((Object[])list.get(i))[10]+"\t"+((Object[])list.get(i))[11]+"\t"+((Object[])list.get(i))[12]+"\t"+((Object[])list.get(i))[13]+"\t"+((Object[])list.get(i))[14]+"\t"+((Object[])list.get(i))[15]+"\t"+((Object[])list.get(i))[16]);
	}*/
	
	/*public void testFindAllElectionsInfoOfRevenueVillagesInTehsil(){
		long start = System.nanoTime();
		List list = candidateBoothResultDAO.findAllElectionsInfoOfRevenueVillagesInTehsil(844l, 4l);
		long end = System.nanoTime();
		System.out.println((end-start)/1000000000);
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]+"\t"+((Object[])list.get(i))[4]+"\t"+((Object[])list.get(i))[5]+"\t"+((Object[])list.get(i))[6]+"\t"+((Object[])list.get(i))[7]+"\t"+((Object[])list.get(i))[8]+"\t"+((Object[])list.get(i))[9]+"\t"+((Object[])list.get(i))[10]);
	}*/
	
/*	public void testFindBoothResultsForTownshipAndElection(){
		List list = candidateBoothResultDAO.findBoothResultsForTownshipAndElection(21794l, 2l);
		System.out.println(list.size());
	}*/
	
	/*public void testGetAllACPCPartiesInMandal(){
		List list = candidateBoothResultDAO.getAllACPCPartiesInMandal(843l);
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]);
	}*/
	/*
	/*public void testFindAssemblyWiseParliamentResultsForParties(){
		long start = System.nanoTime();
		List list = candidateBoothResultDAO.findAssemblyWiseParliamentResultsForParties(231l, 403l, "2009");
		long end = System.nanoTime();
		System.out.println((end-start)/1000000000);
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]);
	}*/
		
/*	public void testAssemblies(){
		List list = candidateBoothResultDAO.findAllPartiesElectionResultsInDistrictForElectionType(19l,"Parliament");
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]);
	}*/
	

	/*public void testAssemblies(){

	List list = candidateBoothResultDAO.getCandidatesResultsForElectionAndConstituencyByMandal(3382l,"2009");
	for(int i=0; i<list.size(); i++)
		System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]);

	}
	*/
	/*public void testGetcandidatesResultsByBoothConstiIds(){
		List list = candidateBoothResultDAO.getcandidatesResultsByBoothConstiIds("2323,2324,2325,2326,2327,2328,2329,2330,2331,	2332,2333,				2334,				2335,				2336,				2337,				2338,				2339,				2340,				2341");
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]+"\t"+((Object[])list.get(i))[4]+"\t"+((Object[])list.get(i))[5]+"\t"+((Object[])list.get(i))[6]+"\t"+((Object[])list.get(i))[7]+"\t"+((Object[])list.get(i))[8]);
	}
	
	public void testGetMandalsForAParliament(){
		List list = candidateBoothResultDAO.getMandalsForAConstituencyForAGivenYear(3554l,"2009");
		ListIterator it = list.listIterator();
		while(it.hasNext()){
			Object[] parms = (Object[])it.next();
			System.out.println(parms[0]+"\t\t"+parms[1]);
			
		}
		System.out.println(list.size());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetElectionResultsInAMandalForAllConstituencies(){
		List resultsList = candidateBoothResultDAO.getElectionResultsInAMandalForAllParties(new Long(90),"Assembly","2004");
		System.out.println("Size :" + resultsList.size());
		if(resultsList != null && resultsList.size() > 0){
			
			for(int i=0;i<resultsList.size();i++){
				
				Object[] params = (Object[])resultsList.get(i);
				System.out.print("   Constituency :" + (String)params[6]);
				System.out.print("   Party :" + (String)params[1]);
				System.out.print("   Votes Earned :" + (Long)params[2]);
				System.out.print("   Valid Votes :" + (Long)params[3]);
				System.out.print("   Rank :" + (Long)params[4]);
								
				System.out.println("....");
			}
			
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetParties(){
		
				
		List partys = candidateBoothResultDAO.getAllPartiesPariticipatedInAConstituencyElection(new Long(80));
		
		
		if(partys != null && partys.size() > 0){
			
			for(int i=0;i<partys.size();i++){
			Object params = (Object)partys.get(i);
			Party party = (Party)params;
			
			System.out.println(" Party :" + party.getShortName());
			}
		}
	}*/
	
	/*
	@SuppressWarnings("unchecked")
	//Test case for getBoothWisePartyResultsInAMandal() DAO method
	@Test
	public void testGetBoothWisePartyResultsInAMandal(){
		
		//List partyResults = candidateBoothResultDAO.getBoothWisePartyResultsInAMandalByPartyRank(new Long(843),"2004","Assembly",new Long(2));
		List partyResults = candidateBoothResultDAO.getBoothWisePartyResultsInAMandal(new Long(843),new Long(24),"2004");
		
		if(partyResults != null && partyResults.size() > 0){
			System.out.println(" Results Size :" + partyResults.size());
		
			/*for(int i=0;i<partyResults.size();i++){
				Object[] params = (Object[])partyResults.get(i);
				Booth booth = (Booth)params[0];
				 //System.out.println(" Booth Details .....");
				System.out.println(" ..." + i);
				 //System.out.print("   " + booth.getPartNo());
				 System.out.print("   " + booth.getLocation());
				 				 
				 System.out.print( " V.E " + (Long)params[1]);
				/* System.out.print( " V.V " + (Long)params[2]);
				 System.out.print( " CName " + (String)params[3]);
				 System.out.print( " Election Type :" + (String)params[4]);
			}*/
			/*
			Iterator listIter = partyResults.listIterator();
			int i=0;
			while(listIter.hasNext()){
				Object[] params = (Object[])listIter.next();
				Booth booth = (Booth)params[0];
				System.out.println( " .... " + (++i));
				System.out.print(" Booth " + booth.getLocation());
				System.out.print(" V.E " + (Long)params[1]);
				System.out.print(" V.V " + (Long)params[2]);
				System.out.print(" Type " + (String)params[7]);
				
				System.out.print(" CC :" + (String)params[4]);
			}
			
			Map<String,String> myMap = new HashMap<String,String>();
			myMap.put("Assembly", "One");
			myMap.put("PArliament", "Two");
			
			Set<String> keySet = myMap.keySet();
			for(String val:keySet){
			System.out.println(" 1 -- " + val);
			}
			
		}
	}*/
	
	/*@SuppressWarnings("unchecked")
	@Test
	public void testGetPartyMandalWiseResults(){
		
		List resultsList = candidateBoothResultDAO.getPartyResultsInAMandalForAnElection(new Long(141),new Long(21),new Long(24),"2009");
		
		System.out.println(" Size :" + resultsList.size());
		Iterator listIterator = resultsList.listIterator();
		while(listIterator.hasNext()){
			Object[] params = (Object[])listIterator.next();
			System.out.println( " V.E :" + params[0]);
			System.out.println( " V.V :" + params[1]);
			System.out.println( " T.E :" + params[2]);
			System.out.println( " T.B :" + params[3]);
		}
	}*/	
	
	/*public void testGetBoothWisePartyResultsInAMandalByConstituencyId()

	{
		List list = candidateBoothResultDAO.getBoothWisePartyResultsInAMandalByConstituencyId(141L, 61L, 388L, "2009", IConstants.PARLIAMENT_ELECTION_TYPE);
		System.out.println(list.size());
		ListIterator li = list.listIterator();
		while(li.hasNext()){
			Object[] params = (Object[])li.next();
			System.out.println(params[0]+"\t"+params[1]+"\t"+params[2]);
		}
	}
	
	public void testFindPartyResultsInAllElectionsByRevenueVillagesInMandal(){
		List list = candidateBoothResultDAO.findPartyResultsInAllElectionsByRevenueVillagesInMandal(373l, 24l);
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]+"\t"+((Object[])list.get(i))[4]+"\t"+((Object[])list.get(i))[5]+"\t"+((Object[])list.get(i))[6]+"\t"+((Object[])list.get(i))[7]+"\t"+((Object[])list.get(i))[8]);
	}
	
	public void testGetResultsForElectionForAllMandalsAndParties(){
		List list = candidateBoothResultDAO.getResultsForElectionForAllMandalsAndParties("373","2009", IConstants.ASSEMBLY_ELECTION_TYPE);
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]+"\t"+((Object[])list.get(i))[4]);
	}
	
	
	
	@Test
	@SuppressWarnings("unchecked")
	public void testGetTownshipwiseResultsInElection(){
		
		List resultsList = candidateBoothResultDAO.findTownshipWiseAllPartyResultsInAMandal("Assembly","2009",new Long(127));
		
		System.out.println(" List Size :" + resultsList.size());
		if(resultsList != null && resultsList.size() > 0){
			log.debug(" DAO RESULT ..........");
			ListIterator resultLI = resultsList.listIterator();
			while(resultLI.hasNext()){
				Object[] params = (Object[])resultLI.next();
				
				System.out.print(" Township ID   :" + params[2]);
				System.out.print(" Township Name :" + params[3]);
				System.out.print("   Party         :" + params[5]);
				System.out.print(" Valid Votes   :" + params[1]);
				System.out.print(" Votes Earned  :" + params[0]);
				System.out.println(" ........................... ");
			}
					
		}
	}*/
	
    @Test
    public void testGetTehsilValidVotes(){
    	List tehsilValidVotes = candidateBoothResultDAO.getValidVotesInAMandal(new Long(127),"Assembly","2009");
    	if(tehsilValidVotes != null){
    		Object params = (Object)tehsilValidVotes.get(0);
    		System.out.println(" Mandal Valid Votes :" + params);
    	}
    }

}
