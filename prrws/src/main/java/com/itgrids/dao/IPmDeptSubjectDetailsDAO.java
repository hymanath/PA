package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmDeptSubjectDetails;

public interface IPmDeptSubjectDetailsDAO extends GenericDao<PmDeptSubjectDetails, Long> {
	public List<Object[]> getPmSubjectList(Long deptId);

}
