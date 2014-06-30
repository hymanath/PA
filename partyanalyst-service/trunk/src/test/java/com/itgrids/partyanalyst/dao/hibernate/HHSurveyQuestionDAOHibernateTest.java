package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IHHSurveyQuestionDAO;

public class HHSurveyQuestionDAOHibernateTest extends BaseDaoTestCase{
	
	private IHHSurveyQuestionDAO hhSurveyQuestionDAO;

	public IHHSurveyQuestionDAO getHhSurveyQuestionDAO() {
		return hhSurveyQuestionDAO;
	}

	public void setHhSurveyQuestionDAO(IHHSurveyQuestionDAO hhSurveyQuestionDAO) {
		this.hhSurveyQuestionDAO = hhSurveyQuestionDAO;
	}

	



	/*public void test(){
		List<HHSurveyQuestion> list=hhSurveyQuestionDAO.getModelBySurveyId(1l);
		System.out.println(list.size());
	}*/
	
	public void test(){
		List<Object[]> list = hhSurveyQuestionDAO.getAllQuestionInSurvey(1l);
		System.out.println(list.size());
	}

}
