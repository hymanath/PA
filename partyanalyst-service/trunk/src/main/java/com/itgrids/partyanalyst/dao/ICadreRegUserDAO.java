package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreRegUser;

public interface ICadreRegUserDAO extends GenericDao<CadreRegUser, Long> {

	public Long getCadreRegUserByUser(Long userId);
	public Long getCadreRegUserByUserForDataMonitoring(Long userId);
	public Long getCadreRegId(Long loginUserId);
}
