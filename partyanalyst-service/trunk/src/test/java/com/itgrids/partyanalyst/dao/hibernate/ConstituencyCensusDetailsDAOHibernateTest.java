package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IConstituencyCensusDetailsDAO;
import com.itgrids.partyanalyst.model.ConstituencyCensusDetails;

public class ConstituencyCensusDetailsDAOHibernateTest extends BaseDaoTestCase{
	
	private IConstituencyCensusDetailsDAO constituencyCensusDetailsDAO;

	public void setConstituencyCensusDetailsDAO(
			IConstituencyCensusDetailsDAO constituencyCensusDetailsDAO) {
		this.constituencyCensusDetailsDAO = constituencyCensusDetailsDAO;
	}
	/*public void test()
	{
		constituencyCensusDetailsDAO.getAll();
	}*/
	
	/*public void testFindConstituencyWiseCensusDetails()
	{
		List<Object[]> list =  constituencyCensusDetailsDAO.findConstituencyWiseCensusDetails(1l, 232l, 2001l);
		System.out.println(list.size());
		for(Object obj:list.get(0))
		{
			System.out.println(obj.toString());
		}
	}*/
	
	/*public void testCheckForConstituencyExistance()
	{
		List<Long> list =  constituencyCensusDetailsDAO.checkForConstituencyExistance(232l);
		System.out.println(list.size());
		
		for(Long id:list){
			System.out.println(id);
			if(232l==id)
			{
				System.out.println("y");
			}
		}
	}*/
	/*public void testGetConstituencyIdsAndPercentages()
	{
		//String censusParam = "model.percentageSC";
		//String censusParam = "model.percentageST";
		//String censusParam = "model.popLiteraturePercentage";
		//String censusParam = "(100-model.popLiteraturePercentage)";
		String censusParam = "model.totalWorkingPopPercentage";
		//String censusParam = "model.nonWorkingPopPercentage";
		List<Object[]> list = constituencyCensusDetailsDAO.getConstituencyIdsAndPercentages(censusParam, 1l);
		
		System.out.println(list.size());
		for(Object[] obj: list)
		{
			System.out.print("==Constituency Id --"+obj[0].toString());
			System.out.println("==percentage      --"+obj[1].toString());
		}
	}*/
	
	public void testGetCensusConstituencyByConstituencyId()
	{
		List<ConstituencyCensusDetails> list = constituencyCensusDetailsDAO.getCensusConstituencyByConstituencyId(1l);
		
		ConstituencyCensusDetails constituencyCensusDetails = list.get(0);
		
		System.out.println(constituencyCensusDetails.getTru());
	}
}
