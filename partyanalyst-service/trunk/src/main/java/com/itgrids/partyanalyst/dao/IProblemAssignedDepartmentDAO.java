package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.ProblemAssignedDepartment;

public interface IProblemAssignedDepartmentDAO extends GenericDao<ProblemAssignedDepartment,Long>{
	
	public List<ProblemAssignedDepartment> getAllActivitesByProblem(Long problemId);

}
