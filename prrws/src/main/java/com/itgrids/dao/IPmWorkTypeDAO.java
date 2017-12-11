package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmWorkType;

public interface IPmWorkTypeDAO extends GenericDao<PmWorkType, Long> {
	public List<Object[]> getWorkTypeList();

}
