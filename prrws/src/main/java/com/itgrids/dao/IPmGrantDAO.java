package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmGrant;

public interface IPmGrantDAO extends GenericDao<PmGrant, Long> {
	public List<Object[]> getPmGrantList();

}
