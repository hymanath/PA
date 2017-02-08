package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AlertDepartmentStatus;

public interface IAlertDepartmentStatusDAO extends GenericDao<AlertDepartmentStatus, Long>{
	public List<Object[]> getAllStatus();
}
