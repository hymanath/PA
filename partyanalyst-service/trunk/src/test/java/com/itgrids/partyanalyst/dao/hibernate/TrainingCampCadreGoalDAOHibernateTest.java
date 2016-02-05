package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ITrainingCampCadreGoalDAO;

public class TrainingCampCadreGoalDAOHibernateTest extends BaseDaoTestCase{
	
	private ITrainingCampCadreGoalDAO trainingCampCadreGoalDAO;

	public void setTrainingCampCadreGoalDAO(
			ITrainingCampCadreGoalDAO trainingCampCadreGoalDAO) {
		this.trainingCampCadreGoalDAO = trainingCampCadreGoalDAO;
	}
	
	public void test()
	{
		int count = trainingCampCadreGoalDAO.deleteGoalsforACadre(6677977l, 16l);
		System.out.println(count);
	}

}
