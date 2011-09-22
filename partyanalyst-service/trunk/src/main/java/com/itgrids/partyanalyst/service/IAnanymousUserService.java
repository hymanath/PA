package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CandidateCommentsVO;
import com.itgrids.partyanalyst.dto.DataTransferVO;
import com.itgrids.partyanalyst.dto.NavigationVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.ProblemDetailsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.UserCommentsInfoVO;

public interface IAnanymousUserService {

	public Boolean saveAnonymousUserDetails(final RegistrationVO userDetails, final Boolean isUpdate);
	
	public RegistrationVO checkAnonymousUserLogin(String anonymousUserId,String password);
	
	public ResultStatus checkForUserNameAvalilability(String userName);
	
	public ResultStatus checkForUserNameAvailabilityForFreashUser(String userName);
		
	public DataTransferVO getAllRegisteredAnonymousUserBasedOnLocation(List<Long> locationIds,String locationType,Long retrivalCount,Long loginId,String status,Long startIndex, String nameString);
	
	public ResultStatus saveCommunicationDataBetweenUsers(final List<Long> senderId,final List<Long> recipeintId,final String messageType,final String subject);
	
	public DataTransferVO getDataForAUserProfile(List<Long> userId,String informationStatus);
	
	public DataTransferVO getAllUsersAfterAcceptingRequest(List<Long> locationIds,String locationType,Long retrivalCount,Long loginId,
			List<Long> senderId,final List<Long> recipeintId,final String messageType,final String subject);
	
	public NavigationVO getAllMessageTypes();
	
	public String displayMessageAndUpdateUnread(Long customMessageId);
	
	public List<SelectOptionVO> findAllProfileOptsAvailableInDB();
	
	public RegistrationVO getDetailsOfUserByUserId(Long registrationId);
	
	public UserCommentsInfoVO getAllPostedReasonsByUserId(Long registrationId, Integer startIndex, Integer results, String order, String columnName, String reasonType);
	
	public ProblemBeanVO getAllPostedProblemsByUserId(Long registrationId, Integer startIndex, Integer results, String order, String columnName, String reasonType);
	
	public CandidateCommentsVO getAllPostedReasonsCountByUserId(Long registrationId);
	
	public ProblemDetailsVO getPostedProblemsCount(Long registrationId);
	
	public DataTransferVO getAllPeopleConnectedPeopleForUser(List<Long> userId);
	
	public ResultStatus saveUserProfileImageName(Long userId,String imageName);
	
	public String getUserProfileImageByUserId(Long userId);
	
	
	public RegistrationVO getUserDetailsToRecoverPassword(String userName);
	
	public Integer saveUserDetailsToChangeUserNameToEmail(Long userId,String userName);
	
	public String getPassword(String password);
	
}	
