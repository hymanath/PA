package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyRebelCandidateDAO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.service.IDelimitationConstituencyMandalService;

public class NominationDAOHibernateTest extends BaseDaoTestCase {
	
	private INominationDAO nominationDAO;
	private IPartyRebelCandidateDAO partyRebelCandidateDAO;
	private ConstituencyElectionResultsVO constituencyElectionResultsVO ;
	private IConstituencyDAO constituencyDAO;
	private IConstituencyElectionDAO constituencyElectionDAO;
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	private IDelimitationConstituencyMandalService delimitationConstituencyMandalService; 
	
	public IDelimitationConstituencyAssemblyDetailsDAO getDelimitationConstituencyAssemblyDetailsDAO() {
		return delimitationConstituencyAssemblyDetailsDAO;
	}

	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}
	public IConstituencyElectionDAO getConstituencyElectionDAO() {
		return constituencyElectionDAO;
	}

	public void setConstituencyElectionDAO(
			IConstituencyElectionDAO constituencyElectionDAO) {
		this.constituencyElectionDAO = constituencyElectionDAO;
	}

	public void setPartyRebelCandidateDAO(
			IPartyRebelCandidateDAO partyRebelCandidateDAO) {
		this.partyRebelCandidateDAO = partyRebelCandidateDAO;
	}
	
	public void setNominationDAO(INominationDAO nominationDAO){
		this.nominationDAO = nominationDAO;
	}

	/*public void testFindByStatePartyAndElectionId() {
		List<Nomination> nominations = nominationDAO.findByElectionIdAndPartyIdStateIdAndDistrictId(10l, 24l, 23l);
		System.out.println(nominations.size());
	}
	
	public void testFindByConstituencyElection(){
		List actualResult = nominationDAO.findByConstituencyElection(new Long(23));
		for(int i = 0; i< actualResult.size();i++)
		{	
			Object[] nominationObj = (Object[])actualResult.get(i);
			System.out.println("sName=:"+nominationObj[0].toString());
			System.out.println("lName=:"+nominationObj[1].toString());
			System.out.println("total votes=:"+Double.parseDouble(nominationObj[2].toString()));
			System.out.println("rank votes=:"+Long.parseLong(nominationObj[3].toString()));
		}	
		
	}
	
	/*
	public void testFindByStatePartyAndElectionId() {
		List<Nomination> nominations = nominationDAO.findByStatePartyAndElectionId(new Long(1), new Long(27), new Long(1));
		
		List<PartyRebelCandidate> rebelCandidates = partyRebelCandidateDAO.findByPartyIdAndElectionId(new Long(1), new Long(27));
		List<Long> rebelIds = new ArrayList<Long>();
		for(PartyRebelCandidate cand : rebelCandidates) {
			rebelIds.add(cand.getCandidate().getCandidateId());
		}
		for(Nomination nomination : nominations) {
			Candidate candidate = nomination.getCandidate();
			if(!rebelIds.contains(candidate.getCandidateId())) {
				System.out.println(candidate.getLastname());
			}
		}
	}
	
	public void testParliamentConstituencyNomination(){
		List<Nomination> list = nominationDAO.findByConstituencyPartyAndElectionYear(new Long(1), new Long(913), "2004");
		System.out.println(list);
		assertEquals(list.get(0).getCandidate().getLastname(), "Magunta");
	}
	
	public void testFindPartiesForConstituencyAndElection(){
		List<Party> list = nominationDAO.findPartiesByConstituencyAndElection(new Long(232), "2009");
		System.out.println(list.get(0).getLongName());
		assertEquals(10, list.size());
		
	}
	
	public void testFindByConstituencyPartyAndElectionYear(){
		List<Nomination> list = nominationDAO.findByConstituencyPartyAndElectionYear(new Long(1), new Long(232), "2009");
		assertEquals(1, list.size());
	}
	
	public void testFindConstitueniesByPartyAndElectionType(){
		List<Constituency> list = nominationDAO.findConstitueniesByPartyAndElectionType(new Long(1), new Long(1), "2004");
		assertEquals(1, list.size());
	}*/
	
	/*public void testFindByConstituencyPartyAndElectionYearIncludingAliance(){
		List<Long> partyIds = new ArrayList<Long>();
		partyIds.add(new Long(1));
		partyIds.add(new Long(11));
		List<Nomination> list = nominationDAO.findByConstituencyPartyAndElectionYearIncludingAliance(partyIds, new Long(408), "2009");
		assertEquals(1, list.size());
	}
	
	/*public void testConstituencyElection(){
		List result = nominationDAO.findAllCandidatesForAnElectionByElectionYear(3382l);
		assertEquals(1, result.size());
	}*/

	/*public void testFindByConstituencyPartyAndElectionYear(){
		
		List list = nominationDAO.findCandidateNamePartyByConstituencyAndElection("3382", "2009");		
		System.out.println(list.size());
	}*/
	
	/*public void testFindByElectionTypeTehsilAndParty(){
		List list = nominationDAO.findMPTCInfoByElectionTypeTehsilAndParty(784l,872l);
		for(int i=0; i<list.size(); i++){
			Object[] params = (Object[])list.get(0);
			Double totalVotesPolled = (Double)params[2];
			Double totalValidVotes = (Double)params[3];
			Double votesEarned = (Double)params[4];
			System.out.println("--"+totalValidVotes);
		}
		System.out.println("Size"+list.size());
	}*/
	
	/*public void testFindValidVotesOfAllCandiatesOfAMandalByElectionTypeMandalAndYear(){
		List list = nominationDAO.findValidVotesOfAllCandiatesOfAMandalByElectionTypeMandalAndYear("MPTC", "2001", new Long(844));
		System.out.println(list.get(0));
	}*/
	
	/*public void testFindLocalLeadersOfMandal(){
		List list = nominationDAO.findLocalLeadersOfMandal(new Long(844));
		for(int i = 0; i<list.size(); i++){
			Object[] values = (Object[])list.get(i);
			String name = ((Candidate)values[0]).getLastname();
			String designation = (String)values[1];
			String party = (String)values[2];
			String year = (String)values[3];
			String constituencyName = (String)values[4];
			
			System.out.println(name+"--"+designation+"--"+party+"--"+year+"--"+constituencyName);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void testCandidateNPartyInfo(){
		List list = nominationDAO.getCandidateNPartyInfo("341","Assembly",new Long(1));
		
		if(list != null){
		Object[] resultList = (Object[])list.get(0);
		
		Long constiId = (Long)resultList[0];
		String constiName = (String)resultList[1];
		Long candId = (Long)resultList[2];
		String firstName = (String)resultList[3];
		String midName = (String)resultList[4];
		String lastName = (String)resultList[5];
		Long partyId = (Long)resultList[6];
		String partyName = (String)resultList[7];
		
		Assert.assertEquals(new Long(341), constiId);
		Assert.assertEquals("Nellore Rural", constiName);
		Assert.assertEquals(null, firstName);
		Assert.assertEquals("Anam Vivekananda Reddy", lastName);
		Assert.assertEquals("INC", partyName);
		
		}
	}*/
	
	/*public void testGetParliamentCandidateNPartyInfo()
	{		
		List list = nominationDAO.getParliamentCandidateNPartyInfo(404l,IConstants.PARLIAMENT_ELECTION_TYPE,1L);
		if(list !=null)
		{
			Object[] result = (Object[]) list.get(0);
			
			Long constiId = (Long)result[0];
			String constiName = (String)result[1];
			
			String firstName = (String)result[3];
			String midName = (String)result[4];
			String lastName = (String)result[5];
			Long partyId = (Long)result[6];
			String partyName = (String)result[7];
			
			Assert.assertEquals(new Long(404), constiId);
			Assert.assertEquals("Nellore", constiName);
			Assert.assertEquals(null, firstName);
			Assert.assertEquals("Mekapati Rajamohan Reddy", lastName);
			Assert.assertEquals("INC", partyName);
			
		}	
	}
	public void testGetAllPartiesOfElectionTypeInTehsil(){
		List parties = nominationDAO.getAllPartiesOfElectionTypeInMandal(844l, IConstants.MPTC_ELECTION_TYPE);
		System.out.println(parties.size());
		for(int i=0; i < parties.size(); i++){
			Object[] params = (Object[])parties.get(i);
			System.out.println(params[0]+"--"+params[1]);
			Long partyId = (Long)params[0];
			String name = params[1].toString();
			if(partyId == 24)
				assertEquals(name, "INC");
		}
	}
	

	
	public void testGetAllConstiteunciesInfoForPartyInTehsil(){
		List list = nominationDAO.getAllConstiteunciesInfoForPartyInTehsil(844l, 24l, 5l);
		System.out.println(list.size());
		for(int i=0; i < list.size(); i++){
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]+"\t"+((Object[])list.get(i))[4]+"\t"+((Object[])list.get(i))[5]+"\t"+((Object[])list.get(i))[6]+"\t"+((Object[])list.get(i))[7]+"\t"+((Object[])list.get(i))[8]+"\t"+((Object[])list.get(i))[9]);
		}
	}
	
	*/
	
	/*public void testConstituencyCandidatesInfo(){
		List result = nominationDAO.getCandidatesInfoForTheGivenConstituency("3358","2004",IConstants.ASSEMBLY_ELECTION_TYPE);
		System.out.println(result.size());
	}*/	
	/*
	public void testFindTotalMPtc(){
		List zptcCount = nominationDAO.getZptcCountInADistrict(19l,IConstants.ZPTC_ELECTION_TYPE,IConstants.ZPTC_ELECTION_TYPE,1L);
		Assert.assertEquals(1, zptcCount.size());	
		List mptcCount = nominationDAO.getMptcCountInADistrict(19l,IConstants.MPTC_ELECTION_TYPE,IConstants.MPTC_ELECTION_TYPE,1L);
		Assert.assertEquals(1, mptcCount.size());	
	}
	
	public void testFindCandidatesByDistrictId(){
		List result = nominationDAO.findAllCandidatesForAnElectionByElectionYearByDistrictId(19l,IConstants.ASSEMBLY_ELECTION_TYPE);
		Assert.assertEquals(1, result.size());	
	}
	
	public void testAllZPTCsInaDistrict(){
		List result = nominationDAO.findAllZPTCsInaDistrict(19l,IConstants.ZPTC_ELECTION_TYPE,1l,"2006");
		Assert.assertEquals(46, result.size());	
	}
	public void testAllMPTCsInaDistrict(){
		List result = nominationDAO.findAllMPTCsInaDistrict(19l,IConstants.MPTC_ELECTION_TYPE,1l,"2006");
		Assert.assertEquals(598, result.size());	
	}
	
	public void testPartyStatusInAZptc(){
		List result1 = nominationDAO.getPartysWinningCandidateInfoForAParticularElectionYear(IConstants.ZPTC_ELECTION_TYPE,"2001",1l,19l);
		Assert.assertEquals(1, result1.size());	
		List result = nominationDAO.getPartysInfoForAParticularElectionYear(IConstants.ZPTC_ELECTION_TYPE,"2001",19l);
		Assert.assertEquals(1, result.size());	
	}
	public void testPartyWinningStatusInAZptc(){
		List result = nominationDAO.getPartysWinningCandidateInfoForAParticularElectionYear(IConstants.ZPTC_ELECTION_TYPE,"2006",1l,19l);
		Assert.assertEquals(1, result.size());	
	}
	
	public void testGetAllCandidatesByElectionTypes(){
		long startTime = System.currentTimeMillis();
		List values = nominationDAO.getAllCandidatesByElectionTypes(" 'Assembly' , 'Parliament' ");
		long endTime = System.currentTimeMillis();
		System.out.println((endTime-startTime)/1000);
		System.out.println(values.size());
	}*/
	/*
	public void testByAllZptcCandidatesForAnElectionYear(){
		List result = nominationDAO.findAllZptcCandidatesInaDistrict(19l,IConstants.ZPTC_ELECTION_TYPE,"2006");
		Assert.assertEquals(1, result.size());	
	}
	public void testByAllMptcCandidatesForAnElectionYear(){
		List result = nominationDAO.findAllZptcCandidatesInaDistrict(19l,IConstants.MPTC_ELECTION_TYPE,"2006");
		Assert.assertEquals(1, result.size());	
	}
	public void testByAllMptcPartysParticipatedForAnElectionYear(){		
		List result2 = nominationDAO.findAllZPTCsInaDistrict(19l,IConstants.ZPTC_ELECTION_TYPE,2l,"2006");
		Assert.assertEquals(1, result2.size());	
		List result = nominationDAO.findAllZPTCsInaDistrict(19l,IConstants.ZPTC_ELECTION_TYPE,1l,"2006");
		Assert.assertEquals(1, result.size());	
	}	
	
	public void testFindAllZptcCandidatesInaDistrict(){
		List result = nominationDAO.findAllZPTCsInaDistrict(19l,IConstants.ZPTC_ELECTION_TYPE,1l,"2001");
		List result2 = nominationDAO.findAllZptcPartysInaDistrict(19l,IConstants.ZPTC_ELECTION_TYPE,"2006",24l,1l);
		List result3 = nominationDAO.findAllZPTCsInaDistrict(19l,IConstants.ZPTC_ELECTION_TYPE,2l,"2001");
		Assert.assertEquals(1, result.size());	
		Assert.assertEquals(1, result2.size());
		Assert.assertEquals(1, result3.size());
	}
	
	public void testFindAllZptcPartysInaDistrict(){
		List result = nominationDAO.findAllZPTCsInaDistrict(19l,IConstants.ZPTC_ELECTION_TYPE,1l,"2001");
		Assert.assertEquals(1, result.size());		
	}
	
	public void testParties(){
			 List result = nominationDAO.getAllPartysForAParticularElectionYear(19l,IConstants.MPTC_ELECTION_TYPE,"2001");
			 Assert.assertEquals(1, result.size());	
	}
*/
	
	
/*	public void testFindCandidatesInfoByConstituencyAndElectionYear(){
		List list = nominationDAO.findCandidatesInfoByConstituencyAndElectionYear(403l, "2009");
		for(Object[] values:(List<Object[]>)list){
			System.out.println(values[0]+"\t"+values[1]+"\t"+values[2]+"\t"+values[3]);
			System.out.println(((Double)values[2]).longValue() - 100l);
		}
	}*/
	
	/*public void testFindPartiesInfoByElectionAndPartyGroupByState(){
		long start = System.currentTimeMillis();
		List list = nominationDAO.findPartiesInfoByElectionAndPartyGroupByState(11l, "24");
		long end = System.currentTimeMillis();
		System.out.println((end-start)/1000);
		for(Object[] values:(List<Object[]>)list)
			System.out.println(values[0]+"\t"+values[1]+"\t"+values[2]+"\t"+values[3]+"\t"+values[4]+"\t"+values[5]);
		assertEquals(35, list.size());
	}*/
	
	/*public void testFindPartiesInfoByElectionAndPartyGroupByDistrict(){
		List list = nominationDAO.findPartiesInfoByElectionAndPartyGroupByDistrict(4l, "24,61,17,18");
		for(Object[] values:(List<Object[]>)list)
			System.out.println(values[0]+"\t"+values[1]+"\t"+values[2]+"\t"+values[3]+"\t"+values[4]+"\t"+values[5]+"\t"+values[6]);
		assertEquals(35, list.size());
	}*/
	
	/*public void testFindPartyWonConstituenciesInfoByElectionAndPartyGroupByState(){
		List list = nominationDAO.findPartyWonConstituenciesInfoByElectionAndPartyGroupByState(10l, "24,62", 1l);
		for(Object[] values:(List<Object[]>)list)
			System.out.println(values[0]+"\t"+values[1]+"\t"+values[2]+"\t"+values[3]+"\t"+values[4]+"\t"+values[5]);
		assertEquals(35, list.size());
	}*/
	
	/*public void testFindPartyWonConstituenciesInfoByElectionAndPartyGroupByDistrict(){
		List list = nominationDAO.findPartyWonConstituenciesInfoByElectionAndPartyGroupByDistrict(10l, "32", 1l);
		for(Object[] values:(List<Object[]>)list)
			System.out.println(values[0]+"\t"+values[1]+"\t"+values[2]+"\t"+values[3]+"\t"+values[4]);
		assertEquals(35, list.size());
	}*/
	
	/*public void testFindConstituencyElectionsByElectionPartyAndPosition(){
		List list = nominationDAO.findConstituencyElectionsByElectionPartyAndPosition(11l, 24l, 1l);
		System.out.println(list.size());
	}*/
	
	/*public void testFindConstituencyElectionByElectionIdAndStateAndPartys(){
		List<Long> partyIds = new ArrayList<Long>();
		partyIds.add(61l);
		partyIds.add(62l);
		List<ConstituencyElection> list = nominationDAO.findConstituencyElectionByElectionIdAndStateAndPartys(17l, 1l, partyIds);
		System.out.println(list.size());
	}*/
	
	/*public void testFindElectionResultsForACandidateForAnElectionInAConstituency(){
		List list = nominationDAO.findElectionResultsForACandidateForAnElectionInAConstituency(380l, 18l, 24l);
		System.out.println(list.size());
	}
	
	@SuppressWarnings("unchecked")
	public void testFindElectionResultsForAllPartiesInAssemblyConstituencies()
	{
		List<Long> constituencyIds = new ArrayList<Long>();
		constituencyIds.add(4L);
		constituencyIds.add(8L);
		constituencyIds.add(362L);
		
		List list1 = nominationDAO.findElectionResultsForAllPartiesInAssemblyConstituencies("2004",constituencyIds,"0.75");
		for(Object[] values:(List<Object[]>)list1)
			System.out.println(values[0]+"\t"+values[1]+"\t"+values[2]+"\t"+values[3]+"\t"+values[4]+"\t"+values[5]+"\t"+values[6]);
			System.out.println("____________________________________________________________________________");
		List list2 = nominationDAO.findElectionResultsForAllPartiesInAssemblyConstituencies("2009",constituencyIds,"0.75");
		for(Object[] values1:(List<Object[]>)list2)
			System.out.println(values1[0]+"\t"+values1[1]+"\t"+values1[2]+"\t"+values1[3]+"\t"+values1[4]+"\t"+values1[5]);

		
	}
	public void testFindWinningCandidatesDetailsInBiElectionContituencies()
	{
		List<Long> constituencyIds = new ArrayList<Long>();
		constituencyIds.add(4L);
		constituencyIds.add(8L);
		constituencyIds.add(362L);
		
		List list1 = nominationDAO.findWinningCandidatesDetailsInContituencies("2009",constituencyIds);
		for(Object[] values:(List<Object[]>)list1)
			System.out.println(values[0]+"\t"+values[1]+"\t"+values[2]+"\t"+values[3]+"\t"+values[4]+"\t"+values[5]+"\t"+values[6]+"\t"+values[7]);
			System.out.println("____________________________________________________________________________");		
	}
	*/

	/*public void testFindOppositionCandidateVotesPercentageInConstituencies()

	{
		List<Long> constituencyIds = new ArrayList<Long>();
		constituencyIds.add(4L);
		constituencyIds.add(8L);
		constituencyIds.add(362L);
		
		List list1 = nominationDAO.findOppositionCandidateVotesPercentageInConstituencies("2009",constituencyIds);
		for(Object[] values:(List<Object[]>)list1)
			System.out.println(values[0]+"\t"+values[1]);
			System.out.println("____________________________________________________________________________");		
	}*/
	
    /*
	public void testGetRankOfACandidate(){
		List rank = nominationDAO.getCandidateRankInAConstituencyElection(new Long(339),"2009","Assembly",new Long(43));
		if(rank != null){
			Object params = (Object)rank.get(0);
			System.out.println("Rank :" + params);
		}
	}*/
	
	//@SuppressWarnings("unchecked")
	/*@SuppressWarnings("unchecked")
	public void testGetWonAndOppCandidateResults(){
		String ranks = "1,2";
		List results = nominationDAO.getWonAndOppCandidateInAnElection(new Long(4),"2009");
		
		if(results != null){
			for(int i=0;i<results.size();i++){
			Object[] params = (Object[])results.get(i);
			System.out.print(" Party     " + params[1]);
			System.out.print(" Candidate " + params[3]);
			
			System.out.println("..");
			}
		}
	}
	
	public void testGetMunicipalitiesAndCorporationsResultsInMandals(){
		List list = nominationDAO.getMunicipalitiesAndCorporationsResultsInMandals("384");
		System.out.println(list.size());
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]+"\t"+((Object[])list.get(i))[4]+"\t"+((Object[])list.get(i))[5]+"\t"+((Object[])list.get(i))[6]);
	}*/
	/*@Test
	public void testGetConstituencyElectionResult(){
		
		List resultList = nominationDAO.getResultsForElectionInConstituency(new Long(30),"2010");
		System.out.println(" Size :" + resultList.size());
		if(resultList != null && resultList.size() > 0){
			Iterator it = resultList.listIterator();
			while(it.hasNext()){
			 Object[] params = (Object[])it.next();
			 			 
			 System.out.println(" Votes Earned :" + params[1]);
			 System.out.println(" Valid Votes  :" + params[3]);
			 Double votesPercent = new Double(params[4].toString());
			 System.out.println(" Votes Percent :" + votesPercent);
			}
		}
	}
	
	public void testGetElectionsInState(){
		List<Election> list = nominationDAO.getElectionsInState(1l);
		for(Election ele:list)
			System.out.println(ele.getElectionYear());
	}
	*/
	/*public void testGet(){
		getElectionIdsAndYearsByElectionScope(1l,43l);
	}
	public List<SelectOptionVO> getElectionIdsAndYearsByElectionScope(Long electionScopeId,Long partyId){
		List<SelectOptionVO> electionYearslist;	
		List elections;
		try{
			electionYearslist = new ArrayList<SelectOptionVO>();	
			elections = nominationDAO.findByElectionScopeIdAndPartyId(electionScopeId, IConstants.ELECTION_SUBTYPE_MAIN,partyId);
			for(int i=0;i<elections.size();i++){
				Object[] parms = (Object[])elections.get(i);
				electionYearslist.add(new SelectOptionVO(Long.parseLong(parms[0].toString()),parms[1].toString()));		
				System.out.println(Long.parseLong(parms[0].toString())+"---"+parms[1].toString());
			}			
			return electionYearslist;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}		
	}*/
	
	/*
	public void testGetAllConstituencyElectionDetails(){
		List<ConstituencyElection> allianceConstituencies = nominationDAO.getAllAllianceConstituenciesForAPartyInAnElection(4l,"61,17,18","24");	
		assertEquals(allianceConstituencies.size(), 98);
	}
	
	public void testGetAllConstituencyElectionDetailsInADistrict(){
		List<ConstituencyElection> allianceConstituencies = nominationDAO.getAllAllianceConstituenciesForAPartyInAnElection(4l,"61,17,18","24");
		assertEquals(allianceConstituencies.size(), 98);
	}*/
	
	/*@Test
	public void testFindNominations(){
		List results = nominationDAO.getCandidateNPartyInfo("8521", IConstants.ASSEMBLY_ELECTION_TYPE, 1L, IConstants.ELECTION_SUBTYPE_MAIN,24L);
		
		System.out.println(results.size());
	}*/
	
