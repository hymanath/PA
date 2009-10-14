package com.itgrids.partyanalyst.util;

import java.util.ArrayList;
import java.util.List;

import com.itgrids.partyanalyst.model.Candidate;

public class DummyCandidates {

	public static List<Candidate> getCandidates(){
		List <Candidate> candidates = new ArrayList<Candidate>();
		Candidate c1 = new Candidate(new Long(1),"aaa1", null ,"bbb1",  null ,  null , null , null ,  null ,  null , null ,  null ,null);
		Candidate c2 = new Candidate(new Long(1),"aaa2", null ,"bbb2",  null ,  null , null , null ,  null ,  null , null ,  null ,null);
		Candidate c3 = new Candidate(new Long(1),"aaa3", null ,"bbb3",  null ,  null , null , null ,  null ,  null , null ,  null ,null);
		Candidate c4 = new Candidate(new Long(1),"aaa4", null ,"bbb5",  null ,  null , null , null ,  null ,  null , null ,  null ,null);
		Candidate c5 = new Candidate(new Long(1),"aaa5", null ,"bbb6",  null ,  null , null , null ,  null ,  null , null ,  null ,null);
		
		candidates.add(c1);
		candidates.add(c2);
		candidates.add(c3);
		candidates.add(c4);
		candidates.add(c5);
		
		return candidates;
	}
	
	public static List<String> getExpectedCandidateNames(){
		List<String> expectedCandidateNames = new ArrayList<String>();
		expectedCandidateNames.add("aaa1 bbb1");
		expectedCandidateNames.add("aaa2 bbb2");
		expectedCandidateNames.add("aaa3 bbb3");
		expectedCandidateNames.add("aaa4 bbb5");
		expectedCandidateNames.add("aaa5 bbb6");
		
		return expectedCandidateNames;
	}
	
}
