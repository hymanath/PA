package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.GovtWorkStatus;

public interface IGovtWorkStatusDAO extends GenericDao<GovtWorkStatus, Long>{
	public List<Object[]> getAllStatusOfWorkType(Long workTypeId);
}
