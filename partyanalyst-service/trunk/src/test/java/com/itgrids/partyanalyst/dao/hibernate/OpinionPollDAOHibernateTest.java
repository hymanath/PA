package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;
import com.itgrids.partyanalyst.dao.IOpinionPollDAO;

public class OpinionPollDAOHibernateTest extends BaseDaoTestCase {
	
	private IOpinionPollDAO opinionPollDAO;

	public void setOpinionPollDAO(IOpinionPollDAO opinionPollDAO) {
		this.opinionPollDAO = opinionPollDAO;
	}

	public void test(){
		opinionPollDAO.getAll();		
	}
}
