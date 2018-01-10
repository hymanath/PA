package com.itgrids.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.EncWorks;

public interface IEncWorksDAO extends GenericDao<EncWorks,Long>{

	public List<Long> getAllDistinctWorkIds();

	public List<Object[]> getWorksData(Date fromDate, Date toDate, String status );

	public EncWorks findOneByworkId(Long workId);

}
