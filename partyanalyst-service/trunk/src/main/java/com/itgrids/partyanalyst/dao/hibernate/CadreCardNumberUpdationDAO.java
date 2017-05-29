package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreCardNumberUpdationDAO;
import com.itgrids.partyanalyst.model.CadreCardNumberUpdation;

public class CadreCardNumberUpdationDAO extends GenericDaoHibernate<CadreCardNumberUpdation, Long> implements ICadreCardNumberUpdationDAO{

	public CadreCardNumberUpdationDAO() {
		super(CadreCardNumberUpdation.class);
		// TODO Auto-generated constructor stub
	}
	
	public List<Object[]> getPrintCountsForAllUser(Date startDate,Date endDate)
	{
		StringBuilder str = new StringBuilder();
		str.append("select count(model.cadreCardNumberUpdationId),model.cardPrintUser.uname,date(model.insertedTime),model.cardPrintUser.cardPrintUserId from CadreCardNumberUpdation model " +
				" where model.cardNumber is null");
		if((startDate != null && endDate != null) && startDate.equals(endDate))
			str.append("  and date(model.insertedTime) =:startDate");
		else if((startDate != null))
		str.append("  and date(model.insertedTime) >=:startDate and date(model.insertedTime) <=:endDate ");
		str.append(" group by date(model.insertedTime),model.cardPrintUser.cardPrintUserId  ");
		Query query = getSession().createQuery(str.toString());
		if((startDate != null && endDate != null) && startDate.equals(endDate))
		{
			query.setDate("startDate", startDate);
		}
		else if((startDate != null && endDate != null) && !startDate.equals(endDate))
		{
			query.setDate("startDate", startDate);	
			query.setDate("endDate", endDate);
		}
		return query.list();
		
	}
	
	public List<Object[]> getReprintCountsForAllUser(Date startDate,Date endDate)
	{
		StringBuilder str = new StringBuilder();
		str.append("select count(model.cadreCardNumberUpdationId),model.cardPrintUser.uname,date(model.insertedTime),model.cardPrintUser.cardPrintUserId from CadreCardNumberUpdation model " +
				" where model.cardNumber is not null");
		if((startDate != null && endDate != null) && startDate.equals(endDate))
			str.append("  and date(model.insertedTime) =:startDate");
		else if((startDate != null))
		str.append("  and date(model.insertedTime) >=:startDate and date(model.insertedTime) <=:endDate ");
		str.append(" group by date(model.insertedTime),model.cardPrintUser.cardPrintUserId  ");
		Query query = getSession().createQuery(str.toString());
		if((startDate != null && endDate != null) && startDate.equals(endDate))
		{
			query.setDate("startDate", startDate);
		}
		else if((startDate != null && endDate != null) && !startDate.equals(endDate))
		{
			query.setDate("startDate", startDate);	
			query.setDate("endDate", endDate);
		}
		return query.list();
		
	}
	
	public List<Object[]> getPrintCountsForUser(Date startDate,Date endDate,Long userId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model.tdpCadre.memberShipNo,model.cardPrintUser.uname,date(model.insertedTime),model.cardPrintUser.cardPrintUserId,model.cardNumber from CadreCardNumberUpdation model ");
		str.append(" where model.cardPrintUser.cardPrintUserId = :userId");
		if((startDate != null && endDate != null) && startDate.equals(endDate))
			str.append(" and date(model.insertedTime) =:startDate");
		else if((startDate != null))
		str.append(" and date(model.insertedTime) >=:startDate and date(model.insertedTime) <=:endDate ");
		Query query = getSession().createQuery(str.toString());
		if((startDate != null && endDate != null) && startDate.equals(endDate))
		{
			query.setDate("startDate", startDate);
		}
		else if((startDate != null && endDate != null) && !startDate.equals(endDate))
		{
		query.setDate("startDate", startDate);	
		query.setDate("endDate", endDate);
		}
		query.setParameter("userId", userId);
		return query.list();
		
	}
	
	public List getReprintCountsByDate(Date startDate,Date endDate,List<Long> enrollmentYearIds)
	{
		StringBuilder str = new StringBuilder();
		str.append("select count(model.cadreCardNumberUpdationId) from CadreCardNumberUpdation model,TdpCadreEnrollmentYear model1 " +
				" where model.cardNumber is not null and model1.tdpCadre.tdpCadreId = model.tdpCadre.tdpCadreId and model1.isDeleted = 'N' ");
		if((startDate != null && endDate != null) && startDate.equals(endDate))
			str.append("  and date(model.insertedTime) =:startDate");
		else if((startDate != null))
		str.append("  and date(model.insertedTime) >=:startDate and date(model.insertedTime) <=:endDate ");
	    if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
	    	str.append(" and model1.enrollmentYear.enrollmentYearId in (:enrollmentYearIds) ");	
	    }
		Query query = getSession().createQuery(str.toString());
		if((startDate != null && endDate != null) && startDate.equals(endDate))
		{
			query.setDate("startDate", startDate);
		}
		else if((startDate != null && endDate != null) && !startDate.equals(endDate))
		{
			query.setDate("startDate", startDate);	
			query.setDate("endDate", endDate);
		}
		 if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
			 query.setParameterList("enrollmentYearIds", enrollmentYearIds); 
		 }
		return query.list();
		
	}

}
