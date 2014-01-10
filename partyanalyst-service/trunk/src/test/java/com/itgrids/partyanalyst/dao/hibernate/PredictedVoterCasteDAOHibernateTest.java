package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPredictedVoterCasteDAO;

public class PredictedVoterCasteDAOHibernateTest extends BaseDaoTestCase{
	private IPredictedVoterCasteDAO predictedVoterCasteDAO;

	public void setPredictedVoterCasteDAO(
			IPredictedVoterCasteDAO predictedVoterCasteDAO) {
		this.predictedVoterCasteDAO = predictedVoterCasteDAO;
	}
	
	
	public void test()
	{
		predictedVoterCasteDAO.getAll();
	}

}
