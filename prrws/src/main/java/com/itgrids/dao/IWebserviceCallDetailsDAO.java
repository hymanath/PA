package com.itgrids.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.model.WebserviceCallDetails;

public interface IWebserviceCallDetailsDAO extends GenericDao<WebserviceCallDetails,Long>{
	public List<Object[]> getWebserviceHealthDetails(Date startDate, Date endDate);
	public List<Object[]> getWebserviceFailureDetails(Date startDate, Date endDate);
	public List<Object[]> getWebserviceDetails(Date startDate, Date endDate);
}
