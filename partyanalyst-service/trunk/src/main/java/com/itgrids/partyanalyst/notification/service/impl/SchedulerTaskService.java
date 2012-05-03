package com.itgrids.partyanalyst.notification.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.ISearchEngineIPAddressDAO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.notification.service.ISchedulerTaskService;

public class SchedulerTaskService implements ISchedulerTaskService{
	
	private final static Logger log = Logger.getLogger(SchedulerTaskService.class);
	
	private ISearchEngineIPAddressDAO searchEngineIPAddressDAO;
	
	public ISearchEngineIPAddressDAO getSearchEngineIPAddressDAO() {
		return searchEngineIPAddressDAO;
	}

	public void setSearchEngineIPAddressDAO(
			ISearchEngineIPAddressDAO searchEngineIPAddressDAO) {
		this.searchEngineIPAddressDAO = searchEngineIPAddressDAO;
	}

	public ResultStatus deleteSearchEngineAccessedURLsFromUserTracking()
	{
		ResultStatus resultStatus = new ResultStatus();
		try{
			List<String> ipAddressList = getAllSearchEngineIPAddresses();
			
			if(ipAddressList != null && ipAddressList.size() > 0)
			{
				
			}
			return resultStatus;
		}catch (Exception e) {
			log.error("Exception Occured in deleteSearchEngineAccessedURLsFromUserTracking() Method, Exception is - "+e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
	}
	
	public List<String> getAllSearchEngineIPAddresses()
	{
		log.debug("Entered into getAllSearchEngineIPAddresses() Method");
		try{
			return searchEngineIPAddressDAO.getAllSearchEngineIPAddresses();
		}catch (Exception e) {
			log.error("Exception Occured in getAllSearchEngineIPAddresses() Method, Exception is - "+e);
			return null;
		}
	}
}
