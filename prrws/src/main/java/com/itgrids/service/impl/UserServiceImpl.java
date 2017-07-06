package com.itgrids.service.impl;
/*
 * @Author R Nagarjuna Gowd
 * Date 06/06/2017
 */
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dao.IConstituencyDAO;
import com.itgrids.dao.IDistrictDAO;
import com.itgrids.dao.IUserDAO;
import com.itgrids.dto.UserVO;
import com.itgrids.model.User;
import com.itgrids.service.IUserService;
import com.itgrids.utils.IConstants;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
	public static Logger log = Logger.getLogger(UserServiceImpl.class);
	@Autowired
	private IUserDAO userDAO;
	@Autowired
	private IConstituencyDAO constituencyDAO;
	@Autowired
	private IDistrictDAO districtDAO;

	@Override
	public UserVO userAuthentication(String userName, String password) {
		UserVO userVO = new UserVO();
		if (userName != null) {
			User user = userDAO.loginAuthentication(userName, password);

			if (user != null) {
				userVO.setUserId(user.getUserId());
				userVO.setUserName(user.getUsername());
				userVO.setEmail(user.getEmail());
				userVO.setPhoneNo(user.getMobileNoo());
				userVO.setResponceCode(1l);
			}else {
				userVO.setResponceCode(0l);
			}

		} 
		return userVO;
	}
	public String getAssignedSearchIdByTypeId( Long searchLevelId,Long searchLevelValue,String fromPage,String toPage){
		String code ="";
		try{
			if(!fromPage.equalsIgnoreCase("FMS")){
				if(searchLevelId != null && searchLevelId == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
					code = constituencyDAO.getAssignedSearchIdByConstituencyId(searchLevelValue,fromPage);
				}else if(searchLevelId != null && searchLevelId == IConstants.DISTRICT_LEVEL_SCOPE_ID ){
					code = districtDAO.getAssignedSearchIdByDistrictId(searchLevelValue,fromPage);
				}
			}else{
				if(searchLevelId != null && searchLevelId == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
					code = constituencyDAO.getAssignedSearchConstituencyId(searchLevelValue,fromPage);
				}else if(searchLevelId != null && searchLevelId == IConstants.DISTRICT_LEVEL_SCOPE_ID ){
					code = districtDAO.getAssignedSearchDistrictId(searchLevelValue,fromPage);
				}
			}
			
		}catch(Exception e){
			log.error("error occured in getAssignedSearchIdByTypeId() method");
			e.printStackTrace();	
		}
		return code;
	 }
	
		
	
}
