package com.itgrids.electoralconnect.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.electoralconnect.dao.IRolesDAO;
import com.itgrids.electoralconnect.dao.IUserDAO;
import com.itgrids.electoralconnect.dao.IUserLoginDAO;
import com.itgrids.electoralconnect.dao.IUserProfileDAO;
import com.itgrids.electoralconnect.dao.IUserRolesDAO;
import com.itgrids.electoralconnect.dao.hibernate.RolesDAO;
import com.itgrids.electoralconnect.dto.UserProfileVO;
import com.itgrids.electoralconnect.dto.UserVO;
import com.itgrids.electoralconnect.model.Roles;
import com.itgrids.electoralconnect.model.User;
import com.itgrids.electoralconnect.model.UserLogin;
import com.itgrids.electoralconnect.model.UserProfile;
import com.itgrids.electoralconnect.model.UserRoles;
import com.itgrids.electoralconnect.service.IUserService;
import com.itgrids.partyanalyst.dao.IRoleDAO;
import com.itgrids.partyanalyst.model.Role;

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
			userRoles.setRole(rolesDAO.get(2l));
			
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
}
