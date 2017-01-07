package com.itgrids.cardprint.service.impl;

import org.apache.log4j.Logger;

import com.itgrids.cardprint.dao.IUserDAO;
import com.itgrids.cardprint.dto.UserVO;
import com.itgrids.cardprint.model.User;
import com.itgrids.cardprint.model.UserType;
import com.itgrids.cardprint.service.IUserService;
import com.itgrids.cardprint.util.SHA512;

public class UserService implements IUserService{

	private static final Logger LOG = Logger.getLogger(UserService.class);
	
	//Attributes
	private IUserDAO userDAO;
	
	//setter methods
	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

   //business methods
	public UserVO validateUserLogin(String userName,String password,String uniqueKey)
	{
		
		SHA512 sha512 = new SHA512();
		UserVO userVO = new UserVO();
		try{
			User user= userDAO.checkUserHashKeyExists(userName);
			if(user == null){
				return userVO;
			}
			
			if(user != null){
				 String hashKey = user.getHashKey() !=  null ? user.getHashKey().toString() : "";
				 String shapwd = sha512.hashText(hashKey + uniqueKey);
				
				 if(password.equals(shapwd)){
					 
					 	userVO.setName(user.getName());
						userVO.setUserId(user.getUserId());
						
						//Getting UserType for the user.
						UserType userType = user.getUserType();
						if(userType != null && userType.getUserTypeId() != null && userType.getUserTypeId().longValue() > 0l){
							userVO.setUserTypeId(userType.getUserTypeId());
							userVO.setUserType(userType.getUserType() != null ?userType.getUserType() : "");
						}
						/*//Entitlements
						List<String> entitlements=new ArrayList<String>();
						entitlements = userEntitlementGroupDAO.getUserEntitleMents(user.getUserId());
						
						if(entitlements != null && entitlements.size() > 0){
							userVO.setEntitlements(entitlements);
						}
						
						List<Object[]> rslt = userEntitlementGroupDAO.getUserEntitlementDetails(user.getUserId());
						List<Long> enttlmntIds = new ArrayList<Long>();
						if(rslt!=null && rslt.size()>0){
							for(Object[] obj:rslt){
								enttlmntIds.add(Long.valueOf(obj[0].toString()));
							}
						}
						
						userVO.setEntitlementIds(enttlmntIds);*/
				  }
				  else{
					  return userVO;
				  }	
		     }
				
	    }
		catch (Exception e) {
			LOG.error("Exception Occured in validateUserLogin() Method, Exception is - ",e);
		}
		return userVO;
	}
	
}


