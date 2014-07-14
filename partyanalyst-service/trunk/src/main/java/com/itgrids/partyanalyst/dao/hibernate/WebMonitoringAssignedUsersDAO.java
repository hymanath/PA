package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IWebMonitoringAssignedUsersDAO;
import com.itgrids.partyanalyst.model.WebMonitoringAssignedUsers;

public class WebMonitoringAssignedUsersDAO extends GenericDaoHibernate<WebMonitoringAssignedUsers, Long> implements
		IWebMonitoringAssignedUsersDAO {
	
	public WebMonitoringAssignedUsersDAO() {
		super(WebMonitoringAssignedUsers.class);
	}
	
	public List<WebMonitoringAssignedUsers>  getAssignedUsersDetailsByWebMonitorId(Long webMonitorId)
	{
		Query query = getSession().createQuery("select WMAU from WebMonitoringAssignedUsers WMAU where WMAU.webMoniterUserId = :webMonitorId");
		
		query.setParameter("webMonitorId", webMonitorId);
		return query.list();
		
	}
	
}
