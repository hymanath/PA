package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.WebMonitoringAssignedUsers;

public interface IWebMonitoringAssignedUsersDAO extends GenericDao<WebMonitoringAssignedUsers, Long>{	
	public List<WebMonitoringAssignedUsers>  getAssignedUsersDetailsByWebMonitorId(Long webMonitorId);	
	public List<Object[]> getAssignedusersForWebMontringTeam(Long userId);
	public List<Long>  getAssignedUsersIdsByWebMonitorId(Long webMonitorId);
	
	public List<Long> getConstiteuncyUsersInConsti(List<Long> constituencyId);
	public List<Long> getAssignedUsersBoothsDetails(Long webMonitorId,Long constituencyId);

	
}
