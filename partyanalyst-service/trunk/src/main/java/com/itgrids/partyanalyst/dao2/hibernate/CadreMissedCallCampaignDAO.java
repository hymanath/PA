package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreMissedCallCampaignDAO;
import com.itgrids.partyanalyst.model.CadreMissedCallCampaign;



public class CadreMissedCallCampaignDAO extends GenericDaoHibernate<CadreMissedCallCampaign, Long> implements ICadreMissedCallCampaignDAO{

	public CadreMissedCallCampaignDAO() {
		super(CadreMissedCallCampaign.class);
	}

	

	
	public Long getAllMissedCallsCount(Date startDate, Date endDate)
	{
		StringBuilder str = new StringBuilder();
		str.append("SELECT count(distinct model.mobileNumber) from CadreMissedCallCampaign model "); 
		
		if(startDate != null && endDate != null && !startDate.equals(endDate))
		str.append(" where date(model.insertedTime) >=:startDate and date(model.insertedTime) <=:endDate");
		else if(startDate != null && endDate != null && startDate.equals(endDate))
		str.append(" where date(model.insertedTime) >=:startDate");
		
	
		Query query = getSession().createQuery(str.toString());
		if(startDate != null && endDate != null && !startDate.equals(endDate)){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		else if(startDate != null && endDate != null && startDate.equals(endDate))
			query.setDate("startDate", startDate);
		
		return  (Long) query.uniqueResult();
	}
	
	public List<String> getAllMobileNos(Date startDate, Date endDate)
	{
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.mobileNumber from CadreMissedCallCampaign model " );
				
		if(startDate != null && endDate != null && !startDate.equals(endDate))
		str.append(" where date(model.insertedTime) >=:startDate and date(model.insertedTime) <=:endDate");
		else if(startDate != null && endDate != null && startDate.equals(endDate))
		str.append(" where date(model.insertedTime) >=:startDate");
		
		Query query = getSession().createQuery(str.toString());
		if(startDate != null && endDate != null && !startDate.equals(endDate)){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		else if(startDate != null && endDate != null && startDate.equals(endDate))
			query.setDate("startDate", startDate);
	
		return  query.list();
	}

}
