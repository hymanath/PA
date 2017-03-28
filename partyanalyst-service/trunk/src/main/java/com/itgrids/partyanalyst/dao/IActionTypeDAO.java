package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ActionType;

public interface IActionTypeDAO extends GenericDao<ActionType, Long> {
	public List<Object[]> getActionTypeList();
}
