package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.FileDepartment;

/**
 * Interface for FileDepartmentDAO.
 * @author <a href="mailto:sasi.itgrids.hyd@gmail.com">SASI</a>
 * @since 19-AUG-2014
 */

public interface IFileDepartmentDAO extends GenericDao<FileDepartment, Long>{
	public List<Object[]> getDepartmentsOfFile(Long fileId);
	public void deleteFileDepartments(Long fileId);
}
