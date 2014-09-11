package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.DuplicateWrongMobileNumbers;

public interface IDuplicateWrongMobileNumbersDAO extends GenericDao<DuplicateWrongMobileNumbers, Long>{
	
	public Long getIsExistMobileDetails(String mobileNo);
	
	public List<Object[]> getInvalidMobileNumbers();
	
}
