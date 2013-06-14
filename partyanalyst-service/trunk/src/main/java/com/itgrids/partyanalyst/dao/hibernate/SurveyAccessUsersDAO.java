package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISurveyAccessUsersDAO;
import com.itgrids.partyanalyst.model.SurveyAccessUsers;

public class SurveyAccessUsersDAO extends GenericDaoHibernate<SurveyAccessUsers, Long> implements ISurveyAccessUsersDAO{

	public SurveyAccessUsersDAO() {
		super(SurveyAccessUsers.class);
		// TODO Auto-generated constructor stub
	}

	public Long checkForDuplicateRecords(Long userId,Long surveyId){
		Query query = getSession().createQuery("select count(model.surveyAccessUsersId) from SurveyAccessUsers model where model.user.userId = :userId and model.survey.surveyId = :surveyId");
		query.setParameter("userId", userId);
		query.setParameter("surveyId", surveyId);
		return (Long)query.uniqueResult();
	}
}
