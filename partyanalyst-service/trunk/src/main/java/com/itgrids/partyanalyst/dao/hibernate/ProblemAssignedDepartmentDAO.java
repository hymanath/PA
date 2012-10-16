package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IProblemAssignedDepartmentDAO;
import com.itgrids.partyanalyst.model.ProblemAssignedDepartment;
import com.itgrids.partyanalyst.model.ProblemHistory;
import com.itgrids.partyanalyst.model.UserProblem;
import com.itgrids.partyanalyst.utils.IConstants;

public class ProblemAssignedDepartmentDAO extends GenericDaoHibernate<ProblemAssignedDepartment,Long>
															implements IProblemAssignedDepartmentDAO
{
	public ProblemAssignedDepartmentDAO()
	{
		super(ProblemAssignedDepartment.class);
	}
	@SuppressWarnings("unchecked")
	public List<ProblemAssignedDepartment> getAllActivitesByProblem(Long userProblemId)
	{
		return getHibernateTemplate().find("from ProblemAssignedDepartment model where model.userProblem.userProblemId = ? ",userProblemId);
				
	}
	
	public List<Object[]> getAssignedProblemsProgressByLocation(Long problemId) {
		return getHibernateTemplate().find("select model.departmentOrganisation.organisationName, " +
				" model.updatedTime " +
				"from ProblemAssignedDepartment model where " +
				"model.userProblem.problem.problemId = ? and (model.userProblem.problem.isDelete is null or model.userProblem.problem.isDelete='"+IConstants.FALSE+"')"+
				
				"group by model.userProblem.userProblemId ",problemId);
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<UserProblem> getDepartmentWiseProblemsBasedOnStatus(Long userId,Long deptOrgId,String statusStr)
	{
		Object[] params = {userId,deptOrgId};
		return getHibernateTemplate().find("select model.userProblem from ProblemAssignedDepartment model where model.userProblem.user.userId = ? and " +
				" (model.userProblem.problem.isDelete is null or model.userProblem.problem.isDelete='"+IConstants.FALSE+"')" +
						" and model.userProblem.problem.isApproved = 'true' and  model.departmentOrganisation.departmentOrganisationId = ? "+ statusStr +" group by model.userProblem.userProblemId ",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getDepartmentWiseProblemStatus(Long userId,Long deptOrgId)
	{
		Object[] params = {userId,deptOrgId};
		return getHibernateTemplate().find("select model.userProblem.problem.problemStatus.status from ProblemAssignedDepartment model where model.userProblem.user.userId = ? and " +
				" (model.userProblem.problem.isDelete is null or model.userProblem.problem.isDelete='"+IConstants.FALSE+"') and model.userProblem.problem.isApproved = 'true'" +
						" and model.departmentOrganisation.departmentOrganisationId = ? group by model.userProblem.userProblemId ",params);

	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Long>  getDeptWiseAssignedProblemProgressIds(Long userId,String deptLocationStr)
	{
		Object[] params ={userId};
		return getHibernateTemplate().find("select max(model2.problemAssignedDepartmentId) from ProblemAssignedDepartment model2 where model2.userProblem.userProblemId in (select model.userProblem.userProblemId from ProblemAssignedDepartment model" +
				" where model.userProblem.user.userId = ? and (model.userProblem.problem.isDelete is null or model.userProblem.problem.isDelete='"+IConstants.FALSE+"')" +
						"and model.userProblem.problem.isApproved = 'true'" +deptLocationStr+" group by model.userProblem.userProblemId) group by model2.userProblem.userProblemId",params);
	}
	
	/*@SuppressWarnings("unchecked")
	public List<Object[]> getProblemsStatusBasedOnAssignedProblemProgressId(Long userId,List<Long> progressIdList)
	{
		Query queryObject = getSession().createQuery(" select model.problemHistory.problemStatus.status,model.departmentOrganisation.departmentOrganisationId,model.departmentOrganisation.organisationName from AssignedProblemProgress model where " +
				" model.problemHistory.problemLocation.problemAndProblemSource.user.userId = ?  and model.problemHistory.isDelete is null and model.problemHistory.isApproved = 'true' and model.assignedProblemProgressId in(:progressIdList) ");
		
		queryObject.setParameter(0, userId);
		queryObject.setParameterList("progressIdList", progressIdList);
		return queryObject.list();
	}
	*/
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getProblemsStatusBasedOnAssignedProblemProgressId(Long userId,List<Long> progressIdList)
	{
		Query queryObject = getSession().createQuery("select model.userProblem.problem.problemStatus.status,model.departmentOrganisation.departmentOrganisationId,model.departmentOrganisation.organisationName from ProblemAssignedDepartment model where" +
				" model.userProblem.user.userId = ? and (model.userProblem.problem.isDelete is null or model.userProblem.problem.isDelete='"+IConstants.FALSE+"')" +
						"and model.userProblem.problem.isApproved = 'true' and model.problemAssignedDepartmentId in(:progressIdList)");
		queryObject.setParameter(0, userId);
		queryObject.setParameterList("progressIdList",progressIdList);
		return queryObject.list();
	}
	
	/*@SuppressWarnings("unchecked")
	public List<ProblemHistory> getProblemsBasedOnAssignedProblemProgressIdAndStatus(Long userId,List<Long> progressIdList,String deptStr,String statusStr)
	{
		Query queryObject = getSession().createQuery(" select model.problemHistory from AssignedProblemProgress model where model.problemHistory.problemLocation.problemAndProblemSource.user.userId = ? and " +
				" model.problemHistory.isDelete is null and model.problemHistory.isApproved = 'true' and model.assignedProblemProgressId in(:progressIdList) "+deptStr+" "+ statusStr);
		
		queryObject.setParameter(0, userId);
		queryObject.setParameterList("progressIdList", progressIdList);
		return queryObject.list();
	}*/
	
	@SuppressWarnings("unchecked")
	public List<UserProblem> getProblemsBasedOnAssignedProblemProgressIdAndStatus(Long userId,List<Long> progressIdList,String deptStr,String statusStr)
	{
		Query queryObject = getSession().createQuery("select model.userProblem from ProblemAssignedDepartment model where model.userProblem.user.userId = ? and " +
				"(model.userProblem.problem.isDelete is null or model.userProblem.problem.isDelete='"+IConstants.FALSE+"') and model.userProblem.problem.isApproved = 'true' and model.problemAssignedDepartmentId in(:progressIdList)"+deptStr+" "+statusStr);
		queryObject.setParameter(0, userId);
		queryObject.setParameterList("progressIdList", progressIdList);
		return queryObject.list();
	}
	
	public List<ProblemAssignedDepartment> getAllActivitesByProblemId(Long userProblemId)
	{
		return getHibernateTemplate().find("from ProblemAssignedDepartment model where model.userProblem.userProblemId = ?  order by model.updatedTime desc",userProblemId);
				
	}
	
	public List<Object[]> getProblemIds(Long userId ,List<Long> deptorgids,boolean userProbOnly){
		StringBuilder queryObj = new StringBuilder();
		queryObj.append("select model1.userProblem.problem.problemId ,model1.departmentOrganisation.organisationName from ProblemAssignedDepartment model1 where (model1.userProblem.userProblemId, model1.updatedTime) in (select model.userProblem.userProblemId,max(model.updatedTime) from ProblemAssignedDepartment model group by model.userProblem.userProblemId) ");
		queryObj.append("  and model1.status in ('ASSIGNED','MODIFIED' ) ");
		if(userProbOnly && userId != null){
			queryObj.append("  and (model1.userProblem.user.userId = :userId ");
		}
		else{
		queryObj.append("  and (model1.userProblem.visibility.visibilityId = :visibilityId ");
		if(userId != null)
			queryObj.append("  or model1.userProblem.user.userId = :userId ");
		
		}
		queryObj.append(" ) and model1.userProblem.problem.isApproved = :isApproved and  model1.userProblem.problem.isDelete = :isDelete and model1.departmentOrganisation.departmentOrganisationId in(:deptorgids) ");
		 
		Query query = getSession().createQuery(queryObj.toString());
		if(!(userProbOnly && userId != null)){
		query.setParameter("visibilityId",1l);
		}
		if(userId != null)
			query.setParameter("userId",userId);
		
		query.setParameter("isApproved",IConstants.TRUE);
		query.setParameter("isDelete",IConstants.FALSE);
		query.setParameterList("deptorgids", deptorgids);
		
		  return query.list();
	}
}
