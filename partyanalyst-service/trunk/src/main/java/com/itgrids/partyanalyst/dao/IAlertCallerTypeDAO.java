package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AlertCallerType;

public interface IAlertCallerTypeDAO extends GenericDao<AlertCallerType, Long>{

	public List<Object[]> getAlertCallerTypesByOrder();
}
