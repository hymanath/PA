package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreRegUserTabUser;

public interface ICadreRegUserTabUserDAO extends GenericDao<CadreRegUserTabUser, Long>{

	public List<Object[]> getUserAssignedConstituencies(Long cadreRegUserId);
	public List<Object[]> getUserAssignedUsers(Long cadreRegUserId,Long constituencyId);
}
