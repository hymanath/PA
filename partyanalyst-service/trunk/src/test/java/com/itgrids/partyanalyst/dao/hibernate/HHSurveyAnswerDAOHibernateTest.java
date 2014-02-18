package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IHHSurveyAnswersDAO;
import com.itgrids.partyanalyst.model.HHSurveyAnswers;

public class HHSurveyAnswerDAOHibernateTest extends BaseDaoTestCase{
	
	private IHHSurveyAnswersDAO hhSurveyAnswersDAO;
	
	

	public IHHSurveyAnswersDAO getHhSurveyAnswersDAO() {
		return hhSurveyAnswersDAO;
	}


	public void test(){
		List<HHSurveyAnswers> list=hhSurveyAnswersDAO.getSurveyAnswersByHouseHoldId(21l);
		System.out.println(list.size());
	}

}
