package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UserImpDate;

public interface IUserImpDatesDAO extends GenericDao<UserImpDate, Long> {

	public List<UserImpDate> findByUserId(Long userID);

}
