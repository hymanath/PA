package com.itgrids.partyanalyst.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.ICommentCategoryCandidateDAO;
import com.itgrids.partyanalyst.dao.ICommentDataCategoryDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IContentDAO;
import com.itgrids.partyanalyst.dao.ICustomMessageDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IFavoriteLinkPageDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IMessageTypeDAO;
import com.itgrids.partyanalyst.dao.IProblemHistoryDAO;
import com.itgrids.partyanalyst.dao.IProfileOptsDAO;
import com.itgrids.partyanalyst.dao.IRoleDAO;
import com.itgrids.partyanalyst.dao.ISettingsOptionDAO;
import com.itgrids.partyanalyst.dao.ISpecialPageDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserConnectedtoDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IUserFavoriteLinksDAO;
import com.itgrids.partyanalyst.dao.IUserPrivacySettingsDAO;
import com.itgrids.partyanalyst.dao.IUserProblemDAO;
import com.itgrids.partyanalyst.dao.IUserProfileOptsDAO;
import com.itgrids.partyanalyst.dao.IUserReferralEmailsDAO;
import com.itgrids.partyanalyst.dao.IUserRolesDAO;
import com.itgrids.partyanalyst.dto.CandidateCommentsVO;
import com.itgrids.partyanalyst.dto.CandidateVO;
import com.itgrids.partyanalyst.dto.CompleteProblemDetailsVO;
import com.itgrids.partyanalyst.dto.DataTransferVO;
import com.itgrids.partyanalyst.dto.EmailDetailsVO;
import com.itgrids.partyanalyst.dto.NavigationVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.ProblemDetailsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.UserCommentsInfoVO;
import com.itgrids.partyanalyst.dto.UserSettingsVO;
import com.itgrids.partyanalyst.model.CommentData;
import com.itgrids.partyanalyst.model.CustomMessage;
import com.itgrids.partyanalyst.model.FavoriteLinkPage;
import com.itgrids.partyanalyst.model.MessageType;
import com.itgrids.partyanalyst.model.ProfileOpts;
import com.itgrids.partyanalyst.model.Role;
import com.itgrids.partyanalyst.model.SettingsOption;
import com.itgrids.partyanalyst.model.User;
import com.itgrids.partyanalyst.model.UserConnectedto;
import com.itgrids.partyanalyst.model.UserFavoriteLinks;
import com.itgrids.partyanalyst.model.UserPrivacySettings;
import com.itgrids.partyanalyst.model.UserProfileOpts;
import com.itgrids.partyanalyst.model.UserReferralEmails;
import com.itgrids.partyanalyst.model.UserRoles;
import com.itgrids.partyanalyst.service.IAnanymousUserService;
import com.itgrids.partyanalyst.service.IDateService;
import com.itgrids.partyanalyst.service.IMailsSendingService;
import com.itgrids.partyanalyst.service.IProblemManagementService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class AnanymousUserService implements IAnanymousUserService {

	public static Logger log = Logger.getLogger(AnanymousUserService.class); 
	//Other Templates
	private TransactionTemplate transactionTemplate = null;
	
	//DAO's
	private IStateDAO stateDAO;
	private IDistrictDAO districtDAO;
	private IConstituencyDAO constituencyDAO;
	private ICustomMessageDAO customMessageDAO;
	private IMessageTypeDAO messageTypeDAO;
	private IUserConnectedtoDAO userConnectedtoDAO;
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	private IStaticDataService staticDataService;
	private IProfileOptsDAO profileOptsDAO;
	private IUserProfileOptsDAO userProfileOptsDAO;
	private ICommentCategoryCandidateDAO commentCategoryCandidateDAO;
	private IProblemHistoryDAO problemHistoryDAO;
	private ICommentDataCategoryDAO commentDataCategoryDAO;
	private IHamletDAO hamletDAO;
	private ITehsilDAO tehsilDAO;
	private IBoothDAO boothDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private IMailsSendingService mailsSendingService;
	private DateUtilService dateUtilService = new DateUtilService();
	private IRoleDAO roleDAO;
	private IUserRolesDAO userRolesDAO;
	private IUserReferralEmailsDAO userReferralEmailsDAO;
	private IUserDAO userDAO;
	private IUserProblemDAO userProblemDAO;
	private IProblemManagementService problemManagementService;
	
	private IUserFavoriteLinksDAO userFavoriteLinksDAO;
	private IFavoriteLinkPageDAO favoriteLinkPageDAO;
	private ISpecialPageDAO specialPageDAO;
	private SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN_VALUE);
	
	
	public ISpecialPageDAO getSpecialPageDAO() {
		return specialPageDAO;
	}

	public void setSpecialPageDAO(ISpecialPageDAO specialPageDAO) {
		this.specialPageDAO = specialPageDAO;
	}

	private ISettingsOptionDAO settingsOptionDAO;
	private IContentDAO contentDAO;	
	private IUserPrivacySettingsDAO userPrivacySettingsDAO;
	
	public IFavoriteLinkPageDAO getFavoriteLinkPageDAO() {
		return favoriteLinkPageDAO;
	}

	public void setFavoriteLinkPageDAO(IFavoriteLinkPageDAO favoriteLinkPageDAO) {
		this.favoriteLinkPageDAO = favoriteLinkPageDAO;
	}

	
	public ISettingsOptionDAO getSettingsOptionDAO() {
		return settingsOptionDAO;
	}

	public void setSettingsOptionDAO(ISettingsOptionDAO settingsOptionDAO) {
		this.settingsOptionDAO = settingsOptionDAO;
	}

	public IContentDAO getContentDAO() {
		return contentDAO;
	}

	public void setContentDAO(IContentDAO contentDAO) {
		this.contentDAO = contentDAO;
	}

	public IUserPrivacySettingsDAO getUserPrivacySettingsDAO() {
		return userPrivacySettingsDAO;
	}

	public void setUserPrivacySettingsDAO(
			IUserPrivacySettingsDAO userPrivacySettingsDAO) {
		this.userPrivacySettingsDAO = userPrivacySettingsDAO;
	}

	public IUserProblemDAO getUserProblemDAO() {
		return userProblemDAO;
	}

	public void setUserProblemDAO(IUserProblemDAO userProblemDAO) {
		this.userProblemDAO = userProblemDAO;
	}

	public IUserReferralEmailsDAO getUserReferralEmailsDAO() {
		return userReferralEmailsDAO;
	}

	public void setUserReferralEmailsDAO(
			IUserReferralEmailsDAO userReferralEmailsDAO) {
		this.userReferralEmailsDAO = userReferralEmailsDAO;
	}

	public IMailsSendingService getMailsSendingService() {
		return mailsSendingService;
	}

	public void setMailsSendingService(IMailsSendingService mailsSendingService) {
		this.mailsSendingService = mailsSendingService;
	}

	public IHamletDAO getHamletDAO() {
		return hamletDAO;
	}

	public void setHamletDAO(IHamletDAO hamletDAO) {
		this.hamletDAO = hamletDAO;
	}

	public ICommentDataCategoryDAO getCommentDataCategoryDAO() {
		return commentDataCategoryDAO;
	}

	public void setCommentDataCategoryDAO(
			ICommentDataCategoryDAO commentDataCategoryDAO) {
		this.commentDataCategoryDAO = commentDataCategoryDAO;
	}

	public IProblemHistoryDAO getProblemHistoryDAO() {
		return problemHistoryDAO;
	}

	public void setProblemHistoryDAO(IProblemHistoryDAO problemHistoryDAO) {
		this.problemHistoryDAO = problemHistoryDAO;
	}

	public ICommentCategoryCandidateDAO getCommentCategoryCandidateDAO() {
		return commentCategoryCandidateDAO;
	}

	public void setCommentCategoryCandidateDAO(
			ICommentCategoryCandidateDAO commentCategoryCandidateDAO) {
		this.commentCategoryCandidateDAO = commentCategoryCandidateDAO;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public IDelimitationConstituencyAssemblyDetailsDAO getDelimitationConstituencyAssemblyDetailsDAO() {
		return delimitationConstituencyAssemblyDetailsDAO;
	}

	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}

	//Service
	private IDateService dateService;
	
	public IUserConnectedtoDAO getUserConnectedtoDAO() {
		return userConnectedtoDAO;
	}

	public void setUserConnectedtoDAO(IUserConnectedtoDAO userConnectedtoDAO) {
		this.userConnectedtoDAO = userConnectedtoDAO;
	}

	public IMessageTypeDAO getMessageTypeDAO() {
		return messageTypeDAO;
	}

	public void setMessageTypeDAO(IMessageTypeDAO messageTypeDAO) {
		this.messageTypeDAO = messageTypeDAO;
	}

	public IDateService getDateService() {
		return dateService;
	}

	public void setDateService(IDateService dateService) {
		this.dateService = dateService;
	}
	
	public ICustomMessageDAO getCustomMessageDAO() {
		return customMessageDAO;
	}

	public void setCustomMessageDAO(ICustomMessageDAO customMessageDAO) {
		this.customMessageDAO = customMessageDAO;
	}

	public IStateDAO getStateDAO() {
		return stateDAO;
	}

	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	public IUserProfileOptsDAO getUserProfileOptsDAO() {
		return userProfileOptsDAO;
	}

	public void setUserProfileOptsDAO(IUserProfileOptsDAO userProfileOptsDAO) {
		this.userProfileOptsDAO = userProfileOptsDAO;
	}

	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}

	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}

	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}

	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public IProfileOptsDAO getProfileOptsDAO() {
		return profileOptsDAO;
	}

	public void setProfileOptsDAO(IProfileOptsDAO profileOptsDAO) {
		this.profileOptsDAO = profileOptsDAO;
	}

	public IRoleDAO getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(IRoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public IUserRolesDAO getUserRolesDAO() {
		return userRolesDAO;
	}

	public void setUserRolesDAO(IUserRolesDAO userRolesDAO) {
		this.userRolesDAO = userRolesDAO;
	}
		
	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public IProblemManagementService getProblemManagementService() {
		return problemManagementService;
	}

	public void setProblemManagementService(
			IProblemManagementService problemManagementService) {
		this.problemManagementService = problemManagementService;
	}
	

	public IUserFavoriteLinksDAO getUserFavoriteLinksDAO() {
		return userFavoriteLinksDAO;
	}

	public void setUserFavoriteLinksDAO(IUserFavoriteLinksDAO userFavoriteLinksDAO) {
		this.userFavoriteLinksDAO = userFavoriteLinksDAO;
	}


public Boolean saveAnonymousUserDetails(final RegistrationVO userDetails, final Boolean isUpdate){
		
		User result = (User)transactionTemplate.execute(new TransactionCallback() {
			
			public Object doInTransaction(TransactionStatus status) {
				User user = null;
				ProfileOpts profileOpts = null;
				String imageName =null;
				
				if(!isUpdate)
					user = new User();
				else{
					user = userDAO.get(userDetails.getRegistrationID());
					userProfileOptsDAO.removeOptsOfExistingUser(userDetails.getRegistrationID());
				}
				
				try{
					
					if(!isUpdate)
					{
						String str = ((Long)System.currentTimeMillis()).toString();
						String pwd = str.substring(str.length()-7,str.length());
						userDetails.setPassword(getPassword(pwd));
						user.setUserName(userDetails.getEmail());
						user.setPassword(userDetails.getPassword());
						user.setRegisteredDate(dateUtilService.getCurrentDateAndTime());
						user.setEmail(userDetails.getEmail());
						user.setIsPwdChanged(IConstants.FALSE);
						
					}
					
					user.setFirstName(userDetails.getFirstName());
					user.setLastName(userDetails.getLastName());
					user.setGender(userDetails.getGender());
					
					
					SimpleDateFormat format = new SimpleDateFormat(IConstants.DATE_PATTERN);
					if(userDetails.getDateOfBirth()!= null && userDetails.getDateOfBirth().trim().length() > 0)
					{
						Date date = format.parse(userDetails.getDateOfBirth());
						user.setDateOfBirth(date);
					}
					
					user.setMobile(userDetails.getMobile());
					user.setAddress(userDetails.getAddress());
					user.setStateId(new Long(userDetails.getState()));
					user.setDistrictId(constituencyDAO.getDistrictIdByConstituencyId(new Long(userDetails.getConstituency())).get(0));
					user.setConstituencyId(new Long(userDetails.getConstituency()));
					user.setUpdatedDate(dateUtilService.getCurrentDateAndTime());
					
					if(isUpdate && userDetails.getUserProfilePic()!= null)
					{	
						user.setProfileImg(userDetails.getUserProfilePic());
					}
					user = userDAO.save(user);
					
					if(!isUpdate && userDetails.getUserProfilePic()!= null)
			        {
						String constiName[] = userDetails.getUserProfilePic().split("/");
						imageName = user.getUserId()+"."+constiName[1];
						ResultStatus imgStatus = saveUserProfileImageName(user.getUserId(),imageName);
					
					}
					
					//userDetails.setDistrict(user.getDistrict().getDistrictName());
					userDetails.setDistrictId(user.getDistrictId());
					
					if(userDetails.getProfileOpts() != null)
					for(Long optsId:userDetails.getProfileOpts())
					{
						profileOpts = profileOptsDAO.get(optsId);
						userProfileOptsDAO.save(new UserProfileOpts(user, profileOpts));
					}
					if(!isUpdate)
					{
						Role role = roleDAO.getRoleByRoleType(IConstants.FREE_USER);
						UserRoles userRoles = new UserRoles();
						userRoles.setRole(role);
						userRoles.setUser(user);
						userRolesDAO.save(userRoles);
					}
					
				}catch(Exception e){
					e.printStackTrace();
					status.setRollbackOnly();
				}
				return user;
			}
		});
		
		if(result != null && result.getUserId() != null)
			return true;
		
	 return false;
	}
	
	/**
	 * This method is used to check whether the username and password entered by anonymous user
	 * is valid or not.
	 * if the entered information is valid this method returns the corresponding details to the
	 * calling method.
	 * 
	 * @author Ravi Kiran.Y
	 * @param anonymousUserId
	 * @param password
	 * @return RegistrationVO
	 */
	public RegistrationVO checkAnonymousUserLogin(String anonymousUserId,String password){	
		RegistrationVO userDetails = null;
		List<User> detailsList = null;	
		ResultStatus resultStatus = new ResultStatus();		
		try{
			userDetails = new RegistrationVO();
			detailsList = userDAO.checkAnonymousUserLogin(anonymousUserId, password);
			if(detailsList.size()!=0){
				for(User resultIterator : detailsList){
					userDetails.setUserName(resultIterator.getUserName());
					userDetails.setPassword(resultIterator.getPassword());
					userDetails.setName(resultIterator.getFirstName());
					userDetails.setGender(resultIterator.getGender());
					userDetails.setDateOfBirth(resultIterator.getDateOfBirth().toString());
					userDetails.setEmail(resultIterator.getEmail());
					userDetails.setPhone(resultIterator.getPhone());
					userDetails.setMobile(resultIterator.getMobile());			
					userDetails.setAddress(resultIterator.getAddress());
					userDetails.setConstituency(resultIterator.getConstituency().getName());
					userDetails.setDistrict(resultIterator.getDistrict().getDistrictName());
					userDetails.setState(resultIterator.getState().getStateName());
					userDetails.setPincode(resultIterator.getPincode());
				}
			 resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			}
			resultStatus.setResultPartial(false);
			resultStatus.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
			userDetails.setResultStatus(resultStatus);			
		}catch(Exception e){
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setResultPartial(true);			
		}
		return userDetails;		
	}
	
	/**
	 * This method can be used to check whether the user name is available or not.
	 * @author Ravi Kiran.Y
	 * @param userName
	 * @return ResultStatus
	 */
	public ResultStatus checkForUserNameAvalilability(String userName){
		ResultStatus resultStatus = new ResultStatus();
		List<User> detailsList = null;
		List<User> detailsListForEmail = null;
		try{
			detailsList = userDAO.checkForUserNameAvailabiity(userName);
			detailsListForEmail=userDAO.checkForUserNameAvailabiityForEmail(userName);
			if(detailsList!=null && detailsList.size()!=0 && detailsListForEmail!=null && detailsListForEmail.size()!=0){
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);	
			}else{
				resultStatus.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
			}
			resultStatus.setResultPartial(false);
		}catch(Exception e){
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setResultPartial(true);
		}
		return resultStatus;
	}
	
	public ResultStatus checkForUserNameAvailabilityForFreashUser(String userName){
		ResultStatus resultStatus = new ResultStatus();
		List<User> detailsList = null;
		List<User> detailsListForEmail = null;
		try{
			detailsList = userDAO.checkForUserNameAvailabiity(userName);
			detailsListForEmail = userDAO.checkForUserNameAvailabiityForEmail(userName);
				
			
			if(detailsList.size()!=0 || detailsListForEmail.size()!=0){
				
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);	
			}else{
				resultStatus.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
			}
			resultStatus.setResultPartial(false);
		}catch(Exception e){
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setResultPartial(true);
		}
		return resultStatus;
	}
	
	/**
	 * This method can be used to retrive ananymous/free users of party analyst based on location wise.
	 * @author Ravi Kiran.Y
	 * @version 1.0,08/10/2010
	 * @param locationIds
	 * @param locationType
	 * @return DataTransferVO
	 */
	public DataTransferVO getAllRegisteredAnonymousUserBasedOnLocation(List<Long> locationIds,String locationType,Long retrivalCount,Long loginId,String status,Long startIndex,String nameString){
		ResultStatus resultStatus = new ResultStatus();
		DataTransferVO dataTransferVO = new DataTransferVO();
		List<CandidateVO> candidateDetails = new ArrayList<CandidateVO>();
		List<Object> result = new ArrayList<Object>();		
		List<Long> userIds = new ArrayList<Long>();
		userIds.add(loginId);
		
		try{
			
			if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY)){
				List list = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesForAListOfParliamentConstituency(locationIds);
				if(list!=null && list.size()!=0){
					for(int i=0; i<list.size(); i++){					
						locationIds.add((Long)list.get(i));
					}
				}
				
			}
			result = userDAO.getAllUsersInSelectedLocations(locationIds, locationType,retrivalCount,startIndex,nameString);	
			candidateDetails = setFriendsListForAUser(result,loginId,status,null);		
			dataTransferVO.setCandidateVO(candidateDetails);
			
			//dataTransferVO.setTotalResultsCount(getAllUsersCountInSelectedLocationsInFilterView(loginId, locationIds, locationType, status, nameString).toString());
						
			dataTransferVO.setConnectedPeopleCount(userConnectedtoDAO.getCountOfAllConnectedPeopleForUser(userIds));			
			resultStatus.setResultPartial(false);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			dataTransferVO.setResultStatus(resultStatus);	
		}catch(Exception e){
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setResultPartial(true);
			dataTransferVO.setResultStatus(resultStatus);	
		}
	return dataTransferVO;
	} 
	
	public DataTransferVO getAllRegisteredAnonymousUserBasedOnLocationAndStatus(List<Long> locationIds,String locationType,Long retrivalCount,Long loginId,String status,Long startIndex,String nameString){
		ResultStatus resultStatus = new ResultStatus();
		DataTransferVO dataTransferVO = new DataTransferVO();
		List<CandidateVO> candidateDetails = new ArrayList<CandidateVO>();
		List<Object> result = new ArrayList<Object>();		
		List<Long> userIds = new ArrayList<Long>();
		userIds.add(loginId);
		List<Long> connectedAndPendingUserIdsList=new ArrayList<Long>();
		
		
		try{			
			if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY)){
				List list = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesForAListOfParliamentConstituency(locationIds);
				if(list!=null && list.size()!=0){
					for(int i=0; i<list.size(); i++){					
						locationIds.add((Long)list.get(i));
					}
				}
				
			}
			
			if(status.equalsIgnoreCase(IConstants.ALL))
			{
				result = userDAO.getAllUsersInSelectedLocations(locationIds, locationType,retrivalCount,startIndex,nameString);
			}
			else if(status.equalsIgnoreCase(IConstants.CONNECTED))
			{
				result = userConnectedtoDAO.getConnectedUsersInSelectedLocations(loginId, locationIds, locationType,retrivalCount,startIndex,nameString);
			}
			else if(status.equalsIgnoreCase(IConstants.PENDING))
			{
				result = customMessageDAO.getPendingUsersInSelectedLocations(loginId, locationIds, locationType,retrivalCount,startIndex,nameString);
			}
			else if(status.equalsIgnoreCase(IConstants.NOTCONNECTED))
			{
				List<Long> connectedIdsList = userConnectedtoDAO.getConnectedUserIdsInSelectedLocations(loginId, locationIds, locationType);
				List<Long> pendingIdsList = customMessageDAO.getPendingUserIdsInSelectedLocations(loginId, locationIds, locationType);
				
				connectedAndPendingUserIdsList = new ArrayList<Long>();
				if(connectedIdsList != null && connectedIdsList.size() > 0)
					connectedAndPendingUserIdsList.addAll(connectedIdsList);
				if(pendingIdsList != null && pendingIdsList.size() > 0)
					connectedAndPendingUserIdsList.addAll(pendingIdsList);
				
				result = userDAO.getNotConnectedUsersInSelectedLocations(loginId, locationIds, locationType, connectedAndPendingUserIdsList, retrivalCount, startIndex, nameString);
			}
			
			if(status.equalsIgnoreCase(IConstants.NOTCONNECTED))
			candidateDetails = setFriendsListForAUser(result,loginId,status,"DISCONNECTED");
			
			else
				candidateDetails = setFriendsListForAUser(result,loginId,status,null);
			dataTransferVO.setCandidateVO(candidateDetails);
			
			dataTransferVO.setTotalResultsCount(getAllUsersCountInSelectedLocationsInFilterView(loginId, locationIds, locationType, status, nameString, connectedAndPendingUserIdsList).toString());
						
			dataTransferVO.setConnectedPeopleCount(userConnectedtoDAO.getCountOfAllConnectedPeopleForUser(userIds));			
			resultStatus.setResultPartial(false);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			dataTransferVO.setResultStatus(resultStatus);	
		}catch(Exception e){
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setResultPartial(true);
			dataTransferVO.setResultStatus(resultStatus);	
		}
	return dataTransferVO;
	} 
	
	
	public Long getAllUsersCountInSelectedLocationsInFilterView(Long userId,List<Long> locationIds,String locationType,String status,String nameString, List<Long> connectedAndPendingUserIdsList)
	{
		try{
			
			if(status.equalsIgnoreCase(IConstants.ALL))
			{
				return userDAO.getAllUsersCountInSelectedLocations(locationIds,locationType,nameString);
			}
			else if(status.equalsIgnoreCase(IConstants.CONNECTED))
			{
				return userConnectedtoDAO.getConnectedUsersCountForAUserInAFilterView(userId,locationIds,locationType,nameString);
			}
			else if(status.equalsIgnoreCase(IConstants.PENDING))
			{
				return customMessageDAO.getPendingUsersCountForAUserInAFilterView(userId,locationIds,locationType,nameString);
			}
			else if(status.equalsIgnoreCase(IConstants.NOTCONNECTED))
			{
				return userDAO.getNotConnectedUsersCountForAUserInAFilterView(userId,locationIds,locationType,nameString, connectedAndPendingUserIdsList);
			}
			
			return 0L;
			
		}catch (Exception e) {
			log.error("Exception Occured in getAllUsersCountInSelectedLocationsInFilterView");
			return 0L;
		}
	}
	/**
	 * This method is used to save the friend request between the users.
	 *
	 * @author Y.Ravi Kiran
	 * @version 1.0,14-10-10.
	 * 
	 * @param locationIds
	 * @param locationType
	 * @param retrivalCount
	 * @param loginId
	 * @param senderId
	 * @param recipeintId
	 * @param messageType
	 * @param subject
	 * @return
	 */
	public DataTransferVO getAllUsersAfterAcceptingRequest(List<Long> locationIds,String locationType,Long retrivalCount,Long loginId,
			List<Long> senderId,final List<Long> recipeintId,final String messageType,final String subject,String senderName){
		String userName = "";
		String email = "";
		
		ResultStatus resultStatus = new ResultStatus();
		ResultStatus resultStatusForSaving = new ResultStatus();
		DataTransferVO dataTransferVO = new DataTransferVO();
		List<CandidateVO> candidateDetails = new ArrayList<CandidateVO>();
		List<Object> result = new ArrayList<Object>();	
		Long startIndex = 0L;
		String nameString = "";
		List<Long> userIds = new ArrayList<Long>();
		try{
			resultStatusForSaving = saveCommunicationDataBetweenUsers(senderId,recipeintId,messageType,subject,senderName);
			dataTransferVO.setResultStatusForComments(resultStatusForSaving);
			userIds.add(loginId);
			result = userDAO.getAllUsersInSelectedLocations(locationIds, locationType,retrivalCount,startIndex,nameString);			
			candidateDetails = setFriendsListForAUser(result,loginId,IConstants.ALL,null);		
			dataTransferVO.setCandidateVO(candidateDetails);
			
			dataTransferVO.setTotalResultsCount((userDAO.getAllUsersCountInSelectedLocations(locationIds, locationType, "")).toString());
			dataTransferVO.setConnectedPeopleCount(userConnectedtoDAO.getCountOfAllConnectedPeopleForUser(userIds));
			
			for(Long recpntId : recipeintId)
			{
				List<Object[]> list = userDAO.getUserEmail(recpntId);
				if(list != null && list.size() > 0)
				{
					for(Object[] params : list)
					{
						userName = params[1].toString()+" "+params[2].toString();
						email = params[3].toString();
					}
						if(email != null && email.trim().length() > 0)
						{
							EmailDetailsVO emailDetailsVO = new EmailDetailsVO();
							emailDetailsVO.setFromAddress(userName);
							emailDetailsVO.setToAddress(email);
							emailDetailsVO.setSubject(subject);
							emailDetailsVO.setSenderName(senderName);
							
							mailsSendingService.sendEmailFriendRequest(emailDetailsVO);
						}
					}
			}
			resultStatus.setResultPartial(false);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			dataTransferVO.setResultStatus(resultStatus);
			
		}catch(Exception e){
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setResultPartial(true);
			dataTransferVO.setResultStatus(resultStatus);	
		}
	return dataTransferVO;
	} 
	
	
	/**
	 * This method can be used by other methods to populate or set data into a data transfer object which contains the list of 
	 * registered users.
	 * @author Ravi Kiran.Y
	 * @version 1.0,13/10/2010	
	 */
	public List<CandidateVO> setFriendsListForAUser(List result,Long loginId,String status,String statusValue){
		List<CandidateVO> candidateDetails = new ArrayList<CandidateVO>();
		List<Long> candidates = new ArrayList<Long>();
		Map<Long, CandidateVO> userIdAndRelationShipWithLogedUser = new HashMap<Long, CandidateVO>();
		DataTransferVO dataTransferVO = new DataTransferVO();
		ResultStatus resultStatus = new ResultStatus();
		String originalStatus = status;
		try{
			if(result!=null && result.size()!=0){
				for(int i=0;i<result.size();i++){
					Object[] parms = (Object[])result.get(i);
					CandidateVO candidateVO = new CandidateVO();
					Long userId = new Long(parms[2].toString());
					String lastName="";
					if(parms[1]!=null){
						lastName = parms[1].toString();
					}
					candidateVO.setCandidateName(parms[0].toString().concat(" ").concat(lastName));
					candidateVO.setId(userId);
					Long friendsCount = userConnectedtoDAO.getConnectedMembersCountForAFreeUser(userId);
					candidateVO.setNoOfFriends(friendsCount);
					Long postsCount = problemHistoryDAO.getProblemCountOfFreeUser(userId);
					candidateVO.setNoOfPosts(postsCount);
					candidateVO.setStatus(IConstants.NOTCONNECTED);
					candidateVO.setConstituencyId(new Long(parms[4].toString()));
					candidateVO.setConstituencyName(parms[3].toString());
					
						if(parms[6].toString() !=null)
							candidateVO.setDistrictId(new Long(parms[6].toString()));
						if(parms[7].toString() !=null)
							candidateVO.setDistrict(parms[7].toString());
						if(parms[8].toString() !=null)
							candidateVO.setStateId(new Long(parms[8].toString()));
						if(parms[9].toString() !=null)	
							candidateVO.setState(parms[9].toString());
					
					if(parms[5] != null){
						if(status.equalsIgnoreCase(IConstants.PENDING)){
							CustomMessage userData=(CustomMessage)parms[5];
							if(userData.getRecepient().getProfileImg()!=null && !userData.getRecepient().getProfileImg().equals(""))
								candidateVO.setImage(userData.getRecepient().getProfileImg());
						}
						else{
					            User user = (User)parms[5];
							if(user.getProfileImg() != null && !user.getProfileImg().equals(""))
								candidateVO.setImage(user.getProfileImg());	
						}					
					}
					
					if(loginId!=0){
						candidates.add(userId);
						userIdAndRelationShipWithLogedUser.put(userId,candidateVO);
					}else{
						candidateDetails.add(candidateVO);
					}				
				}
				if(loginId!=0){	
					if(originalStatus.equalsIgnoreCase(IConstants.NOTCONNECTED)){
						status = IConstants.ALL;
					}
					
					if(originalStatus.equalsIgnoreCase(IConstants.NOTCONNECTED)){
						status = IConstants.ALL;
					}
					if(statusValue != "DISCONNECTED"){
					List<Object> details = customMessageDAO.getRelationShipBetweenTheUsers(candidates,loginId,status);	
					for(int i=0;i<details.size();i++){
						Object[] parms = (Object[])details.get(i);				
						Long userId = new Long(parms[0].toString());			
						if(userIdAndRelationShipWithLogedUser.get(userId)!=null){
							userIdAndRelationShipWithLogedUser.get(userId).setStatus(parms[1].toString());	
						}				
					}
					}
					if(userIdAndRelationShipWithLogedUser.get(loginId)!=null){
						userIdAndRelationShipWithLogedUser.get(loginId).setStatus(IConstants.LOGGED_USER);
					}
					
					List<Long> userID = new ArrayList<Long>(0);
					userID.add(loginId);
					List<UserConnectedto> detailsList = userConnectedtoDAO.checkForRelationBetweenUsers(candidates,userID);	
					Set<Long> userIds = new HashSet<Long>();
					if(detailsList!=null && detailsList.size()!=0){
						for(UserConnectedto userConnectedto : detailsList){
							userIds.add(userConnectedto.getUserSource().getUserId());
							userIds.add(userConnectedto.getUserTarget().getUserId());
						}
						userIds.remove(loginId);
					}	
					List<Long> ids = new ArrayList<Long>(userIds);
					for(int i=0;i<ids.size();i++){	
						userIdAndRelationShipWithLogedUser.get(ids.get(i)).setStatus(IConstants.CONNECTED);
					}
					if(userIdAndRelationShipWithLogedUser.get(loginId)!=null){
						userIdAndRelationShipWithLogedUser.get(loginId).setStatus(IConstants.LOGGED_USER);
					}
					
					for(Map.Entry<Long, CandidateVO> data : userIdAndRelationShipWithLogedUser.entrySet()){
						if(originalStatus.equalsIgnoreCase(IConstants.NOTCONNECTED)){
							if(originalStatus.equalsIgnoreCase(data.getValue().getStatus())){
								candidateDetails.add(data.getValue());
							}
						}else{
							if(status.equalsIgnoreCase(IConstants.ALL)){
								candidateDetails.add(data.getValue());
							}else{
								if(status.equalsIgnoreCase(data.getValue().getStatus())){
									candidateDetails.add(data.getValue());
								}
							}
						}										
					}
				}
				dataTransferVO.setCandidateVO(candidateDetails);
				resultStatus.setResultPartial(false);
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				dataTransferVO.setResultStatus(resultStatus);	
			}else{				
				resultStatus.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
				resultStatus.setResultPartial(true);
				dataTransferVO.setResultStatus(resultStatus);
			}
		}catch(Exception e){
			e.printStackTrace();
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setResultPartial(true);
			dataTransferVO.setResultStatus(resultStatus);
		}
		return candidateDetails;
	}
	
	/**
	 * This method can be used to send a request / accepting the request / posting a scrap / posting a comment / deleting a user.
	 * @author Y.Ravi Kiran.  
	 * @param senderId
	 * @param recipeintId
	 * @param messageType
	 * @param subject
	 */
	public ResultStatus saveCommunicationDataBetweenUsers(final List<Long> senderId,final List<Long> recipeintId,final String messageType,final String subject,final String senderName){
		
		final ResultStatus resultStatus = new ResultStatus();
		transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus status) {
				CustomMessage customMessage = new CustomMessage();
				try{
						EmailDetailsVO emailDetailsVO = new EmailDetailsVO();
						Long messageTypeId=0l;
						Long pendingId=0l;
						Long disconectedId=0l;
						Long blockId=0l;
						String userName = "";
						String email = "";
						for(MessageType type: messageTypeDAO.getAll()){
							if(type.getMessageType().equalsIgnoreCase(messageType)){
								messageTypeId =type.getMessageTypeId();							
							}
							if(type.getMessageType().equalsIgnoreCase(IConstants.PENDING)){
								pendingId =type.getMessageTypeId();							
							}
							if(type.getMessageType().equalsIgnoreCase(IConstants.DISCONNECTED)){
								disconectedId =type.getMessageTypeId();							
							}
							if(type.getMessageType().equalsIgnoreCase(IConstants.BLOCK)){
								blockId =type.getMessageTypeId();							
							}
						}
						
						if(messageType.equalsIgnoreCase(IConstants.FRIEND_REQUEST)){	
							for(int i=0;i<recipeintId.size();i++){
								customMessage.setSubject(subject);								
								customMessage.setSender(userDAO.get(senderId.get(0)));
								customMessage.setRecepient(userDAO.get(recipeintId.get(i)));
								customMessage.setMessageType(messageTypeDAO.get(pendingId));
								customMessage.setSentDate(dateUtilService.getCurrentDateAndTime());					
								customMessageDAO.save(customMessage);
							}
						}
						
						if(messageType.equalsIgnoreCase(IConstants.BLOCK)){	
							int result = customMessageDAO.updateRelationBetweenUsers(senderId,recipeintId,blockId,dateService.getPresentPreviousAndCurrentDayDate(IConstants.DATE_PATTERN,0,IConstants.PRESENT_DAY));						
						}
						
						else if(messageType.equalsIgnoreCase(IConstants.CONNECTED)){
							UserConnectedto userConnectedto = new UserConnectedto();
							for(int i=0;i<recipeintId.size();i++){
								userConnectedto.setUserTarget(userDAO.get(recipeintId.get(i)));
								userConnectedto.setUserSource(userDAO.get(senderId.get(0)));		
								userConnectedtoDAO.save(userConnectedto);
							}
							List<CustomMessage> result = customMessageDAO.checkForRelationBetweenUsersBasedOnType(senderId,recipeintId,IConstants.PENDING);
							for(CustomMessage users : result){
								users.setMessageType(messageTypeDAO.get(messageTypeId));
								users.setSentDate(dateUtilService.getCurrentDateAndTime());
								customMessageDAO.save(users);
							}
							for(Long recipeintUserId : recipeintId)
							{
							List<Object[]> list = userDAO.getUserEmail(recipeintUserId);
							if(list !=null && list.size() > 0)
							{
								for(Object[] params : list)
								{
									userName = params[1].toString()+" "+params[2];
									email = params[3].toString();
								}
								if(email != null && email.trim().length() > 0){
									emailDetailsVO.setFromAddress(userName);
									emailDetailsVO.setSenderName(senderName);
									emailDetailsVO.setToAddress(email);
									mailsSendingService.acceptEmailFriendRequest(emailDetailsVO);
								}
							}
							}
						}
						
						else if(messageType.equalsIgnoreCase(IConstants.COMMENTS) || messageType.equalsIgnoreCase(IConstants.SCRAP)){
							for(int i=0;i<recipeintId.size();i++){
								customMessage.setSubject(subject);
								customMessage.setRecepient(userDAO.get(recipeintId.get(i)));
								customMessage.setSender(userDAO.get(senderId.get(0)));
								customMessage.setMessageType(messageTypeDAO.get(messageTypeId));
								customMessage.setSentDate(dateUtilService.getCurrentDateAndTime());					
								customMessage.setStatus(IConstants.MSG_UNREAD);
								customMessageDAO.save(customMessage);
							}	
							
							for(Long recipeintUserId : recipeintId)
							{
								List<Object[]> list = userDAO.getUserEmail(recipeintUserId);
								if(list != null && list.size() > 0)
								{
									for(Object[] params : list)
									{
										userName = params[1].toString()+" "+params[2].toString();
										email = params[3].toString();
									}
									if(email != null && email.trim().length() > 0)
									{
										emailDetailsVO.setFromAddress(userName);
										emailDetailsVO.setToAddress(email);
										emailDetailsVO.setSubject(subject);
										emailDetailsVO.setSenderName(senderName);
										mailsSendingService.sendMessageToConnectUser(emailDetailsVO);
									}
								}
							}
						}
						
						else if(messageType.equalsIgnoreCase(IConstants.REJECTED)){														
							List<UserConnectedto> result = userConnectedtoDAO.checkForRelationBetweenUsers(senderId,recipeintId);
							if(result!=null && result.size()!=0){
								userConnectedtoDAO.deleteRejectedRequest(senderId,recipeintId);
							}
							List<CustomMessage> customMessageresult = customMessageDAO.checkForRelationBetweenUsers(senderId,recipeintId);
							for(CustomMessage users : customMessageresult){
								users.setMessageType(messageTypeDAO.get(disconectedId));								
								customMessageDAO.save(users);
							}
						}
						resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
						resultStatus.setResultPartial(false);
					}catch(Exception e){
						resultStatus.setExceptionEncountered(e);
						resultStatus.setResultCode(ResultCodeMapper.FAILURE);
						resultStatus.setResultPartial(true);
					}
			 return customMessage;
			}
		});
		return resultStatus;
	}
	
	
	/**
	 * This method can be used to get all details for building user profile.
	 * it takes userId or list of userIds as input and builds a datatransfer object that contains all
	 * details for building user profile.
	 * 
	 * @author Ravi Kiran.Y
	 * @version 1.0,14-0-10.
	 * @version 1.1,15-0-10.
	 * @param userId
	 * @param informationStatus
	 * @return DataTransferVO
	 */
	public DataTransferVO getDataForAUserProfile(List<Long> userId,String informationStatus){
		ResultStatus resultStatus = new ResultStatus();
		DataTransferVO dataTransferVO = new DataTransferVO();
		//DataTransferVO dataVo = new DataTransferVO();
		List<CandidateVO> resultVO = new ArrayList<CandidateVO>(); 
		try{
			if(userId.size() != 1)
			{
				return null;
			}
			/*
			 * This block is used to get the location details of the logged in user ie., state, district, constituency
			 *  
			 */
			Object[] params = null;
			List<Object[]> details =userDAO.getUserLocationDetailsByUserIds(userId);
			if(details.size() == 1)
			{				
				params = details.get(0);
				
				dataTransferVO.setStateId((Long)params[0]);
				dataTransferVO.setStateName(params[1].toString());
				dataTransferVO.setDistrictId((Long)params[2]);
				dataTransferVO.setDistrictName(params[3].toString());
				dataTransferVO.setConstituencyId((Long)params[4]);
				dataTransferVO.setConstituencyName(WordUtils.capitalize(params[5].toString().toLowerCase()));
				dataTransferVO.setUserId((Long)params[6]);
				if(params[7] != null)
				dataTransferVO.setProfileImg(params[7].toString());
			}
			else
			{
				if(log.isDebugEnabled())
					log.fatal(" Exists more than one user with same user id = "+userId.get(0));
			}
			
			/*
			 * 
			 * This block is used to get parliament Constituency Id & Name of the user
			 */
			
			List parliamentDetails = null;
			if(dataTransferVO.getConstituencyId() != null && dataTransferVO.getConstituencyId().longValue() != 0)
				parliamentDetails =  delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentForAssembly(dataTransferVO.getConstituencyId());
			
			if(parliamentDetails !=null && parliamentDetails.size() != 0)
			{
				params = (Object[])parliamentDetails.get(0);
				
				dataTransferVO.setParliamentConstId((Long)params[0]);
				dataTransferVO.setParliamentConstName(params[1].toString());
			}
			
			/*
			 * This block is used to get the connected users count in logged in user location  
			 * 
			 */
			if(dataTransferVO.getConstituencyId() != null)
			{
				List constCount = userDAO.getConnectedUsersCount(dataTransferVO.getConstituencyId(), IConstants.CONSTITUENCY);
				if(constCount.size() == 1)
				{
					dataTransferVO.setConstituencyUsersCount(constCount.get(0).toString());					
				}
				
			}
			
			if(dataTransferVO.getDistrictId() != null)
			{
				List distCount = userDAO.getConnectedUsersCount(dataTransferVO.getDistrictId(), IConstants.DISTRICT);
				if(distCount.size() == 1)
				{
					dataTransferVO.setDistrictUsersCount(distCount.get(0).toString());					
				}
			}
			
			
			/*
			 * This block is used to get all the connected people for the given user or users.
			 * 
			 * The 			
			 * 			resultStatusForConnectedPeople 
			 * 
			 * contains whether the block has been executed successfully or not.
			 */
			DataTransferVO dataVo = getAllPeopleConnectedPeopleForUserBasedOnLevelsOfConnection(userId,1,informationStatus);
			ResultStatus resultStatusForConnectedPeople = new ResultStatus(); 
			if(dataVo.getResultStatus().getResultCode()==ResultCodeMapper.FAILURE){
				resultStatusForConnectedPeople.setResultCode(ResultCodeMapper.FAILURE);
			}else{
				resultVO = dataVo.getCandidateVO();				
				resultStatusForConnectedPeople.setResultCode(ResultCodeMapper.SUCCESS);
				dataTransferVO.setConnectedPeople(resultVO);
			}
			dataTransferVO.setResultStatusForConnectedPeople(resultStatusForConnectedPeople);
			
			
			

			/*
			 * This block is used to get all the connected people based on the level specified in 	
			 * 
			 * 			IConstants.MAX_LEVEL_OF_CONNECTION.
			 * 
			 * The 			
			 * 			resultStatusForPeopleYouMayKnow 
			 * 
			 * contains whether the block has been executed successfully or not.
			 */
			DataTransferVO peopleYouMayKnow  = getAllPeopleConnectedPeopleForUserBasedOnLevelsOfConnection(userId,IConstants.MAX_LEVEL_OF_CONNECTION,informationStatus);			
			ResultStatus resultStatusForPeopleYouMayKnow = new ResultStatus(); 
			if(peopleYouMayKnow.getResultStatus().getResultCode()==ResultCodeMapper.FAILURE){
				resultStatusForPeopleYouMayKnow.setResultCode(ResultCodeMapper.FAILURE);
			}else{
				resultVO = peopleYouMayKnow.getCandidateVO();				
				resultStatusForPeopleYouMayKnow.setResultCode(ResultCodeMapper.SUCCESS);
				dataTransferVO.setPeopleYouMayKnow(resultVO);
			} 
			dataTransferVO.setResultStatusForPeopleYouMayKnow(resultStatusForPeopleYouMayKnow);
			
			/*
			 * This block is used to get all the friend requests that are sent for the user or users.
			 * 
			 * The 			
			 * 			resultStatusForFriendRequest 
			 * 
			 * contains whether the block has been executed successfully or not.
			 */
			
			DataTransferVO friendRequest = getAllMessagesForUser(userId,IConstants.PENDING);
			ResultStatus resultStatusForFriendRequest = new ResultStatus(); 
			if(friendRequest.getResultStatus().getResultCode()==ResultCodeMapper.FAILURE){
				resultStatusForFriendRequest.setResultCode(ResultCodeMapper.FAILURE);
			}else{			
				resultVO = friendRequest.getCandidateVO();
				if(resultVO!=null && resultVO.size()!=0){
					resultStatusForFriendRequest.setResultCode(ResultCodeMapper.SUCCESS);
					dataTransferVO.setFriendRequest(resultVO);		
				}else{
					resultStatusForFriendRequest.setResultCode(ResultCodeMapper.FAILURE);
				}
			} 
			dataTransferVO.setResultStatusForFriendRequest(resultStatusForFriendRequest);
			
			/*
			 * This block is used to get all the scraps for the user or users.
			 * 
			 * The 			
			 * 			resultStatusForScraps 
			 * 
			 * contains whether the block has been executed successfully or not.
			 */
			
			DataTransferVO scraps = getAllMessagesForUser(userId,IConstants.SCRAP);
			ResultStatus resultStatusForScraps = new ResultStatus(); 
			if(scraps.getResultStatus().getResultCode()==ResultCodeMapper.FAILURE){
				resultStatusForScraps.setResultCode(ResultCodeMapper.FAILURE);
			}else{			
				resultVO = scraps.getCandidateVO();
				if(resultVO!=null && resultVO.size()!=0){
					resultStatusForScraps.setResultCode(ResultCodeMapper.SUCCESS);
					dataTransferVO.setScraps(resultVO);		
				}else{
					resultStatusForScraps.setResultCode(ResultCodeMapper.FAILURE);
				}
			} 
			dataTransferVO.setResultStatusForScraps(resultStatusForScraps);
			
			
			/*
			 * This block is used to get all the comments for the user or users.
			 * 
			 * The 			
			 * 			resultStatusForScraps 
			 * 
			 * contains whether the block has been executed successfully or not.
			 */
			
			DataTransferVO statusForComments = getAllMessagesForUser(userId,IConstants.COMMENTS);			
			ResultStatus resultStatusForComments = new ResultStatus(); 
			if(statusForComments.getResultStatus().getResultCode() == ResultCodeMapper.FAILURE){
				resultStatusForComments.setResultCode(ResultCodeMapper.FAILURE);
			}else{			
				resultVO = statusForComments.getCandidateVO();
				if(resultVO!=null && resultVO.size()!=0){
					resultStatusForComments.setResultCode(ResultCodeMapper.SUCCESS);
					dataTransferVO.setComments(resultVO);
				}else{
					resultStatusForComments.setResultCode(ResultCodeMapper.FAILURE);
				}
			} 
			dataTransferVO.setResultStatusForComments(resultStatusForComments);
			
			dataTransferVO.setTotalMsgCount(statusForComments.getTotalMsgCount());
			dataTransferVO.setUnreadMsgCount(statusForComments.getUnreadMsgCount());
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			resultStatus.setResultPartial(false);
			dataTransferVO.setResultStatus(resultStatus);
		}catch(Exception e){
			e.printStackTrace();
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setResultPartial(true);
			dataTransferVO.setResultStatus(resultStatus);	
		}
	return dataTransferVO;
	} 
	
	/**
	 * This method is used to get all the connected people for a user.
	 * 
	 * @author Ravi Kiran.Y
	 * @version 1.0,14-2-11.
	 * 
	 * @param userId
	 * @return DataTransferVO
	 */
	public DataTransferVO getAllPeopleConnectedPeopleForUser(List<Long> userId){
		List<CandidateVO> resultVO = new ArrayList<CandidateVO>(); 
		ResultStatus resultStatus = new ResultStatus();
		DataTransferVO dataTransferVO = new DataTransferVO();
		try{
			DataTransferVO dataVo = getAllPeopleConnectedPeopleForUserBasedOnLevelsOfConnection(userId,1,IConstants.COMPLETE_DETAILS);
			ResultStatus resultStatusForConnectedPeople = new ResultStatus(); 
			if(dataVo.getResultStatus().getResultCode()==ResultCodeMapper.FAILURE){
				resultStatusForConnectedPeople.setResultCode(ResultCodeMapper.FAILURE);
			}else{
				resultVO = dataVo.getCandidateVO();				
				resultStatusForConnectedPeople.setResultCode(ResultCodeMapper.SUCCESS);
				dataTransferVO.setConnectedPeople(resultVO);
			}
			dataTransferVO.setResultStatusForConnectedPeople(resultStatusForConnectedPeople);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			resultStatus.setResultPartial(false);
		}catch(Exception e){
			e.printStackTrace();
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setResultPartial(true);
			dataTransferVO.setResultStatus(resultStatus);	
		}
		return dataTransferVO;
	}
	/**
	 * This method is used to get all connected/not connected people for the user/users based on the level specified.
	 * 
	 * level can be explained as follows....
	 * level 1 means your friends.
	 * level 2 means friends of your friends.
	 * level 3 means friends of your friends of your friends.
	 * .....
	 * 
	 * @author Ravi Kiran.Y
	 * @version 1.0,14-10-10.
	 * @version 1.1,15-10-10.
	 * 
	 * @param userId
	 * @param levels
	 * @return DataTransferVO
	 */
	public DataTransferVO getAllPeopleConnectedPeopleForUserBasedOnLevelsOfConnection(List<Long> userId,int levels,String informationStatus){
		List<Long> originalList = new ArrayList<Long>();			
		originalList.addAll(userId);
		List<Long> newList = new ArrayList<Long>(originalList);
		List<Long> tempIds = new ArrayList<Long>();
		ResultStatus resultStatus = new ResultStatus();
		DataTransferVO dataTransferVO = new DataTransferVO();
		DataTransferVO resultVO = new DataTransferVO();
		List<Long> unKnownPeople = new ArrayList<Long>();
		try{
			for(int i=0;i<levels;i++){
				if(newList.size()!=0 && originalList.size()!=0){
					tempIds =  getUserIds(newList,originalList);
					if(i!=1){
						unKnownPeople.addAll(tempIds);
					}
					newList.clear();
					newList.addAll(tempIds);	
					originalList.addAll(tempIds);	
				}			
			}
			
			//unKnownPeople list gives the list of id's of all the people to the level
			//i.e., if we want all unknown people up to third level 
			
			//newList list contains id's of all the people of that particular level
			
			if(levels>1 && newList.size()!=0){
				List<CustomMessage> customMessageresult = customMessageDAO.checkForRelationBetweenUsers(userId,newList);
				for(CustomMessage users : customMessageresult){
					Long id = users.getRecepient().getUserId();
					if(newList.contains(id)){
						newList.remove(id);
					}
				}	
				customMessageresult = null;
			}
			if(newList!=null && newList.size()!=0){				
				List<User> result = userDAO.getDetailsForUsers(newList);
				resultVO = setUserProfileData(result,informationStatus);	
			}
			
			if(resultVO.getResultStatus()==null){
				dataTransferVO.setCandidateVO(resultVO.getCandidateVO());
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				resultStatus.setResultPartial(false);
				dataTransferVO.setResultStatus(resultStatus);
			}else{
				if(resultVO==null && resultVO.getResultStatus().getResultCode()==ResultCodeMapper.FAILURE){
					dataTransferVO.setCandidateVO(resultVO.getCandidateVO());
					resultStatus.setResultCode(ResultCodeMapper.FAILURE);
					resultStatus.setResultPartial(false);
					dataTransferVO.setResultStatus(resultStatus);
				}else{
					dataTransferVO.setCandidateVO(resultVO.getCandidateVO());
					resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
					resultStatus.setResultPartial(false);
					dataTransferVO.setResultStatus(resultStatus);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setResultPartial(true);
			dataTransferVO.setResultStatus(resultStatus);
		}finally{			
			originalList = null;
			tempIds = null;
			unKnownPeople = null;
		}
		return dataTransferVO;
	}
	
	/**
	 * This method is used to set data for user profiles.
	 * 
	 * @author Ravi Kiran.Y
	 * @version 1.0,14-10-10.
	 * @version 1.1,15-10-10.
	 * @param List<AnanymousUser>
	 * @return DataTransferVO
	 * @return informationStatus
	 * @see getAllPeopleConnectedPeopleForUserBasedOnLevelsOfConnection()
	 */
	public DataTransferVO setUserProfileData(List<User> result,String informationStatus){		
		ResultStatus resultStatus = new ResultStatus();
		List<CandidateVO> candiateVO = new ArrayList<CandidateVO>(0); 
		DataTransferVO dataTransferVO = new DataTransferVO();
		try{
			if(result!=null && result.size()!=0){
				if(informationStatus.equalsIgnoreCase(IConstants.COMPLETE_DETAILS)){
					for(User details : result){
						CandidateVO candidateResults = new CandidateVO();
						String candidateName = " ";
						String name = details.getFirstName();
						if(name!=null){
							candidateName+=name;
						}
						name = details.getLastName();
						if(name!=null){
							candidateName = candidateName +" "+ name;
						}						
						candidateResults.setCandidateName(candidateName);
						candidateResults.setId(details.getUserId());	
						candidateResults.setState(details.getState().getStateName());
						candidateResults.setDistrict(details.getDistrict().getDistrictName());						
						candidateResults.setConstituencyName(details.getConstituency().getName());
						candidateResults.setImage(details.getProfileImg());
						candiateVO.add(candidateResults);
					}
				}else{
					for(User details : result){
						CandidateVO candidateResults = new CandidateVO();
						String candidateName = null;
						String name = details.getFirstName();
						if(name!=null){
							candidateName+=name;
						}
						name = details.getLastName();
						if(name!=null){
							candidateName+=name;
						}
						candidateResults.setCandidateName(candidateName);
						candidateResults.setId(details.getUserId());
						candidateResults.setImage(details.getProfileImg());
						candiateVO.addAll(candiateVO);
					}
				}
				
			}
			dataTransferVO.setCandidateVO(candiateVO);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			resultStatus.setResultPartial(false);
			dataTransferVO.setResultStatus(resultStatus);
		}catch(Exception e){
			e.printStackTrace();
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setResultPartial(true);
			dataTransferVO.setResultStatus(resultStatus);
		}
		return dataTransferVO;		
	}
	
	
	
	/**
	 * This method is used to filter the list of connected people.
	 * As the data from db may/maynot contains some duplicate records passing such records to this method,
	 * can helps to remove the duplicate records.
	 * 
	 * @author Ravi Kiran.Y
	 * @version 1.0,14-10-10.
	 * @param userId
	 * @param ids
	 * @return
	 */
	public List<Long> getUserIds(List<Long> userId,List<Long> ids){		
		Set<Long> setOfUserIds = new HashSet<Long>(0);
		List<Long> listOfUserIds = new ArrayList<Long>(0);
		try{
			List<Object> connectedPeopleIds = userConnectedtoDAO.getAllConnectedPeopleForUser(userId);
			for(int i=0;i<connectedPeopleIds.size();i++){
				Object[] parms = (Object[])connectedPeopleIds.get(i);
				setOfUserIds.add(new Long(parms[0].toString()));
				setOfUserIds.add(new Long(parms[1].toString()));
			}
			setOfUserIds.removeAll(userId);
			setOfUserIds.removeAll(ids);
			listOfUserIds.addAll(setOfUserIds);	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ids = null;
			setOfUserIds = null;
			userId= null;
		}
		
		return listOfUserIds;
	}
	
	public String displayMessageAndUpdateUnread(Long customMessageId){
		CustomMessage customMessage = customMessageDAO.get(customMessageId);
		customMessage.setStatus(IConstants.MSG_READ);
		customMessageDAO.save(customMessage);
		return customMessage.getSubject();
	}
	
	/**
	 * This method is used to get all the comments or scraps that are being received by the user.
	 * @author Ravi Kiran.Y
	 * @version 1.0,14-10-10.
	 * @param userId
	 * @param messageType
	 * @return
	 */
	public DataTransferVO getAllMessagesForUser(List<Long> userId,String messageType){
		ResultStatus resultStatus = new ResultStatus();
		List<CandidateVO> candiateVO = new ArrayList<CandidateVO>(0); 
		DataTransferVO dataTransferVO = new DataTransferVO();
		Long totalMsgCount = 0l;
		Long unreadMsgCount= 0l;
		String message,data;
		try{
			List<Object> result = customMessageDAO.getAllMessagesForUser(userId,messageType);
			if(result!=null && result.size()!=0){
				totalMsgCount = new Long(result.size());
				for(int i=0;i<result.size();i++){
					Object[] parms = (Object[])result.get(i);
					CandidateVO candidateResults = new CandidateVO();
					
					if(parms[0] != null)
						message = staticDataService.removeSpecialCharectersFromString(parms[0].toString());
					else
						message = "";
					
					
					
					data = StringUtils.replace(parms[0].toString(),"\n"," ");
					data = staticDataService.removeSpecialCharectersFromString(data);
					
					if(data.length() < 20)
						candidateResults.setData(data);
					else
						candidateResults.setData(data.substring(0, 19));
					
					candidateResults.setMessage(message);
					candidateResults.setId(new Long(parms[1].toString()));
					String candidateName="";
					
					if(parms[2]!=null)
						candidateName+=parms[2].toString().concat("  ").concat("  ");
					
					if(parms[3]!=null)
						candidateName+=parms[3].toString();

					candidateResults.setState(parms[4].toString());
					candidateResults.setDistrict(parms[5].toString());					
					candidateResults.setConstituencyName(parms[6].toString());
					candidateResults.setCandidateName(candidateName.toUpperCase());
					String status = (parms[8]!=null)?parms[8].toString():"";
					candidateResults.setStatus(status);
					if(IConstants.MSG_UNREAD.equalsIgnoreCase(status))
						unreadMsgCount++;
					
					candidateResults.setRecepientId(parms[10]!=null?(Long)parms[10]:null);
					candidateResults.setPostedDate(parms[9]!=null?DateService.timeStampConversion(parms[9].toString()):"");
					candidateResults.setCostumMessageId(parms[7]!=null?(Long)parms[7]:null);
					candiateVO.add(candidateResults);
				}
			}
			dataTransferVO.setTotalMsgCount(totalMsgCount);
			dataTransferVO.setUnreadMsgCount(unreadMsgCount);
			dataTransferVO.setCandidateVO(candiateVO);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			resultStatus.setResultPartial(false);
			dataTransferVO.setResultStatus(resultStatus);
			}catch(Exception e){
				e.printStackTrace();
				resultStatus.setExceptionEncountered(e);
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				resultStatus.setResultPartial(true);
				dataTransferVO.setResultStatus(resultStatus);	
			}
		return dataTransferVO;		
	}	
	
	/**
	 * This method can be used to get all the message types.
	 * @author Ravi Kiran.Y
	 * @version 1.0,16-10-10.
	 * @return
	 */
	public NavigationVO getAllMessageTypes(){
		ResultStatus resultStatus = new ResultStatus();
		NavigationVO navigationVO = new  NavigationVO();
		 List<SelectOptionVO> selList = new ArrayList<SelectOptionVO>(0);
		try{
			for(MessageType type: messageTypeDAO.getAll()){
				if(
				   IConstants.CONNECTED.equalsIgnoreCase(type.getMessageType()) 	|| 
				   
				   IConstants.NOTCONNECTED.equalsIgnoreCase(type.getMessageType()) 	||
				   
				   IConstants.PENDING.equalsIgnoreCase(type.getMessageType())){
					
					SelectOptionVO selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId(type.getMessageTypeId());
					selectOptionVO.setName(type.getMessageType());
					selList.add(selectOptionVO);
				}
				
			}
			navigationVO.setMessageTypes(selList);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			resultStatus.setResultPartial(false);
			navigationVO.setResultStatus(resultStatus);
		}catch(Exception e){
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setResultPartial(true);
			navigationVO.setResultStatus(resultStatus);	
		}
	return navigationVO;
	}
	
	public List<SelectOptionVO> findAllProfileOptsAvailableInDB(){
		List<SelectOptionVO> opts = new ArrayList<SelectOptionVO>();
		List list = profileOptsDAO.getAllProfileOpts();
		for(Object[] values:(List<Object[]>)list)
			opts.add(new SelectOptionVO(Long.parseLong(values[0].toString()), values[1].toString()));
		return opts;
	}
	
	public RegistrationVO getDetailsOfUserByUserId(Long registrationId)
	{
		try{
		RegistrationVO registrationVO = new RegistrationVO();
		User user = userDAO.getUserByUserId(registrationId);
		List<Long> userOpts = new ArrayList<Long>();
		try{
			
			registrationVO.setRegistrationID(registrationId);
			registrationVO.setUserName(user.getUserName());
			registrationVO.setPassword(user.getPassword());
			registrationVO.setGender(user.getGender());
			registrationVO.setEmail(user.getEmail());
			registrationVO.setPhone(user.getPhone());
			registrationVO.setMobile(user.getMobile());
			registrationVO.setAddress(user.getAddress());
			if(user.getDateOfBirth()!=null)
			{
				registrationVO.setDateOfBirth(DateService.timeStampConversionToDDMMYY(user.getDateOfBirth().toString()));
			}
			registrationVO.setFirstName(user.getFirstName());
			registrationVO.setLastName(user.getLastName());
			
			if(user.getState() != null)
				registrationVO.setState(user.getState().getStateId().toString());
			if(user.getConstituency() != null)
				registrationVO.setConstituency(user.getConstituency().getConstituencyId().toString());
			
			registrationVO.setPincode(user.getPincode());
			
			/*for(UserProfileOpts userOptsModel:user.getUserProfileOptses())
				userOpts.add(userOptsModel.getProfileOpts().getProfileOptsId());
			*/
			registrationVO.setProfileOpts(userOpts);

		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return registrationVO;
	  }catch (Exception e) {
		  log.error("Exception Encounted, check log Details - "+e);
		  return null;
	  }
	}
	
	public CandidateCommentsVO getAllPostedReasonsCountByUserId(Long registrationId)
	{
		CandidateCommentsVO commentsVO = new CandidateCommentsVO();
		commentsVO.setApprovedReasonsCount(0L);
		commentsVO.setRejectedReasonsCount(0L);
		commentsVO.setNotConsideredReasonsCount(0L);
		commentsVO.setTotalPostedReasonsCount(0L);
		commentsVO.setPostedReasonsCountByConnectedUsers(0L);
		try 
		{	
			List<Long> connectedUserIds = getConnectedUserIdsByUserId(registrationId);
			List comments = commentCategoryCandidateDAO.getPostedReasonsCountByFreeUserId(registrationId);
			if(comments != null && comments.size() > 0)
			{
				for (int i = 0; i < comments.size(); i++) {
					Object[] params = (Object[])comments.get(i);
					
					if(params[1] == null)
						commentsVO.setNotConsideredReasonsCount((Long)params[0]);					
					else if(params[1].toString().equalsIgnoreCase("false"))
						commentsVO.setRejectedReasonsCount((Long)params[0]);
					else if(params[1].toString().equalsIgnoreCase("true"))
						commentsVO.setApprovedReasonsCount((Long)params[0]);
				}				
			}
			
			//get Connected Users Posted Reasons Count
			
			if(connectedUserIds != null && connectedUserIds.size() > 0)
			{
				List connUsersPostedReasCount = commentCategoryCandidateDAO.getConnectedUsersPostedReasonsCount(connectedUserIds);
				if(connUsersPostedReasCount != null && connUsersPostedReasCount.size() > 0)
				{
					for(int i=0;i<connUsersPostedReasCount.size();i++)
					{
						Object[] reasonsCount = (Object[])connUsersPostedReasCount.get(i);
						if(reasonsCount[1].toString().equalsIgnoreCase("true"))
							commentsVO.setPostedReasonsCountByConnectedUsers((Long)reasonsCount[0]);
						
					}
				}
			}
			
			//List commentsCount = commentCategoryCandidateDAO.getPostedReasonsCountOtherThanLoginUserId(registrationId,connectedUserIds);
			List commentsCount = commentCategoryCandidateDAO.getPostedReasonsCountOtherThanLoginUserId(registrationId);
			if(commentsCount != null && commentsCount.size() == 1)
			{
				Long count = (Long)commentsCount.get(0) - commentsVO.getPostedReasonsCountByConnectedUsers();
				
				//commentsVO.setPostedReasonsCountByOtherUsers((Long)commentsCount.get(0));	
				commentsVO.setPostedReasonsCountByOtherUsers(count);
			}
			//commentsVO.setTotalPostedReasonsCount(commentsVO.getApprovedReasonsCount()+ commentsVO.getRejectedReasonsCount() + commentsVO.getNotConsideredReasonsCount() + commentsVO.getPostedReasonsCountByOtherUsers()+commentsVO.getPostedReasonsCountByConnectedUsers());
			commentsVO.setTotalPostedReasonsCount(commentsVO.getApprovedReasonsCount()+  commentsVO.getPostedReasonsCountByOtherUsers()+commentsVO.getPostedReasonsCountByConnectedUsers());
			return commentsVO;
		}
		catch (Exception e) {
			e.printStackTrace();
			return commentsVO;
		}
		
		
	}
	
	@SuppressWarnings("unchecked")
	public UserCommentsInfoVO getAllPostedReasonsByUserId(Long registrationId, Integer startIndex, Integer results, String order, String columnName, String reasonType)
	{		
		String columnInModel = getColumnNameFromModel(columnName);
		UserCommentsInfoVO userComments = new UserCommentsInfoVO();
		
		List<CandidateCommentsVO> commentsList = null;
		
		try 
		{	
			List<Long> connectedUserIds = getConnectedUserIdsByUserId(registrationId); 
			List comments = commentCategoryCandidateDAO.getPostedReasonsByFreeUserId(registrationId, startIndex, results, order, columnInModel, reasonType,connectedUserIds);
			
			if(comments != null && comments.size() > 0)
			{
				commentsList = new ArrayList<CandidateCommentsVO>();
				for (int i = 0; i < comments.size(); i++)
				{
					CandidateCommentsVO comment = new CandidateCommentsVO();
					Object[] params = (Object[])comments.get(i);
					
					CommentData commentData = (CommentData)params[0];
					comment.setCommentId(commentData.getCommentDataId());
					comment.setCommentDesc(commentData.getCommentDesc());
					String date = commentData.getCommentDate().toString();
					String dateObj = date.substring(8,10)+'-'+date.substring(5,7)+'-'+date.substring(0,4);
					comment.setCommentedOn(dateObj);
					comment.setCommentedBy(commentData.getCommentBy());
					comment.setIsApproved(commentData.getIsApproved());
					comment.setCandidateId((Long)params[1]);
					comment.setCandidate(params[2].toString());
					comment.setPartyName(params[3].toString());
					comment.setConstituencyName(params[4].toString());
					comment.setRank((Long)params[5]);
					comment.setElectionType(params[6].toString());
					comment.setElectionYear(params[7].toString());		
					comment.setUserId((Long)params[8]);
					comment.setImgURL((params[9]!=null && params[9].toString().length() > 0 )? params[9].toString() : "human.jpg");
					//comment.setCommentCategory(commentDataCategoryDAO.get(commentData.getCommentDataCategory().getCommentDataCategoryId()).getCommentDataCategoryType());
					comment.setCommentCategory(commentData.getCommentDataCategory().getCommentDataCategoryType());
					
					commentsList.add(comment);					
				}
			}	
			
			userComments.setCandidateComments(commentsList);
			
			/*if(reasonType.equals(IConstants.TOTAL) || reasonType.equals(IConstants.OTHERUSERS))
				userComments.setTotalResultsCount(commentCategoryCandidateDAO.getTotalPostedReasonsCountByFreeUserId());
			else
				userComments.setTotalResultsCount(commentCategoryCandidateDAO.getTotalPostedReasonsCountByFreeUserId(registrationId));
			
			*/
			userComments.setTotalResultsCount(commentCategoryCandidateDAO.getTotalPostedReasonsCount(reasonType, registrationId, connectedUserIds));
			
			return userComments; 
			
		}
		catch (Exception e)
		{
			return userComments; 
		}		
	}
	
	private String getColumnNameFromModel(String columnName) {
		
		String dbColumnName = "";
		if(columnName.equalsIgnoreCase("candidate"))
			dbColumnName = "model.nomination.candidate.lastname";
		else if(columnName.equalsIgnoreCase("partyName"))
			dbColumnName = "model.nomination.party.shortName";
		else if(columnName.equalsIgnoreCase("electionYear"))
			dbColumnName = "model.nomination.constituencyElection.election.electionYear";
		else if(columnName.equalsIgnoreCase("rank"))
			dbColumnName = "model.nomination.candidateResult.rank";
		else if(columnName.equalsIgnoreCase("commentDesc"))
			dbColumnName = "model.commentData.commentDesc";
		else  if(columnName.equalsIgnoreCase("commentedOn"))
			dbColumnName = "model.commentData.commentDate";
		else  if(columnName.equalsIgnoreCase("constituencyName"))
			dbColumnName = "model.nomination.constituencyElection.constituency.name";
		else  if(columnName.equalsIgnoreCase("electionType"))
			dbColumnName = "model.nomination.constituencyElection.election.electionScope.electionType.electionType";
		
		return dbColumnName;
	}
	
	public ProblemDetailsVO getPostedProblemsCount(Long userId)
	{
		ProblemDetailsVO problemVO = new ProblemDetailsVO();
		problemVO.setNotConsideredProblemsCount(0l);
		problemVO.setApprovedProblemsCount(0l);
		problemVO.setRejectedProblemsCount(0l);
		problemVO.setPostedProblemsCountByLoggedInUsers(0l);
		problemVO.setPostedProblemsCountByOtherUsers(0l);
		problemVO.setTotalPostedProblemsCount(0l);
		problemVO.setPostedProblemsCountByConnectedUsers(0l);
		try
		{
			List<Long> connectedUserIds = getConnectedUserIdsByUserId(userId);
			List problems = userProblemDAO.getAllPostedProblemCount(userId);
			if(problems !=null && problems.size() > 0)
			{
				for(int i=0;i<problems.size();i++)
				{
				Object[] params = (Object[])problems.get(i);
				if(params[1].toString().equalsIgnoreCase("false"))
					problemVO.setNotConsideredProblemsCount((Long)params[0]);
				else if(params[1].toString().equalsIgnoreCase("true"))
					problemVO.setApprovedProblemsCount((Long)params[0]);
				else 
					problemVO.setRejectedProblemsCount((Long)params[0]);
					
					
				
				}
			}
			
			//problem Count by ConnectedUsers
			
			if(connectedUserIds != null && connectedUserIds.size() > 0)
			{
				List connectedUsersPostedProbCount = userProblemDAO.getConnectedUsersProblemCount(connectedUserIds);
				if(connectedUsersPostedProbCount != null && connectedUsersPostedProbCount.size() > 0)
				{
					for(int i=0;i<connectedUsersPostedProbCount.size();i++)
					{
						Object[] approvedProblemCount = (Object[])connectedUsersPostedProbCount.get(i);
					if(approvedProblemCount[1].toString().equalsIgnoreCase("true"))
						problemVO.setPostedProblemsCountByConnectedUsers((Long)approvedProblemCount[0]);
					}
				}
			}
			
			//List commentsCount = userProblemDAO.getAllPostedProblemCountOtherThanLoggedInUser(userId,connectedUserIds);
			
			List commentsCount = userProblemDAO.getAllPostedProblemCountOtherThanLoggedInUser(userId);
			if(commentsCount != null && commentsCount.size() == 1)
			{
				Long PostedProblemCountBYOtherUser = (Long)commentsCount.get(0) - problemVO.getPostedProblemsCountByConnectedUsers();
				
				problemVO.setPostedProblemsCountByOtherUsers(PostedProblemCountBYOtherUser);				
			}
			problemVO.setTotalPostedProblemsCount(problemVO.getApprovedProblemsCount() + problemVO.getPostedProblemsCountByOtherUsers()+problemVO.getPostedProblemsCountByConnectedUsers());
			problemVO.setPostedProblemsCountByLoggedInUsers(problemVO.getNotConsideredProblemsCount() + problemVO.getRejectedProblemsCount() + problemVO.getApprovedProblemsCount());
		} 
		catch(Exception e)
		{
			e.printStackTrace();
			return problemVO;
		}
		return problemVO;
	}
	
	public String getProblemTableColoumnName(String columnType)
	{
		String dbColumnName = "";
		if(columnType.equalsIgnoreCase("definition"))
			dbColumnName = "model.problem.title";
		else if(columnType.equalsIgnoreCase("description"))
			dbColumnName = "model.problem.description";
		
		else if(columnType.equalsIgnoreCase("existingFrom"))
			dbColumnName = "model.problem.existingFrom";
		else if(columnType.equalsIgnoreCase("identifiedDate"))
			dbColumnName = "model.problem.identifiedOn";
		else if(columnType.equalsIgnoreCase("locationType"))
			dbColumnName = "model.problem.regionScopes.regionScopesId";
		else if(columnType.equalsIgnoreCase("problemId"))
			dbColumnName = "model.problem.problemId";
		return dbColumnName;
		
		
	}
	
	public ProblemBeanVO getAllPostedProblemsByUserId(Long registrationId, Integer startIndex, Integer results, 
			String order, String columnName, String reasonType)
	{
		String dbColoumnName = getProblemTableColoumnName(columnName);
		List<ProblemDetailsVO> problemList = null;
		ProblemBeanVO problemDetailsVO = new ProblemBeanVO();
		Long totalRecords = 0l;
		CompleteProblemDetailsVO problemRating = new CompleteProblemDetailsVO();
		try
		{			
			List<Long> connectedUserIds = getConnectedUserIdsByUserId(registrationId);
			
			List problems = userProblemDAO.getAllPostedProblemsByAnanymousUserId(registrationId, startIndex, results, order, dbColoumnName, reasonType,connectedUserIds);
			totalRecords = userProblemDAO.getAllRecordsCountForPostedProblemsByAnanymousUserId(registrationId, reasonType,connectedUserIds);
			
			if(problems != null && problems.size() > 0 )
			{
				problemList = new ArrayList<ProblemDetailsVO>();
				
				for (int i = 0; i < problems.size(); i++)
				{
					Object[] params = (Object[])problems.get(i);
					ProblemDetailsVO problem= new ProblemDetailsVO(); 
					problem.setProblemID(params[0]!=null?(Long)params[0]:0);
					problem.setDescription(params[2]!=null?params[2].toString():"");
					problem.setIdentifiedDate(params[3]!=null?sdf.format(params[3]):"");
					//problem.setIdentifiedDate(params[3]!=null?params[3].toString():"");
					problemRating = problemManagementService.getAverageRatingOfAProblem((Long)params[0]);
					problem.setAverageRating(problemRating);
					problem.setRating(problemRating.getAvgRating());
					
					if(params[3]!=null)
					problem.setPostedDate((Date)params[3]);
					
					problem.setDefinition(params[1]!=null?params[1].toString():"");
					problem.setExistingFrom(params[4]!=null?sdf.format(params[4]):"");
					//problem.setExistingFrom(params[4]!=null?params[4].toString():"");
					problem.setUserId((Long)params[10]);
					problem.setUserImageURL(params[11] != null && params[11].toString().length() > 0 ? params[11].toString() : "human.jpg");
					problem.setFirstName(params[12].toString());
					problem.setLastName(params[13].toString());
					if(params[7] != null && params[7].toString().equalsIgnoreCase(IConstants.STATE))
					{
						if(params[5] != null)
							problem.setLocation(stateDAO.get(Long.parseLong(params[5].toString())).getStateName() + " State");
						else
							problem.setLocation(" ");
					}
					else if(params[7] != null && params[7].toString().equalsIgnoreCase(IConstants.DISTRICT))
					{
						if(params[5] != null)
							problem.setLocation(districtDAO.get(Long.parseLong(params[5].toString())).getDistrictName() + " District");
						else
							problem.setLocation(" ");
					}						
					else if(params[7] != null && params[7].toString().equalsIgnoreCase(IConstants.CONSTITUENCY))
					{
						if(params[5] != null)
							problem.setLocation(constituencyDAO.get(Long.parseLong(params[5].toString())).getName() + " Constituency");
						else
							problem.setLocation(" ");
						
					}	
					else if(params[7] != null && params[7].toString().equalsIgnoreCase(IConstants.VILLAGE))
					{
						if(params[5] != null)
							problem.setLocation(hamletDAO.get(Long.parseLong(params[5].toString())).getHamletName() + " Village");
						else
							problem.setLocation(" ");
						
					}else if(params[7] != null && (params[7].toString().equalsIgnoreCase(IConstants.MANDAL) || params[7].toString().equalsIgnoreCase(IConstants.TEHSIL)))
					{
						if(params[5] != null)
							problem.setLocation(tehsilDAO.get(Long.parseLong(params[5].toString())).getTehsilName() + " Mandal");
						else
							problem.setLocation(" ");
						
					}else if(params[7] != null && params[7].toString().equalsIgnoreCase(IConstants.WARD))
					{
						if(params[5] != null)
							problem.setLocation(constituencyDAO.get(Long.parseLong(params[5].toString())).getName() + " Ward");
						else
							problem.setLocation(" ");
						
					}
					else if(params[7] != null && params[7].toString().equalsIgnoreCase(IConstants.BOOTH))
					{
						if(params[5] != null)
							problem.setLocation(boothDAO.get(Long.parseLong(params[5].toString())).getPartNo() + " Booth");
						else
							problem.setLocation(" ");
						
					}else if(params[7] != null && params[7].toString().equalsIgnoreCase("MUNICIPAL-CORP-GMC"))
					{
						if(params[5] != null)
							problem.setLocation(localElectionBodyDAO.get(Long.parseLong(params[5].toString())).getName());
						else
							problem.setLocation(" ");
						
					}
					else
						problem.setLocation(" ");
					
					problem.setLocationType(params[7].toString());
					problem.setIsApproved(params[8].toString());
					problem.setProblemHistoryId((Long)params[9]);
					problemList.add(problem);
				}
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		problemDetailsVO.setTotalResultsCount(totalRecords.toString());
		problemDetailsVO.setProblemsInfo(problemList);
		return problemDetailsVO;
		
	}
	
	public ResultStatus saveUserProfileImageName(Long userId,String imageName)
	{
		ResultStatus resultStatus = new ResultStatus();
		int result;
		
		try {
			result = userDAO.saveUserProfileImageNameToDB(userId, imageName); 
			resultStatus.setResultCode(result);			
			
		} catch (Exception e) {
			resultStatus.setExceptionEncountered(e);
			resultStatus.setExceptionMsg("Exception encountered during update : "+e.getMessage());	
			resultStatus.setResultCode(0);
		}
		return resultStatus;
	}
	
	public String getUserProfileImageByUserId(Long userId)
	{
		String imageURL = userDAO.getUserProfileImageNameByUserId(userId);
		if(imageURL == null || imageURL.trim().length() == 0)
			imageURL = "human.jpg";
		return imageURL;
	}
	
	/*
	 * To convert the date that is retrived from DB to dd/MM/yyyy HH:mm:ss
	 */
	public Date getCurrentDateAndTime(){
		try {
		java.util.Date now = new java.util.Date();
        String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        String strDateNew = sdf.format(now);        
			now = sdf.parse(strDateNew);
			return now;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	

public RegistrationVO getUserDetailsToRecoverPassword(String userName){
	
	List detailsList = null;
	RegistrationVO registrationVO=new RegistrationVO();
	try{
		detailsList = userDAO.getUserDetails(userName);
	
		if(detailsList!=null && detailsList.size()!=0){
			Object ananymousUserObj[] =(Object[]) detailsList.get(0);
			registrationVO.setEmail((String)ananymousUserObj[0]);
			
			if(registrationVO.getEmail() != null && registrationVO.getEmail().trim().length() > 0)
			{
				registrationVO.setPassword((String)ananymousUserObj[1]);
				registrationVO.setFirstName((String)ananymousUserObj[2]);
				registrationVO.setLastName((String)ananymousUserObj[3]);
			}
			else
				registrationVO.setEmail(null);
			
			registrationVO.setUserName(userName);
			}
	}catch(Exception e){
		e.printStackTrace();
		return null;
	}
	return registrationVO;
}

public Integer saveUserDetailsToChangeUserNameToEmail(Long userId,String userName){
	
	Integer detailsList = null;
	RegistrationVO registrationVO=new RegistrationVO();
	try{
		detailsList = (Integer) userDAO.saveUserNameTOEmail(userId,userName);
	
		
	}catch(Exception e){
		e.printStackTrace();
		return null;
	}
	return detailsList;
}

public String getPassword(String password){
	Boolean i=true;
	try{
	while(i){
		if(userDAO.getPassword(password) != null)
			return password;
    }
	}catch (Exception e){
		log.debug("Exception Encountered", e);
	}
	return password;
}
@SuppressWarnings("unchecked")
public String changeUserPassword(String crntpassword,String newpassword,Long regId,String userName)
{   
	
	if(regId == 0l)
	{
		List<Object> userId = userDAO.getUserIdByUserName(userName);
		regId = (Long) userId.get(0);
	}
	
	List chkpwd = userDAO.checkUserPassword(crntpassword,regId);
	
	if(chkpwd.size() == 0)
		return IConstants.NoPassword;
	Integer chkPwdVals = userDAO.changeUserPassword(newpassword, regId, IConstants.TRUE, dateUtilService.getCurrentDateAndTime());
	return IConstants.YesPassword;
}



public  ResultStatus saveEmailForAUser(String userName,final String email)
{
	ResultStatus resultStatus = new ResultStatus();
	
	try{
		final List<User> users = userDAO.getUserByUserName(userName);
		
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {
				User user = users.get(0);
				user.setEmail(email);
				userDAO.save(user);
			}
		});
		
		resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		return resultStatus;
		}
	catch(Exception e){
		resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		return resultStatus;
	}
	
}

public  ResultStatus saveEmailAndSetAsUserName(String userName,final String email)
{
	ResultStatus resultStatus = new ResultStatus();
	
	try{
		final List<User> users = userDAO.getUserByUserName(userName);
		
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {
				User user = users.get(0);
				user.setEmail(email);
				user.setUserName(email);
				userDAO.save(user);
			}
		});
		
		resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		return resultStatus;
		}
	catch(Exception e){
		resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		return resultStatus;
	}
	
}

public ResultStatus changeForUserNameAsEmail(String userName){
	ResultStatus resultStatus = new ResultStatus();
	List<User> detailsList = null;
	List<User> detailsListForEmail = null;
	try{
		detailsList = userDAO.checkForUserNameAvailabiity(userName);
		detailsListForEmail=userDAO.checkForUserNameAvailabiityForEmail(userName);
		List<User> users = userDAO.changeUserNameAsEmail(userName);
		User user = users.get(0);
		user.setUserName(userName);
		userDAO.save(user);
		
		if(detailsList!=null && detailsList.size()!=0 && detailsListForEmail!=null && detailsListForEmail.size()!=0){
			
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);	
		}else{
			resultStatus.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
		}
		resultStatus.setResultPartial(false);
	}catch(Exception e){
		resultStatus.setExceptionEncountered(e);
		resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		resultStatus.setResultPartial(true);
	}
	return resultStatus;
}

public void saveMailContacts(Long userId ,List<String> mailsList)
{	
	log.debug("Entered into the saveMailContacts method");
	try	{
		if(mailsList != null && mailsList.size()>0)
		{
			List<String> userEmails = getUserReferencedEmails(userId);
			for(String mail : mailsList)
			{
				if(mail != null && mail.length() > 0 && userEmails != null && !userEmails.contains(mail))
				{
					try
					{
						UserReferralEmails userReferralEmail = new UserReferralEmails();
						
						userReferralEmail.setUserId(userId);
						userReferralEmail.setEmail(mail);
						userReferralEmail.setTime(dateUtilService.getCurrentDateAndTime());
						userReferralEmailsDAO.save(userReferralEmail);
					}catch (Exception e) {}
				}
			}
		}
	}
	catch(Exception e){
		log.error("Exception raised in saveMailContacts");
	}	
}

public List<String> getUserReferencedEmails(Long userId)
{
	try{
		List<String> emailList = userReferralEmailsDAO.getUserReferencedEmails(userId);
		
		if(emailList != null && emailList.size() > 0)
			for(String email : emailList)
				emailList.add(email);
		
		return emailList;
	}catch (Exception e) {
		log.error("Exception Occured in getUserReferencedEmails() Method, Exception is - "+e);
		return null;
	}
}

public Long getUserConstituencyId(Long userId){
	try{
		User user = userDAO.get(userId);
		return user.getConstituency().getConstituencyId();
	}catch(Exception e){
		log.error("Exception Occured in getUserConstituencyId() Method, Exception is - "+e);
	}
	return null;
}

	
public DataTransferVO getAllMessagesForLoggedUser(List<Long> userId,String messageType,Integer startIndex, Integer maxResults)
{
	ResultStatus resultStatus = new ResultStatus();
	List<CandidateVO> candiateVO = new ArrayList<CandidateVO>(0); 
	DataTransferVO dataTransferVO = new DataTransferVO();
	Long totalMsgCount = 0l;
	Long unreadMsgCount= 0l;
	String message,data;
	try{
		final SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		List<Object> result = customMessageDAO.getAllMessagesForUser(userId,messageType, startIndex, maxResults);
		if(result!=null && result.size()!=0){
			List<Object> totalresult = customMessageDAO.getAllMessagesForUser(userId,messageType);
			totalMsgCount = new Long(totalresult.size());
			for(int i=0;i<result.size();i++){
				Object[] parms = (Object[])result.get(i);
				CandidateVO candidateResults = new CandidateVO();
				
				if(parms[0] != null)
					message = staticDataService.removeSpecialCharectersFromString(parms[0].toString());
				else
					message = "";
				
				
				
				data = StringUtils.replace(parms[0].toString(),"\n"," ");
				data = staticDataService.removeSpecialCharectersFromString(data);
				
				if(data.length() < 20)
					candidateResults.setData(data);
				else
					candidateResults.setData(data.substring(0, 19));
				
				candidateResults.setMessage(message);
				candidateResults.setId(new Long(parms[1].toString()));
				String candidateName="";
				
				if(parms[2]!=null)
					candidateName+=parms[2].toString().concat("  ").concat("  ");
				
				if(parms[3]!=null)
					candidateName+=parms[3].toString();
				candidateResults.setState(parms[4].toString());
				candidateResults.setDistrict(parms[5].toString());					
				candidateResults.setConstituencyName(parms[6].toString());
				candidateResults.setCandidateName(candidateName.toUpperCase());
				String status = (parms[8]!=null)?parms[8].toString():"";
				candidateResults.setStatus(status);
				if(IConstants.MSG_UNREAD.equalsIgnoreCase(status))
					unreadMsgCount++;
				
				candidateResults.setRecepientId(parms[10]!=null?(Long)parms[10]:null);
				candidateResults.setPostedDate(parms[9]!=null?dateFormat.format((Date)parms[9]):"");
				candidateResults.setCostumMessageId(parms[7]!=null?(Long)parms[7]:null);
				String profileImg=(parms[11]!=null)?parms[11].toString():"";
				candidateResults.setProfileImg(profileImg);
				candiateVO.add(candidateResults);
			}
		}
		dataTransferVO.setTotalMsgCount(totalMsgCount);
		dataTransferVO.setUnreadMsgCount(unreadMsgCount);
		dataTransferVO.setCandidateVO(candiateVO);
		resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		resultStatus.setResultPartial(false);
		dataTransferVO.setResultStatus(resultStatus);
		}catch(Exception e){
			e.printStackTrace();
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setResultPartial(true);
			dataTransferVO.setResultStatus(resultStatus);	
		}
	return dataTransferVO;
}
//getAllMessagesForUser
public DataTransferVO getAllSentMessagesForLoggedUser(List<Long> userId,String messageType)
{
	ResultStatus resultStatus = new ResultStatus();
	List<CandidateVO> candiateVO = new ArrayList<CandidateVO>(0); 
	DataTransferVO dataTransferVO = new DataTransferVO();
	Long totalMsgCount = 0l;
	Long unreadMsgCount= 0l;
	String message,data;
	try{
		final SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		List<Object> result = customMessageDAO.getAllSentMessagesForUser(userId,messageType);
		if(result!=null && result.size()!=0){
			totalMsgCount = new Long(result.size());
			for(int i=0;i<result.size();i++){
				Object[] parms = (Object[])result.get(i);
				CandidateVO candidateResults = new CandidateVO();
				
				if(parms[0] != null)
					message = staticDataService.removeSpecialCharectersFromString(parms[0].toString());
				else
					message = "";
				
				
				
				data = StringUtils.replace(parms[0].toString(),"\n"," ");
				data = staticDataService.removeSpecialCharectersFromString(data);
				
				if(data.length() < 20)
					candidateResults.setData(data);
				else
					candidateResults.setData(data.substring(0, 19));
				
				candidateResults.setMessage(message);
				candidateResults.setId(new Long(parms[1].toString()));
				String candidateName="";
				
				if(parms[2]!=null)
					candidateName+=parms[2].toString().concat("  ").concat("  ");
				
				if(parms[3]!=null)
					candidateName+=parms[3].toString();
				candidateResults.setState(parms[4].toString());
				candidateResults.setDistrict(parms[5].toString());					
				candidateResults.setConstituencyName(parms[6].toString());
				candidateResults.setCandidateName(candidateName.toUpperCase());
				String status = (parms[8]!=null)?parms[8].toString():"";
				candidateResults.setStatus(status);
				if(IConstants.MSG_UNREAD.equalsIgnoreCase(status))
					unreadMsgCount++;
				
				candidateResults.setRecepientId(parms[10]!=null?(Long)parms[10]:null);
				candidateResults.setPostedDate(parms[9]!=null?dateFormat.format((Date)parms[9]):"");
				candidateResults.setCostumMessageId(parms[7]!=null?(Long)parms[7]:null);
				String profileImg=(parms[11]!=null)?parms[11].toString():"";
				candidateResults.setProfileImg(profileImg);
				candiateVO.add(candidateResults);
			}
		}
		dataTransferVO.setTotalMsgCount(totalMsgCount);
		dataTransferVO.setUnreadMsgCount(unreadMsgCount);
		dataTransferVO.setCandidateVO(candiateVO);
		resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		resultStatus.setResultPartial(false);
		dataTransferVO.setResultStatus(resultStatus);
		}catch(Exception e){
			e.printStackTrace();
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setResultPartial(true);
			dataTransferVO.setResultStatus(resultStatus);	
		}
	return dataTransferVO;
}

/*public List<RegistrationVO> getFriendsListForUserProfile(Long userId)
{
	List<RegistrationVO> friendsDetails = new ArrayList<RegistrationVO>(0);
	
	try{
		
		List<Object[]> list =null;
		List<Object[]> userDetailsList = userConnectedtoDAO.getAllConnectedPeopleForPublicProfile(userId);
		List<Long> userIds = null;
		if(userDetailsList != null && userDetailsList.size()>0)
		{
			Map<Long, Object[]> map = new HashMap<Long, Object[]>(0);
			for(Object[] params: userDetailsList)
			{
				if(params[0] != userId)
				map.put((Long)params[0], params);
			}
			 list = new ArrayList<Object[]>(map.values());
		}
		
		if(list != null && list.size() >0)
		{
			
			for(Object[] friendsList : list)
			{
				userIds = new ArrayList<Long>(0);
				userIds.add((Long)friendsList[0]);
				userIds.add((Long)friendsList[1]);
			}
		     if(userIds != null && userIds.size() > 0)
			  {
		    	 //for(Long userId12 : userIds)
		    	 //{
		    		 
				List<Object[]> userDetails = userDAO.getUserBasicDetailsForProfile(userIds);
				for(Object[] senderDet : userDetails)
				{
				  selectOptionVO = new SelectOptionVO();
				  selectOptionVO.setId((Long)senderDet[0]);
				       selectOptionVO.setName(senderDet[1].toString() +" "+senderDet[2].toString());
					if(senderDet[3] != null)
				       selectOptionVO.setUrl(senderDet[3].toString());
					 else
					 selectOptionVO.setUrl("human.jpg"); 
					 selectOptionVOList.add(selectOptionVO);
							 
					//}
		    	 }
				}
			}
				
				
			
		
		
	}catch (Exception e) {
		e.printStackTrace();
		log.error("Exception Occuted in getFriendsListForUserProfile() Method, Exception - "+e);
	}
	return friendsDetails;
}
*/


public List<RegistrationVO> getFriendsListForUserProfile(Long userId)
{
	List<RegistrationVO> friendsDetails = new ArrayList<RegistrationVO>(0);
	try{
		
		List<Object[]> userDetailsList = userConnectedtoDAO.getAllConnectedPeopleForPublicProfile(userId);

		if(userDetailsList != null && userDetailsList.size() > 0)
		{
			List<Long> userIds = new ArrayList<Long>(0);
			for(Object[] params : userDetailsList)
			{
				Long friendId = (Long)params[0];
				if(!friendId.equals(userId) && !userIds.contains(friendId))
					userIds.add(friendId);
				friendId = (Long)params[1];
				if(!friendId.equals(userId) && !userIds.contains(friendId))
					userIds.add(friendId);
			}
			
			List<Object[]> userDetails = userDAO.getUserBasicDetailsForProfile(userIds);
			
			RegistrationVO registrationVO = null;
			for(Object[] params2 : userDetails)
			{
				registrationVO = new RegistrationVO();
				registrationVO.setRegistrationID((Long)params2[0]);
				registrationVO.setFirstName(params2[1].toString());
				registrationVO.setLastName(params2[2].toString());
				
				if(params2[3] != null && params2[3].toString().length() > 0)
					registrationVO.setUserProfilePic(params2[3].toString());
				else
					registrationVO.setUserProfilePic("member.jpg");
				
				friendsDetails.add(registrationVO);
			}
		}
		return friendsDetails;	
	}catch (Exception e) {
		log.error("Exception Occuted in getFriendsListForUserProfile() Method, Exception - "+e);
		return friendsDetails;
	}
	
}

public List<Long> getConnectedUserIdsByUserId(Long userId)
{
	List<Long> connectedUserIds = null;
	try{
		List<Object[]> connectedUserIdsList = userConnectedtoDAO.getAllConnectedPeopleForPublicProfile(userId);
		if(connectedUserIdsList != null && connectedUserIdsList.size() > 0)
		{
			connectedUserIds = new ArrayList<Long>(0);
			for(Object[] params : connectedUserIdsList)
			{
				Long friendId = (Long)params[0];
				if(!friendId.equals(userId) && !connectedUserIds.contains(friendId))
					connectedUserIds.add(friendId);
				friendId = (Long)params[1];
				if(!friendId.equals(userId) && !connectedUserIds.contains(friendId))
					connectedUserIds.add(friendId);
			}
		}
		return connectedUserIds;
	}catch (Exception e) {
		e.printStackTrace();
		log.error("Exception Occured in getConnectedUserIdsByUserId() method, Exception is- "+e);
		return connectedUserIds;
	}
}



/*public List<ProblemBeanVO> getProblemDetailsForProfilePage(Long registrationId, Integer startIndex, Integer results, 
		String order, String columnName, String reasonType)
{
	List<ProblemBeanVO> problemBeanVOList = null;
	ProblemBeanVO problemDetails = getAllPostedProblemsByUserId(registrationId,startIndex,results,order,columnName,reasonType);
	List<ProblemDetailsVO> problemsInfo = problemDetails.getProblemsInfo();
	
	Map<String, ProblemBeanVO> problemsMapList = new HashMap<String, ProblemBeanVO>(0);
	try{
		if(problemsInfo != null && problemsInfo.size() > 0)
		{
			for(ProblemDetailsVO problems : problemsInfo)
			{
				String key = problems.getIdentifiedDate();
				
				if(problemsMapList.get(key) == null)
				{
					ProblemBeanVO problemBeanVO = new ProblemBeanVO();
					problemBeanVO.setPostedDate(key);
					problemBeanVO.setPostDate(problems.getPostedDate());
					List<ProblemDetailsVO> list = new ArrayList<ProblemDetailsVO>();
					list.add(problems);
					problemBeanVO.setProblemsInfo(list);
					
					problemsMapList.put(key, problemBeanVO);
					
				}
				else
				{
					ProblemBeanVO problemBeanVO = problemsMapList.get(key);
					problemBeanVO.getProblemsInfo().add(problems);
				}
			}
		}
		problemBeanVOList = new ArrayList<ProblemBeanVO>(problemsMapList.values());
		Collections.sort(problemBeanVOList, new Comparator<ProblemBeanVO>() {
		    public int compare(ProblemBeanVO m1, ProblemBeanVO m2) {
		        return m2.getPostDate().compareTo(m1.getPostDate());
		    }
		});
		return problemBeanVOList;
	}catch (Exception e) {
		log.error("Exception Occured in getProblemDetailsForProfilePage() method, Exception- "+e);
		return problemBeanVOList;
	}
}*/

/**
 * This method will fetch all setting options 
 * @param userId
 */
public UserSettingsVO getTotalSettingsOptionsOfAnUser(Long userId){
	
	if(log.isDebugEnabled())
	log.debug("Entered into getTotalSettingsOptions service method");
	
	List<UserSettingsVO> userSettingsList = new ArrayList<UserSettingsVO>();
	List<String> optionsList = new ArrayList<String>();
	UserSettingsVO userSettingsVO = new UserSettingsVO();
	
	try{
		
		
		List<SettingsOption> settingsOptions = settingsOptionDAO.getAllSettingsOptions();
		
		for(SettingsOption selectOption:settingsOptions)
			optionsList.add(selectOption.getOptionName());
		
		
		userSettingsVO.setOptionsList(optionsList);
		
		List<UserPrivacySettings> userSettings = userPrivacySettingsDAO.getUserSavedSettings(userId);
		
		if(userSettings != null && userSettings.size() >0)			
			userSettingsVO.setSelectedOptionId(userSettings.get(0).getSettingsOption().getSettingsOptionId());
		else
			userSettingsVO.setSelectedOptionId(0L);
			
		
		
		//List<Content> conents = contentDAO.getContentTypes();
		
		
		
		
		/*Map<Long , Long> map = new HashMap<Long , Long>();
		Map<Long , String> map1 = new HashMap<Long , String>();
		
		
		for(SettingsOption selectOption:settingsOptions)
			optionsList.add(selectOption.getOptionName());
		
		for(UserPrivacySettings userSetting:userSettings){				
			map.put(userSetting.getContent().getContentId(), userSetting.getSettingsOption().getSettingsOptionId());
			map1.put(userSetting.getContent().getContentId(), userSetting.getSettingsOption().getOptionName());
		}
			
		
		for(Content content:conents){	
			
			UserSettingsVO userSettingsVO = new UserSettingsVO();
			userSettingsVO.setContentName(content.getName());
			userSettingsVO.setOptionsList(optionsList);
			userSettingsVO.setSelectedOptionId(map.get(content.getContentId()));
			userSettingsVO.setSelectedOptionName(map1.get(content.getContentId()));
			
			userSettingsList.add(userSettingsVO);
			
		}*/
	}catch(Exception e){
		log.debug("Exception raised in getTotalSettingsOptions service method");
		e.printStackTrace();
		
	}
	
	return userSettingsVO;
}


public String updateUserSettingsDetailsAction(Long selectedOptionId , Long userId){
	
	try{
	UserPrivacySettings userPrivacySettings = null;
	
	List<UserPrivacySettings> userPrivacySettingsList   = userPrivacySettingsDAO.getUserSavedSettings(userId);
	if(userPrivacySettingsList!=null && userPrivacySettingsList.size()>0){
		userPrivacySettings = userPrivacySettingsList.get(0);

	}
	
	if(userPrivacySettings == null)
		userPrivacySettings = new UserPrivacySettings();
	
	userPrivacySettings.setUser(userDAO.getUserByUserId(userId));
	userPrivacySettings.setContent(contentDAO.get(1L));
	userPrivacySettings.setSettingsOption(settingsOptionDAO.get(selectedOptionId));
	
	userPrivacySettingsDAO.save(userPrivacySettings);
	
	}catch(Exception e){	
	  e.printStackTrace();
	return null;
	}
	
	return IConstants.SUCCESS;	
	
}

public List<UserSettingsVO> getUserFavAllLinksAction(Long userId){
	if(log.isDebugEnabled())
    	log.debug("Entered into getUserFavAllLinksAction service method");
	
	List<UserSettingsVO> userFavoriteLinks = null;
	String constituencyId = null;
	String districtId = null;
	String stateID = null;
	String specialPageId = null;
	String specialPageName =null;
	String stateName = null;
	
	List<Long> constituencyList=new ArrayList<Long>();
	List<Long> districtList=new ArrayList<Long>();
	List<Long> stateList=new ArrayList<Long>();
	List<Long> specialPagesList=new ArrayList<Long>();
	
	Map<String,List<Long>> map= new HashMap<String,List<Long>>();
	
	try{
		List<UserFavoriteLinks>  userFavoriteLinksList = userFavoriteLinksDAO.getUserFavouriteLinksAction(userId);
		if(userFavoriteLinksList != null && userFavoriteLinksList.size() >0)
			userFavoriteLinks = new ArrayList<UserSettingsVO>();
			UserSettingsVO userSettingsVO = new UserSettingsVO();
		
for(UserFavoriteLinks userFavoriteLink:userFavoriteLinksList){
			
			
			userSettingsVO.setFavouriteLinkType(userFavoriteLink.getFavoriteLinkPage().getPage());
			
			if(userSettingsVO.getFavouriteLinkType().equalsIgnoreCase("constituency"))
			{
				String url = userFavoriteLink.getUrl().toString();
			 	constituencyId = url.replaceAll("\\D+","");
				constituencyList.add(Long.valueOf(constituencyId));
			}
			if(userSettingsVO.getFavouriteLinkType().equalsIgnoreCase("district"))
			{
				String url = userFavoriteLink.getUrl().toString();
			 	districtId = url.replaceAll("\\D+","");
			 	districtList.add(Long.valueOf(districtId));
			}
			if(userSettingsVO.getFavouriteLinkType().equalsIgnoreCase("state"))
			{
				String url = userFavoriteLink.getUrl().toString();
				stateID = url.replaceAll("\\D+","");
				stateList.add(Long.valueOf(stateID));
			}
			if(userSettingsVO.getFavouriteLinkType().equalsIgnoreCase("specialpage"))
			{
				String url = userFavoriteLink.getUrl().toString();
				specialPageId = url.replaceAll("\\D+","");
				specialPagesList.add(Long.valueOf(specialPageId));
			}
			//have to add all the list to list
		}
			
			map.put("constituencies", constituencyList);
			map.put("district", districtList);
			map.put("states", stateList);
			map.put("specialPages", specialPagesList);
			
			
			userSettingsVO.setSetFavIdMap(map);
			
			userFavoriteLinks.add(userSettingsVO);
	}
	catch (Exception e) {
		log.debug("Exception raised in getUserFavouriteLinksAction service method");
		e.printStackTrace();
	}
	
	return userFavoriteLinks;
}
public List<UserSettingsVO> getUserFavouriteLinksAction(Long userId){
	
	
    if(log.isDebugEnabled())
    	log.debug("Entered into getUserFavouriteLinksAction service method");
	
	List<UserSettingsVO> userFavoriteLinks = null;
	String constituencyId = null;
	String districtId = null;
	String stateID = null;
	String specialPageId = null;
	String specialPageName =null;
	String stateName = null;
	
	try{
	
	
		List<UserFavoriteLinks>  userFavoriteLinksList = userFavoriteLinksDAO.getUserFavouriteLinksAction(userId);
	
	  if(userFavoriteLinksList != null && userFavoriteLinksList.size() >0)
		userFavoriteLinks = new ArrayList<UserSettingsVO>();
	
		for(UserFavoriteLinks userFavoriteLink:userFavoriteLinksList){
			
			UserSettingsVO userSettingsVO = new UserSettingsVO();
			userSettingsVO.setFavouriteLinkType(userFavoriteLink.getFavoriteLinkPage().getPage());
			
			if(userSettingsVO.getFavouriteLinkType().equalsIgnoreCase("constituency"))
			{
				String url = userFavoriteLink.getUrl().toString();
			 	constituencyId = url.replaceAll("\\D+","");
				List<Object[]> values = constituencyDAO.getConstituencyName(new Long(constituencyId));
				for(Object[] result : values)
				{
			 	userSettingsVO.setName(result[0].toString()+ " "+result[1].toString());
				}
			}
			if(userSettingsVO.getFavouriteLinkType().equalsIgnoreCase("district"))
			{
				String url = userFavoriteLink.getUrl().toString();
			 	districtId = url.replaceAll("\\D+","");
				Object value = districtDAO.getDistrictNameById(new Long(districtId));
				userSettingsVO.setName(value.toString());
			}
			if(userSettingsVO.getFavouriteLinkType().equalsIgnoreCase("state"))
			{
				String url = userFavoriteLink.getUrl().toString();
				stateID = url.replaceAll("\\D+","");
				Object value = stateDAO.getStateName(new Long(stateID));
				stateName = value.toString().substring(1, value.toString().length()-1);
				userSettingsVO.setName(stateName);
			}
			if(userSettingsVO.getFavouriteLinkType().equalsIgnoreCase("specialpage"))
			{
				String url = userFavoriteLink.getUrl().toString();
				specialPageId = url.replaceAll("\\D+","");
				Object value = specialPageDAO.getSpecialPageName(new Long(specialPageId));
				specialPageName = value.toString().substring(1, value.toString().length()-1);
				userSettingsVO.setName(specialPageName);
			}
			
			
			userSettingsVO.setUserFavoriteLinksId(userFavoriteLink.getUserFavoriteLinksId());
			userSettingsVO.setFavouriteLink(userFavoriteLink.getUrl());
			userSettingsVO.setFavouriteLinkTitle(userFavoriteLink.getPageTitle());	
			
			
			userFavoriteLinks.add(userSettingsVO);
		}
	
	}catch(Exception e){
		
		
		log.debug("Exception raised in getUserFavouriteLinksAction service method");
		e.printStackTrace();
		
	}
	
	return userFavoriteLinks;
	
	
}


public String removeFavouriteLink(Long linkId){	
	
	if(log.isDebugEnabled())
		log.debug("Exception raised in removeFavouriteLink service method");	
	
	try{
	
	int count = userFavoriteLinksDAO.deleteUserFavouritelinkById(linkId);
	
	
		if(count >0)	
		    return IConstants.SUCCESS;
		else 
			return null;
	
	}catch(Exception e){
		
		log.error("Exception raised in removeFavouriteLink service method");
		e.printStackTrace();
		return null;
		
	}
	
}

/**
 * This method will save the user favourite link details
 * @param userId
 * @param link
 * @param pageTitle
 */
public String saveUserFavouriteLink(Long userId , String link,String pageTitle, String queryString , String environment){
	
	if(log.isDebugEnabled())
		log.debug("Exception raised in saveUserFavouriteLink service method");
	
	String queryString1 = queryString.replaceAll(",", "&");
	
	queryString1 = queryString1.substring(0, queryString1.length() - 1);
	try{		
		
		List<FavoriteLinkPage> favouriteLinkList = favoriteLinkPageDAO.getFavoriteLinkByActionName(link);		
		
		if(favouriteLinkList != null && favouriteLinkList.size() >0){
			
			FavoriteLinkPage  favoriteLinkPage = favouriteLinkList.get(0);
			
			UserFavoriteLinks  userFavoriteLinks = null;
			
			
			
		
			String finalURL = "";
			if(environment.equalsIgnoreCase("live"))
			 // finalURL = "http://partyanalyst.com/"+link+".action?"+queryString1;
				finalURL = ""+link+".action?"+queryString1;
				
				
			else
			 // finalURL = "http://partyanalyst.com/"+link+".action?"+queryString1;
				finalURL = ""+link+".action?"+queryString1;
			
			List<UserFavoriteLinks> userFavouriteLinkList = userFavoriteLinksDAO.checkForAlreadyExistedOrNot(userId,finalURL);
			
			if(userFavouriteLinkList != null && userFavouriteLinkList.size() >0)				
                return IConstants.SUCCESS;	
			else				
				userFavoriteLinks = new UserFavoriteLinks();
				
			
			userFavoriteLinks.setUser(userDAO.getUserByUserId(userId));
			userFavoriteLinks.setFavoriteLinkPage(favoriteLinkPage);
			
			
			
			 userFavoriteLinks.setUrl(finalURL);
				
			userFavoriteLinks.setPageTitle(pageTitle);
			
			userFavoriteLinksDAO.save(userFavoriteLinks);
			
			return IConstants.SUCCESS;
			
			
		}
		return null;
		
	}catch(Exception e){
		log.error("Exception raised in saveUserFavouriteLink service method");
		e.printStackTrace();
		return null;
		
	}
}
/** This method is used to delete Inboxmsgs,SentBoxmsgs **/
public ResultStatus deleteMessageFromInbox(Long userId,Long senderId,String type,Long customMessageId,String btnName)

{
	ResultStatus resultStatus = new ResultStatus();
	try
	{
		
		if(type.equalsIgnoreCase(IConstants.COMMENTS));
		{
		Long typeId = 5l;
		if(btnName.equalsIgnoreCase("sentBox"))
		customMessageDAO.updateIsSender(userId, senderId, typeId,customMessageId);
		else
		 customMessageDAO.updateIsRecePient(userId, senderId, typeId,customMessageId);
		}
		resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
	}
	catch(Exception e)
	{
		e.printStackTrace();
		resultStatus.setResultCode(ResultCodeMapper.FAILURE);
	}
	return resultStatus;
}

public DataTransferVO getAllConnectedUsersBasedonLocationType(List<Long> locationIds,String locationType,Long retrivalCount,Long loginId,String status,Long startIndex,String nameString){
		ResultStatus resultStatus = new ResultStatus();
		DataTransferVO dataTransferVO = new DataTransferVO();
		List<CandidateVO> candidateDetails = new ArrayList<CandidateVO>();
		List<Object> result = new ArrayList<Object>();		
		List<Long> userIds = new ArrayList<Long>();
		userIds.add(loginId);
		List<Long> connectedAndPendingUserIdsList=new ArrayList<Long>();
		List<Long> constituencyIds = new ArrayList<Long>();
		List<Long> districtIds = new ArrayList<Long>();
		String searchType = null;
		Long count = 0l;
		List<Object[]> users =null;
		
		try{			
			if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY)){
				List list = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesForAListOfParliamentConstituency(locationIds);
				if(list!=null && list.size()!=0){
					for(int i=0; i<list.size(); i++){					
						locationIds.add((Long)list.get(i));
					}
				}
			}
			
			if(status.equalsIgnoreCase(IConstants.ALL))
			{
				result = userDAO.getAllUsersInSelectedLocations(locationIds, locationType,retrivalCount,startIndex,nameString);
			}
			else if(status.equalsIgnoreCase(IConstants.CONNECTED))
			{
				result = userConnectedtoDAO.getConnectedUsersInSelectedLocations(loginId, locationIds, locationType,retrivalCount,startIndex,nameString);
			}
			else if(status.equalsIgnoreCase(IConstants.PENDING))
			{
				result = customMessageDAO.getPendingUsersInSelectedLocations(loginId, locationIds, locationType,retrivalCount,startIndex,nameString);
			}
			else if(status.equalsIgnoreCase(IConstants.NOTCONNECTED))
			{
				List<Long> connectedIdsList = userConnectedtoDAO.getConnectedUserIdsInSelectedLocations(loginId, locationIds, locationType);
				List<Long> pendingIdsList = customMessageDAO.getPendingUserIdsInSelectedLocations(loginId, locationIds, locationType);
				
				connectedAndPendingUserIdsList = new ArrayList<Long>();
				if(connectedIdsList != null && connectedIdsList.size() > 0)
					connectedAndPendingUserIdsList.addAll(connectedIdsList);
				if(pendingIdsList != null && pendingIdsList.size() > 0)
					connectedAndPendingUserIdsList.addAll(pendingIdsList);
				
				result = userDAO.getNotConnectedUsersInSelectedLocations(loginId, locationIds, locationType, connectedAndPendingUserIdsList, retrivalCount, startIndex, nameString);
			}
			
			candidateDetails = setFriendsListForAUser(result,loginId,status,null);
			dataTransferVO.setCandidateVO(candidateDetails);
			
			dataTransferVO.setTotalResultsCount(getAllUsersCountInSelectedLocationsInFilterView(loginId, locationIds, locationType, status, nameString, connectedAndPendingUserIdsList).toString());
			dataTransferVO.setConstituencyUsersCount(userDAO.getAllUsersCountInSelectedLocations(locationIds,locationType,nameString).toString());
			List userLocations = userDAO.getAnanymousUserLocationDetailsByIds(userIds);
			if(userLocations !=null && userLocations.size()>0){
				Object[] values = (Object[])userLocations.get(0);
				Long districtId = (Long)values[2];
				Long constiId = (Long)values[4];
				districtIds.add(districtId);
				constituencyIds.add(constiId);
				}
				searchType = "NOTSAME";
			if(locationIds.size()==1){
				if(locationIds.containsAll(constituencyIds) || locationIds.containsAll(districtIds))
					searchType = "SAME";
					users = userConnectedtoDAO.getCountOfAllConnectedPeopleForUserByDistrict(userIds,locationIds,locationType,searchType);				
			}			
			else{
				if(constituencyIds != null && locationIds.containsAll(constituencyIds)){
				users = userConnectedtoDAO.getCountOfAllConnectedPeopleForUserInSameLocation(userIds,locationIds,searchType);
				}
				else{
					users = userConnectedtoDAO.getCountOfAllConnectedPeopleForUserByDistrict(userIds,locationIds,locationType,searchType);
				}
			}
			count = (long) users.size();
			dataTransferVO.setConnectedPeopleCount(count.toString());
			
			resultStatus.setResultPartial(false);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			dataTransferVO.setResultStatus(resultStatus);	
		}catch(Exception e){
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setResultPartial(true);
			dataTransferVO.setResultStatus(resultStatus);	
		}
	return dataTransferVO;
	} 
}