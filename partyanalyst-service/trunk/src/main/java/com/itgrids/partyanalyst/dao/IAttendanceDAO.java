package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.Attendance;

public interface IAttendanceDAO extends GenericDao<Attendance,Long>{
	
	public List<Long> getPrimarykey(String uniqueKey,String imei,Long tabPrimaryKey);

}
