package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.GovtAlertDepartmentLocationNew;

public interface IGovtAlertDepartmentLocationNewDAO extends GenericDao<GovtAlertDepartmentLocationNew, Long> {
	public List<Object[]> getUserAccessLevels(Long userId);
	public List<Object[]> getDeptIdAndNameForUserAccessLevel(Long userId);
}
