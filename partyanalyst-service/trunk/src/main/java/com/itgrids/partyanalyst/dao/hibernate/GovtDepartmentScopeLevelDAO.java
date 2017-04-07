package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

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
}
