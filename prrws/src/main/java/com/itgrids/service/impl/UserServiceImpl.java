package com.itgrids.service.impl;
/*
 * @Author R Nagarjuna Gowd
 * Date 06/06/2017
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dao.IUserDAO;
import com.itgrids.dto.UserVO;
import com.itgrids.model.User;
import com.itgrids.service.IUserService;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDAO IuserDAO;

	@Override
	public UserVO userAuthentication(String userName, String password) {
		UserVO userVO = new UserVO();
		if (userName != null) {
			User user = IuserDAO.loginAuthentication(userName, password);

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

}
