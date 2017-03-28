package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AppointmentManageUser;
import com.itgrids.partyanalyst.model.AppointmentUser;

public interface IAppointmentManageUserDAO extends GenericDao<AppointmentManageUser, Long> {
	
	public List<Object[]> getAppointmentUsersDtlsByUserId(Long userId);
	
}
