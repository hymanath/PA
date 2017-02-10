package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.GovtDepartment;

public interface IGovtDepartmentDAO extends GenericDao<GovtDepartment, Long>{
	public List<Object[]> getAllDepartment();
	public List<Long> getGovtDepartmentIdsOfNewsDept(List<Long> newsDepartmentIds);
}
