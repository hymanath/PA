package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.GovtDepartmentDesignation;

public interface IGovtDepartmentDesignationDAO extends GenericDao<GovtDepartmentDesignation, Long>{

	public List<Object[]> getDesignationsForDepartment(Long govtDepartmentId);
}
