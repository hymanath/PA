package com.itgrids.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.WebServiceData;

public interface IWebServiceDataDAO extends GenericDao<WebServiceData, Long>{
	public String getRfidTrackingOverAllTargetsData(Long webserviceId) ;

	public Long getLatestDataId();
}
