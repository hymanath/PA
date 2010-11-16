package com.itgrids.partyanalyst.util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.itgrids.partyanalyst.dto.EventActionPlanVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.UserEventVO;
import com.itgrids.partyanalyst.model.Cadre;
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
import com.itgrids.partyanalyst.model.GroupEntitlement;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.model.InformationSource;
import com.itgrids.partyanalyst.model.MessageType;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.PartyImportantDates;
import com.itgrids.partyanalyst.model.Problem;
import com.itgrids.partyanalyst.model.ProblemAndProblemSource;
import com.itgrids.partyanalyst.model.ProblemLocation;
import com.itgrids.partyanalyst.model.Registration;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.model.UserEventActionPlan;
import com.itgrids.partyanalyst.model.UserEvents;
import com.itgrids.partyanalyst.model.UserGroupEntitlement;
import com.itgrids.partyanalyst.model.UserGroups;
import com.itgrids.partyanalyst.model.UserImpDate;


public class MockData {
	
	private static final Country country = new Country(new Long(1), "India", "Hyderabd", "IN", null); 

	private static final State state = new State(new Long(1), country, "Andhra Pradesh", null, null, null,
			null, "telugu", null,null, null, null,"Neem", "kabaddi", null,null, null, null, null, null, null,null,null,null,null); 
	

	private static final ElectionType electionType = new ElectionType(new Long(2),"Assembly","State", null);
	private static final ElectionScope electionScope = new ElectionScope(new Long(2),electionType,state,country,null,null);
	
	private static final List<Party> parties = new ArrayList<Party> ();	
	private static final District  district = new District(new Long(19), "Nellore","Nellore",null,null,state,null,null,null,null,null,null,null);
	private static final List<Election> elections = new ArrayList<Election>();
 
	private static final List<Candidate> candidates = new ArrayList<Candidate>(); 
	
	private static final List<Constituency> constituencies = new ArrayList<Constituency>();	
	
	private static final List<Nomination> nominations = new ArrayList<Nomination>();
	private static final List<ConstituencyElection> constituencyElections = new ArrayList<ConstituencyElection>();
	private static final List<ConstituencyElectionResult> constituencyElectionResults = 
														new ArrayList<ConstituencyElectionResult>();
	
	private static final List<CandidateResult> candidateResults = new ArrayList<CandidateResult>();	
	private static final List<Party> allianceParties = new ArrayList<Party>();
	
	static{	
		Election election1 = new Election(new Long(3), electionScope, null, null, null, "2004", null,null,null,null,null);
		Election election2 = new Election(new Long(4), electionScope, null, null, null, "2009", null,null,null,null,null);
		elections.add(election1);
		elections.add(election2); 
		getParties();
		getConstituencies();
		
	}
	
	public static List<State> getStates(){
		List<State> list = new ArrayList<State>();
		list.add(state);
		return list;
	}
	public static State getState(){
		return state;
	}
	
	public Country getCountry(){
		return country;
	}
	
	public ElectionType getElectionType(){
		return electionType;
	}
	
	public static ElectionScope getElectionScope(){
		return electionScope;
	}
	
	public static List<MessageType> getAllMessageTypes(){
		List<MessageType> groupsInfo = new ArrayList<MessageType>();
		
		MessageType set1 = new MessageType();
		MessageType set2 = new MessageType();
		MessageType set3 = new MessageType();
		MessageType set4 = new MessageType();
		MessageType set5 = new MessageType();
		MessageType set6 = new MessageType();
		MessageType set7 = new MessageType();
		MessageType set8 = new MessageType();
		MessageType set9 = new MessageType();
				
		set1.setMessageTypeId(new Long(1));		
		set1.setMessageType("CONNECTED");
		set1.setDescription("connected");
		
		set2.setMessageTypeId( new Long(2));
		set2.setMessageType("DISCONNECTED");
		set2.setDescription("dis connected");
		
		set3.setMessageTypeId(new Long(3));
		set3.setMessageType("PENDING");
		set3.setDescription("pending of request");
		
		set4.setMessageTypeId(new Long(4));
		set4.setMessageType("FRIEND REQUEST");
		set4.setDescription("friend request acceptance");
		
		set5.setMessageTypeId(new Long(5));
		set5.setMessageType("COMMENTS");
		set5.setDescription("posting comments");
		
		set6.setMessageTypeId(new Long(6));
		set6.setMessageType("SCRAP");
		set6.setDescription("posting a scrap");
		
		set7.setMessageTypeId(new Long(7));
		set7.setMessageType("BLOCK");
		set7.setDescription("BLOCK");
		
		set8.setMessageTypeId(new Long(8));
		set8.setMessageType("UNBLOCK");
		set8.setDescription("UNBLOCK");
		
		set9.setMessageTypeId(new Long(9));
		set9.setMessageType("NOT CONNECTED");
		set9.setDescription("not connected");
		
		groupsInfo.add(set1);
		groupsInfo.add(set2);
		groupsInfo.add(set3);
		groupsInfo.add(set4);
		groupsInfo.add(set5);
		groupsInfo.add(set6);
		groupsInfo.add(set7);
		groupsInfo.add(set8);
		groupsInfo.add(set9);
		
		return groupsInfo;		
	}
	
	public static List getAnanymousUserIds(){
		List userIds = new ArrayList();
		
		Object[] set1 = new Object[3];
		Object[] set2 = new Object[3];
		Object[] set3 = new Object[3];
		
		set1[0] = new Long(1);		
		set1[1] = "1";
		set1[2] = "2";
		
		set2[0] = new Long(2);
		set2[1] = "1";
		set2[2] = "3";
		
		set3[0] = new Long(3);
		set3[1] = "4";
		set3[2] = "1";
		
		userIds.add(set1);
		userIds.add(set2);
		userIds.add(set3);
		
		return userIds;		
	}
	
	
	public static List getAllGroups(){
		List groupsInfo = new ArrayList();
		
		Object[] set1 = new Object[3];
		Object[] set2 = new Object[3];
		Object[] set3 = new Object[3];
		
		set1[0] = new Long(1);		
		set1[1] = "PRIVILEGED_ENTITLEMENTS";
		set2[0] = new Long(2);
		set2[1] = "DEFAULT_ENTITLEMENTS";		
		set3[0] = new Long(3);
		set3[1] = "DISTRICT_PAGE";
		
		groupsInfo.add(set1);
		groupsInfo.add(set2);
		groupsInfo.add(set3);
		
		return groupsInfo;		
	}
	
	public static List getAllEntitlements(){
		List entitlementsInfo = new ArrayList();
		
		Object[] set1 = new Object[3];
		Object[] set2 = new Object[3];
		Object[] set3 = new Object[3];
		Object[] set4 = new Object[3];
		Object[] set5 = new Object[3];
		
		set1[0] = new Long(1);		
		set1[1] = "CONSTITUENCY_PAGE";
		set2[0] = new Long(2);
		set2[1] = "PARTY_PERFORMANCE_REPORT";		
		set3[0] = new Long(3);
		set3[1] = "ELECTION_COMPARISION_REPORT";
		set4[0] = new Long(4);
		set4[1] = "PARTY_RESULTS_REPORT";		
		set5[0] = new Long(5);
		set5[1] = "PARTY_INFLUENCE_REPORT";
		
		entitlementsInfo.add(set1);
		entitlementsInfo.add(set2);
		entitlementsInfo.add(set3);
		entitlementsInfo.add(set4);
		entitlementsInfo.add(set5);
		
		return entitlementsInfo;		
	}
	
	public static List getAllUserGroups(){
		List groupsInfo = new ArrayList();
		
		Object[] set1 = new Object[3];
		Object[] set2 = new Object[3];
		
		set1[0] = new Long(1);		
		set1[1] = "NORMAL_USER";
		set2[0] = new Long(2);
		set2[1] = "BJP_PARTY";		
		
		
		groupsInfo.add(set1);
		groupsInfo.add(set2);
		
		return groupsInfo;		
	}  
	
	public static List getAllEntitlementGroupsBasedOnUserGroupId(Long userId){
		UserGroups userGroup1 = new UserGroups(new Long(1),"NORMAL_USER");	
		UserGroups userGroup2 = new UserGroups(new Long(2),"BJP_PARTY");
		
		GroupEntitlement groupEntitlement1 = new GroupEntitlement(new Long(1),"PRIVILEGED_ENTITLEMENTS");
		GroupEntitlement groupEntitlement2 = new GroupEntitlement(new Long(2),"DEFAULT_ENTITLEMENTS");
		GroupEntitlement groupEntitlement3 = new GroupEntitlement(new Long(3),"DISTRICT_PAGE");
		
		UserGroupEntitlement electionType1 = new UserGroupEntitlement(27l,userGroup1,groupEntitlement1);
		UserGroupEntitlement electionType2 = new UserGroupEntitlement(28l,userGroup1,groupEntitlement2);
		UserGroupEntitlement electionType3 = new UserGroupEntitlement(28l,userGroup1,groupEntitlement3);
		
		ElectionScope electionScope = new ElectionScope(new Long(1),electionType,state,country,null,null);
		List<ElectionScope> elecScopes = new ArrayList<ElectionScope>();
		elecScopes.add(electionScope);
		return elecScopes;
	} 
	
	public static List<ElectionScope> getElectionScopes(Long electionTypeId){
		ElectionType electionType = new ElectionType(electionTypeId,"Assembly","State", null);
		ElectionScope electionScope = new ElectionScope(new Long(1),electionType,state,country,null,null);
		List<ElectionScope> elecScopes = new ArrayList<ElectionScope>();
		elecScopes.add(electionScope);
		return elecScopes;
		
	}
	 
	public static List getVoterCastDetails(){
		List VoterCastInfo = new ArrayList();
		Object[] set1 = new Object[3];
		Object[] set2 = new Object[3];
		Object[] set3 = new Object[3];
		Object[] set4 = new Object[3];
		Object[] set5 = new Object[3];
		Object[] set6 = new Object[3];
		
		set1[0] = new Long(10);
		set1[1] = "m";
		set1[2] = "Balija";
		
		set2[0] = new Long(20);
		set2[1] = "f";
		set2[2] = "Balija";
		
		set3[0] = new Long(5);
		set3[1] = "m";
		set3[2] = "Chowdary";
		
		set4[0] = new Long(10);
		set4[1] = "f";
		set4[2] = "Chowdary";
		
		set5[0] = new Long(20);
		set5[1] = "f";
		set5[2] = "Reddy";
		
		set6[0] = new Long(20);
		set6[1] = "m";
		set6[2] = "Reddy";
		
		VoterCastInfo.add(set1);
		VoterCastInfo.add(set2);
		VoterCastInfo.add(set3);
		VoterCastInfo.add(set4);
		VoterCastInfo.add(set5);
		VoterCastInfo.add(set6);
	
		return VoterCastInfo;
	}
	
	public static List<Tehsil> getTehsils(){
		List<Tehsil> tehsils = new ArrayList<Tehsil>();
		Tehsil obj = new Tehsil();
		obj.setTehsilId(new Long(833));
		obj.setTehsilName("Kondapuram");
		obj.setDistrict(district);
		tehsils.add(obj);		
		return tehsils;
	}
	
	public static List<Party> getParties(){
		//Party dummy1 = new Party(new Long(1), "longName", "shortName",null, null, null,null, Set<Nomination> nominations);
		Party dummy1 = new Party(new Long(1), "Indian National Congress", "INC",null, null, null,null, null,null);
		Party dummy2 = new Party(new Long(2), "Bharatiya Janata Party", "BJP",null, null, null,null, null,null);
		Party dummy3 = new Party(new Long(3), "Bahujan Samaj Party", "BSP",null, null, null,null, null,null);

		Party dummy5 = new Party(new Long(5), "Communist Party of India (Marxist)", "CPM",null, null, null,null, null,null);
		Party dummy6 = new Party(new Long(6), "Samajwadi Party", "SP",null, null, null,null, null,null);
		Party dummy9 = new Party(new Long(9), "Praja Rajyam Party", "PRP",null, null, null,null, null,null);
		
		Party dummy10 = new Party(new Long(10), "Telangana Rashtra Samithi", "TRP",null, null, null,null, null,null);
		Party dummy11 = new Party(new Long(11), "Telugu Desam", "TDP",null, null, null,null, null,null);
		Party dummy12 = new Party(new Long(12), "Lok Satta Party", null,null, null, null,null, null,null);
		Party dummy13 = new Party(new Long(13), "Independent", "IND",null, null, null,null, null,null);
		
		Party dummy4 = new Party(new Long(4), "Communist Party of India", "CPI",null, null, null,null, null,null);
		Party dummy14 = new Party(new Long(14), "Pyramid Party of India", "PPOI",null, null, null,null, null,null);
		Party dummy17 = new Party(new Long(17), "Trilinga Praja Pragati Party", null,null, null, null,null, null,null);
		Party dummy23 = new Party(new Long(23), "Republican Party of India (A)", null,null, null, null,null, null,null);
		Party dummy44 = new Party(new Long(44), "B.C.United Front", null,null, null, null,null, null,null);
		Party dummy45 = new Party(new Long(45), "Rajyadhikara Party", null,null, null, null,null, null,null);
		Party dummy51 = new Party(new Long(51), "Bharatiya Jan Shakti", null,null, null, null,null, null,null);
		Party dummy54 = new Party(new Long(54), "Bharatiya Rashtravadi Paksha", "BRP",null, null, null,null, null,null);
		Party dummy62 = new Party(new Long(62), "NTR Telugu Desam Party (Lakshmi Parvathi)", "NTRTDP(LP)",null, null, null,null, null,null);
		
		parties.add(dummy1);	parties.add(dummy2);	parties.add(dummy3);	parties.add(dummy5);	
		parties.add(dummy6);	parties.add(dummy9);	parties.add(dummy10);	parties.add(dummy11);
		parties.add(dummy12);	parties.add(dummy13);	parties.add(dummy14);		parties.add(dummy17);
		parties.add(dummy23);	parties.add(dummy44);	parties.add(dummy45);		parties.add(dummy51);
		parties.add(dummy54);	parties.add(dummy62);   parties.add(dummy4);
		
		return parties;
	}
	
	private static Party getParty(Long id){
		for(Party party: parties){
			if(party.getPartyId().equals(id))
				return party;
		}
		return null;
	}
	
	public static District getDistrict(){
		return district;
	}
	
	public static List<Election> getElections(){
	
		return elections;
	}
	private static Election getElection(Long id){
		for(Election election:elections){
			if(election.getElectionId().equals(id))
				return election;
		}
		return null;
	}
	public static List<Constituency> getConstituencies() {
		if(constituencies.size()>0)
			return constituencies;
		Constituency constituency1 = new Constituency(new Long(1),district, electionScope,state,"Kavali",null,null,country.getCountryId(),null,null,null,null);
		Constituency constituency2 = new Constituency(new Long(2),district, electionScope,state,"Atmakuru",null,null,country.getCountryId(),null,null,null,null);
		Constituency constituency3 = new Constituency(new Long(3),district, electionScope,state,"Kovuru",null,null,country.getCountryId(),null,null,null,null);
		Constituency constituency4= new Constituency(new Long(4),district, electionScope,state,"Nellore City",null,null,country.getCountryId(),null,null,null,null);
		Constituency constituency5 = new Constituency(new Long(5),district, electionScope,state,"Nellore Rural",null,null,country.getCountryId(),null,null,null,null);
		Constituency constituency6 = new Constituency(new Long(6),district, electionScope,state,"Sarvepalli",null,null,country.getCountryId(),null,null,null,null);
		Constituency constituency7 = new Constituency(new Long(7),district, electionScope,state,"Gudur",null,null,country.getCountryId(),null,null,null,null);
		Constituency constituency8 = new Constituency(new Long(8),district, electionScope,state,"Sullurpeta",null,null,country.getCountryId(),null,null,null,null);
		Constituency constituency9 = new Constituency(new Long(9),district, electionScope,state,"Venkatagiri",null,null,country.getCountryId(),null,null,null,null);
		Constituency constituency10 = new Constituency(new Long(10),district, electionScope,state,"Udayagiri",null,null,country.getCountryId(),null,null,null,null);
		Constituency constituency11 = new Constituency(new Long(11),district, electionScope,state,"Rapur",null,null,country.getCountryId(),null,null,null,null);
		Constituency constituency12 = new Constituency(new Long(12),district, electionScope,state,"Allur",null,null,country.getCountryId(),null,null,null,null);
		Constituency constituency13 = new Constituency(new Long(13),district, electionScope,state,"Nellore",null,null,country.getCountryId(),null,null,null,null);
		/*Constituency c1 = new Constituency(Long constituencyId, District district,
				ElectionScope electionScope, State state, String name,
				Date startDate, Date deformDate, Long countryId,
				Set<ConstituencyElection> constituencyElections)*/
		constituencies.add(constituency1);
		constituencies.add(constituency2);
		constituencies.add(constituency3);
		constituencies.add(constituency4);
		constituencies.add(constituency5);
		constituencies.add(constituency6);
		constituencies.add(constituency7);
		constituencies.add(constituency8);
		constituencies.add(constituency9);
		constituencies.add(constituency10);
		constituencies.add(constituency11);
		constituencies.add(constituency12);
		constituencies.add(constituency13);
		 
		getConstituencyElections();
		constituency1.setConstituencyElections(getConstituencyElectionList(new Long(1)));
		constituency2.setConstituencyElections(getConstituencyElectionList(new Long(2)));
		constituency3.setConstituencyElections(getConstituencyElectionList(new Long(3)));
		constituency4.setConstituencyElections(getConstituencyElectionList(new Long(4)));
		constituency5.setConstituencyElections(getConstituencyElectionList(new Long(5)));
		constituency6.setConstituencyElections(getConstituencyElectionList(new Long(6)));
		constituency7.setConstituencyElections(getConstituencyElectionList(new Long(7)));
		constituency8.setConstituencyElections(getConstituencyElectionList(new Long(8)));
		constituency9.setConstituencyElections(getConstituencyElectionList(new Long(9)));
		constituency10.setConstituencyElections(getConstituencyElectionList(new Long(10)));
		constituency11.setConstituencyElections(getConstituencyElectionList(new Long(11)));
		constituency12.setConstituencyElections(getConstituencyElectionList(new Long(12)));
		constituency13.setConstituencyElections(getConstituencyElectionList(new Long(13)));
		//getConstituencyElectionResults();
		/*getCandidates();
		//getCandidateResults();
		getNominations();*/
		return constituencies;
	}
	

