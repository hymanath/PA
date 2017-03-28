package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISurveyorDAO;
import com.itgrids.partyanalyst.model.Surveyor;
import com.itgrids.partyanalyst.model.UserProblem;

public class SurveyorDAO extends GenericDaoHibernate<Surveyor, Long> implements ISurveyorDAO{

	public SurveyorDAO() {
		super(Surveyor.class);
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("unchecked")
	public List<Surveyor> getSurveyorDetails()
	{
		return getHibernateTemplate().find("from Surveyor model ");
	}

   	@SuppressWarnings("unchecked")
   	public List<Surveyor> getSurveyDataBySurveyorId(Long surveyorId)
   {
   	return getHibernateTemplate().find("from Surveyor model where model.surveyorId = ? ",surveyorId);
   }
   	
}
