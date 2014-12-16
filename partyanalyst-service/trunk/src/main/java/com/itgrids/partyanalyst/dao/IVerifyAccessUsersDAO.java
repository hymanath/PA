package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VerifyAccessUsers;

public interface IVerifyAccessUsersDAO extends GenericDao<VerifyAccessUsers, Long> {

	public List<String> getUserStatus(List<Long> userIds);
}