/*	public void testGetConstituenciesCountByDistrictForElectionStateAndParties(){
		System.out.println(nominationDAO.getConstituenciesCountByDistrictForElectionStateAndParties(4l, 1l, "361").size());
	}*/
	
	/*public void testGetAll(){
		List<Nomination> nominations = nominationDAO.getAll();
		assertEquals(nominations.size() >= 0, true);
	}*/
	
	/*@SuppressWarnings("unchecked")
	@Test
	public void testGetLocalElectionResultsForParticipatedParties(){
		List results = nominationDAO.getAllParticipatedPartyResultsInALocalBodyElection(488L, "2005");
		
		if(results != null && results.size() > 0){
			ListIterator li = results.listIterator();
			while(li.hasNext()){
				Object[] values = (Object[])li.next();
				System.out.println(" Party ID :" + (Long)values[0] + " Party :" + (String)values[1] + " Participated In :" + (Long)values[2] + " Valid Votes :" + (Double)values[3]);
				
			}
		}
	}*/
	
	/*@SuppressWarnings("unchecked")
	@Test
	public void testGetAllPartysSeatsStatusBasedOnRank(){
		List results = nominationDAO.getResultsOfAllPartiesInLocalBodyELectionsBasedOnNthRank(488L, "2005", 3L);
		
		if(results != null && results.size() > 0){
			ListIterator li = results.listIterator();
			while(li.hasNext()){
				Object[] values = (Object[])li.next();
				System.out.println(" Party ID :" + (Long)values[0] + " Party :" + (String)values[1] + " Seats Gained :" + values[2]);
			}
		}
	}*/

