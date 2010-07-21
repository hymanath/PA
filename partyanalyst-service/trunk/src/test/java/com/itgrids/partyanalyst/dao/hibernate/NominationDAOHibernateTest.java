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
import org.junit.Assert;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyRebelCandidateDAO;
import com.itgrids.partyanalyst.dto.CandidateDetailsVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.dto.DelimitationConstituencyMandalResultVO;
import com.itgrids.partyanalyst.dto.MandalAllElectionDetailsVO;
import com.itgrids.partyanalyst.dto.MandalInfoVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.TeshilPartyInfoVO;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.CandidateResult;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ConstituencyElection;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.PartyRebelCandidate;
import com.itgrids.partyanalyst.service.IDelimitationConstituencyMandalService;
import com.itgrids.partyanalyst.service.IStaticDataService;
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
	}*/
	
	/*public void testFindByConstituencyElection(){
		List<Nomination> actualResult = nominationDAO.findByConstituencyElection(new Long(1));
		for(Nomination nomination: actualResult)
			System.out.println("NominationID=:"+nomination.getNominationId()+" partyID=:"+nomination.getParty().getPartyId());
		Assert.assertEquals(10, actualResult.size());
	}
	
	
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
	}
	
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
		List list = nominationDAO.findPartiesInfoByElectionAndPartyGroupByDistrict(10l, "24,62");
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
	}*/
	/*
	@SuppressWarnings("unchecked")
	public void testFindElectionResultsForAllPartiesInAssemblyConstituencies()
	{
		List<Long> constituencyIds = new ArrayList<Long>();
		constituencyIds.add(4L);
		constituencyIds.add(8L);
		constituencyIds.add(362L);
		
		List list1 = nominationDAO.findElectionResultsForAllPartiesInAssemblyConstituencies("2004",constituencyIds);
		for(Object[] values:(List<Object[]>)list1)
			System.out.println(values[0]+"\t"+values[1]+"\t"+values[2]+"\t"+values[3]+"\t"+values[4]+"\t"+values[5]+"\t"+values[6]);
			System.out.println("____________________________________________________________________________");
		List list2 = nominationDAO.findElectionResultsForAllPartiesInAssemblyConstituencies("2009",constituencyIds);
		for(Object[] values1:(List<Object[]>)list2)
			System.out.println(values1[0]+"\t"+values1[1]+"\t"+values1[2]+"\t"+values1[3]+"\t"+values1[4]+"\t"+values1[5]);

		
	}*/
		
	/*@SuppressWarnings("unchecked")
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
	}*/
	
	public void testGetMunicipalitiesAndCorporationsResultsInMandals(){
		List list = nominationDAO.getMunicipalitiesAndCorporationsResultsInMandals("384");
		System.out.println(list.size());
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]+"\t"+((Object[])list.get(i))[4]+"\t"+((Object[])list.get(i))[5]+"\t"+((Object[])list.get(i))[6]);
	}
}

