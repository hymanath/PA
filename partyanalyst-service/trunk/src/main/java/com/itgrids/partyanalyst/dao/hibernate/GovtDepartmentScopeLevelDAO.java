package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtDepartmentScopeLevelDAO;
import com.itgrids.partyanalyst.model.GovtDepartmentScopeLevel;

public class GovtDepartmentScopeLevelDAO extends GenericDaoHibernate<GovtDepartmentScopeLevel, Long> implements IGovtDepartmentScopeLevelDAO{

	public GovtDepartmentScopeLevelDAO(){
		super(GovtDepartmentScopeLevel.class);
	}
	
	public List<Object[]> getDepartmentLevels(Long deptId){
		
		Query query = getSession().createQuery(" SELECT model.govtDepartmentScope.govtDepartmentScopeId,model.govtDepartmentScope.levelName " +
												" FROM GovtDepartmentScopeLevel model " +
												" WHERE model.isDeleted = 'N' " +
												" AND model.govtDepartmentId = :deptId " +
												" GROUP BY  model.govtDepartmentScope.govtDepartmentScopeId" +
												" ORDER BY model.orderNo  ");
		
		query.setParameter("deptId", deptId);
		
		return query.list();
	}
	
	public List<Object[]> getParentLevelsOfLevel(Long deptId,Long levelId){
		
		Query query = getSession().createQuery(" SELECT model.parentGovtDepartmentScope.govtDepartmentScopeId,model.parentGovtDepartmentScope.levelName" +
				" FROM GovtDepartmentScopeLevel model " +
				" WHERE model.isDeleted = 'N' " +
				" AND model.govtDepartmentId =:deptId" +
				" AND model.govtDepartmentScopeId =:levelId " +
				" GROUP BY  model.parentGovtDepartmentScope.govtDepartmentScopeId " +
				" ORDER BY model.orderNo  ");
		
	query.setParameter("deptId", deptId);
	query.setParameter("levelId", levelId);
		
		return query.list();
		
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> govtDepartmentScopeLevelDetails(Long scopeId){
   		Query query = getSession().createQuery(" " +
   				" select " +
   				" model.govtDepartmentId," +
   				" model.govtDepartmentScopeId " +
   				" from GovtDepartmentScopeLevel model " +
   				" where " +
   				" model.parentGovtDepartmentScopeId =:scopeId and model.isDeleted ='N' ");
   		query.setParameter("scopeId",scopeId);
   		return query.list();
     }
	public List<Object[]> getChildDeptScopeIdList(Long govtDepartmentId,Long parentGovtDepartmentScopeId){
		Query query = getSession().createQuery(" select model.govtDepartment.govtDepartmentId, " +
				" model.govtDepartmentScopeId,model.govtDepartmentScope.levelName " +
				" from GovtDepartmentScopeLevel model " +
				" where " +
				" model.govtDepartment.govtDepartmentId = :govtDepartmentId " +
				" and model.parentGovtDepartmentScope.govtDepartmentScopeId =:parentGovtDepartmentScopeId");
		query.setParameter("govtDepartmentId",govtDepartmentId);
		query.setParameter("parentGovtDepartmentScopeId",parentGovtDepartmentScopeId);
		return query.list();
	}
	
	public List<Object[]> getDepartmentSubLevels(Long departmentId,Long parentlevelId){
		Query query = getSession().createQuery(" select model.govtDepartmentScope.govtDepartmentScopeId, " +
				" model.govtDepartmentScope.levelName " +
				" from GovtDepartmentScopeLevel model " +
				" where " +
				" model.govtDepartment.govtDepartmentId = :departmentId " +
				" and model.parentGovtDepartmentScope.govtDepartmentScopeId =:parentlevelId");
		
		query.setParameter("departmentId",departmentId);
		query.setParameter("parentlevelId",parentlevelId);
		return query.list();
	}
	public List<Object[]> getAllScopesInAscOrder(Long govtDepartmentId){
		Query query = getSession().createQuery(" select distinct " +
											   " model.govtDepartment.govtDepartmentId," +
											   " model.parentGovtDepartmentScopeId " +
											   " from GovtDepartmentScopeLevel model " +
											   " where " +
											   " model.govtDepartment.govtDepartmentId =:govtDepartmentId " +
											   " order by model.parentGovtDepartmentScopeId ");
		query.setParameter("govtDepartmentId", govtDepartmentId);
		//query.setFirstResult(0);
		//query.setMaxResults(4);
		return query.list();
	}
	public List<Object[]> getAllScopesOfAllDeptInAscOrder(List<Long> deptIds){
		Query query = getSession().createQuery(" select distinct " +
											   " model.govtDepartment.govtDepartmentId," +
											   " model.parentGovtDepartmentScopeId," +
											   " model.parentGovtDepartmentScope.levelName," +
											   " model.parentGovtDepartmentScope.color " +
											   " from GovtDepartmentScopeLevel model " +
											   " where " +
											   " model.govtDepartment.govtDepartmentId in (:deptIds) " +
											   " order by model.govtDepartment.govtDepartmentId,model.orderNo ");
		query.setParameterList("deptIds", deptIds);
		  
		return query.list();
	}
	public List<Object[]> getChildGovtScopesLevelByParentScopeLevel(Long parentScopeId,Long deptId){
		Query query = getSession().createQuery(" select distinct " +
											   " model.govtDepartment.govtDepartmentId," +
											   " model.parentGovtDepartmentScopeId," +
											   " model.govtDepartmentScopeId " +
											   " from GovtDepartmentScopeLevel model " +
											   " where " +
											   " model.govtDepartment.govtDepartmentId =:deptId " +
											   " and model.parentGovtDepartmentScopeId=:parentScopeId " +
											   " order by model.govtDepartment.govtDepartmentId,model.orderNo ");
		query.setParameter("deptId", deptId);
		query.setParameter("parentScopeId", parentScopeId);
		return query.list();
	}
	public List<Object[]> getRequiredDeptScopeByScopeIds(List<Long> deptIds,Set<Long> scopeIds){
		Query query = getSession().createQuery(" select distinct " +
											   " model.govtDepartment.govtDepartmentId," +
											   " model.parentGovtDepartmentScopeId," +
											   " model.parentGovtDepartmentScope.levelName," +
											   " model.parentGovtDepartmentScope.color " +
											   " from GovtDepartmentScopeLevel model " +
											   " where " +
											   " model.govtDepartment.govtDepartmentId in (:deptIds) and model.parentGovtDepartmentScopeId in (:scopeIds)  " +
											   " order by model.govtDepartment.govtDepartmentId,model.orderNo ");
		query.setParameterList("deptIds", deptIds);
		query.setParameterList("scopeIds", scopeIds);
		  
		return query.list();
	}
	public List<Object[]> getChildGovtScopeByParentScope(Set<Long> parentScopeIds,List<Long> deptIds){
		Query query = getSession().createQuery(" select distinct " +
											   " model.govtDepartment.govtDepartmentId," +
											   " model.parentGovtDepartmentScopeId," +
											   " model.govtDepartmentScope.govtDepartmentScopeId," +
											   " model.govtDepartmentScope.levelName," +
											   " model.govtDepartmentScope.color " +
											   " from GovtDepartmentScopeLevel model " +
											   " where " +
											   " model.govtDepartment.govtDepartmentId in(:deptIds) " +
											   " and model.parentGovtDepartmentScopeId in(:parentScopeIds) group by " +
											   " model.govtDepartment.govtDepartmentId,model.parentGovtDepartmentScopeId,model.govtDepartmentScope.govtDepartmentScopeId " +
											   " order by model.govtDepartment.govtDepartmentId," +
											   " model.parentGovtDepartmentScopeId,model.govtDepartmentScope.govtDepartmentScopeId ");
		query.setParameterList("deptIds", deptIds);
		query.setParameterList("parentScopeIds", parentScopeIds);
		return query.list();
	}
	public List<Object[]> getChildGovtScopesLevelNamesByParentScopeLevel(Long parentScopeId,Long deptId){
		Query query = getSession().createQuery(" select distinct " +
											   " model.govtDepartmentScopeId,model.govtDepartmentScope.levelName " +
											   " from GovtDepartmentScopeLevel model " +
											   " where " +
											   " model.govtDepartment.govtDepartmentId =:deptId and model.parentGovtDepartmentScopeId=:parentScopeId " +
											   " order by model.govtDepartment.govtDepartmentId,model.parentGovtDepartmentScopeId ");
		query.setParameter("deptId", deptId);
		query.setParameter("parentScopeId", parentScopeId);
		return query.list();
	}
	public List<Object[]> getGovtScopesLevelByParentScopeLevel(Long parentScopeId,List<Long> deptIdList){
		Query query = getSession().createQuery(" select distinct " +
											   " model.govtDepartmentScopeId,model.govtDepartmentScope.levelName " +
											   " from GovtDepartmentScopeLevel model " +
											   " where " +
											   " model.govtDepartment.govtDepartmentId in(:deptIdList) " +
											   " and model.parentGovtDepartmentScopeId=:parentScopeId " +
											   " order by model.govtDepartment.govtDepartmentId,model.parentGovtDepartmentScopeId ");
		query.setParameterList("deptIdList", deptIdList);
		query.setParameter("parentScopeId", parentScopeId);
		return query.list();
	}
	public List<Object[]> getDeptsChildLevelByParentScope(Long userLevelId,List<Long> deptIds){
		Query query = getSession().createQuery(" select distinct " +
											   " model.govtDepartment.govtDepartmentId," +
											   " model.parentGovtDepartmentScopeId," +
											   " model.govtDepartmentScopeId " +
											   " from GovtDepartmentScopeLevel model " +
											   " where " +
											   " model.govtDepartment.govtDepartmentId in(:deptIds) " +
											   " and model.parentGovtDepartmentScopeId=:userLevelId " +
											   " group by model.govtDepartment.govtDepartmentId,model.parentGovtDepartmentScopeId,model.govtDepartmentScopeId " +
											   " order by model.govtDepartment.govtDepartmentId,model.parentGovtDepartmentScopeId ");
		query.setParameterList("deptIds", deptIds);
		query.setParameter("userLevelId", userLevelId);
		return query.list();
	}
	
	public List<Object[]> getChildGovtScopesLevelByParentScopeLevel1(Long parentScopeId,List<Long> deptIds){
		Query query = getSession().createQuery(" select distinct " +
											   " model.govtDepartment.govtDepartmentId," +
											   " model.parentGovtDepartmentScopeId," +
											   " model.govtDepartmentScopeId " +
											   " from GovtDepartmentScopeLevel model " +
											   " where " +
											   " model.govtDepartment.govtDepartmentId in (:deptIds) " +
											   " and model.parentGovtDepartmentScopeId=:parentScopeId " +
											   " order by model.govtDepartment.govtDepartmentId,model.parentGovtDepartmentScopeId ");
		query.setParameterList("deptIds", deptIds);
		query.setParameter("parentScopeId", parentScopeId);
		return query.list();
	}
	public List<Object[]> getDeptScopeIdAndOrder(Long govtDeptId){
		Query query = getSession().createQuery(" select " +
											   " model.parentGovtDepartmentScopeId," +
											   " model.orderNo from GovtDepartmentScopeLevel model " +
											   " where " +
											   " model.govtDepartment.govtDepartmentId =:govtDeptId " +
											   " group by model.parentGovtDepartmentScopeId  " +
											   " order by model.orderNo ");
		query.setParameter("govtDeptId", govtDeptId);
		  
		return query.list();
	}
}