	private static Constituency getConstituency(Long id){
		for(Constituency constituency:constituencies){
			if(constituency.getConstituencyId().equals(id))
				return constituency;
		}
		return null;
	}
	
	
	/*public List<Nomination> getNominations() {
		return nominations;
	}*/
	public static List<ConstituencyElection> getConstituencyElections() {
		getElections();
		ConstituencyElection constiElection1 = new ConstituencyElection(new Long(1),getElection(new Long(4)),getConstituency(new Long(1)),null,getNominationsList(new Long(1)),getConstituencyElectionResult(new Long(1)), null);
		ConstituencyElection constiElection2 = new ConstituencyElection(new Long(2),getElection(new Long(4)),getConstituency(new Long(2)),null,getNominationsList(new Long(2)),getConstituencyElectionResult(new Long(2)), null);
		ConstituencyElection constiElection3 = new ConstituencyElection(new Long(3),getElection(new Long(4)),getConstituency(new Long(3)),null,getNominationsList(new Long(3)),getConstituencyElectionResult(new Long(3)), null);
		ConstituencyElection constiElection4 = new ConstituencyElection(new Long(4),getElection(new Long(4)),getConstituency(new Long(4)),null,getNominationsList(new Long(4)),getConstituencyElectionResult(new Long(4)), null);
		ConstituencyElection constiElection5 = new ConstituencyElection(new Long(5),getElection(new Long(4)),getConstituency(new Long(5)),null,getNominationsList(new Long(5)),getConstituencyElectionResult(new Long(5)), null);
		ConstituencyElection constiElection6 = new ConstituencyElection(new Long(6),getElection(new Long(4)),getConstituency(new Long(6)),null,getNominationsList(new Long(6)),getConstituencyElectionResult(new Long(6)), null);
		ConstituencyElection constiElection7 = new ConstituencyElection(new Long(7),getElection(new Long(4)),getConstituency(new Long(7)),null,getNominationsList(new Long(7)),getConstituencyElectionResult(new Long(7)), null);
		ConstituencyElection constiElection8 = new ConstituencyElection(new Long(8),getElection(new Long(4)),getConstituency(new Long(8)),null,getNominationsList(new Long(8)),getConstituencyElectionResult(new Long(8)), null);
		ConstituencyElection constiElection9 = new ConstituencyElection(new Long(9),getElection(new Long(4)),getConstituency(new Long(9)),null,getNominationsList(new Long(9)),getConstituencyElectionResult(new Long(9)), null);
		ConstituencyElection constiElection10 = new ConstituencyElection(new Long(10),getElection(new Long(4)),getConstituency(new Long(10)),null,getNominationsList(new Long(10)),getConstituencyElectionResult(new Long(10)), null);
		ConstituencyElection constiElection11 = new ConstituencyElection(new Long(11),getElection(new Long(3)),getConstituency(new Long(1)),null,getNominationsList(new Long(11)),getConstituencyElectionResult(new Long(11)), null);
		ConstituencyElection constiElection12 = new ConstituencyElection(new Long(12),getElection(new Long(3)),getConstituency(new Long(2)),null,getNominationsList(new Long(12)),getConstituencyElectionResult(new Long(12)), null);
		ConstituencyElection constiElection13 = new ConstituencyElection(new Long(13),getElection(new Long(3)),getConstituency(new Long(3)),null,getNominationsList(new Long(13)),getConstituencyElectionResult(new Long(13)), null);
		ConstituencyElection constiElection14 = new ConstituencyElection(new Long(14),getElection(new Long(3)),getConstituency(new Long(6)),null,getNominationsList(new Long(14)),getConstituencyElectionResult(new Long(14)), null);
		ConstituencyElection constiElection15 = new ConstituencyElection(new Long(15),getElection(new Long(3)),getConstituency(new Long(7)),null,getNominationsList(new Long(15)),getConstituencyElectionResult(new Long(15)), null);
		ConstituencyElection constiElection16 = new ConstituencyElection(new Long(16),getElection(new Long(3)),getConstituency(new Long(8)),null,getNominationsList(new Long(16)),getConstituencyElectionResult(new Long(16)), null);
		ConstituencyElection constiElection17 = new ConstituencyElection(new Long(17),getElection(new Long(3)),getConstituency(new Long(9)),null,getNominationsList(new Long(17)),getConstituencyElectionResult(new Long(17)), null);
		ConstituencyElection constiElection18 = new ConstituencyElection(new Long(18),getElection(new Long(3)),getConstituency(new Long(10)),null,getNominationsList(new Long(18)),getConstituencyElectionResult(new Long(18)), null);
		ConstituencyElection constiElection19 = new ConstituencyElection(new Long(19),getElection(new Long(3)),getConstituency(new Long(11)),null,getNominationsList(new Long(19)),getConstituencyElectionResult(new Long(19)), null);
		ConstituencyElection constiElection20 = new ConstituencyElection(new Long(20),getElection(new Long(3)),getConstituency(new Long(12)),null,getNominationsList(new Long(20)),getConstituencyElectionResult(new Long(20)), null);
		ConstituencyElection constiElection21 = new ConstituencyElection(new Long(21),getElection(new Long(3)),getConstituency(new Long(13)),null,getNominationsList(new Long(21)),getConstituencyElectionResult(new Long(21)), null);
		/*ConstituencyElection(Long constiElecId, Election election,
				Constituency constituency, Date electionDate,
				Set<Nomination> nominations,
				ConstituencyElectionResult constituencyElectionResult)*/
		constituencyElections.add(constiElection1);
		constituencyElections.add(constiElection2);
		constituencyElections.add(constiElection3);
		constituencyElections.add(constiElection4);
		constituencyElections.add(constiElection5);
		constituencyElections.add(constiElection6);
		constituencyElections.add(constiElection7);
		constituencyElections.add(constiElection8);
		constituencyElections.add(constiElection9);
		constituencyElections.add(constiElection10);
		constituencyElections.add(constiElection11);
		constituencyElections.add(constiElection12);
		constituencyElections.add(constiElection13);
		constituencyElections.add(constiElection14);
		constituencyElections.add(constiElection15);
		constituencyElections.add(constiElection16);
		constituencyElections.add(constiElection17);
		constituencyElections.add(constiElection18);
		constituencyElections.add(constiElection19);
		constituencyElections.add(constiElection20);
		constituencyElections.add(constiElection21);
		getConstituencyElectionResults();
		constiElection1.setConstituencyElectionResult(getConstituencyElectionResult(new Long(1)));
		constiElection2.setConstituencyElectionResult(getConstituencyElectionResult(new Long(2)));
		constiElection3.setConstituencyElectionResult(getConstituencyElectionResult(new Long(3)));
		constiElection4.setConstituencyElectionResult(getConstituencyElectionResult(new Long(4)));
		constiElection5.setConstituencyElectionResult(getConstituencyElectionResult(new Long(5)));
		constiElection6.setConstituencyElectionResult(getConstituencyElectionResult(new Long(6)));
		constiElection7.setConstituencyElectionResult(getConstituencyElectionResult(new Long(7)));
		constiElection8.setConstituencyElectionResult(getConstituencyElectionResult(new Long(8)));
		constiElection9.setConstituencyElectionResult(getConstituencyElectionResult(new Long(9)));
		constiElection10.setConstituencyElectionResult(getConstituencyElectionResult(new Long(10)));
		constiElection11.setConstituencyElectionResult(getConstituencyElectionResult(new Long(11)));
		constiElection12.setConstituencyElectionResult(getConstituencyElectionResult(new Long(12)));
		constiElection13.setConstituencyElectionResult(getConstituencyElectionResult(new Long(13)));
		constiElection14.setConstituencyElectionResult(getConstituencyElectionResult(new Long(14)));
		constiElection15.setConstituencyElectionResult(getConstituencyElectionResult(new Long(15)));
		constiElection16.setConstituencyElectionResult(getConstituencyElectionResult(new Long(16)));
		constiElection17.setConstituencyElectionResult(getConstituencyElectionResult(new Long(17)));
		constiElection18.setConstituencyElectionResult(getConstituencyElectionResult(new Long(18)));
		constiElection19.setConstituencyElectionResult(getConstituencyElectionResult(new Long(19)));
		constiElection20.setConstituencyElectionResult(getConstituencyElectionResult(new Long(20)));
		constiElection21.setConstituencyElectionResult(getConstituencyElectionResult(new Long(21)));
		 
		getNominations();
		constiElection1.setNominations(getNominationsList(new Long(1)));
		constiElection2.setNominations(getNominationsList(new Long(2)));
		constiElection3.setNominations(getNominationsList(new Long(3)));
		constiElection4.setNominations(getNominationsList(new Long(4)));
		constiElection5.setNominations(getNominationsList(new Long(5)));
		constiElection6.setNominations(getNominationsList(new Long(6)));
		constiElection7.setNominations(getNominationsList(new Long(7)));
		constiElection8.setNominations(getNominationsList(new Long(8)));
		constiElection9.setNominations(getNominationsList(new Long(9)));
		constiElection10.setNominations(getNominationsList(new Long(10)));
		constiElection11.setNominations(getNominationsList(new Long(11)));
		constiElection12.setNominations(getNominationsList(new Long(12)));
		constiElection13.setNominations(getNominationsList(new Long(13)));
		constiElection14.setNominations(getNominationsList(new Long(14)));
		constiElection15.setNominations(getNominationsList(new Long(15)));
		constiElection16.setNominations(getNominationsList(new Long(16)));
		constiElection17.setNominations(getNominationsList(new Long(17)));
		constiElection18.setNominations(getNominationsList(new Long(18)));
		constiElection19.setNominations(getNominationsList(new Long(19)));
		constiElection20.setNominations(getNominationsList(new Long(20)));
		constiElection21.setNominations(getNominationsList(new Long(21)));
		return constituencyElections;
	}
	

	public static ConstituencyElection getConstituencyElection(Long id){
		for(ConstituencyElection constituencyElection:constituencyElections){
			if(constituencyElection.getConstiElecId().equals(id))
				return constituencyElection;
		}
		return null;
		
	}
	
	private static Set<ConstituencyElection> getConstituencyElectionList(Long constituencyID){
		Set<ConstituencyElection> list = new HashSet<ConstituencyElection> ();
		for(ConstituencyElection constituencyElection:constituencyElections){
			if(constituencyElection.getConstituency().getConstituencyId().equals(constituencyID))
				list.add(constituencyElection);
		}
		return list;
		
	}
		
	public static List<ConstituencyElectionResult> getConstituencyElectionResults() {
		ConstituencyElectionResult result1 = new ConstituencyElectionResult(new Long(1), getConstituencyElection(new Long(1)),
				new Double(154795), new Double(154690),new Double(105), new Double(0),new Double(218392), new Double(8));
		ConstituencyElectionResult result2 = new ConstituencyElectionResult(new Long(2), getConstituencyElection(new Long(2)),
				new Double(148235), new Double(148137),new Double(98), new Double(0),new Double(189744), new Double(8));
		ConstituencyElectionResult result3 = new ConstituencyElectionResult(new Long(3), getConstituencyElection(new Long(3)),
				new Double(169090), new Double(169009),new Double(81), new Double(0),new Double(227019), new Double(0));
		ConstituencyElectionResult result4 = new ConstituencyElectionResult(new Long(4), getConstituencyElection(new Long(4)),
				new Double(106457), new Double(106389),new Double(68), new Double(0),new Double(199718), new Double(19));
		ConstituencyElectionResult result5 = new ConstituencyElectionResult(new Long(5), getConstituencyElection(new Long(5)),
				new Double(124393), new Double(124110),new Double(283), new Double(0),new Double(226860), new Double(0));
		ConstituencyElectionResult result6 = new ConstituencyElectionResult(new Long(6), getConstituencyElection(new Long(6)),
				new Double(155941), new Double(155930),new Double(11), new Double(0),new Double(195431), new Double(0));
		ConstituencyElectionResult result7 = new ConstituencyElectionResult(new Long(7), getConstituencyElection(new Long(7)),
				new Double(152885), new Double(152836),new Double(49), new Double(0),new Double(210646), new Double(0));
		ConstituencyElectionResult result8 = new ConstituencyElectionResult(new Long(8), getConstituencyElection(new Long(8)),
				new Double(159872), new Double(159872),new Double(0), new Double(0),new Double(212082), new Double(0));
		ConstituencyElectionResult result9 = new ConstituencyElectionResult(new Long(9), getConstituencyElection(new Long(9)),
				new Double(159924), new Double(159851),new Double(73), new Double(0),new Double(208287), new Double(5));
		ConstituencyElectionResult result10 = new ConstituencyElectionResult(new Long(10), getConstituencyElection(new Long(10)),
				new Double(147807), new Double(147807),new Double(0), new Double(0),new Double(197654), new Double(0));
		

		ConstituencyElectionResult result11 = new ConstituencyElectionResult(new Long(11), getConstituencyElection(
				new Long(18)),new Double(120191), new Double(89319),new Double(4), new Double(0),new Double(89323), new Double(4));
		ConstituencyElectionResult result12 = new ConstituencyElectionResult(new Long(12), getConstituencyElection(
				new Long(11)),new Double(163577), new Double(118179),new Double(6), new Double(0),new Double(118185), new Double(5));
		ConstituencyElectionResult result13 = new ConstituencyElectionResult(new Long(13), getConstituencyElection(
				new Long(20)),new Double(138466), new Double(110083),new Double(18), new Double(0),new Double(110101), new Double(4));
		ConstituencyElectionResult result14 = new ConstituencyElectionResult(new Long(14), getConstituencyElection(
				new Long(13)),new Double(141031), new Double(105172),new Double(4), new Double(0),new Double(105176), new Double(6));
		ConstituencyElectionResult result15 = new ConstituencyElectionResult(new Long(15), getConstituencyElection(
				new Long(12)),new Double(147793), new Double(119562),new Double(20), new Double(0),new Double(119582), new Double(1));
		ConstituencyElectionResult result16 = new ConstituencyElectionResult(new Long(16), getConstituencyElection(
				new Long(19)),new Double(176019), new Double(132912),new Double(9), new Double(0),new Double(132921), new Double(8));
		ConstituencyElectionResult result17 = new ConstituencyElectionResult(new Long(17), getConstituencyElection(
				new Long(21)),new Double(218351), new Double(115869),new Double(190), new Double(0),new Double(116059), new Double(3));
		ConstituencyElectionResult result18 = new ConstituencyElectionResult(new Long(18), getConstituencyElection(
				new Long(14)),new Double(164184), new Double(133146),new Double(1), new Double(0),new Double(133147), new Double(6));
		ConstituencyElectionResult result19 = new ConstituencyElectionResult(new Long(19), getConstituencyElection(
				new Long(15)),new Double(167337), new Double(120132),new Double(8), new Double(0),new Double(120140), new Double(0));
		ConstituencyElectionResult result20 = new ConstituencyElectionResult(new Long(20), getConstituencyElection(
				new Long(16)),new Double(154216), new Double(113894),new Double(13), new Double(0),new Double(113907), new Double(0));
		ConstituencyElectionResult result21 = new ConstituencyElectionResult(new Long(21), getConstituencyElection(
				new Long(17)),new Double(147438), new Double(116450),new Double(1), new Double(0),new Double(116451), new Double(6));

		
		/*(Long constiElecResultId,
				ConstituencyElection constituencyElection, Double totalVotes,
				Double validVotes, Double rejectedVotes, Double missingVotes,
				Double totalVotesPolled, Double tenderedVotes)
				*/
		constituencyElectionResults.add(result1);
		constituencyElectionResults.add(result2);
		constituencyElectionResults.add(result3);
		constituencyElectionResults.add(result4);
		constituencyElectionResults.add(result5);
		constituencyElectionResults.add(result6);
		constituencyElectionResults.add(result7);
		constituencyElectionResults.add(result8);
		constituencyElectionResults.add(result9);
		constituencyElectionResults.add(result10);
		constituencyElectionResults.add(result11);
		constituencyElectionResults.add(result12);
		constituencyElectionResults.add(result13);
		constituencyElectionResults.add(result14);
		constituencyElectionResults.add(result15);
		constituencyElectionResults.add(result16);
		constituencyElectionResults.add(result17);
		constituencyElectionResults.add(result18);
		constituencyElectionResults.add(result19);
		constituencyElectionResults.add(result20);
		constituencyElectionResults.add(result21);
		return constituencyElectionResults;
	}

	private static ConstituencyElectionResult getConstituencyElectionResult(Long id){
		for(ConstituencyElectionResult constituencyElectionResult:constituencyElectionResults){
			if(constituencyElectionResult.getConstiElecResultId().equals(id))
				return constituencyElectionResult;
		}
		return null;
		
	}

	private static Nomination getNomination(Long id){
		for(Nomination nomination : nominations){
			if(nomination.getNominationId().equals(id))
				return nomination;
		}
		return null;
	}
	
