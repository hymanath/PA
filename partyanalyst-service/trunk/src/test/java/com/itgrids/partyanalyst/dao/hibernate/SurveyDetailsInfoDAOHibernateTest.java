package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ISurveyDetailsInfoDAO;

public class SurveyDetailsInfoDAOHibernateTest extends BaseDaoTestCase{
	private ISurveyDetailsInfoDAO surveyDetailsInfoDAO;

	public void setSurveyDetailsInfoDAO(ISurveyDetailsInfoDAO surveyDetailsInfoDAO) {
		this.surveyDetailsInfoDAO = surveyDetailsInfoDAO;
	}
	
	public void test()
	{
		/*List<Long> boothIds = new ArrayList<Long>();
		boothIds.add(371197l);
		boothIds.add(371198l);
		 List<Object[]> list = surveyDetailsInfoDAO.getVoterDetailsForbooths(boothIds);
		 System.out.println(list.size());*/
		surveyDetailsInfoDAO.getAll();
		
		 
	}

}
