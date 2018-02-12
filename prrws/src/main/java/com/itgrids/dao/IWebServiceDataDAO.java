package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.dto.WebServiceDataVO;
import com.itgrids.model.WebServiceData;

public interface IWebServiceDataDAO extends GenericDao<WebServiceData, Long>{
	public List<Object[]> getRfidTrackingOverAllTargetsData(Long webserviceId) ;

}
