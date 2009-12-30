/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 06, 2009
 */
package com.itgrids.partyanalyst.util;

import java.util.ArrayList;
import java.util.List;

import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.CandidateResult;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ConstituencyElection;
import com.itgrids.partyanalyst.model.Country;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.ElectionScope;
import com.itgrids.partyanalyst.model.ElectionType;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.State;

public class DummyCandidateResults {

	
	
	public static Country getCountry(){
		   Country country = new Country();
		   country.setCountryId(new Long(1));
		   country.setCountryName("India");
		   country.setCapital("Delhi");
		   country.setIsoCode("IN");
	return country;	
	}
	
	public static State getState(){
		State state = new State();
		    state.setStateId(new Long(1));
		    state.setStateName("Andhra Pradesh");
		    state.setAdminCapital("Hyderabad");
		    state.setStateCode(new Long(28));
		    state.setStateSong("Maa Telugu Talli");
		    state.setCountry(getCountry());
    return state;		  
	}
	
	public static District getDistrict(){
		
		    District district = new District();
		    district.setDistrictId(new Long(1));
		    district.setDistrictName("Adilabad");
		    district.setDistrictCode(new Long(1));
		    district.setState(getState());
		    
    return district;
	}
	
	public static ElectionType getElectionType(){
		    ElectionType electionType = new ElectionType();
		    electionType.setElectionTypeId(new Long(2));
		    electionType.setElectionType("Assembly");
		    electionType.setScope("State");
	return electionType;
	}
	
	public static ElectionScope getElectionScope(){
		  ElectionScope electionScope = new ElectionScope();
		  electionScope.setCountry(getCountry());
		  electionScope.setElectionScopeId(new Long(2));
		  electionScope.setElectionType(getElectionType());
		  electionScope.setState(getState());
	return electionScope;
	}
	
	public static Constituency getConstituency1(){
			
		  Constituency constituency = new Constituency();
		  constituency.setConstituencyId(new Long(617));
		  constituency.setElectionScope(getElectionScope());
		  constituency.setName("Sirpur");
		  constituency.setState(getState());
		  constituency.setCountryId(new Long(1));
		  constituency.setDistrict(getDistrict());
		  
	return constituency;
	}
	
	public static Constituency getConstituency2(){
		
		  Constituency constituency = new Constituency();
		  constituency.setConstituencyId(new Long(618));
		  constituency.setElectionScope(getElectionScope());
		  constituency.setName("Chennur (SC)");
		  constituency.setState(getState());
		  constituency.setCountryId(new Long(1));
		  constituency.setDistrict(getDistrict());
		  
    return constituency;
	}
	
	public static Election getElection(){
		  Election election = new Election();
		  election.setElectionId(new Long(10));
		  election.setElectionScope(getElectionScope());
		  election.setElectionYear("2009");
	return election;
	}
	
	public List<Candidate> getCandidates(){
		List<Candidate> candidates = new ArrayList<Candidate>();
		
		    Candidate candidate = new Candidate();
		    candidate.setCandidateId(new Long(184));
		    candidate.setLastname("Kaveti Sammaiah");
		    candidates.add(candidate);
		    
		    candidate.setCandidateId(new Long(185));
		    candidate.setLastname("Koneru Konappa");
		    candidates.add(candidate);
		    
		    candidate.setCandidateId(new Long(186));
		    candidate.setLastname("Ganapuram Muralidher");
		    candidates.add(candidate);
		    
		    candidate.setCandidateId(new Long(187));
		    candidate.setLastname("Lendugure Mengaji Patel");
		    candidates.add(candidate);
		    
		    candidate.setCandidateId(new Long(188));
		    candidate.setLastname("Dubbula Venkaiah");
		    candidates.add(candidate);
		    
		    candidate.setCandidateId(new Long(189));
		    candidate.setLastname("Bingi Srinivas");
		    candidates.add(candidate);
		    
		    candidate.setCandidateId(new Long(190));
		    candidate.setLastname("Ravindar Nikode");
		    candidates.add(candidate);
		    
		    candidate.setCandidateId(new Long(191));
		    candidate.setLastname("Dubbula Janardhan");
		    candidates.add(candidate);
		    
		    candidate.setCandidateId(new Long(192));
		    candidate.setLastname("Nallala Odelu");
		    candidates.add(candidate);
		    
		    candidate.setCandidateId(new Long(193));
		    candidate.setLastname("G.Vinod");
		    candidates.add(candidate);
		    
		    candidate.setCandidateId(new Long(194));
		    candidate.setLastname("Andugula Srinivas");
		    candidates.add(candidate);
		    
	return candidates;
	}
	