/*	public void testGetAllCandidatesByElectionTypes(){
		long startTime = System.currentTimeMillis();
		List values = nominationDAO.getAllCandidatesByElectionTypeInState("Assembly", 1l, "");
		System.out.println("size:"+values.size());
		for(int i = 0; i<values.size(); i++)
		{
			System.out.println(((Object[])values.get(i))[0]+"\t"+((Object[])values.get(i))[1]+"\t"+((Object[])values.get(i))[2]+"\t"+((Object[])values.get(i))[3]);
		}
		long endTime = System.currentTimeMillis();
		System.out.println((endTime-startTime)/1000);
		//System.out.println(values.size());
	}*/
	/*public void testFindAllElectionResultsForConstituencies(){
	List list = nominationDAO.findAllElectionResultsForConstituencies("10876,10877,10878,10880, 10882");
	for(int i=0; i<list.size(); i++)
		System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]+"\t"+((Object[])list.get(i))[4]+"\t"+((Object[])list.get(i))[5]+"\t"+((Object[])list.get(i))[6]+"\t"+((Object[])list.get(i))[7]+"\t"+((Object[])list.get(i))[8]+"\t"+((Object[])list.get(i))[9]+"\t"+((Object[])list.get(i))[10]);
}*/
/*
public void testGetLocalBodiesElecCandidateDetailsForAnElection(){
	List list = nominationDAO.getLocalBodiesElecConstituenciesDetailsForAnElection(19l, "488, 478");
	for(int i=0; i<list.size(); i++)
		System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]);
}*/

	/*public void testGet(){
		List list = nominationDAO.getALLPartiesByElectionId(33l,"18398,18400,18441,18447");
		System.out.println(list.size());
	}*/
	
	/*public void testGet(){
		Long start = System.currentTimeMillis();
		List list = nominationDAO.getNominations("select model from Constituency model");
		Long end = System.currentTimeMillis(); 
		System.out.println(list.size()+" Time Taken = "+(end - start)/1000);
	}
	
	public void testGetVotesInAConstituency(){
		List list = nominationDAO.getVotesInfoForAConstituency("18420","2009","Greater Municipal Corp");
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]);
		
		List list2 = nominationDAO.getVotesInfoForAConstituency("8","2009","Assembly");
		for(int i=0; i<list2.size(); i++)
			System.out.println(((Object[])list2.get(i))[0]+"\t"+((Object[])list2.get(i))[1]);
	}*/
	
	/*public void testGetLocalBodyData(){
		List list = nominationDAO.getCandidatesInfoForTheGivenMuncipalityOrCorporationConstituency("18420","2009","Greater Municipal Corp");
		for(int i=0; i<list.size(); i++){
			Object[] parms = (Object[])list.get(i);
		//	System.out.println(parms[0]+"\t"+parms[1]+"\t"+parms[2]+"\t"+parms[3]+"\t"+parms[4]+"\t"+parms[5]+"\t"+parms[6]+"\t"+parms[7]+"\t"+parms[8]+"\t"+parms[9]+"\t"+parms[10]+"\t"+parms[11]+"\t"+parms[12]+"\t"+parms[13]+"\t"+parms[14]+"\t");
		}
	
	}
	
	/*@Test
	public void testGetNominationsForLocalElectionBodyElection(){
		
		List list2 = nominationDAO.getAllElectionIdsAndYearsForADistrict(1L);
		for(int i=0; i<list2.size(); i++)
			System.out.println(((Object[])list2.get(i))[0]+"\t"+((Object[])list2.get(i))[1]);
		
	}*/
	
	/*@SuppressWarnings("unchecked")
	@Test
	public void testGetTotalValidVotesAndEarnedVotesInAConstituency(){
		
		List result = nominationDAO.getVotesInfoForAConstituency("8722", "2005", IConstants.MUNCIPLE_ELECTION_TYPE);
		
		System.out.println(" Results Size :" + result.size());
		for(int i=0;i<result.size();i++){
		 Object[] params = (Object[])result.get(i);
		 System.out.println(" Total Candidate Votes Earned :" + (Double)params[0]);
		 System.out.println(" Total Constituency Valid Votes :" + (Double)params[1]);
		}
		
	}*/
	
	/*public void testfindByCandidateLastName(){
	//String[] names = {"reddy","jagan"};
	String sort = "model.candidate.candidateId";
	List<Object[]> obj = nominationDAO.findByFirstMiddleAndLastNames("reddy",sort,"asc",0,20,"3,4");
	int i;
	for(i=0;i<obj.size();i++)
	{
		
	Object[] x = (Object[])obj.get(i);
	
	for(Object y : x)
	{
    	System.out.print("  "+y.toString());
    }
	System.out.println();
	}
	System.out.println(obj.size());
    i=0;
	for(Candidate result:obj)
	{
		System.out.println("============="+ ++i +" "+result.getLastname());
		//System.out.print("   "+new ArrayList<Nomination>(result.getNominations()).get(0).getParty().getShortName());
	}
	
	}*/
	/*public void testTotalSearchCount(){
		List<Long> obj = nominationDAO.totalSearchCount("kamalakara","2,4");
		System.out.println(obj.get(0));
	}
		*/
	/*	public void testFindElectionResultsForAllCostituenciesByElectionTypeYearAndCountryId(){
			long start = System.currentTimeMillis();
			Map<Long, List<Object[]>> constituenciesInfoById = new HashMap<Long, List<Object[]>>();
			Long constituencyId = 0l;
			String constituencyName = "";
			Long partyId = null;
			Long rank = null;
			List<Object[]> constituencyResult = null;
			List<Long> alliancePartiesIds = new ArrayList<Long>();
			List list = nominationDAO.findElectionResultsForAllCostituenciesByElectionTypeYearAndDistrictId(2l, "2004", 23l);
			for(Object[] values:(List<Object[]>)list){
				constituencyId = (Long)values[0];
				constituencyResult = constituenciesInfoById.get(constituencyId);
				if(constituencyResult == null)
					constituencyResult = new ArrayList<Object[]>();
					constituencyResult.add(values);
				constituenciesInfoById.put(constituencyId, constituencyResult);
			}
			System.out.println(list.size());
			long end = System.currentTimeMillis();
			System.out.println((end-start)/1000);
		}
		*/
		/*public void testFindElectionResultsForAllCostituenciesByElectionTypeYearAndSateId(){
			
		}
	
		public void testFindElectionResultsForAllCostituenciesByElectionTypeYearAndDistrictId(){
			
		}*/
	
