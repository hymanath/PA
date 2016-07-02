package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Alert;
import com.itgrids.partyanalyst.model.AlertUser;

public interface IAlertUserDAO extends GenericDao<AlertUser, Long> {
	public List<Long> getUserTypeIds(Long userId);
}
