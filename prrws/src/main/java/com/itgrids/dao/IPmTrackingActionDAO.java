package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmTrackingAction;

public interface IPmTrackingActionDAO extends GenericDao<PmTrackingAction, Long> {
	public List<Object[]> getActionsList();
}
