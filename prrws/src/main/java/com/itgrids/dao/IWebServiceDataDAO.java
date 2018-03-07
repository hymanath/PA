package com.itgrids.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.WebServiceData;

public interface IWebServiceDataDAO extends GenericDao<WebServiceData, Long>{

	public Long getLatestDataId();
	
	public String getRfidTrackingOverAllTargetsData(Date fromDate);
	
	public List<Object[]> getWebserviceResponseData(Long webserviceId,String input);
}