	private static Set<Nomination> getNominationsList(Long constituencyElectionID){
		Set<Nomination> list = new HashSet<Nomination>();
		for(Nomination nomination : nominations){
			if(nomination.getConstituencyElection().getConstiElecId().equals(constituencyElectionID))
				list.add(nomination);
		}
		return list;
	}
	
	
	public static List<CandidateResult> getCandidateResults()
	{
		/*CandidateResult(Long candidateResultId, Nomination nomination,
				Double votesEarned, Long rank)
				*/
		CandidateResult cr1 = new CandidateResult(new Long(1),null,new Double(69219),new Long(1) );
		CandidateResult cr2 = new CandidateResult(new Long(2),null,new Double(50192),new Long(2) );
		CandidateResult cr3 = new CandidateResult(new Long(3),null,new Double(27352),new Long(3) );
		CandidateResult cr4 = new CandidateResult(new Long(4),null,new Double(3314),new Long(4) );
		CandidateResult cr5 = new CandidateResult(new Long(5),null,new Double(1133),new Long(5) );
		CandidateResult cr6 = new CandidateResult(new Long(6),null,new Double(1108),new Long(6) );
		CandidateResult cr7 = new CandidateResult(new Long(7),null,new Double(866),new Long(7) );
		CandidateResult cr8 = new CandidateResult(new Long(8),null,new Double(716),new Long(8) );
		CandidateResult cr9 = new CandidateResult(new Long(9),null,new Double(415),new Long(9) );
		CandidateResult cr10 = new CandidateResult(new Long(10),null,new Double(375),new Long(10) );
		CandidateResult cr11 = new CandidateResult(new Long(11),null,new Double(76907),new Long(1) );
		CandidateResult cr12 = new CandidateResult(new Long(12),null,new Double(58263),new Long(2) );
		CandidateResult cr13 = new CandidateResult(new Long(13),null,new Double(8772),new Long(3) );
		CandidateResult cr14 = new CandidateResult(new Long(14),null,new Double(1604),new Long(4) );
		CandidateResult cr15 = new CandidateResult(new Long(15),null,new Double(713),new Long(5) );
		CandidateResult cr16 = new CandidateResult(new Long(16),null,new Double(618),new Long(6) );
		CandidateResult cr17 = new CandidateResult(new Long(17),null,new Double(352),new Long(7) );
		CandidateResult cr18 = new CandidateResult(new Long(18),null,new Double(299),new Long(8) );
		CandidateResult cr19 = new CandidateResult(new Long(19),null,new Double(221),new Long(9) );
		CandidateResult cr20 = new CandidateResult(new Long(20),null,new Double(202),new Long(10) );
		CandidateResult cr21 = new CandidateResult(new Long(21),null,new Double(186),new Long(11) );
		CandidateResult cr22 = new CandidateResult(new Long(22),null,new Double(73212),new Long(1) );
		CandidateResult cr23 = new CandidateResult(new Long(23),null,new Double(65768),new Long(2) );
		CandidateResult cr24 = new CandidateResult(new Long(24),null,new Double(22624),new Long(3) );
		CandidateResult cr25 = new CandidateResult(new Long(25),null,new Double(1614),new Long(4) );
		CandidateResult cr26 = new CandidateResult(new Long(26),null,new Double(1379),new Long(5) );
		CandidateResult cr27 = new CandidateResult(new Long(27),null,new Double(1207),new Long(6) );
		CandidateResult cr28 = new CandidateResult(new Long(28),null,new Double(893),new Long(7) );
		CandidateResult cr29 = new CandidateResult(new Long(29),null,new Double(869),new Long(8) );
		CandidateResult cr30 = new CandidateResult(new Long(30),null,new Double(671),new Long(9) );
		CandidateResult cr31 = new CandidateResult(new Long(31),null,new Double(390),new Long(10) );
		CandidateResult cr32 = new CandidateResult(new Long(32),null,new Double(382),new Long(11) );
		CandidateResult cr33 = new CandidateResult(new Long(33),null,new Double(36103),new Long(1) );
		CandidateResult cr34 = new CandidateResult(new Long(34),null,new Double(36013),new Long(2) );
		CandidateResult cr35 = new CandidateResult(new Long(35),null,new Double(26173),new Long(3) );
		CandidateResult cr36 = new CandidateResult(new Long(36),null,new Double(3670),new Long(4) );
		CandidateResult cr37 = new CandidateResult(new Long(37),null,new Double(1950),new Long(5) );
		CandidateResult cr38 = new CandidateResult(new Long(38),null,new Double(853),new Long(6) );
		CandidateResult cr39 = new CandidateResult(new Long(39),null,new Double(427),new Long(7) );
		CandidateResult cr40 = new CandidateResult(new Long(40),null,new Double(372),new Long(8) );
		CandidateResult cr41 = new CandidateResult(new Long(41),null,new Double(320),new Long(9) );
		CandidateResult cr42 = new CandidateResult(new Long(42),null,new Double(264),new Long(10) );
		CandidateResult cr43 = new CandidateResult(new Long(43),null,new Double(148),new Long(11) );
		CandidateResult cr44 = new CandidateResult(new Long(44),null,new Double(96),new Long(12) );
		CandidateResult cr45 = new CandidateResult(new Long(45),null,new Double(46941),new Long(1) );
		CandidateResult cr46 = new CandidateResult(new Long(46),null,new Double(43810),new Long(2) );
		CandidateResult cr47 = new CandidateResult(new Long(47),null,new Double(23143),new Long(3) );
		CandidateResult cr48 = new CandidateResult(new Long(48),null,new Double(3460),new Long(4) );
		CandidateResult cr49 = new CandidateResult(new Long(49),null,new Double(2969),new Long(5) );
		CandidateResult cr50 = new CandidateResult(new Long(50),null,new Double(1400),new Long(6) );
		CandidateResult cr51 = new CandidateResult(new Long(51),null,new Double(925),new Long(7) );
		CandidateResult cr52 = new CandidateResult(new Long(52),null,new Double(568),new Long(8) );
		CandidateResult cr53 = new CandidateResult(new Long(53),null,new Double(403),new Long(9) );
		CandidateResult cr54 = new CandidateResult(new Long(54),null,new Double(247),new Long(10) );
		CandidateResult cr55 = new CandidateResult(new Long(55),null,new Double(244),new Long(11) );
		CandidateResult cr56 = new CandidateResult(new Long(56),null,new Double(73760),new Long(1) );
		CandidateResult cr57 = new CandidateResult(new Long(57),null,new Double(63476),new Long(2) );
		//new candidateResult

		CandidateResult cr58 = new CandidateResult(new Long(58),null,new Double(11553),new Long(3) );
		CandidateResult cr59 = new CandidateResult(new Long(59),null,new Double(1895),new Long(4) );
		CandidateResult cr60 = new CandidateResult(new Long(60),null,new Double(1854),new Long(5) );
		CandidateResult cr61 = new CandidateResult(new Long(61),null,new Double(818),new Long(6) );
		CandidateResult cr62 = new CandidateResult(new Long(62),null,new Double(781),new Long(7) );
		CandidateResult cr63 = new CandidateResult(new Long(63),null,new Double(712),new Long(8) );
		CandidateResult cr64 = new CandidateResult(new Long(64),null,new Double(335),new Long(9) );
		CandidateResult cr65 = new CandidateResult(new Long(65),null,new Double(312),new Long(10) );
		CandidateResult cr66 = new CandidateResult(new Long(66),null,new Double(277),new Long(11) );
		CandidateResult cr67 = new CandidateResult(new Long(67),null,new Double(157),new Long(12) );
		CandidateResult cr68 = new CandidateResult(new Long(68),null,new Double(64330),new Long(1) );
		CandidateResult cr69 = new CandidateResult(new Long(69),null,new Double(53092),new Long(2) );
		CandidateResult cr70 = new CandidateResult(new Long(70),null,new Double(27318),new Long(3) );
		CandidateResult cr71 = new CandidateResult(new Long(71),null,new Double(2878),new Long(4) );
		CandidateResult cr72 = new CandidateResult(new Long(72),null,new Double(2710),new Long(5) );
		CandidateResult cr73 = new CandidateResult(new Long(73),null,new Double(1090),new Long(6) );
		CandidateResult cr74 = new CandidateResult(new Long(74),null,new Double(829),new Long(7) );
		CandidateResult cr75 = new CandidateResult(new Long(75),null,new Double(589),new Long(8) );
		CandidateResult cr76 = new CandidateResult(new Long(76),null,new Double(66089),new Long(1) );
		CandidateResult cr77 = new CandidateResult(new Long(77),null,new Double(60722),new Long(2) );
		CandidateResult cr78 = new CandidateResult(new Long(78),null,new Double(24832),new Long(3) );
		CandidateResult cr79 = new CandidateResult(new Long(79),null,new Double(3559),new Long(4) );
		CandidateResult cr80 = new CandidateResult(new Long(80),null,new Double(1746),new Long(5) );
		CandidateResult cr81 = new CandidateResult(new Long(81),null,new Double(1002),new Long(6) );
		CandidateResult cr82 = new CandidateResult(new Long(82),null,new Double(876),new Long(7) );
		CandidateResult cr83 = new CandidateResult(new Long(83),null,new Double(671),new Long(8) );
		CandidateResult cr84 = new CandidateResult(new Long(84),null,new Double(375),new Long(9) );
		CandidateResult cr85 = new CandidateResult(new Long(85),null,new Double(69731),new Long(1) );
		CandidateResult cr86 = new CandidateResult(new Long(86),null,new Double(62965),new Long(2) );
		CandidateResult cr87 = new CandidateResult(new Long(87),null,new Double(19970),new Long(3) );
		CandidateResult cr88 = new CandidateResult(new Long(88),null,new Double(1898),new Long(4) );
		CandidateResult cr89 = new CandidateResult(new Long(89),null,new Double(1520),new Long(5) );
		CandidateResult cr90 = new CandidateResult(new Long(90),null,new Double(1361),new Long(6) );
		CandidateResult cr91 = new CandidateResult(new Long(91),null,new Double(1240),new Long(7) );
		CandidateResult cr92 = new CandidateResult(new Long(92),null,new Double(697),new Long(8) );
		CandidateResult cr93 = new CandidateResult(new Long(93),null,new Double(469),new Long(9) );
		CandidateResult cr94 = new CandidateResult(new Long(94),null,new Double(55870),new Long(2) );
		CandidateResult cr95 = new CandidateResult(new Long(95),null,new Double(1308),new Long(6) );
		CandidateResult cr96 = new CandidateResult(new Long(96),null,new Double(69352),new Long(1) );
		CandidateResult cr97 = new CandidateResult(new Long(97),null,new Double(1747),new Long(5) );
		CandidateResult cr98 = new CandidateResult(new Long(98),null,new Double(326),new Long(9) );
		CandidateResult cr99 = new CandidateResult(new Long(99),null,new Double(540),new Long(7) );
		CandidateResult cr100 = new CandidateResult(new Long(100),null,new Double(14512),new Long(3) );
		

		/*CandidateResult(Long candidateResultId, Nomination nomination,
				Double votesEarned, Long rank)
				*/
		

		CandidateResult cr101 = new CandidateResult(new Long(101),null,new Double(286),new Long(12) );
		CandidateResult cr102 = new CandidateResult(new Long(102),null,new Double(1827),new Long(4) );
		CandidateResult cr103 = new CandidateResult(new Long(103),null,new Double(309),new Long(11) );
		CandidateResult cr104 = new CandidateResult(new Long(104),null,new Double(240),new Long(13) );
		CandidateResult cr105 = new CandidateResult(new Long(105),null,new Double(388),new Long(9) );
		CandidateResult cr106 = new CandidateResult(new Long(106),null,new Double(516),new Long(8) );
		CandidateResult cr107 = new CandidateResult(new Long(107),null,new Double(586),new Long(6) );
		CandidateResult cr108 = new CandidateResult(new Long(108),null,new Double(55076),new Long(1) );
		CandidateResult cr109 = new CandidateResult(new Long(109),null,new Double(32001),new Long(2) );
		CandidateResult cr110 = new CandidateResult(new Long(110),null,new Double(821),new Long(3) );
		CandidateResult cr111 = new CandidateResult(new Long(111),null,new Double(343),new Long(4) );
		CandidateResult cr112 = new CandidateResult(new Long(112),null,new Double(175),new Long(5) );
		CandidateResult cr113 = new CandidateResult(new Long(113),null,new Double(146),new Long(6) );
		CandidateResult cr114 = new CandidateResult(new Long(114),null,new Double(145),new Long(7) );
		CandidateResult cr115 = new CandidateResult(new Long(115),null,new Double(135),new Long(8) );
		CandidateResult cr116 = new CandidateResult(new Long(116),null,new Double(128),new Long(9) );
		CandidateResult cr117 = new CandidateResult(new Long(117),null,new Double(111),new Long(10) );
		CandidateResult cr118 = new CandidateResult(new Long(118),null,new Double(108),new Long(11) );
		CandidateResult cr119 = new CandidateResult(new Long(119),null,new Double(72),new Long(12) );
		CandidateResult cr120 = new CandidateResult(new Long(120),null,new Double(58),new Long(13) );
		// t0day started 1st Sep
		/*CandidateResult(Long candidateResultId, Nomination nomination,
		Double votesEarned, Long rank)
		*/
		CandidateResult cr121 = new CandidateResult(new Long(121),null,new Double(68167),new Long(1) );
		CandidateResult cr122 = new CandidateResult(new Long(122),null,new Double(47018),new Long(2) );
		CandidateResult cr123 = new CandidateResult(new Long(123),null,new Double(1006),new Long(3) );
		CandidateResult cr124 = new CandidateResult(new Long(124),null,new Double(797),new Long(4) );
		CandidateResult cr125 = new CandidateResult(new Long(125),null,new Double(403),new Long(5) );
		CandidateResult cr126 = new CandidateResult(new Long(126),null,new Double(60760),new Long(1) );
		CandidateResult cr127 = new CandidateResult(new Long(127),null,new Double(47388),new Long(2) );
		CandidateResult cr128 = new CandidateResult(new Long(128),null,new Double(927),new Long(3) );
		CandidateResult cr129 = new CandidateResult(new Long(129),null,new Double(659),new Long(4) );
		CandidateResult cr130 = new CandidateResult(new Long(130),null,new Double(349),new Long(5) );
		CandidateResult cr131 = new CandidateResult(new Long(131),null,new Double(45270),new Long(1) );
		CandidateResult cr132 = new CandidateResult(new Long(132),null,new Double(44790),new Long(2) );
		CandidateResult cr133 = new CandidateResult(new Long(133),null,new Double(10769),new Long(3) );
		CandidateResult cr134 = new CandidateResult(new Long(134),null,new Double(2126),new Long(4) );
		CandidateResult cr135 = new CandidateResult(new Long(135),null,new Double(590),new Long(5) );
		CandidateResult cr136 = new CandidateResult(new Long(136),null,new Double(477),new Long(6) );
		CandidateResult cr137 = new CandidateResult(new Long(137),null,new Double(454),new Long(7) );
		CandidateResult cr138 = new CandidateResult(new Long(138),null,new Double(406),new Long(8) );
		CandidateResult cr139 = new CandidateResult(new Long(139),null,new Double(290),new Long(9) );
		CandidateResult cr140 = new CandidateResult(new Long(140),null,new Double(43347),new Long(1) );
		CandidateResult cr141 = new CandidateResult(new Long(141),null,new Double(38950),new Long(2) );
		CandidateResult cr142 = new CandidateResult(new Long(142),null,new Double(32686),new Long(3) );
		CandidateResult cr143 = new CandidateResult(new Long(143),null,new Double(1969),new Long(4) );
		CandidateResult cr144 = new CandidateResult(new Long(144),null,new Double(769),new Long(5) );
		CandidateResult cr145 = new CandidateResult(new Long(145),null,new Double(709),new Long(6) );
		CandidateResult cr146 = new CandidateResult(new Long(146),null,new Double(495),new Long(7) );
		CandidateResult cr147 = new CandidateResult(new Long(147),null,new Double(328),new Long(8) );
		CandidateResult cr148 = new CandidateResult(new Long(148),null,new Double(309),new Long(9) );
		CandidateResult cr149 = new CandidateResult(new Long(149),null,new Double(67607),new Long(1) );
		CandidateResult cr150 = new CandidateResult(new Long(150),null,new Double(61769),new Long(2) );
		CandidateResult cr151 = new CandidateResult(new Long(151),null,new Double(1110),new Long(3) );
		CandidateResult cr152 = new CandidateResult(new Long(152),null,new Double(949),new Long(4) );
		CandidateResult cr153 = new CandidateResult(new Long(153),null,new Double(795),new Long(5) );
		CandidateResult cr154 = new CandidateResult(new Long(154),null,new Double(682),new Long(6) );
		CandidateResult cr155 = new CandidateResult(new Long(155),null,new Double(67635),new Long(1) );
		CandidateResult cr156 = new CandidateResult(new Long(156),null,new Double(45863),new Long(2) );
		CandidateResult cr157 = new CandidateResult(new Long(157),null,new Double(1239),new Long(3) );
		CandidateResult cr158 = new CandidateResult(new Long(158),null,new Double(485),new Long(4) );
		CandidateResult cr159 = new CandidateResult(new Long(159),null,new Double(407),new Long(5) );
		CandidateResult cr160 = new CandidateResult(new Long(160),null,new Double(240),new Long(6) );
		CandidateResult cr161 = new CandidateResult(new Long(161),null,new Double(67783),new Long(1) );
		CandidateResult cr162 = new CandidateResult(new Long(162),null,new Double(60158),new Long(2) );
		CandidateResult cr163 = new CandidateResult(new Long(163),null,new Double(1847),new Long(3) );
		CandidateResult cr164 = new CandidateResult(new Long(164),null,new Double(790),new Long(4) );
		CandidateResult cr165 = new CandidateResult(new Long(165),null,new Double(544),new Long(5) );
		CandidateResult cr166 = new CandidateResult(new Long(166),null,new Double(412),new Long(6) );
		CandidateResult cr167 = new CandidateResult(new Long(167),null,new Double(337),new Long(7) );
		CandidateResult cr168 = new CandidateResult(new Long(168),null,new Double(310),new Long(8) );
		CandidateResult cr169 = new CandidateResult(new Long(169),null,new Double(289),new Long(9) );
		CandidateResult cr170 = new CandidateResult(new Long(170),null,new Double(187),new Long(10) );
		CandidateResult cr171 = new CandidateResult(new Long(171),null,new Double(182),new Long(11) );
		CandidateResult cr172 = new CandidateResult(new Long(172),null,new Double(179),new Long(12) );
		CandidateResult cr173 = new CandidateResult(new Long(173),null,new Double(128),new Long(13) );
		CandidateResult cr174 = new CandidateResult(new Long(174),null,new Double(62809),new Long(1) );
		CandidateResult cr175 = new CandidateResult(new Long(175),null,new Double(53978),new Long(2) );
		CandidateResult cr176 = new CandidateResult(new Long(176),null,new Double(1575),new Long(3) );
		CandidateResult cr177 = new CandidateResult(new Long(177),null,new Double(962),new Long(4) );
		CandidateResult cr178 = new CandidateResult(new Long(178),null,new Double(808),new Long(5) );
		CandidateResult cr179 = new CandidateResult(new Long(179),null,new Double(56939),new Long(1) );
		CandidateResult cr180 = new CandidateResult(new Long(180),null,new Double(48124),new Long(2) );
		CandidateResult cr181 = new CandidateResult(new Long(181),null,new Double(4007),new Long(3) );
		CandidateResult cr182 = new CandidateResult(new Long(182),null,new Double(1799),new Long(4) );
		CandidateResult cr183 = new CandidateResult(new Long(183),null,new Double(940),new Long(5) );
		CandidateResult cr184 = new CandidateResult(new Long(184),null,new Double(847),new Long(6) );
		CandidateResult cr185 = new CandidateResult(new Long(185),null,new Double(687),new Long(7) );
		CandidateResult cr186 = new CandidateResult(new Long(186),null,new Double(551),new Long(8) );
		CandidateResult cr187 = new CandidateResult(new Long(187),null,new Double(57830),new Long(1) );
		CandidateResult cr188 = new CandidateResult(new Long(188),null,new Double(51135),new Long(2) );
		CandidateResult cr189 = new CandidateResult(new Long(189),null,new Double(5624),new Long(3) );
		CandidateResult cr190 = new CandidateResult(new Long(190),null,new Double(1023),new Long(4) );
		CandidateResult cr191 = new CandidateResult(new Long(191),null,new Double(838),new Long(5) );
		candidateResults.add(cr1); 
		candidateResults.add(cr2);
		candidateResults.add(cr3);
		candidateResults.add(cr4);
		candidateResults.add(cr5);
		candidateResults.add(cr6);
		candidateResults.add(cr7);
		candidateResults.add(cr8);
		candidateResults.add(cr9);
		candidateResults.add(cr10);
		candidateResults.add(cr11);
		candidateResults.add(cr12);
		candidateResults.add(cr13);
		candidateResults.add(cr14);
		candidateResults.add(cr15);
		candidateResults.add(cr16);
		candidateResults.add(cr17);
		candidateResults.add(cr18);
		candidateResults.add(cr19);
		candidateResults.add(cr20);
		
		candidateResults.add(cr21);		candidateResults.add(cr22);		candidateResults.add(cr23);		candidateResults.add(cr24);
		candidateResults.add(cr25);		candidateResults.add(cr26);		candidateResults.add(cr27);		candidateResults.add(cr28);
		candidateResults.add(cr29);		candidateResults.add(cr30);		candidateResults.add(cr31);		candidateResults.add(cr32);
		candidateResults.add(cr33);		candidateResults.add(cr34);		candidateResults.add(cr35);		candidateResults.add(cr36);
		candidateResults.add(cr37);		candidateResults.add(cr38);		candidateResults.add(cr39);		candidateResults.add(cr40);
		candidateResults.add(cr41);		candidateResults.add(cr42);		candidateResults.add(cr43);		candidateResults.add(cr44);
		candidateResults.add(cr45);		candidateResults.add(cr46);		candidateResults.add(cr47);		candidateResults.add(cr48);
		candidateResults.add(cr49);		candidateResults.add(cr50);		candidateResults.add(cr51);		candidateResults.add(cr52);		
		candidateResults.add(cr53);		candidateResults.add(cr54);		candidateResults.add(cr55);		candidateResults.add(cr56);		
		candidateResults.add(cr57);

		candidateResults.add(cr58);	candidateResults.add(cr59);	candidateResults.add(cr60); candidateResults.add(cr61);
		candidateResults.add(cr62);	candidateResults.add(cr63);	candidateResults.add(cr64); candidateResults.add(cr65);
		candidateResults.add(cr69);	candidateResults.add(cr68);	candidateResults.add(cr67); candidateResults.add(cr66);
		candidateResults.add(cr70);	candidateResults.add(cr71);	candidateResults.add(cr72); candidateResults.add(cr73);
		candidateResults.add(cr77);	candidateResults.add(cr76);	candidateResults.add(cr75); candidateResults.add(cr74);
		candidateResults.add(cr78);	candidateResults.add(cr79);	candidateResults.add(cr80); candidateResults.add(cr81);
		candidateResults.add(cr85);	candidateResults.add(cr84);	candidateResults.add(cr83); candidateResults.add(cr82);
		candidateResults.add(cr86);	candidateResults.add(cr87);	candidateResults.add(cr88); candidateResults.add(cr89);
		candidateResults.add(cr93);	candidateResults.add(cr92);	candidateResults.add(cr91); candidateResults.add(cr90);
		candidateResults.add(cr94);	candidateResults.add(cr95);	candidateResults.add(cr96); candidateResults.add(cr97);
		candidateResults.add(cr100);	candidateResults.add(cr99); candidateResults.add(cr98);
		
		candidateResults.add(cr101); 	candidateResults.add(cr102); candidateResults.add(cr103);	candidateResults.add(cr104);
		candidateResults.add(cr105);	candidateResults.add(cr106);	candidateResults.add(cr107);	candidateResults.add(cr108);
		candidateResults.add(cr109);	candidateResults.add(cr110);	candidateResults.add(cr111);	candidateResults.add(cr112);
		candidateResults.add(cr113);	candidateResults.add(cr114);	candidateResults.add(cr115);	candidateResults.add(cr116);
		candidateResults.add(cr117);	candidateResults.add(cr118);	candidateResults.add(cr119);	candidateResults.add(cr120);
		
		
		//tday
		candidateResults.add(cr121);		candidateResults.add(cr122);		candidateResults.add(cr123);		candidateResults.add(cr124);
		candidateResults.add(cr125);		candidateResults.add(cr126);		candidateResults.add(cr127);		candidateResults.add(cr128);
		candidateResults.add(cr129);		candidateResults.add(cr130);		candidateResults.add(cr131);		candidateResults.add(cr132);
		candidateResults.add(cr133);		candidateResults.add(cr134);		candidateResults.add(cr135);		candidateResults.add(cr136);
		candidateResults.add(cr137);		candidateResults.add(cr138);		candidateResults.add(cr139);		candidateResults.add(cr140);
		candidateResults.add(cr141);		candidateResults.add(cr142);		candidateResults.add(cr143);		candidateResults.add(cr144);
		candidateResults.add(cr145);		candidateResults.add(cr146);		candidateResults.add(cr147);		candidateResults.add(cr148);
		candidateResults.add(cr149);		candidateResults.add(cr150);		candidateResults.add(cr151);		candidateResults.add(cr152);		
		candidateResults.add(cr153);		candidateResults.add(cr154);		candidateResults.add(cr155);		candidateResults.add(cr156);		
		candidateResults.add(cr157);

		candidateResults.add(cr158);	candidateResults.add(cr159);	candidateResults.add(cr160); candidateResults.add(cr161);
		candidateResults.add(cr162);	candidateResults.add(cr163);	candidateResults.add(cr164); candidateResults.add(cr165);
		candidateResults.add(cr169);	candidateResults.add(cr168);	candidateResults.add(cr167); candidateResults.add(cr166);
		candidateResults.add(cr170);	candidateResults.add(cr171);	candidateResults.add(cr172); candidateResults.add(cr173);
		candidateResults.add(cr177);	candidateResults.add(cr176);	candidateResults.add(cr175); candidateResults.add(cr174);
		candidateResults.add(cr178);	candidateResults.add(cr179);	candidateResults.add(cr180); candidateResults.add(cr181);
		candidateResults.add(cr185);	candidateResults.add(cr184);	candidateResults.add(cr183); candidateResults.add(cr182);
		candidateResults.add(cr186);	candidateResults.add(cr187);	candidateResults.add(cr188); candidateResults.add(cr189);
		candidateResults.add(cr191); candidateResults.add(cr190);
		 
		
		
		return candidateResults;

	}

	private static CandidateResult getCandidateResult(Long id){
		for(CandidateResult candidateResult : candidateResults){
			if(candidateResult.getCandidateResultId().equals(id))
			return candidateResult;
		}
		return null;
	}
	
	public static void main(String ad[]) {
		CandidateResult rs = getCandidateResult(new Long(69));
		System.out.println(rs.getRank());
	}
	
	private static Candidate getCandidate(Long id){
		for(Candidate candidate : candidates){
			if(candidate.getCandidateId().equals(id))
			return candidate;
		}
		return null;
	}
	
