package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.KaizalaResponderType;

public interface IKaizalaResponderTypeDAO extends GenericDao<KaizalaResponderType, Long> {
	public Long getResponderType(String type);
}
