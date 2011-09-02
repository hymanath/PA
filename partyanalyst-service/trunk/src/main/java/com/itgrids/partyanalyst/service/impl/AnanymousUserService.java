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
import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAnanymousUserDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.ICommentCategoryCandidateDAO;
import com.itgrids.partyanalyst.dao.ICommentDataCategoryDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.ICustomMessageDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IMessageTypeDAO;
import com.itgrids.partyanalyst.dao.IProblemHistoryDAO;
import com.itgrids.partyanalyst.dao.IProfileOptsDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserConnectedtoDAO;
import com.itgrids.partyanalyst.dao.IUserProfileOptsDAO;
import com.itgrids.partyanalyst.dto.CandidateCommentsVO;
import com.itgrids.partyanalyst.dto.CandidateVO;
import com.itgrids.partyanalyst.dto.DataTransferVO;
import com.itgrids.partyanalyst.dto.NavigationVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.ProblemDetailsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.UserCommentsInfoVO;
import com.itgrids.partyanalyst.model.AnanymousUser;
import com.itgrids.partyanalyst.model.CommentData;
import com.itgrids.partyanalyst.model.CustomMessage;
import com.itgrids.partyanalyst.model.MessageType;
import com.itgrids.partyanalyst.model.ProfileOpts;
import com.itgrids.partyanalyst.model.UserConnectedto;
import com.itgrids.partyanalyst.model.UserProfileOpts;
import com.itgrids.partyanalyst.service.IAnanymousUserService;
import com.itgrids.partyanalyst.service.IDateService;
import com.itgrids.partyanalyst.service.IStaticDataService;
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
	private IAnanymousUserDAO ananymousUserDAO;
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
	private  MailService mailService;
	
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

	public IAnanymousUserDAO getAnanymousUserDAO() {
		return ananymousUserDAO;
	}

	public void setAnanymousUserDAO(IAnanymousUserDAO ananymousUserDAO) {
		this.ananymousUserDAO = ananymousUserDAO;
	}
	
	
	public IProfileOptsDAO getProfileOptsDAO() {
		return profileOptsDAO;
	}

	public void setProfileOptsDAO(IProfileOptsDAO profileOptsDAO) {
		this.profileOptsDAO = profileOptsDAO;
	}

	/**
	 * This method can be used for saving of Anonymous User details.
	 * 
	 * @author Ravi Kiran.Y 
	 * @param RegistrationVO
	 * @return RegistrationVO
	 */
	public Boolean saveAnonymousUserDetails(final RegistrationVO userDetails, final Boolean isUpdate){
		
		AnanymousUser ananymousUserReturn = (AnanymousUser)transactionTemplate.execute(new TransactionCallback() {
			
			public Object doInTransaction(TransactionStatus status) {	
				AnanymousUser ananymousUser = null;
				ProfileOpts profileOpts = null;
				String imageName =null;
				if(!isUpdate)
					ananymousUser = new AnanymousUser();
				else{
					ananymousUser = ananymousUserDAO.get(userDetails.getRegistrationID());
					userProfileOptsDAO.removeOptsOfExistingUser(userDetails.getRegistrationID());
				}
				
				try{
					if(!isUpdate){
						ananymousUser.setUsername(userDetails.getEmail());
						ananymousUser.setPassword(userDetails.getPassword());
						ananymousUser.setRegisteredDate(getCurrentDateAndTime());
					}
					
					
					ananymousUser.setName(userDetails.getFirstName());
					ananymousUser.setLastName(userDetails.getLastName());
					ananymousUser.setGender(userDetails.getGender());
					SimpleDateFormat format = new SimpleDateFormat(IConstants.DATE_PATTERN);
					if(userDetails.getDateOfBirth()!=null)
						if(!("".equalsIgnoreCase(userDetails.getDateOfBirth())))
					{
					Date date = format.parse(userDetails.getDateOfBirth());
					ananymousUser.setDateofbirth(date);
					}
					ananymousUser.setEmail(userDetails.getEmail());
					ananymousUser.setMobile(userDetails.getMobile());
					ananymousUser.setAddress(userDetails.getAddress());
					ananymousUser.setState(stateDAO.get(new Long(userDetails.getState())));
					ananymousUser.setConstituency(constituencyDAO.get(new Long(userDetails.getConstituency())));
					ananymousUser.setDistrict(constituencyDAO.get(new Long(userDetails.getConstituency())).getDistrict());
					ananymousUser.setUpdatedDate(getCurrentDateAndTime());		
					
					
					
					if(isUpdate && userDetails.getUserProfilePic()!=null)
					{					
						ananymousUser.setProfileImg(userDetails.getUserProfilePic());
					}
					
				ananymousUser = ananymousUserDAO.save(ananymousUser);
					
			if(!isUpdate)
					{
				 // If the user does'nt select the profile image
				        if(userDetails.getUserProfilePic()!=null)
			           {
						String constiName[] = userDetails.getUserProfilePic().split("/");
					
						imageName = ananymousUser.getUserId()+"."+constiName[1];
			            }
						userDetails.setRegistrationID(ananymousUser.getUserId());
						 
						ResultStatus imgStatus = saveUserProfileImageName(ananymousUser.getUserId(), imageName);
				        
					}
					
					if(userDetails.getProfileOpts() != null)
					for(Long optsId:userDetails.getProfileOpts()){
						profileOpts = profileOptsDAO.get(optsId);
						userProfileOptsDAO.save(new UserProfileOpts(ananymousUser, profileOpts));
					}
					
				}catch(Exception e){
					e.printStackTrace();
					status.setRollbackOnly();
				}
			 return ananymousUser;
			}
		});
		
		if(ananymousUserReturn != null && ananymousUserReturn.getUserId() != null)
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
		List<AnanymousUser> detailsList = null;	
		ResultStatus resultStatus = new ResultStatus();		
		try{
			userDetails = new RegistrationVO();
			detailsList = ananymousUserDAO.checkAnonymousUserLogin(anonymousUserId,password);
			if(detailsList.size()!=0){
				for(AnanymousUser resultIterator : detailsList){
					userDetails.setUserName(resultIterator.getUsername());
					userDetails.setPassword(resultIterator.getPassword());
					userDetails.setName(resultIterator.getName());
					userDetails.setGender(resultIterator.getGender());
					userDetails.setDateOfBirth(resultIterator.getDateofbirth().toString());
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
		List<AnanymousUser> detailsList = null;
		try{
			detailsList = ananymousUserDAO.checkForUserNameAvailabiity(userName);
			if(detailsList!=null && detailsList.size()!=0){
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
			result = ananymousUserDAO.getAllUsersInSelectedLocations(locationIds, locationType,retrivalCount,startIndex,nameString);	
			candidateDetails = setFriendsListForAUser(result,loginId,status);		
			dataTransferVO.setCandidateVO(candidateDetails);
			dataTransferVO.setTotalResultsCount(ananymousUserDAO.getAllUsersCountInSelectedLocations(locationIds, locationType));
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
			List<Long> senderId,final List<Long> recipeintId,final String messageType,final String subject){
		ResultStatus resultStatus = new ResultStatus();
		ResultStatus resultStatusForSaving = new ResultStatus();
		DataTransferVO dataTransferVO = new DataTransferVO();
		List<CandidateVO> candidateDetails = new ArrayList<CandidateVO>();
		List<Object> result = new ArrayList<Object>();		
		Long startIndex = 0L;
		String nameString = "";
		List<Long> userIds = new ArrayList<Long>();
		try{
			resultStatusForSaving = saveCommunicationDataBetweenUsers(senderId,recipeintId,messageType,subject);
			dataTransferVO.setResultStatusForComments(resultStatusForSaving);
			userIds.add(loginId);
			result = ananymousUserDAO.getAllUsersInSelectedLocations(locationIds, locationType,retrivalCount,startIndex,nameString);			
			candidateDetails = setFriendsListForAUser(result,loginId,IConstants.ALL);		
			dataTransferVO.setCandidateVO(candidateDetails);
			
			dataTransferVO.setTotalResultsCount(ananymousUserDAO.getAllUsersCountInSelectedLocations(locationIds, locationType));
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

	/**
	 * This method can be used by other methods to populate or set data into a data transfer object which contains the list of 
	 * registered users.
	 * @author Ravi Kiran.Y
	 * @version 1.0,13/10/2010	
	 */
	public List<CandidateVO> setFriendsListForAUser(List result,Long loginId,String status){
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
					candidateVO.setStatus(IConstants.NOTCONNECTED);
					candidateVO.setConstituencyId(new Long(parms[4].toString()));
					candidateVO.setConstituencyName(parms[3].toString());
					
					if(parms[5] != null){
						
						AnanymousUser ananyUser = (AnanymousUser)parms[5];
						if(ananyUser.getProfileImg() != null && !ananyUser.getProfileImg().equals(""))
							candidateVO.setImage(ananyUser.getProfileImg());
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
					List<Object> details = customMessageDAO.getRelationShipBetweenTheUsers(candidates,loginId,status);	
					for(int i=0;i<details.size();i++){
						Object[] parms = (Object[])details.get(i);				
						Long userId = new Long(parms[0].toString());			
						if(userIdAndRelationShipWithLogedUser.get(userId)!=null){
							userIdAndRelationShipWithLogedUser.get(userId).setStatus(parms[1].toString());	
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
							userIds.add(userConnectedto.getSenderId().getUserId());
							userIds.add(userConnectedto.getRecepientId().getUserId());
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
	public ResultStatus saveCommunicationDataBetweenUsers(final List<Long> senderId,final List<Long> recipeintId,final String messageType,final String subject){
		final ResultStatus resultStatus = new ResultStatus();	
		transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus status) {
				CustomMessage customMessage = new CustomMessage();
				try{
						Long messageTypeId=0l;
						Long pendingId=0l;
						Long disconectedId=0l;
						Long blockId=0l;
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
								customMessage.setSenderId(ananymousUserDAO.get(senderId.get(0)));
								customMessage.setRecepientId(ananymousUserDAO.get(recipeintId.get(i)));
								customMessage.setMessageType(messageTypeDAO.get(pendingId));
								customMessage.setSentDate(dateService.getPresentPreviousAndCurrentDayDate(IConstants.DATE_PATTERN,0,IConstants.PRESENT_DAY));					
								customMessageDAO.save(customMessage);
							}
						}
						
						if(messageType.equalsIgnoreCase(IConstants.BLOCK)){	
							int result = customMessageDAO.updateRelationBetweenUsers(senderId,recipeintId,blockId,dateService.getPresentPreviousAndCurrentDayDate(IConstants.DATE_PATTERN,0,IConstants.PRESENT_DAY));						
						}
						
						else if(messageType.equalsIgnoreCase(IConstants.CONNECTED)){
							UserConnectedto userConnectedto = new UserConnectedto();
							for(int i=0;i<recipeintId.size();i++){
								userConnectedto.setRecepientId(ananymousUserDAO.get(recipeintId.get(i)));
								userConnectedto.setSenderId(ananymousUserDAO.get(senderId.get(0)));		
								userConnectedtoDAO.save(userConnectedto);
							}
							List<CustomMessage> result = customMessageDAO.checkForRelationBetweenUsersBasedOnType(senderId,recipeintId,IConstants.PENDING);
							for(CustomMessage users : result){
								users.setMessageType(messageTypeDAO.get(messageTypeId));
								users.setSentDate(dateService.getPresentPreviousAndCurrentDayDate(IConstants.DATE_TIME_PATTERN,0,IConstants.PRESENT_DAY));
								customMessageDAO.save(users);
							}
						}
						
						else if(messageType.equalsIgnoreCase(IConstants.COMMENTS) || messageType.equalsIgnoreCase(IConstants.SCRAP)){
							for(int i=0;i<recipeintId.size();i++){
								customMessage.setSubject(subject);
								customMessage.setRecepientId(ananymousUserDAO.get(recipeintId.get(i)));
								customMessage.setSenderId(ananymousUserDAO.get(senderId.get(0)));
								customMessage.setMessageType(messageTypeDAO.get(messageTypeId));
								customMessage.setSentDate(dateService.getPresentPreviousAndCurrentDayDate(IConstants.DATE_TIME_PATTERN,0,IConstants.PRESENT_DAY));					
								customMessage.setStatus(IConstants.MSG_UNREAD);
								customMessageDAO.save(customMessage);
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
			List details = ananymousUserDAO.getAnanymousUserLocationDetailsByIds(userId);
			if(details.size() == 1)
			{				
				params = (Object[]) details.get(0);
				
				dataTransferVO.setStateId((Long)params[0]);
				dataTransferVO.setStateName(params[1].toString());
				dataTransferVO.setDistrictId((Long)params[2]);
				dataTransferVO.setDistrictName(params[3].toString());
				dataTransferVO.setConstituencyId((Long)params[4]);
				dataTransferVO.setConstituencyName(params[5].toString());				
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
				List constCount = ananymousUserDAO.getConnectedUsersCount(dataTransferVO.getConstituencyId(), IConstants.CONSTITUENCY);
				if(constCount.size() == 1)
				{
					dataTransferVO.setConstituencyUsersCount(constCount.get(0).toString());					
				}
				
			}
			
			if(dataTransferVO.getDistrictId() != null)
			{
				List distCount = ananymousUserDAO.getConnectedUsersCount(dataTransferVO.getDistrictId(), IConstants.DISTRICT);
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
					Long id = users.getRecepientId().getUserId();
					if(newList.contains(id)){
						newList.remove(id);
					}
				}	
				customMessageresult = null;
			}
			if(newList!=null && newList.size()!=0){				
				List<AnanymousUser> result = ananymousUserDAO.getDetailsForUsers(newList);
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
	public DataTransferVO setUserProfileData(List<AnanymousUser> result,String informationStatus){		
		ResultStatus resultStatus = new ResultStatus();
		List<CandidateVO> candiateVO = new ArrayList<CandidateVO>(0); 
		DataTransferVO dataTransferVO = new DataTransferVO();
		try{
			if(result!=null && result.size()!=0){
				if(informationStatus.equalsIgnoreCase(IConstants.COMPLETE_DETAILS)){
					for(AnanymousUser details : result){
						CandidateVO candidateResults = new CandidateVO();
						String candidateName = " ";
						String name = details.getName();
						if(name!=null){
							candidateName+=name;
						}
						name = details.getLastName();
						if(name!=null){
							candidateName+=name;
						}						
						candidateResults.setCandidateName(candidateName);
						candidateResults.setId(details.getUserId());	
						candidateResults.setState(details.getState().getStateName());
						candidateResults.setDistrict(details.getDistrict().getDistrictName());						
						candidateResults.setConstituencyName(details.getConstituency().getName());
						candiateVO.add(candidateResults);
					}
				}else{
					for(AnanymousUser details : result){
						CandidateVO candidateResults = new CandidateVO();
						String candidateName = null;
						String name = details.getName();
						if(name!=null){
							candidateName+=name;
						}
						name = details.getLastName();
						if(name!=null){
							candidateName+=name;
						}
						candidateResults.setCandidateName(candidateName);
						candidateResults.setId(details.getUserId());					
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
					
					/*message = StringUtils.replace(parms[0].toString(),"\n",IConstants.HTMLENTER);
					message = StringUtils.replace(message,"'",IConstants.HTMLSINGLEQUOTE);
					message = StringUtils.replace(message,"\"",IConstants.HTMLDOUBLEQUOTES);*/
					
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
	
	public RegistrationVO getDetailsOfUserByUserId(Long registrationId){
		RegistrationVO registrationVO = new RegistrationVO();
		AnanymousUser registration = ananymousUserDAO.get(registrationId);
		List<Long> userOpts = new ArrayList<Long>();
		try{
			
			registrationVO.setRegistrationID(registrationId);
			registrationVO.setUserName(registration.getUsername());
			registrationVO.setPassword(registration.getPassword());
			registrationVO.setGender(registration.getGender());
			registrationVO.setEmail(registration.getEmail());
			registrationVO.setPhone(registration.getPhone());
			registrationVO.setMobile(registration.getMobile());
			registrationVO.setAddress(registration.getAddress());
			if(registration.getDateofbirth()!=null)
			{
				registrationVO.setDateOfBirth(DateService.timeStampConversionToDDMMYY(registration.getDateofbirth().toString()));
			}
			registrationVO.setFirstName(registration.getName());
			registrationVO.setLastName(registration.getLastName());
			registrationVO.setState(registration.getState().getStateId().toString());
			registrationVO.setConstituency(registration.getConstituency().getConstituencyId().toString());
			registrationVO.setPincode(registration.getPincode());
			
			for(UserProfileOpts userOptsModel:registration.getUserProfileOptses())
				userOpts.add(userOptsModel.getProfileOpts().getProfileOptsId());
			
			registrationVO.setProfileOpts(userOpts);

		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return registrationVO;
	}
	
	public CandidateCommentsVO getAllPostedReasonsCountByUserId(Long registrationId)
	{
		CandidateCommentsVO commentsVO = new CandidateCommentsVO();
		commentsVO.setApprovedReasonsCount(0L);
		commentsVO.setRejectedReasonsCount(0L);
		commentsVO.setNotConsideredReasonsCount(0L);
		commentsVO.setTotalPostedReasonsCount(0L);
		
		try 
		{	
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
			
			List commentsCount = commentCategoryCandidateDAO.getPostedReasonsCountOtherThanLoginUserId(registrationId);
			
			if(commentsCount != null && commentsCount.size() == 1)
			{
				commentsVO.setPostedReasonsCountByOtherUsers((Long)commentsCount.get(0));				
			}
			
			commentsVO.setTotalPostedReasonsCount(commentsVO.getApprovedReasonsCount()+ commentsVO.getRejectedReasonsCount() + commentsVO.getNotConsideredReasonsCount() + commentsVO.getPostedReasonsCountByOtherUsers());
			
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
			List comments = commentCategoryCandidateDAO.getPostedReasonsByFreeUserId(registrationId, startIndex, results, order, columnInModel, reasonType);
			
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
					comment.setCommentedOn(commentData.getCommentDate().toString());
					comment.setCommentedBy(commentData.getCommentBy());
					comment.setIsApproved(commentData.getIsApproved());
					comment.setCandidateId((Long)params[1]);
					comment.setCandidate(params[2].toString());
					comment.setPartyName(params[3].toString());
					comment.setConstituencyName(params[4].toString());
					comment.setRank((Long)params[5]);
					comment.setElectionType(params[6].toString());
					comment.setElectionYear(params[7].toString());		
					
					//comment.setCommentCategory(commentDataCategoryDAO.get(commentData.getCommentDataCategory().getCommentDataCategoryId()).getCommentDataCategoryType());
					comment.setCommentCategory(commentData.getCommentDataCategory().getCommentDataCategoryType());
					
					commentsList.add(comment);					
				}
			}	
			
			userComments.setCandidateComments(commentsList);
			
			if(reasonType.equals(IConstants.TOTAL) || reasonType.equals(IConstants.OTHERUSERS))
				userComments.setTotalResultsCount(commentCategoryCandidateDAO.getTotalPostedReasonsCountByFreeUserId());
			else
				userComments.setTotalResultsCount(commentCategoryCandidateDAO.getTotalPostedReasonsCountByFreeUserId(registrationId));
			
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
	
	
	public ProblemDetailsVO getPostedProblemsCount(Long registrationId)
	{
		ProblemDetailsVO problemVO = new ProblemDetailsVO() ;
		problemVO.setNotConsideredProblemsCount(0L);
		problemVO.setApprovedProblemsCount(0L);
		problemVO.setRejectedProblemsCount(0L);
		problemVO.setPostedProblemsCountByOtherUsers(0L);
		problemVO.setTotalPostedProblemsCount(0L);
		problemVO.setPostedProblemsCountByLoggedInUsers(0L);
		
		try {
			List problems = problemHistoryDAO.getAllPostedProblemCount(registrationId);
			
			if(problems != null && problems.size() > 0)
			{
				for (int i = 0; i < problems.size(); i++) {
					Object[] params = (Object[])problems.get(i);
					
					if(params[1].toString().equalsIgnoreCase("false"))
						problemVO.setNotConsideredProblemsCount(((Long)params[0]));					
					else if(params[1].toString().equalsIgnoreCase("true"))
						problemVO.setApprovedProblemsCount((Long)params[0]);
					else 
						problemVO.setRejectedProblemsCount((Long)params[0]);
				}	
				
			}
				
				List commentsCount = problemHistoryDAO.getAllPostedProblemCountOtherThanLoggedInUser(registrationId);
				
				if(commentsCount != null && commentsCount.size() == 1)
				{
					problemVO.setPostedProblemsCountByOtherUsers((Long)commentsCount.get(0));				
				}
				problemVO.setTotalPostedProblemsCount(problemVO.getNotConsideredProblemsCount() + problemVO.getRejectedProblemsCount() + problemVO.getApprovedProblemsCount() + problemVO.getPostedProblemsCountByOtherUsers());
				problemVO.setPostedProblemsCountByLoggedInUsers(problemVO.getNotConsideredProblemsCount() + problemVO.getRejectedProblemsCount() + problemVO.getApprovedProblemsCount());
			} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return problemVO;
	}
	
	
	public String getProblemTableColoumnName(String coloumnType)
	{
		String dbColoumnName = "";
		
		if(coloumnType.equalsIgnoreCase("definition"))
			dbColoumnName = "model.problemLocation.problemAndProblemSource.problem.problem";
		else if(coloumnType.equalsIgnoreCase("description"))
			dbColoumnName = "model.problemLocation.problemAndProblemSource.problem.description";
		else if(coloumnType.equalsIgnoreCase("existingFrom"))
			dbColoumnName = "model.problemLocation.problemAndProblemSource.problem.existingFrom";
		else if(coloumnType.equalsIgnoreCase("identifiedDate"))
			dbColoumnName = "model.problemLocation.problemAndProblemSource.problem.identifiedOn";
		else if(coloumnType.equalsIgnoreCase("locationType"))
			dbColoumnName = "model.problemLocation.problemImpactLevelValue";
		
		return dbColoumnName;
	}
	public ProblemBeanVO getAllPostedProblemsByUserId(Long registrationId, Integer startIndex, Integer results, 
			String order, String columnName, String reasonType)
	{
		String dbColoumnName = getProblemTableColoumnName(columnName);
		List<ProblemDetailsVO> problemList = null;
		ProblemBeanVO problemDetailsVO = new ProblemBeanVO();
		Long totalRecords = 0l;
		/*
		 * Problem
				
			Description
				
			Existing From
				
			Posted On
				
			Location
				
			Location Type*/
		try
		{			
			List problems = problemHistoryDAO.getAllPostedProblemsByAnanymousUserId(registrationId, startIndex, results, order, dbColoumnName, reasonType);
			totalRecords = problemHistoryDAO.getAllRecordsCountForPostedProblemsByAnanymousUserId(registrationId, reasonType);
			
			if(problems != null && problems.size() > 0 )
			{
				problemList = new ArrayList<ProblemDetailsVO>();
				
				for (int i = 0; i < problems.size(); i++)
				{
					Object[] params = (Object[])problems.get(i);
					ProblemDetailsVO problem = new ProblemDetailsVO();
					problem.setProblemID(params[0] != null?(Long)params[0]:0);
					problem.setDescription(params[1] != null?params[1].toString():"");
					problem.setIdentifiedDate(params[2] != null?params[2].toString():"");
					problem.setYear(params[3] != null?params[3].toString():"");
					problem.setDefinition(params[4] != null?params[4].toString():"");
					problem.setExistingFrom(params[5] != null?params[5].toString():"");
					
					if(params[7] != null && params[7].toString().equalsIgnoreCase(IConstants.STATE))
					{
						if(params[6] != null)
							problem.setLocation(stateDAO.get(Long.parseLong(params[6].toString())).getStateName() + " State");
						else
							problem.setLocation(" ");
					}
					else if(params[7] != null && params[7].toString().equalsIgnoreCase(IConstants.DISTRICT))
					{
						if(params[6] != null)
							problem.setLocation(districtDAO.get(Long.parseLong(params[6].toString())).getDistrictName() + " District");
						else
							problem.setLocation(" ");
					}						
					else if(params[7] != null && params[7].toString().equalsIgnoreCase(IConstants.CONSTITUENCY))
					{
						if(params[6] != null)
							problem.setLocation(constituencyDAO.get(Long.parseLong(params[6].toString())).getName() + " Constituency");
						else
							problem.setLocation(" ");
						
					}	
					else if(params[7] != null && params[7].toString().equalsIgnoreCase(IConstants.VILLAGE))
					{
						if(params[6] != null)
							problem.setLocation(hamletDAO.get(Long.parseLong(params[6].toString())).getHamletName() + " Village");
						else
							problem.setLocation(" ");
						
					}else if(params[7] != null && (params[7].toString().equalsIgnoreCase(IConstants.MANDAL) || params[7].toString().equalsIgnoreCase(IConstants.TEHSIL)))
					{
						if(params[6] != null)
							problem.setLocation(tehsilDAO.get(Long.parseLong(params[6].toString())).getTehsilName() + " Mandal");
						else
							problem.setLocation(" ");
						
					}else if(params[7] != null && params[7].toString().equalsIgnoreCase(IConstants.WARD))
					{
						if(params[6] != null)
							problem.setLocation(constituencyDAO.get(Long.parseLong(params[6].toString())).getName() + " Ward");
						else
							problem.setLocation(" ");
						
					}
					else if(params[7] != null && params[7].toString().equalsIgnoreCase(IConstants.BOOTH))
					{
						if(params[6] != null)
							problem.setLocation(boothDAO.get(Long.parseLong(params[6].toString())).getPartNo() + " Booth");
						else
							problem.setLocation(" ");
						
					}else if(params[7] != null && params[7].toString().equalsIgnoreCase("MUNICIPAL-CORP-GMC"))
					{
						if(params[6] != null)
							problem.setLocation(localElectionBodyDAO.get(Long.parseLong(params[6].toString())).getName());
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
			result = ananymousUserDAO.saveUserProfileImageNameToDB(userId, imageName);
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
		String imageURL = "";
		List params = ananymousUserDAO.getUserProfileImageNameByUserId(userId);
		if(params != null && params.size() == 1)
		{
			if(params.get(0) != null)
				imageURL = params.get(0).toString();
			else
				imageURL = "";
		}
		
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
		detailsList = ananymousUserDAO.getUserDetails(userName);
	
		if(detailsList!=null && detailsList.size()!=0){
			Object ananymousUserObj[] =(Object[]) detailsList.get(0);
			registrationVO.setEmail((String)ananymousUserObj[0]);
			registrationVO.setPassword((String)ananymousUserObj[1]);
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
		detailsList = (Integer) ananymousUserDAO.saveUserNameTOEmail(userId,userName);
	
		
	}catch(Exception e){
		e.printStackTrace();
		return null;
	}
	return detailsList;
}



}
