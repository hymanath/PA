package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.DuplicateWrongMobileNumbers;

public interface IDuplicateWrongMobileNumbersDAO extends GenericDao<DuplicateWrongMobileNumbers, Long>{
	
	public List<Object[]> getIsExistMobileDetails(List<String> mobileNosList);
	
	public List<Object[]> getInvalidMobileNumbers();
	
}
