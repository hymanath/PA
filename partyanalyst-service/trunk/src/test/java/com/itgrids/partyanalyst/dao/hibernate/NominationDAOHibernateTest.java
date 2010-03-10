package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Assert;

import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyRebelCandidateDAO;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.PartyRebelCandidate;
import com.itgrids.partyanalyst.utils.IConstants;

public class NominationDAOHibernateTest extends BaseDaoTestCase {
	
	private INominationDAO nominationDAO;
	private IPartyRebelCandidateDAO partyRebelCandidateDAO;
	
	
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
	*/
	public void testConstituencyElection(){
		List result = nominationDAO.findAllCandidatesForAnElectionByElectionYear(3382l);
		assertEquals(1, result.size());
	}
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
	}*/
	
	public void testFindAllMptcAndZptcElectionsInfoInMandal(){
		List list = nominationDAO.findAllMptcAndZptcElectionsInfoInMandal(844l);
		for(int i=0; i<list.size(); i++){
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]+"\t"+((Object[])list.get(i))[4]+"\t"+((Object[])list.get(i))[5]+"\t"+((Object[])list.get(i))[6]+"\t"+((Object[])list.get(i))[7]+"\t"+((Object[])list.get(i))[8]+"\t"+((Object[])list.get(i))[9]+"\t"+((Object[])list.get(i))[10]+"\t"+((Object[])list.get(i))[11]+"\t"+((Object[])list.get(i))[12]+"\t"+((Object[])list.get(i))[13]);
		}
			
	}
}
