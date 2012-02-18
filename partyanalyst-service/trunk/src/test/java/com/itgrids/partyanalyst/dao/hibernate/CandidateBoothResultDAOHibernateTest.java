package com.itgrids.partyanalyst.dao.hibernate;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
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
		List list = candidateBoothResultDAO.findMandalWisePartiesResultsForElection(844l, 38l);
		System.out.println(list.size());
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
	
	/*public void testFindAssemblyWiseParliamentResultsForParties(){
		long start = System.nanoTime();
		List list = candidateBoothResultDAO.findAssemblyRegionResultsForPartiesForAConstituency(231l, 403l, "2009");
		long end = System.nanoTime();
		System.out.println((end-start)/1000000000);
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]);
	}*/
		
	/*public void testAssemblies(){
		List list = candidateBoothResultDAO.findAllPartiesElectionResultsInDistrictForElectionType(19l,"Parliament");
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]);
	}
	*/

	/*public void testAssemblies(){

		List list = candidateBoothResultDAO.getCandidatesResultsForElectionAndConstituencyByMandal(50l,"2009");
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]);

	}*/
	
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
	
	/*public void testGetResultsForElectionForAllMandalsAndParties(){
		List list = candidateBoothResultDAO.getResultsForElectionForAllMandalsAndParties("373","2009", IConstants.ASSEMBLY_ELECTION_TYPE);
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]+"\t"+((Object[])list.get(i))[4]);
	}*/
	
	
	
	/*@Test
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
	
   /* @Test
    public void testGetTehsilValidVotes(){
    	List tehsilValidVotes = candidateBoothResultDAO.getValidVotesInAMandal(new Long(127),"Assembly","2009");
    	if(tehsilValidVotes != null){
    		Object params = (Object)tehsilValidVotes.get(0);
    		System.out.println(" Mandal Valid Votes :" + params);
    	}
    }*/

	/*public void testFindAssemblyWiseParliamentResultsForPartiesInAssembly(){
		List list = candidateBoothResultDAO.findAssemblyWiseParliamentResultsForPartiesInAssembly(362l, "2009");
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]+"\t"+((Object[])list.get(i))[4]+"\t"+((Object[])list.get(i))[5]+"\t"+((Object[])list.get(i))[6]+"\t"+((Object[])list.get(i))[7]+"\t"+((Object[])list.get(i))[8]+"\t"+((Object[])list.get(i))[9]+"\t"+((Object[])list.get(i))[10]);
		System.out.println(list.size());
	}*/
	
	/*public void testFindElectionResultsForAMappedConstituencyByElectionType(){
		List list = candidateBoothResultDAO.findElectionResultsForAMappedConstituencyByElectionType(363l, "2004");
		System.out.println("====================="+list.size());
		System.out.println(list);
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]+"\t"+((Object[])list.get(i))[4]+"\t"+((Object[])list.get(i))[5]+"\t"+((Object[])list.get(i))[6]+"\t"+((Object[])list.get(i))[7]+"\t"+((Object[])list.get(i))[8]+"\t"+((Object[])list.get(i))[9]+"\t"+((Object[])list.get(i))[10]+"\t"+((Object[])list.get(i))[11]);
	}*/
	
	/*public void testFindElectionResultsForAConstituencyByElectionYear(){
		List list = candidateBoothResultDAO.findElectionResultsForAConstituencyByElectionYear(363l, "2009");
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]+"\t"+((Object[])list.get(i))[4]+"\t"+((Object[])list.get(i))[5]+"\t"+((Object[])list.get(i))[6]+"\t"+((Object[])list.get(i))[7]+"\t"+((Object[])list.get(i))[8]+"\t"+((Object[])list.get(i))[9]+"\t"+((Object[])list.get(i))[10]);
	}
	*/
	
	/*public void testGetAllPartiesResultsByMandalsMappedConstituency(){
		List list = candidateBoothResultDAO.getAllPartiesResultsByMandalsMappedConstituency(362l, "2004", IConstants.ASSEMBLY_ELECTION_TYPE);
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]+"\t"+((Object[])list.get(i))[4]+"\t"+((Object[])list.get(i))[5]);
	}*/
	
	/*public void testGetBoothwiseCandidateResultsForGivenPartNosInAnElectionYear(){
		List partNos = new ArrayList();
		partNos.add("1");
		partNos.add("2");
		partNos.add("3");
		partNos.add("4");
		List list = candidateBoothResultDAO.getBoothwiseCandidateResultsForGivenPartNosInAnElectionYear(partNos, "2009", 4l);
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]+"\t"+((Object[])list.get(i))[4]+"\t"+((Object[])list.get(i))[5]);
	}*/
	
	/*public void testGetPartNosOfAnElectionForAConstituency(){
		List list = candidateBoothResultDAO.getPartNosOfAnElectionForAConstituency(341l, "2010");
		System.out.println(list);
	}*/
	
	/*public void testGetCandidatesResultsForElectionAndConstituencyByLocalElectionBody(){
		//List list = candidateBoothResultDAO.getCandidatesResultsForElectionAndConstituencyByLocalElectionBody(232l, "2009");
		//List list = candidateBoothResultDAO.getCandidatesResultsForElectionAndConstituencyByMandal(232l,"2009");
		List list = candidateBoothResultDAO.getCandidatesResultsForElectionAndConstituencyByLocalElectionBodyWard(232l,"2009");
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]+"\t"+((Object[])list.get(i))[4]+"\t"+((Object[])list.get(i))[5]+"\t"+((Object[])list.get(i))[6]+"\t"+((Object[])list.get(i))[7]+"\t"+((Object[])list.get(i))[8]+"\t"+((Object[])list.get(i))[9]);
		 
	}*/
	
	/*public void testGetBoothwisePartyResultsOfNominationInMandalWithInConstituency(){
		List list = candidateBoothResultDAO.getBoothwisePartyResultsOfNominationInMandalWithInConstituency(16261l, 232l, 835l);
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]+"\t"+((Object[])list.get(i))[4]);
	}*/
	
	/*public void testGetBoothwisePartyResultsOfNominationInLocalBodyWithInConstituency(){
		List list = candidateBoothResultDAO.getBoothwisePartyResultsOfNominationInLocalBodyWithInConstituency(16261l, 232l, 835l);
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]+"\t"+((Object[])list.get(i))[4]);
	}*/
	
	/*public void testGetBoothwisePartyResultsOfNominationInLocalBodyWardWithInConstituency(){
		List list = candidateBoothResultDAO.getBoothwisePartyResultsOfNominationInLocalBodyWardWithInConstituency(16261l, 232l, 835l);
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]+"\t"+((Object[])list.get(i))[4]);
	}*/
	
	/*public void testGetBoothwisePartyResultsOfNominationInUnMappedBoothsWithInConstituency(){
		List list = candidateBoothResultDAO.getBoothwisePartyResultsOfNominationInUnMappedBoothsWithInConstituency(14147l, 315l, "2009");
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]+"\t"+((Object[])list.get(i))[4]);
	}*/
	
	/*public void testGetCandidatesResultsForElectionAndConstituencyByMandalWise()
	{
	  List obj = candidateBoothResultDAO.getCandidatesResultsForElectionAndConstituencyByMandalWise(232l,"2009","Assembly");
	  System.out.println(obj.size());
	}*/
	
	/*public void testFindBoothResultsForBoothsAndElection()
	{
		List<Long> boothslist = new ArrayList<Long>(0);
		boothslist.add(211l);boothslist.add(212l);boothslist.add(213l);boothslist.add(214l);
		boothslist.add(215l);boothslist.add(216l);boothslist.add(217l);boothslist.add(218l);boothslist.add(219l);
		
		List<Object[]> list = candidateBoothResultDAO.findBoothResultsForBoothsAndElection(boothslist,38l);
		System.out.println(list.size());
		
		long totalvotes = 0;
		for(Object[] params : list)
		{
			totalvotes += (Long)params[2];
			System.out.println("Party Id - "+params[0]+" Party Name - "+params[1]+" Votes Earned - "+params[2]);
		}
		System.out.println("Total Votes - "+totalvotes);
	}*/
	
	/*public void testFindPachayatWisePartiesResultsForElectionInATehsil()
	{
		List<Object[]> list = candidateBoothResultDAO.findPachayatWisePartiesResultsForElectionInATehsil(844l,38l);
		
		System.out.println(list.size());
		for(Object[] params : list)
		{
			System.out.println();
			for(Object obj : params)
				System.out.print("\t"+obj.toString());
		}
	}*/
	
	/*public void test()
	{
		try
		{
			InetAddress addr = InetAddress.getByName("180.76.5.155");
			System.out.println(addr.getHostName());
			System.out.println(addr.getCanonicalHostName());
		}catch (Exception e) {
			e.printStackTrace();
		}
		

	}*/
	
	public void testGetPanchayatWisePartiesResultForAElectionInATehsil()
	{
		List<Object[]> list = candidateBoothResultDAO.getPanchayatWisePartiesResultForAElectionInATehsil(844l,38l);
		
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println();
			for(Object obj : params)
				System.out.print("\t"+obj.toString());
		}
	}

}
