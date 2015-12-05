package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ActivityAttributeQuestionnaireInfo;

public interface IActivityAttributeQuestionnaireInfoDAO extends GenericDao<ActivityAttributeQuestionnaireInfo, Long>{

	public List<Object[]> getAttributeListByQuestionnaires(List<Long> questionnairesList);
}
