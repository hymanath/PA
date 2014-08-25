package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.NewsType;
/**
 * Interface for NewsTypeDAO.
 * @author <a href="mailto:sasi.itgrids.hyd@gmail.com">SASI</a>
 * @since 19-AUG-2014
 */
public interface INewsTypeDAO extends GenericDao<NewsType, Long>{
	public List<Object[]> getAllNewsTypes();
}