	public static List<Candidate> getCandidates(){/*
		Candidate(Long candidateId, String firstname, String middlename,
				String lastname, Date dateofbirth, String emailAddress,
				String phone, String mobile, String address, String education,
				String gender, Set<Nomination> nominations)
		Candidate  c1 = new Candidate(new Long(1), "MASTHAN RAO",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c2 = new Candidate(new Long(2), "VISHNUVARDHAN REDDY",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c3 = new Candidate(new Long(3), "PRATAP KUMAR REDDY",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c4 = new Candidate(new Long(4), "VENKATA SATYANARAYANA",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c5 = new Candidate(new Long(5), " KRISHNA REDDY",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c6 = new Candidate(new Long(6), "PRASAD",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c7 = new Candidate(new Long(7), "MALAKONDA REDDY",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c8 = new Candidate(new Long(8), "SIVA KUMAR REDDY",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c9 = new Candidate(new Long(9), "VENKATA SUBBAIAH",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c10 = new Candidate(new Long(10), "VENKATA SUBBAMMA",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c11 = new Candidate(new Long(11), "RAMA NARAYANA REDDY",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c12 = new Candidate(new Long(12), "LAKSHMAIAH NAIDU",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c13 = new Candidate(new Long(13), "SHAIK",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c14 = new Candidate(new Long(14), "RAMAKRISHNAIAH",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c15 = new Candidate(new Long(15), "BASHA",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c16 = new Candidate(new Long(16), "SIRIVELLA",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c17 = new Candidate(new Long(17), "PRABHAKAR NAIDU",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c18 = new Candidate(new Long(18), "CHANDRA MOULI",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c19 = new Candidate(new Long(19), "VENKATA SUBBAIAH",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c20 = new Candidate(new Long(20), "VENU GOPAL REDDY",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c21 = new Candidate(new Long(21), "VIJAYA BHASKAR REDDY",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c22 = new Candidate(new Long(22), "PRASANNA KUMAR REDDY",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c23 = new Candidate(new Long(23), "SRINIVASULU REDDY",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c24 = new Candidate(new Long(24), "MUNEMMA",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c25 = new Candidate(new Long(25), "NAGESWARA RAO",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c26 = new Candidate(new Long(26), "SUDHAKAR",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c27 = new Candidate(new Long(27), "RAGHU",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c28 = new Candidate(new Long(28), "SRINIVASULU",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c29 = new Candidate(new Long(29), "VENKATESWARLU",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c30 = new Candidate(new Long(30), "VIJAYAMMA",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c31 = new Candidate(new Long(31), "RAVI",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c32 = new Candidate(new Long(32), "SURESH",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c33 = new Candidate(new Long(33), "SRIDHAR KRISHNA REDDY",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c34 = new Candidate(new Long(34), "ANIL KUMAR",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c35 = new Candidate(new Long(35), "RAMESH REDDY",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c36 = new Candidate(new Long(36), "VIJAYA LAKSHMI",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c37 = new Candidate(new Long(37), "BHASKAR",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c38 = new Candidate(new Long(38), "ZIAUL HAQ",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c39 = new Candidate(new Long(39), "HUSNARA BEGUM",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c40 = new Candidate(new Long(40), "BALASUBRAMANYA SASTRI",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c41 = new Candidate(new Long(41), "RAVINDRA BABU",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c42 = new Candidate(new Long(42), "NARAYANA",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c43 = new Candidate(new Long(43), "PAPA RAO",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c44 = new Candidate(new Long(44), "SUBBAIAH",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c45 = new Candidate(new Long(45), "VIVEKANANDA REDDY",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c46 = new Candidate(new Long(46), "VENKATA RAMANA REDDY",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c47 = new Candidate(new Long(47), "BHANU RAJU P",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c48 = new Candidate(new Long(48), "K. REDDY",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c49 = new Candidate(new Long(49), "ANJANEYA REDDY ",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c50 = new Candidate(new Long(50), "NAGESWARA RAO YADAV",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c51 = new Candidate(new Long(51), "GEETHA",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c52 = new Candidate(new Long(52), "SURESH KUMAR",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c53 = new Candidate(new Long(53), "RAJASEKHAR REDDY",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c54 = new Candidate(new Long(54), "S. SHARMA",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c55 = new Candidate(new Long(55), "SHAMEEM",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c56 = new Candidate(new Long(56), "PRABHAKARA REDDY",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c57 = new Candidate(new Long(57), "CHANDRA MOHAN REDDY",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c58 = new Candidate(new Long(58), "VENKATA SESHA REDDY",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c59 = new Candidate(new Long(59), "CHENGAL RAO",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c60 = new Candidate(new Long(60), "SREENIVASULU REDDY",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c61 = new Candidate(new Long(61), "SUNDARARAMIREDDY",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c62 = new Candidate(new Long(62), "PRABHAKAR REDDY",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c63 = new Candidate(new Long(63), "KRISHNAPATNAM",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c64 = new Candidate(new Long(64), "MALLAREDDY",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c65 = new Candidate(new Long(65), "PRAMEELA",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c66 = new Candidate(new Long(66), "CHINA MASTHANAIAH",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c67 = new Candidate(new Long(67), "AUDISESHAIAH",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c68 = new Candidate(new Long(68), "DURGA PRASAD RAO",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c69 = new Candidate(new Long(69), "KRISHNAIAH",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c70 = new Candidate(new Long(70), "BABU RAVINDRA",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c71 = new Candidate(new Long(71), "SEENAIAH",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c72 = new Candidate(new Long(72), "VENKATA KRISHNAIAH",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c73 = new Candidate(new Long(73), "KRISHNAIAH",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c74 = new Candidate(new Long(74), "AMARAVATHAMMA",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c75 = new Candidate(new Long(75), "SRINIVASULU",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c76 = new Candidate(new Long(76), "VENKATA RATHNAIAH",null,null, null, null,null, null,null, null,null, null,null);
		Candidate  c77 = new Candidate(new Long(77), "SARASWATHI",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c78 = new Candidate(new Long(78), "ESWARAMMA",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c79 = new Candidate(new Long(79), "RAMACHANDRAIAH",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c80 = new Candidate(new Long(80), "KANCHIRAJU",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c81 = new Candidate(new Long(81), "SURYANARAYANA",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c82 = new Candidate(new Long(82), "CHINA GANGAIAH",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c83 = new Candidate(new Long(83), "SRINIVASULU",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c84 = new Candidate(new Long(84), "VENKATA KRISHNA PRASAD",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c85 = new Candidate(new Long(85), "RAMA KRISHNA",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c86 = new Candidate(new Long(86), "RAJYA LAKSHMI",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c87 = new Candidate(new Long(87), "MURALI YADAV",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c88 = new Candidate(new Long(88), "VENKATA ANUPKUMAR REDDY",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c89 = new Candidate(new Long(89), "SUBRAMANYAM",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c90 = new Candidate(new Long(90), "VENKATASWAMY",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c91 = new Candidate(new Long(91), "MASTHNAIAH",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c92 = new Candidate(new Long(92), "VENKATA SREEDHAR BABU",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c93 = new Candidate(new Long(93), "GOPALU",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c94 = new Candidate(new Long(94), "VIJAYA RAMI REDDY",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c95 = new Candidate(new Long(95), "PUSHPANJALI",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c96 = new Candidate(new Long(96), "CHANDRA SEKHAR REDDY",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c97 = new Candidate(new Long(97), "ANKAIAH CHOWDARY",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c98 = new Candidate(new Long(98), "CHENCHU NAIDU",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c99 = new Candidate(new Long(99), "MALYADRI",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c100 = new Candidate(new Long(100), "ANJANADRI",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c101 = new Candidate(new Long(101), "CHINNA VENGALA REDDY",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c102 = new Candidate(new Long(102), "VENKATA SUBBAREDDY",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c103 = new Candidate(new Long(103), "KRISHNA REDDY",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c104 = new Candidate(new Long(104), "SUBBARAO",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c105 = new Candidate(new Long(105), "SRINIVASULU REDDY",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c106 = new Candidate(new Long(106), "KRISHNAIAH",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c107 = new Candidate(new Long(107), "VENKATESWARA REDDY",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c108 = new Candidate(new Long(108), "CHANDRASEKHARA REDDY",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c109 = new Candidate(new Long(109), "VIJAYARAMI REDDY",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c110 = new Candidate(new Long(110), "RAMREDDY",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c111 = new Candidate(new Long(111), "VENKATESWARAREDDY",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c112 = new Candidate(new Long(112), "RAMANA REDDY",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c113 = new Candidate(new Long(113), "RANGA REDDY",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c114 = new Candidate(new Long(114), "NARASIMHULU",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c115 = new Candidate(new Long(115), "VENGAIAH",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c116 = new Candidate(new Long(116), "RAMANJANEYULU",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c117 = new Candidate(new Long(117), "KONDALA RAO",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c118 = new Candidate(new Long(118), "PEDA BRAHMAIAH",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c119 = new Candidate(new Long(119), "RAM REDDY",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c120 = new Candidate(new Long(120), "MAGUNTA",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c121 = new Candidate(new Long(121), "JANAKIRAM",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c122 = new Candidate(new Long(122), "SATYANARAYANA",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c123 = new Candidate(new Long(123), "VENKATESWARLU",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c124 = new Candidate(new Long(124), "MUSHTAK",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c125 = new Candidate(new Long(125), "MURALIDHAR RAO",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c126 = new Candidate(new Long(126), "INDRASENA",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c127 = new Candidate(new Long(127), "NAGARAJU",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c128 = new Candidate(new Long(128), "VENKAIAH",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c129 = new Candidate(new Long(129), "BABU",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c130 = new Candidate(new Long(130), "SUBBA RAO",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c131 = new Candidate(new Long(131), "SRIHARI",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c132 = new Candidate(new Long(132), "VENKATA RAMANAIAH",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c133 = new Candidate(new Long(133), "DEVASENAIAH",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c134 = new Candidate(new Long(134), "DEVAKUMAR REDDY",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c135 = new Candidate(new Long(135), "KRISHNAIAH",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c136 = new Candidate(new Long(136), "SUNDARARAMI REDDY",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c137 = new Candidate(new Long(137), "VENKATA SESHA REDDY",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c138 = new Candidate(new Long(138), "SREENIVASULU REDDY",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c139 = new Candidate(new Long(139), "GIRI NAIDU",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c140 = new Candidate(new Long(140), "RAMA NAIDU",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c141 = new Candidate(new Long(141), "VENKATA SESHA REDDY",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c142 = new Candidate(new Long(142), "SRIHARI NAIDU",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c143 = new Candidate(new Long(143), "SRINIVASULU REDDY",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c144 = new Candidate(new Long(144), "SUBRAMANYAM",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c145 = new Candidate(new Long(145), "CHINA PENCHALAIAH",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c146 = new Candidate(new Long(146), "RATHNAIAH",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c147 = new Candidate(new Long(147), "CHENCHAIAH",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c148 = new Candidate(new Long(148), "SURESH REDDY",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c149 = new Candidate(new Long(149), "PRATAP",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c150 = new Candidate(new Long(150), "RADHAKRISHNA MURTHY",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c151 = new Candidate(new Long(151), "NARASIMHULU",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c152 = new Candidate(new Long(152), "RAGHURAM",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c153 = new Candidate(new Long(153), "ARUNA",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c154 = new Candidate(new Long(154), "VENKATA KRISHNA REDDY",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c155 = new Candidate(new Long(155), "RAMANAIAH",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c156 = new Candidate(new Long(156), "SUDHAKARA REDDY",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c157 = new Candidate(new Long(157), "NAGESWARA RAO",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c158 = new Candidate(new Long(158), "SREENIVASULU",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c159 = new Candidate(new Long(159), "CHANDRASEKHARA REDDY",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c160 = new Candidate(new Long(160), "VENKATA RAMANAIAH",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c161 = new Candidate(new Long(161), "KAMALAKARA REDDY",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c162 = new Candidate(new Long(162), "PRAKASA RAO",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c163 = new Candidate(new Long(163), "RAJESWARAMMA",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c164 = new Candidate(new Long(164), "KESAVULU",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c165 = new Candidate(new Long(165), "KONDAIAH",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c166 = new Candidate(new Long(166), "LAKSHMAMMA",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c167 = new Candidate(new Long(167), "SUBHRAMANYAM",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c168 = new Candidate(new Long(168), "VENKATA RATNAIAH",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c169 = new Candidate(new Long(169), "CHENGAIAH",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c170 = new Candidate(new Long(170), "MURALI",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c171 = new Candidate(new Long(171), "MATAMMA",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c172 = new Candidate(new Long(172), "VISWANADAM",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c173 = new Candidate(new Long(173), "GANGA PRASAD",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c174 = new Candidate(new Long(174), "RAJYALAKSHMI",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c175 = new Candidate(new Long(175), "BHASKARA SAIKRISHNA YACHENDRA",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c176 = new Candidate(new Long(176), "RAMESH",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c177 = new Candidate(new Long(177), "KALENDRA PRASAD",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c178 = new Candidate(new Long(178), "CHANDRASEKHAR YADAV",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c179 = new Candidate(new Long(179), "NAIDU",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c180 = new Candidate(new Long(180), "GOPALA KRISHNA REDDY",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c181 = new Candidate(new Long(181), "MADHAVAIAH",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c182 = new Candidate(new Long(182), "CHALAPATHI",null,null, null, null,null, null,null, null,null, null, null);
		Candidate  c183 = new Candidate(new Long(183), "CHANDRA SEKHAR",null,null, null, null,null, null,null, null,null, null, null);
		
		candidates.add(c1); candidates.add(c2);	candidates.add(c3);	candidates.add(c4);	candidates.add(c5);
		candidates.add(c6);	candidates.add(c7);	candidates.add(c8);	candidates.add(c9);	candidates.add(c10);
		candidates.add(c11); candidates.add(c12);candidates.add(c13);candidates.add(c14);	candidates.add(c15);
		candidates.add(c16);candidates.add(c17);candidates.add(c18);candidates.add(c19);	candidates.add(c20);
		candidates.add(c21);	candidates.add(c22);	candidates.add(c23);	candidates.add(c24);	candidates.add(c25);
		candidates.add(c26);	candidates.add(c27);	candidates.add(c28);	candidates.add(c29);	candidates.add(c30);
		candidates.add(c31);	candidates.add(c32);	candidates.add(c33);	candidates.add(c34);	candidates.add(c35);
		candidates.add(c36);	candidates.add(c37);	candidates.add(c38);	candidates.add(c39);	candidates.add(c40);
		candidates.add(c41);	candidates.add(c42);	candidates.add(c43);	candidates.add(c44);	candidates.add(c45);
		candidates.add(c46);
		candidates.add(c47);
		candidates.add(c48);
		candidates.add(c49);
		candidates.add(c50);
		candidates.add(c51);
		candidates.add(c52);
		candidates.add(c53);
		candidates.add(c54);
		candidates.add(c55);
		candidates.add(c56);
		candidates.add(c57);
		candidates.add(c58);
		candidates.add(c59);
		candidates.add(c60);
		candidates.add(c61);
		candidates.add(c62);
		candidates.add(c63);
		candidates.add(c64);
		candidates.add(c65);
		candidates.add(c66);
		candidates.add(c67);
		candidates.add(c68);
		candidates.add(c69);
		candidates.add(c70);
		candidates.add(c71);
		candidates.add(c72);
		candidates.add(c73);
		candidates.add(c74);
		candidates.add(c75);
		candidates.add(c76);
		candidates.add(c77);
		candidates.add(c78);
		candidates.add(c79);
		candidates.add(c80);
		candidates.add(c81);
		candidates.add(c82);
		candidates.add(c83);
		candidates.add(c84);
		candidates.add(c85);
		candidates.add(c86);
		candidates.add(c87);
		candidates.add(c88);
		candidates.add(c89);
		candidates.add(c90);
		candidates.add(c91);
		candidates.add(c92);
		candidates.add(c93);
		candidates.add(c94);
		candidates.add(c95);
		candidates.add(c96);
		candidates.add(c97);
		candidates.add(c98);
		candidates.add(c99);
		candidates.add(c100);
		candidates.add(c101);
		candidates.add(c102);
		candidates.add(c103);
		candidates.add(c104);
		candidates.add(c105);
		candidates.add(c106);
		candidates.add(c107);
		candidates.add(c108);
		candidates.add(c109);
		candidates.add(c110);
		candidates.add(c111);
		candidates.add(c112);
		candidates.add(c113);
		candidates.add(c114);
		candidates.add(c115);
		candidates.add(c116);
		candidates.add(c117);
		candidates.add(c118);
		candidates.add(c119);
		candidates.add(c120);
		candidates.add(c121);
		candidates.add(c122);
		candidates.add(c123);
		candidates.add(c124);
		candidates.add(c125);
		candidates.add(c126);
		candidates.add(c127);
		candidates.add(c128);
		candidates.add(c129);
		candidates.add(c130);
		candidates.add(c131);
		candidates.add(c132);
		candidates.add(c133);
		candidates.add(c134);
		candidates.add(c135);
		candidates.add(c136);
		candidates.add(c137);
		candidates.add(c138);
		candidates.add(c139);
		candidates.add(c140);
		candidates.add(c141);
		candidates.add(c142);
		candidates.add(c143);
		candidates.add(c144);
		candidates.add(c145);
		candidates.add(c146);
		candidates.add(c147);
		candidates.add(c148);
		candidates.add(c149);
		candidates.add(c150);
		candidates.add(c151);
		candidates.add(c152);
		candidates.add(c153);
		candidates.add(c154);
		candidates.add(c155);
		candidates.add(c156);
		candidates.add(c157);
		candidates.add(c158);
		candidates.add(c159);
		candidates.add(c160);
		candidates.add(c161);
		candidates.add(c162);
		candidates.add(c163);
		candidates.add(c164);
		candidates.add(c165);
		candidates.add(c166);
		candidates.add(c167);
		candidates.add(c168);
		candidates.add(c169);
		candidates.add(c170);
		candidates.add(c171);
		candidates.add(c172);
		candidates.add(c173);
		candidates.add(c174);
		candidates.add(c175);
		candidates.add(c176);
		candidates.add(c177);
		candidates.add(c178);
		candidates.add(c179);
		candidates.add(c180);
		candidates.add(c181);
		candidates.add(c182);
		candidates.add(c183);
		*/
		return candidates;
	}

	/*private static final ElectionType electionType = new ElectionType(new Long(2),"Assembly","State", null);
	private static final ElectionScope electionScope = new ElectionScope(new Long(2),electionType,new Long(1),null,null);
	private static final List<Party> parties = new ArrayList<Party> ();	
	private static final District  district = new District(new Long(15), state, "Nellore","Nellore",null,null,null,null,null,null,null);
	private static final List<Election> elections = new ArrayList<Election>();
 
	private static final List<Candidate> candidates = new ArrayList<Candidate>(); 
	
	private static final List<Constituency> constituencies = new ArrayList<Constituency>();	
	
	private static final List<Nomination> nominations = new ArrayList<Nomination>();
	private static final List<ConstituencyElection> constituencyElections = new ArrayList<ConstituencyElection>();
	private static final List<ConstituencyElectionResult> constituencyElectionResults = 
														new ArrayList<ConstituencyElectionResult>();
	
	private static final List<CandidateResult> candidateResults = new ArrayList<CandidateResult>();*/
	
