package com.itgrids.partyanalyst.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.itgrids.partyanalyst.model.CandidateResult;
import com.itgrids.partyanalyst.model.ConstituencyElection;
import com.itgrids.partyanalyst.model.ConstituencyElectionResult;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.Party;

public class DummyPartyResultsData {
	private static final List<Party> parties = new ArrayList<Party> ();	
	static{
		Party dummy1 = new Party(new Long(1), "Indian National Congress", "INC",null, null, null,null, null,null,null,null,null,null);
		Party dummy2 = new Party(new Long(2), "Bharatiya Janata Party", "BJP",null, null, null,null, null,null,null,null,null,null);
		Party dummy3 = new Party(new Long(3), "Bahujan Samaj Party", "BSP",null, null, null,null, null,null,null,null,null,null);

		Party dummy4 = new Party(new Long(4), "Communist Party of India (Marxist)", "CPM",null, null, null,null, null,null,null,null,null,null);
		Party dummy5 = new Party(new Long(5), "Samajwadi Party", "SP",null, null, null,null, null,null,null,null,null,null);
		Party dummy6 = new Party(new Long(6), "Praja Rajyam Party", "PRP",null, null, null,null, null,null,null,null,null,null);
		
		Party dummy7 = new Party(new Long(7), "Telangana Rashtra Samithi", "TRP",null, null, null,null, null,null,null,null,null,null);
		Party dummy8 = new Party(new Long(8), "Telugu Desam", "TDP",null, null, null,null, null,null,null,null,null,null);
		Party dummy9 = new Party(new Long(9), "Lok Satta Party", "LSP",null, null, null,null, null,null,null,null,null,null);
		Party dummy10 = new Party(new Long(10), "Independent", "IND",null, null, null,null, null,null,null,null,null,null);
		

		Party dummy11 = new Party(new Long(11), "Pyramid Party of India", "PPOI",null, null, null,null,null,null,null,null,null,null);
		Party dummy12 = new Party(new Long(12), "Trilinga Praja Pragati Party", null,null, null, null,null, null,null,null,null,null,null);
		Party dummy13 = new Party(new Long(13), "Republican Party of India (A)", null,null, null, null,null, null,null,null,null,null,null);
		Party dummy14 = new Party(new Long(14), "B.C.United Front", null,null, null, null,null, null,null,null,null,null,null);
		Party dummy15 = new Party(new Long(15), "Rajyadhikara Party", null,null, null, null,null, null,null,null,null,null,null);
		Party dummy16 = new Party(new Long(16), "Bharatiya Jan Shakti", null,null, null, null,null, null,null,null,null,null,null);
		Party dummy17 = new Party(new Long(17), "Bharatiya Rashtravadi Paksha", "BRP",null, null, null,null, null,null,null,null,null,null);
		Party dummy18 = new Party(new Long(18), "NTR Telugu Desam Party (Lakshmi Parvathi)", "NTRTDP(LP)",null, null,null, null,null,null,null,null,null,null);
		parties.add(dummy1);	parties.add(dummy2);	parties.add(dummy3);		parties.add(dummy5);
		parties.add(dummy6);    parties.add(dummy7);	parties.add(dummy8);		parties.add(dummy9);	
		parties.add(dummy10);	parties.add(dummy11);	parties.add(dummy12);		parties.add(dummy13);	
		parties.add(dummy14);	parties.add(dummy15);	parties.add(dummy16);		parties.add(dummy17);	
		parties.add(dummy18);	parties.add(dummy4);
	}
	public static List<ConstituencyElectionResult> getConstituencyElectionResultList(){
		List<ConstituencyElectionResult> list = new ArrayList<ConstituencyElectionResult>();

		ConstituencyElectionResult obj1 = new ConstituencyElectionResult(new Long(1));
		obj1.setConstituencyElection(new ConstituencyElection(new Long(1)));
		obj1.setValidVotes(new Double(8000));

		ConstituencyElectionResult obj2 = new ConstituencyElectionResult(new Long(2));
		obj2.setConstituencyElection(new ConstituencyElection(new Long(2)));
		obj2.setValidVotes(new Double(7000));
		
		
		ConstituencyElectionResult obj3 = new ConstituencyElectionResult(new Long(3));
		obj3.setConstituencyElection(new ConstituencyElection(new Long(3)));
		obj3.setValidVotes(new Double(4500));

		ConstituencyElectionResult obj4 = new ConstituencyElectionResult(new Long(4));
		obj4.setConstituencyElection(new ConstituencyElection(new Long(4)));
		obj4.setValidVotes(new Double(3500));
		
		
		ConstituencyElectionResult obj5 = new ConstituencyElectionResult(new Long(5));
		obj5.setConstituencyElection(new ConstituencyElection(new Long(5)));
		obj5.setValidVotes(new Double(4000)); 
		

		list.add(obj1);
		list.add(obj2);
		list.add(obj3);
		list.add(obj4);
		list.add(obj5);
		
		
		return list;
	}

