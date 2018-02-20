package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmRefCandidateDepartment;

public interface IPmRefCandidateDepartmentDAO extends GenericDao<PmRefCandidateDepartment, Long>{
	
	public List<Object[]> getPmRefCandidateDepartments(List<Long> refCandIds);
	}