/*	public void testGetData(){
		//String electionYear,String electionType,Long constituencyId,Long candidateId
		List list = nominationDAO.getList("2009",IConstants.ASSEMBLY_ELECTION_TYPE,232l,15404l);
		System.out.println(list.get(0).toString()); 
    }

*/
	
/*	public void testData1(){
		List list = nominationDAO.getCountOfAllCandidates("2005",0l,1l,IConstants.CORPORATION_ELECTION_TYPE,1l,IConstants.STATE_LEVEL,IConstants.WINNER_CANDIDATES,"Candidates",0l);
		System.out.println(list.get(0).toString()); 
	}*/
	
/*	public void testData2(){
//String electionYear,Long locationId,Long stateId,String electionType,Long rank,String locationType,String candidateType,Long partyId
		List list = nominationDAO.getCountOfAllLocalBodyCandidates("2005",0l,1l,IConstants.MUNCIPLE_ELECTION_TYPE,1l,IConstants.STATE_LEVEL,IConstants.WINNER_CANDIDATES,"Candidates",0l);
		System.out.println(list);
		List list2 = nominationDAO.getCountOfAllLocalBodyCandidates("2005",0l,1l,IConstants.MUNCIPLE_ELECTION_TYPE,1l,IConstants.STATE_LEVEL,IConstants.WINNER_CANDIDATES,null,0l);
		System.out.println(list2);
	}*/
	
	/*public void testFindAllCandidatesForAnElectionBytheElectionYearByRankForLocalBody(){
		List list = nominationDAO.findAllCandidatesForAnElectionBytheElectionYearByRankForLocalBody("2005", 1l, IConstants.MUNCIPLE_ELECTION_TYPE, 2l);
		System.out.println(list.size());
	}*/
	/*
	public void testGetAllCandidateDetails(){
		
		 * (String electionYear,Long locationId,Long stateId,String electionType,
		 * Long rank,String locationType,String candidateType,Long partyId,
			int startIndex, int maxResult, String order, String columnName)
		 
		List list = nominationDAO.getAllCandidateDetails("2005", 0l,1l, IConstants.MUNCIPLE_ELECTION_TYPE,1l,IConstants.STATE_LEVEL,IConstants.WINNER_CANDIDATES,0l,0,20,"asc","model.candidate.lastname");
		System.out.println(list.size());
	}
	
	public void testFindElectionResultsForAllPartiesInAssemblyConstituenciesByCriteria(){
		List<Long> partyIds = new ArrayList<Long>(0);
		List<Long> districtIds = new ArrayList<Long>(0);
		List<Long> constiIds = new ArrayList<Long>(0);
		districtIds.add(19l);
		partyIds.add(361l);
		constiIds.add(20l);
		String query = " and model.constituencyElection.constituency.district.districtId in (:districtIds)  "+ 
				" and model.party.partyId in (:partyIds)";
		List list = nominationDAO.findElectionResultsForAllPartiesInAssemblyConstituenciesByCriteria("2009", constiIds, 
				partyIds, districtIds, query);
		System.out.println(list.size());
	}
	

	public void testGetData(){
		List<String> electionYears =  new ArrayList<String>();
		electionYears.add("2009");
		List list = nominationDAO.getAllPartiesStrengths(IConstants.ASSEMBLY_ELECTION_TYPE,1l,electionYears,IConstants.ELECTION_SUBTYPE_MAIN,IConstants.ALL_PARTIES,0l);
		for(int i=0; i<list.size(); i++){
			Object[] parms = (Object[])list.get(i);
			System.out.println(parms[0]+"\t"+parms[1]+"\t"+parms[2]);
		}
	}
	
	public void testGetData(){
		
		List<Long> constIds = new ArrayList<Long>(0);
		constIds.add(261l);
		constIds.add(284l);
		constIds.add(23l);
		constIds.add(2l);
		
		
		List list = nominationDAO.getAllAllianceCandidateDetailsForAConstituency(constIds,885l,2l);
		for(int i=0; i<list.size(); i++){
			Object[] parms = (Object[])list.get(i);
			System.out.println(parms[0]+"\t"+parms[1]+"\t"+parms[2]);
		}
		System.out.println(list);
	}*/
	
	/*@Test
	public void testGetParliamentsInDistrict(){
		
		List result = nominationDAO.getParliamentCandidateNPartyInfoInElection(501L, IConstants.PARLIAMENT_ELECTION_TYPE, 1L,"2009");
		
		if(result != null)
			System.out.println(" Result :" + result.size());
	}*/
	
	/*@SuppressWarnings("unchecked")
	public void testGetCandidateNPartyInfo()
	{
		List<Object[]> result = nominationDAO.getCandidateNPartyInfo("242,243,251,245,244,250,249", IConstants.ASSEMBLY_ELECTION_TYPE, 1l,"MAIN",1l);
		
		if(result != null && result.size() > 0)
			for(Object[] param : result)
			{   
				System.out.println();
				for(Object obj : param)
				{
					System.out.print(obj+" --- ");
				}
			}
	}*/
	
	/*public void testGetCandidateAndPartyInfo()
	{
		List<Long> list = new ArrayList<Long>(0);
		list.add(242l);list.add(243l);list.add(251l);list.add(245l);list.add(244l);list.add(250l);list.add(249l);
		for(Long constituencyId : list)
		{
			List<Object[]> result = nominationDAO.getCandidateAndPartyInfo(constituencyId, IConstants.ASSEMBLY_ELECTION_TYPE, 1l);
			
			System.out.println();
			for(Object obj : result.get(0))
			{
				System.out.print(obj+" --- ");
			}
				
		}
	}*/
	
	/*@Test
	public void testGetAllElectionYearsForAConstituency()
	{
		List<Object> list = nominationDAO.getAllElectionYearsForAConstituency(251l, IConstants.ASSEMBLY_ELECTION_TYPE);
		
		for(Object obj : list)
			System.out.println(obj);
	}*/
	
	/*@SuppressWarnings("unchecked")
	public void testGetCandidateNPartyInfoForParliament()
	{
		List<Object[]> list = nominationDAO.getCandidateNPartyInfoForParliament("478", IConstants.PARLIAMENT_ELECTION_TYPE, 1l);
		
		for(Object param : list.get(0))
			System.out.println(param);
		
	}*/
	
	/*@SuppressWarnings("unchecked")
	public void testGetParliamentCandidateNPartyInfo()
	{
		List<Object[]> list = nominationDAO.getParliamentCandidateNPartyInfo(478l, IConstants.PARLIAMENT_ELECTION_TYPE,1l);
		
		if(list != null && list.size() > 0)
		for(Object param : list.get(0))
			System.out.println(param);
	}*/
	
	/*@SuppressWarnings("unchecked")
	public void testGetCandidateNPartyInfoForParliament()
	{
		List<Object[]> list = nominationDAO.getCandidateAndPartyInfoForParliament(501l,IConstants.PARLIAMENT_ELECTION_TYPE,1l);
		System.out.println(list.size());
		if(list != null && list.size() > 0)
			for(Object obj : list.get(0))
				System.out.println(obj);
	}*/
	
	/*public void testFindConstituencyElectionByElectionIdAndDistrictIdAndPartys()
	{
		List<Long> ids = new ArrayList<Long>();
		ids.add(53l);ids.add(265l);
		List<ConstituencyElection> list = nominationDAO.findConstituencyElectionByElectionIdAndDistrictIdAndPartys(107l, 90l,ids);
		
		if(list != null && list.size() > 0)
			for(ConstituencyElection election : list)
				System.out.println(election.getConstituency().getConstituencyId());
	}*/
	
