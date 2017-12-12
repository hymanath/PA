package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmDepartment;

public interface IPmDepartmentDAO extends GenericDao<PmDepartment, Long> {
	public List<Object[]> getAllPmDepartmentsList();
}
