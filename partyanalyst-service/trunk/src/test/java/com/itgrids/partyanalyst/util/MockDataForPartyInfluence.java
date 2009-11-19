/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on November 9, 2009
 */
package com.itgrids.partyanalyst.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.CandidateResult;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ConstituencyElection;
import com.itgrids.partyanalyst.model.ConstituencyElectionResult;
import com.itgrids.partyanalyst.model.Country;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.ElectionScope;
import com.itgrids.partyanalyst.model.ElectionType;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.State;

public class MockDataForPartyInfluence {

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
	
	public static District getNelloreDistrict(){
		
	    District district = new District();
	    district.setDistrictId(new Long(19));
	    district.setDistrictName("Nellore");
	    district.setDistrictCode(new Long(1));
	    district.setState(getState());
	    
    return district;
    }
	
   public static District getChittoorDistrict(){
		
	    District district = new District();
	    district.setDistrictId(new Long(23));
	    district.setDistrictName("Nellore");
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

   
  public static Election getElection2004(){
	   
	   Election election =new Election();
	   election.setElectionId(new Long(1));
	   election.setElectionYear("2004");
	   election.setElectionScope(getElectionScope());
	  return election;
   }
  
  public static Election getElection2009(){
	   
	   Election election =new Election();
	   election.setElectionId(new Long(1));
	   election.setElectionYear("2009");
	   election.setElectionScope(getElectionScope());
	  return election;
  }
  
  
  public static List<Nomination> getNominationsForPartyForYearOne(){
	  
	  List<Nomination> nominations = new ArrayList<Nomination>();
	  
	  //nomination 1
	  Nomination nomination1 = new Nomination();
	  ConstituencyElectionResult constituencyElectionResult1 = new ConstituencyElectionResult();
	  ConstituencyElection constituencyElection1 = new ConstituencyElection();
	  Set<Nomination> nominatn1 = new HashSet<Nomination>();
	  
	    nomination1.setNominationId(new Long(1));
	  
	    Candidate candidate1 = new Candidate();
	    candidate1.setCandidateId(new Long(1));
	    candidate1.setFirstname("Vishnuvardhan Reddy");
	    candidate1.setLastname("Katam Reddy Vishnuvardhan Reddy");
	    
	    nomination1.setCandidate(candidate1);
	    
	    Party party1 = new Party();	    
	    party1.setPartyId(new Long(1));
	    party1.setLongName("Indian National Congress");
	    party1.setShortName("INC");
	    party1.setPartyRecognization("NP");
	    
	    nomination1.setParty(party1);
	    
	    Constituency constituency1 = new Constituency();
	    constituency1.setConstituencyId(new Long(1));
	    constituency1.setName("KAVALI");
	    constituency1.setDistrict(getNelloreDistrict());
	  
	    constituencyElectionResult1.setConstiElecResultId(new Long(1));
	    constituencyElectionResult1.setConstituencyElection(constituencyElection1);
	    constituencyElectionResult1.setValidVotes(new Double(154690.0));
	  
	    constituencyElection1.setConstiElecId(new Long(1));
	    constituencyElection1.setConstituency(constituency1);
	    constituencyElection1.setElection(getElection2009());
	    constituencyElection1.setConstituencyElectionResult(constituencyElectionResult1);
	    nominatn1.add(nomination1);
	    constituencyElection1.setNominations(nominatn1);
	    
	    CandidateResult candidateResult1 = new CandidateResult();
	    candidateResult1.setCandidateResultId(new Long(1));
	    candidateResult1.setRank(new Long(2));
	    candidateResult1.setVotesEarned(new Double(50192.0));
	    candidateResult1.setVotesPercengate("32.45");
	    candidateResult1.setNomination(nomination1);
	    
	    nomination1.setConstituencyElection(constituencyElection1);
	    nomination1.setCandidateResult(candidateResult1);
	    
	  nominations.add(nomination1);
	  
	  //nomination 2
	  
	  Nomination nomination2 = new Nomination();
	  ConstituencyElectionResult constituencyElectionResult2 = new ConstituencyElectionResult();
	  ConstituencyElection constituencyElection2 = new ConstituencyElection();
	  Set<Nomination> nominatn2 = new HashSet<Nomination>();
	  
	  nomination2.setNominationId(new Long(2));
	  
	    Candidate candidate2 = new Candidate();
	    candidate2.setCandidateId(new Long(2));
	    candidate2.setFirstname("Srinivasulu Reddy");
	    candidate2.setLastname("Polamreddy Srinivasulu Reddy");
	    
	    nomination2.setCandidate(candidate2);
	    
	    Party party2 = new Party();	    
	    party2.setPartyId(new Long(1));
	    party2.setLongName("Indian National Congress");
	    party2.setShortName("INC");
	    party2.setPartyRecognization("NP");
	    
	    nomination2.setParty(party2);
	    
	    Constituency constituency2 = new Constituency();
	    constituency2.setConstituencyId(new Long(2));
	    constituency2.setName("KOVUR");
	    constituency2.setDistrict(getNelloreDistrict());
	  
	    constituencyElectionResult2.setConstiElecResultId(new Long(2));
	    constituencyElectionResult2.setConstituencyElection(constituencyElection2);
	    constituencyElectionResult2.setValidVotes(new Double(169009.0));
	  
	    constituencyElection2.setConstiElecId(new Long(2));
	    constituencyElection2.setConstituency(constituency2);
	    constituencyElection2.setElection(getElection2009());
	    constituencyElection2.setConstituencyElectionResult(constituencyElectionResult2);
	    nominatn2.add(nomination2);
	    constituencyElection2.setNominations(nominatn2);
	    
	    CandidateResult candidateResult2 = new CandidateResult();
	    candidateResult2.setCandidateResultId(new Long(2));
	    candidateResult2.setRank(new Long(2));
	    candidateResult2.setVotesEarned(new Double(65768.0));
	    candidateResult2.setVotesPercengate("38.91");
	    candidateResult2.setNomination(nomination2);
	    
	    nomination2.setConstituencyElection(constituencyElection2);
	    nomination2.setCandidateResult(candidateResult2);
	    
	  nominations.add(nomination2);
	  
	  
	  //nomination 3
	  
	  Nomination nomination3 = new Nomination();
	  ConstituencyElectionResult constituencyElectionResult3 = new ConstituencyElectionResult();
	  ConstituencyElection constituencyElection3 = new ConstituencyElection();
	  Set<Nomination> nominatn3 = new HashSet<Nomination>();
	  
	  nomination3.setNominationId(new Long(3));
	  
	    Candidate candidate3 = new Candidate();
	    candidate3.setCandidateId(new Long(3));
	    candidate3.setFirstname("Karunakar Reddy");
	    candidate3.setLastname("Karunakar Reddy Bhumana");
	    
	    nomination3.setCandidate(candidate3);
	    
	    Party party3 = new Party();	    
	    party3.setPartyId(new Long(1));
	    party3.setLongName("Indian National Congress");
	    party3.setShortName("INC");
	    party3.setPartyRecognization("NP");
	    
	    nomination3.setParty(party3);
	    
	    Constituency constituency3 = new Constituency();
	    constituency3.setConstituencyId(new Long(3));
	    constituency3.setName("TIRUPATI");
	    constituency3.setDistrict(getChittoorDistrict());
	  
	    constituencyElectionResult3.setConstiElecResultId(new Long(3));
	    constituencyElectionResult3.setConstituencyElection(constituencyElection3);
	    constituencyElectionResult3.setValidVotes(new Double(127627.0));
	  
	    constituencyElection3.setConstiElecId(new Long(3));
	    constituencyElection3.setConstituency(constituency3);
	    constituencyElection3.setElection(getElection2009());
	    constituencyElection3.setConstituencyElectionResult(constituencyElectionResult3);
	    nominatn3.add(nomination3);
	    constituencyElection3.setNominations(nominatn3);
	    
	    CandidateResult candidateResult3 = new CandidateResult();
	    candidateResult3.setCandidateResultId(new Long(3));
	    candidateResult3.setRank(new Long(2));
	    candidateResult3.setVotesEarned(new Double(40379.0));
	    candidateResult3.setVotesPercengate("31.64");
	    candidateResult3.setNomination(nomination3);
	    
	    nomination3.setConstituencyElection(constituencyElection3);
	    nomination3.setCandidateResult(candidateResult3);
	    
	  nominations.add(nomination3);
	  
	  
  return nominations;
  }
  
  
 public static List<Nomination> getNominationsForNewPartyForYearOne(){
	  
	  List<Nomination> nominations = new ArrayList<Nomination>();
	  
	  //nomination 1
	  Nomination nomination1 = new Nomination();
	  ConstituencyElectionResult constituencyElectionResult1 = new ConstituencyElectionResult();
	  ConstituencyElection constituencyElection1 = new ConstituencyElection();
	  Set<Nomination> nominatn1 = new HashSet<Nomination>();
	  
	    nomination1.setNominationId(new Long(1));
	  
	    Candidate candidate1 = new Candidate();
	    candidate1.setCandidateId(new Long(1));
	    candidate1.setFirstname("Ramireddy Pratap Kumar Reddy");
	    candidate1.setLastname("Ramireddy Pratap Kumar Reddy");
	    
	    nomination1.setCandidate(candidate1);
	    
	    Party party1 = new Party();	    
	    party1.setPartyId(new Long(9));
	    party1.setLongName("Praja Rajyam Party");
	    party1.setShortName("PRP");
	    nomination1.setParty(party1);
	    
	    	    
	    Constituency constituency1 = new Constituency();
	    constituency1.setConstituencyId(new Long(1));
	    constituency1.setName("KAVALI");
	    constituency1.setDistrict(getNelloreDistrict());
	  
	    constituencyElectionResult1.setConstiElecResultId(new Long(1));
	    constituencyElectionResult1.setConstituencyElection(constituencyElection1);
	    constituencyElectionResult1.setValidVotes(new Double(154690.0));
	  
	    constituencyElection1.setConstiElecId(new Long(1));
	    constituencyElection1.setConstituency(constituency1);
	    constituencyElection1.setElection(getElection2009());
	    constituencyElection1.setConstituencyElectionResult(constituencyElectionResult1);
	    nominatn1.add(nomination1);
	    constituencyElection1.setNominations(nominatn1);
	    
	    CandidateResult candidateResult1 = new CandidateResult();
	    candidateResult1.setCandidateResultId(new Long(1));
	    candidateResult1.setRank(new Long(2));
	    candidateResult1.setVotesEarned(new Double(27352.0	));
	    candidateResult1.setVotesPercengate("17.68");
	    candidateResult1.setNomination(nomination1);
	    
	    nomination1.setConstituencyElection(constituencyElection1);
	    nomination1.setCandidateResult(candidateResult1);
	    
	  nominations.add(nomination1);
	  
	  //nomination 2
	  
	  Nomination nomination2 = new Nomination();
	  ConstituencyElectionResult constituencyElectionResult2 = new ConstituencyElectionResult();
	  ConstituencyElection constituencyElection2 = new ConstituencyElection();
	  Set<Nomination> nominatn2 = new HashSet<Nomination>();
	  
	  nomination2.setNominationId(new Long(2));
	  
	    Candidate candidate2 = new Candidate();
	    candidate2.setCandidateId(new Long(2));
	    candidate2.setFirstname("Thupakula Munemma");
	    candidate2.setLastname("Thupakula Munemma");
	    
	    nomination2.setCandidate(candidate2);
	    
	    Party party2 = new Party();	    
	    party2.setPartyId(new Long(9));
	    party2.setLongName("Praja Rajyam Party");
	    party2.setShortName("PRP");
	    nomination2.setParty(party2);
	    
	    Constituency constituency2 = new Constituency();
	    constituency2.setConstituencyId(new Long(2));
	    constituency2.setName("KOVUR");
	    constituency2.setDistrict(getNelloreDistrict());
	  
	    constituencyElectionResult2.setConstiElecResultId(new Long(2));
	    constituencyElectionResult2.setConstituencyElection(constituencyElection2);
	    constituencyElectionResult2.setValidVotes(new Double(169009.0));
	  
	    constituencyElection2.setConstiElecId(new Long(2));
	    constituencyElection2.setConstituency(constituency2);
	    constituencyElection2.setElection(getElection2009());
	    constituencyElection2.setConstituencyElectionResult(constituencyElectionResult2);
	    nominatn2.add(nomination2);
	    constituencyElection2.setNominations(nominatn2);
	    
	    CandidateResult candidateResult2 = new CandidateResult();
	    candidateResult2.setCandidateResultId(new Long(2));
	    candidateResult2.setRank(new Long(2));
	    candidateResult2.setVotesEarned(new Double(22624.0));
	    candidateResult2.setVotesPercengate("13.39");
	    candidateResult2.setNomination(nomination2);
	    
	    nomination2.setConstituencyElection(constituencyElection2);
	    nomination2.setCandidateResult(candidateResult2);
	    
	  nominations.add(nomination2);
	  
	  
	  //nomination 3
	  
	  Nomination nomination3 = new Nomination();
	  ConstituencyElectionResult constituencyElectionResult3 = new ConstituencyElectionResult();
	  ConstituencyElection constituencyElection3 = new ConstituencyElection();
	  Set<Nomination> nominatn3 = new HashSet<Nomination>();
	  
	  nomination3.setNominationId(new Long(3));
	  
	    Candidate candidate3 = new Candidate();
	    candidate3.setCandidateId(new Long(3));
	    candidate3.setFirstname("Konidala Chiranjeevi");
	    candidate3.setLastname("Konidala Chiranjeevi");
	    
	    nomination3.setCandidate(candidate3);
	    
	    Party party3 = new Party();	    
	    party3.setPartyId(new Long(9));
	    party3.setLongName("Praja Rajyam Party");
	    party3.setShortName("PRP");
	    	    
	    nomination3.setParty(party3);
	    
	    Constituency constituency3 = new Constituency();
	    constituency3.setConstituencyId(new Long(3));
	    constituency3.setName("TIRUPATI");
	    constituency3.setDistrict(getChittoorDistrict());
	  
	    constituencyElectionResult3.setConstiElecResultId(new Long(3));
	    constituencyElectionResult3.setConstituencyElection(constituencyElection3);
	    constituencyElectionResult3.setValidVotes(new Double(127627.0));
	  
	    constituencyElection3.setConstiElecId(new Long(3));
	    constituencyElection3.setConstituency(constituency3);
	    constituencyElection3.setElection(getElection2009());
	    constituencyElection3.setConstituencyElectionResult(constituencyElectionResult3);
	    nominatn3.add(nomination3);
	    constituencyElection3.setNominations(nominatn3);
	    
	    CandidateResult candidateResult3 = new CandidateResult();
	    candidateResult3.setCandidateResultId(new Long(3));
	    candidateResult3.setRank(new Long(2));
	    candidateResult3.setVotesEarned(new Double(56309.0));
	    candidateResult3.setVotesPercengate("44.12");
	    candidateResult3.setNomination(nomination3);
	    
	    nomination3.setConstituencyElection(constituencyElection3);
	    nomination3.setCandidateResult(candidateResult3);
	    
	  nominations.add(nomination3);
	  
	  
  return nominations;
  }
 
 public static List<Nomination> getNominationsForPartyForYearTwo(){
	  
	  List<Nomination> nominations = new ArrayList<Nomination>();
	  
	  //nomination 1
	  Nomination nomination1 = new Nomination();
	  ConstituencyElectionResult constituencyElectionResult1 = new ConstituencyElectionResult();
	  ConstituencyElection constituencyElection1 = new ConstituencyElection();
	  Set<Nomination> nominatn1 = new HashSet<Nomination>();
	  
	    nomination1.setNominationId(new Long(1));
	  
	    Candidate candidate1 = new Candidate();
	    candidate1.setCandidateId(new Long(1));
	    candidate1.setFirstname("PARVATHAMMA MAGUNTA");
	    candidate1.setLastname("PARVATHAMMA MAGUNTA");
	    
	    nomination1.setCandidate(candidate1);
	    
	    Party party1 = new Party();	    
	    party1.setPartyId(new Long(1));
	    party1.setLongName("Indian National Congress");
	    party1.setShortName("INC");
	    party1.setPartyRecognization("NP");
	    
	    nomination1.setParty(party1);
	    
	    Constituency constituency1 = new Constituency();
	    constituency1.setConstituencyId(new Long(1));
	    constituency1.setName("KAVALI");
	    constituency1.setDistrict(getNelloreDistrict());
	  
	    constituencyElectionResult1.setConstiElecResultId(new Long(1));
	    constituencyElectionResult1.setConstituencyElection(constituencyElection1);
	    constituencyElectionResult1.setValidVotes(new Double(118179.0));
	  
	    constituencyElection1.setConstiElecId(new Long(1));
	    constituencyElection1.setConstituency(constituency1);
	    constituencyElection1.setElection(getElection2009());
	    constituencyElection1.setConstituencyElectionResult(constituencyElectionResult1);
	    nominatn1.add(nomination1);
	    constituencyElection1.setNominations(nominatn1);
	    
	    CandidateResult candidateResult1 = new CandidateResult();
	    candidateResult1.setCandidateResultId(new Long(1));
	    candidateResult1.setRank(new Long(2));
	    candidateResult1.setVotesEarned(new Double(	68167.0));
	    candidateResult1.setVotesPercengate("57.68");
	    candidateResult1.setNomination(nomination1);
	    
	    nomination1.setConstituencyElection(constituencyElection1);
	    nomination1.setCandidateResult(candidateResult1);
	    
	  nominations.add(nomination1);
	  
	  //nomination 2
	  
	  Nomination nomination2 = new Nomination();
	  ConstituencyElectionResult constituencyElectionResult2 = new ConstituencyElectionResult();
	  ConstituencyElection constituencyElection2 = new ConstituencyElection();
	  Set<Nomination> nominatn2 = new HashSet<Nomination>();
	  
	  nomination2.setNominationId(new Long(2));
	  
	    Candidate candidate2 = new Candidate();
	    candidate2.setCandidateId(new Long(2));
	    candidate2.setFirstname("Srinivasulu Reddy");
	    candidate2.setLastname("Polamreddy Srinivasulu Reddy");
	    
	    nomination2.setCandidate(candidate2);
	    
	    Party party2 = new Party();	    
	    party2.setPartyId(new Long(1));
	    party2.setLongName("Indian National Congress");
	    party2.setShortName("INC");
	    party2.setPartyRecognization("NP");
	    
	    nomination2.setParty(party2);
	    
	    Constituency constituency2 = new Constituency();
	    constituency2.setConstituencyId(new Long(2));
	    constituency2.setName("KOVUR");
	    constituency2.setDistrict(getNelloreDistrict());
	  
	    constituencyElectionResult2.setConstiElecResultId(new Long(2));
	    constituencyElectionResult2.setConstituencyElection(constituencyElection2);
	    constituencyElectionResult2.setValidVotes(new Double(105172.0));
	  
	    constituencyElection2.setConstiElecId(new Long(2));
	    constituencyElection2.setConstituency(constituency2);
	    constituencyElection2.setElection(getElection2009());
	    constituencyElection2.setConstituencyElectionResult(constituencyElectionResult2);
	    nominatn2.add(nomination2);
	    constituencyElection2.setNominations(nominatn2);
	    
	    CandidateResult candidateResult2 = new CandidateResult();
	    candidateResult2.setCandidateResultId(new Long(2));
	    candidateResult2.setRank(new Long(2));
	    candidateResult2.setVotesEarned(new Double(45270.0));
	    candidateResult2.setVotesPercengate("43.04");
	    candidateResult2.setNomination(nomination2);
	    
	    nomination2.setConstituencyElection(constituencyElection2);
	    nomination2.setCandidateResult(candidateResult2);
	    
	  nominations.add(nomination2);
	  
	  
	  //nomination 3
	  
	  Nomination nomination3 = new Nomination();
	  ConstituencyElectionResult constituencyElectionResult3 = new ConstituencyElectionResult();
	  ConstituencyElection constituencyElection3 = new ConstituencyElection();
	  Set<Nomination> nominatn3 = new HashSet<Nomination>();
	  
	  nomination3.setNominationId(new Long(3));
	  
	    Candidate candidate3 = new Candidate();
	    candidate3.setCandidateId(new Long(3));
	    candidate3.setFirstname("VENKATA RAMANA");
	    candidate3.setLastname("M.VENKATA RAMANA");
	    
	    nomination3.setCandidate(candidate3);
	    
	    Party party3 = new Party();	    
	    party3.setPartyId(new Long(1));
	    party3.setLongName("Indian National Congress");
	    party3.setShortName("INC");
	    party3.setPartyRecognization("NP");
	    
	    nomination3.setParty(party3);
	    
	    Constituency constituency3 = new Constituency();
	    constituency3.setConstituencyId(new Long(3));
	    constituency3.setName("TIRUPATI");
	    constituency3.setDistrict(getChittoorDistrict());
	  
	    constituencyElectionResult3.setConstiElecResultId(new Long(3));
	    constituencyElectionResult3.setConstituencyElection(constituencyElection3);
	    constituencyElectionResult3.setValidVotes(new Double(148685.0));
	  
	    constituencyElection3.setConstiElecId(new Long(3));
	    constituencyElection3.setConstituency(constituency3);
	    constituencyElection3.setElection(getElection2009());
	    constituencyElection3.setConstituencyElectionResult(constituencyElectionResult3);
	    nominatn3.add(nomination3);
	    constituencyElection3.setNominations(nominatn3);
	    
	    CandidateResult candidateResult3 = new CandidateResult();
	    candidateResult3.setCandidateResultId(new Long(3));
	    candidateResult3.setRank(new Long(2));
	    candidateResult3.setVotesEarned(new Double(91863.0));
	    candidateResult3.setVotesPercengate("61.78");
	    candidateResult3.setNomination(nomination3);
	    
	    nomination3.setConstituencyElection(constituencyElection3);
	    nomination3.setCandidateResult(candidateResult3);
	    
	  nominations.add(nomination3);
	  
	  
  return nominations;
  }
 
}
