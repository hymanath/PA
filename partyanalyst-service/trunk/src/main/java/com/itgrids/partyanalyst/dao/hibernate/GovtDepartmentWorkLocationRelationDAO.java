package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtDepartmentWorkLocationRelationDAO;
import com.itgrids.partyanalyst.model.GovtDepartmentWorkLocationRelation;

public class GovtDepartmentWorkLocationRelationDAO extends GenericDaoHibernate<GovtDepartmentWorkLocationRelation, Long> implements IGovtDepartmentWorkLocationRelationDAO{

	public GovtDepartmentWorkLocationRelationDAO(){
		super(GovtDepartmentWorkLocationRelation.class);
	}
	
	public List<Object[]> getGovtSubLevelInfo(Long levelValue){
		
		Query query = getSession().createQuery( " SELECT model.govtDepartmentWorkLocation.govtDepartmentScopeId,model.govtDepartmentWorkLocation.govtDepartmentScope.levelName," +
												"model.govtDepartmentWorkLocationId,model.govtDepartmentWorkLocation.locationName" +
												" " +
												" FROM GovtDepartmentWorkLocationRelation model " +
												" WHERE model.isDeleted ='N' " +
												" and model.parentGovtDepartmentWorkLocationId =:levelValue " +
												" ORDER BY model.govtDepartmentWorkLocation.govtDepartmentScopeId " );
		query.setParameter("levelValue", levelValue);
		return query.list();
		
	}
	
	public List<Long> getChildLocations(Long levelValue){
		Query query = getSession().createQuery(" SELECT distinct model.govtDepartmentWorkLocationRelationId " +
				" " +
				" FROM GovtDepartmentWorkLocationRelation model " +
				" WHERE model.isDeleted ='N' " +
				" and model.parentGovtDepartmentWorkLocationId =:levelValue " );
		
		query.setParameter("levelValue", levelValue);
		return query.list(); 
		
	}
	
	
}
