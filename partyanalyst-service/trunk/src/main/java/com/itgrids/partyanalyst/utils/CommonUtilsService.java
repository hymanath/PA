package com.itgrids.partyanalyst.utils;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IDynamicKeysDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dto.TdpCadreVO;
import com.itgrids.partyanalyst.service.ICadreDetailsService;

public class CommonUtilsService {

	private static final Logger LOG = Logger.getLogger(CommonUtilsService.class);
	private IDynamicKeysDAO dynamicKeysDAO;
	private ITdpCadreDAO tdpCadreDAO;
	private ICadreDetailsService cadreDetailsService;
	
	
	public void setCadreDetailsService(ICadreDetailsService cadreDetailsService) {
		this.cadreDetailsService = cadreDetailsService;
	}
	
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
			/*
			if(memberShipNo == null || memberShipNo.isEmpty() || memberShipNo.toString().trim().length() <= 7)
				return false;
		    Long tdpCadreId = tdpCadreDAO.checkMemberExists(memberShipNo);
			if(tdpCadreId != null)
				valid  = true;
			else
				valid = false;	
			 */
			if(memberShipNo == null || memberShipNo.trim().isEmpty() || memberShipNo.toString().trim().length() <= 7)
				return false; 
			
			TdpCadreVO tdpCadreVO = cadreDetailsService.searchTdpCadreDetailsBySearchCriteriaForCommitte(0L, 0L, "", memberShipNo, "", "", "", 0L, "");
			if(tdpCadreVO != null)
			{
				if(tdpCadreVO.getTdpCadreDetailsList() != null && tdpCadreVO.getTdpCadreDetailsList().size()>0)
				{
					valid  = true;
				}
				else
				{
					valid = false;	
				}
			}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return valid;
	}

	
}
