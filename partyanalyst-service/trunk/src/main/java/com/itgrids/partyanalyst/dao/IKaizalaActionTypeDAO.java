package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.KaizalaActionType;
import com.itgrids.partyanalyst.model.KaizalaActions;

public interface IKaizalaActionTypeDAO extends GenericDao<KaizalaActionType, Long> {
	public Long getActionTypeId(String actionType);
}