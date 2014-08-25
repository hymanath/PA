package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Department;

/**
 * Interface for DepartmentDAO.
 * @author <a href="mailto:sasi.itgrids.hyd@gmail.com">SASI</a>
 * @since 19-AUG-2014
 */
public interface IDepartmentDAO extends GenericDao<Department, Long>{
	public List<Object[]> getAllDepartments();
}
