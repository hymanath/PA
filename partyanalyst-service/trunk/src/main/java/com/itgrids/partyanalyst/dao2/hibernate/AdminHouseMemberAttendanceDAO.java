package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAdminHouseMemberAttendanceDAO;
import com.itgrids.partyanalyst.model.AdminHouseMemberAttendance;

public class AdminHouseMemberAttendanceDAO extends GenericDaoHibernate<AdminHouseMemberAttendance, Long> implements IAdminHouseMemberAttendanceDAO{

	public AdminHouseMemberAttendanceDAO() {
		super(AdminHouseMemberAttendance.class);
		
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getSessionWiseTopAbsentMembersInfo(Long sessionDayId,Date startDate,Date endDate)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.adminHouseMemberAttendanceId,count(model.adminHouseMemberAttendanceId),  AdminHouseMemberAttendance from  model ");
		
		if(startDate != null && endDate != null){
			queryStr.append(" where model.adminHouseSessionDay.adminHouseSessionDayId = :sessionDayId " +
					" (date(model.adminHouseSessionDay.sessionDate) between :startDate and :endDate) " +
					" and model.isAttended = 'Y' ");
		}
		Query query = getSession().createQuery(queryStr.toString());
			query.setParameter("sessionDayId", sessionDayId);
		
		if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getPartyWiseAttendanceInfo()
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" ");
		
		Query query = getSession().createQuery(queryStr.toString());
		

		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getMPWiseAttendanceInfo()
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" ");
		
		Query query = getSession().createQuery(queryStr.toString());
		

		return query.list();
	}
}
