package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.GovtMainWork;

public interface IGovtMainWorkDAO extends GenericDao<GovtMainWork, Long>{
	public List<Object[]> getPraposalWorksCount();
	public List<Object[]> getAllMainWorksForUser(Long userId,Long workTypeId);
}
