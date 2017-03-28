package com.itgrids.partyanalyst.dao.impl;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AlertSource;
import com.itgrids.partyanalyst.model.AlertSourceUser;

public interface IAlertSourceUserDAO extends
GenericDao<AlertSourceUser, Long>{
	public List<Object[]> getAlertSourceUsersByUser(Long userId);
	public List<Long> getAlertSourceUserIds(Long userId);

}
