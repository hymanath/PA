package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.KaizalaActions;

public interface IKaizalaActionsDAO extends GenericDao<KaizalaActions, Long> {
	public List<Long> getKaizalaActionId(String actionId);
}