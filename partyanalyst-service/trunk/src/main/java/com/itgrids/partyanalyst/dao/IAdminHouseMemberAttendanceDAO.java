package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AdminHouseMemberAttendance;

public interface IAdminHouseMemberAttendanceDAO extends GenericDao<AdminHouseMemberAttendance, Long>{
	public List<Object[]> getSessionWiseTopAbsentMembersInfo(Long sessionDayId,Date startDate,Date endDate);
	public List<Object[]> getPartyWiseAttendanceInfo();
	public List<Object[]> getMPWiseAttendanceInfo();
}
