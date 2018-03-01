package com.itgrids.dao;

import java.util.Date;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.WebServiceData;

public interface IWebServiceDataDAO extends GenericDao<WebServiceData, Long>{

	public Long getLatestDataId();
	public String getRfidTrackingOverAllTargetsData(Date fromDate);
}