/*	public void testGetCandidatesToMapWithUser()
	{
		List<Long> ids = new ArrayList<Long>();
		ids.add(53l);ids.add(265l);
		List<Object[]> list = nominationDAO.getCandidatesToMapWithUser("M","Ashok",0L,1L,1L);
		System.out.println(list.size());
			for(Object[] result : list)
				System.out.println("id :"+result[0]+"Name :"+result[1]);
	}*/
	
	/*public void testSelectData()
	{
		List<Object[]> list = nominationDAO.selectData(38l);
		
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println();
			for(Object obj:params)
				System.out.print("\t"+obj);
		}
	}*/
	
	/*public void testGetGenderWiseElectionResultOfParties()
	{
		List<Long> partiesList = new ArrayList<Long>(0);
		partiesList.add(362l);
		partiesList.add(872l);
		List<Object[]> list = nominationDAO.getGenderWiseElectionResultOfParties(38l);
		
		System.out.println(list.size());
		
		for(Object[] params:list)
		{
			System.out.println();
			for(Object obj : params)
				System.out.print("\t"+obj);
		}
	}*/
	
	/*public void testGetConstituencyAreaTypeWiseElectionResultOfParties()
	{
		List<Object[]> list = nominationDAO.getConstituencyAreaTypeWiseElectionResultOfParties(38l);
		
		System.out.println(list.size());
		
		for(Object[] params:list)
		{
			System.out.println();
			for(Object obj : params)
				System.out.print("\t"+obj);
		}
	}*/
	
	/*public void testGetCandidates()
	{
		List<Object[]> list = nominationDAO.getCandidates();
		
		
		for(Object[] params : list)
		{
			System.out.println();
			for(Object obj : params)
				System.out.print("\t"+obj);
		}
		
		System.out.println(list.size());
	}*/
	
	/*public void testGetConstituencyAreaTypePercentageWiseElectionResultOfParties()
	{
		List<Long> partiesList = new ArrayList<Long>(0);
		partiesList.add(362l);
		partiesList.add(872l);
		List<Object[]> list = nominationDAO.getConstituencyAreaTypePercentageWiseElectionResultOfParties(38l,"2001",partiesList);
		
		for(Object[] params : list)
		{
			System.out.println();
			for(Object obj : params)
				System.out.print("\t"+obj);
		}
		
		System.out.println(list.size());
	}*/
	
	/*public void test()
	{
		Double d = new Double(43086.0);
		System.out.println(d.longValue());
	}*/
	/*public void testgetCandidatesBasedOnElectionId(){
		
		List<Object[]> candidates = nominationDAO.getCandidatesBasedOnElectionId("har",0l,38l);
		
		System.out.println(candidates.size());
		
		for(Object[] params : candidates){
			System.out.println(new Long(params[0].toString()));
			System.out.println(params[1].toString());
			
		}
	}*/
    /* public void testGetCandidateDetails(){
		
		List<Object[]> candidates = nominationDAO.getCandidateDetails("Parliament",461l,1l,"2009");
		
		System.out.println(candidates.size());
		
		for(Object[] params : candidates)
		{
			System.out.println();
			for(Object obj : params)
				System.out.print("\t"+obj);
		}
	}*/
	
	/*public void test()
	{
		List<Object[]> list = nominationDAO.getPartiwiseParticipatedCountInAElection(38l);
		
		System.out.println(list);
		
		for(Object[] params : list)
		{
			System.out.println();
			for(Object obj : params)
				System.out.print("\t"+obj);
		}
	}*/
	
	
	/*public void testGetPartywiseWonCount()
	{
		List<Long> cList = new ArrayList<Long>(0);
		cList.add(1l);
		cList.add(2l);
		List<Object[]> list = nominationDAO.getPartywiseWonCount(38l,cList);
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println();
			for(Object obj : params)
				System.out.print("\t"+obj);
		}
	}*/
	
	/*public void testGetPartiesWonInfo()
	{
		List<Object[]> list = nominationDAO.getPartiesWonInfo(38l);
		
		System.out.println(list.size());
		for(Object[] params : list)
		{
			System.out.println();
			for(Object obj : params)
				System.out.print("\t"+obj);
		}
	}*/
	
	/*public void testGetpartiesPCinfo()
	{
		List<Object[]> list = nominationDAO.getpartiesPCinfo(38l);
		
		System.out.println(list.size());
		for(Object[] params : list)
		{
			System.out.println();
			for(Object obj : params)
				System.out.print("\t"+obj);
		}
	}*/
	
	/*public void testGetPartyWinConstituenciesInAElection()
	{
		List<Long>constituenciesList = new ArrayList<Long>(0);
		constituenciesList.add(1l);
		constituenciesList.add(2l);
		constituenciesList.add(3l);
		constituenciesList.add(4l);
		constituenciesList.add(5l);
		
		List<Object[]> list = nominationDAO.getPartyRankConstituenciesInAElection(362l,38l, constituenciesList);
		
		System.out.println(list.size());
		for(Object[] params : list)
		{
			System.out.println();
			for(Object obj : params)
				System.out.print("\t"+obj);
		}
	}*/
	
	/*public void testGetPartyLostConstituencies()
	{
		List<Long>constituenciesList = new ArrayList<Long>(0);
		constituenciesList.add(1l);
		constituenciesList.add(2l);
		constituenciesList.add(3l);
		constituenciesList.add(4l);
		constituenciesList.add(5l);
		
		List<Object[]> list = nominationDAO.getPartyLostConstituencies(362l,38l, constituenciesList);
		
		System.out.println(list.size());
		for(Object[] params : list)
		{
			System.out.println();
			for(Object obj : params)
				System.out.print("\t"+obj);
		}
	}*/
	
	/*public void testGetConstituencyWiseCandidatesStates()
	{
		List<Object[]> list = nominationDAO.getConstituencyWiseCandidatesStates(38l);
		
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println();
			for(Object obj : params)
				System.out.print("\t"+obj);
		}
	}*/
	/*public void testGetAllDistrictsForAnElection()
	{
		List<Object[]> list = nominationDAO.getAllDistrictsForAnElection(3l);
		
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println();
			for(Object obj : params)
				System.out.print("\t"+obj);
		}
	}*/
	
	/*public void testGetAllConstituenciesInADistrict()
	{
		List<Long> list = nominationDAO.getAllConstituenciesInADistrict(14l,38l);
		
		System.out.println(list.size());
		
		
			for(Long obj : list)
				System.out.print("\t"+obj);
		
	}*/
	/*public void testGetAllWonCountPartyWise()
	{
		List<Object> list = nominationDAO.getAllWonCountPartyWise(14l,3l,362l);
		
		System.out.println((Long)(list.get(0)));
		
	}*/
	/*public void testGetAllPartiesWithAtleastOneWon()
	{
		List<Object[]> list = nominationDAO.getAllPartiesWithAtleastOneWon(14l);
		
		for(Object[] party :list)
		{
		 System.out.println("id : "+party[0].toString()+"  Name : "+party[1].toString());
		}
		
	}*/
	/*public void testGetAllPartiesForPartialElec()
	{
		List<Object[]> list = nominationDAO.getAllPartiesForPartialElec(182l);
		
		for(Object[] party :list)
		{
		 System.out.println("id : "+party[0].toString()+"  Name : "+party[1].toString());
		}
		
	}*/
	
	/*public void testGetPartiesWithAtleatOneWinningSeatForAElection()
	{
		List<Object[]> list = nominationDAO.getPartiesWithAtleatOneWinningSeatForAElection(38l);
		
		System.out.println(list.size());
		
		for(Object[] params : list)
			System.out.println(params[0] +"---"+params[1]);
	}*/
	
	/*public void testgetCandidatePertcipatedConstituenciesInAElection()
	{
		List<Object[]> list = nominationDAO.getCandidatePertcipatedConstituenciesInAElection(1984l,38l);
		
		System.out.println(list.size());
		
		for(Object[] params : list)
			System.out.println(params[0] +"---"+params[1]);
	}*/
	/*public void testGetStateDetails()
	{
		List<Object[]> list = nominationDAO.getStateDetails(872l);
		
		System.out.println(list.size());
		
		for(Object[] params : list)
			System.out.println(params[0] +"---"+params[1]);
	}*/
	/*public void testGetElecYears()
	{
		List<Object[]> list = nominationDAO.getElecYears(872l,1l,1l);
		
		System.out.println(list.size());
		
		for(Object[] params : list)
			System.out.println(params[0] +"---"+params[1]);
	}*/
	/*public void testGetCandidateDetailsForAParty()
	{
		List<Object[]> list = nominationDAO.getCandidateDetailsForAParty(872l,17l);
		
		System.out.println(list.size());
		
		for(Object[] params : list)
			System.out.println(params[0] +"---"+params[1]);
	}*/
	
	/*public void test()
	{
		Date date = new Date();
		System.out.println(date.getTime());
	}*/
	
	/*public void testGetConstituencyElectionIdsOfMarginVotesNotExisted()
	{
		List<Long> list = nominationDAO.getConstituencyElectionIdsOfMarginVotesNotExisted(17l);
		System.out.println(list.size());
		
		if(list != null && list.size() > 0)
			for(Long conId : list)
				System.out.println(conId);
	}*/
	
	/*public void testGetCandidatesResultsInAConstituencyElection()
	{
		List<CandidateResult> list = nominationDAO.getCandidatesResultsInAConstituencyElection(324l);
		System.out.println(list.size());
		
		for(CandidateResult candidateResult : list)
		{
			System.out.println(candidateResult.getNomination().getCandidate().getLastname());
		}
	}*/
	
	/*public void testfindCandidateNamePartyByConstituencyAndElectionDate()
	{
	 List list=nominationDAO.getCandidatesInfoForTheGivenConstituency("232","2012", "Assembly") ;
	 System.out.println(list.size());
	 for(Object[] params : (List<Object[]>)list)
	 {
		System.out.println(params[13]); 
	 }
}*/
	/*public void testgetAllCandidatesInADistrict()
	{
	 List<Object[]> list = nominationDAO.getAllCandidatesInADistrict(202l, 152l) ;
	 System.out.println(list.size());
	 for(Object[] params : (List<Object[]>)list)
	 {
		System.out.println(params[1]); 
	 }
	}*/

	/*public void testFindSeatsWonByAPartyInMuncipalityForAnElectionYear()
	{
	 List<Object[]> list = nominationDAO.findSeatsWonByAPartyInMuncipalityForAnElectionYear("46","2007","MUNCIPALITY",1l) ;
	 System.out.println(list.size());
	 for(Object[] params : (List<Object[]>)list)
	 {
		System.out.println(params[1]); 
	 }
	}
	public void testGetLocalBodyWiseResultsOfAllPartiesInLocalElectionBodies()
	{
		List<Long> ids = new ArrayList<Long>();
		ids.add(31805l);
	 List<Object[]> list = nominationDAO.getLocalBodyWiseResultsOfAllPartiesInLocalElectionBodies(null,63l,ids) ;
	 System.out.println(list.size());
	 for(Object[] params : (List<Object[]>)list)
	 {
		System.out.println(params[1]+" "+params[2]); 
	 }
	}*/
	
	
	/*public void testGetMuncipalityCandidateDetails()
	{
		List<Long> ids = new ArrayList<Long>();
		ids.add(31805l);
	 List<Object[]> list = nominationDAO.getMuncipalityCandidateDetails("Greater Municipal Corp",5l) ;
	 System.out.println(list.size());
	 for(Object[] params : (List<Object[]>)list)
	 {
		System.out.println(params[1]+" "+params[2]); 
	 }
	}*/
	
	/*public void test()
	{
		List<Long> ids = new ArrayList<Long>();
		ids.add(72l);
		ids.add(514l);
		ids.add(662l);
		ids.add(872l);
		ids.add(886l);
		ids.add(1117l);
		ids.add(163l);
		ids.add(362l);
	 List<Object[]> list = nominationDAO.getParties(ids,"Assembly");
	 for(Object[] params : list)
	 {
		 System.out.println(params[1]);
	 }
	}*/

