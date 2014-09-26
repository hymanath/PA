package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Group;

public interface IGroupDAO extends GenericDao<Group,Long> {

	public List<Object[]> getAllGroups();
}
