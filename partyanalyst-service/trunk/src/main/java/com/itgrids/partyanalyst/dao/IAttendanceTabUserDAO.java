package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.AttendanceTabUser;

public interface IAttendanceTabUserDAO extends GenericDao<AttendanceTabUser,Long>{
	
	public List<Object[]> getAttendanceTabUserByUsernameAndPassword(String username,String password);
	public List<Object[]> getAttendanceTabUserDetailes();
}
