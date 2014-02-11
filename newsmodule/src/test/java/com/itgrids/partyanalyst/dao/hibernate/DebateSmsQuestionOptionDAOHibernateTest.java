package com.itgrids.partyanalyst.dao.hibernate;

import java.text.SimpleDateFormat;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IDebateSmsQuestionOptionDAO;

public class DebateSmsQuestionOptionDAOHibernateTest extends BaseDaoTestCase{

	private IDebateSmsQuestionOptionDAO debateSmsQuestionOptionDAO;

	public void setDebateSmsQuestionOptionDAO(
			IDebateSmsQuestionOptionDAO debateSmsQuestionOptionDAO) {
		this.debateSmsQuestionOptionDAO = debateSmsQuestionOptionDAO;
	}
	
	/*public void testAll()
	{
		
	}*/
	
	public void testgetSmsQuestionDetails()
	{
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			String fromDateStr = "01/01/2013";
			String toDateStr = "02/05/2014";
			
			List<Object[]> values = debateSmsQuestionOptionDAO.getSmsQuestionDetails(sdf.parse(fromDateStr), sdf.parse(toDateStr));
			for (Object[] objects : values) {
				System.out.println(objects[0] +":"+ objects[1] +":"+ objects[2] +":"+ objects[3]);
			}
		
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
