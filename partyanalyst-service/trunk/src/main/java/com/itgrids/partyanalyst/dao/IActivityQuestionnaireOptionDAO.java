package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ActivityQuestionnaireOption;

public interface IActivityQuestionnaireOptionDAO extends GenericDao<ActivityQuestionnaireOption, Long>{
	public List<Object[]> getQuestionnaireForScope(Long scopeId);
	public List<Object[]> getQuestionnaireOfScope(List<Long> scopeIds);
	
	public List<Object[]> getQuestionnaireForScopeAndRespondentTypeIds(Long scopeId,Long requiredAttributeId);
}
