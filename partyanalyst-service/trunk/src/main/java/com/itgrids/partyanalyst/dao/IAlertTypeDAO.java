package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Alert;
import com.itgrids.partyanalyst.model.AlertType;

public interface IAlertTypeDAO extends GenericDao<AlertType, Long> {
	
	public List<Object[]> getAlertType();
	public List<Object[]> getAlertTypeForOrganization();
	public List<Long> getIdOfName(String type);
}
