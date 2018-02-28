package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IWebServiceDataDAO;
import com.itgrids.model.WebServiceData;

@Repository
public class WebServiceDataDAO extends GenericDaoHibernate<WebServiceData,Long> implements IWebServiceDataDAO{

	public WebServiceDataDAO()
	{
		super(WebServiceData.class);
	}
	
	@Override
	public String getRfidTrackingOverAllTargetsData(Long webserviceId) {		
		Query query = getSession().createQuery("select  model.responceData from WebServiceData model where model.isDeleted ='N' ");
		//query.setParameter("webserviceId", webserviceId);
		return (String)query.uniqueResult();
	}

	@Override
	public Long getLatestDataId() {
		Query query = getSession().createQuery(" select model.webServiceDataId from WebServiceData model where model.isDeleted ='N' ");
		return (Long)query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public String getWebserviceResponseData(Long webserviceId,String input) {
		
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT model.responceData FROM WebServiceData model where model.isDeleted ='N' AND model.webserviceId = :webserviceId ");
		sb.append(" AND model.responceData IS NOT NULL ");
		
		if(input != null && input.trim().length() > 0)
			sb.append(" AND model.inputData = :input ");
		
		sb.append(" ORDER BY model.webServiceDataId DESC ");
		
		Query query = getSession().createQuery(sb.toString());
		
		query.setParameter("webserviceId",webserviceId);
		
		if(input != null && input.trim().length() > 0)
			query.setParameter("input",input);
		
		query.setMaxResults(1);
		
		List<Object> list = query.list();
		
		if(list != null && list.size() > 0)
			return list.get(0).toString();
		else
			return null;
	}
	
}