	public static List<Nomination> getNominations() {
		/*Nomination(Long nominationId,
				ConstituencyElection constituencyElection, Party party,
				Candidate candidate, String nominationStatus, Double assets,
				Double liabilities, String criminalCharges,
				CandidateResult candidateResult) */
		getCandidates();
		getParties();
		getCandidateResults();
		Nomination n1 = new Nomination(new Long(1),getConstituencyElection(new Long(1)),getParty(new Long(11)),getCandidate(new Long(1)),null,null,null,null,getCandidateResult(new Long(1)), null, null,null);
		Nomination n2 = new Nomination(new Long(2),getConstituencyElection(new Long(1)),getParty(new Long(1)),getCandidate(new Long(2)),null,null,null,null,getCandidateResult(new Long(2)), null,null,null);
		Nomination n3 = new Nomination(new Long(3),getConstituencyElection(new Long(1)),getParty(new Long(9)),getCandidate(new Long(3)),null,null,null,null,getCandidateResult(new Long(3)), null,null,null);
		Nomination n4 = new Nomination(new Long(4),getConstituencyElection(new Long(1)),getParty(new Long(2)),getCandidate(new Long(4)),null,null,null,null,getCandidateResult(new Long(4)), null,null,null);
		Nomination n5 = new Nomination(new Long(5),getConstituencyElection(new Long(1)),getParty(new Long(3)),getCandidate(new Long(5)),null,null,null,null,getCandidateResult(new Long(5)), null,null,null);
		Nomination n6 = new Nomination(new Long(6),getConstituencyElection(new Long(1)),getParty(new Long(13)),getCandidate(new Long(6)),null,null,null,null,getCandidateResult(new Long(6)), null,null,null);

		Nomination n7 = new Nomination(new Long(7),getConstituencyElection(new Long(1)),getParty(new Long(14)),getCandidate(new Long(7)),null,null,null,null,getCandidateResult(new Long(7)), null,null,null);
		Nomination n8 = new Nomination(new Long(8),getConstituencyElection(new Long(1)),getParty(new Long(13)),getCandidate(new Long(8)),null,null,null,null,getCandidateResult(new Long(8)), null,null,null);
		Nomination n9 = new Nomination(new Long(9),getConstituencyElection(new Long(1)),getParty(new Long(13)),getCandidate(new Long(9)),null,null,null,null,getCandidateResult(new Long(9)), null,null,null);
		Nomination n10 = new Nomination(new Long(10),getConstituencyElection(new Long(1)),getParty(new Long(13)),getCandidate(new Long(10)),null,null,null,null,getCandidateResult(new Long(10)), null,null,null);
		Nomination n11 = new Nomination(new Long(11),getConstituencyElection(new Long(2)),getParty(new Long(1)),getCandidate(new Long(11)),null,null,null,null,getCandidateResult(new Long(11)), null,null,null);
		Nomination n12 = new Nomination(new Long(12),getConstituencyElection(new Long(2)),getParty(new Long(11)),getCandidate(new Long(12)),null,null,null,null,getCandidateResult(new Long(12)), null,null,null);
		Nomination n13 = new Nomination(new Long(13),getConstituencyElection(new Long(2)),getParty(new Long(9)),getCandidate(new Long(13)),null,null,null,null,getCandidateResult(new Long(13)), null,null,null);
		Nomination n14 = new Nomination(new Long(14),getConstituencyElection(new Long(2)),getParty(new Long(3)),getCandidate(new Long(14)),null,null,null,null,getCandidateResult(new Long(14)), null,null,null);
		Nomination n15 = new Nomination(new Long(15),getConstituencyElection(new Long(2)),getParty(new Long(13)),getCandidate(new Long(15)),null,null,null,null,getCandidateResult(new Long(15)), null,null,null);
		Nomination n16 = new Nomination(new Long(16),getConstituencyElection(new Long(2)),getParty(new Long(14)),getCandidate(new Long(16)),null,null,null,null,getCandidateResult(new Long(16)), null,null,null);
		Nomination n17 = new Nomination(new Long(17),getConstituencyElection(new Long(2)),getParty(new Long(13)),getCandidate(new Long(17)),null,null,null,null,getCandidateResult(new Long(17)), null,null,null);
		Nomination n18 = new Nomination(new Long(18),getConstituencyElection(new Long(2)),getParty(new Long(13)),getCandidate(new Long(18)),null,null,null,null,getCandidateResult(new Long(18)), null,null,null);
		Nomination n19 = new Nomination(new Long(19),getConstituencyElection(new Long(2)),getParty(new Long(13)),getCandidate(new Long(19)),null,null,null,null,getCandidateResult(new Long(19)), null,null,null);
		Nomination n20 = new Nomination(new Long(20),getConstituencyElection(new Long(2)),getParty(new Long(13)),getCandidate(new Long(20)),null,null,null,null,getCandidateResult(new Long(20)), null,null,null);
		Nomination n21 = new Nomination(new Long(21),getConstituencyElection(new Long(2)),getParty(new Long(13)),getCandidate(new Long(21)),null,null,null,null,getCandidateResult(new Long(21)), null,null,null);
		Nomination n22 = new Nomination(new Long(22),getConstituencyElection(new Long(3)),getParty(new Long(11)),getCandidate(new Long(22)),null,null,null,null,getCandidateResult(new Long(22)), null,null,null);
		Nomination n23 = new Nomination(new Long(23),getConstituencyElection(new Long(3)),getParty(new Long(1)),getCandidate(new Long(23)),null,null,null,null,getCandidateResult(new Long(23)), null,null,null);
		Nomination n24 = new Nomination(new Long(24),getConstituencyElection(new Long(3)),getParty(new Long(9)),getCandidate(new Long(24)),null,null,null,null,getCandidateResult(new Long(24)), null,null,null);
		Nomination n25 = new Nomination(new Long(25),getConstituencyElection(new Long(3)),getParty(new Long(3)),getCandidate(new Long(25)),null,null,null,null,getCandidateResult(new Long(25)), null,null,null);
		Nomination n26 = new Nomination(new Long(26),getConstituencyElection(new Long(3)),getParty(new Long(12)),getCandidate(new Long(26)),null,null,null,null,getCandidateResult(new Long(26)), null,null,null);
		Nomination n27 = new Nomination(new Long(27),getConstituencyElection(new Long(3)),getParty(new Long(13)),getCandidate(new Long(27)),null,null,null,null,getCandidateResult(new Long(27)), null,null,null);
		Nomination n28 = new Nomination(new Long(28),getConstituencyElection(new Long(3)),getParty(new Long(13)),getCandidate(new Long(28)),null,null,null,null,getCandidateResult(new Long(28)), null,null,null);
		Nomination n29 = new Nomination(new Long(29),getConstituencyElection(new Long(3)),getParty(new Long(14)),getCandidate(new Long(29)),null,null,null,null,getCandidateResult(new Long(29)), null,null,null);
		Nomination n30 = new Nomination(new Long(30),getConstituencyElection(new Long(3)),getParty(new Long(13)),getCandidate(new Long(30)),null,null,null,null,getCandidateResult(new Long(30)), null,null,null);
		Nomination n31 = new Nomination(new Long(31),getConstituencyElection(new Long(3)),getParty(new Long(17)),getCandidate(new Long(31)),null,null,null,null,getCandidateResult(new Long(31)), null,null,null);
		Nomination n32 = new Nomination(new Long(32),getConstituencyElection(new Long(3)),getParty(new Long(13)),getCandidate(new Long(32)),null,null,null,null,getCandidateResult(new Long(32)), null,null,null);
		Nomination n33 = new Nomination(new Long(33),getConstituencyElection(new Long(4)),getParty(new Long(9)),getCandidate(new Long(33)),null,null,null,null,getCandidateResult(new Long(33)), null,null,null);
		Nomination n34 = new Nomination(new Long(34),getConstituencyElection(new Long(4)),getParty(new Long(1)),getCandidate(new Long(34)),null,null,null,null,getCandidateResult(new Long(34)), null,null,null);
		Nomination n35 = new Nomination(new Long(35),getConstituencyElection(new Long(4)),getParty(new Long(11)),getCandidate(new Long(35)),null,null,null,null,getCandidateResult(new Long(35)), null,null,null);
		Nomination n36 = new Nomination(new Long(36),getConstituencyElection(new Long(4)),getParty(new Long(12)),getCandidate(new Long(36)),null,null,null,null,getCandidateResult(new Long(36)), null,null,null);
		Nomination n37 = new Nomination(new Long(37),getConstituencyElection(new Long(4)),getParty(new Long(2)),getCandidate(new Long(37)),null,null,null,null,getCandidateResult(new Long(37)), null,null,null);
		Nomination n38 = new Nomination(new Long(38),getConstituencyElection(new Long(4)),getParty(new Long(13)),getCandidate(new Long(38)),null,null,null,null,getCandidateResult(new Long(38)), null,null,null);
		Nomination n39 = new Nomination(new Long(39),getConstituencyElection(new Long(4)),getParty(new Long(13)),getCandidate(new Long(39)),null,null,null,null,getCandidateResult(new Long(39)), null,null,null);
		Nomination n40 = new Nomination(new Long(40),getConstituencyElection(new Long(4)),getParty(new Long(14)),getCandidate(new Long(40)),null,null,null,null,getCandidateResult(new Long(40)), null,null,null);
		Nomination n41 = new Nomination(new Long(41),getConstituencyElection(new Long(4)),getParty(new Long(3)),getCandidate(new Long(41)),null,null,null,null,getCandidateResult(new Long(41)), null,null,null);
		Nomination n42 = new Nomination(new Long(42),getConstituencyElection(new Long(4)),getParty(new Long(23)),getCandidate(new Long(42)),null,null,null,null,getCandidateResult(new Long(42)), null,null,null);
		Nomination n43 = new Nomination(new Long(43),getConstituencyElection(new Long(4)),getParty(new Long(13)),getCandidate(new Long(43)),null,null,null,null,getCandidateResult(new Long(43)), null,null,null);
		Nomination n44 = new Nomination(new Long(44),getConstituencyElection(new Long(4)),getParty(new Long(17)),getCandidate(new Long(44)),null,null,null,null,getCandidateResult(new Long(44)), null,null,null);
		Nomination n45 = new Nomination(new Long(45),getConstituencyElection(new Long(5)),getParty(new Long(1)),getCandidate(new Long(45)),null,null,null,null,getCandidateResult(new Long(45)), null,null,null);
		Nomination n46 = new Nomination(new Long(46),getConstituencyElection(new Long(5)),getParty(new Long(9)),getCandidate(new Long(46)),null,null,null,null,getCandidateResult(new Long(46)), null,null,null);
		Nomination n47 = new Nomination(new Long(47),getConstituencyElection(new Long(5)),getParty(new Long(5)),getCandidate(new Long(47)),null,null,null,null,getCandidateResult(new Long(47)), null,null,null);
		Nomination n48 = new Nomination(new Long(48),getConstituencyElection(new Long(5)),getParty(new Long(12)),getCandidate(new Long(48)),null,null,null,null,getCandidateResult(new Long(48)), null,null,null);
		Nomination n49 = new Nomination(new Long(49),getConstituencyElection(new Long(5)),getParty(new Long(2)),getCandidate(new Long(49)),null,null,null,null,getCandidateResult(new Long(49)), null,null,null);
		Nomination n50 = new Nomination(new Long(50),getConstituencyElection(new Long(5)),getParty(new Long(3)),getCandidate(new Long(50)),null,null,null,null,getCandidateResult(new Long(50)), null,null,null);
		Nomination n51 = new Nomination(new Long(51),getConstituencyElection(new Long(5)),getParty(new Long(14)),getCandidate(new Long(51)),null,null,null,null,getCandidateResult(new Long(51)), null,null,null);
		Nomination n52 = new Nomination(new Long(52),getConstituencyElection(new Long(5)),getParty(new Long(13)),getCandidate(new Long(52)),null,null,null,null,getCandidateResult(new Long(52)), null,null,null);
		Nomination n53 = new Nomination(new Long(53),getConstituencyElection(new Long(5)),getParty(new Long(13)),getCandidate(new Long(53)),null,null,null,null,getCandidateResult(new Long(53)), null,null,null);
		Nomination n54 = new Nomination(new Long(54),getConstituencyElection(new Long(5)),getParty(new Long(13)),getCandidate(new Long(54)),null,null,null,null,getCandidateResult(new Long(54)), null,null,null);
		Nomination n55 = new Nomination(new Long(55),getConstituencyElection(new Long(5)),getParty(new Long(51)),getCandidate(new Long(55)),null,null,null,null,getCandidateResult(new Long(55)), null,null,null);
		Nomination n56 = new Nomination(new Long(56),getConstituencyElection(new Long(6)),getParty(new Long(1)),getCandidate(new Long(56)),null,null,null,null,getCandidateResult(new Long(56)), null,null,null);
		Nomination n57 = new Nomination(new Long(57),getConstituencyElection(new Long(6)),getParty(new Long(11)),getCandidate(new Long(57)),null,null,null,null,getCandidateResult(new Long(57)), null,null,null);
		
		//new nomination
		Nomination n58 = new Nomination(new Long(58),getConstituencyElection(new Long(6)),getParty(new Long(9)),getCandidate(new Long(58)),null,null,null,null,getCandidateResult(new Long(58)), null,null,null);
		Nomination n59 = new Nomination(new Long(59),getConstituencyElection(new Long(6)),getParty(new Long(3)),getCandidate(new Long(59)),null,null,null,null,getCandidateResult(new Long(59)), null,null,null);
		Nomination n60 = new Nomination(new Long(60),getConstituencyElection(new Long(6)),getParty(new Long(2)),getCandidate(new Long(60)),null,null,null,null,getCandidateResult(new Long(60)), null,null,null);
		Nomination n61 = new Nomination(new Long(61),getConstituencyElection(new Long(6)),getParty(new Long(13)),getCandidate(new Long(61)),null,null,null,null,getCandidateResult(new Long(61)), null,null,null);
		Nomination n62 = new Nomination(new Long(62),getConstituencyElection(new Long(6)),getParty(new Long(13)),getCandidate(new Long(62)),null,null,null,null,getCandidateResult(new Long(62)), null,null,null);
		Nomination n63 = new Nomination(new Long(63),getConstituencyElection(new Long(6)),getParty(new Long(12)),getCandidate(new Long(63)),null,null,null,null,getCandidateResult(new Long(63)), null,null,null);
		Nomination n64 = new Nomination(new Long(64),getConstituencyElection(new Long(6)),getParty(new Long(13)),getCandidate(new Long(64)),null,null,null,null,getCandidateResult(new Long(64)), null,null,null);
		Nomination n65 = new Nomination(new Long(65),getConstituencyElection(new Long(6)),getParty(new Long(14)),getCandidate(new Long(65)),null,null,null,null,getCandidateResult(new Long(65)), null,null,null);
		Nomination n66 = new Nomination(new Long(66),getConstituencyElection(new Long(6)),getParty(new Long(13)),getCandidate(new Long(66)),null,null,null,null,getCandidateResult(new Long(66)), null,null,null);
		Nomination n67 = new Nomination(new Long(67),getConstituencyElection(new Long(6)),getParty(new Long(45)),getCandidate(new Long(67)),null,null,null,null,getCandidateResult(new Long(67)), null,null,null);
		Nomination n68 = new Nomination(new Long(68),getConstituencyElection(new Long(7)),getParty(new Long(11)),getCandidate(new Long(68)),null,null,null,null,getCandidateResult(new Long(68)), null,null,null);
		Nomination n69 = new Nomination(new Long(69),getConstituencyElection(new Long(7)),getParty(new Long(1)),getCandidate(new Long(69)),null,null,null,null,getCandidateResult(new Long(69)), null,null,null);
		Nomination n70 = new Nomination(new Long(70),getConstituencyElection(new Long(7)),getParty(new Long(9)),getCandidate(new Long(70)),null,null,null,null,getCandidateResult(new Long(70)), null,null,null);
		Nomination n71 = new Nomination(new Long(71),getConstituencyElection(new Long(7)),getParty(new Long(3)),getCandidate(new Long(71)),null,null,null,null,getCandidateResult(new Long(71)), null,null,null);
		Nomination n72 = new Nomination(new Long(72),getConstituencyElection(new Long(7)),getParty(new Long(12)),getCandidate(new Long(72)),null,null,null,null,getCandidateResult(new Long(72)), null,null,null);
		Nomination n73 = new Nomination(new Long(73),getConstituencyElection(new Long(7)),getParty(new Long(13)),getCandidate(new Long(73)),null,null,null,null,getCandidateResult(new Long(73)), null,null,null);
		Nomination n74 = new Nomination(new Long(74),getConstituencyElection(new Long(7)),getParty(new Long(14)),getCandidate(new Long(74)),null,null,null,null,getCandidateResult(new Long(74)), null,null,null);
		Nomination n75 = new Nomination(new Long(75),getConstituencyElection(new Long(7)),getParty(new Long(13)),getCandidate(new Long(75)),null,null,null,null,getCandidateResult(new Long(75)), null,null,null);
		Nomination n76 = new Nomination(new Long(76),getConstituencyElection(new Long(8)),getParty(new Long(11)),getCandidate(new Long(76)),null,null,null,null,getCandidateResult(new Long(76)), null,null,null);
		Nomination n77 = new Nomination(new Long(77),getConstituencyElection(new Long(8)),getParty(new Long(1)),getCandidate(new Long(77)),null,null,null,null,getCandidateResult(new Long(77)), null,null,null);
		Nomination n78 = new Nomination(new Long(78),getConstituencyElection(new Long(8)),getParty(new Long(9)),getCandidate(new Long(78)),null,null,null,null,getCandidateResult(new Long(78)), null,null,null);
		Nomination n79 = new Nomination(new Long(79),getConstituencyElection(new Long(8)),getParty(new Long(3)),getCandidate(new Long(79)),null,null,null,null,getCandidateResult(new Long(79)), null,null,null);
		Nomination n80 = new Nomination(new Long(80),getConstituencyElection(new Long(8)),getParty(new Long(12)),getCandidate(new Long(80)),null,null,null,null,getCandidateResult(new Long(80)), null,null,null);
		Nomination n81 = new Nomination(new Long(81),getConstituencyElection(new Long(8)),getParty(new Long(14)),getCandidate(new Long(81)),null,null,null,null,getCandidateResult(new Long(81)), null,null,null);
		Nomination n82 = new Nomination(new Long(82),getConstituencyElection(new Long(8)),getParty(new Long(13)),getCandidate(new Long(82)),null,null,null,null,getCandidateResult(new Long(82)), null,null,null);
		Nomination n83 = new Nomination(new Long(83),getConstituencyElection(new Long(8)),getParty(new Long(13)),getCandidate(new Long(83)),null,null,null,null,getCandidateResult(new Long(83)), null,null,null);
		Nomination n84 = new Nomination(new Long(84),getConstituencyElection(new Long(8)),getParty(new Long(13)),getCandidate(new Long(84)),null,null,null,null,getCandidateResult(new Long(84)), null,null,null);
		Nomination n85 = new Nomination(new Long(85),getConstituencyElection(new Long(9)),getParty(new Long(11)),getCandidate(new Long(85)),null,null,null,null,getCandidateResult(new Long(85)), null,null,null);
		Nomination n86 = new Nomination(new Long(86),getConstituencyElection(new Long(9)),getParty(new Long(1)),getCandidate(new Long(86)),null,null,null,null,getCandidateResult(new Long(86)), null,null,null);
		Nomination n87 = new Nomination(new Long(87),getConstituencyElection(new Long(9)),getParty(new Long(9)),getCandidate(new Long(87)),null,null,null,null,getCandidateResult(new Long(87)), null,null,null);
		Nomination n88 = new Nomination(new Long(88),getConstituencyElection(new Long(9)),getParty(new Long(2)),getCandidate(new Long(88)),null,null,null,null,getCandidateResult(new Long(88)), null,null,null);
		Nomination n89 = new Nomination(new Long(89),getConstituencyElection(new Long(9)),getParty(new Long(12)),getCandidate(new Long(89)),null,null,null,null,getCandidateResult(new Long(89)), null,null,null);
		Nomination n90 = new Nomination(new Long(90),getConstituencyElection(new Long(9)),getParty(new Long(13)),getCandidate(new Long(90)),null,null,null,null,getCandidateResult(new Long(90)), null,null,null);
		Nomination n91 = new Nomination(new Long(91),getConstituencyElection(new Long(9)),getParty(new Long(3)),getCandidate(new Long(91)),null,null,null,null,getCandidateResult(new Long(91)), null,null,null);
		Nomination n92 = new Nomination(new Long(92),getConstituencyElection(new Long(9)),getParty(new Long(14)),getCandidate(new Long(92)),null,null,null,null,getCandidateResult(new Long(92)), null,null,null);
		Nomination n93 = new Nomination(new Long(93),getConstituencyElection(new Long(9)),getParty(new Long(13)),getCandidate(new Long(93)),null,null,null,null,getCandidateResult(new Long(93)), null,null,null);
		Nomination n94 = new Nomination(new Long(94),getConstituencyElection(new Long(10)),getParty(new Long(11)),getCandidate(new Long(94)),null,null,null,null,getCandidateResult(new Long(94)), null,null,null);
		Nomination n95 = new Nomination(new Long(95),getConstituencyElection(new Long(10)),getParty(new Long(3)),getCandidate(new Long(95)),null,null,null,null,getCandidateResult(new Long(95)), null,null,null);
		Nomination n96 = new Nomination(new Long(96),getConstituencyElection(new Long(10)),getParty(new Long(1)),getCandidate(new Long(96)),null,null,null,null,getCandidateResult(new Long(96)), null,null,null);
		Nomination n97 = new Nomination(new Long(97),getConstituencyElection(new Long(10)),getParty(new Long(12)),getCandidate(new Long(97)),null,null,null,null,getCandidateResult(new Long(97)), null,null,null);
		Nomination n98 = new Nomination(new Long(98),getConstituencyElection(new Long(10)),getParty(new Long(6)),getCandidate(new Long(98)),null,null,null,null,getCandidateResult(new Long(98)), null,null,null);
		Nomination n99 = new Nomination(new Long(99),getConstituencyElection(new Long(10)),getParty(new Long(14)),getCandidate(new Long(99)),null,null,null,null,getCandidateResult(new Long(99)), null,null,null);
		Nomination n100 = new Nomination(new Long(100),getConstituencyElection(new Long(10)),getParty(new Long(9)),getCandidate(new Long(100)),null,null,null,null,getCandidateResult(new Long(100)), null,null,null);
		Nomination n101 = new Nomination(new Long(101),getConstituencyElection(new Long(10)),getParty(new Long(13)),getCandidate(new Long(101)),null,null,null,null,getCandidateResult(new Long(101)), null,null,null);
		Nomination n102 = new Nomination(new Long(102),getConstituencyElection(new Long(10)),getParty(new Long(13)),getCandidate(new Long(102)),null,null,null,null,getCandidateResult(new Long(102)), null,null,null);
		Nomination n103 = new Nomination(new Long(103),getConstituencyElection(new Long(10)),getParty(new Long(13)),getCandidate(new Long(103)),null,null,null,null,getCandidateResult(new Long(103)), null,null,null);
		Nomination n104 = new Nomination(new Long(104),getConstituencyElection(new Long(10)),getParty(new Long(13)),getCandidate(new Long(104)),null,null,null,null,getCandidateResult(new Long(104)), null,null,null);
		Nomination n105 = new Nomination(new Long(105),getConstituencyElection(new Long(10)),getParty(new Long(13)),getCandidate(new Long(105)),null,null,null,null,getCandidateResult(new Long(105)), null,null,null);
		Nomination n106 = new Nomination(new Long(106),getConstituencyElection(new Long(10)),getParty(new Long(13)),getCandidate(new Long(106)),null,null,null,null,getCandidateResult(new Long(106)), null,null,null);
		Nomination n107 = new Nomination(new Long(107),getConstituencyElection(new Long(10)),getParty(new Long(13)),getCandidate(new Long(107)),null,null,null,null,getCandidateResult(new Long(107)), null,null,null);
		
		Nomination n108 = new Nomination(new Long(108),getConstituencyElection(new Long(10)),getParty(new Long(1)),getCandidate(new Long(108)),null,null,null,null,
				getCandidateResult(new Long(108)), null,null,null);
		Nomination n109 = new Nomination(new Long(109),getConstituencyElection(new Long(10)),getParty(new Long(11)),getCandidate(new Long(109)),null,null,null,null,
				getCandidateResult(new Long(109)), null,null,null);
		Nomination n110 = new Nomination(new Long(110),getConstituencyElection(new Long(10)),getParty(new Long(13)),getCandidate(new Long(110)),null,null,null,null,
				getCandidateResult(new Long(110)), null,null,null);
		Nomination n111 = new Nomination(new Long(111),getConstituencyElection(new Long(10)),getParty(new Long(13)),getCandidate(new Long(111)),null,null,null,null,getCandidateResult(new Long(111)), null,null,null);
		Nomination n112 = new Nomination(new Long(112),getConstituencyElection(new Long(10)),getParty(new Long(13)),getCandidate(new Long(112)),null,null,null,null,
				getCandidateResult(new Long(112)), null,null,null);
		
		Nomination n113 = new Nomination(new Long(113),getConstituencyElection(new Long(10)),getParty(new Long(13)),getCandidate(new Long(105)),null,null,null,null,getCandidateResult(new Long(113)), null,null,null);
		Nomination n114 = new Nomination(new Long(114),getConstituencyElection(new Long(10)),getParty(new Long(13)),getCandidate(new Long(113)),null,null,null,null,getCandidateResult(new Long(114)), null,null,null);
		Nomination n115 = new Nomination(new Long(115),getConstituencyElection(new Long(10)),getParty(new Long(13)),getCandidate(new Long(114)),null,null,null,null,getCandidateResult(new Long(115)), null,null,null);
		Nomination n116 = new Nomination(new Long(116),getConstituencyElection(new Long(10)),getParty(new Long(13)),getCandidate(new Long(115)),null,null,null,null,getCandidateResult(new Long(116)), null,null,null);
		Nomination n117 = new Nomination(new Long(117),getConstituencyElection(new Long(10)),getParty(new Long(13)),getCandidate(new Long(116)),null,null,null,null,getCandidateResult(new Long(117)), null,null,null);
		Nomination n118 = new Nomination(new Long(118),getConstituencyElection(new Long(10)),getParty(new Long(13)),getCandidate(new Long(117)),null,null,null,null,getCandidateResult(new Long(118)), null,null,null);
		Nomination n119 = new Nomination(new Long(119),getConstituencyElection(new Long(10)),getParty(new Long(13)),getCandidate(new Long(118)),null,null,null,null,getCandidateResult(new Long(119)), null,null,null);
		Nomination n120 = new Nomination(new Long(120),getConstituencyElection(new Long(10)),getParty(new Long(13)),getCandidate(new Long(119)),null,null,null,null,getCandidateResult(new Long(120)), null,null,null);
		Nomination n121 = new Nomination(new Long(122),getConstituencyElection(new Long(11)),getParty(new Long(1)),getCandidate(new Long(120)),null,null,null,null,
				getCandidateResult(new Long(121)), null,null,null);
		Nomination n122 = new Nomination(new Long(123),getConstituencyElection(new Long(11)),getParty(new Long(11)),getCandidate(new Long(121)),null,null,null,null,
				getCandidateResult(new Long(122)), null,null,null);
		Nomination n123 = new Nomination(new Long(124),getConstituencyElection(new Long(11)),getParty(new Long(14)),getCandidate(new Long(122)),null,null,null,null,
				getCandidateResult(new Long(123)), null,null,null);
		Nomination n124 = new Nomination(new Long(125),getConstituencyElection(new Long(11)),getParty(new Long(13)),getCandidate(new Long(123)),null,null,null,null,
				getCandidateResult(new Long(124)), null,null,null);
		Nomination n125 = new Nomination(new Long(126),getConstituencyElection(new Long(11)),getParty(new Long(13)),getCandidate(new Long(124)),null,null,null,null,
				getCandidateResult(new Long(123)), null,null,null);
		Nomination n126 = new Nomination(new Long(128),getConstituencyElection(new Long(20)),getParty(new Long(1)),getCandidate(new Long(2)),null,null,null,null,
				getCandidateResult(new Long(126)), null,null,null);
		Nomination n127 = new Nomination(new Long(129),getConstituencyElection(new Long(20)),getParty(new Long(11)),getCandidate(new Long(1)),null,null,null,null,
				getCandidateResult(new Long(127)), null,null,null);
		Nomination n128 = new Nomination(new Long(130),getConstituencyElection(new Long(20)),getParty(new Long(13)),getCandidate(new Long(125)),null,null,null,null,
				getCandidateResult(new Long(128)), null,null,null);
		Nomination n129 = new Nomination(new Long(131),getConstituencyElection(new Long(20)),getParty(new Long(6)),getCandidate(new Long(126)),null,null,null,null,
				getCandidateResult(new Long(129)), null,null,null);
		Nomination n130 = new Nomination(new Long(132),getConstituencyElection(new Long(20)),getParty(new Long(13)),getCandidate(new Long(127)),null,null,null,null,
				getCandidateResult(new Long(130)), null,null,null);
		Nomination n131 = new Nomination(new Long(134),getConstituencyElection(new Long(13)),getParty(new Long(1)),getCandidate(new Long(23)),null,null,null,null,
				getCandidateResult(new Long(131)), null,null,null);
		Nomination n132 = new Nomination(new Long(135),getConstituencyElection(new Long(13)),getParty(new Long(11)),getCandidate(new Long(22)),null,null,null,null,
				getCandidateResult(new Long(132)), null,null,null);
		Nomination n133 = new Nomination(new Long(136),getConstituencyElection(new Long(13)),getParty(new Long(5)),getCandidate(new Long(128)),null,null,null,null,
				getCandidateResult(new Long(133)), null,null,null);
		Nomination n134 = new Nomination(new Long(137),getConstituencyElection(new Long(13)),getParty(new Long(3)),getCandidate(new Long(129)),null,null,null,null,
				getCandidateResult(new Long(134)), null,null,null);
		Nomination n135 = new Nomination(new Long(138),getConstituencyElection(new Long(13)),getParty(new Long(13)),getCandidate(new Long(130)),null,null,null,null,
				getCandidateResult(new Long(135)), null,null,null);
		Nomination n136 = new Nomination(new Long(139),getConstituencyElection(new Long(13)),getParty(new Long(44)),getCandidate(new Long(131)),null,null,null,null,
				getCandidateResult(new Long(136)), null,null,null);
		Nomination n137 = new Nomination(new Long(140),getConstituencyElection(new Long(13)),getParty(new Long(13)),getCandidate(new Long(132)),null,null,null,null,
				getCandidateResult(new Long(137)), null,null,null);
		Nomination n138 = new Nomination(new Long(141),getConstituencyElection(new Long(13)),getParty(new Long(13)),getCandidate(new Long(133)),null,null,null,null,
				getCandidateResult(new Long(138)), null,null,null);
		Nomination n139 = new Nomination(new Long(142),getConstituencyElection(new Long(13)),getParty(new Long(13)),getCandidate(new Long(134)),null,null,null,null,
				getCandidateResult(new Long(139)), null,null,null);
		Nomination n140 = new Nomination(new Long(144),getConstituencyElection(new Long(12)),getParty(new Long(13)),getCandidate(new Long(12)),null,null,null,null,
				getCandidateResult(new Long(140)), null,null,null);
		Nomination n141 = new Nomination(new Long(145),getConstituencyElection(new Long(12)),getParty(new Long(2)),getCandidate(new Long(135)),null,null,null,null,
				getCandidateResult(new Long(141)), null,null,null);
		Nomination n142 = new Nomination(new Long(146),getConstituencyElection(new Long(12)),getParty(new Long(1)),getCandidate(new Long(136)),null,null,null,null,
				getCandidateResult(new Long(142)), null,null,null);
		Nomination n143 = new Nomination(new Long(147),getConstituencyElection(new Long(12)),getParty(new Long(13)),getCandidate(new Long(137)),null,null,null,null,
				getCandidateResult(new Long(143)), null,null,null);
		Nomination n144 = new Nomination(new Long(148),getConstituencyElection(new Long(12)),getParty(new Long(13)),getCandidate(new Long(138)),null,null,null,null,
				getCandidateResult(new Long(144)), null,null,null);
		Nomination n145 = new Nomination(new Long(149),getConstituencyElection(new Long(12)),getParty(new Long(13)),getCandidate(new Long(139)),null,null,null,null,
				getCandidateResult(new Long(145)), null,null,null);
		Nomination n146 = new Nomination(new Long(150),getConstituencyElection(new Long(12)),getParty(new Long(13)),getCandidate(new Long(140)),null,null,null,null,
				getCandidateResult(new Long(146)), null,null,null);
		Nomination n147 = new Nomination(new Long(151),getConstituencyElection(new Long(12)),getParty(new Long(13)),getCandidate(new Long(141)),null,null,null,null,
				getCandidateResult(new Long(147)), null,null,null);
		Nomination n148 = new Nomination(new Long(152),getConstituencyElection(new Long(12)),getParty(new Long(13)),getCandidate(new Long(142)),null,null,null,null,
				getCandidateResult(new Long(148)), null,null,null);
		Nomination n149 = new Nomination(new Long(154),getConstituencyElection(new Long(19)),getParty(new Long(1)),getCandidate(new Long(11)),null,null,null,null,
				getCandidateResult(new Long(149)), null,null,null);
		Nomination n150 = new Nomination(new Long(155),getConstituencyElection(new Long(19)),getParty(new Long(11)),getCandidate(new Long(143)),null,null,null,null,
				getCandidateResult(new Long(150)), null,null,null);
		Nomination n151 = new Nomination(new Long(156),getConstituencyElection(new Long(19)),getParty(new Long(54)),getCandidate(new Long(144)),null,null,null,null,
				getCandidateResult(new Long(151)), null,null,null);
		Nomination n152 = new Nomination(new Long(157),getConstituencyElection(new Long(19)),getParty(new Long(13)),getCandidate(new Long(145)),null,null,null,null,getCandidateResult(new Long(152)), null,null,null);
		Nomination n153 = new Nomination(new Long(158),getConstituencyElection(new Long(19)),getParty(new Long(13)),getCandidate(new Long(146)),null,null,null,null,getCandidateResult(new Long(153)), null,null,null);
		Nomination n154 = new Nomination(new Long(159),getConstituencyElection(new Long(19)),getParty(new Long(13)),getCandidate(new Long(147)),null,null,null,null,getCandidateResult(new Long(154)), null,null,null);
		Nomination n155 = new Nomination(new Long(161),getConstituencyElection(new Long(21)),getParty(new Long(1)),getCandidate(new Long(45)),null,null,null,null,getCandidateResult(new Long(155)), null,null,null);
		Nomination n156 = new Nomination(new Long(162),getConstituencyElection(new Long(21)),getParty(new Long(2)),getCandidate(new Long(148)),null,null,null,null,
				getCandidateResult(new Long(156)), null,null,null);
		Nomination n157 = new Nomination(new Long(163),getConstituencyElection(new Long(21)),getParty(new Long(3)),getCandidate(new Long(149)),null,null,null,null,getCandidateResult(new Long(157)), null,null,null);
		Nomination n158 = new Nomination(new Long(164),getConstituencyElection(new Long(21)),getParty(new Long(13)),getCandidate(new Long(44)),null,null,null,null,getCandidateResult(new Long(158)), null,null,null);
		Nomination n159 = new Nomination(new Long(165),getConstituencyElection(new Long(21)),getParty(new Long(6)),getCandidate(new Long(150)),null,null,null,null,getCandidateResult(new Long(159)), null,null,null);
		Nomination n160 = new Nomination(new Long(166),getConstituencyElection(new Long(21)),getParty(new Long(13)),getCandidate(new Long(151)),null,null,null,null,
				getCandidateResult(new Long(160)), null,null,null);
		Nomination n161 = new Nomination(new Long(168),getConstituencyElection(new Long(14)),getParty(new Long(1)),getCandidate(new Long(56)),null,null,null,null,getCandidateResult(new Long(161)), null,null,null);
		Nomination n162 = new Nomination(new Long(169),getConstituencyElection(new Long(14)),getParty(new Long(11)),getCandidate(new Long(57)),null,null,null,null,getCandidateResult(new Long(162)), null,null,null);
		Nomination n163 = new Nomination(new Long(170),getConstituencyElection(new Long(14)),getParty(new Long(3)),getCandidate(new Long(59)),null,null,null,null,getCandidateResult(new Long(163)), null,null,null);
		Nomination n164 = new Nomination(new Long(171),getConstituencyElection(new Long(14)),getParty(new Long(13)),getCandidate(new Long(152)),null,null,null,null,getCandidateResult(new Long(164)), null,null,null);
		Nomination n165 = new Nomination(new Long(172),getConstituencyElection(new Long(14)),getParty(new Long(6)),getCandidate(new Long(153)),null,null,null,null,getCandidateResult(new Long(165)), null,null,null);
		Nomination n166 = new Nomination(new Long(173),getConstituencyElection(new Long(14)),getParty(new Long(13)),getCandidate(new Long(154)),null,null,null,null,getCandidateResult(new Long(166)), null,null,null);
		Nomination n167 = new Nomination(new Long(174),getConstituencyElection(new Long(14)),getParty(new Long(13)),getCandidate(new Long(155)),null,null,null,null,getCandidateResult(new Long(167)), null,null,null);
		Nomination n168 = new Nomination(new Long(175),getConstituencyElection(new Long(14)),getParty(new Long(13)),getCandidate(new Long(156)),null,null,null,null,getCandidateResult(new Long(168)), null,null,null);
		Nomination n169 = new Nomination(new Long(176),getConstituencyElection(new Long(14)),getParty(new Long(44)),getCandidate(new Long(157)),null,null,null,null,getCandidateResult(new Long(169)), null,null,null);
		Nomination n170 = new Nomination(new Long(177),getConstituencyElection(new Long(14)),getParty(new Long(13)),getCandidate(new Long(158)),null,null,null,null,getCandidateResult(new Long(170)), null,null,null);
		Nomination n171 = new Nomination(new Long(178),getConstituencyElection(new Long(14)),getParty(new Long(13)),getCandidate(new Long(159)),null,null,null,null,getCandidateResult(new Long(171)), null,null,null);
		Nomination n172 = new Nomination(new Long(179),getConstituencyElection(new Long(14)),getParty(new Long(13)),getCandidate(new Long(160)),null,null,null,null,getCandidateResult(new Long(172)), null,null,null);
		Nomination n173 = new Nomination(new Long(180),getConstituencyElection(new Long(14)),getParty(new Long(13)),getCandidate(new Long(161)),null,null,null,null,getCandidateResult(new Long(73)), null,null,null);
		Nomination n174 = new Nomination(new Long(182),getConstituencyElection(new Long(15)),getParty(new Long(1)),getCandidate(new Long(162)),null,null,null,null,
				getCandidateResult(new Long(174)), null,null,null);
		Nomination n175 = new Nomination(new Long(183),getConstituencyElection(new Long(15)),getParty(new Long(11)),getCandidate(new Long(163)),null,null,null,null,getCandidateResult(new Long(175)), null,null,null);
		Nomination n176 = new Nomination(new Long(184),getConstituencyElection(new Long(15)),getParty(new Long(3)),getCandidate(new Long(164)),null,null,null,null,getCandidateResult(new Long(176)), null,null,null);
		Nomination n177 = new Nomination(new Long(185),getConstituencyElection(new Long(15)),getParty(new Long(13)),getCandidate(new Long(165)),null,null,null,null,getCandidateResult(new Long(177)), null,null,null);
		Nomination n178 = new Nomination(new Long(186),getConstituencyElection(new Long(15)),getParty(new Long(13)),getCandidate(new Long(166)),null,null,null,null,getCandidateResult(new Long(178)), null,null,null);
		Nomination n179 = new Nomination(new Long(188),getConstituencyElection(new Long(16)),getParty(new Long(1)),getCandidate(new Long(167)),null,null,null,null,getCandidateResult(new Long(179)), null,null,null);
		Nomination n180 = new Nomination(new Long(189),getConstituencyElection(new Long(16)),getParty(new Long(11)),getCandidate(new Long(168)),null,null,null,null,
				getCandidateResult(new Long(180)), null,null,null);
		Nomination n181 = new Nomination(new Long(190),getConstituencyElection(new Long(16)),getParty(new Long(3)),getCandidate(new Long(169)),null,null,null,null,getCandidateResult(new Long(181)), null,null,null);
		Nomination n182 = new Nomination(new Long(191),getConstituencyElection(new Long(16)),getParty(new Long(13)),getCandidate(new Long(170)),null,null,null,null,getCandidateResult(new Long(182)), null,null,null);
		Nomination n183 = new Nomination(new Long(192),getConstituencyElection(new Long(16)),getParty(new Long(13)),getCandidate(new Long(171)),null,null,null,null,getCandidateResult(new Long(183)), null,null,null);
		Nomination n184 = new Nomination(new Long(193),getConstituencyElection(new Long(16)),getParty(new Long(13)),getCandidate(new Long(172)),null,null,null,null,getCandidateResult(new Long(184)), null,null,null);
		Nomination n185 = new Nomination(new Long(194),getConstituencyElection(new Long(16)),getParty(new Long(13)),getCandidate(new Long(173)),null,null,null,null,
				getCandidateResult(new Long(185)), null,null,null);
		Nomination n186 = new Nomination(new Long(195),getConstituencyElection(new Long(16)),getParty(new Long(13)),getCandidate(new Long(82)),null,null,null,null,
				getCandidateResult(new Long(186)), null,null,null);
		Nomination n187 = new Nomination(new Long(197),getConstituencyElection(new Long(17)),getParty(new Long(1)),getCandidate(new Long(174)),null,null,null,null,
				getCandidateResult(new Long(187)), null,null,null);
		Nomination n188 = new Nomination(new Long(198),getConstituencyElection(new Long(17)),getParty(new Long(11)),getCandidate(new Long(175)),null,null,null,null,
				getCandidateResult(new Long(188)), null,null,null);
		Nomination n189 = new Nomination(new Long(199),getConstituencyElection(new Long(17)),getParty(new Long(3)),getCandidate(new Long(176)),null,null,null,null,
				getCandidateResult(new Long(189)), null,null,null);
		Nomination n190 = new Nomination(new Long(200),getConstituencyElection(new Long(17)),getParty(new Long(62)),getCandidate(new Long(177)),null,null,null,null,
				getCandidateResult(new Long(190)), null,null,null);
		Nomination n191 = new Nomination(new Long(201),getConstituencyElection(new Long(17)),getParty(new Long(6)),getCandidate(new Long(178)),null,null,null,null,
				getCandidateResult(new Long(191)), null,null,null);
		 
		 
		nominations.add(n1);
		nominations.add(n2);
		nominations.add(n3);
		nominations.add(n4);
		nominations.add(n5);
		nominations.add(n6);
		nominations.add(n7);
		nominations.add(n8);
		nominations.add(n9);
		nominations.add(n10);
		nominations.add(n11);
		nominations.add(n12);
		nominations.add(n13);
		nominations.add(n14);
		nominations.add(n15);
		nominations.add(n16);
		nominations.add(n17);
		nominations.add(n18);
		nominations.add(n19);
		nominations.add(n20);
		nominations.add(n21);
		nominations.add(n22);
		nominations.add(n23);
		nominations.add(n24);
		nominations.add(n25);
		nominations.add(n26);
		nominations.add(n27);
		nominations.add(n28);		nominations.add(n29);		nominations.add(n30);
		nominations.add(n31);
		nominations.add(n32);
		nominations.add(n33);
		nominations.add(n34);
		nominations.add(n35);
		nominations.add(n36);
		nominations.add(n37);
		nominations.add(n38);
		nominations.add(n39);
		nominations.add(n40);
		nominations.add(n41);
		nominations.add(n42);
		nominations.add(n43);
		nominations.add(n44);
		nominations.add(n45);
		nominations.add(n46);
		nominations.add(n47);
		nominations.add(n48);
		nominations.add(n49);
		nominations.add(n50);
		nominations.add(n51);
		nominations.add(n52);
		nominations.add(n53);
		nominations.add(n54);
		nominations.add(n55);
		nominations.add(n56);
		nominations.add(n57);
		nominations.add(n58);
		nominations.add(n59);
		nominations.add(n60);
		nominations.add(n61);
		nominations.add(n62);
		nominations.add(n63);
		nominations.add(n64);
		nominations.add(n65);
		nominations.add(n66);
		nominations.add(n67);
		nominations.add(n68);
		nominations.add(n69);
		nominations.add(n70);
		nominations.add(n71);
		nominations.add(n72);
		nominations.add(n73);
		nominations.add(n74);
		nominations.add(n75);
		nominations.add(n76);
		nominations.add(n77);
		nominations.add(n78);
		nominations.add(n79);
		nominations.add(n80);
		nominations.add(n81);
		nominations.add(n82);
		nominations.add(n83);
		nominations.add(n84);
		nominations.add(n85);
		nominations.add(n86);
		nominations.add(n87);
		nominations.add(n88);
		nominations.add(n89);
		nominations.add(n90);
		nominations.add(n91);
		nominations.add(n92);
		nominations.add(n93);
		nominations.add(n94);
		nominations.add(n95);
		nominations.add(n96);
		nominations.add(n97);
		nominations.add(n98);
		nominations.add(n99);
		nominations.add(n100);
		nominations.add(n101);
		nominations.add(n102);
		nominations.add(n103);
		nominations.add(n104);
		nominations.add(n105);
		nominations.add(n106);
		nominations.add(n107);
		nominations.add(n108);
		nominations.add(n109);
		nominations.add(n110);
		nominations.add(n111);
		nominations.add(n112);
		nominations.add(n113);
		nominations.add(n114);
		nominations.add(n115);
		nominations.add(n116);
		nominations.add(n117);
		nominations.add(n118);
		nominations.add(n119);
		nominations.add(n120);
		nominations.add(n121);
		nominations.add(n122);
		nominations.add(n123);
		nominations.add(n124);
		nominations.add(n125);
		nominations.add(n126);
		nominations.add(n127);
		nominations.add(n128);
		nominations.add(n129);
		nominations.add(n130);
		nominations.add(n131);
		nominations.add(n132);
		nominations.add(n133);
		nominations.add(n134);
		nominations.add(n135);
		nominations.add(n136);
		nominations.add(n137);
		nominations.add(n138);
		nominations.add(n139);
		nominations.add(n140);
		nominations.add(n141);
		nominations.add(n142);
		nominations.add(n143);
		nominations.add(n144);
		nominations.add(n145);
		nominations.add(n146);
		nominations.add(n147);
		nominations.add(n148);
		nominations.add(n149);
		nominations.add(n150);
		nominations.add(n151);
		nominations.add(n152);
		nominations.add(n153);
		nominations.add(n154);
		nominations.add(n155);
		nominations.add(n156);
		nominations.add(n157);
		nominations.add(n158);
		nominations.add(n159);
		nominations.add(n160);
		nominations.add(n161);
		nominations.add(n162);
		nominations.add(n163);
		nominations.add(n164);
		nominations.add(n165);
		nominations.add(n166);
		nominations.add(n167);
		nominations.add(n168);
		nominations.add(n169);
		nominations.add(n170);
		nominations.add(n171);
		nominations.add(n172);
		nominations.add(n173);
		nominations.add(n174);
		nominations.add(n175);
		nominations.add(n176);
		nominations.add(n177);
		nominations.add(n178);
		nominations.add(n179);
		nominations.add(n180);
		nominations.add(n181);
		nominations.add(n182);
		nominations.add(n183);
		nominations.add(n184);
		nominations.add(n185);
		nominations.add(n186);
		nominations.add(n187);
		nominations.add(n188);
		nominations.add(n189);
		nominations.add(n190);
		nominations.add(n191);
// setting candidateresults with nominations
		CandidateResult cr1 =candidateResults.get(0);
		CandidateResult cr2 =candidateResults.get(1);
		CandidateResult cr3 =candidateResults.get(2);
		CandidateResult cr4 =candidateResults.get(3);
		CandidateResult cr5 =candidateResults.get(4);
		CandidateResult cr6 =candidateResults.get(5);
		CandidateResult cr7 =candidateResults.get(6);
		CandidateResult cr8 =candidateResults.get(7);
		CandidateResult cr9 =candidateResults.get(8);
		CandidateResult cr10 =candidateResults.get(9);
		CandidateResult cr11 =candidateResults.get(10);
		CandidateResult cr12 =candidateResults.get(11);
		CandidateResult cr13 =candidateResults.get(12);
		CandidateResult cr14 =candidateResults.get(13);
		CandidateResult cr15 =candidateResults.get(14);
		CandidateResult cr16 =candidateResults.get(15);
		CandidateResult cr17 =candidateResults.get(16);
		CandidateResult cr18 =candidateResults.get(17);
		CandidateResult cr19 =candidateResults.get(18);
		CandidateResult cr20 =candidateResults.get(19);
		CandidateResult cr21 =candidateResults.get(20);
		CandidateResult cr22 =candidateResults.get(21);
		CandidateResult cr23 =candidateResults.get(22);
		CandidateResult cr24 =candidateResults.get(23);
		CandidateResult cr25 =candidateResults.get(24);
		CandidateResult cr26 =candidateResults.get(25);
		CandidateResult cr27 =candidateResults.get(26);
		CandidateResult cr28 =candidateResults.get(27);
		CandidateResult cr29 =candidateResults.get(28);
		CandidateResult cr30 =candidateResults.get(29);
		CandidateResult cr31 =candidateResults.get(30);
		CandidateResult cr32 =candidateResults.get(31);
		CandidateResult cr33 =candidateResults.get(32);
		CandidateResult cr34 =candidateResults.get(33);
		CandidateResult cr35 =candidateResults.get(34);
		CandidateResult cr36 =candidateResults.get(35);
		CandidateResult cr37 =candidateResults.get(36);
		CandidateResult cr38 =candidateResults.get(37);
		CandidateResult cr39 =candidateResults.get(38);
		CandidateResult cr40 =candidateResults.get(39);
		CandidateResult cr41 =candidateResults.get(40);
		CandidateResult cr42 =candidateResults.get(41);
		CandidateResult cr43 =candidateResults.get(42);
		CandidateResult cr44 =candidateResults.get(43);
		CandidateResult cr45 =candidateResults.get(44);
		CandidateResult cr46 =candidateResults.get(45);
		CandidateResult cr47 =candidateResults.get(46);
		CandidateResult cr48 =candidateResults.get(47);
		CandidateResult cr49 =candidateResults.get(48);
		CandidateResult cr50 =candidateResults.get(49);
		CandidateResult cr51 =candidateResults.get(50);
		CandidateResult cr52 =candidateResults.get(51);
		CandidateResult cr53 =candidateResults.get(52);
		CandidateResult cr54 =candidateResults.get(53);
		CandidateResult cr55 =candidateResults.get(54);
		CandidateResult cr56 =candidateResults.get(55);

		CandidateResult cr57 =candidateResults.get(56);	CandidateResult cr58 =candidateResults.get(57);	CandidateResult cr59 =candidateResults.get(58);
		CandidateResult cr60 =candidateResults.get(59);	CandidateResult cr61 =candidateResults.get(60);	CandidateResult cr62 =candidateResults.get(61);
		CandidateResult cr63 =candidateResults.get(62);	CandidateResult cr64 =candidateResults.get(63);	CandidateResult cr65 =candidateResults.get(64);
		CandidateResult cr68 =candidateResults.get(67);	CandidateResult cr67 =candidateResults.get(66);	CandidateResult cr66 =candidateResults.get(65);
		CandidateResult cr69 =candidateResults.get(68);	CandidateResult cr70 =candidateResults.get(69);	CandidateResult cr71 =candidateResults.get(70);
		CandidateResult cr74 =candidateResults.get(73);	CandidateResult cr73 =candidateResults.get(72);	CandidateResult cr72 =candidateResults.get(71);
		CandidateResult cr75 =candidateResults.get(74);	CandidateResult cr76 =candidateResults.get(75);	CandidateResult cr77 =candidateResults.get(76);
		CandidateResult cr80 =candidateResults.get(79);	CandidateResult cr79 =candidateResults.get(78);	CandidateResult cr78 =candidateResults.get(77);
		CandidateResult cr81 =candidateResults.get(80);	CandidateResult cr82 =candidateResults.get(81);	CandidateResult cr83 =candidateResults.get(82);
		CandidateResult cr86 =candidateResults.get(85);	CandidateResult cr85 =candidateResults.get(84);	CandidateResult cr84 =candidateResults.get(83);
		CandidateResult cr87 =candidateResults.get(86);	CandidateResult cr88 =candidateResults.get(87);	CandidateResult cr89 =candidateResults.get(88);
		CandidateResult cr92 =candidateResults.get(91);	CandidateResult cr91 =candidateResults.get(90);	CandidateResult cr90 =candidateResults.get(89);
		CandidateResult cr93 =candidateResults.get(92);	CandidateResult cr94 =candidateResults.get(93);	CandidateResult cr95 =candidateResults.get(94);
		CandidateResult cr97 =candidateResults.get(96);	CandidateResult cr96 =candidateResults.get(95);	//CandidateResult cr96 =candidateResults.get(95);
		CandidateResult cr98 =candidateResults.get(97);	CandidateResult cr99 =candidateResults.get(98);	CandidateResult cr100 =candidateResults.get(99);
		

		CandidateResult cr101 =candidateResults.get(100);
		CandidateResult cr102 =candidateResults.get(101);
		CandidateResult cr103 =candidateResults.get(102);
		CandidateResult cr104 =candidateResults.get(103);
		CandidateResult cr105 =candidateResults.get(104);
		CandidateResult cr106 =candidateResults.get(105);
		CandidateResult cr107 =candidateResults.get(106);
		CandidateResult cr108 =candidateResults.get(107);
		CandidateResult cr109 =candidateResults.get(108);
		CandidateResult cr110 =candidateResults.get(109);
		CandidateResult cr111 =candidateResults.get(110);
		CandidateResult cr112 =candidateResults.get(111);
		CandidateResult cr113 =candidateResults.get(112);
		CandidateResult cr114 =candidateResults.get(113);
		CandidateResult cr115 =candidateResults.get(114);
		CandidateResult cr116 =candidateResults.get(115);
		CandidateResult cr117 =candidateResults.get(116);
		CandidateResult cr118 =candidateResults.get(117);
		CandidateResult cr119 =candidateResults.get(118);
		CandidateResult cr120 =candidateResults.get(119); 
		// today
		
		CandidateResult cr121 =candidateResults.get(120);
		CandidateResult cr122 =candidateResults.get(121);
		CandidateResult cr123 =candidateResults.get(122);
		CandidateResult cr124 =candidateResults.get(123);
		CandidateResult cr125 =candidateResults.get(124);
		CandidateResult cr126 =candidateResults.get(125);
		CandidateResult cr127 =candidateResults.get(126);
		CandidateResult cr128 =candidateResults.get(127);
		CandidateResult cr129 =candidateResults.get(128);
		CandidateResult cr130 =candidateResults.get(129);
		CandidateResult cr131 =candidateResults.get(130);
		CandidateResult cr132 =candidateResults.get(131);
		CandidateResult cr133 =candidateResults.get(132);
		CandidateResult cr134 =candidateResults.get(133);
		CandidateResult cr135 =candidateResults.get(134);
		CandidateResult cr136 =candidateResults.get(135);
		CandidateResult cr137 =candidateResults.get(136);
		CandidateResult cr138 =candidateResults.get(137);
		CandidateResult cr139 =candidateResults.get(138);
		CandidateResult cr140 =candidateResults.get(139);
		CandidateResult cr141 =candidateResults.get(140);
		CandidateResult cr142 =candidateResults.get(141);
		CandidateResult cr143 =candidateResults.get(142);
		CandidateResult cr144 =candidateResults.get(143);
		CandidateResult cr145 =candidateResults.get(144);
		CandidateResult cr146 =candidateResults.get(145);
		CandidateResult cr147 =candidateResults.get(146);
		CandidateResult cr148 =candidateResults.get(147);
		CandidateResult cr149 =candidateResults.get(148);
		CandidateResult cr150 =candidateResults.get(149);
		CandidateResult cr151 =candidateResults.get(150);
		CandidateResult cr152 =candidateResults.get(151);
		CandidateResult cr153 =candidateResults.get(152);
		CandidateResult cr154 =candidateResults.get(153);
		CandidateResult cr155 =candidateResults.get(154);
		CandidateResult cr156 =candidateResults.get(155);

		CandidateResult cr157 =candidateResults.get(156);	CandidateResult cr158 =candidateResults.get(157);	CandidateResult cr159 =candidateResults.get(158);
		CandidateResult cr160 =candidateResults.get(159);	CandidateResult cr161 =candidateResults.get(160);	CandidateResult cr162 =candidateResults.get(161);
		CandidateResult cr163 =candidateResults.get(162);	CandidateResult cr164 =candidateResults.get(163);	CandidateResult cr165 =candidateResults.get(164);
		CandidateResult cr168 =candidateResults.get(167);	CandidateResult cr167 =candidateResults.get(166);	CandidateResult cr166 =candidateResults.get(165);
		CandidateResult cr169 =candidateResults.get(168);	CandidateResult cr170 =candidateResults.get(169);	CandidateResult cr171 =candidateResults.get(170);
		CandidateResult cr174 =candidateResults.get(173);	CandidateResult cr173 =candidateResults.get(172);	CandidateResult cr172 =candidateResults.get(171);
		CandidateResult cr175 =candidateResults.get(174);	CandidateResult cr176 =candidateResults.get(175);	CandidateResult cr177 =candidateResults.get(176);
		CandidateResult cr180 =candidateResults.get(179);	CandidateResult cr179 =candidateResults.get(178);	CandidateResult cr178 =candidateResults.get(177);
		CandidateResult cr181 =candidateResults.get(180);	CandidateResult cr182 =candidateResults.get(181);	CandidateResult cr183 =candidateResults.get(182);
		CandidateResult cr186 =candidateResults.get(185);	CandidateResult cr185 =candidateResults.get(184);	CandidateResult cr184 =candidateResults.get(183);
		CandidateResult cr187 =candidateResults.get(186);	CandidateResult cr188 =candidateResults.get(187);	CandidateResult cr189 =candidateResults.get(188);
		CandidateResult cr191 =candidateResults.get(190);	CandidateResult cr190 =candidateResults.get(189);
		 
		
		cr1.setNomination(getNomination(new Long(1)));		cr2.setNomination(getNomination(new Long(2)));
		cr3.setNomination(getNomination(new Long(3)));		cr4.setNomination(getNomination(new Long(4)));
		cr6.setNomination(getNomination(new Long(6)));		cr5.setNomination(getNomination(new Long(5)));
		cr7.setNomination(getNomination(new Long(7)));		
		cr9.setNomination(getNomination(new Long(9)));		cr8.setNomination(getNomination(new Long(8)));
		cr10.setNomination(getNomination(new Long(10)));	cr11.setNomination(getNomination(new Long(11)));
		cr13.setNomination(getNomination(new Long(13)));		cr12.setNomination(getNomination(new Long(12)));
		cr14.setNomination(getNomination(new Long(14)));		cr15.setNomination(getNomination(new Long(15)));
		cr17.setNomination(getNomination(new Long(17)));		cr16.setNomination(getNomination(new Long(16)));
		cr18.setNomination(getNomination(new Long(18)));		cr19.setNomination(getNomination(new Long(19)));
		cr21.setNomination(getNomination(new Long(21)));		cr20.setNomination(getNomination(new Long(20)));
		cr22.setNomination(getNomination(new Long(22)));		cr23.setNomination(getNomination(new Long(23)));
		cr25.setNomination(getNomination(new Long(25)));		cr24.setNomination(getNomination(new Long(24)));
		cr26.setNomination(getNomination(new Long(26)));		cr27.setNomination(getNomination(new Long(27)));
		cr29.setNomination(getNomination(new Long(29)));		cr28.setNomination(getNomination(new Long(28)));
		cr30.setNomination(getNomination(new Long(30)));		cr31.setNomination(getNomination(new Long(31)));
		cr33.setNomination(getNomination(new Long(33)));		cr32.setNomination(getNomination(new Long(32)));
		cr34.setNomination(getNomination(new Long(34)));		cr35.setNomination(getNomination(new Long(35)));
		cr37.setNomination(getNomination(new Long(37)));		cr36.setNomination(getNomination(new Long(36)));
		cr38.setNomination(getNomination(new Long(38)));		cr39.setNomination(getNomination(new Long(39)));
		cr41.setNomination(getNomination(new Long(41)));		cr40.setNomination(getNomination(new Long(40)));
		cr42.setNomination(getNomination(new Long(42)));		cr43.setNomination(getNomination(new Long(43)));
		cr45.setNomination(getNomination(new Long(45)));		cr44.setNomination(getNomination(new Long(44)));
		cr46.setNomination(getNomination(new Long(46)));		cr47.setNomination(getNomination(new Long(47)));
		cr49.setNomination(getNomination(new Long(49)));		cr48.setNomination(getNomination(new Long(48)));
		cr50.setNomination(getNomination(new Long(50)));		cr51.setNomination(getNomination(new Long(51)));
		cr52.setNomination(getNomination(new Long(52)));		cr53.setNomination(getNomination(new Long(53)));
		cr55.setNomination(getNomination(new Long(55)));		cr54.setNomination(getNomination(new Long(54)));
		cr56.setNomination(getNomination(new Long(56)));		cr57.setNomination(getNomination(new Long(57)));
		


		//cr57.setNomination(getNomination(new Long()));		
		cr58.setNomination(getNomination(new Long(58)));
		cr59.setNomination(getNomination(new Long(59)));		cr60.setNomination(getNomination(new Long(60)));
		cr62.setNomination(getNomination(new Long(62)));		cr61.setNomination(getNomination(new Long(61)));
		cr63.setNomination(getNomination(new Long(63)));		cr64.setNomination(getNomination(new Long(64)));
		cr66.setNomination(getNomination(new Long(66)));		cr65.setNomination(getNomination(new Long(65)));
		cr67.setNomination(getNomination(new Long(67)));		cr68.setNomination(getNomination(new Long(68)));
		cr70.setNomination(getNomination(new Long(70)));		cr69.setNomination(getNomination(new Long(69)));
		cr71.setNomination(getNomination(new Long(71)));		cr72.setNomination(getNomination(new Long(72)));
		cr74.setNomination(getNomination(new Long(74)));		cr73.setNomination(getNomination(new Long(73)));
		cr75.setNomination(getNomination(new Long(75)));		cr76.setNomination(getNomination(new Long(76)));
		cr78.setNomination(getNomination(new Long(78)));		cr77.setNomination(getNomination(new Long(77)));
		cr79.setNomination(getNomination(new Long(79)));		cr80.setNomination(getNomination(new Long(80)));
		cr82.setNomination(getNomination(new Long(82)));		cr81.setNomination(getNomination(new Long(81)));
		cr83.setNomination(getNomination(new Long(83)));		cr84.setNomination(getNomination(new Long(84)));
		cr86.setNomination(getNomination(new Long(86)));		cr85.setNomination(getNomination(new Long(85)));
		cr87.setNomination(getNomination(new Long(87)));		cr88.setNomination(getNomination(new Long(88)));
		cr90.setNomination(getNomination(new Long(90)));		cr89.setNomination(getNomination(new Long(89)));
		cr91.setNomination(getNomination(new Long(91)));		cr92.setNomination(getNomination(new Long(92)));
		cr94.setNomination(getNomination(new Long(94)));		cr93.setNomination(getNomination(new Long(93)));
		cr95.setNomination(getNomination(new Long(95)));		cr96.setNomination(getNomination(new Long(96)));
		cr98.setNomination(getNomination(new Long(98)));		cr97.setNomination(getNomination(new Long(97)));
		cr99.setNomination(getNomination(new Long(99)));		cr100.setNomination(getNomination(new Long(100)));
 

		cr101.setNomination(getNomination(new Long(101)));		cr102.setNomination(getNomination(new Long(102)));
		cr103.setNomination(getNomination(new Long(103)));		cr104.setNomination(getNomination(new Long(104)));
		cr106.setNomination(getNomination(new Long(106)));		cr105.setNomination(getNomination(new Long(105)));
		cr107.setNomination(getNomination(new Long(107)));		
		cr109.setNomination(getNomination(new Long(109)));		cr108.setNomination(getNomination(new Long(108)));
		cr110.setNomination(getNomination(new Long(110)));		cr111.setNomination(getNomination(new Long(111)));
		cr113.setNomination(getNomination(new Long(113)));		cr112.setNomination(getNomination(new Long(112)));
		cr114.setNomination(getNomination(new Long(114)));		cr115.setNomination(getNomination(new Long(115)));
		cr117.setNomination(getNomination(new Long(117)));		cr116.setNomination(getNomination(new Long(116)));
		cr118.setNomination(getNomination(new Long(118)));		cr119.setNomination(getNomination(new Long(119)));
		cr120.setNomination(getNomination(new Long(120)));
		//today
		cr121.setNomination(getNomination(new Long(122)));
		cr122.setNomination(getNomination(new Long(123)));		cr123.setNomination(getNomination(new Long(124)));
		cr125.setNomination(getNomination(new Long(126)));		cr124.setNomination(getNomination(new Long(125)));
		cr126.setNomination(getNomination(new Long(128)));		cr127.setNomination(getNomination(new Long(129)));
		cr129.setNomination(getNomination(new Long(131)));		cr128.setNomination(getNomination(new Long(130)));
		cr130.setNomination(getNomination(new Long(132)));		cr131.setNomination(getNomination(new Long(134)));
		cr133.setNomination(getNomination(new Long(136)));		cr132.setNomination(getNomination(new Long(135)));
		cr134.setNomination(getNomination(new Long(137)));		cr135.setNomination(getNomination(new Long(138)));
		cr137.setNomination(getNomination(new Long(140)));		cr136.setNomination(getNomination(new Long(139)));
		cr138.setNomination(getNomination(new Long(141)));		cr139.setNomination(getNomination(new Long(142)));
		cr141.setNomination(getNomination(new Long(145)));		cr140.setNomination(getNomination(new Long(144)));
		cr142.setNomination(getNomination(new Long(146)));		cr143.setNomination(getNomination(new Long(147)));
		cr145.setNomination(getNomination(new Long(149)));		cr144.setNomination(getNomination(new Long(148)));
		cr146.setNomination(getNomination(new Long(150)));		cr147.setNomination(getNomination(new Long(151)));
		cr149.setNomination(getNomination(new Long(154)));		cr148.setNomination(getNomination(new Long(152)));
		cr150.setNomination(getNomination(new Long(155)));		cr151.setNomination(getNomination(new Long(156)));
		cr152.setNomination(getNomination(new Long(157)));		cr153.setNomination(getNomination(new Long(158)));
		cr155.setNomination(getNomination(new Long(161)));		cr154.setNomination(getNomination(new Long(159)));
		cr156.setNomination(getNomination(new Long(162)));		cr157.setNomination(getNomination(new Long(163)));
		


		//cr57.setNomination(getNomination(new Long()));		
		cr158.setNomination(getNomination(new Long(164)));
		cr159.setNomination(getNomination(new Long(165)));		cr160.setNomination(getNomination(new Long(166)));
		
		cr162.setNomination(getNomination(new Long(169)));		cr161.setNomination(getNomination(new Long(168)));
		cr163.setNomination(getNomination(new Long(170)));		cr164.setNomination(getNomination(new Long(171)));
		cr166.setNomination(getNomination(new Long(173)));		cr165.setNomination(getNomination(new Long(172)));
		cr167.setNomination(getNomination(new Long(174)));		cr168.setNomination(getNomination(new Long(175)));
		cr170.setNomination(getNomination(new Long(177)));		cr169.setNomination(getNomination(new Long(176)));
		cr171.setNomination(getNomination(new Long(178)));		cr172.setNomination(getNomination(new Long(179)));
		cr174.setNomination(getNomination(new Long(182)));		cr173.setNomination(getNomination(new Long(180)));
		cr175.setNomination(getNomination(new Long(183)));		cr176.setNomination(getNomination(new Long(184)));
		cr178.setNomination(getNomination(new Long(186)));		cr177.setNomination(getNomination(new Long(185)));
		cr179.setNomination(getNomination(new Long(188)));		cr180.setNomination(getNomination(new Long(189)));
		cr182.setNomination(getNomination(new Long(191)));		cr181.setNomination(getNomination(new Long(190)));
		cr183.setNomination(getNomination(new Long(192)));		cr184.setNomination(getNomination(new Long(193)));
		cr186.setNomination(getNomination(new Long(195)));		cr185.setNomination(getNomination(new Long(194)));
		cr187.setNomination(getNomination(new Long(197)));		cr188.setNomination(getNomination(new Long(198)));
		cr190.setNomination(getNomination(new Long(200)));		cr189.setNomination(getNomination(new Long(199)));
		cr191.setNomination(getNomination(new Long(201)));		
		
		
		return nominations;
	}
	public static List<District> getDistricts(Long stateId) {
		List<District> districts = new ArrayList<District>();
		districts.add(new District(new Long(1),"Adilabad","Adilabad",new Double(16.1), new Double(2082479), getState(),null,null,null,null, null,null,null));
		districts.add(new District(new Long(2), 	"Ananthapur",	"Ananthapur",	new Double(19.1),	new Double(3183814),	getState(),null	,null	,null,null, null,null,null));
		districts.add(new District(new Long(3), 	"Chittoor",	"Chittoor",	new Double(15.2),	new Double(3261118),	getState()	,null	,null	,null,null, null,null,null));
		districts.add(new District(new Long(4), 	"Cuddapah",	"Cuddapah",	new Double(15.4),	new Double(2267769),	getState()	,null	,null	,null,null, null,null,null));
		districts.add(new District(new Long(5),	"East Godavari",	"Kakinada",	new Double(10.8), new Double(4541222),	getState()	,null	,null	,null,null, null,null,null));
		districts.add(new District(new Long(6),	"Guntur",	"Guntur",	new Double(11.4),	new Double(4106999),	getState()	,null	,null	,null,null, null,null,null));
		districts.add(new District(new Long(7),	"Hyderabad",	"Hyderabad",	new Double(0.2),	new Double(3145939),	getState()	,null	,null	,null,null, null,null,null));
		districts.add(new District(new Long(8),	"Karimnagar",	"Karimnagar",	new Double(11.8),	new Double(3037486),	getState()	,null	,null	,null,null, null,null,null));
		districts.add(new District(new Long(9),	"Khammam",	"Khammam",	new Double(16),	new Double(2215809),	getState()	,null	,null	,null,null, null,null,null));
		districts.add(new District(new Long(10),	"Krishna",	"Machiapatnam",	new Double(18.7),	new Double(3698933), getState()	,null	,null	,null,null, null,null,null));
		districts.add(new District(new Long(11),	"Kurnool",	"Kurnool",	new Double(17.7),	new Double(2973024), getState(),  null	,null	,null,null, null,null,null));
		districts.add(new District(new Long(12),	"Mahaboobnagar",	"Mahaboobnagar",	new Double(18.4),	new Double(3077050), getState()	,null,null,null, null, null,null,null));
		districts.add(new District(new Long(13),	"Medak",	"Sangareddy",	new Double(9.7),	new Double(2269800), getState()	,null	,null	,null, null, null,null,null));
		districts.add(new District(new Long(14),	"Nalgonda",	"Nalgonda",	new Double(14.2),	new Double(2852092), getState()	,null	,null	,null, null, null,null,null));
		districts.add(new District(new Long(15),	"Nellore",	"Nellore",	new Double(13.1),	new Double(2392260), getState()	,null	,null	,null, null, null,null,null));
		districts.add(new District(new Long(16),	"Prakasam",	"Ongole",	new Double(17.6),	new Double(2759166), getState()	,null	,null	,null, null, null,null,null));
		districts.add(new District(new Long(17),	"Nizamabad",	"Nizamabad",	new Double(8), new Double(2037621), getState()	,null	,null	,null, null, null,null,null));
		districts.add(new District(new Long(18),	"Rangareddy",	"Hyderabad",	new Double(7.5), new Double(2551966), getState()	,null	,null,null, null, null,null,null));
		districts.add(new District(new Long(19),	"Srikakulam",	"Srikakakulam",	new Double(5.8),	new Double(2321126), getState()	,null	,null,null, null, null,null,null));
		districts.add(new District(new Long(20),	"Vishakapatnam", "Vishakapatnam",	new Double(11.2),	new Double(3285092), getState()	,null	,null,null, null, null,null,null));
		districts.add(new District(new Long(21),	"Vizingaram",	"Viziangaram",	new Double(6.5),	new Double(2110943),getState()	,null	,null,null, null, null,null,null));
		districts.add(new District(new Long(22),	"Warangal",	"Warangal",	new Double(12.9),	new Double(2818832), getState()	,null	,null	,null, null, null,null,null));
		districts.add(new District(new Long(23),	"West Godavari",	"Eluru",	new Double(7.7),	new Double(1), getState()	,null	,null	,null, null, null,null,null));
		return districts;
	}
	public static List<Party> getAllianceParties() {
		Party dummy4 = new Party(new Long(4), "Communist Party of India", "CPI",null, null, null,null, null,null);
		Party dummy5 = new Party(new Long(5), "Communist Party of India (Marxist)", "CPM",null, null, null,null, null,null);
		Party dummy10 = new Party(new Long(10), "Telangana Rashtra Samithi", "TRP",null, null, null,null, null,null);
		Party dummy11 = new Party(new Long(11), "Telugu Desam", "TDP",null, null, null,null, null,null);
		allianceParties.add(dummy4);
		allianceParties.add(dummy5);
		allianceParties.add(dummy10);
		allianceParties.add(dummy11);
		return allianceParties;
	}
	
