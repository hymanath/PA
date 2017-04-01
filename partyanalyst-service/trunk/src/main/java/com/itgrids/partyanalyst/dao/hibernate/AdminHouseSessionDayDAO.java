package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAdminHouseSessionDayDAO;
import com.itgrids.partyanalyst.model.AdminHouseSessionDay;

public class AdminHouseSessionDayDAO extends GenericDaoHibernate<AdminHouseSessionDay, Long> implements IAdminHouseSessionDayDAO{

	public AdminHouseSessionDayDAO() {
		super(AdminHouseSessionDay.class);
		
	}

	public List<Object[]> getSingleDate(Long adminHuSessionId){
		StringBuilder sb = new StringBuilder();
			sb.append("select model.adminHouseSessionDayId," +
					" date(model.sessionDate)" +
					" from AdminHouseSessionDay model " +
					" where model.isDeleted = 'N' and model.adminHouseSession.isActive ='Y' and model.adminHouseSession.isDeleted = 'N' " );
		if(adminHuSessionId != null && adminHuSessionId.longValue() > 0l)
		{
			sb.append(" and model.adminHouseSession.adminHouseSessionId = :adminHuSessionId");
		}
			sb.append(" order by model.sessionDate ");
			
		Query query = getSession().createQuery(sb.toString());
		if(adminHuSessionId != null && adminHuSessionId.longValue() > 0l)
			query.setParameter("adminHuSessionId", adminHuSessionId);
		return query.list();
		
		
		
	}
}
