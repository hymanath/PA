package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UnionTabUser;

public interface IUnionTabUserDAO extends GenericDao<UnionTabUser, Long>{
	public List<UnionTabUser> checkUserExists(String uname,String pwd);
}
