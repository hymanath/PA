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
		Query query = getSession().createQuery("select distinct WMAU from WebMonitoringAssignedUsers WMAU where WMAU.webMoniterUserId = :webMonitorId ");
		
		query.setParameter("webMonitorId", webMonitorId);
		return query.list();
		
	}
	
	public List<Object[]> getAssignedusersForWebMontringTeam(Long userId)
	{
		Query query = getSession().createQuery("select model.surveyUser.surveyUserId,model.surveyUser.userName from WebMonitoringAssignedUsers model " +
				" where model.webMoniterUserId = :userId and model.isDelete = 'N'");
		query.setParameter("userId", userId);
		return query.list();
	}
	
	public List<Long>  getAssignedUsersIdsByWebMonitorId(Long webMonitorId)
	{
		Query query = getSession()
				.createQuery(
						"select WMAU.surveyUserId from WebMonitoringAssignedUsers WMAU where WMAU.webMoniterUserId = :webMonitorId and WMAU.isDelete = 'N'");
		
		query.setParameter("webMonitorId", webMonitorId);
		
		return query.list();
		
	}
	

	public List<Long> getConstiteuncyUsersInConsti(List<Long> constiUsersList){
		Query query = getSession().createQuery("select distinct WMAU.surveyUser.surveyUserId from WebMonitoringAssignedUsers WMAU where " +
				" WMAU.surveyUserId in (:constiUsersList) and WMAU.isDelete = 'N'");

		query.setParameterList("constiUsersList", constiUsersList);
		
		return query.list();
	}
	

	public List<Long> getAssignedUsersBoothsDetails(Long webMonitorId,Long constituencyId)
	{
		
		Query query = getSession().createQuery("select distinct SUBA.booth.boothId from WebMonitoringAssignedUsers WMAU ,SurveyUserBoothAssign SUBA where" +
				"WMAU.surveyUser.surveyUserId  = SUBA.surveyUser.surveyUserId and " +
				"WMAU.webMoniterUserId = :webMonitorId and  SUBA.booth.constituency.constituencyId = :constituencyId");
		
		query.setParameter("webMonitorId", webMonitorId);
		query.setParameter("constituencyId", constituencyId);
		
		return query.list();
	}
}
