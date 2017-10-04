package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.KaizalaGroupType;

public interface IKaizalaGroupTypeDAO extends GenericDao<KaizalaGroupType, Long>{
	public Long checkGroupTypeExistence(String type);
}
