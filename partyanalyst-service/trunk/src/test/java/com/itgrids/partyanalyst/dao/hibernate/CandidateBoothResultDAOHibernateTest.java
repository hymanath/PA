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
	
	/*public void testGetPanchayatWisePartiesResultForAElectionInATehsil()
	{
		List<Object[]> list = candidateBoothResultDAO.getdata(1053l,38l);
		
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println();
			for(Object obj : params)
				System.out.print("\t"+obj.toString());
		}
	}*/
	
	/*public void testFindPanchayatWiseAllPartyResultsInAMandal()
	{
		List<Object[]> list = (List<Object[]>)candidateBoothResultDAO.findPanchayatWiseAllPartyResultsInAMandal(IConstants.ASSEMBLY_ELECTION_TYPE, "2009", 844l);
		
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println();
			for(Object obj : params)
				System.out.print("\t"+obj.toString());
		}
	}*/
	
	/*public void testGetPartiesParticipatedInAManadalForAnElection()
	{
		List<Long> list = candidateBoothResultDAO.getPartiesParticipatedInAManadalForAnElection(841l,38l);
		System.out.println(list.size());
		for(Long partyId : list)
			System.out.println(partyId);
	}
*/
	
	/*public void testgetPartyIdsByConstituencyIdAndElectionYear()
	{
		List<Long> partyIdsList = candidateBoothResultDAO.getPartyIdsByMandalIdAndElectionYear(844l, "2009");
		System.out.println(partyIdsList);
		
	}*/
	
	/*public void testgetPartyIdsByLocalEleBodyIdAndElectionYear()
	{
		List<Long> partyIdsList = candidateBoothResultDAO.getPartyIdsByLocalEleBodyIdAndElectionYear(83l, "2009");
		System.out.println(partyIdsList);
	}*/
	
	/*public void testgetAllPartiesCrossVotingReportByMandalIdAndAssemblyConstituencyId()
	{
		List<Long> partyIdsList = new ArrayList<Long>(0);
		partyIdsList.add(163l);
		partyIdsList.add(362l);
		partyIdsList.add(872l);
		partyIdsList.add(239l);
		partyIdsList.add(651l);
		partyIdsList.add(662l);
		partyIdsList.add(366l);
		partyIdsList.add(514l);
		
		List<Object[]> list = candidateBoothResultDAO.getAllPartiesCrossVotingReportByEleYearAndConstituencyId(IConstants.MANDAL,844l, 232l, "2009", partyIdsList);
		System.out.println(list.size());
		if(list != null && list.size() >0)
		for(Object[] params : list)
		{
			System.out.println(params[0]+" "+params[1]+" "+params[2]+" "+params[3]);
		}
		
	}*/
	
		
	/*public void testgetValidVotesByLocalEleBodyIdAndConstituencyId()
	{
		List list = candidateBoothResultDAO.getValidVotesByEleTypeAndConstituencyId(IConstants.MANDAL,844l, 232l, "2009");
		System.out.println(list);
	}*/
	
	
	/*public void testgetPartyIdsByMandalIdAndElectionYear()
	{
		List<Long> l = candidateBoothResultDAO.getPartyIdsByMandalIdAndElectionYear(IConstants.LOCALELECTIONBODY, 83l, "2009");
		System.out.println(l);
	}*/
	
	
	/*public void testgetPartyIdsListByEleIdAndYearAndConstId()
	{
		List<Long> list = candidateBoothResultDAO.getPartyIdsListByEleIdAndYearAndConstId(232l, 38l, "2009");
		
				System.out.println(list);
	}*/
	
	
	/*public void testgetVotesEarnedForSelectedbooth()
	{
		List<Object[]> list = candidateBoothResultDAO.getVotesEarnedForSelectedbooth(232l, 38l, 207l);
		for(Object[] params:list)
		 System.out.println(params[0]+":"+params[1]);
	}*/
	
	/*public void testGetTotalValidVotes()
	{
		List<Long> boothIdsList = new ArrayList<Long>(0);
		long[] array = new long[]{203, 201, 202, 206, 207, 210, 204, 205, 208, 209, 223, 224, 220, 221, 222, 225, 227, 228, 226, 229, 230, 231, 218, 213, 214, 215, 216, 211, 212, 217, 219, 243, 242, 240, 237, 238, 239, 235, 236, 232, 233, 234, 241};
		for(int i=0;i<array.length;i++)
			boothIdsList.add(array[i]);
		
		System.out.println(candidateBoothResultDAO.getTotalValidVotes(boothIdsList, 38l, 232L));
	}*/
	
	/*public void testGetValidVotesForMultipleBooths()
	{
		List<Long> boothIdsList = new ArrayList<Long>(0);
		long[] array = new long[]{203, 201, 202, 206, 207, 210, 204, 205, 208, 209, 223, 224, 220, 221, 222, 225, 227, 228, 226, 229, 230, 231, 218, 213, 214, 215, 216, 211, 212, 217, 219, 243, 242, 240, 237, 238, 239, 235, 236, 232, 233, 234, 241};
		//for(int i=0;i<array.length;i++)
			boothIdsList.add(241l);
			List<Object[]> results = candidateBoothResultDAO.getValidVotesForMultipleBooths(boothIdsList, 38l, 232L);
			Object[] value= results.get(0);
			System.out.println(value[0].toString()+" "+value[1].toString());
	}*/
	
