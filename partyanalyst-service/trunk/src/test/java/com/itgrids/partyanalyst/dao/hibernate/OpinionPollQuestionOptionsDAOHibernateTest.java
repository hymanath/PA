package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IOpinionPollQuestionOptionsDAO;

public class OpinionPollQuestionOptionsDAOHibernateTest extends BaseDaoTestCase{

	private IOpinionPollQuestionOptionsDAO opinionPollQuestionOptionsDAO;

	public IOpinionPollQuestionOptionsDAO getOpinionPollQuestionOptionsDAO() {
		return opinionPollQuestionOptionsDAO;
	}

	public void setOpinionPollQuestionOptionsDAO(
			IOpinionPollQuestionOptionsDAO opinionPollQuestionOptionsDAO) {
		this.opinionPollQuestionOptionsDAO = opinionPollQuestionOptionsDAO;
	}
	
	
	/*public void testGet(){
		opinionPollQuestionOptionsDAO.getAll();
	}
	*/
	public void testgetOptions()
	{
		List<Object[]> list = opinionPollQuestionOptionsDAO.getOptions(2l);
		for(Object[] params : list)
		{
			System.out.println(params[0].toString());
			System.out.println(params[1].toString());
		}
	}
}
