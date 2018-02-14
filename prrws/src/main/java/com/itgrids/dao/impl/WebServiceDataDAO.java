package com.itgrids.dao.impl;

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
	
}