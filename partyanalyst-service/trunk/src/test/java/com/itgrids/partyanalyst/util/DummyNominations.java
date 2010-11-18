package com.itgrids.partyanalyst.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.BoothConstituencyElection;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.CandidateBoothResult;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ConstituencyElection;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.ElectionScope;
import com.itgrids.partyanalyst.model.ElectionType;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.Party;

public class DummyNominations {

	public static List<Nomination> getNominations(){
		List <Nomination> nominations = new ArrayList<Nomination>();

		Nomination nomination1 = new Nomination();
		ConstituencyElection constituencyElection1 = new ConstituencyElection();
		Election electionl = new Election();
		electionl.setElectionYear("2004");
		constituencyElection1.setElection(electionl);
		nomination1.setConstituencyElection(constituencyElection1);		
		
		Nomination nomination2 = new Nomination();
		ConstituencyElection constituencyElection2 = new ConstituencyElection();
		Election election2 = new Election();
		election2.setElectionYear("2009");
		constituencyElection2.setElection(election2);
		nomination2.setConstituencyElection(constituencyElection2);

		Nomination nomination3 = new Nomination();
		ConstituencyElection constituencyElection3 = new ConstituencyElection();
		Election election3 = new Election();
		election3.setElectionYear("2007");
		constituencyElection3.setElection(election3);
		nomination3.setConstituencyElection(constituencyElection3);

		nominations.add(nomination1);
		nominations.add(nomination2);
		nominations.add(nomination3);
		return nominations;
	}
	
	public static List<Nomination> getNominationsWithBoothResults(){
		List <Nomination> nominations = new ArrayList<Nomination>();
		Booth booth1 = new Booth("1", null , "Nimmanapalli Road","Basinikonda, Ramacharlapalli", null, null,null,new Long(1000),null,null,null,null,null,null);
		Booth booth2 = new Booth("2", null , "Nimmanapalli Road1","Basinikonda, Ramacharlapalli1", null, null,null,new Long(1500),null,null,null,null,null,null);
		Booth booth3= new Booth("3", null , "Nimmanapalli Road2","Basinikonda, Ramacharlapalli2", null, null,null,new Long(1400),null,null,null,null,null,null);
		Booth booth4 = new Booth("4", null , "Nimmanapalli Road3","Basinikonda, Ramacharlapalli3", null, null,null,new Long(900),null,null,null,null,null,null);
		Booth booth5 = new Booth("5", null , "Nimmanapalli Road4","Basinikonda, Ramacharlapalli4", null, null,null,new Long(1220),null,null,null,null,null,null);
		
		BoothConstituencyElection bce1 = new BoothConstituencyElection(booth1, null,null,null,null);
		BoothConstituencyElection bce2= new BoothConstituencyElection(booth2, null,null,null,null);
		BoothConstituencyElection bce3 = new BoothConstituencyElection(booth3, null,null,null, null);
		BoothConstituencyElection bce4 = new BoothConstituencyElection(booth4, null,null,null, null);
		BoothConstituencyElection bce5 = new BoothConstituencyElection(booth5, null,null,null, null);
		
		CandidateBoothResult br1 = new CandidateBoothResult(new Long(900),null,null,bce1);
		CandidateBoothResult br2 = new CandidateBoothResult(new Long(1200),null,null,bce2);
		CandidateBoothResult br3 = new CandidateBoothResult(new Long(1300),null,null,bce3);
		CandidateBoothResult br4 = new CandidateBoothResult(new Long(750),null,null,bce4);
		CandidateBoothResult br5 = new CandidateBoothResult(new Long(1100),null,null,bce5);
		
		Set<CandidateBoothResult> brs = new HashSet<CandidateBoothResult>();
		brs.add(br1);
		brs.add(br2);
		brs.add(br3);
		brs.add(br4);
		brs.add(br5);
		
		Candidate candidate = new Candidate();
		candidate.setLastname("Konedala Chiranjeevi");
		
		Constituency consti = new Constituency();
		consti.setName("Tirupathi");
		
		ElectionType elecType = new ElectionType();
		elecType.setElectionType("Assembly");
		
		ElectionScope elecScope = new ElectionScope();
		elecScope.setElectionType(elecType);
		
		Election elec = new Election();
		elec.setElectionScope(elecScope);
		elec.setElectionYear("2004");
		
		ConstituencyElection consElec = new ConstituencyElection();
		consElec.setElection(elec);
		consElec.setConstituency(consti);
		
		Party party = new Party();
		party.setShortName("PRP");
		
		Nomination nomination = new Nomination();
		nomination.setCandidateBoothResults(brs);
		nomination.setConstituencyElection(consElec);
		nomination.setCandidate(candidate);
		nomination.setParty(party);
		nominations.add(nomination);
		
		return nominations;
	}
}
