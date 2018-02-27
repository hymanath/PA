package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.BiometricDeviceLog;

public interface IBiometricDeviceLogDAO extends GenericDao<BiometricDeviceLog,Long>{
	
	public Integer getMaxLogIdForAMonth(Integer year,Integer month);
}
