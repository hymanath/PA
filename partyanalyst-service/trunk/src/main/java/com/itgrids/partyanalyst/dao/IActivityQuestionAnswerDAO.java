package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.SearchAttributeVO;
import com.itgrids.partyanalyst.model.ActivityQuestionAnswer;

public interface IActivityQuestionAnswerDAO extends GenericDao<ActivityQuestionAnswer, Long>{

	public List<Object[]> getActivityQuestionnairesCountsByLocation(SearchAttributeVO searchAttributeVO,Long stateId);
	public List<Object[]> getActivityQuestionnairesAttributeCountsByLocation(SearchAttributeVO searchAttributeVO,Long optionId,Long stateId);
	public List<Object[]> getActivityQuestionnairesCountsByDayWise(SearchAttributeVO searchAttributeVO,Long stateId);
	public List<Object[]> getActivityQuestionnairesAttributeCountsByDayWise(SearchAttributeVO searchAttributeVO,Long optionId,Long stateId);
	public List<Object[]> getActivityQuestionAnswerCountReasonWise(Long questionId);
	public List<Object[]> getActivityQuestionAnswerCountByQuestionAndLocation(Long questionId,String searchType,Long searchValue,Long activityScopeId);
	public List<Object[]> getTheLocationWiseData(Long questionId,Long activityScopeId,Long constituencyId);
	public List<Object[]> getOptionsCountByScopId(Long activityScopeId,Long reportType,Long questionId);
	public List<Long> getActivityQuestionnaireAnswerIdsList(Long activityScopeId,Long locationValue,Long constituencyId);
	public int updateActivityQuestionnaireAnswerIdsList(Long activityScopeId,Long locationValue,Long constituencyId);
}