/*	public void testParties()
	{
		List<Long> list = new ArrayList<Long>();
		list.add(390l);list.add(391l);list.add(392l);list.add(455l);list.add(476l);list.add(482l);list.add(483l);list.add(1662l);list.add(1664l);list.add(163l);list.add(239l);list.add(265l);list.add(269l);list.add(362l);
		List<Object[]> values = nominationDAO.getPartiesList(12l, list, "Assembly");
		for (Object[] parms : values) {
			System.out.println(parms[0] +":"+ parms[1]);
		}
	}*/
	
/*	public void testParties()
	{
		List elections = nominationDAO.findByPartyId(1117l);
		System.out.println(elections.size());
		System.out.println(elections);
		for (int i = 0; i < elections.size(); i++) {
			Object[] parms = (Object[]) elections.get(i);
			System.out.println(parms[0].toString());
			System.out.println(parms[1].toString());
		}
		
		
	}
	*/
	/*public void testParties()
	{
		
		List<Object[]> values = nominationDAO.getConstituenciesByParties(1l,"2011",1117l);
		for (Object[] parms : values) {
			System.out.println(parms[0] +":"+ parms[0]);
			System.out.println(parms[0] +":"+ parms[1]);
		}
		
		
	}*/
	

			
	/*public void testParties()
	{
		List  values = nominationDAO.findByElectionScopeIdAndPartyIds(2l,1117l);
		System.out.println(values);
		for (Object objects : values) {
		Object[] a = (Object[])objects;
		System.out.println(a[0]);
		System.out.println(a[1]);
		}
			
	}*/
	
	/*public void testgetElectionyearsByElection()
	{
		List<Long> eleIds = new ArrayList<Long>();
		eleIds.add(3l);
		eleIds.add(38l);
		List<Object[]> values = nominationDAO.getElectionyearsByElection(eleIds,872l);
		for (Object[] parms : values) {
			System.out.println(parms[0] +":"+ parms[1]);
		}
	}*/
	
	/*public void testgetCandidatesToSubScribe(){
		List<Object[]> values = nominationDAO.getCandidatesToSubScribe(1l,"jagan","assembly",1,50);
		int count = 0;
		for (Object[] parms : values) {
			//System.out.println(parms[0] +":"+ parms[1]);
			count++;
		}
		System.out.println(count);
	}*/
	
	/*public void testgetConstituencyResult(){
		List<Object[]> values = nominationDAO.getConstituencyResult(232l);
		System.out.println(values.size());
		for (Object[] parms : values) {
			System.out.println(parms[0] +":"+ parms[1]);
		}
	}*/
	
