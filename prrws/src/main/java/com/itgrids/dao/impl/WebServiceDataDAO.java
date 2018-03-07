package com.itgrids.dao.impl;

import java.util.Date;
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
	public String getRfidTrackingOverAllTargetsData(Date fromDate) {		
		Query query = getSession().createQuery("select model.responceData from WebServiceData model where  "
					+ " model.webServiceDataId in (select max(model1.webServiceDataId) from WebServiceData  model1 where date(model1.insertedTime) =:fromDate and model1.webserviceId=127) ");
			
		if(fromDate != null){
		query.setParameter("fromDate", fromDate);
		}
			//query.setParameter("webserviceId", webserviceId);
			return (String)query.uniqueResult();
		}
	

	public Long getLatestDataId() {
		Query query = getSession().createQuery(" select model.webServiceDataId from WebServiceData model where model.webserviceId = 127 ");
		return (Long)query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getWebserviceResponseData(Long webserviceId,String input) {
		
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT model.responceData,model.insertedTime FROM WebServiceData model where model.isDeleted ='N' AND ");
		sb.append(" model.webserviceId = :webserviceId AND model.responceData IS NOT NULL ");
		
		if(input != null && input.trim().length() > 0)
			sb.append(" AND model.inputData = :input ");
		
		sb.append(" ORDER BY model.webServiceDataId DESC ");
		
		Query query = getSession().createQuery(sb.toString());
		
		query.setParameter("webserviceId",webserviceId);
		
		if(input != null && input.trim().length() > 0)
			query.setParameter("input",input);
		
		query.setMaxResults(1);
		return query.list();
	}

	
	
}