package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.StudentParentDetails;

public interface IStudentParentDetailsDAO extends GenericDao<StudentParentDetails, Long>{
	public List<Object[]> getParentDetails(Long studentId);
}
