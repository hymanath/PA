package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.User;

public interface IUserDAO extends GenericDao<User,Long>{
	
	public List<User> checkForUserNameAvailabiity(String userName);
	
	public List<User> checkForUserNameAvailabiityForEmail(String userName);
	
	public List<User> checkUserPassword(String password,Long userId); 
	
	public Integer changeUserPassword(String password,Long registrationId,String status,Date date);
	
	public List<Object> getUserIdByUserName(String userName);
	
	public User findByUserNameAndPassword(String userName, String password);
	
	public String getUserProfileImageNameByUserId(Long userId);
	
	public List<Object[]> getUserLocationDetailsByUserIds(List<Long> userIds);
	
	public User getUserByUserId(Long userId);
	
	public List getConnectedUsersCount(Long locationId,String locationType);
	
	public Integer saveUserProfileImageNameToDB(Long userId, String imageName);
	
}
