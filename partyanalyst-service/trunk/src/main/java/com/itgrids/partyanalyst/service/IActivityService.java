package com.itgrids.partyanalyst.service;

import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.ActivityAttendanceInfoVO;
import com.itgrids.partyanalyst.dto.ActivityDetailsVO;
import com.itgrids.partyanalyst.dto.ActivityDocumentVO;
import com.itgrids.partyanalyst.dto.ActivityLoginVO;
import com.itgrids.partyanalyst.dto.ActivityResponseVO;
import com.itgrids.partyanalyst.dto.ActivityVO;
import com.itgrids.partyanalyst.dto.ActivityWSVO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.EventFileUploadVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.OptionsCountVo;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SearchAttributeVO;
import com.itgrids.partyanalyst.dto.TabDetailsVO;
import com.itgrids.partyanalyst.dto.TdpCadreWSVO;
import com.itgrids.partyanalyst.model.UserAddress;

public interface IActivityService {
	
	public Map<Long,ActivityVO> getAttributeListByQuestionnaireList(List<Long> questionnairesList);
	public List<ActivityVO> getActivityDayWiseCountsByLocation(SearchAttributeVO searchAttributeVO,Long stateId);
	public ActivityVO getActivityDetailsBySearchCriteria(SearchAttributeVO searchAttributeVO,Long stateId,boolean isExecuted);
	public List<IdNameVO> getActivityLevelsList();
	public BasicVO getActivityTypeList();

	public ResultStatus eventsUploadForm(EventFileUploadVO eventFileUploadVO);
	
	public ResultStatus deleteEventUploadFilebyActivityInfoDocId(String acitivityInfoDocId);
	public ResultStatus saveActivityQuestionnaireDetails(final ActivityVO finalvo);
//	public ActivityVO getQuestionnaireForScope(Long scopeId,Long requiredAttributeId,Long questionId,Long optionId);

	public ResultStatus uploadActivityDocuments(Long activityId,String sourcePath,ActivityDocumentVO activityLocation);
	public List<IdNameVO> getActivityLeadersDetailsByActivityScope(List<Long> activityScopeIds,boolean showCounts);
	public List<IdNameVO> getTeamMembersByLeaderAndActivityScope(List<Long> teamLeaderIds,List<Long> activityScopeIds);
	
	public UserAddress saveUserAddressByLevelIdAndLevelValue(Long levelId,Long levelValue);
	
	public List<BasicVO> getActivityDocumentsImages(Long levelId,Long levelValue,Long day,Integer startIndex,Integer maxIndex,Long activityScopeId,String activityDate,String tempVar);
	public List<String> getActivityDates(Long activityScopeId);
	public Long savingTabDetails(final TabDetailsVO tabDetailsVO);
	public ResultStatus savingAttendenceQuestionAnswer(final Long activityQuestionAnswerId,final Long attendenceId);
	
	public ActivityLoginVO checkActivityTabUserLogin(String userName,String password);
	public Long getConstituencyId(Long locationLevel, Long locationValue);
	public TdpCadreWSVO getCadreDetailsByVoterIdorMemberShipNo(String memberShipNo,String voterCardNo);
	public ActivityWSVO getUserActivityDetailsByUserId(Long userId);
	public List<BasicVO> getRequiredAttributesListForScope(Long scopeId);
	public BasicVO getActivityLocationWiseDetailsByScopeId(Long scopeId);
	public List<String> getActivityScopeDates(Long activityScopeId);
	public List<ActivityAttendanceInfoVO> getActivityDayWiseCountsByLocationForAttendance(SearchAttributeVO searchAttributeVO,Long stateId);
	public ActivityVO getActivityDetailsForCadre(Long cadreId);
	public ActivityVO getActivityDetailsByActivityLevelIdAndCadreId(Long activityLevelId,Long tdpCadreId,Long locationId,Long boothId,Long panchayatId,Long mandalId,Long constituencyId,Long districtId,Long stateId);
	public List<IdNameVO> getAccessValuesOfUserId(Long userId,String type);
	//public List<IdNameVO> getActivityStatusDetailsByScopeId(Long activityScopeId);
	public List<IdNameVO> getActivityStatusDetailsByScopeIdAndLocationValue(Long activityScopeId,Long constituencyId,String mandalId,String villageId,Long activityQuestionId);
	public List<IdNameVO> getQuestions(Long scopeId);
	public List<IdNameVO> getOptionsForQuestion(Long questionId);
	public List<IdNameVO> getQuestionsForReportType(Long activityScopeId);
	public List<OptionsCountVo> getOptionDetailsForQuestion(Long activityScopeId,Long reportType, Long qstnId);
	public List<IdNameVO> getActivityStatusDetailsByScopeId(Long activityScopeId,Long questionsId);
	public List<OptionsCountVo> getCommentDetails(Long activityScopeId,Long reportType, Long qstnId, Long levelId, Long reportTypeId);
	public ActivityResponseVO getActivityQuestionnnaireWiseReport(SearchAttributeVO searchVO);
	public List<ActivityResponseVO> getActivityLocationInfoDetailsByActivityScope(Long activityLevel,Long activityScope,List<Long> questionIds);
	public List<IdNameVO> getAllActivities();
	public List<IdNameVO> getAllActivityLevelsByActivity(Long activityId);
	public ActivityVO getActivityQuestionaryOptionsByActivityDate(String activityDate,Long day,Long activityScopeId);
	public ActivityVO getQuestionnaireForScope(Long scopeId,Long requiredAttributeId,Long questionId,Long optionId,List<Long> selectedDay);
	public ActivityVO getCanditeActivtyAttendanceLocationsDtls(Long cadreId,Long activityLevelId,Long activityScopeId,String statusCode);
	public List<ActivityResponseVO> getquestinaireForRetrieving(Long day);
	public List<ActivityResponseVO> getCallStatus();
	public List<ActivityResponseVO> getCallSuportType();
	public List<ActivityResponseVO> getAllCallingPurpose();
	public ResultStatus saveCallerFeedBackDetailsForCadre(Long callPurposeId,Long callStatusId,Long callSupportId,String description,Long cadreId,Long calledBy);
	public ActivityVO getActivitiesQuesDetails(Long activityId,Long activityScopeId,String startDateStr,String endDateStr);
	public List<ActivityVO> getDistrictNamesByScopeId(Long activityScopeId,Long activityMemberId,Long stateId,Long userTypeId,String startDate, String endDate);
	public List<ActivityVO> getConstByDistrictId(Long activityScopeId,Long districtId,String fromDate, String toDate);
	public List<ActivityVO> getMandOrMuncByconstituencyId(Long activityScopeId,Long constituencyId,String fromDate,String toDate);
	public List<ActivityVO> getPanchayatOrWardsByMandalOrMuncId(Long activityScopeId,Long mandalOrMuncId,String fromDate,String toDate);
	public List<ActivityDetailsVO> getActivityDetailsBasedOnLocation(String locationType,List<Long> locationValues,Long activityScopeId,Long constituencyId);
	public ResultStatus updateActivityInfo(ActivityDetailsVO inputVO,String locationType, Long activityScopeId,Long locationValue,String conductedDate,String updateStatus);
	public List<ActivityDetailsVO> getActivityQuestionOptionDtls(Long activityScopeId,Long activiyLocationInfoId);
	public ResultStatus saveActivityAnswerDetails(ActivityDetailsVO inputVO);
	public ResultStatus uploadDocumentImage(ActivityDetailsVO inputVO,List<String> docuemntBase64List);
}
