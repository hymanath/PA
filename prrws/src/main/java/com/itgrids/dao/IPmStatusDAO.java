package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmStatus;

public interface IPmStatusDAO extends GenericDao<PmStatus, Long> {
	public List<Object[]> getPmStatusList(Long statId);

}