/*	public void testgetConstituencyResultForParty(){
		List<Object[]> values = nominationDAO.getConstituencyResultForParty(232l);
		for (Object[] parms : values) {
			System.out.println(parms[1] +":"+ parms[2]);
		}
	}*/
	
	/*public void testgetAllBoothsByConstituency(){
		List<Object[]> values = nominationDAO.getAllBoothsByConstituency(232l);
		System.out.println(values.size());
		for (Object[] param : values) {
			System.out.println(param[0]+":"+param[1] );
		}
	}*/
	/*public void testfindBoothResultsForBoothsAndElectionAndAllParties(){
		List<Long> eleIds = new ArrayList<Long>();

		int boothIds[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,128,129,130,131,132,133,134,135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,150,151,152,153,154,155,156,157,158,159,160,161,162,163,164,165,166,167,168,169,170,171,172,173,174,175,176,177,178,179,180,181,182,183,184,185,186,187,188,189,190,191,192,193,194,195,196,197,198,199,200,201,202,203,204,205,206,207,208,209,210,211,212,213,214,215,216,217,218,219,220,221,222,223,224,225,226,227,228,229,230,231,232,233,234,235,236,237,238,239,240,241,242,243};
		for(int i =0;i<boothIds.length;i++){
			eleIds.add((long)boothIds[i]);
		}
		
		List<Object[]> values = nominationDAO.findBoothResultsForBoothsAndElectionAndAllParties(eleIds);
				for (Object[] parms : values) {
					System.out.println(parms[1] +":"+ parms[2]+":"+ parms[3]);
				}
	}*/
	/*public void testgetElectionResultsCount(){
		List<Long> eleIds = new ArrayList<Long>();
		int boothIds[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,128,129,130,131,132,133,134,135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,150,151,152,153,154,155,156,157,158,159,160,161,162,163,164,165,166,167,168,169,170,171,172,173,174,175,176,177,178,179,180,181,182,183,184,185,186,187,188,189,190,191,192,193,194,195,196,197,198,199,200,201,202,203,204,205,206,207,208,209,210,211,212,213,214,215,216,217,218,219,220,221,222,223,224,225,226,227,228,229,230,231,232,233,234,235,236,237,238,239,240,241,242,243};
		for(int i =0;i<boothIds.length;i++){
			eleIds.add((long)boothIds[i]);
		}
		
		List<Object[]> values = nominationDAO.getElectionResultsCount(eleIds);
				for (Object[] parms : values) {
					System.out.println(parms[0] +":"+parms[1] +":"+ parms[2]);
				}
	}*/
	
	/*public void testfindAllEleHappendInAConstituency(){
	List<Long> values = nominationDAO.findAllEleHappendInAConstituency(232l);
	System.out.println(values);
	}*/
	
