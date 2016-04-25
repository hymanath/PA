package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ActivityStatusQuestionnaire;

public interface IActivityStatusQuestionnaireDAO extends GenericDao<ActivityStatusQuestionnaire, Long> {
	public List<Long> getActivityStatusQuestionsListByActivityScopeId(Long activityScopeId);
}
