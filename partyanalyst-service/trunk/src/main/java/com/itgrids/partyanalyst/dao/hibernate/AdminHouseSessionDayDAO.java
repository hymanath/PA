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

	public List<Object[]> getSingleDate(Long termId,String sessionYear,Long sessionId){
		StringBuilder sb = new StringBuilder();
			sb.append("select model.adminHouseSessionDayId," +
					" date(model.sessionDate)" +
					" from AdminHouseSessionDay model " +
					" where model.isDeleted = 'N' and model.adminHouseSession.isActive ='Y' and model.adminHouseSession.isDeleted = 'N' ");
		if(termId != null && termId.longValue() > 0l)
		{
			sb.append(" and model.adminHouseSession.adminHouseTerm.adminHouseTermId = :termId");
		}
		if(sessionYear != null && !sessionYear.toString().isEmpty())
		{
			sb.append(" and model.adminHouseSession.year = :sessionYear");
		}
		if(sessionId != null && sessionId.longValue() > 0l)
		{
			sb.append(" and model.adminHouseSession.houseSession.houseSessionId = :sessionId");
		}
		Query query = getSession().createQuery(sb.toString());
		if(termId != null && termId.longValue() > 0l)
			query.setParameter("termId", termId);
		if(sessionYear != null && !sessionYear.toString().isEmpty())
			query.setParameter("sessionYear", sessionYear);
		if(sessionId != null && sessionId.longValue() > 0l)
			query.setParameter("sessionId", sessionId);
		
		return query.list();
		
		
		
	}
}
