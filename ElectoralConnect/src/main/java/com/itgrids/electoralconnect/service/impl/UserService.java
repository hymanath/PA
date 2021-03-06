package com.itgrids.electoralconnect.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import com.itgrids.electoralconnect.dao.IAnnouncementsDAO;
import com.itgrids.electoralconnect.dao.ICommentDAO;
import com.itgrids.electoralconnect.dao.ICommentReplyDAO;
import com.itgrids.electoralconnect.dao.IRolesDAO;
import com.itgrids.electoralconnect.dao.IUserDAO;
import com.itgrids.electoralconnect.dao.IUserLoginDAO;
import com.itgrids.electoralconnect.dao.IUserProfileDAO;
import com.itgrids.electoralconnect.dao.IUserRolesDAO;
import com.itgrids.electoralconnect.dto.CommentVO;
import com.itgrids.electoralconnect.dto.UserProfileVO;
import com.itgrids.electoralconnect.model.Announcements;
import com.itgrids.electoralconnect.model.Comment;
import com.itgrids.electoralconnect.model.CommentReply;
import com.itgrids.electoralconnect.model.Roles;
import com.itgrids.electoralconnect.model.User;
import com.itgrids.electoralconnect.model.UserLogin;
import com.itgrids.electoralconnect.model.UserProfile;
import com.itgrids.electoralconnect.model.UserRoles;
import com.itgrids.electoralconnect.service.IUserService;
import com.itgrids.electoralconnect.dto.RegistrationVO;
import com.itgrids.electoralconnect.dto.ResultCodeMapper;
import com.itgrids.electoralconnect.dto.ResultStatus;
import com.itgrids.electoralconnect.dto.UserVO;


public class UserService implements IUserService{
		//DAO's 
		private IUserDAO userDAO;
		private IUserLoginDAO userLoginDAO;
		private IUserProfileDAO userProfileDAO;
		private IUserRolesDAO userRolesDAO;
		private IRolesDAO rolesDAO;
		private IAnnouncementsDAO announcementsDAO;
		private DateUtilService dateUtilService;
		private ICommentDAO commentDAO;
		private ICommentReplyDAO commentReplyDAO;
		private static final Logger LOG=Logger.getLogger(UserService.class);
		private TransactionTemplate transactionTemplate=null;
		
		
		public IUserDAO getUserDAO() {
			return userDAO;
		}
		public void setUserDAO(IUserDAO userDAO) {
			this.userDAO = userDAO;
		}
		public IUserLoginDAO getUserLoginDAO() {
			return userLoginDAO;
		}
		public void setUserLoginDAO(IUserLoginDAO userLoginDAO) {
			this.userLoginDAO = userLoginDAO;
		}
		public IUserProfileDAO getUserProfileDAO() {
			return userProfileDAO;
		}
		public void setUserProfileDAO(IUserProfileDAO userProfileDAO) {
			this.userProfileDAO = userProfileDAO;
		}
		public IUserRolesDAO getUserRolesDAO() {
			return userRolesDAO;
		}
		public void setUserRolesDAO(IUserRolesDAO userRolesDAO) {
			this.userRolesDAO = userRolesDAO;
		}