	public static Set<Nomination> getNominationList(){
		Set<Nomination> nominations = new HashSet<Nomination>();
		Nomination n1 = new Nomination();
		Party party1 = new Party(1L); party1.setShortName("INC");
		n1.setParty(party1);
		CandidateResult cr1 = new CandidateResult(1L);
		cr1.setRank(1L);
		n1.setCandidateResult(cr1);
		Candidate cand1 = new Candidate(1L); cand1.setFirstname("firstname");cand1.setMiddlename("MiddleName");cand1.setMiddlename("LastName");
		n1.setCandidate(cand1);
		nominations.add(n1);
		
		Nomination n3 = new Nomination();
		Party party3 = new Party(3L); party3.setShortName("LSP");
		n3.setParty(party3);
		CandidateResult cr3 = new CandidateResult(3L);
		cr3.setRank(3L);
		n3.setCandidateResult(cr3);
		Candidate cand3 = new Candidate(1L); cand3.setFirstname("firstname");cand3.setMiddlename("MiddleName");cand3.setMiddlename("LastName");
		n3.setCandidate(cand3);
		nominations.add(n3);
		
		Nomination n2 = new Nomination();
		Party party2 = new Party(2L); party2.setShortName("TDP");
		n2.setParty(party2);
		CandidateResult cr2 = new CandidateResult(2L);
		cr2.setRank(2L);
		n2.setCandidateResult(cr2);
		Candidate cand2 = new Candidate(1L); cand2.setFirstname("firstname");cand2.setMiddlename("MiddleName");cand2.setMiddlename("LastName");
		n2.setCandidate(cand2);
		nominations.add(n2);
		
		Nomination n4 = new Nomination();
		Party party4 = new Party(4L); party4.setShortName("TRS");
		n4.setParty(party4);
		CandidateResult cr4 = new CandidateResult(4L);
		cr4.setRank(4L);
		n4.setCandidateResult(cr4);
		Candidate cand4 = new Candidate(1L); cand4.setFirstname("firstname");cand4.setMiddlename("MiddleName");cand4.setMiddlename("LastName");
		n4.setCandidate(cand4);
		nominations.add(n4);
		
		Nomination n5 = new Nomination();
		Party party5 = new Party(5L); party5.setShortName("MIM");
		n5.setParty(party5);
		CandidateResult cr5 = new CandidateResult(5L);
		cr5.setRank(5L);
		n5.setCandidateResult(cr5);
		Candidate cand5 = new Candidate(1L); cand5.setFirstname("firstname");cand5.setMiddlename("MiddleName");cand5.setMiddlename("LastName");
		n5.setCandidate(cand5);
		nominations.add(n5);
		
		Nomination n6 = new Nomination();
		Party party6 = new Party(6L); party1.setShortName("INC");
		n6.setParty(party6);
		CandidateResult cr6 = new CandidateResult(6L);
		cr6.setRank(6L);
		n6.setCandidateResult(cr6);
		Candidate cand6 = new Candidate(1L); cand6.setFirstname("firstname");cand6.setMiddlename("MiddleName");cand6.setMiddlename("LastName");
		n6.setCandidate(cand6);
		nominations.add(n6);
		
		return nominations;
	}
	
