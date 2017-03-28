package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UserAppointmentUser;

public interface IUserAppointmentUserDAO extends GenericDao<UserAppointmentUser, Long>{

	public List<Object[]> getAppointmentStatusByUserId(Long userId);
	public List<Long> getLoginUserAppointmentUserType(Long userId);
}
