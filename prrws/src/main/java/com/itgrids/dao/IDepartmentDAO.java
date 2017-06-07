package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.Department;

public interface IDepartmentDAO extends GenericDao<Department,Long>{
	
	public List<Object[]> getAllDepartments();
	
}
