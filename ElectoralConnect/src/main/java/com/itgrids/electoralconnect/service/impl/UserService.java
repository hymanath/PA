package com.itgrids.electoralconnect.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.electoralconnect.dao.IRolesDAO;
import com.itgrids.electoralconnect.dao.IUserDAO;
import com.itgrids.electoralconnect.dao.IUserLoginDAO;
import com.itgrids.electoralconnect.dao.IUserProfileDAO;
import com.itgrids.electoralconnect.dao.IUserRolesDAO;
import com.itgrids.electoralconnect.dto.UserProfileVO;
import com.itgrids.electoralconnect.dto.UserVO;
import com.itgrids.electoralconnect.model.Roles;
import com.itgrids.electoralconnect.model.User;
import com.itgrids.electoralconnect.model.UserLogin;
import com.itgrids.electoralconnect.model.UserProfile;
import com.itgrids.electoralconnect.model.UserRoles;
import com.itgrids.electoralconnect.service.IUserService;
import com.itgrids.electoralconnect.dto.RegistrationVO;
import com.itgrids.partyanalyst.dao.hibernate.UserRolesDAO;

public class UserService implements IUserService{
		//DAO's 
		private IUserDAO userDAO;
		private IUserLoginDAO userLoginDAO;
		private IUserProfileDAO userProfileDAO;
		private IUserRolesDAO userRolesDAO;
		private IRolesDAO rolesDAO;
		private static final Logger log=Logger.getLogger(UserService.class);
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
		public UserVO registerUser(final UserProfileVO userProfileVO){
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
			Roles roles=new Roles();
			
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
				log.debug("Exception Raised ..In registerUser in UserService"+e);
			}
			return user;
		  }
			
		});
			UserVO userVO=new UserVO();
			userVO.setUsername(result.getUserLogin().getUserName());
			userVO.setPwd(result.getUserLogin().getPassword());
			userVO.setFirstname(result.getUserProfile().getFirstName());
			userVO.setLastname(result.getUserProfile().getLastName());
			userVO.setEmailId(result.getUserLogin().getUserName());
			
			
			return userVO;
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
			RegistrationVO regVO        = new RegistrationVO();
			//List<Object[]> userDetails = userDAO.checkForValidUser(username, password);
			List<Object[]> userDetails = userRolesDAO.checkForValidUser(username, password);
			UserProfile userProfile    = new UserProfile();
			UserLogin userLogin        = new UserLogin();
			User user                  = new User();
			Roles roles                = new Roles();
			if(userDetails != null && userDetails.size() > 0)
			{
				for (Object[] parms : userDetails) {
					/*userProfile = (UserProfile) parms[0];
					userLogin     =   (UserLogin) parms[1];*/
					user          =   (User) parms[0];
					roles         =   (Roles) parms[1];
					userProfile   =   user.getUserProfile();
					userLogin     =   user.getUserLogin();
					regVO.setFirstName(userProfile.getLastName());
					regVO.setLastName(userProfile.getLastName());
					regVO.setEmail(userProfile.getEmailId());
					regVO.setMobile(userProfile.getMobileNo());
					regVO.setUserName(userLogin.getUserName());
					regVO.setRegistrationID(userLogin.getUserLoginId());
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
			return regVO;
		}
}
