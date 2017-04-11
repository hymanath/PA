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
	
	@SuppressWarnings("unchecked")
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
    
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllDivisionDetails(Long districtId){
   		Query query = getSession().createQuery("select model.govtUserAddress.divisionId,model.locationName" +
   				                            " from  GovtDepartmentWorkLocation model " +
   				                            "where model.govtDepartmentScope.govtDepartmentScopeId =:scopeId " +
   				                            "and model.isDeleted='N' and model.govtUserAddress.districtId =:districtId ");   				                          
   		query.setParameter("scopeId", 6l); 
   		query.setParameter("districtId", districtId);
   		return query.list();
     }
     public List<Object[]> getAllSubDivisionDetails(Long divisionId){
   		Query query = getSession().createQuery("select model.govtUserAddress.subDivisionId,model.locationName" +
   				                            " from  GovtDepartmentWorkLocation model " +
   				                            "where model.govtDepartmentScope.govtDepartmentScopeId =:scopeId " +
   				                            "and model.isDeleted='N'and model.govtUserAddress.divisionId =:divisionId");   				                          
   		query.setParameter("scopeId", 7l);
   		query.setParameter("divisionId", divisionId);
   		return query.list();
     }
     @SuppressWarnings("unchecked")
 	public List<Object[]> getAllDistrictDetails(){
 		Query query = getSession().createQuery("select model.govtUserAddress.districtId,model.locationName" +
 				                            " from  GovtDepartmentWorkLocation model " +
 				                            "where model.govtDepartmentScope.govtDepartmentScopeId =:scopeId " +
 				                            "and model.isDeleted='N'");   				                          
 		query.setParameter("scopeId", 5l);   				                            
 		return query.list();
       }   
}
