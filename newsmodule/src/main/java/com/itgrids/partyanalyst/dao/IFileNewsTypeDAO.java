package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.FileNewsType;

/**
 * Interface for FileNewsTypeDAO.
 * @author <a href="mailto:sasi.itgrids.hyd@gmail.com">SASI</a>
 * @since 19-AUG-2014
 */
public interface IFileNewsTypeDAO extends GenericDao<FileNewsType, Long>{
	public List<Object[]> getNewsTypes(Long fileId);
	public void deleteFileNewsTypes(Long fileId);
}
