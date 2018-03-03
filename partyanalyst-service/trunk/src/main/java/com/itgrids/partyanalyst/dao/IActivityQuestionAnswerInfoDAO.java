package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ActivityQuestionAnswerInfo;

public interface IActivityQuestionAnswerInfoDAO extends GenericDao<ActivityQuestionAnswerInfo, Long> {

	List<Object[]> getInchargeMLAAttendCount(Long activityScopeId,Long locationScopeId, Date startDate, Date endDate);

}
