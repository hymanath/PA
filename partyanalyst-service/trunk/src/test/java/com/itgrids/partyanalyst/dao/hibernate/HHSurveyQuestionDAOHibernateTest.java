package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IHHSurveyQuestionDAO;
import com.itgrids.partyanalyst.model.HHSurveyQuestion;

public class HHSurveyQuestionDAOHibernateTest extends BaseDaoTestCase{
	
	private IHHSurveyQuestionDAO hhSurveyQuestionDAO;

	public void setHhSurveyQuestionDAO(IHHSurveyQuestionDAO hhSurveyQuestionDAO) {
		this.hhSurveyQuestionDAO = hhSurveyQuestionDAO;
	}



	public void test(){
		List<HHSurveyQuestion> list=hhSurveyQuestionDAO.getModelBySurveyId(1l);
		System.out.println(list.size());
	}

}
