package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAnanymousUserDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.ICustomMessageDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IMessageTypeDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.IUserConnectedtoDAO;
import com.itgrids.partyanalyst.dto.CandidateVO;
import com.itgrids.partyanalyst.dto.DataTransferVO;
import com.itgrids.partyanalyst.dto.NavigationVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.AnanymousUser;
import com.itgrids.partyanalyst.model.CustomMessage;
import com.itgrids.partyanalyst.model.MessageType;
import com.itgrids.partyanalyst.model.UserConnectedto;
import com.itgrids.partyanalyst.service.IAnanymousUserService;
import com.itgrids.partyanalyst.service.IDateService;
import com.itgrids.partyanalyst.utils.IConstants;

public class AnanymousUserService implements IAnanymousUserService {

	
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

	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
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
	
	
	/**
	 * This method can be used for saving of Anonymous User details.
	 * 
	 * @author Ravi Kiran.Y 
	 * @param RegistrationVO
	 * @return RegistrationVO
	 */
	public Boolean saveAnonymousUserDetails(final RegistrationVO userDetails){
		
		AnanymousUser ananymousUserReturn = (AnanymousUser)transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus status) {	
				AnanymousUser ananymousUser = new AnanymousUser();
				try{
				ananymousUser.setName(userDetails.getFirstName());
				ananymousUser.setLastName(userDetails.getLastName());
				ananymousUser.setUsername(userDetails.getUserName());
				ananymousUser.setPassword(userDetails.getPassword());
				ananymousUser.setGender(userDetails.getGender());
				SimpleDateFormat format = new SimpleDateFormat(IConstants.DATE_PATTERN);
				Date date =null;
				date= format.parse(userDetails.getDateOfBirth());
				
				ananymousUser.setDateofbirth(date);
				ananymousUser.setEmail(userDetails.getEmail());
				ananymousUser.setMobile(userDetails.getMobile());
				ananymousUser.setPhone(userDetails.getPhone());
				ananymousUser.setAddress(userDetails.getAddress());
				//userDetails.getCountry()
				ananymousUser.setState(stateDAO.get(new Long(userDetails.getState())));
				ananymousUser.setDistrict(districtDAO.get(new Long(userDetails.getDistrict())));
				ananymousUser.setConstituency(constituencyDAO.get(new Long(userDetails.getConstituency())));
				ananymousUser.setPincode(userDetails.getPincode());
				ananymousUser = ananymousUserDAO.save(ananymousUser);
				 
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
	public DataTransferVO getAllRegisteredAnonymousUserBasedOnLocation(List<Long> locationIds,String locationType,Long retrivalCount,Long loginId,String status){
		ResultStatus resultStatus = new ResultStatus();
		DataTransferVO dataTransferVO = new DataTransferVO();
		List<CandidateVO> candidateDetails = new ArrayList<CandidateVO>();
		List<Object> result = new ArrayList<Object>();		
		
		try{
			
			if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY)){
				List list = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesForAListOfParliamentConstituency(locationIds);
				if(list!=null && list.size()!=0){
					for(int i=0; i<list.size(); i++){					
						locationIds.add((Long)list.get(i));
					}
				}
				
			}
			result = ananymousUserDAO.getAllUsersInSelectedLocations(locationIds, locationType,retrivalCount);	
			candidateDetails = setFriendsListForAUser(result,loginId,status);		
			dataTransferVO.setCandidateVO(candidateDetails);
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
		
		try{
			resultStatusForSaving = saveCommunicationDataBetweenUsers(senderId,recipeintId,messageType,subject);
			dataTransferVO.setResultStatusForComments(resultStatusForSaving);
			
			result = ananymousUserDAO.getAllUsersInSelectedLocations(locationIds, locationType,retrivalCount);			
			candidateDetails = setFriendsListForAUser(result,loginId,IConstants.ALL);		
			dataTransferVO.setCandidateVO(candidateDetails);
			
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
					List<Object> detailsList = customMessageDAO.getRelationShipBetweenTheUsers(candidates,loginId,status);	
					for(int i=0;i<detailsList.size();i++){
						Object[] parms = (Object[])detailsList.get(i);				
						Long userId = new Long(parms[0].toString());			
						if(userIdAndRelationShipWithLogedUser.get(userId)!=null){
							userIdAndRelationShipWithLogedUser.get(userId).setStatus(parms[1].toString());	
						}				
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
		List<CandidateVO> candiateVO = new ArrayList<CandidateVO>(0); 
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
			System.gc();
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
			System.gc();
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
		try{
			List<Object> result = customMessageDAO.getAllMessagesForUser(userId,messageType);
			if(result!=null && result.size()!=0){
				totalMsgCount = new Long(result.size());
				for(int i=0;i<result.size();i++){
					Object[] parms = (Object[])result.get(i);
					CandidateVO candidateResults = new CandidateVO();
					if(parms[0].toString().length() < 20)
						candidateResults.setData(parms[0].toString());
					else
						candidateResults.setData(parms[0].toString().substring(0, 19));
					candidateResults.setMessage(parms[0].toString());
					candidateResults.setId(new Long(parms[1].toString()));
					String candidateName="";
					
					if(parms[2]!=null)
						candidateName+=parms[2].toString().concat("  ").concat("  ");
					
					if(parms[3]!=null)
						candidateName+=parms[3].toString();

					candidateResults.setState(parms[4].toString());
					candidateResults.setDistrict(parms[5].toString());					
					candidateResults.setConstituencyName(parms[6].toString());
					candidateResults.setCandidateName(candidateName);
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
}
