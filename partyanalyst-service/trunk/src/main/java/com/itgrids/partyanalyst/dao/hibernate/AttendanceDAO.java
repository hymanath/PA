package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAttendanceDAO;
import com.itgrids.partyanalyst.model.Attendance;

public class AttendanceDAO extends GenericDaoHibernate<Attendance,Long> implements IAttendanceDAO{

	public AttendanceDAO()
	{
		super(Attendance.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getPrimarykey(String uniqueKey,String imei,Long tabPrimaryKey)
	{
		Query query = getSession().createQuery("SELECT model.attendanceId FROM Attendance model where model.uniqueKey = :uniqueKey and model.imei = :imei and model.tabPrimaryKey = :tabPrimaryKey" +
				" order by model.insertedTime DESC");
		query.setParameter("uniqueKey",uniqueKey);
		query.setParameter("imei",imei);
		query.setParameter("tabPrimaryKey",tabPrimaryKey);
		return query.list();
	}
}
