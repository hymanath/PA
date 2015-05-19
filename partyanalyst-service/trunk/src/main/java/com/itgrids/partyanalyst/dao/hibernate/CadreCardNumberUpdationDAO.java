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
		str.append("select count(model.cadreCardNumberUpdationId),model.cardPrintUser.uname,date(model.insertedTime),model.cardPrintUser.cardPrintUserId from CadreCardNumberUpdation model ");
		if((startDate != null && endDate != null) && startDate.equals(endDate))
			str.append(" where date(model.insertedTime) >=:startDate");
		else if((startDate != null))
		str.append(" where date(model.insertedTime) >=:startDate and date(model.insertedTime) <=:endDate ");
		str.append(" group by date(model.insertedTime),model.cardPrintUser.cardPrintUserId  ");
		Query query = getSession().createQuery(str.toString());
		if((startDate != null && endDate != null) && startDate.equals(endDate))
		{
		query.setParameter("startDate", startDate);
		}
		else if((startDate != null && endDate != null) && !startDate.equals(endDate))
		{
		query.setParameter("startDate", startDate);	
		query.setParameter("endDate", endDate);
		}
		return query.list();
		
	}
	
	public List<Object[]> getPrintCountsForUser(Date startDate,Date endDate,Long userId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model.tdpCadre.memberShipNo,model.cardPrintUser.uname,date(model.insertedTime),model.cardPrintUser.cardPrintUserId from CadreCardNumberUpdation model ");
		str.append(" where model.cardPrintUser.cardPrintUserId = :userId");
		if((startDate != null && endDate != null) && startDate.equals(endDate))
			str.append(" and date(model.insertedTime) >=:startDate");
		else if((startDate != null))
		str.append(" and date(model.insertedTime) >=:startDate and date(model.insertedTime) <=:endDate ");
		Query query = getSession().createQuery(str.toString());
		if((startDate != null && endDate != null) && startDate.equals(endDate))
		{
		query.setParameter("startDate", startDate);
		}
		else if((startDate != null && endDate != null) && !startDate.equals(endDate))
		{
		query.setParameter("startDate", startDate);	
		query.setParameter("endDate", endDate);
		}
		query.setParameter("userId", userId);
		return query.list();
		
	}

}
