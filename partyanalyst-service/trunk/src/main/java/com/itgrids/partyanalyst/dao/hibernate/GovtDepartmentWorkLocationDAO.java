package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtDepartmentWorkLocationDAO;
import com.itgrids.partyanalyst.model.GovtDepartmentWorkLocation;

public class GovtDepartmentWorkLocationDAO extends GenericDaoHibernate<GovtDepartmentWorkLocation, Long> implements IGovtDepartmentWorkLocationDAO{

	public GovtDepartmentWorkLocationDAO(){
		super(GovtDepartmentWorkLocation.class);
	}
	
	public List<Object[]> getLevelWiseInfo(Long deptId,Set<Long> levelIds){
		
		StringBuilder sb = new StringBuilder();
				
		sb.append(" SELECT model.govtDepartmentScopeId,model.govtDepartmentWorkLocationId,model.locationName " +
											  " FROM GovtDepartmentWorkLocation model " +
											  " WHERE model.isDeleted ='N' " );
		
		if(deptId !=null && deptId.longValue()>0l){
			sb.append(" and model.govtDepartmentId = :deptId ");
		}
		if(levelIds !=null && levelIds.size()>0){
			sb.append(" and model.govtDepartmentScopeId in (:levelIds) ");
		}
		
		sb.append(" GROUP BY model.govtDepartmentScopeId,model.govtDepartmentWorkLocationId ");
		
		
		Query query = getSession().createQuery(sb.toString());
		
		if(deptId !=null && deptId.longValue()>0l){
			query.setParameter("deptId", deptId);
		}
		if(levelIds !=null && levelIds.size()>0){
			query.setParameterList("levelIds", levelIds);
		}
		
		return query.list();
		
	}
	
	public List<Object[]> getParentLevelValuesListInfo(List<Long> levelValues){
		
		Query query = getSession().createQuery("select model.govtDepartmentWorkLocationId,model.locationName" +
				" FROM GovtDepartmentWorkLocation model" +
				" WHERE model.isDeleted ='N' " +
				" and model.govtDepartmentWorkLocationId in (:levelValues) ") ;
		
		query.setParameterList("levelValues", levelValues);
		return query.list();
	}
	
}
