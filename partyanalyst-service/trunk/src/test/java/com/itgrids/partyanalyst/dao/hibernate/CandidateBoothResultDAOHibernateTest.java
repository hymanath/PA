package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.model.CandidateBoothResult;
import com.itgrids.partyanalyst.model.Party;

public class CandidateBoothResultDAOHibernateTest extends BaseDaoTestCase{
	
	private ICandidateBoothResultDAO candidateBoothResultDAO;

	public ICandidateBoothResultDAO getCandidateBoothResultDAO() {
		return candidateBoothResultDAO;
	}

	public void setCandidateBoothResultDAO(
			ICandidateBoothResultDAO candidateBoothResultDAO) {
		this.candidateBoothResultDAO = candidateBoothResultDAO;
	}
	
	/*public void testFindByBoothConstituencyElectionAndParty(){
		List<CandidateBoothResult> list = candidateBoothResultDAO.findByBoothConstituencyElectionAndParty(new Long(200), new Long(1));
		assertEquals(1, list.size());
	}*/

	/*public void testFindByConstituencyElection(){
		List<CandidateBoothResult> list = candidateBoothResultDAO.findByConstituencyElection(new Long(125));
		assertEquals(1950, list.size());
	}*/
	
	/*public void testFindByNominationAndBoothConstituencyElection(){
		List<CandidateBoothResult> list = candidateBoothResultDAO.findByNominationAndBoothConstituencyElection(new Long(690), new Long(77));
		assertEquals(1, list.size());
	}*/
	
	/*public void testFidPartiesForConstituencyAndElection(){
		List<Party> list = candidateBoothResultDAO.findPartiesByConstituencyAndElectionYear(new Long(408), "2004");
		assertEquals("INC", list.get(0).getShortName());
	}*/
	
	/*public void testGetPartyGenderWiseBoothVotesForMandal(){
		List list = candidateBoothResultDAO.getPartyGenderWiseBoothVotesForMandal(864l);
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]+"\t"+((Object[])list.get(i))[4]+"\t"+((Object[])list.get(i))[5]+"\t"+((Object[])list.get(i))[6]+"\t"+((Object[])list.get(i))[7]+"\t"+((Object[])list.get(i))[8]+"\t"+((Object[])list.get(i))[9]+"\t"+((Object[])list.get(i))[10]+"\t"+((Object[])list.get(i))[11]+"\t"+((Object[])list.get(i))[12]+"\t"+((Object[])list.get(i))[13]+"\t"+((Object[])list.get(i))[14]+"\t"+((Object[])list.get(i))[15]+"\t"+((Object[])list.get(i))[16]);
	}*/
	
	public void testFindMandalWisePartiesResultsForElection(){
		List list = candidateBoothResultDAO.findMandalWisePartiesResultsForElection(844l, 2l);
		assertEquals(list.size(), 2);
	}
}
