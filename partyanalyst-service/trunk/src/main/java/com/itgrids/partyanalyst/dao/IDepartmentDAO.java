package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Department;

public interface IDepartmentDAO extends GenericDao<Department, Long> {

	public List<Object[]> getDepartments();
}
