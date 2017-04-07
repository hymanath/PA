package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationOfficerDetailsNewDAO;
import com.itgrids.partyanalyst.model.GovtDepartmentDesignationOfficerDetailsNew;

public class GovtDepartmentDesignationOfficerDetailsNewDAO extends GenericDaoHibernate<GovtDepartmentDesignationOfficerDetailsNew, Long>
		implements IGovtDepartmentDesignationOfficerDetailsNewDAO {
      public GovtDepartmentDesignationOfficerDetailsNewDAO(){
    	  super(GovtDepartmentDesignationOfficerDetailsNew.class);
      }
      
      public List<Object[]> getGovtDeptDesigOffrDetlsIdAndGovtOfcrId(Long userId,List<Long> levelValues , Long levelId){
    	  
    	  StringBuilder sb = new StringBuilder();
    	  
    	  sb.append(" select model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId,model.govtOfficer.govtOfficerId," +
    	  		" model.govtOfficer.officerName, model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
    	  		"model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName,model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId,model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.designationName  from GovtDepartmentDesignationOfficerDetailsNew model where " +
    	  		" model.isDeleted = 'N' ");
    	  
    	  
    	  if(levelId != null && levelId.longValue() >0l){
    		  sb.append(" and  model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId = :levelId " );
    	  }
    	  
    	  if(levelValues != null && levelValues.size() >0){
    		  sb.append(" and  model.govtDepartmentDesignationOfficer.levelValue in (:levelValues) ");
    	  }
    	  if(userId != null && userId.longValue() >0l){
    		  sb.append(" and  model.user.userId = :userId " );
    	  }
    	  Query query = getSession().createQuery(sb.toString());
    	  
    	  if(userId != null && userId.longValue() >0l){
    		  query.setParameter("userId", userId);  
    	  }
    	  
    	  if(levelId != null && levelId.longValue() >0l){
    		  query.setParameter("levelId", levelId); 
    	  }
    	  if(levelValues != null && levelValues.size() >0){
    		  query.setParameterList("levelValues", levelValues);
    	  }
    	  return query.list();
      }
}