/*	public void testfindAllMptcAndZptcElectionsInfoByelectionId(){
		List<Long> eleIds = new ArrayList<Long>();
		int boothIds[] = {72, 163, 265, 269, 362, 366, 662, 872, 886, 1117};
		for(int i =0;i<boothIds.length;i++){
			eleIds.add((long)boothIds[i]);
		}
		List<Long> tehsilIds = new ArrayList<Long>();
		int boothId[] = {72, 163, 265, 269, 362, 366, 662, 872, 886, 1117};
		for(int i =0;i<boothId.length;i++){
			tehsilIds.add((long)boothId[i]);
		}
		List<Object[]> values = nominationDAO.findAllMptcAndZptcElectionsInfoByelectionId(37l,tehsilIds,eleIds);
		for (Object[] parms : values) {
			System.out.println(parms[0] +":"+parms[1] +":"+ parms[2]);
		}
	}*/
	
	/*public void test(){
		List<Long> electionIds=new ArrayList<Long>();
		electionIds.add(40l);
		electionIds.add(42l);
		electionIds.add(201l);
		
		//electionIds.add(63l);
		
		//List<Object[]> li=nominationDAO.findAllZptcOrMptcResultsInaConstituency(323l,electionTypeIds,"2009");
		//List<Object[]> li1=nominationDAO.findAllZptcOrMptcResultsInaConstituencyPartyWise(323l, electionTypeIds, "2009");
		
		List<Object[]> li=nominationDAO.findMuncipalOrCorpResultsInaConstituency(31l,electionIds);
		List<Object[]> li1=nominationDAO.findMuncipalOrCorpResultsInaConstituencyPartyWise(31l, electionIds);
		
		
		List<Object[]> li=nominationDAO.findMuncipalOrCorpResultsOfGMCInaConstituency(315l, electionIds);
		List<Object[]> li1=nominationDAO.findMuncipalOrCorpResultsOfGMCInaConstituencyPartyWise(315l, electionIds);
		
		
		List<PartyResultsVO> electionList=new ArrayList<PartyResultsVO>();
		Map<Long,List<PartyResultsVO>> eleMap=new HashMap<Long, List<PartyResultsVO>>();
		
		for(Object[] ob:li){
			List<PartyResultsVO> wardsList=eleMap.get(Long.valueOf(ob[7].toString()));
			if(wardsList==null){
				wardsList=new ArrayList<PartyResultsVO>();
			}
			
			eleMap.put(Long.valueOf(ob[7].toString()), wardsList);
		}
		for(Object[] ob:li){
			List<PartyResultsVO> wardsList=eleMap.get(Long.valueOf(ob[7].toString()));
			
			PartyResultsVO pvo=new PartyResultsVO();
			pvo.setElectionId(Long.valueOf(ob[7].toString()));
			pvo.setValidVotes(ob[6]!=null?((Double)ob[6]).longValue():0l);
			pvo.setVotesPolled(ob[5]!=null?((Double)ob[5]).longValue():0l);
			pvo.setYear(Long.valueOf(ob[0].toString()));
			pvo.setElectionName(ob[1].toString());
			pvo.setLocation(ob[3].toString());
			pvo.setLocationId(Long.valueOf(ob[2].toString()));
			wardsList.add(pvo);
		}
		
		List<Long> partyIds=new ArrayList<Long>();
		partyIds.add(872l);
		partyIds.add(362l);
		partyIds.add(886l);
		partyIds.add(163l);
		
		
		for(Object[] ob:li1){
			
			List<PartyResultsVO> wardsList=eleMap.get(Long.valueOf(ob[5].toString()));
			
			PartyResultsVO pvo_temp=getMatchedWardVO(wardsList,Long.valueOf(ob[5].toString()),Long.valueOf(ob[6].toString()));
			
			List<PartyResultsVO> partyResults=pvo_temp.getPartyResultsVOList();
			if(partyResults==null){
				partyResults=new ArrayList<PartyResultsVO>();
			}
			PartyResultsVO pv=getMatchedPartyVO(partyResults,Long.valueOf(ob[0].toString()));
			if(pv==null){
				pv=new PartyResultsVO();
			}
			pv.setPartyId(Long.valueOf(ob[0].toString()));
			pv.setPartyName(ob[1].toString());
			pv.setVotesEarned(((Double)(ob[2])).longValue());
			pv.setDiffPercent(calcPercentage(pvo_temp.getVotesPolled(), pv.getVotesEarned()));
			pv.setRank(Long.valueOf(ob[8].toString()));
			pv.setNominationId(Long.valueOf(ob[9].toString()));
			partyResults.add(pv);
			
			
			if(!partyIds.contains(pv.getPartyId())){
				Long existVotes=pvo_temp.getOtherVotes()!=null?pvo_temp.getOtherVotes():0l;
				pvo_temp.setOtherVotes(existVotes+pv.getVotesEarned());
				pvo_temp.setOtherVotesPercent(calcPercentage(pvo_temp.getVotesPolled(), pvo_temp.getOtherVotes()));
			}
			pvo_temp.setPartyResultsVOList(partyResults);
			
		}
		

		PartyResultsVO prvo=new PartyResultsVO();
		Map<Long,PartyResultsVO> party_res=new HashMap<Long, PartyResultsVO>();
		
		for (Entry<Long, List<PartyResultsVO>> entry : eleMap.entrySet())
		{
		List<PartyResultsVO> electionList1=entry.getValue();		
		for(PartyResultsVO pvo:electionList1){
			List<PartyResultsVO> partyResults=pvo.getPartyResultsVOList();
			Collections.sort(partyResults);
			Long marginVotes=0l;
			for(int i=0;i<partyResults.size();i++){
				//PARTICIPATED AND RANK COUNT START
				PartyResultsVO parr=party_res.get(partyResults.get(i).getPartyId().longValue());
				if(parr==null){
					parr=new PartyResultsVO();
					parr.setWon(0l);
					parr.setParticipated(0l);
					parr.setVotesEarned(0l);
					parr.setPercentage(calcPercentage(pvo.getValidVotes(), parr.getVotesEarned()));
					//party_res.put(partyResults.get(i).getPartyId().longValue(), parr);
				}
				
				if(partyResults.get(i).getRank()!=null){
					if(partyResults.get(i).getRank()==1){
						Long exist=parr.getWon();
						parr.setWon(exist+1l);
					}
				}
					Long existPrtcptd=parr.getParticipated();
					parr.setParticipated(existPrtcptd+1l);
					parr.setPartyId(partyResults.get(i).getPartyId().longValue());
					
					
					Long votesErnd_exist=parr.getVotesEarned();
					parr.setVotesEarned(partyResults.get(i).getVotesEarned()+votesErnd_exist);
					parr.setPercentage(calcPercentage(pvo.getValidVotes(),partyResults.get(i).getVotesEarned()));
					
					
					party_res.put(partyResults.get(i).getPartyId().longValue(), parr);
					
					
					if(!partyIds.contains(partyResults.get(i).getPartyId().longValue())){
						if(prvo.getParticipated()==null){
							prvo.setParticipated(0l);
							prvo.setWon(0l);
							prvo.setOtherVotes(0l);
							prvo.setOtherVotesPercent(calcPercentage(pvo.getValidVotes(),partyResults.get(i).getVotesEarned()));
						}
						Long exPr=parr.getParticipated();
						prvo.setParticipated(exPr+1l);
						
						if(partyResults.get(i).getRank()==1){
							Long exWn=parr.getWon();
							prvo.setWon(exWn+1l);
						}
						
						Long votesErnd=prvo.getOtherVotes();
						prvo.setOtherVotes(partyResults.get(i).getVotesEarned()+votesErnd);
						prvo.setOtherVotesPercent(calcPercentage(pvo.getValidVotes(),partyResults.get(i).getVotesEarned()));
					
					}
				//END
					
				
				if(partyResults.get(i).getPartyId().longValue()==872l){
					if(i==0){
						if(!partyIds.contains(partyResults.get(1).getPartyId())){
							marginVotes=partyResults.get(0).getVotesEarned()-pvo.getOtherVotes();
						}else{
							marginVotes=partyResults.get(0).getVotesEarned()-partyResults.get(1).getVotesEarned();
							partyResults.get(0).setRank(1l);
							pvo.setMarginVotes(marginVotes);
						}
					}else{
						if(!partyIds.contains(partyResults.get(0).getPartyId())){
							marginVotes=partyResults.get(i).getPartyId().longValue()-pvo.getOtherVotes();
						}else{
							marginVotes=partyResults.get(i).getVotesEarned()-partyResults.get(0).getVotesEarned();
							partyResults.get(0).setRank(1l);
							pvo.setMarginVotes(marginVotes);
						}
					}
				}
			}
		}
		prvo.setElectionId(entry.getKey());
		
		List<PartyResultsVO> partyStrengths=new ArrayList<PartyResultsVO>(party_res.values());
			if(prvo.getPartyResultsVOList()==null){
				prvo.setPartyResultsVOList(electionList1);
				prvo.setPartyStrengths(partyStrengths);
			}else{
				List<PartyResultsVO> eleList=prvo.getPartyResultsVOList();
				eleList.addAll(electionList1);
				prvo.setPartyResultsVOList(eleList);
			}
		}
		
		System.out.println(electionList.size());
		 
	}
	
	public PartyResultsVO getMatchedVO(List<PartyResultsVO> pvoList,Long electionId){
		for(PartyResultsVO pv:pvoList){
			if(pv.getElectionId().longValue()==electionId.longValue()){
				return pv;
			}
		}
		return null;
	}
	public PartyResultsVO getMatchedWardVO(List<PartyResultsVO> pvoList,Long electionId,Long wardId){
		for(PartyResultsVO pv:pvoList){
			if(pv.getElectionId().longValue()==electionId.longValue() && pv.getLocationId().longValue()==wardId.longValue()){
				return pv;
			}
		}
		return null;
	}
	public PartyResultsVO getMatchedPartyVO(List<PartyResultsVO> pvoList,Long partyId){
		for(PartyResultsVO pv:pvoList){
			if(pv.getPartyId().longValue()==partyId.longValue()){
				return pv;
			}
		}
		return null;
	}
	
	public String calcPercentage(Long total,Long count){
		if(total>0){
			return count != 0 ? roundTo2DigitsFloatValue((float) count * 100f / total): "0.00";
		}else{
			return "0.00";
		}
	}
	
	public String roundTo2DigitsFloatValue(Float number){
		  
		log.debug("Entered into the roundTo2DigitsFloatValue service method");
		  
		  String result = "";
		  try
		  {
			  
			
			NumberFormat f = NumberFormat.getInstance(Locale.ENGLISH);  
			f.setMaximumFractionDigits(2);  
			f.setMinimumFractionDigits(2);
			
			result =  f.format(number);
		  }catch(Exception e)
		  {
			  log.error("Exception raised in roundTo2DigitsFloatValue service method");
			  e.printStackTrace();
		  }
		  return result;
	  }*/
	/*
	public void testGetAllDtails(){
		List<Long> listIds = new ArrayList<Long>();
		listIds.add(461L);
		listIds.add(469L);
		listIds.add(471L);
		listIds.add(477L);
		listIds.add(480L);
		listIds.add(481L);
		listIds.add(484L);
		listIds.add(485L);
		listIds.add(486L);
		listIds.add(487L);
		listIds.add(489L);
		listIds.add(490L);
		listIds.add(496L);
		listIds.add(499L);
		listIds.add(502L);
		listIds.add(510L);
		listIds.add(511L);
		
		List<Object[]> list = nominationDAO.getWonAndLeadCountPartyWise(260L,listIds,4L);
		System.out.println(list);
	public void test(){
		List<Long> constiIds = new ArrayList<Long>();
		//constiIds.add(228l);
		constiIds.add(156l);
		
		List<Long> partyIds = new ArrayList<Long>();
		partyIds.add(362l);
		partyIds.add(872l);
		partyIds.add(366l);
		
		
		//List<Object[]> list = nominationDAO.getPartysInfoForAParticularElectionYearInConsitutencies(38l,constiIds);
		List<Object[]> list = nominationDAO.partysVotesShareInConstituenciesOfElection(38l,constiIds,partyIds);
		System.out.println(list.size());
	}
	*/
	
	public void testDetails()
	{
		List<Long> staticPartyList = new ArrayList<Long>();
		staticPartyList.add(362L);  // INC
		staticPartyList.add(163L);  // BJP
		
		
		staticPartyList.add(872L);  // TDP
		staticPartyList.add(1117L); // YSRC 
		staticPartyList.add(514L);  // LSP
		//staticPartyList.add(239L);  // BSP
		staticPartyList.add(662L);  // PRP			
		//staticPartyList.add(1712L); // JSP
		staticPartyList.add(265L);  // CPI
		staticPartyList.add(269L);  // CPM	
	
		staticPartyList.add(886L);  // TRS
		staticPartyList.add(72L);   // AIMIM

		List<Long> constituencyIds = new ArrayList<Long>();
		constituencyIds.add(243L);
		constituencyIds.add(282L);
		@SuppressWarnings("unchecked")
		List<Object[]> list = nominationDAO.findPartiesByConstituencListAndElection(constituencyIds,"2014");
		
		System.out.println(list.size());
		
	}
}	
	
