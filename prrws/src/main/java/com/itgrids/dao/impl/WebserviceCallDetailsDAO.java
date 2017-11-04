package com.itgrids.dao.impl;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IWebserviceCallDetailsDAO;
import com.itgrids.model.WebserviceCallDetails;

@Repository
public class WebserviceCallDetailsDAO extends GenericDaoHibernate<WebserviceCallDetails,Long> implements IWebserviceCallDetailsDAO{

	public WebserviceCallDetailsDAO()
	{
		super(WebserviceCallDetails.class);
	}
	@Override
	public List<Object[]> getWebserviceHealthDetails(Date startDate, Date endDate){
		StringBuilder sb = new StringBuilder();
		sb.append(" select "
				+ " webserviceCallDetails.webservice.serviceProvider.serviceProviderId, "//0
				+ " webserviceCallDetails.webservice.serviceProvider.providerName, "//1
				+ " webserviceCallDetails.webservice.serviceModule.serviceModuleId, "//2
				+ " webserviceCallDetails.webservice.serviceModule.moduleName, "//3
				+ " webserviceCallDetails.webservice.webserviceId, "//4
				+ " webserviceCallDetails.webservice.serviceName, "//5
				+ " webserviceCallDetails.status, "//6
				+ " count(webserviceCallDetails.status), "//7
				+ " sum(webserviceCallDetails.timeTaken) "//8
				+ " from "
				+ " WebserviceCallDetails webserviceCallDetails "
				+ " where "
				+ " (date(webserviceCallDetails.callTime) between :startDate and :endDate) "
				+ " group by "
				+ " webserviceCallDetails.webservice.webserviceId, "
				+ " webserviceCallDetails.status ");
		Query query = getSession().createQuery(sb.toString());
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
		return query.list();  
	}
	@Override
	public List<Object[]> getWebserviceFailureDetails(Date startDate, Date endDate){
		StringBuilder sb = new StringBuilder();
		sb.append(" select "
				+ " webserviceCallDetails.webservice.webserviceId, "//0
				+ " count(webserviceCallDetails.webserviceCallDetailsId) "//1
				+ " from "
				+ " WebserviceCallDetails webserviceCallDetails "
				+ " where "
				+ " (date(webserviceCallDetails.callTime) between :startDate and :endDate) and "
				+ " webserviceCallDetails.timeTaken is null "
				+ " group by "
				+ " webserviceCallDetails.webservice.webserviceId");
		Query query = getSession().createQuery(sb.toString());
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
		return query.list();  
	}
	@Override
	public List<Object[]> getWebserviceDetails(Date startDate, Date endDate) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select "
				+ " webserviceCallDetails.webservice.webserviceId, "//0
				+ " webserviceCallDetails.webservice.url, "//1
				+ " webserviceCallDetails.webservice.serviceName, "//2
				+ " webserviceCallDetails.callTime, " //3
				+ " webserviceCallDetails.timeTaken, " //4
				+ " webserviceCallDetails.webservice.serviceProvider.providerName," //5
				+ " webserviceCallDetails.status " //6
				+ " from "
				+ " WebserviceCallDetails webserviceCallDetails "
				+ " where "
				+ " (date(webserviceCallDetails.callTime) between :startDate and :endDate)  "
				+ " order by "
				+ " webserviceCallDetails.webservice.webserviceId , webserviceCallDetails.callTime ");
		Query query = getSession().createQuery(sb.toString());
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
		return query.list();  
	}
}
