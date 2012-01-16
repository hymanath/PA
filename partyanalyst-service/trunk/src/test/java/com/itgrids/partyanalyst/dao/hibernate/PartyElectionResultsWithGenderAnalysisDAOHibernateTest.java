package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import com.itgrids.partyanalyst.dao.IPartyElectionResultsWithGenderAnalysisDAO;

public class PartyElectionResultsWithGenderAnalysisDAOHibernateTest extends BaseDaoTestCase{
	
	private IPartyElectionResultsWithGenderAnalysisDAO partyElectionResultsWithGenderAnalysisDAO;

	public void setPartyElectionResultsWithGenderAnalysisDAO(
			IPartyElectionResultsWithGenderAnalysisDAO partyElectionResultsWithGenderAnalysisDAO) {
		this.partyElectionResultsWithGenderAnalysisDAO = partyElectionResultsWithGenderAnalysisDAO;
	}
	
	/*public void test()
	{
		partyElectionResultsWithGenderAnalysisDAO.getAll();
	}*/

	/*public void testGetCountOfPartiesInAElection()
	{
		List<Long> partiesList = new ArrayList<Long>(0);
		partiesList.add(362l);
		partiesList.add(872l);
		Long obj = (Long) partyElectionResultsWithGenderAnalysisDAO.getCountOfPartiesInAElection(3l, partiesList);
		
		System.out.println(obj.toString());
	}*/
	
	public void testGetPartyWiseGenderDetails()
	{
		List<Long> partiesList = new ArrayList<Long>(0);
		partiesList.add(362l);
		partiesList.add(872l);
		
		List<Object[]> list = partyElectionResultsWithGenderAnalysisDAO.getPartyWiseGenderDetails(3l, partiesList);
		
		System.out.println();
		
		for(Object[] params : list)
		{
			System.out.println();
			for(Object obj : params)
				System.out.print("\t"+obj.toString());
		}
	}
}
