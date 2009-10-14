package com.itgrids.partyanalyst.util;

import java.util.ArrayList;
import java.util.List;

import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.ConstituencyElection;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.Nomination;

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
	
}
