package com.itgrids.partyanalyst.dao.impl;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AcceptanceStatus;
import com.itgrids.partyanalyst.model.ActivityDaywiseQuestionnaire;

public interface IActivityDaywiseQuestionnaireDAO extends GenericDao<ActivityDaywiseQuestionnaire, Long> {
	public List<Long> getActivityQuestionIds(Date activityDate,Long day,Long activityScopeId);
	public List<Long> getSelectedDayWiseQuestionWithOptions(Long scopeId,List<Long> seletedDays);

}
