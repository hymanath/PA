package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IActivityAttributeQuestionnaireInfoDAO;
import com.itgrids.partyanalyst.model.ActivityAttributeQuestionnaireInfo;

public class ActivityAttributeQuestionnaireInfoDAO extends GenericDaoHibernate<ActivityAttributeQuestionnaireInfo, Long> implements IActivityAttributeQuestionnaireInfoDAO{

	public ActivityAttributeQuestionnaireInfoDAO() {
		super(ActivityAttributeQuestionnaireInfo.class);
	}
	
	public List<Object[]> getAttributeListByQuestionnaires(List<Long> questionnairesList){
		
		Query query = getSession().createQuery(" select model.activityQuestionnaire.activityQuestionnaireId,model.activityAttribute.activityAttributeId, " +
							" model.activityAttribute.attributeName from ActivityAttributeQuestionnaireInfo model " +
							" where model.activityQuestionnaire.activityQuestionnaireId in (:questionnairesList) and model.isDeleted = 'false' ");
		
		query.setParameterList("questionnairesList", questionnairesList);
		
		return query.list();
	}
}
