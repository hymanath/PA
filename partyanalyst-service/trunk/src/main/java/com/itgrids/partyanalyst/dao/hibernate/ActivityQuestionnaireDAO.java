package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IActivityQuestionnaireDAO;
import com.itgrids.partyanalyst.model.ActivityQuestionnaire;

public class ActivityQuestionnaireDAO extends GenericDaoHibernate<ActivityQuestionnaire, Long> implements IActivityQuestionnaireDAO{

	public ActivityQuestionnaireDAO() {
		super(ActivityQuestionnaire.class);
		
	}
	
	public List<Long> getQuestionnaireIdsListByScopeId(Long scopeId){
		
		Query query = getSession().createQuery(" select model.activityQuestionnaireId from ActivityQuestionnaire model " +
							" where model.activityScope.activityScopeId = :scopeId and model.isDeleted = 'N' ");
		
		query.setParameter("scopeId", scopeId);
		
		return query.list();
	}

}
