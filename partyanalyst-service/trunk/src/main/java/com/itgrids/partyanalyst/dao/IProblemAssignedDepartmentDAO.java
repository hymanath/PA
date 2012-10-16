package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;


import com.itgrids.partyanalyst.model.ProblemAssignedDepartment;
import com.itgrids.partyanalyst.model.UserProblem;

public interface IProblemAssignedDepartmentDAO extends GenericDao<ProblemAssignedDepartment,Long>{
	
	public List<ProblemAssignedDepartment> getAllActivitesByProblem(Long problemId);
	
	public List<Object[]> getAssignedProblemsProgressByLocation(Long problemId);
	
	public List<UserProblem> getDepartmentWiseProblemsBasedOnStatus(Long userId,Long deptOrgId,String statusStr);
	
	public List<Object> getDepartmentWiseProblemStatus(Long userId,Long deptOrgId);
	
	public List<Long>  getDeptWiseAssignedProblemProgressIds(Long userId,String deptLocationStr);
	
	public List<Object[]> getProblemsStatusBasedOnAssignedProblemProgressId(Long userId,List<Long> progressIdList);
	
	public List<UserProblem> getProblemsBasedOnAssignedProblemProgressIdAndStatus(Long userId,List<Long> progressIdList,String deptStr,String statusStr);

	public List<ProblemAssignedDepartment> getAllActivitesByProblemId(Long userProblemId);
	
	public List<Object[]> getProblemIds(Long userId ,List<Long> deptorgids,boolean userProbOnly);
}