/*	public void testGetConstituencyTotalVotes()
	{
	  System.out.println(candidateBoothResultDAO.getConstituencyTotalVotes(232l, 38l));
	}*/
	
	/*public void testgetParticipatedPartiesInConstituency(){
		List<Object[]> results = candidateBoothResultDAO.getParticipatedPartiesInConstituency(221l);
		for(Object[] params:results)
			 System.out.println(params[0]+":"+params[1]);
	}*/
	
	public void testgetPartyWiseDelimationEffectBasedOnVoters()
	{
		List<Long> partyIds = new ArrayList<Long>();
		partyIds.add(872l);
		partyIds.add(362l);
		partyIds.add(662l);
		List<Object[]> values = candidateBoothResultDAO.getPartyWiseAfterDelimationEffectBasedOnVoters(38l,282l);
		for (Object[] params : values) {
			 System.out.println(params[0]+":"+params[1]);
		}
	}
	/*public void testgetPartyWiseDelimationEffectBasedOnVoters()
	{
		List<Long> partyIds = new ArrayList<Long>();
		partyIds.add(872l);
		partyIds.add(362l);
		partyIds.add(662l);
		List<Long> boothIds = new ArrayList<Long>();
		boothIds.add(88400l);
		boothIds.add(88401l);
		boothIds.add(88402l);
		boothIds.add(88404l);
		boothIds.add(88405l);
		boothIds.add(88406l);
		boothIds.add(88407l);
		boothIds.add(88408l);
		boothIds.add(88409l);
		boothIds.add(88410l);
		boothIds.add(88411l);
		boothIds.add(88412l);
		boothIds.add(88413l);
		boothIds.add(88414l);
		boothIds.add(88415l);
		boothIds.add(88416l);
		boothIds.add(88417l);
		boothIds.add(88418l);
		boothIds.add(88419l);
		boothIds.add(88420l);
		boothIds.add(88421l);
		boothIds.add(88422l);
		boothIds.add(88423l);
		boothIds.add(88424l);
		boothIds.add(88425l);
		boothIds.add(88426l);
		boothIds.add(88427l);
		boothIds.add(88428l);
		boothIds.add(88429l);
		boothIds.add(88430l);
		boothIds.add(88431l);
		boothIds.add(88432l);
		boothIds.add(88433l);
		boothIds.add(88434l);
		boothIds.add(88435l);
		boothIds.add(88436l);
		boothIds.add(88437l);
		boothIds.add(88438l);
		boothIds.add(88439l);
		boothIds.add(88440l);
		boothIds.add(88441l);
		boothIds.add(88442l);
		boothIds.add(88443l);
		boothIds.add(88444l);
		boothIds.add(88445l);
		boothIds.add(88446l);
		boothIds.add(88447l);
		boothIds.add(88448l);
		boothIds.add(88449l);
		boothIds.add(88450l);
		boothIds.add(88451l);
		boothIds.add(88452l);
		boothIds.add(88453l);
		boothIds.add(88454l);
		boothIds.add(88455l);
		boothIds.add(88456l);
		boothIds.add(88457l);
		boothIds.add(88458l);
		boothIds.add(88459l);
		boothIds.add(88460l);
		boothIds.add(88461l);
		boothIds.add(88462l);
		boothIds.add(88463l);
		boothIds.add(88464l);
		boothIds.add(88465l);
		boothIds.add(88466l);
		boothIds.add(88467l);
		boothIds.add(88468l);
		boothIds.add(88469l);
		boothIds.add(88470l);
		boothIds.add(88471l);
		boothIds.add(88472l);
		boothIds.add(88473l);
		boothIds.add(88474l);
		boothIds.add(88475l);
		boothIds.add(88476l);
		boothIds.add(88477l);
		boothIds.add(88478l);
		boothIds.add(88479l);
		boothIds.add(88480l);
		boothIds.add(88481l);
		boothIds.add(88482l);
		boothIds.add(88483l);
		boothIds.add(88484l);
		boothIds.add(88485l);
		boothIds.add(88486l);
		boothIds.add(88487l);
		boothIds.add(88488l);
		boothIds.add(88489l);
		boothIds.add(88490l);
		boothIds.add(88491l);
		boothIds.add(88492l);
		boothIds.add(88493l);
		boothIds.add(88494l);
		boothIds.add(88495l);
		boothIds.add(88496l);
		boothIds.add(88497l);
		boothIds.add(88498l);
		boothIds.add(88499l);
		boothIds.add(88500l);
		boothIds.add(88501l);
		boothIds.add(88502l);
		boothIds.add(88503l);
		boothIds.add(88504l);
		boothIds.add(88505l);
		boothIds.add(88506l);
		boothIds.add(88507l);
		boothIds.add(88508l);
		boothIds.add(88509l);
		boothIds.add(88510l);
		boothIds.add(88511l);
		boothIds.add(88512l);
		boothIds.add(88513l);
		boothIds.add(88514l);
		boothIds.add(88515l);
		boothIds.add(88516l);
		boothIds.add(88517l);
		boothIds.add(88518l);
		boothIds.add(88519l);
		boothIds.add(88520l);
		boothIds.add(88521l);
		boothIds.add(88522l);
		boothIds.add(88523l);
		boothIds.add(88524l);
		boothIds.add(88525l);
		boothIds.add(88526l);
		boothIds.add(88527l);
		boothIds.add(88528l);
		boothIds.add(88529l);
		boothIds.add(88530l);
		boothIds.add(88531l);
		boothIds.add(88532l);
		boothIds.add(88533l);
		boothIds.add(88534l);
		boothIds.add(88535l);
		boothIds.add(88536l);
		boothIds.add(88537l);
		boothIds.add(88538l);
		boothIds.add(88539l);
		boothIds.add(88540l);
		boothIds.add(88541l);
		boothIds.add(88542l);
		boothIds.add(88543l);
		boothIds.add(88544l);
		boothIds.add(88545l);
		boothIds.add(88546l);
		boothIds.add(88547l);
		boothIds.add(88548l);
		boothIds.add(88549l);
		boothIds.add(88550l);
		boothIds.add(88551l);
		boothIds.add(88552l);
		boothIds.add(88553l);
		boothIds.add(88554l);
		boothIds.add(88555l);
		boothIds.add(88556l);
		boothIds.add(88557l);
		boothIds.add(88558l);
		boothIds.add(88559l);
		boothIds.add(88560l);
		boothIds.add(88561l);
		boothIds.add(88562l);
		boothIds.add(88563l);
		boothIds.add(88564l);
		boothIds.add(88565l);

		List<Object[]> values = candidateBoothResultDAO.getPartyWiseBeforDelimationEffectBasedOnVoters(3l,boothIds);
		for (Object[] params : values) {
			 System.out.println(params[0]+":"+params[1]);
		}
	}*/
}
