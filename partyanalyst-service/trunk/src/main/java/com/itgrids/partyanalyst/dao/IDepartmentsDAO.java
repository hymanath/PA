package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Departments;

public interface IDepartmentsDAO extends GenericDao<Departments, Long>{
	public List<Object[]> getDepartments(Long postType);
	public List<Object[]> getDepartmentByIdsList(Long postType,List<Long> deptIds);
}
