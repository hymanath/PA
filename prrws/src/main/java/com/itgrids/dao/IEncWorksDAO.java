package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.EncWorks;

public interface IEncWorksDAO extends GenericDao<EncWorks,Long>{

	public List<Long> getAllDistinctWorkIds();

}
