package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPredictedVoterCasteDAO;

public class PredictedVoterCasteDAOHibernateTest extends BaseDaoTestCase{
	private IPredictedVoterCasteDAO predictedVoterCasteDAO;

	public void setPredictedVoterCasteDAO(
			IPredictedVoterCasteDAO predictedVoterCasteDAO) {
		this.predictedVoterCasteDAO = predictedVoterCasteDAO;
	}
	
	
	/*public void test()
	{
		predictedVoterCasteDAO.getAll();
	}*/
	
	/*public void testGetPredictedCasteList()
	{
		List<Object[]> list = predictedVoterCasteDAO.getPredictedCasteList();
		System.out.println(list.size());
	}*/
	
	/*public void testGetVoterIdsFromCastePredictionForACaste()
	{
		List<Long> voterIdsList = predictedVoterCasteDAO.getVoterIdsFromCastePredictionForACaste(292l,0,100);
		System.out.println(voterIdsList.size());
	}*/
	
	public void testGetVoterIdsFromCastePrediction()
	{
		List<Long> voterIdsList = predictedVoterCasteDAO.getVoterIdsFromCastePrediction(1l,292l,0,100);
		System.out.println(voterIdsList.size());
	}

}