		public IRolesDAO getRolesDAO() {
			return rolesDAO;
		}
		public void setRolesDAO(IRolesDAO rolesDAO) {
			this.rolesDAO = rolesDAO;
		}
		public TransactionTemplate getTransactionTemplate() {
			return transactionTemplate;
		}
		public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
			this.transactionTemplate = transactionTemplate;
		}
		
		public IAnnouncementsDAO getAnnouncementsDAO() {
			return announcementsDAO;
		}
		public void setAnnouncementsDAO(IAnnouncementsDAO announcementsDAO) {
			this.announcementsDAO = announcementsDAO;
		}
		
		public ICommentDAO getCommentDAO() {
			return commentDAO;
		}
		public void setCommentDAO(ICommentDAO commentDAO) {
			this.commentDAO = commentDAO;
		}
		
		
		public ICommentReplyDAO getCommentReplyDAO() {
			return commentReplyDAO;
		}
		public void setCommentReplyDAO(ICommentReplyDAO commentReplyDAO) {
			this.commentReplyDAO = commentReplyDAO;
		}
		public String validateEmail(String emailId){
			String res="";
			List<Object[]> usersList=new ArrayList<Object[]>();
			usersList=userProfileDAO.validateEmail(emailId);
			if(usersList.size()!=0){
				res="FAILED";
			}
			else{
				res="SUCCESS";
			}
			return res;
		}
		public RegistrationVO registerUser(final UserProfileVO userProfileVO){
			User result = (User)transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus status) {
			UserProfile userProfile=new UserProfile();
			UserLogin userLogin=new UserLogin();
			User user=null;
			
			try{
			
			//User Profile Details Saving
			userProfile.setFirstName(userProfileVO.getFirstName());
			userProfile.setLastName(userProfileVO.getLastName());
			userProfile.setEmailId(userProfileVO.getEmailId());
			userProfile.setMobileNo(userProfileVO.getMobileNo());
			userProfile.setEpicNo(userProfileVO.getEpicId());
			
			userProfile=userProfileDAO.save(userProfile);
			
			
			//User Login Details Saving
			String str = ((Long)System.currentTimeMillis()).toString();
			String pwd = str.substring(str.length()-7,str.length());
			
			userLogin.setUserName(userProfile.getEmailId());
			userLogin.setPassword(pwd);
			userLogin.setLastLoginTime(null);
			userLogin.setIsPwdChanged("NO");
			userLogin.setIsEmailVerified("NO");
			
			userLogin=userLoginDAO.save(userLogin);
			
			//User details Saving in User Table
			
			user=new User(userProfile, userLogin);
			user.setUserLogin(userLogin);
			user.setUserProfile(userProfile);
			user=userDAO.save(user);
			
			UserRoles userRoles=new UserRoles();
			//Roles roles=new Roles();
			
			userRoles.setUser(user);
			
			if(userProfileVO.getUserType() != null && userProfileVO.getUserType().equalsIgnoreCase("admin"))
			{
				userRoles.setRoles(rolesDAO.get(1l));
			}
			else
			{
				userRoles.setRoles(rolesDAO.get(2l));
			}
			
			
			userRoles=userRolesDAO.save(userRoles);
			
			}
			catch (Exception e) {
				LOG.debug("Exception Raised ..In registerUser in UserService"+e);
			}
			return user;
		  }
			
		});
			RegistrationVO regVO = new RegistrationVO();
			regVO.setUserName(result.getUserLogin().getUserName());
			regVO.setRegistrationID(result.getUserId());
			regVO.setFirstName(result.getUserProfile().getFirstName());
			regVO.setLastName(result.getUserProfile().getLastName());
			regVO.setMobile(result.getUserProfile().getMobileNo());
			regVO.setEmail(result.getUserProfile().getEmailId());
			regVO.setPassword(result.getUserLogin().getPassword());
			return regVO;
		}
		
		/**
		 * This Service is used for Verfying the weather user entered username and password or correct or wrong
		 * @param String username
		 * @param String password
		 * @return RegistrationVO
		 * @date 29-06-2013
		 */
		public RegistrationVO checkForValidUser(String username,String password)
		{
			RegistrationVO regVO         = new RegistrationVO();
			try 
			{
				LOG.debug("Entered Into checkForValidUser() method in UserService Service"); 
				//List<Object[]> userDetails = userDAO.checkForValidUser(username, password);
				List<Object[]> userDetails   = userRolesDAO.checkForValidUser(username, password);
				UserProfile userProfile      = new UserProfile();
				UserLogin userLogin          = new UserLogin();
				User user                    = new User();
				Roles roles                  = new Roles();
				if(userDetails != null && userDetails.size() > 0)
				{
					for (Object[] parms : userDetails) {
						
						user          =   (User) parms[0];
						roles         =   (Roles) parms[1];
						userProfile   =   user.getUserProfile();
						userLogin     =   user.getUserLogin();
						regVO.setFirstName(userProfile.getFirstName());
						regVO.setLastName(userProfile.getLastName());
						regVO.setEmail(userProfile.getEmailId());
						regVO.setMobile(userProfile.getMobileNo());
						regVO.setUserName(userLogin.getUserName());
						regVO.setRegistrationID(user.getUserId());
						regVO.setIsPasswordChanged(userLogin.getIsPwdChanged());
						if(roles.getRole().equalsIgnoreCase("Admin"))
						{
							regVO.setIsAdmin(true);
						}
						else
						{
							regVO.setIsAdmin(false);
						}
					}
				}
			} catch (Exception e)
			{
				LOG.error("Exception Raised in checkForValidUser() method in UserService Service",e); 
			}
			
			return regVO;
		}
		
		/**
		 * This Service is used for updateing the password
		 * @param String password
		 * @param Long userId
		 * @return ResultStatus
		 */
		public ResultStatus updateUserPassword(String password,Long userId)
		{
			ResultStatus resultStatus = new ResultStatus();
			try
			{
				LOG.debug("Entered into updateUserPassword() method in UserService Service");
				Long userLoginId =0l;
				if(userId != null)
				{
					userLoginId   = userDAO.get(userId).getUserLogin().getUserLoginId();
				}
				
				int passwordStatus = userDAO.updatePassword(password, userLoginId);
				if(passwordStatus == 1)
				{
					resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				}
				else
				{
					resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				}
			} catch (Exception e)
			{
				LOG.error("Exception Raised in updateUserPassword() method in UserService Service",e);
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			}
			
			return resultStatus;
		}
		/**
		 * This Service is used for getting the password For the sake of user forgeted password
		 * @param String username
		 * @return RegistrationVO
		 * @date 02-07-2013
		 */
		public RegistrationVO forgetPasswordService(String username)
		{
			RegistrationVO regVO = new RegistrationVO();
			try 
			{
				LOG.debug("Entered into forgetPasswordService() method in UserService Service");
				User user = userDAO.getUserDetailsByUserName(username);
				if(user != null)
				{
					regVO.setUserName(user.getUserLogin().getUserName());
					regVO.setRegistrationID(user.getUserId());
					regVO.setPassword(user.getUserLogin().getPassword());
					regVO.setFirstName(user.getUserProfile().getFirstName());
					regVO.setLastName(user.getUserProfile().getLastName());
					regVO.setEmail(user.getUserProfile().getEmailId());
					regVO.setMobile(user.getUserProfile().getMobileNo());
				}
			} catch (Exception e)
			{
				LOG.error("Exception Raised in forgetPasswordService() method in UserService Service",e);
			}
			
			return regVO;
		}
		/**
		 * This Service is used for saving the comments enterd by user about announcement or pressrelease
		 * @param Long userId
		 * @param Long annoncementId
		 * @param String comment
		 * @return ResultStatus
		 * @date 02-07-2013
		 */
		public ResultStatus saveComment(Long userId,Long annoncementId,String comment)
		{
			ResultStatus resultStatus = new ResultStatus();
			try 
			{
				LOG.debug("Entered into saveComment() method in UserService Service");
				Comment commentModel = new Comment();
				if(userId != null && userId > 0)
				{
					commentModel.setUser(userDAO.get(userId));
				}
				if(annoncementId != null && annoncementId > 0)
				{
					commentModel.setAnnouncements(announcementsDAO.get(annoncementId));
				}
				dateUtilService = new DateUtilService();
				commentModel.setComment(comment);
				commentModel.setTime(dateUtilService.getCurrentDateAndTime());
				commentModel.setIsDelete("NO");
				commentModel = commentDAO.save(commentModel);
				if(commentModel != null)
				{
					resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				}
				else
				{
					resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				}
			} catch (Exception e)
			{
				LOG.error("Exception Raised in saveComment() method in UserService Service",e);
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			}
			
			return resultStatus;
		}
		
		
		public ResultStatus saveReplyComment(final Long userId,final Long commentId,final String comment){
			ResultStatus resultStatus = new ResultStatus();
			try 
			{
				CommentReply commentReply=(CommentReply) transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
				LOG.debug("Entered into saveComment() method in UserService Service");
				Comment commentModel = new Comment();
				CommentReply commentReply = new CommentReply();
				
				if(userId != null && userId > 0)
				{
					commentModel.setUser(userDAO.get(userId));
				}
				/*if(commentId != null && commentId > 0)
				{
					commentModel.setAnnouncements(commentDAO .get(commentId));
				}*/
				dateUtilService = new DateUtilService();
				commentModel.setComment(comment);
				commentModel.setTime(dateUtilService.getCurrentDateAndTime());
				commentModel.setIsDelete("NO");
				commentModel = commentDAO.save(commentModel);
				commentReply.setComment(commentModel);
				commentReply.setCommentedOn(commentId);
				commentReply =commentReplyDAO.save(commentReply);
				}
				});
				if(commentReply != null)
				{
					resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				}
				else
				{
					resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				}
			} catch (Exception e)
			{
				LOG.error("Exception Raised in saveComment() method in UserService Service",e);
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			}
			
			return resultStatus;
		}
		/**
		 * This Service is used for getting all comments commented by users
		 * @param Long announcementId
		 * @param Long startIndex
		 * @param Long  maxIndex
		 * @return List<CommentVO>
		 * @date 03-07-2013
		 */
		public List<CommentVO> getAllCommentsCommentedByUser(Long announcementId,int startIndex,int maxIndex)
		{
			List<CommentVO> cmmntRplyList =null; 
			List<Long> cmmntIdsList=new ArrayList<Long>();
			Map<Long,CommentVO> cmmntMap=new HashMap<Long, CommentVO>();
			List<CommentReply> cmmntRply=new ArrayList<CommentReply>();
			List<CommentVO> returnList=null;
			try
			{
				LOG.debug("Entered into getAllCommentsCommentedByUser() method in UserService Service");
				User user = new User();
				Long totalCount = commentDAO.getTotalCommentsCountByAnnouncementId(announcementId);
				List<Object[]> commentsList = commentDAO.getAllComments(announcementId, startIndex, maxIndex);
				if(commentsList != null && commentsList.size() > 0)
				{
					//returnList = new ArrayList<CommentVO>();
					for (Object[] parms : commentsList) {
						CommentVO commentVO = new CommentVO();
						cmmntRplyList =new ArrayList<CommentVO>(); 
						
						commentVO.setCommentId((Long)parms[0]);
						commentVO.setComment(parms[1].toString());
						user          = (User) parms[2];
						commentVO.setName(user.getUserProfile().getFirstName());
						commentVO.setCmmntRplyList(cmmntRplyList);
						
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
						
						String reportDate="";
						if(parms[3]!=null){
							reportDate = df.format(parms[3]);
						}
						
						//commentVO.setDate((Date) parms[3]);
						commentVO.setCommentedTime(reportDate);
						commentVO.setTotal(totalCount);
						cmmntIdsList.add((Long)parms[0]);
						cmmntMap.put((Long)parms[0], commentVO);
						//returnList.add(commentVO);
					}
					cmmntRply=commentReplyDAO.getRepliesForCommentByIds(cmmntIdsList);
					
					for(CommentReply param:cmmntRply){
						CommentVO commentVO2=new CommentVO();
						commentVO2.setCommentId(param.getComment().getCommentId());
						commentVO2.setComment(param.getComment().getComment());
						commentVO2.setName(param.getComment().getUser().getUserProfile().getFirstName()+""+param.getComment().getUser().getUserProfile().getLastName());
						DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
						
						String reportDate1="";
						if(param.getComment().getTime()!=null){
							reportDate1 = df1.format(param.getComment().getTime());
						}
						commentVO2.setCommentedTime(reportDate1);
						commentVO2.setAbused(param.getComment().getIsDelete());
						if(cmmntMap.get(param.getCommentedOn().longValue())!=null){
							cmmntMap.get(param.getCommentedOn().longValue()).getCmmntRplyList().add(commentVO2);
						}
					}
					List<Object[]> cmntReply=commentReplyDAO.getReplyCountsByIdsList(cmmntIdsList);
					for(Object[] obj:cmntReply){
						if(cmmntMap.get(Long.parseLong(obj[0].toString()))!=null){
							cmmntMap.get(obj[0]).setReplyCount(Long.parseLong(obj[1].toString()));
						}
					}
					
					returnList=new ArrayList<CommentVO>(cmmntMap.values());
					
				}
			} catch (Exception e)
			{
				returnList = new ArrayList<CommentVO>();
				LOG.error("Exception Raised in getAllCommentsCommentedByUser() method in UserService Service",e);
			}
			
			return returnList;
		}
		/**
		 * This Service is user for getting Top 5 Comments commented by the users
		 * @param Long announcementId
		 * @return List<CommentVO>
		 * @date 03-07-2013
		 */
		/*public List<CommentVO> getTop5CommentsCommentedByUser(Long announcementId)
		{
			List<CommentVO> returnList = null;
			try {
				LOG.debug("Entered into getTop5CommentsCommentedByUser() method in UserService Service");
				User user = new User();
				List<Object[]> commentsList = commentDAO.getTop5Comments(announcementId);
				if(commentsList != null && commentsList.size() > 0)
				{
					returnList = new ArrayList<CommentVO>();
					for (Object[] parms : commentsList) {
						CommentVO commentVO = new CommentVO();
						commentVO.setCommentId((Long)parms[0]);
						commentVO.setComment(parms[1].toString());
						user          = (User) parms[2];
						commentVO.setName(user.getUserProfile().getFirstName());
						commentVO.setDate((Date) parms[3]);
						returnList.add(commentVO);
					}
				}
			} catch (Exception e) {
				returnList = new ArrayList<CommentVO>();
				LOG.error("Exception Raised in getTop5CommentsCommentedByUser() method in UserService Service",e);
			}
			
			return returnList;
		}*/
		
		/**
		 * This Service is used for getting all comments By Selected Between Dates
		 * @param Date startDate
		 * @param Date endDate
		 * @param Long startIndex
		 * @param Long  maxIndex
		 * @return List<CommentVO>
		 * @date 03-07-2013
		 */
		public List<CommentVO> getAllCommentsBetweenSelectedDates(Date startDate,Date endDate,int startIndex,int maxIndex)
		{
			List<CommentVO> returnList = null;
			try
			{
				LOG.debug("Entered into getAllCommentsBetweenSelectedDates() method in UserService Service");
				User user = new User();
				Announcements announcements = new Announcements();
				Long totalCount = commentDAO.getCommentsCountBetweenSelectedDates(startDate,endDate);
				List<Object[]> commentsList = commentDAO.getCommentsBetwnnSelectedDates(startDate, endDate,startIndex, maxIndex);
				if(commentsList != null && commentsList.size() > 0)
				{
					returnList = new ArrayList<CommentVO>();
					for (Object[] parms : commentsList) {
						CommentVO commentVO = new CommentVO();
						commentVO.setCommentId((Long)parms[0]);
						commentVO.setComment(parms[1].toString());
						user          = (User) parms[2];
						announcements = (Announcements) parms[4];
						commentVO.setName(user.getUserProfile().getFirstName());
						
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
						
						String reportDate="";
						if(parms[3]!=null){
							reportDate = df.format(parms[3]);
						}
						commentVO.setCommentedTime(reportDate);
						commentVO.setAnnouncement(announcements.getTitle());
						commentVO.setAbused(parms[5].toString());
						commentVO.setTotal(totalCount);
						
						List<CommentReply> cmmntReplyList=commentReplyDAO.getRepliesForCommentById(commentVO.getCommentId());
						List<CommentVO> replyList=new ArrayList<CommentVO>();
						if(cmmntReplyList!=null){
							
							for(CommentReply param:cmmntReplyList){
								CommentVO commentVO2=new CommentVO();
								commentVO2.setCommentId(param.getComment().getCommentId());
								commentVO2.setComment(param.getComment().getComment());
								commentVO2.setName(param.getComment().getUser().getUserProfile().getFirstName()+""+param.getComment().getUser().getUserProfile().getLastName());
								Long replyCount=commentReplyDAO.getRepliesForCommentByIdCount(commentVO.getCommentId());
								commentVO2.setTotal(replyCount);
								DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
								
								String reportDate1="";
								if(param.getComment().getTime()!=null){
									reportDate1 = df1.format(param.getComment().getTime());
								}
								commentVO2.setCommentedTime(reportDate1);
								commentVO2.setAbused(param.getComment().getIsDelete());
								replyList.add(commentVO2);
							}
						}
						
						commentVO.setCmmntRplyList(replyList);
						returnList.add(commentVO);
					}
				}
				
			} catch (Exception e)
			{
				returnList = new ArrayList<CommentVO>();
				LOG.error("Exception Raised in getAllCommentsCommentedByUser() method in UserService Service",e);
			}
			
			return returnList;
		}
		
		/**
		 * This Service is used to get all comments 
		 * @param int startIndex
		 * @param int maxIndex
		 * @return List<CommentVO>
		 * @date 03-07-2013
		 */
		public List<CommentVO> getAllComments(int startIndex,int maxIndex)
		{
			List<CommentVO> returnList = null;
			try 
			{
				LOG.debug("Entered into getAllComments() method in UserService Service");
				User user = new User();
				Announcements announcements = new Announcements();
				Long totalCount = commentDAO.getTotalComments();
				List<Object[]> commentsList = commentDAO.getAllComments(startIndex, maxIndex);
				if(commentsList != null && commentsList.size() > 0)
				{
					returnList = new ArrayList<CommentVO>();
					for (Object[] parms : commentsList) {
						CommentVO commentVO = new CommentVO();
						commentVO.setCommentId((Long)parms[0]);
						commentVO.setComment(parms[1].toString());
						user          = (User) parms[2];
						announcements = (Announcements) parms[4];
						commentVO.setName(user.getUserProfile().getFirstName());
						commentVO.setDate((Date) parms[3]);
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
						
						String reportDate="";
						if(parms[3]!=null){
							reportDate = df.format(parms[3]);
						}
						commentVO.setCommentedTime(reportDate);
						commentVO.setAnnouncement(announcements.getTitle());
						commentVO.setAbused(parms[5].toString());
						commentVO.setTotal(totalCount);
						List<CommentReply> cmmntReplyList=commentReplyDAO.getRepliesForCommentById(commentVO.getCommentId());
						List<CommentVO> replyList=new ArrayList<CommentVO>();
						if(cmmntReplyList!=null){
							
							for(CommentReply param:cmmntReplyList){
								CommentVO commentVO2=new CommentVO();
								commentVO2.setCommentId(param.getComment().getCommentId());
								commentVO2.setComment(param.getComment().getComment());
								commentVO2.setName(param.getComment().getUser().getUserProfile().getFirstName()+""+param.getComment().getUser().getUserProfile().getLastName());
								Long replyCount=commentReplyDAO.getRepliesForCommentByIdCount(commentVO.getCommentId());
								commentVO2.setTotal(replyCount);
								DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
								
								String reportDate1="";
								if(param.getComment().getTime()!=null){
									reportDate1 = df1.format(param.getComment().getTime());
								}
								commentVO2.setCommentedTime(reportDate1);
								commentVO2.setAbused(param.getComment().getIsDelete());
								replyList.add(commentVO2);
							}
						}
						
						commentVO.setCmmntRplyList(replyList);
						returnList.add(commentVO);
					}
				}
			} catch (Exception e)
			{
				returnList = new ArrayList<CommentVO>();
				LOG.error("Exception Raised in getAllComments() method in UserService Service",e);
			}
			
			return returnList;
		}
		/**
		 * This Service  is used to Abuse the comment
		 * @param Long commentId
		 * @return UserVO
		 * @date  03-07-2013
		 */
		public UserVO abuseCommentService(Long commentId)
		{
			UserVO userVO = new UserVO();
			try 
			{
				LOG.debug("Entered into abuseCommentService() method in UserService Service");
				int aduseState = commentDAO.abuseTheComment(commentId);
				Comment comment = commentDAO.get(commentId);
				if(comment != null)
				{
					if(comment.getUser() != null)
					{
						userVO.setFirstname(comment.getUser().getUserProfile().getFirstName());
						userVO.setLastname(comment.getUser().getUserProfile().getLastName());
						userVO.setEmailId(comment.getUser().getUserProfile().getEmailId());
						userVO.setPwd(comment.getUser().getUserLogin().getPassword());
						userVO.setDescription(comment.getComment());
						if(aduseState == 1)
						{
							userVO.setResultCode(ResultCodeMapper.SUCCESS);
						}
						else
						{
							userVO.setResultCode(ResultCodeMapper.FAILURE);
						}
						
					}
				}
				
			} catch (Exception e) 
			{
				userVO.setResultCode(ResultCodeMapper.FAILURE);
				LOG.error("Exception Raised in abuseCommentService() method in UserService Service",e);
			}
			return userVO;
		}
		
		/**
		 * This Service is used to remove the Special charactes in the Text
		 * @param String textValue
		 * @return  String
		 * @date 06-07-2013
		 */
		public static String removeSpecialCharsFromAString(String textValue){
			if(textValue != null){
				/*String[] j=IConstants.SPECIALCHARS;
				for(int i=0;i<j.length;i++){
					textValue=textValue.replace(j[i], "");
				}*/
				textValue = textValue.replaceAll("\uFFFD", "");
				textValue = textValue.replaceAll("&#65533;", "");
			}
			return textValue;
		}
		
		/**
		 * This Service is used to get the password of the user
		 * @param Long userId
		 * @return String
		 * @date 09-07-2013
		 */
		public String getPasswordByUserId(Long userId)
		{
			String password = null;
			if(userId != null && userId > 0)
			{
				password = userDAO.getPasswordByUser(userId);
			}
			return password;
		}
		
		public Long getRepliesCount(Long commentId){
			Long repcount=0l;
			if(commentId>0){
				repcount=commentReplyDAO.getRepliesForCommentByIdCount(commentId);
			}
			return repcount;
		}
		public List<CommentVO> getRepliesList(Long commentId){
			List<CommentVO> repliesList=new ArrayList<CommentVO>();
			List<CommentReply> replist=commentReplyDAO.getRepliesForCommentById(commentId);
			
			if(replist!=null){
				for(CommentReply param:replist){
					CommentVO commentVO=new CommentVO();
					commentVO.setCommentId(param.getComment().getCommentId());
					commentVO.setComment(param.getComment().getComment());
					commentVO.setName(param.getComment().getUser().getUserProfile().getFirstName()+""+param.getComment().getUser().getUserProfile().getLastName());
					Long replyCount=commentReplyDAO.getRepliesForCommentByIdCount(commentId);
					commentVO.setTotal(replyCount);
					DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					
					String reportDate1="";
					if(param.getComment().getTime()!=null){
						reportDate1 = df1.format(param.getComment().getTime());
					}
					commentVO.setCommentedTime(reportDate1);
					commentVO.setAbused(param.getComment().getIsDelete());
					repliesList.add(commentVO);
				}
			}
			
			return repliesList;
		}
}
