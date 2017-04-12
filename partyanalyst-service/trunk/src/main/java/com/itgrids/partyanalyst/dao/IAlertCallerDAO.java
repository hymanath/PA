package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AlertCaller;

public interface IAlertCallerDAO extends GenericDao<AlertCaller, Long>{
	public List<Long> checkIsExist(String mobileNO,String callerName);
}