	public List<Party> getParties(){
		List<Party> parties = new ArrayList<Party>();
		
		    Party party = new Party();
		    party.setPartyId(new Long(10));
		    party.setLongName("Telangana Rashtra Samithi");
		    party.setShortName("TRS");
		    party.setPartyRecognization("SP");
		    parties.add(party);
		    
		    party.setPartyId(new Long(1));
		    party.setLongName("Indian National Congress");
		    party.setShortName("INC");
		    party.setPartyRecognization("NP");
		    parties.add(party);
		    
		    party.setPartyId(new Long(2));
		    party.setLongName("Bharatiya Janata Party");
		    party.setShortName("BJP");
		    party.setPartyRecognization("NP");
		    parties.add(party);
		    
		    party.setPartyId(new Long(9));
		    party.setLongName("Praja Rajyam Party");
		    party.setShortName("PRP");
		    parties.add(party);
		    
		    party.setPartyId(new Long(13));
		    party.setLongName("Independent");
		    party.setShortName("IND");
		    parties.add(party);
		    
		    party.setPartyId(new Long(14));
		    party.setLongName("Pyramid Party of India");
		    party.setShortName("PPOI");
		    parties.add(party);
		    
		    party.setPartyId(new Long(3));
		    party.setLongName("Bahujan Samaj Party");
		    party.setShortName("BSP");
		    parties.add(party);
		    
		    party.setPartyId(new Long(12));
		    party.setLongName("Lok Satta Party");
		    parties.add(party);
		
	return parties;
	}
	
