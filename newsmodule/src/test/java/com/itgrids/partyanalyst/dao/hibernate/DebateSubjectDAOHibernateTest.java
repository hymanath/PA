package com.itgrids.partyanalyst.dao.hibernate;

import java.text.SimpleDateFormat;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IDebateSubjectDAO;

public class DebateSubjectDAOHibernateTest extends BaseDaoTestCase{

	private IDebateSubjectDAO debateSubjectDAO;

	public void setDebateSubjectDAO(IDebateSubjectDAO debateSubjectDAO) {
		this.debateSubjectDAO = debateSubjectDAO;
	}
	
	/*public void testAll()
	{
		
	}*/
	
	/*public void testgetDebateDetalsForSelectedCriteria()
	{
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			String fromDateStr = "01/01/2014";
			String toDateStr = "02/02/2014";
			List<Object[]> values = debateSubjectDAO.getDebateDetalsForSelectedCriteria(sdf.parse(fromDateStr),sdf.parse(toDateStr),null,362l,2225l);
			System.out.println(values.size());
			for (Object[] parms : values) {
				System.out.println(parms[0] +":"+ parms[1] +":"+ parms[2]);
			}
		} 
		catch (Exception e)
		{
			
		}
		
	}*/
	
	public void testsearchCriteriaForDebateSearch()
	{
		List<Object[]> values = debateSubjectDAO.searchCriteriaForDebateSearch("u0C48");
		for (Object[] parms : values) {
			System.out.println(parms[0] +":"+ parms[1] +":"+ parms[2]);
		}
	}
}
