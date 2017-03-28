package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationMappingDAO;
import com.itgrids.partyanalyst.model.GovtDepartmentDesignation;
import com.itgrids.partyanalyst.model.GovtDepartmentDesignationMapping;

public class GovtDepartmentDesignationMappingDAO extends GenericDaoHibernate<GovtDepartmentDesignationMapping, Long>
implements IGovtDepartmentDesignationMappingDAO
{

	public GovtDepartmentDesignationMappingDAO() {
		super(GovtDepartmentDesignationMapping.class);
	}
	
	public List<Object[]> getSubDesignationLevelInfo(List<Long> parentDesignationIds){
		
		StringBuilder str = new StringBuilder();
		
		str.append(" SELECT model.parentDesignationId,model.govtDepartmentDesignation.govtDepartmentLevelId," +
				" model.govtDepartmentDesignation.govtDepartmentLevel.levelName " +
				" FROM GovtDepartmentDesignationMapping model " +
				" WHERE model.isDeleted = 'N' ");
		
		if(parentDesignationIds !=null && parentDesignationIds.size()>0){
			str.append(" and model.parentDesignationId in (:parentDesignationIds)  ");
		}
		
		str.append(" GROUP BY model.parentDesignationId,model.govtDepartmentDesignation.govtDepartmentLevelId ");
		
		Query hqlQuery = getSession().createQuery(str.toString());
		
		if(parentDesignationIds !=null && parentDesignationIds.size()>0){
			hqlQuery.setParameterList("parentDesignationIds", parentDesignationIds);
		}
		
		return hqlQuery.list();		
	}
	
	public List<Object[]> getSubDesignationsInfo(List<Long> parentDesignationIds,Long levelId){
		
		StringBuilder str = new StringBuilder();
		
		str.append(" SELECT model.parentDesignationId,model.govtDepartmentDesignation.govtDepartmentDesignationId," +
				" model.govtDepartmentDesignation.designationName " +
				
				" FROM GovtDepartmentDesignationMapping model " +
				" WHERE model.isDeleted = 'N' ");
		
		if(parentDesignationIds !=null && parentDesignationIds.size()>0l){
			str.append(" and model.parentDesignationId in (:parentDesignationIds)  ");
		}
		
		if(levelId !=null && levelId.longValue()>0l){
			str.append(" and model.govtDepartmentDesignation.govtDepartmentLevelId =:levelId  ");
		}
		
		str.append(" GROUP BY model.parentDesignationId,model.govtDepartmentDesignation.govtDepartmentDesignationId ");
		
		Query hqlQuery = getSession().createQuery(str.toString());
		
		if(parentDesignationIds !=null && parentDesignationIds.size()>0){
			hqlQuery.setParameterList("parentDesignationIds", parentDesignationIds);
		}
		
		if(levelId !=null && levelId.longValue()>0l){
			hqlQuery.setParameter("levelId", levelId);
		}
		
		return hqlQuery.list();		
	}

}
