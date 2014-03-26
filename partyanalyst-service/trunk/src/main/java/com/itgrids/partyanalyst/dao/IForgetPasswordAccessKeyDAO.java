
package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ForgetPasswordAccessKey;

public interface IForgetPasswordAccessKeyDAO extends GenericDao<ForgetPasswordAccessKey, Long>{
	
	public ForgetPasswordAccessKey getModelByRandomNumber(String accessKey);
	
	public int updateIsValidated(String accessKey);
	
}