package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAdminHouseSessionDAO;
import com.itgrids.partyanalyst.model.AdminHouseSession;

public class AdminHouseSessionDAO extends GenericDaoHibernate<AdminHouseSession, Long> implements IAdminHouseSessionDAO {
	public AdminHouseSessionDAO() {
		super(AdminHouseSession.class);

	}
	
	public List<Object[]> getAllSessions(Long termId,String sessionYr){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select model.adminHouseSessionId,model.houseSession.sessionName from AdminHouseSession model where " +
				" model.houseSession.isDeleted = 'N' and model.houseSession.isActive = 'Y' and " +
				"model.houseSession.adminHouse.adminHouseId = 3 and model.adminHouseTerm.isDeleted = 'N' and model.adminHouseTerm.isActive = 'Y' " );
		 if(termId != null && termId.longValue() > 0l){
			 sb.append(" and model.adminHouseTerm.adminHouseTermId = :termId " ); 
		 }
		 
		 if(sessionYr != null && !sessionYr.toString().isEmpty()){
			 sb.append(" and model.year = :sessionYr " ); 
		 }
		 
		 Query query = getSession().createQuery(sb.toString());
			if(termId != null && termId.longValue() > 0l)
				query.setParameter("termId", termId);
			if(sessionYr != null  && !sessionYr.toString().isEmpty())
				query.setParameter("sessionYr", sessionYr);
			
			return query.list();
	}
	
	public List<Object[]> getAllSessionYears(Long termId){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select distinct model.year,model.yearDesc from AdminHouseSession model where " +
				" model.houseSession.adminHouse.adminHouseId = 3 and model.adminHouseTerm.isDeleted = 'N' " +
				" and model.adminHouseTerm.isActive = 'Y'  and model.isActive = 'Y' and model.isDeleted = 'N' " );
		 if(termId != null && termId.longValue() > 0l){
			 sb.append(" and model.adminHouseTerm.adminHouseTermId = :termId " ); 
		 }
		 
		Query query = getSession().createQuery(sb.toString());
			if(termId != null && termId.longValue() > 0l)
				query.setParameter("termId", termId);
			
			return query.list();
	}
	
	public Object[] getDates(Long termId,String sessionYear,Long sessionId){
		StringBuilder sb = new StringBuilder();
		sb.append("select min(model.fromDate),max(model.toDate) " +
				" from AdminHouseSession model  where " +
				" model.houseSession.adminHouse.adminHouseId = 3 and model.adminHouseTerm.isDeleted = 'N' " +
				" and model.adminHouseTerm.isActive = 'Y' and model.isActive = 'Y' and model.isDeleted = 'N' " );
		if(termId != null && termId.longValue() > 0l){
			sb.append(" and model.adminHouseTerm.adminHouseTermId = :termId " ); 
		}
		if(sessionYear != null){
			sb.append(" and model.year = :sessionYear");
		}
		if(sessionId != null && sessionId.longValue() > 0l){
			sb.append(" and model.houseSession.houseSessionId = :sessionId");
		}
		Query query = getSession().createQuery(sb.toString());
			if(termId != null && termId.longValue() > 0l)
				query.setParameter("termId", termId);
			if(sessionYear != null)
				query.setParameter("sessionYear", sessionYear);
			if(sessionId != null && sessionId.longValue() > 0l)
				query.setParameter("sessionId", sessionId);
			
			return (Object[]) query.uniqueResult();
			
		
	}
}
