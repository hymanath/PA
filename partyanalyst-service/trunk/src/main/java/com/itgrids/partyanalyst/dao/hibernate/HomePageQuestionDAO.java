package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IHomePageQuestionDAO;
import com.itgrids.partyanalyst.model.HomePageQuestion;

public class HomePageQuestionDAO extends GenericDaoHibernate<HomePageQuestion, Long> implements IHomePageQuestionDAO{

	public HomePageQuestionDAO(){
		super(HomePageQuestion.class);
	}
	
	
	public List getQuestionsForThePresentDay(Date currentDate,String isDelete) {
		
		
		Object[] params = {currentDate,currentDate,isDelete};
		return getHibernateTemplate().find("select model.homePageQuestionId,model.question  from HomePageQuestion model"+
				" where date(model.questionStartDate) <= ? and date(model.questionEndDate) >= ?"+
				" and model.isDelete != ?",params);
				
	}
}