	public static List<CandidateResult> getCandidateResults(){
		
		List<CandidateResult> candidateResults = new ArrayList<CandidateResult>();
		
		        Candidate candidate = new Candidate();
                candidate.setCandidateId(new Long(184));
                candidate.setLastname("Kaveti Sammaiah");
		        ConstituencyElection constituencyElection = new ConstituencyElection();
			    constituencyElection.setConstiElecId(new Long(2979));
			    constituencyElection.setConstituency(getConstituency1());
			    constituencyElection.setElection(getElection());
			    Party party = new Party();
			    party.setPartyId(new Long(10));
			    party.setLongName("Telangana Rashtra Samithi");
			    party.setShortName("TRS");
			    party.setPartyRecognization("SP");
			    Nomination nomination = new Nomination();
		        nomination.setNominationId(new Long(24050));
		        nomination.setCandidate(candidate);
		        nomination.setConstituencyElection(constituencyElection);
			    nomination.setParty(party);
		     
		              CandidateResult result = new CandidateResult();
		              result.setCandidateResultId(new Long(24050));
		              result.setRank(new Long(1));
		              result.setVotesEarned(new Double(47978));
		              result.setVotesPercengate("38.71");
		              result.setNomination(nomination);
		              candidateResults.add(result);
		        
		        Candidate candidate1 = new Candidate();
			    candidate1.setCandidateId(new Long(185));
			    candidate1.setLastname("Koneru Konappa");
			    ConstituencyElection constituencyElection1 = new ConstituencyElection();
		        constituencyElection1.setConstiElecId(new Long(2979));
		        constituencyElection1.setConstituency(getConstituency1());
		        constituencyElection1.setElection(getElection());
		        Party party1 = new Party();
		        party1.setPartyId(new Long(1));
			    party1.setLongName("Indian National Congress");
			    party1.setShortName("INC");
			    party1.setPartyRecognization("NP");
		        Nomination nomination1 = new Nomination();
	            nomination1.setNominationId(new Long(24051));
	            nomination1.setCandidate(candidate1);
	            nomination1.setConstituencyElection(constituencyElection1);
		        nomination1.setParty(party1);
		         
		           CandidateResult result1 = new CandidateResult();
		           result1.setCandidateResultId(new Long(24051));
		           result1.setRank(new Long(2));
		           result1.setVotesEarned(new Double(40564));
		           result1.setVotesPercengate("32.73");
		           result1.setNomination(nomination1);
		           candidateResults.add(result1);
		           
		        Nomination nomination2 = new Nomination();
		        nomination2.setNominationId(new Long(24052));
		        Candidate candidate2 = new Candidate();
		        candidate2.setCandidateId(new Long(186));
			    candidate2.setLastname("Ganapuram Muralidher");
				nomination2.setCandidate(candidate2);
		        ConstituencyElection constituencyElection2 = new ConstituencyElection();
			    constituencyElection2.setConstiElecId(new Long(2979));
			    constituencyElection2.setConstituency(getConstituency1());
			    constituencyElection2.setElection(getElection());
			    nomination2.setConstituencyElection(constituencyElection2);
			    Party party2 = new Party();
			    party2.setPartyId(new Long(2));
			    party2.setLongName("Bharatiya Janata Party");
			    party2.setShortName("BJP");
			    party2.setPartyRecognization("NP");
			    nomination2.setParty(party2);    
		             
			        CandidateResult result2 = new CandidateResult();
		            result2.setCandidateResultId(new Long(24052));
		            result2.setRank(new Long(3));
		            result2.setVotesEarned(new Double(9755));
		            result2.setVotesPercengate("7.87");
		            result2.setNomination(nomination2);
		            candidateResults.add(result2);
		            
		        Nomination nomination3 = new Nomination();
			    nomination3.setNominationId(new Long(24053));
			    Candidate candidate3 = new Candidate();
			    candidate3.setCandidateId(new Long(187));
			    candidate3.setLastname("Lendugure Mengaji Patel");
		        nomination3.setCandidate(candidate3);
			    ConstituencyElection constituencyElection3 = new ConstituencyElection();
				constituencyElection3.setConstiElecId(new Long(2979));
				constituencyElection3.setConstituency(getConstituency1());
				constituencyElection3.setElection(getElection());
				nomination3.setConstituencyElection(constituencyElection3);
				Party party3 = new Party();
				party3.setPartyId(new Long(9));
			    party3.setLongName("Praja Rajyam Party");
			    party3.setShortName("PRP");
				nomination3.setParty(party3);    
		    
				    CandidateResult result3 = new CandidateResult();
		            result3.setCandidateResultId(new Long(24053));
		            result3.setRank(new Long(4));
		            result3.setVotesEarned(new Double(8783));
		            result3.setVotesPercengate("7.09");
		            result3.setNomination(nomination3);
		            candidateResults.add(result3);
		            
		        Nomination nomination4 = new Nomination();
				nomination4.setNominationId(new Long(24054));
				Candidate candidate4 = new Candidate();
				candidate4.setCandidateId(new Long(188));
			    candidate4.setLastname("Dubbula Venkaiah");
			    nomination4.setCandidate(candidate4);
				ConstituencyElection constituencyElection4 = new ConstituencyElection();
		        constituencyElection4.setConstiElecId(new Long(2979));
			    constituencyElection4.setConstituency(getConstituency1());
			    constituencyElection4.setElection(getElection());
				nomination4.setConstituencyElection(constituencyElection4);
				Party party4 = new Party();
				party4.setPartyId(new Long(13));
			    party4.setLongName("Independent");
			    party4.setShortName("IND");
			    nomination4.setParty(party4);
		    
			         CandidateResult result4 = new CandidateResult();
		             result4.setCandidateResultId(new Long(24054));
		             result4.setRank(new Long(5));
		             result4.setVotesEarned(new Double(8608));
		             result4.setVotesPercengate("6.94");
		             result4.setNomination(nomination4);
		             candidateResults.add(result4);
		    
		        Nomination nomination5 = new Nomination();
			    nomination5.setNominationId(new Long(24055));
			    Candidate candidate5 = new Candidate();
			    candidate5.setCandidateId(new Long(189));
			    candidate5.setLastname("Bingi Srinivas");
			    nomination5.setCandidate(candidate5);
				ConstituencyElection constituencyElection5 = new ConstituencyElection();
				constituencyElection5.setConstiElecId(new Long(2979));
			    constituencyElection5.setConstituency(getConstituency1());
			    constituencyElection5.setElection(getElection());
				nomination5.setConstituencyElection(constituencyElection5);
				Party party5 = new Party();
				party5.setPartyId(new Long(14));
			    party5.setLongName("Pyramid Party of India");
			    party5.setShortName("PPOI");
				nomination5.setParty(party5);
				
				     CandidateResult result5 = new CandidateResult();
		             result5.setCandidateResultId(new Long(24055));
		             result5.setRank(new Long(6));
		             result5.setVotesEarned(new Double(3366));
		             result5.setVotesPercengate("2.72");
		             result5.setNomination(nomination5);
		             candidateResults.add(result5);
		    
	return 	candidateResults;
	}
	
	
}
