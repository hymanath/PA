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
import com.itgrids.partyanalyst.dto.MandalAllElectionDetailsVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.CandidateResult;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.PartyRebelCandidate;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;

public class NominationDAOHibernateTest extends BaseDaoTestCase {
	
	private INominationDAO nominationDAO;
	private IPartyRebelCandidateDAO partyRebelCandidateDAO;
	private ConstituencyElectionResultsVO constituencyElectionResultsVO ;
	private IConstituencyDAO constituencyDAO;
	private IConstituencyElectionDAO constituencyElectionDAO;
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;

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
	}*/
	/*public void testGetAllPartiesOfElectionTypeInTehsil(){
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
	}*/
	

	
	/*public void testGetAllConstiteunciesInfoForPartyInTehsil(){
		List list = nominationDAO.getAllConstiteunciesInfoForPartyInTehsil(844l, 24l, 5l);
		System.out.println(list.size());
		for(int i=0; i < list.size(); i++){
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]+"\t"+((Object[])list.get(i))[4]+"\t"+((Object[])list.get(i))[5]+"\t"+((Object[])list.get(i))[6]+"\t"+((Object[])list.get(i))[7]+"\t"+((Object[])list.get(i))[8]+"\t"+((Object[])list.get(i))[9]);
		}
	}
	
	public void testFindAllMptcAndZptcElectionsInfoInMandal(){
		List list = nominationDAO.findAllMptcAndZptcElectionsInfoInMandal(844l);
		for(int i=0; i<list.size(); i++){
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]+"\t"+((Object[])list.get(i))[4]+"\t"+((Object[])list.get(i))[5]+"\t"+((Object[])list.get(i))[6]+"\t"+((Object[])list.get(i))[7]+"\t"+((Object[])list.get(i))[8]+"\t"+((Object[])list.get(i))[9]+"\t"+((Object[])list.get(i))[10]+"\t"+((Object[])list.get(i))[11]+"\t"+((Object[])list.get(i))[12]+"\t"+((Object[])list.get(i))[13]);
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
	}*/
	
	/*public void testGetAllCandidatesByElectionTypes(){
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
	
	
/*	public ConstituencyElectionResultsVO getAssemblyOrParliamentCandidateDetailsForTheSelectedYearForAParty(String electionType,String electionYear,String resultsCategory,String electionLevel,Long partyId,Long stateId){
		
		if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE)){
			getCandidatesPartyInfoForAnElectionType(IConstants.ASSEMBLY_ELECTION_TYPE,electionYear,resultsCategory,electionLevel,partyId,stateId);			
		}else if(electionType.equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE)){
			getCandidatesPartyInfoForAnElectionType(IConstants.PARLIAMENT_ELECTION_TYPE,electionYear,resultsCategory,electionLevel,partyId,stateId);
		}
		return null;		
	}*/
	
	public void testCall(){

	  	 //District Level Details verified here....
	  
	//	getCandidatesPartyInfoForAnElectionType(IConstants.ASSEMBLY_ELECTION_TYPE,"2009",IConstants.WINNER_CANDIDATES,IConstants.DISTRICT_LEVEL,19l,0l,1l);
	//	getCandidatesPartyInfoForAnElectionType(IConstants.ASSEMBLY_ELECTION_TYPE,"2009",IConstants.ALL_CANDIDATES,IConstants.DISTRICT_LEVEL,19l,0l,1l);
	
	//	getCandidatesPartyInfoForAnElectionType(IConstants.PARLIAMENT_ELECTION_TYPE,"2009",IConstants.ALL_CANDIDATES,IConstants.DISTRICT_LEVEL,19l,0l,1l);
	//	getCandidatesPartyInfoForAnElectionType(IConstants.PARLIAMENT_ELECTION_TYPE,"2009",IConstants.WINNER_CANDIDATES,IConstants.DISTRICT_LEVEL,19l,0l,1l);
		
	//	getCandidatesWinnerInfoForAnElectionTypes(IConstants.ASSEMBLY_ELECTION_TYPE,"2009",IConstants.WINNER_CANDIDATES,IConstants.DISTRICT_LEVEL,19l,62l,1l);
	//	getCandidatesWinnerInfoForAnElectionTypes(IConstants.ASSEMBLY_ELECTION_TYPE,"2009",IConstants.ALL_CANDIDATES,IConstants.DISTRICT_LEVEL,19l,62l,1l);
		
	//	getCandidatesWinnerInfoForAnElectionTypes(IConstants.PARLIAMENT_ELECTION_TYPE,"2009",IConstants.ALL_CANDIDATES,IConstants.DISTRICT_LEVEL,19l,24l,1l);
	//	getCandidatesWinnerInfoForAnElectionTypes(IConstants.PARLIAMENT_ELECTION_TYPE,"2009",IConstants.WINNER_CANDIDATES,IConstants.DISTRICT_LEVEL,19l,24l,1l);

		 
	
	/* 
	 	 //Constituency Level Details verified here....
		  
		getCandidatesPartyInfoForAnElectionType(IConstants.ASSEMBLY_ELECTION_TYPE,"2009",IConstants.WINNER_CANDIDATES,IConstants.CONSTITUENCY_LEVEL,3382l,0l,1l);
		getCandidatesPartyInfoForAnElectionType(IConstants.ASSEMBLY_ELECTION_TYPE,"2009",IConstants.ALL_CANDIDATES,IConstants.CONSTITUENCY_LEVEL,3382l,0l,1l);
		
		getCandidatesPartyInfoForAnElectionType(IConstants.PARLIAMENT_ELECTION_TYPE,"2009",IConstants.ALL_CANDIDATES,IConstants.CONSTITUENCY_LEVEL,3565l,0l,1l);
		getCandidatesPartyInfoForAnElectionType(IConstants.PARLIAMENT_ELECTION_TYPE,"2009",IConstants.WINNER_CANDIDATES,IConstants.CONSTITUENCY_LEVEL,3565l,0l,1l);
		
		getCandidatesWinnerInfoForAnElectionTypes(IConstants.ASSEMBLY_ELECTION_TYPE,"2009",IConstants.WINNER_CANDIDATES,IConstants.CONSTITUENCY_LEVEL,3382l,62l,1l);
		getCandidatesWinnerInfoForAnElectionTypes(IConstants.ASSEMBLY_ELECTION_TYPE,"2009",IConstants.ALL_CANDIDATES,IConstants.CONSTITUENCY_LEVEL,3382l,62l,1l);
	
		getCandidatesWinnerInfoForAnElectionTypes(IConstants.PARLIAMENT_ELECTION_TYPE,"2009",IConstants.ALL_CANDIDATES,IConstants.CONSTITUENCY_LEVEL,3565l,62l,1l);
		getCandidatesWinnerInfoForAnElectionTypes(IConstants.PARLIAMENT_ELECTION_TYPE,"2009",IConstants.WINNER_CANDIDATES,IConstants.CONSTITUENCY_LEVEL,3565l,62l,1l);
	*/
		
/*
		//State Level Details verified here....
	
		getCandidatesPartyInfoForAnElectionType(IConstants.ASSEMBLY_ELECTION_TYPE,"2009",IConstants.ALL_CANDIDATES,IConstants.STATE_LEVEL,null,0l,1l);
		getCandidatesPartyInfoForAnElectionType(IConstants.ASSEMBLY_ELECTION_TYPE,"2009",IConstants.WINNER_CANDIDATES,IConstants.STATE_LEVEL,null,0l,1l);

		getCandidatesPartyInfoForAnElectionType(IConstants.PARLIAMENT_ELECTION_TYPE,"2009",IConstants.ALL_CANDIDATES,IConstants.STATE_LEVEL,null,0l,1l);
		getCandidatesPartyInfoForAnElectionType(IConstants.PARLIAMENT_ELECTION_TYPE,"2009",IConstants.WINNER_CANDIDATES,IConstants.STATE_LEVEL,null,0l,1l);
	
		getCandidatesWinnerInfoForAnElectionTypes(IConstants.ASSEMBLY_ELECTION_TYPE,"2009",IConstants.ALL_CANDIDATES,IConstants.STATE_LEVEL,null,62l,1l);
		getCandidatesWinnerInfoForAnElectionTypes(IConstants.ASSEMBLY_ELECTION_TYPE,"2009",IConstants.WINNER_CANDIDATES,IConstants.STATE_LEVEL,null,62l,1l);

		getCandidatesWinnerInfoForAnElectionTypes(IConstants.PARLIAMENT_ELECTION_TYPE,"2009",IConstants.ALL_CANDIDATES,IConstants.STATE_LEVEL,null,62l,1l);
		getCandidatesWinnerInfoForAnElectionTypes(IConstants.PARLIAMENT_ELECTION_TYPE,"2009",IConstants.WINNER_CANDIDATES,IConstants.STATE_LEVEL,null,62l,1l);
*/
		
	/*	 
	 	//Country Level Details verified here....
	 	 
	 	getCandidatesPartyInfoForAnElectionType(IConstants.PARLIAMENT_ELECTION_TYPE,"2009",IConstants.ALL_CANDIDATES,IConstants.COUNTRY_LEVEL,1l,0l,null);
		
	 	getCandidatesPartyInfoForAnElectionType(IConstants.PARLIAMENT_ELECTION_TYPE,"2009",IConstants.WINNER_CANDIDATES,IConstants.COUNTRY_LEVEL,1l,0l,null);
	 	
		getCandidatesWinnerInfoForAnElectionTypes(IConstants.PARLIAMENT_ELECTION_TYPE,"2009",IConstants.ALL_CANDIDATES,IConstants.COUNTRY_LEVEL,1l,24l,null);
		
		getCandidatesWinnerInfoForAnElectionTypes(IConstants.PARLIAMENT_ELECTION_TYPE,"2009",IConstants.WINNER_CANDIDATES,IConstants.COUNTRY_LEVEL,1l,24l,null);
		*/
		
		
	 	//ZPTC Level Details verified here....
	 
		getCandidatesPartyInfoForAnElectionType(IConstants.ZPTC_ELECTION_TYPE,"2006",IConstants.ALL_CANDIDATES,null,null,0l,1l);
		
	// 	getCandidatesPartyInfoForAnElectionType(IConstants.ZPTC_ELECTION_TYPE,"2006",IConstants.WINNER_CANDIDATES,null,null,0l,1l);
	 	
	//	getCandidatesPartyInfoForAnElectionType(IConstants.ZPTC_ELECTION_TYPE,"2006",IConstants.ALL_CANDIDATES,null,null,24l,1l);
		
	//	getCandidatesPartyInfoForAnElectionType(IConstants.ZPTC_ELECTION_TYPE,"2006",IConstants.WINNER_CANDIDATES,null,null,24l,1l);
		
		
/*	//	getCandidatesPartyInfoForAnElectionType(IConstants.MPTC_ELECTION_TYPE,"2006",IConstants.ALL_CANDIDATES,null,null,0l,1l);
		
	// 	getCandidatesPartyInfoForAnElectionType(IConstants.MPTC_ELECTION_TYPE,"2006",IConstants.WINNER_CANDIDATES,null,null,0l,1l);
	 	
	//	getCandidatesPartyInfoForAnElectionType(IConstants.MPTC_ELECTION_TYPE,"2006",IConstants.ALL_CANDIDATES,null,null,24l,1l);
		
	//	getCandidatesPartyInfoForAnElectionType(IConstants.MPTC_ELECTION_TYPE,"2006",IConstants.WINNER_CANDIDATES,null,null,24l,1l);
*/	}
	
	
	public CandidateDetailsVO getCandidatesPartyInfoForAnElectionType(String electionType,String electionYear,String resultsCategory,String electionLevel,Long locationId,Long partyId,Long stateId){
		CandidateDetailsVO candidateDetailsVO = new CandidateDetailsVO();
		if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE)){
			if(partyId==0l){
				candidateDetailsVO = getCandidatesInfoForAnElectionType(IConstants.ASSEMBLY_ELECTION_TYPE,electionYear,resultsCategory,electionLevel,locationId,stateId);						
			}else{
				candidateDetailsVO = getCandidatesWinnerInfoForAnElectionTypes(IConstants.ASSEMBLY_ELECTION_TYPE,electionYear,resultsCategory,electionLevel,locationId,partyId,stateId);
			}
		}else if(electionType.equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE)){
			if(partyId==0l){
				candidateDetailsVO = getCandidatesInfoForAnElectionType(IConstants.PARLIAMENT_ELECTION_TYPE,electionYear,resultsCategory,electionLevel,locationId,stateId);		
			}else{
				candidateDetailsVO = getCandidatesWinnerInfoForAnElectionTypes(IConstants.PARLIAMENT_ELECTION_TYPE,electionYear,resultsCategory,electionLevel,locationId,partyId,stateId);
			}
		}
		else if(electionType.equalsIgnoreCase(IConstants.ZPTC_ELECTION_TYPE)){
			
			candidateDetailsVO = getZptcOrMptcCandidatesInfoForAnElectionType(electionType,electionYear,resultsCategory,electionLevel,stateId,partyId);
		}
		else if(electionType.equalsIgnoreCase(IConstants.MPTC_ELECTION_TYPE)){
			candidateDetailsVO = getZptcOrMptcCandidatesInfoForAnElectionType(electionType,electionYear,resultsCategory,electionLevel,stateId,partyId);
		}
		return candidateDetailsVO;				
	}
	
	public CandidateDetailsVO getZptcOrMptcCandidatesInfoForAnElectionType(String electionType,String electionYear,String resultsCategory,String electionLevel,Long stateId,Long partyId){
		CandidateDetailsVO candidateDetailsVO = new CandidateDetailsVO();
		Long winnerCandidateRank = 1l,successorCandidateRank=2l;
		List allCandidateResult= null;
		List winnerCandidateResult = null;
		List successorCandidateResult = null;
		List<CandidateDetailsVO> allCandidates = new ArrayList<CandidateDetailsVO>(0);
		List<CandidateDetailsVO> winnerCandidate = new ArrayList<CandidateDetailsVO>(0);
		List<CandidateDetailsVO> successorCandidate = new ArrayList<CandidateDetailsVO>(0);
		
		if(partyId==0l && resultsCategory.equalsIgnoreCase(IConstants.ALL_CANDIDATES)){
			allCandidateResult = nominationDAO.findAllZPTCsOrMPTCsInaState(stateId,electionType,electionYear);	
			winnerCandidateResult = nominationDAO.findAllZPTCsOrMPTCsInaStateByRank(stateId,electionType,electionYear,winnerCandidateRank);
			successorCandidateResult = nominationDAO.findAllZPTCsOrMPTCsInaStateByRank(stateId,electionType,electionYear,successorCandidateRank);
			successorCandidate = setWinnerCandidateDetailsIntoVO(winnerCandidateResult,successorCandidateResult,0l);
			allCandidates = setAllCandidateDetailsIntoVo(winnerCandidateResult,allCandidateResult);
			if(successorCandidate!=null){
				allCandidates.addAll(successorCandidate);
			}	
			candidateDetailsVO.setCandidateDetails(allCandidates);
		}else if(partyId==0l && resultsCategory.equalsIgnoreCase(IConstants.WINNER_CANDIDATES)){
			 winnerCandidateResult = nominationDAO.findAllZPTCsOrMPTCsInaStateByRank(stateId,electionType,electionYear,winnerCandidateRank);
			 successorCandidateResult = nominationDAO.findAllZPTCsOrMPTCsInaStateByRank(stateId,electionType,electionYear,successorCandidateRank);
			 winnerCandidate = setWinnerCandidateDetailsIntoVO(winnerCandidateResult,successorCandidateResult,0l);
			 candidateDetailsVO.setCandidateDetails(winnerCandidate);
		}if(partyId!=0l && resultsCategory.equalsIgnoreCase(IConstants.ALL_CANDIDATES)){
			allCandidateResult = nominationDAO.findAllZPTCsOrMPTCsInaStateForAParty( stateId, electionType, electionYear, partyId);	
			winnerCandidateResult = nominationDAO.findAllZPTCsOrMPTCsInaStateByRank(stateId,electionType,electionYear,winnerCandidateRank);
			successorCandidateResult = nominationDAO.findAllZPTCsOrMPTCsInaStateByRank(stateId,electionType,electionYear,successorCandidateRank);
			successorCandidate = setWinnerCandidateDetailsIntoVO(winnerCandidateResult,successorCandidateResult,partyId);
			allCandidates = setAllCandidateDetailsIntoVo(winnerCandidateResult,allCandidateResult);
			if(successorCandidate!=null){
				allCandidates.addAll(successorCandidate);
			}	
			candidateDetailsVO.setCandidateDetails(allCandidates);
		}else if(partyId!=0l && resultsCategory.equalsIgnoreCase(IConstants.WINNER_CANDIDATES)){
			 winnerCandidateResult = nominationDAO.findAllZPTCsOrMPTCsInaStateForAPartyByRank( stateId, electionType, electionYear, partyId, winnerCandidateRank);
			 successorCandidateResult = nominationDAO.findAllZPTCsOrMPTCsInaStateByRank(stateId,electionType,electionYear,successorCandidateRank);
			 winnerCandidate = setWinnerCandidateDetailsIntoVO(winnerCandidateResult,successorCandidateResult,partyId);
			 candidateDetailsVO.setCandidateDetails(winnerCandidate);
		}
		return null;		
	}
	
	
	public CandidateDetailsVO getCandidatesInfoForAnElectionType(String electionType,String electionYear,String resultsCategory,String electionLevel,Long locationId,Long stateId){
		Long winnerCandidateRank = 1l,successorCandidateRank=2l;
		List allCandidateResult= null;
		List winnerCandidateResult = null;
		List successorCandidateResult = null;
		CandidateDetailsVO candidateDetailsVO = new CandidateDetailsVO();
		List<CandidateDetailsVO> allCandidates = new ArrayList<CandidateDetailsVO>(0);
		List<CandidateDetailsVO> winnerCandidate = new ArrayList<CandidateDetailsVO>(0);
		List<CandidateDetailsVO> successorCandidate = new ArrayList<CandidateDetailsVO>(0);
		
		if(resultsCategory.equalsIgnoreCase(IConstants.WINNER_CANDIDATES)){
			if(electionLevel.equalsIgnoreCase(IConstants.COUNTRY_LEVEL)){
				winnerCandidateResult = nominationDAO.findAllCandidatesForAnElectionBytheElectionYearByCountryIdByRank(electionYear,locationId,electionType,winnerCandidateRank);
				successorCandidateResult = nominationDAO.findAllCandidatesForAnElectionBytheElectionYearByCountryIdByRank(electionYear,locationId,electionType,successorCandidateRank);
			}else if(electionLevel.equalsIgnoreCase(IConstants.STATE_LEVEL)){
				  winnerCandidateResult = nominationDAO.findAllCandidatesForAnElectionBytheElectionYearByRank(electionYear,stateId,electionType,winnerCandidateRank);
				  successorCandidateResult = nominationDAO.findAllCandidatesForAnElectionBytheElectionYearByRank(electionYear,stateId,electionType,successorCandidateRank);
			}else if(electionLevel.equalsIgnoreCase(IConstants.DISTRICT_LEVEL)){					
					if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE)){
						StringBuilder listOfConstituencies  = new StringBuilder();	
						listOfConstituencies = getAssemblyConstituenciesForDistrict(locationId,stateId,electionYear);
						winnerCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRank(listOfConstituencies.substring(1),electionYear,electionType,winnerCandidateRank);
						successorCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRank(listOfConstituencies.substring(1),electionYear,electionType,successorCandidateRank);
					}else if(electionType.equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE)){
						StringBuilder listOfParliamentConstituencies  = new StringBuilder();	
						listOfParliamentConstituencies = getParliamentConstituenciesForDistrict(locationId,stateId,electionYear);	
						winnerCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRank(listOfParliamentConstituencies.substring(1),electionYear,electionType,winnerCandidateRank);
						successorCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRank(listOfParliamentConstituencies.substring(1),electionYear,electionType,successorCandidateRank);
					}					
			}else if(electionLevel.equalsIgnoreCase(IConstants.CONSTITUENCY_LEVEL)){					
					winnerCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRank(locationId.toString(),electionYear,electionType,winnerCandidateRank);
					successorCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRank(locationId.toString(),electionYear,electionType,successorCandidateRank);
			}
			winnerCandidate = setWinnerCandidateDetailsIntoVO(winnerCandidateResult,successorCandidateResult,0l);
			candidateDetailsVO.setCandidateDetails(winnerCandidate);
			
		}else if(resultsCategory.equalsIgnoreCase(IConstants.ALL_CANDIDATES)){
			if(electionLevel.equalsIgnoreCase(IConstants.COUNTRY_LEVEL)){
				winnerCandidateResult = nominationDAO.findAllCandidatesForAnElectionBytheElectionYearByCountryIdByRank(electionYear,locationId,electionType,winnerCandidateRank);
				successorCandidateResult = nominationDAO.findAllCandidatesForAnElectionBytheElectionYearByCountryIdByRank(electionYear,locationId,electionType,successorCandidateRank);
				allCandidateResult = nominationDAO.findAllCandidatesForAnElectionBytheElectionYearByCountryId(electionYear,locationId,electionType);
			}else if(electionLevel.equalsIgnoreCase(IConstants.STATE_LEVEL)){
				winnerCandidateResult = nominationDAO.findAllCandidatesForAnElectionBytheElectionYearByRank(electionYear,stateId,electionType,winnerCandidateRank);
				successorCandidateResult = nominationDAO.findAllCandidatesForAnElectionBytheElectionYearByRank(electionYear,stateId,electionType,successorCandidateRank);
				allCandidateResult = nominationDAO.findAllCandidatesForAnElectionBytheElectionYear(electionYear,stateId,electionType);	
			}else if(electionLevel.equalsIgnoreCase(IConstants.DISTRICT_LEVEL)){
				 if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE)){
						StringBuilder listOfConstituencies  = new StringBuilder();	
						listOfConstituencies = getAssemblyConstituenciesForDistrict(locationId,stateId,electionYear);
						winnerCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRank(listOfConstituencies.substring(1),electionYear,electionType,winnerCandidateRank);
						successorCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRank(listOfConstituencies.substring(1),electionYear,electionType,successorCandidateRank);
						allCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituency(listOfConstituencies.substring(1),electionYear,electionType);	
				 }else if(electionType.equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE)){
						StringBuilder listOfParliamentConstituencies  = new StringBuilder();	
						listOfParliamentConstituencies = getParliamentConstituenciesForDistrict(locationId,stateId,electionYear);			
						winnerCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRank(listOfParliamentConstituencies.substring(1),electionYear,electionType,winnerCandidateRank);
						successorCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRank(listOfParliamentConstituencies.substring(1),electionYear,electionType,successorCandidateRank);
						allCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituency(listOfParliamentConstituencies.substring(1),electionYear,electionType);					
				}	
			}else if(electionLevel.equalsIgnoreCase(IConstants.CONSTITUENCY_LEVEL)){
				winnerCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRank(locationId.toString(),electionYear,electionType,winnerCandidateRank);
				successorCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRank(locationId.toString(),electionYear,electionType,successorCandidateRank);
				allCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituency(locationId.toString(),electionYear,electionType);
			}
		
			successorCandidate = setWinnerCandidateDetailsIntoVO(winnerCandidateResult,successorCandidateResult,0l);
			allCandidates = setAllCandidateDetailsIntoVo(winnerCandidateResult,allCandidateResult);
			if(successorCandidate!=null){
				allCandidates.addAll(successorCandidate);
			}	
			candidateDetailsVO.setCandidateDetails(allCandidates);
		}		
		return candidateDetailsVO;
	}
	
								
	public CandidateDetailsVO getCandidatesWinnerInfoForAnElectionTypes(String electionType,String electionYear,String resultsCategory,String electionLevel,Long locationId,Long partyId,Long stateId){
		Long winnerCandidateRank = 1l,successorCandidateRank=2l;
		List allCandidateResult= null;
		List winnerCandidateResult = null;
		List successorCandidateResult = null;
		CandidateDetailsVO candidateDetailsVO = new CandidateDetailsVO();
		List<CandidateDetailsVO> allCandidates = new ArrayList<CandidateDetailsVO>(0);
		List<CandidateDetailsVO> winnerCandidate = new ArrayList<CandidateDetailsVO>(0);
		List<CandidateDetailsVO> successorCandidate = new ArrayList<CandidateDetailsVO>(0);
		
		if(resultsCategory.equalsIgnoreCase(IConstants.WINNER_CANDIDATES)){
			if(electionLevel.equalsIgnoreCase(IConstants.COUNTRY_LEVEL)){
				winnerCandidateResult = nominationDAO.findAllCandidatesForAnElectionBytheElectionYearByCountryIdByRankForAParty(electionYear,locationId,electionType,partyId,winnerCandidateRank);
				successorCandidateResult = nominationDAO.findAllCandidatesForAnElectionBytheElectionYearByCountryIdByRank(electionYear,locationId,electionType,successorCandidateRank);
			}else if(electionLevel.equalsIgnoreCase(IConstants.STATE_LEVEL)){
				winnerCandidateResult = nominationDAO.findAllCandidatesForAnElectionBytheElectionYearByRankForAParty(electionYear,stateId,electionType,winnerCandidateRank,partyId);
				successorCandidateResult =  nominationDAO.findAllCandidatesForAnElectionBytheElectionYearByRank(electionYear,stateId,electionType,successorCandidateRank);
				}else if(electionLevel.equalsIgnoreCase(IConstants.DISTRICT_LEVEL)){
				if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE)){							
					StringBuilder listOfConstituencies  = new StringBuilder();	
					listOfConstituencies = getAssemblyConstituenciesForDistrict(locationId,stateId,electionYear);
					winnerCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRank(listOfConstituencies.substring(1),electionYear,electionType,winnerCandidateRank);
					successorCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRank(listOfConstituencies.substring(1),electionYear,electionType,successorCandidateRank);
				}else if(electionType.equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE)){
					StringBuilder listOfParliamentConstituencies  = new StringBuilder();	
					listOfParliamentConstituencies = getParliamentConstituenciesForDistrict(locationId,stateId,electionYear);						
					winnerCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRankAndPartyId(listOfParliamentConstituencies.substring(1),electionYear,electionType,winnerCandidateRank,partyId);
					successorCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRank(listOfParliamentConstituencies.substring(1),electionYear,electionType,successorCandidateRank);
				}		 
				}else if(electionLevel.equalsIgnoreCase(IConstants.CONSTITUENCY_LEVEL)){					
					winnerCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRankAndPartyId(locationId.toString(),electionYear,electionType,winnerCandidateRank,partyId);
					successorCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRank(locationId.toString(),electionYear,electionType,successorCandidateRank);
				}
			winnerCandidate = setWinnerCandidateDetailsIntoVO(winnerCandidateResult,successorCandidateResult,partyId);
			candidateDetailsVO.setCandidateDetails(winnerCandidate);
		}else if(resultsCategory.equalsIgnoreCase(IConstants.ALL_CANDIDATES)){
			if(electionLevel.equalsIgnoreCase(IConstants.COUNTRY_LEVEL)){
				winnerCandidateResult = nominationDAO.findAllCandidatesForAnElectionBytheElectionYearByCountryIdByRank(electionYear,locationId,electionType,winnerCandidateRank);
				successorCandidateResult = nominationDAO.findAllCandidatesForAnElectionBytheElectionYearByCountryIdByRank(electionYear,locationId,electionType,successorCandidateRank);
				allCandidateResult = nominationDAO.findAllCandidatesForAnElectionBytheElectionYearByCountryIdForAParty(electionYear,locationId,electionType,partyId);
			}else if(electionLevel.equalsIgnoreCase(IConstants.STATE_LEVEL)){
				winnerCandidateResult = nominationDAO.findAllCandidatesForAnElectionBytheElectionYearByRank(electionYear,stateId,electionType,winnerCandidateRank);
				successorCandidateResult = nominationDAO.findAllCandidatesForAnElectionBytheElectionYearByRank(electionYear,stateId,electionType,successorCandidateRank);
				allCandidateResult = nominationDAO.findAllAssemblyCandidatesForAnElectionBytheElectionYear(electionYear,stateId,electionType,partyId);	
			}else if(electionLevel.equalsIgnoreCase(IConstants.DISTRICT_LEVEL)){
				if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE)){						
					StringBuilder listOfConstituencies  = new StringBuilder();	
					listOfConstituencies = getAssemblyConstituenciesForDistrict(locationId,stateId,electionYear);
					winnerCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRank(listOfConstituencies.substring(1),electionYear,electionType,winnerCandidateRank);
					successorCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRank(listOfConstituencies.substring(1),electionYear,electionType,successorCandidateRank);
					allCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituency(listOfConstituencies.substring(1),electionYear,electionType);
				}else if(electionType.equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE)){
					StringBuilder listOfParliamentConstituencies  = new StringBuilder();	
					listOfParliamentConstituencies = getParliamentConstituenciesForDistrict(locationId,stateId,electionYear);
					winnerCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRank(listOfParliamentConstituencies.substring(1),electionYear,electionType,winnerCandidateRank);
					successorCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRank(listOfParliamentConstituencies.substring(1),electionYear,electionType,successorCandidateRank);
					allCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyByPartyId(listOfParliamentConstituencies.substring(1),electionYear,electionType,partyId);						
				}
			}else if(electionLevel.equalsIgnoreCase(IConstants.CONSTITUENCY_LEVEL)){
					winnerCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRank(locationId.toString(),electionYear,electionType,winnerCandidateRank);
					successorCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyBasedOnRank(locationId.toString(),electionYear,electionType,successorCandidateRank);	
					allCandidateResult = nominationDAO.getCandidatesInfoForTheGivenConstituencyByPartyId(locationId.toString(),electionYear,electionType,partyId);
			}
			successorCandidate = setWinnerCandidateDetailsIntoVO(winnerCandidateResult,successorCandidateResult,partyId);
			allCandidates = setAllCandidateDetailsIntoVo(winnerCandidateResult,allCandidateResult);
			System.out.println("=================");
			System.out.println(successorCandidate.size()+"\t"+allCandidates.size());
			System.out.println("=================");
			if(successorCandidate!=null){
				allCandidates.addAll(successorCandidate);
			}	
			System.out.println("=================");
			System.out.println(allCandidates.size());
			System.out.println("=================");
			candidateDetailsVO.setCandidateDetails(allCandidates);
		}	
		return candidateDetailsVO;
	}
	
	public List<CandidateDetailsVO> setWinnerCandidateDetailsIntoVO(List winningCandidate,List successorCandidate,Long partyId){	
		List<CandidateDetailsVO> candidateDetails = new ArrayList<CandidateDetailsVO>(0);		
		Long constituencyId,candidatepartyID=0l;
		Long selectedPartyId = partyId;
		Long noPartySelectionCriteria =0l;
		Float differenceVotes,votesPercentage;
		Map<Long,Float> winner = new HashMap<Long,Float>(0);
		Map<Long,Float> successor = new HashMap<Long,Float>(0);
		try{
			for(int i=0;i<successorCandidate.size();i++){
				Object[] parms = (Object[])successorCandidate.get(i);
				successor.put(Long.parseLong(parms[9].toString()), Float.parseFloat(parms[2].toString()));
			}
			log.info("Inside populateElectionsData() method..");
			for(int i=0;i<winningCandidate.size();i++){
				CandidateDetailsVO candidateDetailsVo = new CandidateDetailsVO();
				Object[] parms = (Object[])winningCandidate.get(i);
				constituencyId = Long.parseLong(parms[9].toString());
				candidateDetailsVo.setCandidateId(new Long(parms[0].toString()));
				String candidateName = parms[1].toString();
				if(candidateName.contains("\n")){
					candidateName = candidateName.replace("\n"," ");
					candidateDetailsVo.setCandidateName(candidateName);
				}else{
					candidateDetailsVo.setCandidateName(candidateName);
				}
				if(parms[2]!= null){
					candidateDetailsVo.setVotesEarned(parms[2].toString());
				}else{
					candidateDetailsVo.setVotesEarned("--");
				}
				if(parms[3]!= null){
					candidateDetailsVo.setVotesPercentage(parms[3].toString());
				}else{
					candidateDetailsVo.setVotesPercentage("--");
				}			
				candidateDetailsVo.setRank(new Long(parms[4].toString()));
				if(parms[6]!= null){
					candidateDetailsVo.setPartyFlag(parms[6].toString());
				}else{
					candidateDetailsVo.setPartyFlag("no_Image.png");
				}			
				candidateDetailsVo.setPartyName(parms[7].toString());
				candidateDetailsVo.setConstituencyId(new Long(parms[9].toString()));
				candidateDetailsVo.setConstituencyName(parms[10].toString());
				candidateDetailsVo.setElectionYear(parms[11].toString());
				candidateDetailsVo.setElectionType(parms[12].toString());	
				candidateDetailsVo.setMoreDetails("view more details");
				if(successor.containsKey(constituencyId)){
					differenceVotes = (Float.parseFloat(parms[2].toString())-successor.get(constituencyId));
					if(Float.parseFloat(parms[2].toString())!=0f){
						votesPercentage =  differenceVotes/(Float.parseFloat(parms[2].toString()))*100;
					}else{
						votesPercentage = 0f;
					}				
					candidateDetailsVo.setVotesDifference(Float.parseFloat(differenceVotes.toString()));
					candidateDetailsVo.setVotesPercentage(new BigDecimal(votesPercentage.floatValue()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());		
				}else{
					differenceVotes = 0f;
					votesPercentage = 0f;
					candidateDetailsVo.setVotesDifference(differenceVotes);
					candidateDetailsVo.setVotesPercentage(votesPercentage.toString());
				}
				
				candidatepartyID = new Long(parms[5].toString());
				
				if(selectedPartyId==0L){
					candidateDetails.add(candidateDetailsVo);
					System.out.println(candidateDetailsVo.getCandidateName()+"\t\t"+parms[4]+"\t\t"+candidateDetailsVo.getPartyName()+"\t"+candidateDetailsVo.getConstituencyName()+"\t"+candidateDetailsVo.getVotesEarned()+"\t"+Float.parseFloat(parms[2].toString())+"\t"+candidateDetailsVo.getVotesDifference()+"\t"+candidateDetailsVo.getVotesPercentage());
				}
				else if(candidatepartyID.equals(selectedPartyId)){
					candidateDetails.add(candidateDetailsVo);	
					System.out.println(candidateDetailsVo.getCandidateName()+"\t\t"+parms[4]+"\t\t"+candidateDetailsVo.getPartyName()+"\t"+candidateDetailsVo.getConstituencyName()+"\t"+candidateDetailsVo.getVotesEarned()+"\t"+Float.parseFloat(parms[2].toString())+"\t"+candidateDetailsVo.getVotesDifference()+"\t"+candidateDetailsVo.getVotesPercentage());
				}else{}								
			}
		return candidateDetails;
		}catch(Exception e){
			log.error("Exception raised please check the log for details"+e);
			e.printStackTrace();
			return null;
		}
	}
	
	public List<CandidateDetailsVO> setAllCandidateDetailsIntoVo(List winningCandidate,List allCandidates){
		List<CandidateDetailsVO> candidateDetails = new ArrayList<CandidateDetailsVO>(0);	
		Map<Long,Float> winner = new HashMap<Long,Float>(0);
		Float differenceVotes,votesPercentage;
		Long constituencyId;
		Long rank=0l;
		try{
			for(int i=0;i<winningCandidate.size();i++){
				Object[] parms = (Object[])winningCandidate.get(i);
				winner.put(Long.parseLong(parms[9].toString()), Float.parseFloat(parms[2].toString()));
			}		
			for(int i=0;i<allCandidates.size();i++){
				Object[] parms = (Object[])allCandidates.get(i);
				CandidateDetailsVO candidateDetailsVo = new CandidateDetailsVO();
				constituencyId=Long.parseLong(parms[9].toString());
				candidateDetailsVo.setCandidateId(new Long(parms[0].toString()));
				String candidateName = parms[1].toString();
				if(candidateName.contains("\n")){
					candidateName = candidateName.replace("\n"," ");
					candidateDetailsVo.setCandidateName(candidateName);
				}else{
					candidateDetailsVo.setCandidateName(candidateName);
				}
				if(parms[2]!= null){
					candidateDetailsVo.setVotesEarned(parms[2].toString());
				}else{
					candidateDetailsVo.setVotesEarned("--");
				}
				if(parms[3]!= null){
					candidateDetailsVo.setVotesPercentage(parms[3].toString());
				}else{
					candidateDetailsVo.setVotesPercentage("--");
				}			
				candidateDetailsVo.setRank(new Long(parms[4].toString()));
				if(parms[6]!= null){
					candidateDetailsVo.setPartyFlag(parms[6].toString());
				}else{
					candidateDetailsVo.setPartyFlag("no_Image.png");
				}			
				candidateDetailsVo.setPartyName(parms[7].toString());
				candidateDetailsVo.setConstituencyId(new Long(parms[9].toString()));
				candidateDetailsVo.setConstituencyName(parms[10].toString());
				candidateDetailsVo.setElectionYear(parms[11].toString());
				candidateDetailsVo.setElectionType(parms[12].toString());	
				candidateDetailsVo.setMoreDetails("view more details");
				if(winner.containsKey(constituencyId)){
					differenceVotes = winner.get(constituencyId)-Float.parseFloat(parms[2].toString());
					if(winner.get(constituencyId)!=0){
						votesPercentage =  differenceVotes/winner.get(constituencyId)*100;
					}else{
						votesPercentage = 0f;
					}					
					candidateDetailsVo.setVotesDifference(Float.parseFloat(differenceVotes.toString()));
					candidateDetailsVo.setVotesPercentage(new BigDecimal(votesPercentage.floatValue()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				}else{
					differenceVotes = 0f;
					votesPercentage = 0f;
					candidateDetailsVo.setVotesDifference(differenceVotes);
					candidateDetailsVo.setVotesPercentage(votesPercentage.toString());
				}
				rank = Long.parseLong(parms[4].toString());
				if(rank!=1l){
					candidateDetails.add(candidateDetailsVo);
					System.out.println(candidateDetailsVo.getCandidateName()+"\t\t"+parms[4]+"\t\t"+candidateDetailsVo.getPartyName()+"\t"+candidateDetailsVo.getConstituencyName()+"\t"+candidateDetailsVo.getVotesEarned()+"\t"+Float.parseFloat(parms[2].toString())+"\t"+candidateDetailsVo.getVotesDifference()+"\t"+candidateDetailsVo.getVotesPercentage());
				}else{}
					
			}
			System.out.println("=======================");
			System.out.println("=======================");
			System.out.println("=======================");
			System.out.println("All Candidates Size is-->"+allCandidates.size());
			System.out.println("=======================");
			System.out.println("=======================");
			System.out.println("=======================");
			return candidateDetails;	
		}catch(Exception e){
			log.error("Exception raised please check the log for details"+e);
			e.printStackTrace();
			return null;
		}
		
	}
		
	public StringBuilder getAssemblyConstituenciesForDistrict(Long locationId,Long stateId,String electionYear){
		StringBuilder listOfConstituencies  = new StringBuilder();	
		List list = constituencyElectionDAO.findConstituencyByDistrictAndStateIds(locationId,stateId,electionYear);						
		for(int i=0;i<list.size();i++){
			Object[] parms = (Object[])list.get(i);
			listOfConstituencies.append(",").append(new Long(parms[0].toString()));
		}
		return listOfConstituencies;
	}
	
	public StringBuilder getParliamentConstituenciesForDistrict(Long locationId,Long stateId,String electionYear){
		Set<Long> parliamentIds = new HashSet<Long>(0);
		List list = constituencyElectionDAO.findConstituencyByDistrictAndStateIds(locationId,stateId,electionYear);						
		StringBuilder listOfConstituencies  = new StringBuilder();					
		for(int i=0;i<list.size();i++){
			Object[] parms = (Object[])list.get(i);
			listOfConstituencies.append(",").append(new Long(parms[0].toString()));
		}						
		List parliamentList = delimitationConstituencyAssemblyDetailsDAO.findParliamentConstituencyForListOfAssemblyConstituency(listOfConstituencies.substring(1),new Long(electionYear));
		for(int i=0;i<parliamentList.size();i++){
			Object[] parms = (Object[])parliamentList.get(i);
			parliamentIds.add(new Long(parms[0].toString()));
		}						
		StringBuilder listOfParliamentConstituencies  = new StringBuilder();	
		Iterator it = parliamentIds.iterator();
		while(it.hasNext()){							
			listOfParliamentConstituencies.append(",").append(new Long(it.next().toString()));
		}				
		return listOfParliamentConstituencies;		
	}
	
	/*public void testZptcCandidatesForADistrict(){
		List list = nominationDAO.findAllZPTCsInaState(1l,IConstants.ZPTC_ELECTION_TYPE,"2006");
		for(int i=0;i<list.size();i++){
			Object[] parms = (Object[])list.get(i);
			System.out.println(parms[0]+"\t"+parms[1]);
		}
		
	}*/
}
