package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CandidateCommentsVO;
import com.itgrids.partyanalyst.dto.DataTransferVO;
import com.itgrids.partyanalyst.dto.NavigationVO;
import com.itgrids.partyanalyst.dto.ProblemDetailsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface IAnanymousUserService {

	public Boolean saveAnonymousUserDetails(final RegistrationVO userDetails, final Boolean isUpdate);
	
	public RegistrationVO checkAnonymousUserLogin(String anonymousUserId,String password);
	
	public ResultStatus checkForUserNameAvalilability(String userName);
	
	public DataTransferVO getAllRegisteredAnonymousUserBasedOnLocation(List<Long> locationIds,String locationType,Long retrivalCount,Long loginId,String status);
	
	public ResultStatus saveCommunicationDataBetweenUsers(final List<Long> senderId,final List<Long> recipeintId,final String messageType,final String subject);
	
	public DataTransferVO getDataForAUserProfile(List<Long> userId,String informationStatus);
	
	public DataTransferVO getAllUsersAfterAcceptingRequest(List<Long> locationIds,String locationType,Long retrivalCount,Long loginId,
			List<Long> senderId,final List<Long> recipeintId,final String messageType,final String subject);
	
	public NavigationVO getAllMessageTypes();
	
	public String displayMessageAndUpdateUnread(Long customMessageId);
	
	public List<SelectOptionVO> findAllProfileOptsAvailableInDB();
	
	public RegistrationVO getDetailsOfUserByUserId(Long registrationId);
	
	public List<CandidateCommentsVO> getAllPostedReasonsByUserId(Long registrationId);
	
	public List<ProblemDetailsVO> getAllPostedProblemsByUserId(Long registrationId);
	
}
