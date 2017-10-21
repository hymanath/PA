package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.KaizalaGroups;

public interface IKaizalaGroupsDAO extends GenericDao<KaizalaGroups, Long>{

	public List<Long> checkGroupExistence(String groupId);
	public Integer removeParentGroup(Long kaizalaGroupId);
}
