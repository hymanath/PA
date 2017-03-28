package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtDepartmentLevelDAO;
import com.itgrids.partyanalyst.model.GovtDepartmentLevel;

public class GovtDepartmentLevelDAO extends GenericDaoHibernate<GovtDepartmentLevel, Long> implements IGovtDepartmentLevelDAO{

	public GovtDepartmentLevelDAO() {
		super(GovtDepartmentLevel.class);
		
	}

	public List<Object[]> getDepartmentLevels(){
		Query query = getSession().createQuery("select model.govtDepartmentLevelId," +
											" model.levelName" +
											" from GovtDepartmentLevel model");
		return query.list();
	}
	
	public List<Object[]> getLowerLevelsByLevel(Long levelId){
		Query query = getSession().createQuery("select model.govtDepartmentLevelId," +
												" model.levelName" +
												" from GovtDepartmentLevel model" +
												" where model.govtDepartmentLevelId > :levelId");
		query.setParameter("levelId", levelId);
		return query.list();
	}
	
	public List<Long> getlevelIdsForLevel(Long levelId){
		Query query = getSession().createQuery("select model.alertRegionScopes.alertRegionScopesId" +
											" from GovtDepartmentLevel model" +
											" where model.govtDepartmentLevelId = :levelId");
		query.setParameter("levelId", levelId);
		return query.list();
	}
	
	public Long getRegionScopeIdBylevel(Long levelId){
		Query query = getSession().createQuery("select model.alertRegionScopes.alertRegionScopesId" +
												" from GovtDepartmentLevel model" +
												" where model.govtDepartmentLevelId = :levelId");
		query.setParameter("levelId", levelId);
		return (Long) query.uniqueResult();
	}
}
