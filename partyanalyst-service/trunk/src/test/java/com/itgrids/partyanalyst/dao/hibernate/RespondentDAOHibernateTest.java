package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IRespondentDAO;

public class RespondentDAOHibernateTest extends BaseDaoTestCase{
	private IRespondentDAO respondentDAO;

	public void setRespondentDAO(IRespondentDAO respondentDAO) {
		this.respondentDAO = respondentDAO;
	}
    public void testGetAll(){
    	respondentDAO.getAll();
    }
}
