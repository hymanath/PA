package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ActivityQuestionnaire;

public interface IActivityQuestionnaireDAO extends GenericDao<ActivityQuestionnaire, Long>{

	public List<Long> getQuestionnaireIdsListByScopeId(Long scopeId);
	public List<Object[]> getQuestionnareOptionsDetails(Long questionId);
	public List<Object[]> getQuestionnareForScopeId(Long scopeId);
	public List<Long> getActivityQuestionnaireIdByQuestionId(Long scopeId,Long activityQuestionId);
	public List<Object[]> getQuestionIdsByScopeId(Long scopeId);
}
