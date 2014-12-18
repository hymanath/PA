package com.itgrids.partyanalyst.utils;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IDynamicKeysDAO;

public class CommonUtilsService {

	private static final Logger LOG = Logger.getLogger(CommonUtilsService.class);
	private IDynamicKeysDAO dynamicKeysDAO;
	
	public void setDynamicKeysDAO(IDynamicKeysDAO dynamicKeysDAO) {
		this.dynamicKeysDAO = dynamicKeysDAO;
	}
	
	public String getDynamicValueOfAKey(String key)
	{
		String value = null;
		try{
			value = dynamicKeysDAO.getValueByKey(key);
		}catch(Exception e)
		{
			LOG.error("Exception Occured in getDynamicValueOfAKey() Method");
		}
		return value;
	}
	
}
