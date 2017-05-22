package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.GovtDepartmentRelation;

public interface IGovtDepartmentRelationDAO extends GenericDao<GovtDepartmentRelation, Long>{
	public List<Object[]> getAllMainDepartments();
}
