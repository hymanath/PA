package com.itgrids.partyanalyst.utils;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IDynamicKeysDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;

public class CommonUtilsService {

	private static final Logger LOG = Logger.getLogger(CommonUtilsService.class);
	private IDynamicKeysDAO dynamicKeysDAO;
	private ITdpCadreDAO tdpCadreDAO;
	
	
	public ITdpCadreDAO getTdpCadreDAO() {
		return tdpCadreDAO;
	}


	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}
	
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
	
	public Boolean checkValidMember(String memberShipNo)
	{
		
		Boolean valid = false;
		try{
			
			Long tdpCadreId = tdpCadreDAO.checkMemberExists(memberShipNo);
			if(tdpCadreId != null)
				valid  = true;
			else
				valid = false;	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return valid;
	}

	
}
