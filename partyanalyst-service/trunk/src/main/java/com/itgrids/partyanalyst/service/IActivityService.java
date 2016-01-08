package com.itgrids.partyanalyst.service;

import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.ActivityDocumentVO;
import com.itgrids.partyanalyst.dto.ActivityVO;
import com.itgrids.partyanalyst.dto.ActivityWSVO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.EventFileUploadVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SearchAttributeVO;
import com.itgrids.partyanalyst.dto.TabDetailsVO;
import com.itgrids.partyanalyst.model.UserAddress;

public interface IActivityService {
	
	public Map<Long,ActivityVO> getAttributeListByQuestionnaireList(List<Long> questionnairesList);
	public List<ActivityVO> getActivityDayWiseCountsByLocation(SearchAttributeVO searchAttributeVO);
	public ActivityVO getActivityDetailsBySearchCriteria(SearchAttributeVO searchAttributeVO);
	public List<IdNameVO> getActivityLevelsList();
	public BasicVO getActivityTypeList();

	public ResultStatus eventsUploadForm(EventFileUploadVO eventFileUploadVO);
	
	public ResultStatus deleteEventUploadFilebyActivityInfoDocId(String acitivityInfoDocId);
	public ResultStatus saveActivityQuestionnaireDetails(final ActivityVO finalvo);
	public ActivityVO getQuestionnaireForScope(Long scopeId);

	public ResultStatus uploadActivityDocuments(Long activityId,String sourcePath,ActivityDocumentVO activityLocation);
	public List<IdNameVO> getActivityLeadersDetailsByActivityScope(List<Long> activityScopeIds,boolean showCounts);
	public List<IdNameVO> getTeamMembersByLeaderAndActivityScope(List<Long> teamLeaderIds,List<Long> activityScopeIds);
	
	public UserAddress saveUserAddressByLevelIdAndLevelValue(Long levelId,Long levelValue);
	
	public List<BasicVO> getActivityDocumentsImages(Long levelId,Long levelValue,Long day,Integer startIndex,Integer maxIndex,Long activityScopeId,String activityDate);
	public ActivityWSVO getUserActivityDetailsByUserId(String username, String password);
	public Long savingTabDetails(final TabDetailsVO tabDetailsVO);
	
}
