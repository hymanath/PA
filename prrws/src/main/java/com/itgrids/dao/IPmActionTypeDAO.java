package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmActionType;

public interface IPmActionTypeDAO extends GenericDao<PmActionType, Long> {
	public List<Object[]> getPmActionTypeList();

}