	public static ConstituencyElection createConstituencyElection(){
		ConstituencyElection constituencyElection = new ConstituencyElection();
		constituencyElection.setNominations(getNominationList());
		Constituency constituency = new Constituency(1L);
		constituency.setName("constituencyName");
		constituencyElection.setConstituency(constituency);
		// for constituencyElection --->nominations, constituency
		// for nomination ----> candidateresult, party, candidate
		return constituencyElection;
	}
	
	public static List<UserEvents> getTodaysUserEvents() {
		List<UserEvents> userEventsList = new ArrayList<UserEvents>();
		UserEvents userEvents1 = new UserEvents();
				
		userEvents1.setUserEventsId(5L);
		Registration user = new Registration();
		user.setRegistrationId(4L);
		user.setFirstName("sample");
		user.setLastName("sample");
		user.setParty(new Party(1L));
		userEvents1.setRegistration(user);
		userEvents1.setDescription("Village level meeting with party candidates");
		userEvents1.setLocationType("MANDAL");
		userEvents1.setLocationId(833L);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		calendar.set(2010, 05, 19, 13, 00, 00);
		userEvents1.setStartDate(calendar.getTime());
		calendar.set(2010, 05, 19, 16, 00, 00);
		userEvents1.setEndDate(calendar.getTime());
		Cadre cadre1 =  new Cadre();cadre1.setCadreId(1L);
		Cadre cadre2 =  new Cadre();cadre2.setCadreId(2L);
		List<Cadre> organizers = new ArrayList<Cadre>(); 
		organizers.add(cadre1); 
		organizers.add(cadre2);
		userEvents1.setOrganizers(organizers);
		List<UserEventActionPlan> plans2 = new ArrayList<UserEventActionPlan>();
		userEvents1.setUserEventActionPlans(plans2);
		userEventsList.add(userEvents1);
		
		return userEventsList;
	}
	