	public static Party getParty(Long id){
		for(Party party: parties){
			if(party.getPartyId().equals(id))
				return party;
		}
		return null;
	}
	public static List<Election> getElections(){
		List<Election> list = new ArrayList<Election>();
		Election election = new Election();
		election.setElectionId(new Long(1));
		election.setElectionYear("2009");
		

		Election election1 = new Election();
		election1.setElectionId(new Long(2));
		election1.setElectionYear("2004");
		
		Election election2 = new Election();
		election2.setElectionId(new Long(3));
		election2.setElectionYear("1999");
		

		list.add(election);
		list.add(election1);
		list.add(election2);
		
		return list;
	}
	
	public static List<ConstituencyElection> getConstituencyElectionList(Election election){
		List<ConstituencyElection> constituencyElectionList = new ArrayList<ConstituencyElection>();
		if(election.getElectionId().equals(new Long(1))){
			//-------------------------------
			ConstituencyElection ce1 = new ConstituencyElection(new Long(1));
			{
				Set<Nomination> nominations = new HashSet<Nomination>();
				
				Nomination n2 = new Nomination(new Long(2));
				n2.setParty(getParty(new Long(8)));
				CandidateResult cr2 = new CandidateResult(new Long(2));
				cr2.setVotesEarned(new Double(750));
				cr2.setRank(new Long(2));
				n2.setCandidateResult(cr2);				
				nominations.add(n2);
				

				Nomination n3 = new Nomination(new Long(3));
				n3.setParty(getParty(new Long(6)));
				CandidateResult cr3 = new CandidateResult(new Long(3));
				cr3.setVotesEarned(new Double(600));
				cr3.setRank(new Long(3));
				n3.setCandidateResult(cr3);				
				nominations.add(n3);
				
				Nomination n1 = new Nomination(new Long(1));
				n1.setParty(getParty(new Long(1)));
				CandidateResult cr1 = new CandidateResult(new Long(1));
				cr1.setVotesEarned(new Double(1000));
				cr1.setRank(new Long(1));
				n1.setCandidateResult(cr1);				
				nominations.add(n1);

				Nomination n4 = new Nomination(new Long(4));
				n4.setParty(getParty(new Long(7)));
				CandidateResult cr4 = new CandidateResult(new Long(4));
				cr4.setVotesEarned(new Double(500));
				cr4.setRank(new Long(4));
				n4.setCandidateResult(cr4);				
				nominations.add(n4);
				

				Nomination n5 = new Nomination(new Long(5));
				n5.setParty(getParty(new Long(9)));
				CandidateResult cr5 = new CandidateResult(new Long(5));
				cr5.setVotesEarned(new Double(400));
				cr5.setRank(new Long(5));
				n5.setCandidateResult(cr5);				
				nominations.add(n5);
				
				ce1.setNominations(nominations);
			}
				
			//-------------------------------
			ConstituencyElection ce2 = new ConstituencyElection(new Long(2));
			{
				Set<Nomination> nominations = new HashSet<Nomination>();				

				Nomination n2 = new Nomination(new Long(7));
				n2.setParty(getParty(new Long(8)));
				CandidateResult cr2 = new CandidateResult(new Long(7));
				cr2.setVotesEarned(new Double(1000));
				cr2.setRank(new Long(1));
				n2.setCandidateResult(cr2);				
				nominations.add(n2);
				

				Nomination n3 = new Nomination(new Long(8));
				n3.setParty(getParty(new Long(6)));
				CandidateResult cr3 = new CandidateResult(new Long(8));
				cr3.setVotesEarned(new Double(600));
				cr3.setRank(new Long(3));
				n3.setCandidateResult(cr3);				
				nominations.add(n3);
				

				Nomination n4 = new Nomination(new Long(9));
				n4.setParty(getParty(new Long(7)));
				CandidateResult cr4 = new CandidateResult(new Long(9));
				cr4.setVotesEarned(new Double(500));
				cr4.setRank(new Long(4));
				n4.setCandidateResult(cr4);				
				nominations.add(n4);
				
				Nomination n1 = new Nomination(new Long(6));
				n1.setParty(getParty(new Long(1)));
				CandidateResult cr1 = new CandidateResult(new Long(6));
				cr1.setVotesEarned(new Double(750));
				cr1.setRank(new Long(2));
				n1.setCandidateResult(cr1);				
				nominations.add(n1);
				
				Nomination n5 = new Nomination(new Long(10));
				n5.setParty(getParty(new Long(9)));
				CandidateResult cr5 = new CandidateResult(new Long(10));
				cr5.setVotesEarned(new Double(400));
				cr5.setRank(new Long(5));
				n5.setCandidateResult(cr5);				
				nominations.add(n5);
				
				ce2.setNominations(nominations);
			}
			//-------------------------------
			ConstituencyElection ce3 = new ConstituencyElection(new Long(3));
			{
				Set<Nomination> nominations = new HashSet<Nomination>();	

				Nomination n2 = new Nomination(new Long(12));
				n2.setParty(getParty(new Long(8)));
				CandidateResult cr2 = new CandidateResult(new Long(12));
				cr2.setVotesEarned(new Double(750));
				cr2.setRank(new Long(2));
				n2.setCandidateResult(cr2);				
				nominations.add(n2);
				

				Nomination n3 = new Nomination(new Long(13));
				n3.setParty(getParty(new Long(6)));
				CandidateResult cr3 = new CandidateResult(new Long(13));
				cr3.setVotesEarned(new Double(600));
				cr3.setRank(new Long(4));
				n3.setCandidateResult(cr3);				
				nominations.add(n3);
				

				Nomination n4 = new Nomination(new Long(14));
				n4.setParty(getParty(new Long(7)));
				CandidateResult cr4 = new CandidateResult(new Long(14));
				cr4.setVotesEarned(new Double(500));
				cr4.setRank(new Long(5));
				n4.setCandidateResult(cr4);				
				nominations.add(n4);
				

				Nomination n5 = new Nomination(new Long(15));
				n5.setParty(getParty(new Long(9)));
				CandidateResult cr5 = new CandidateResult(new Long(15));
				cr5.setVotesEarned(new Double(700));
				cr5.setRank(new Long(3));
				n5.setCandidateResult(cr5);				
				nominations.add(n5);
				
				Nomination n1 = new Nomination(new Long(11));
				n1.setParty(getParty(new Long(1)));
				CandidateResult cr1 = new CandidateResult(new Long(11));
				cr1.setVotesEarned(new Double(1200));
				cr1.setRank(new Long(1));
				n1.setCandidateResult(cr1);				
				nominations.add(n1);
				ce3.setNominations(nominations);
			}
			//-------------------------------
			ConstituencyElection ce4 = new ConstituencyElection(new Long(4));
			{
				Set<Nomination> nominations = new HashSet<Nomination>();

				Nomination n2 = new Nomination(new Long(17));
				n2.setParty(getParty(new Long(8)));
				CandidateResult cr2 = new CandidateResult(new Long(2));
				cr2.setVotesEarned(new Double(1200));
				cr2.setRank(new Long(2));
				n2.setCandidateResult(cr2);				
				nominations.add(n2);
				

				Nomination n3 = new Nomination(new Long(18));
				n3.setParty(getParty(new Long(6)));
				CandidateResult cr3 = new CandidateResult(new Long(3));
				cr3.setVotesEarned(new Double(600));
				cr3.setRank(new Long(3));
				n3.setCandidateResult(cr3);				
				nominations.add(n3);

				Nomination n1 = new Nomination(new Long(16));
				n1.setParty(getParty(new Long(1)));
				CandidateResult cr1 = new CandidateResult(new Long(1));
				cr1.setVotesEarned(new Double(1500));
				cr1.setRank(new Long(1));
				n1.setCandidateResult(cr1);				
				nominations.add(n1);				

				Nomination n4 = new Nomination(new Long(19));
				n4.setParty(getParty(new Long(7)));
				CandidateResult cr4 = new CandidateResult(new Long(19));
				cr4.setVotesEarned(new Double(500));
				cr4.setRank(new Long(4));
				n4.setCandidateResult(cr4);				
				nominations.add(n4);
				

				Nomination n5 = new Nomination(new Long(20));
				n5.setParty(getParty(new Long(9)));
				CandidateResult cr5 = new CandidateResult(new Long(20));
				cr5.setVotesEarned(new Double(400));
				cr5.setRank(new Long(5));
				n5.setCandidateResult(cr5);				
				nominations.add(n5);
				
				ce4.setNominations(nominations);
			}
			//-------------------------------
			ConstituencyElection ce5 = new ConstituencyElection(new Long(5));
			{
				Set<Nomination> nominations = new HashSet<Nomination>();

				Nomination n5 = new Nomination(new Long(25));
				n5.setParty(getParty(new Long(9)));
				CandidateResult cr5 = new CandidateResult(new Long(25));
				cr5.setVotesEarned(new Double(400));
				cr5.setRank(new Long(5));
				n5.setCandidateResult(cr5);				
				nominations.add(n5);
				
				Nomination n1 = new Nomination(new Long(21));
				n1.setParty(getParty(new Long(1)));
				CandidateResult cr1 = new CandidateResult(new Long(21));
				cr1.setVotesEarned(new Double(1000));
				cr1.setRank(new Long(1));
				n1.setCandidateResult(cr1);				
				nominations.add(n1);
				

				Nomination n2 = new Nomination(new Long(22));
				n2.setParty(getParty(new Long(8)));
				CandidateResult cr2 = new CandidateResult(new Long(22));
				cr2.setVotesEarned(new Double(750));
				cr2.setRank(new Long(2));
				n2.setCandidateResult(cr2);				
				nominations.add(n2);
				

				Nomination n3 = new Nomination(new Long(23));
				n3.setParty(getParty(new Long(6)));
				CandidateResult cr3 = new CandidateResult(new Long(23));
				cr3.setVotesEarned(new Double(600));
				cr3.setRank(new Long(3));
				n3.setCandidateResult(cr3);				
				nominations.add(n3);
				

				Nomination n4 = new Nomination(new Long(24));
				n4.setParty(getParty(new Long(7)));
				CandidateResult cr4 = new CandidateResult(new Long(24));
				cr4.setVotesEarned(new Double(500));
				cr4.setRank(new Long(4));
				n4.setCandidateResult(cr4);				
				nominations.add(n4);
				
				ce5.setNominations(nominations);
			}
			constituencyElectionList.add(ce1);
			constituencyElectionList.add(ce2);
			constituencyElectionList.add(ce3);
			constituencyElectionList.add(ce4);
			constituencyElectionList.add(ce5);
		}
		else if(election.getElectionId().equals(new Long(2))){
			//-------------------------------
			ConstituencyElection ce1 = new ConstituencyElection(new Long(6));
			{
				Set<Nomination> nominations = new HashSet<Nomination>();

				Nomination n4 = new Nomination(new Long(104));
				n4.setParty(getParty(new Long(7)));
				CandidateResult cr4 = new CandidateResult(new Long(104));
				cr4.setVotesEarned(new Double(500));
				cr4.setRank(new Long(4));
				n4.setCandidateResult(cr4);				
				nominations.add(n4);
				

				Nomination n5 = new Nomination(new Long(105));
				n5.setParty(getParty(new Long(9)));
				CandidateResult cr5 = new CandidateResult(new Long(105));
				cr5.setVotesEarned(new Double(400));
				cr5.setRank(new Long(5));
				n5.setCandidateResult(cr5);				
				nominations.add(n5);
				
				Nomination n1 = new Nomination(new Long(101));
				n1.setParty(getParty(new Long(1)));
				CandidateResult cr1 = new CandidateResult(new Long(101));
				cr1.setVotesEarned(new Double(2000));
				cr1.setRank(new Long(1));
				n1.setCandidateResult(cr1);				
				nominations.add(n1);
				

				Nomination n2 = new Nomination(new Long(102));
				n2.setParty(getParty(new Long(8)));
				CandidateResult cr2 = new CandidateResult(new Long(102));
				cr2.setVotesEarned(new Double(750));
				cr2.setRank(new Long(2));
				n2.setCandidateResult(cr2);				
				nominations.add(n2);
				

				Nomination n3 = new Nomination(new Long(103));
				n3.setParty(getParty(new Long(6)));
				CandidateResult cr3 = new CandidateResult(new Long(103));
				cr3.setVotesEarned(new Double(600));
				cr3.setRank(new Long(3));
				n3.setCandidateResult(cr3);				
				nominations.add(n3);
				
				ce1.setNominations(nominations);
			}
				
			//-------------------------------
			ConstituencyElection ce2 = new ConstituencyElection(new Long(7));
			{
				Set<Nomination> nominations = new HashSet<Nomination>();

				Nomination n2 = new Nomination(new Long(107));
				n2.setParty(getParty(new Long(8)));
				CandidateResult cr2 = new CandidateResult(new Long(107));
				cr2.setVotesEarned(new Double(1000));
				cr2.setRank(new Long(1));
				n2.setCandidateResult(cr2);				
				nominations.add(n2);
				

				Nomination n3 = new Nomination(new Long(108));
				n3.setParty(getParty(new Long(6)));
				CandidateResult cr3 = new CandidateResult(new Long(108));
				cr3.setVotesEarned(new Double(600));
				cr3.setRank(new Long(3));
				n3.setCandidateResult(cr3);				
				nominations.add(n3);
				

				Nomination n1 = new Nomination(new Long(106));
				n1.setParty(getParty(new Long(1)));
				CandidateResult cr1 = new CandidateResult(new Long(106));
				cr1.setVotesEarned(new Double(750));
				cr1.setRank(new Long(2));
				n1.setCandidateResult(cr1);				
				nominations.add(n1);
				
				Nomination n4 = new Nomination(new Long(109));
				n4.setParty(getParty(new Long(7)));
				CandidateResult cr4 = new CandidateResult(new Long(109));
				cr4.setVotesEarned(new Double(500));
				cr4.setRank(new Long(4));
				n4.setCandidateResult(cr4);				
				nominations.add(n4);
				

				Nomination n5 = new Nomination(new Long(110));
				n5.setParty(getParty(new Long(9)));
				CandidateResult cr5 = new CandidateResult(new Long(110));
				cr5.setVotesEarned(new Double(400));
				cr5.setRank(new Long(5));
				n5.setCandidateResult(cr5);				
				nominations.add(n5);
				
				ce2.setNominations(nominations);
			}
			//-------------------------------
			ConstituencyElection ce3 = new ConstituencyElection(new Long(8));
			{
				Set<Nomination> nominations = new HashSet<Nomination>();
				Nomination n1 = new Nomination(new Long(111));
				n1.setParty(getParty(new Long(1)));
				CandidateResult cr1 = new CandidateResult(new Long(111));
				cr1.setVotesEarned(new Double(1200));
				cr1.setRank(new Long(1));
				n1.setCandidateResult(cr1);				
				nominations.add(n1);
				

				Nomination n2 = new Nomination(new Long(112));
				n2.setParty(getParty(new Long(8)));
				CandidateResult cr2 = new CandidateResult(new Long(112));
				cr2.setVotesEarned(new Double(750));
				cr2.setRank(new Long(2));
				n2.setCandidateResult(cr2);				
				nominations.add(n2);
				

				Nomination n3 = new Nomination(new Long(113));
				n3.setParty(getParty(new Long(6)));
				CandidateResult cr3 = new CandidateResult(new Long(113));
				cr3.setVotesEarned(new Double(600));
				cr3.setRank(new Long(4));
				n3.setCandidateResult(cr3);				
				nominations.add(n3);
				

				Nomination n4 = new Nomination(new Long(114));
				n4.setParty(getParty(new Long(7)));
				CandidateResult cr4 = new CandidateResult(new Long(114));
				cr4.setVotesEarned(new Double(500));
				cr4.setRank(new Long(5));
				n4.setCandidateResult(cr4);				
				nominations.add(n4);
				

				Nomination n5 = new Nomination(new Long(115));
				n5.setParty(getParty(new Long(9)));
				CandidateResult cr5 = new CandidateResult(new Long(115));
				cr5.setVotesEarned(new Double(700));
				cr5.setRank(new Long(3));
				n5.setCandidateResult(cr5);				
				nominations.add(n5);
				
				ce3.setNominations(nominations);
			}
			//-------------------------------
			ConstituencyElection ce4 = new ConstituencyElection(new Long(9));
			{
				Set<Nomination> nominations = new HashSet<Nomination>();
				Nomination n1 = new Nomination(new Long(116));
				n1.setParty(getParty(new Long(1)));
				CandidateResult cr1 = new CandidateResult(new Long(116));
				cr1.setVotesEarned(new Double(1500));
				cr1.setRank(new Long(1));
				n1.setCandidateResult(cr1);				
				nominations.add(n1);
				

				Nomination n2 = new Nomination(new Long(117));
				n2.setParty(getParty(new Long(8)));
				CandidateResult cr2 = new CandidateResult(new Long(117));
				cr2.setVotesEarned(new Double(1200));
				cr2.setRank(new Long(2));
				n2.setCandidateResult(cr2);				
				nominations.add(n2);
				
				Nomination n5 = new Nomination(new Long(120));
				n5.setParty(getParty(new Long(9)));
				CandidateResult cr5 = new CandidateResult(new Long(120));
				cr5.setVotesEarned(new Double(400));
				cr5.setRank(new Long(5));
				n5.setCandidateResult(cr5);				
				nominations.add(n5);
				
				Nomination n3 = new Nomination(new Long(118));
				n3.setParty(getParty(new Long(6)));
				CandidateResult cr3 = new CandidateResult(new Long(118));
				cr3.setVotesEarned(new Double(600));
				cr3.setRank(new Long(3));
				n3.setCandidateResult(cr3);				
				nominations.add(n3);
				

				Nomination n4 = new Nomination(new Long(119));
				n4.setParty(getParty(new Long(7)));
				CandidateResult cr4 = new CandidateResult(new Long(119));
				cr4.setVotesEarned(new Double(500));
				cr4.setRank(new Long(4));
				n4.setCandidateResult(cr4);				
				nominations.add(n4);
				
				
				ce4.setNominations(nominations);
			}
			//-------------------------------
			ConstituencyElection ce5 = new ConstituencyElection(new Long(10));
			{
				Set<Nomination> nominations = new HashSet<Nomination>();
				Nomination n1 = new Nomination(new Long(121));
				n1.setParty(getParty(new Long(1)));
				CandidateResult cr1 = new CandidateResult(new Long(121));
				cr1.setVotesEarned(new Double(1000));
				cr1.setRank(new Long(1));
				n1.setCandidateResult(cr1);				
				nominations.add(n1);
				

				Nomination n2 = new Nomination(new Long(122));
				n2.setParty(getParty(new Long(8)));
				CandidateResult cr2 = new CandidateResult(new Long(122));
				cr2.setVotesEarned(new Double(750));
				cr2.setRank(new Long(2));
				n2.setCandidateResult(cr2);				
				nominations.add(n2);
				

				Nomination n3 = new Nomination(new Long(123));
				n3.setParty(getParty(new Long(6)));
				CandidateResult cr3 = new CandidateResult(new Long(123));
				cr3.setVotesEarned(new Double(600));
				cr3.setRank(new Long(3));
				n3.setCandidateResult(cr3);				
				nominations.add(n3);
				

				Nomination n4 = new Nomination(new Long(124));
				n4.setParty(getParty(new Long(7)));
				CandidateResult cr4 = new CandidateResult(new Long(124));
				cr4.setVotesEarned(new Double(500));
				cr4.setRank(new Long(4));
				n4.setCandidateResult(cr4);				
				nominations.add(n4);
				

				Nomination n5 = new Nomination(new Long(125));
				n5.setParty(getParty(new Long(9)));
				CandidateResult cr5 = new CandidateResult(new Long(125));
				cr5.setVotesEarned(new Double(400));
				cr5.setRank(new Long(5));
				n5.setCandidateResult(cr5);				
				nominations.add(n5);
				
				ce5.setNominations(nominations);
			}
			constituencyElectionList.add(ce1);
			constituencyElectionList.add(ce2);
			constituencyElectionList.add(ce3);
			constituencyElectionList.add(ce4);
			constituencyElectionList.add(ce5);
		}
		else if(election.getElectionId().equals(new Long(3))){
			//-------------------------------
			ConstituencyElection ce1 = new ConstituencyElection(new Long(11));
			{
				Set<Nomination> nominations = new HashSet<Nomination>();
				Nomination n1 = new Nomination(new Long(201));
				n1.setParty(getParty(new Long(8)));
				CandidateResult cr1 = new CandidateResult(new Long(201));
				cr1.setVotesEarned(new Double(1000));
				cr1.setRank(new Long(1));
				n1.setCandidateResult(cr1);				
				nominations.add(n1);
				

				Nomination n2 = new Nomination(new Long(202));
				n2.setParty(getParty(new Long(1)));
				CandidateResult cr2 = new CandidateResult(new Long(202));
				cr2.setVotesEarned(new Double(750));
				cr2.setRank(new Long(2));
				n2.setCandidateResult(cr2);				
				nominations.add(n2);
				

				Nomination n3 = new Nomination(new Long(203));
				n3.setParty(getParty(new Long(6)));
				CandidateResult cr3 = new CandidateResult(new Long(203));
				cr3.setVotesEarned(new Double(600));
				cr3.setRank(new Long(3));
				n3.setCandidateResult(cr3);				
				nominations.add(n3);
				

				Nomination n4 = new Nomination(new Long(204));
				n4.setParty(getParty(new Long(7)));
				CandidateResult cr4 = new CandidateResult(new Long(204));
				cr4.setVotesEarned(new Double(500));
				cr4.setRank(new Long(4));
				n4.setCandidateResult(cr4);				
				nominations.add(n4);
				

				Nomination n5 = new Nomination(new Long(205));
				n5.setParty(getParty(new Long(9)));
				CandidateResult cr5 = new CandidateResult(new Long(205));
				cr5.setVotesEarned(new Double(400));
				cr5.setRank(new Long(5));
				n5.setCandidateResult(cr5);				
				nominations.add(n5);
				
				ce1.setNominations(nominations);
			}
				
			//-------------------------------
			ConstituencyElection ce2 = new ConstituencyElection(new Long(12));
			{
				Set<Nomination> nominations = new HashSet<Nomination>();
				Nomination n1 = new Nomination(new Long(206));
				n1.setParty(getParty(new Long(1)));
				CandidateResult cr1 = new CandidateResult(new Long(206));
				cr1.setVotesEarned(new Double(750));
				cr1.setRank(new Long(2));
				n1.setCandidateResult(cr1);				
				nominations.add(n1);
				

				Nomination n2 = new Nomination(new Long(207));
				n2.setParty(getParty(new Long(8)));
				CandidateResult cr2 = new CandidateResult(new Long(207));
				cr2.setVotesEarned(new Double(1000));
				cr2.setRank(new Long(1));
				n2.setCandidateResult(cr2);				
				nominations.add(n2);
				

				Nomination n3 = new Nomination(new Long(208));
				n3.setParty(getParty(new Long(6)));
				CandidateResult cr3 = new CandidateResult(new Long(208));
				cr3.setVotesEarned(new Double(600));
				cr3.setRank(new Long(3));
				n3.setCandidateResult(cr3);				
				nominations.add(n3);
				

				Nomination n4 = new Nomination(new Long(209));
				n4.setParty(getParty(new Long(7)));
				CandidateResult cr4 = new CandidateResult(new Long(209));
				cr4.setVotesEarned(new Double(500));
				cr4.setRank(new Long(4));
				n4.setCandidateResult(cr4);				
				nominations.add(n4);
				

				Nomination n5 = new Nomination(new Long(210));
				n5.setParty(getParty(new Long(9)));
				CandidateResult cr5 = new CandidateResult(new Long(210));
				cr5.setVotesEarned(new Double(400));
				cr5.setRank(new Long(5));
				n5.setCandidateResult(cr5);				
				nominations.add(n5);
				
				ce2.setNominations(nominations);
			}
			//-------------------------------
			ConstituencyElection ce3 = new ConstituencyElection(new Long(13));
			{
				Set<Nomination> nominations = new HashSet<Nomination>();
		
				Nomination n2 = new Nomination(new Long(212));
				n2.setParty(getParty(new Long(1)));
				CandidateResult cr2 = new CandidateResult(new Long(212));
				cr2.setVotesEarned(new Double(750));
				cr2.setRank(new Long(2));
				n2.setCandidateResult(cr2);				
				nominations.add(n2);
				

				Nomination n3 = new Nomination(new Long(213));
				n3.setParty(getParty(new Long(6)));
				CandidateResult cr3 = new CandidateResult(new Long(213));
				cr3.setVotesEarned(new Double(600));
				cr3.setRank(new Long(4));
				n3.setCandidateResult(cr3);				
				nominations.add(n3);
				

				Nomination n4 = new Nomination(new Long(214));
				n4.setParty(getParty(new Long(7)));
				CandidateResult cr4 = new CandidateResult(new Long(214));
				cr4.setVotesEarned(new Double(500));
				cr4.setRank(new Long(5));
				n4.setCandidateResult(cr4);				
				nominations.add(n4);
				
				Nomination n1 = new Nomination(new Long(211));
				n1.setParty(getParty(new Long(8)));
				CandidateResult cr1 = new CandidateResult(new Long(211));
				cr1.setVotesEarned(new Double(1200));
				cr1.setRank(new Long(1));
				n1.setCandidateResult(cr1);				
				nominations.add(n1);
				
				Nomination n5 = new Nomination(new Long(215));
				n5.setParty(getParty(new Long(9)));
				CandidateResult cr5 = new CandidateResult(new Long(215));
				cr5.setVotesEarned(new Double(700));
				cr5.setRank(new Long(3));
				n5.setCandidateResult(cr5);				
				nominations.add(n5);
				
				ce3.setNominations(nominations);
			}
			//-------------------------------
			ConstituencyElection ce4 = new ConstituencyElection(new Long(14));
			{
				Set<Nomination> nominations = new HashSet<Nomination>();
				
				Nomination n2 = new Nomination(new Long(217));
				n2.setParty(getParty(new Long(1)));
				CandidateResult cr2 = new CandidateResult(new Long(217));
				cr2.setVotesEarned(new Double(1200));
				cr2.setRank(new Long(2));
				n2.setCandidateResult(cr2);				
				nominations.add(n2);
				

				Nomination n3 = new Nomination(new Long(218));
				n3.setParty(getParty(new Long(6)));
				CandidateResult cr3 = new CandidateResult(new Long(218));
				cr3.setVotesEarned(new Double(600));
				cr3.setRank(new Long(3));
				n3.setCandidateResult(cr3);				
				nominations.add(n3);
				

				Nomination n4 = new Nomination(new Long(219));
				n4.setParty(getParty(new Long(7)));
				CandidateResult cr4 = new CandidateResult(new Long(219));
				cr4.setVotesEarned(new Double(500));
				cr4.setRank(new Long(4));
				n4.setCandidateResult(cr4);				
				nominations.add(n4);
				
				Nomination n1 = new Nomination(new Long(216));
				n1.setParty(getParty(new Long(8)));
				CandidateResult cr1 = new CandidateResult(new Long(1));
				cr1.setVotesEarned(new Double(1500));
				cr1.setRank(new Long(1));
				n1.setCandidateResult(cr1);				
				nominations.add(n1);
				
				Nomination n5 = new Nomination(new Long(220));
				n5.setParty(getParty(new Long(9)));
				CandidateResult cr5 = new CandidateResult(new Long(220));
				cr5.setVotesEarned(new Double(400));
				cr5.setRank(new Long(5));
				n5.setCandidateResult(cr5);				
				nominations.add(n5);
				
				ce4.setNominations(nominations);
			}
			//-------------------------------
			ConstituencyElection ce5 = new ConstituencyElection(new Long(15));
			{
				Set<Nomination> nominations = new HashSet<Nomination>();
				
				Nomination n4 = new Nomination(new Long(224));
				n4.setParty(getParty(new Long(7)));
				CandidateResult cr4 = new CandidateResult(new Long(224));
				cr4.setVotesEarned(new Double(500));
				cr4.setRank(new Long(4));
				n4.setCandidateResult(cr4);				
				nominations.add(n4);
				

				Nomination n5 = new Nomination(new Long(225));
				n5.setParty(getParty(new Long(9)));
				CandidateResult cr5 = new CandidateResult(new Long(225));
				cr5.setVotesEarned(new Double(400));
				cr5.setRank(new Long(5));
				n5.setCandidateResult(cr5);				
				nominations.add(n5);
				
				Nomination n1 = new Nomination(new Long(221));
				n1.setParty(getParty(new Long(8)));
				CandidateResult cr1 = new CandidateResult(new Long(221));
				cr1.setVotesEarned(new Double(1000));
				cr1.setRank(new Long(1));
				n1.setCandidateResult(cr1);				
				nominations.add(n1);
				

				Nomination n2 = new Nomination(new Long(22));
				n2.setParty(getParty(new Long(1)));
				CandidateResult cr2 = new CandidateResult(new Long(222));
				cr2.setVotesEarned(new Double(750));
				cr2.setRank(new Long(2));
				n2.setCandidateResult(cr2);				
				nominations.add(n2);
				

				Nomination n3 = new Nomination(new Long(23));
				n3.setParty(getParty(new Long(6)));
				CandidateResult cr3 = new CandidateResult(new Long(223));
				cr3.setVotesEarned(new Double(600));
				cr3.setRank(new Long(3));
				n3.setCandidateResult(cr3);				
				nominations.add(n3);
				
				ce5.setNominations(nominations);
			}
			constituencyElectionList.add(ce1);
			constituencyElectionList.add(ce2);
			constituencyElectionList.add(ce3);
			constituencyElectionList.add(ce4);
			constituencyElectionList.add(ce5);
		}
		return constituencyElectionList;
		
	}
	
	public static List<Party> getParties(){
		return parties;
	}
}
