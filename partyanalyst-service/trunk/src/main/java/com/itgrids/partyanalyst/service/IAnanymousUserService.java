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
import com.itgrids.partyanalyst.dto.UserSettingsVO;

public interface IAnanymousUserService {

	public Boolean saveAnonymousUserDetails(final RegistrationVO userDetails, final Boolean isUpdate);
	
	public RegistrationVO checkAnonymousUserLogin(String anonymousUserId,String password);
	
	public ResultStatus checkForUserNameAvalilability(String userName);
	
	public ResultStatus checkForUserNameAvailabilityForFreashUser(String userName);
		
	public DataTransferVO getAllRegisteredAnonymousUserBasedOnLocation(List<Long> locationIds,String locationType,Long retrivalCount,Long loginId,String status,Long startIndex, String nameString);
	
	public ResultStatus saveCommunicationDataBetweenUsers(final List<Long> senderId,final List<Long> recipeintId,final String messageType,final String subject,final String senderName);
	
	public DataTransferVO getDataForAUserProfile(List<Long> userId,String informationStatus);
	
	public DataTransferVO getAllUsersAfterAcceptingRequest(List<Long> locationIds,String locationType,Long retrivalCount,Long loginId,
			List<Long> senderId,final List<Long> recipeintId,final String messageType,final String subject,String senderName);
	
	public NavigationVO getAllMessageTypes();
	
	public String displayMessageAndUpdateUnread(Long customMessageId);
	
	public List<SelectOptionVO> findAllProfileOptsAvailableInDB();
	
	public RegistrationVO getDetailsOfUserByUserId(Long userId);
	
	public UserCommentsInfoVO getAllPostedReasonsByUserId(Long userId, Integer startIndex, Integer results, String order, String columnName, String reasonType);
	
	public ProblemBeanVO getAllPostedProblemsByUserId(Long userId, Integer startIndex, Integer results, String order, String columnName, String reasonType);
	
	public CandidateCommentsVO getAllPostedReasonsCountByUserId(Long userId);
	
	public ProblemDetailsVO getPostedProblemsCount(Long userId);
	
	public DataTransferVO getAllPeopleConnectedPeopleForUser(List<Long> userId);
	
	public ResultStatus saveUserProfileImageName(Long userId,String imageName);
	
	public String getUserProfileImageByUserId(Long userId);
	
	public RegistrationVO getUserDetailsToRecoverPassword(String userName);
	
	public Integer saveUserDetailsToChangeUserNameToEmail(Long userId,String userName);
	
	public String getPassword(String password);
	
	//public String changeUserPassword(String crntPassword,String newPassword,String userName);
	
	public  ResultStatus saveEmailForAUser(String userName,String email);
	
	public ResultStatus changeForUserNameAsEmail(String userName);
	
	public  ResultStatus saveEmailAndSetAsUserName(String userName,final String email);
	
	public void saveMailContacts(Long userId ,List<String> mailsList);
	
	public DataTransferVO getAllRegisteredAnonymousUserBasedOnLocationAndStatus(List<Long> locationIds,String locationType,Long retrivalCount,Long loginId,String status,Long startIndex,String nameString);
	
	public Long getUserConstituencyId(Long userId);
	
	public String changeUserPassword(String crntpassword,String newpassword,Long registrationId,String userName);
	
	public DataTransferVO getAllMessagesForLoggedUser(List<Long> userId,String messageType);
	
	public DataTransferVO getAllSentMessagesForLoggedUser(List<Long> userId,String messageType);

	public List<RegistrationVO> getFriendsListForUserProfile(Long userId);
	
	public UserSettingsVO getTotalSettingsOptionsOfAnUser(Long userId);
	
	public String updateUserSettingsDetailsAction(Long selectedId , Long userId);
	
	public List<UserSettingsVO> getUserFavouriteLinksAction(Long userId);
	
	public List<UserSettingsVO> getUserFavAllLinksAction(Long userId);
	
	public String removeFavouriteLink(Long linkId);
	
	public String saveUserFavouriteLink(Long userId , String link,String pageTitle, String queryString , String environment);
	
	public ResultStatus deleteMessageFromInbox(Long userId,Long senderId,String type,Long customMessageId,String btnName);
	
	public DataTransferVO getAllConnectedUsersBasedonLocationType(List<Long> locationIds,String locationType,Long retrivalCount,Long loginId,String status,Long startIndex,String nameString);
	
}	