	public static List<UserEvents> getUserPlannedEvents(){
		List<UserEvents> userEventsList = new ArrayList<UserEvents>();
		UserEvents userEvents1 = new UserEvents();
		UserEvents userEvents2 = new UserEvents();
		
		userEvents1.setUserEventsId(1L);
		Registration user = new Registration();
		user.setRegistrationId(1L);
		user.setFirstName("fName");
		user.setLastName("lName");
		user.setParty(new Party(1L));
		userEvents1.setRegistration(user);
		userEvents1.setDescription("Event 1 Description.....");
		userEvents1.setLocationType("DISTRICT");
		userEvents1.setLocationId(1L);
		userEvents1.setStartDate(Calendar.getInstance().getTime());
		userEvents1.setEndDate(Calendar.getInstance().getTime());
		Cadre cadre1 =  new Cadre();cadre1.setCadreId(1L);
		Cadre cadre2 =  new Cadre();cadre2.setCadreId(2L);
		List<Cadre> organizers = new ArrayList<Cadre>(); organizers.add(cadre1); organizers.add(cadre2);
		userEvents1.setOrganizers(organizers);

		List<UserEventActionPlan> plans1 = new ArrayList<UserEventActionPlan>();
		UserEventActionPlan plan1= new UserEventActionPlan();plan1.setUserEvents(userEvents1);
		UserEventActionPlan plan2= new UserEventActionPlan();plan2.setUserEvents(userEvents1);
		plans1.add(plan1);plans1.add(plan2);
		userEvents1.setUserEventActionPlans(plans1);
		userEventsList.add(userEvents1);
		
		userEvents2.setUserEventsId(2L);
		userEvents2.setRegistration(user);
		userEvents2.setDescription("Event 2 Description.....");
		userEvents2.setLocationType("MANDAL");
		userEvents2.setLocationId(11L);
		userEvents2.setStartDate(Calendar.getInstance().getTime());
		userEvents2.setEndDate(Calendar.getInstance().getTime());
		List<Cadre> organizers2 = new ArrayList<Cadre>(); organizers2.add(cadre1); //organizers2.add(cadre2);
		userEvents2.setOrganizers(organizers2);
		List<UserEventActionPlan> plans2 = new ArrayList<UserEventActionPlan>();
		userEvents2.setUserEventActionPlans(plans2);
		userEventsList.add(userEvents2);
		return userEventsList;
	}
	
	public static UserEventVO getUserEventVO(){		
		UserEventVO userPlannedEvents = new UserEventVO();
		userPlannedEvents.setUserID(1L);
		userPlannedEvents.setDescription("Helping poor of Hyderabad city.....");
		userPlannedEvents.setLocationType("YAKUTPURA");
		userPlannedEvents.setLocationId(1L);
		userPlannedEvents.setStartDate(Calendar.getInstance().getTime());
		userPlannedEvents.setEndDate(Calendar.getInstance().getTime());
		List <SelectOptionVO> organizers = new ArrayList<SelectOptionVO>();
		SelectOptionVO obj = new SelectOptionVO(1L, "");
		organizers.add(obj);
		List<EventActionPlanVO> actionPlans = new ArrayList<EventActionPlanVO>();
		EventActionPlanVO actionPlan1 = new EventActionPlanVO();
		actionPlan1.setAction("ground work at nallakunta");
		actionPlan1.setUserEventsId(12L);
		actionPlan1.setTargetDate(Calendar.getInstance().getTime());
		List<SelectOptionVO> list = new ArrayList<SelectOptionVO>();
		SelectOptionVO list1 = new SelectOptionVO(4L,"");
		list.add(list1);
		actionPlan1.setActionPlanOrganizers(list);
		
		actionPlans.add(actionPlan1);
		userPlannedEvents.setOrganizers(organizers);
		userPlannedEvents.setActionPlans(actionPlans);
		return userPlannedEvents;
	}
	
	public static List<ProblemBeanVO> getProblemBeanVOsToSave(){
		List<ProblemBeanVO> problems = new ArrayList<ProblemBeanVO>();
		ProblemBeanVO p1, p2, p3, p4;
		p1 = new ProblemBeanVO();
		p1.setProblemLocationId(1l);
		p1.setProblemSourceScopeId(3l);
		p1.setProblemClassificationId(1l);
		p1.setProblemStatusId(2l);
		
		p1.setProblem("1aaaxxx");
		p1.setDescription("1xxxaaaa");
		
		p2 = new ProblemBeanVO();
		p2.setProblemLocationId(2l);
		p2.setProblemSourceScopeId(5l);
		p2.setProblemClassificationId(2l);
		p2.setProblemStatusId(2l);
		
		p2.setProblem("2aaaxxx");
		p2.setDescription("2xxxaaaa");
		
		p3 = new ProblemBeanVO();
		p3.setProblemLocationId(3l);
		p3.setProblemSourceScopeId(5l);
		p3.setProblemClassificationId(1l);
		p3.setProblemStatusId(2l);
		
		p3.setProblem("3aaaxxx");
		p3.setDescription("3xxxaaaa");
		
		p4 = new ProblemBeanVO();
		p4.setProblemLocationId(4l);
		p4.setProblemSourceScopeId(5l);
		p4.setProblemClassificationId(2l);
		p4.setProblemStatusId(2l);
		
		p4.setProblem("4aaaxxx");
		p4.setDescription("4xxxaaaa");
		 
		problems.add(p1);
		problems.add(p2);
		problems.add(p3);
		problems.add(p4);
		
		return problems;
	}
	
	public static List<ProblemLocation> getProblemsForUser(){
		List<ProblemLocation> list = new ArrayList<ProblemLocation>();
		
		Problem problem1 = new Problem();
		problem1.setProblem("No Trasportation FAcilities");
		problem1.setDescription("No Bus service provided to Ramanthapur Hamlet");
		problem1.setIdentifiedOn(Calendar.getInstance().getTime());
		problem1.setExistingFrom(Calendar.getInstance().getTime());
		
		ProblemAndProblemSource problemAndProblemSource1 = new ProblemAndProblemSource();
		problemAndProblemSource1.setProblemSource(new InformationSource(1L));
		
		
		Hamlet hamlet1 = new Hamlet();
		hamlet1.setHamletName("IsakaPalli");
		
		ProblemLocation problemLocation1 = new ProblemLocation(); 
		problemLocation1.setHamlet(hamlet1);
		
		problemAndProblemSource1.setProblem(problem1);
		problemLocation1.setProblemAndProblemSource(problemAndProblemSource1);
		list.add(problemLocation1);
			
		Problem problem2 = new Problem();
		problem2.setProblem("No Street Lights");
		problem2.setDescription("No Street Lights in Harijamwada Colony");
		problem2.setIdentifiedOn(Calendar.getInstance().getTime());
		problem2.setExistingFrom(Calendar.getInstance().getTime());
		InformationSource problemSource = new InformationSource(new Long(1));
		ProblemAndProblemSource problemAndProblemSource2 = new ProblemAndProblemSource();
		problemAndProblemSource2.setProblemSource(problemSource);
		
		
		Hamlet hamlet2 = new Hamlet();
		hamlet2.setHamletName("singaPeta");
		
		ProblemLocation problemLocation2 = new ProblemLocation(); 
		problemLocation2.setHamlet(hamlet2);
		
		problemAndProblemSource2.setProblem(problem2);
		problemLocation2.setProblemAndProblemSource(problemAndProblemSource2);
		list.add(problemLocation2);
		return list;		
	}
	
	/*public static ConstituencyElectionResultsVO getAllCandidatesDetailsForConstituency(Long constituencyId,String electionYear,String electionType){
				 
		 List<CandidateOppositionVO> candidateOppositionVO = new ArrayList<CandidateOppositionVO>(0);	 
			 CandidateOppositionVO  candidateOppositionVo = new CandidateOppositionVO();
				 candidateOppositionVo.setCandidateId(13268L);
				 candidateOppositionVo.setCandidateName("GOTTIPATI LAKSHMI NARAYANA");
				 candidateOppositionVo.setVotesEarned("565.0");
				 candidateOppositionVo.setVotesPercentage("0.39");
				 candidateOppositionVo.setRank(4L); 
				 candidateOppositionVo.setPartyFlag("Independent"); 
				 candidateOppositionVo.setPartyId(24L); 
				 candidateOppositionVo.setPartyLongName("Independent");
				 candidateOppositionVo.setPartyName("IND");
			  candidateOppositionVO.add(candidateOppositionVo);
			 
			  CandidateOppositionVO  candidateOppositionvo = new CandidateOppositionVO();
				 candidateOppositionvo.setCandidateId(11614L);
				 candidateOppositionvo.setCandidateName("Kodela Siva Prasada Rao");
				 candidateOppositionvo.setVotesEarned("64073.0");
				 candidateOppositionvo.setVotesPercentage("44.01");
				 candidateOppositionvo.setRank(3L); 
				 candidateOppositionvo.setPartyFlag("TDP.PNG"); 
				 candidateOppositionvo.setPartyId(62L); 
				 candidateOppositionvo.setPartyLongName("Telugu Desam");
				 candidateOppositionvo.setPartyName("TDP");
			  candidateOppositionVO.add(candidateOppositionVo);
		 
			 CandidateWonVO candidateWonVO = new CandidateWonVO(); 
				 candidateWonVO.setCandidateId(13266L);
				 candidateWonVO.setCandidateName("KASU  VENKATA KRISHNA REDDY");
				 candidateWonVO.setVotesEarned("79568.0");
				 candidateWonVO.setVotesPercentage("54.66");
				 candidateWonVO.setRank(1L); 
				 candidateWonVO.setPartyFlag("INC.png"); 
				 candidateWonVO.setPartyId(24L); 
				 candidateWonVO.setPartyLongName("Indian National Congress");
				 candidateWonVO.setPartyName("INC");
				
				 constituencyElectionResults.setCandidateOppositionList(candidateOppositionVO);
				 constituencyElectionResults.setCandidateResultsVO(candidateWonVO);
		return null;		
	}*/
	
	public static List getConstituenciesByConstituencyId(Long constituencyId,String electionYear,String electionType){
			 List result = new ArrayList(0);
			 result.add(3382l);
			 result.add("Kavali");
			 result.add(electionType);
			 result.add(19L);
			 result.add("Nellore");
			 result.add(1L);
			 result.add("Andhra Pradesh");
			 result.add(electionYear);
		 return null;
	}
	public static RegistrationVO getRegistrationVO() {
		RegistrationVO user = new RegistrationVO();
		user.setRegistrationID(1L);
		user.setFirstName("Ashok");
		user.setLastName("Dakavaram");
		user.setParty(62L);
		user.setSubscribePartyImpDate("ALL");
		return user;
		
	}
	public static List<UserImpDate> getTodaysUserImpDates() {
		UserImpDate importantDatesVO = new UserImpDate();
		
		importantDatesVO.setUser(new Registration(1L));
		importantDatesVO.setTitle("My Birthday as party candidate");
		importantDatesVO.setDescription("date that I joined into party");
		importantDatesVO.setEffectiveDate(new Date());
		importantDatesVO.setTillDate(new Date());
		importantDatesVO.setIsDeleted("NO");
		importantDatesVO.setUserImpDateID(1L);
		importantDatesVO.setRecFreqType("YEARLY");
		
		return Arrays.asList(importantDatesVO);
	}
	public static List<PartyImportantDates> getPartyImpdates() {
		PartyImportantDates impDates = new PartyImportantDates();
		impDates.setParty(new Party(62L));
		impDates.setPartyImportantDateId(18L);
		impDates.setImportantDate(new Date("6/19/1980"));
		impDates.setRecursive("T");
		impDates.setRecursiveFrequency("YEARLY");
		impDates.setTitle("PARTY PRESIDENT'S BIRTHDAY");
		
		return Arrays.asList(impDates);
	}
}
