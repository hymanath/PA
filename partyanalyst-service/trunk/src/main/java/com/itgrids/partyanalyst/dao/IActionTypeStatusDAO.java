package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ActionTypeStatus;

public interface IActionTypeStatusDAO extends GenericDao<ActionTypeStatus, Long> {
	public List<Object[]> getActionTypeList();
	public List<Object[]> getAlertActionTypeWiseStatus(Long actionTypeId);
}
