package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ScheduleInviteeStatus;

public interface IScheduleInviteeStatusDAO extends GenericDao<ScheduleInviteeStatus, Long>{
	
	public List<Object[]> getAllStatusList();

}
