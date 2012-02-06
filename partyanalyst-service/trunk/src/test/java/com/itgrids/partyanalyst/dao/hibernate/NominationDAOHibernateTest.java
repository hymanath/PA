package com.itgrids.partyanalyst.dao.hibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyRebelCandidateDAO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResults;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.dto.PartiesDetailsVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.ConstituencyElection;
import com.itgrids.partyanalyst.service.IDelimitationConstituencyMandalService;
import com.itgrids.partyanalyst.utils.IConstants;

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
	/*
	public void testFindByConstituencyPartyAndElectionYear(){
		
		List list = nominationDAO.findCandidateNamePartyByConstituencyAndElection("3382", "2009");		
		assertEquals(1, list.size());
	}
	
	public void testFindByElectionTypeTehsilAndParty(){
		List list = nominationDAO.findMPTCInfoByElectionTypeTehsilAndParty("MPTC", new Long(844), new Long(24));
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
	
	
	
	public void testConstituencyCandidatesInfo(){
		List result = nominationDAO.getCandidatesInfoForTheGivenConstituency(3358l,"2004",IConstants.ASSEMBLY_ELECTION_TYPE);
		Assert.assertEquals(1, result.size());	
	}	
	
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
	

	public void testFindAllMptcAndZptcElectionsInfoInMandal(){
		List list = nominationDAO.findAllMptcAndZptcElectionsInfoInMandal(844l);
		for(int i=0; i<list.size(); i++){
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]+"\t"+((Object[])list.get(i))[4]+"\t"+((Object[])list.get(i))[5]+"\t"+((Object[])list.get(i))[6]+"\t"+((Object[])list.get(i))[7]+"\t"+((Object[])list.get(i))[8]+"\t"+((Object[])list.get(i))[9]+"\t"+((Object[])list.get(i))[10]+"\t"+((Object[])list.get(i))[11]+"\t"+((Object[])list.get(i))[12]+"\t"+((Object[])list.get(i))[13]);
		}
	}*/
	
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
	
	/*public void testGetCandidatesToMapWithUser()
	{
		List<Long> ids = new ArrayList<Long>();
		ids.add(53l);ids.add(265l);
		List<Object[]> list = nominationDAO.getCandidatesToMapWithUser("M","KALIKI YANADI REDDY",0L,1L,1L);
		
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
	
	public void test()
	{
		Double d = new Double(43086.0);
		System.out.println(d.longValue());
	}
}
	
