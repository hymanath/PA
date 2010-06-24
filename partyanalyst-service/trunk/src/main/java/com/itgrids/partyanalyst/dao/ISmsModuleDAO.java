package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SmsModule;

public interface ISmsModuleDAO extends GenericDao<SmsModule, Long> {

	
	List<SmsModule> findBySmsModuleId(Long smsModuleId);
	
	List<SmsModule> findByModuleName(String moduleName);
	
}
