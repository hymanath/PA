package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ActivityQuestionnaireOption;

public interface IActivityQuestionnaireOptionDAO extends GenericDao<ActivityQuestionnaireOption, Long>{
	//public List<Object[]> getQuestionnaireForScope(Long scopeId,Long questionId,Long optionId);
	public List<Object[]> getQuestionnaireOfScope(List<Long> scopeIds);
	
	public List<Object[]> getQuestionnaireForScopeAndRespondentTypeIds(Long scopeId,Long requiredAttributeId,List<Long> questionnaireIds);
	public List<Object[]> getOptionsForQuestions(Long questionId);
	public List<Object[]> getOptionsByQuesttionareIds(Long questionnareId);
	
	public List<Object[]> getTextboxQuestionaireForScope(Long scopeId,Long questionId,Long optionId,List<Long> questionnaireIds);
	public List<Object[]> getTextBoxQuestionnaireForScopeAndRespondentTypeIds(Long scopeId,Long requiredAttributeId,List<Long> questionnaireIds);
	public List<Object[]> getQuestionnaireOptionsDetailsOfScope(List<Long> scopeIds);
	public List<Object[]> getActivityQuestionOptions(List<Long> activityQuestionnaireIds);
	public List<Object[]> getQuestionnaireForScope(Long scopeId,Long questionId,Long optionId,List<Long> questionnaireIds);
	public List<Object[]> getQuesAndOptionsByScopeIds(List<Long> scopeIdsList);
}
