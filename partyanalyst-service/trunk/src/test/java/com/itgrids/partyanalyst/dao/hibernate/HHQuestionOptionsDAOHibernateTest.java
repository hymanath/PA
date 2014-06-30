package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IHHQuestionOptionsDAO;

public class HHQuestionOptionsDAOHibernateTest extends BaseDaoTestCase{
	
	private IHHQuestionOptionsDAO hhQuestionOptionsDAO;

	
	
	public IHHQuestionOptionsDAO getHhQuestionOptionsDAO() {
		return hhQuestionOptionsDAO;
	}



	public void setHhQuestionOptionsDAO(IHHQuestionOptionsDAO hhQuestionOptionsDAO) {
		this.hhQuestionOptionsDAO = hhQuestionOptionsDAO;
	}



	public void test(){
		List<Object[]> list = hhQuestionOptionsDAO.getOptionsForQuestions(2l);
		System.out.println(list.size());
	}

	

}
