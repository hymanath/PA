package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UserEmail;

public interface IUserEmailDAO extends GenericDao<UserEmail, Long> {
	public List<Object[]> getEmailList(Long emailId);

}
