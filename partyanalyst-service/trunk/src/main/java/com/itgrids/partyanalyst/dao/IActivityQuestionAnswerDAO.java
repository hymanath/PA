package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.SearchAttributeVO;
import com.itgrids.partyanalyst.model.ActivityQuestionAnswer;

public interface IActivityQuestionAnswerDAO extends GenericDao<ActivityQuestionAnswer, Long>{

	public List<Object[]> getActivityQuestionnairesCountsByLocation(SearchAttributeVO searchAttributeVO,Long stateId);
	public List<Object[]> getActivityQuestionnairesAttributeCountsByLocation(SearchAttributeVO searchAttributeVO,Long optionId,Long stateId);
	public List<Object[]> getActivityQuestionnairesCountsByDayWise(SearchAttributeVO searchAttributeVO,Long stateId);
	public List<Object[]> getActivityQuestionnairesAttributeCountsByDayWise(SearchAttributeVO searchAttributeVO,Long optionId,Long stateId);
	public List<Object[]> getActivityQuestionAnswerCountReasonWise(List<Long> questionIdsList);
	public List<Object[]> getActivityQuestionAnswerCountByQuestionAndLocation(Long questionId,String searchType,Long searchValue,Long activityScopeId);
	public List<Object[]> getTheLocationWiseData(Long questionId,Long activityScopeId,Long constituencyId);
	public List<Object[]> getOptionsCountByScopId(Long activityScopeId,Long reportType,Long questionId);
	public List<Long> getActivityQuestionnaireAnswerIdsList(Long activityScopeId,Long locationValue,Long constituencyId);
	public int updateActivityQuestionnaireAnswerIdsList(Long activityScopeId,Long locationValue,Long constituencyId);
	public List<Object[]> getOptionsCountByScopIdForComments(Long activityScopeId,Long reportType,Long questionId);
	public List<Object[]> getCommentDetails(Long activityScopeId,Long reportType,Long questnId, Long levelId, Long reportTypeId,String locationLevelStr);
	public List<Object[]> getLocationWiseResponseDetails(SearchAttributeVO searchVO,List<Long> activityScopeQuestionIdsLsit);
	public List<Object[]> getActivityLocationInfoByScope(Long activityLevel,Long activityScope,Long questionId,String optionType);
	public List<Object[]> getActivityLocationInfoByScope(SearchAttributeVO searchVO,String optionType);
	public List<Object[]> getActivityQuesAndOptionsByDayWise(Long day);
	public List<Object[]> getQuestionsPerc(Long activityId,Long activityScopeId);
	//public List<Object[]> getQuestionsPerc(Long activityId,Long activityScopeId);
	public List<Object[]> getQuestionAnswerDetails(Long activityLocationInfoId);
	public List<Object[]> getCountanswereddetails(Long activityScopeId, Long scopeId,String locationType);
	public List<Object[]> getDayWiseQuestionAnswerDetails(Long activityLocationInfoId,Date activityDate);
	public List<Long> checkIsAnswerIsSubmitted(Long activityLocationInfoId);
	public Long updateAnswerDlts(Long activityLocationInfoId,Long activityQuestionnaireId,Long activityOptionId);
}
