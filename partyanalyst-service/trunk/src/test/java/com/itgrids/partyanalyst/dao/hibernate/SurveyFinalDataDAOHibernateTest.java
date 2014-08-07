package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ISurveyFinalDataDAO;

public class SurveyFinalDataDAOHibernateTest extends BaseDaoTestCase {

	private ISurveyFinalDataDAO surveyFinalDataDAO;

	public ISurveyFinalDataDAO getSurveyFinalDataDAO() {
		return surveyFinalDataDAO;
	}

	public void setSurveyFinalDataDAO(ISurveyFinalDataDAO surveyFinalDataDAO) {
		this.surveyFinalDataDAO = surveyFinalDataDAO;
	}

	public void test(){
		List<Long> boothIds = new ArrayList<Long>();
		boothIds.add(439809l);
		boothIds.add(439819l);
		
		List<Object[]> slist = surveyFinalDataDAO.getThirdPartyStatusWithBooths(boothIds);
		System.out.println(slist.size());
	}
	
}
