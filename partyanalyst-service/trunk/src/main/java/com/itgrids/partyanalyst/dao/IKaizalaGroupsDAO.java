package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.KaizalaGroups;

public interface IKaizalaGroupsDAO extends GenericDao<KaizalaGroups, Long>{

	public Long checkGroupExistence(String groupId);
}
