package com.itgrids.partyanalyst.dao.hibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dto.CandidatePartyInfoVO;
import com.itgrids.partyanalyst.dto.ConstituencyOrMandalWiseElectionVO;
import com.itgrids.partyanalyst.dto.ConstituencyRevenueVillagesVO;
import com.itgrids.partyanalyst.dto.PartyElectionResultVO;
import com.itgrids.partyanalyst.model.CandidateBoothResult;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.utils.IConstants;

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
		List list = candidateBoothResultDAO.getPartyGenderWiseBoothVotesForMandal(844l);
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]+"\t"+((Object[])list.get(i))[4]+"\t"+((Object[])list.get(i))[5]+"\t"+((Object[])list.get(i))[6]+"\t"+((Object[])list.get(i))[7]+"\t"+((Object[])list.get(i))[8]+"\t"+((Object[])list.get(i))[9]+"\t"+((Object[])list.get(i))[10]+"\t"+((Object[])list.get(i))[11]+"\t"+((Object[])list.get(i))[12]+"\t"+((Object[])list.get(i))[13]+"\t"+((Object[])list.get(i))[14]+"\t"+((Object[])list.get(i))[15]+"\t"+((Object[])list.get(i))[16]);
	}*/
	
	/*public void testFindMandalWisePartiesResultsForElection(){
		List list = candidateBoothResultDAO.findMandalWisePartiesResultsForElection(844l, 2l);
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]+"\t"+((Object[])list.get(i))[4]+"\t"+((Object[])list.get(i))[5]+"\t"+((Object[])list.get(i))[6]+"\t"+((Object[])list.get(i))[7]+"\t"+((Object[])list.get(i))[8]+"\t"+((Object[])list.get(i))[9]);
	}*/
	
	/*public void testFindTownshipElectionResult(){
		long start = System.nanoTime();
		List list = candidateBoothResultDAO.findTownshipElectionResult(21817l, 2l);
		long end = System.nanoTime();
		System.out.println((end-start)/1000000000);
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]);
	}*/
	
	/*public void testGetBoothsResultsForBooths(){
		List list = candidateBoothResultDAO.findPartyResultsForBooths("221,222");
		System.out.println(list.size());
	}*/
	
	/*public void testFindAllElectionsInfoInRevenueVillage(){
		long start = System.nanoTime();
		List list = candidateBoothResultDAO.findAllElectionsInfoInRevenueVillage(21816l);
		long end = System.nanoTime();
		System.out.println((end-start)/1000000000);
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]+"\t"+((Object[])list.get(i))[4]+"\t"+((Object[])list.get(i))[5]+"\t"+((Object[])list.get(i))[6]+"\t"+((Object[])list.get(i))[7]+"\t"+((Object[])list.get(i))[8]+"\t"+((Object[])list.get(i))[9]+"\t"+((Object[])list.get(i))[10]+"\t"+((Object[])list.get(i))[11]+"\t"+((Object[])list.get(i))[12]+"\t"+((Object[])list.get(i))[13]+"\t"+((Object[])list.get(i))[14]+"\t"+((Object[])list.get(i))[15]+"\t"+((Object[])list.get(i))[16]);
	}*/
	
	/*public void testFindAllElectionsInfoOfRevenueVillagesInTehsil(){
		long start = System.nanoTime();
		List list = candidateBoothResultDAO.findAllElectionsInfoOfRevenueVillagesInTehsil(844l, 4l);
		long end = System.nanoTime();
		System.out.println((end-start)/1000000000);
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]+"\t"+((Object[])list.get(i))[4]+"\t"+((Object[])list.get(i))[5]+"\t"+((Object[])list.get(i))[6]+"\t"+((Object[])list.get(i))[7]+"\t"+((Object[])list.get(i))[8]+"\t"+((Object[])list.get(i))[9]+"\t"+((Object[])list.get(i))[10]);
	}*/
	
/*	public void testFindBoothResultsForTownshipAndElection(){
		List list = candidateBoothResultDAO.findBoothResultsForTownshipAndElection(21794l, 2l);
		System.out.println(list.size());
	}*/
	
	/*public void testGetAllACPCPartiesInMandal(){
		List list = candidateBoothResultDAO.getAllACPCPartiesInMandal(843l);
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]);
	}*/
	
	/*public void testFindAssemblyWiseParliamentResultsForParties(){
		long start = System.nanoTime();
		List list = candidateBoothResultDAO.findAssemblyWiseParliamentResultsForParties(231l, 403l, "2009");
		long end = System.nanoTime();
		System.out.println((end-start)/1000000000);
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]);
	}*/
		
/*	public void testAssemblies(){
		List list = candidateBoothResultDAO.findAllPartiesElectionResultsInDistrictForElectionType(19l,"Parliament");
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]);
	}*/
	

	/*public void testAssemblies(){

	List list = candidateBoothResultDAO.getCandidatesResultsForElectionAndConstituencyByMandal(3382l,"2009");
	for(int i=0; i<list.size(); i++)
		System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]);

	}
	*/
	/*public void testGetcandidatesResultsByBoothConstiIds(){
		List list = candidateBoothResultDAO.getcandidatesResultsByBoothConstiIds("2323,2324,2325,2326,2327,2328,2329,2330,2331,	2332,2333,				2334,				2335,				2336,				2337,				2338,				2339,				2340,				2341");
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]+"\t"+((Object[])list.get(i))[4]+"\t"+((Object[])list.get(i))[5]+"\t"+((Object[])list.get(i))[6]+"\t"+((Object[])list.get(i))[7]+"\t"+((Object[])list.get(i))[8]);
	}
	
	public void testGetMandalsForAParliament(){
		List list = candidateBoothResultDAO.getMandalsForAConstituencyForAGivenYear(3554l,"2009");
		ListIterator it = list.listIterator();
		while(it.hasNext()){
			Object[] parms = (Object[])it.next();
			System.out.println(parms[0]+"\t\t"+parms[1]);
			
		}
		System.out.println(list.size());
	}*/
	
	/*public void testGetBoothWisePartyResultsInAMandalByConstituencyId()
	{
		List list = candidateBoothResultDAO.getBoothWisePartyResultsInAMandalByConstituencyId(141L, 61L, 388L, "2009", IConstants.PARLIAMENT_ELECTION_TYPE);
		System.out.println(list.size());
		ListIterator li = list.listIterator();
		while(li.hasNext()){
			Object[] params = (Object[])li.next();
			System.out.println(params[0]+"\t"+params[1]+"\t"+params[2]);
		}
	}
	
	public void testFindPartyResultsInAllElectionsByRevenueVillagesInMandal(){
		List list = candidateBoothResultDAO.findPartyResultsInAllElectionsByRevenueVillagesInMandal(373l, 24l);
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]+"\t"+((Object[])list.get(i))[4]+"\t"+((Object[])list.get(i))[5]+"\t"+((Object[])list.get(i))[6]+"\t"+((Object[])list.get(i))[7]+"\t"+((Object[])list.get(i))[8]);
	}*/
	
	

}
