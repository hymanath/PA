package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationOfficerNewDAO;
import com.itgrids.partyanalyst.model.GovtDepartmentDesignationOfficerNew;

public class GovtDepartmentDesignationOfficerNewDAO extends GenericDaoHibernate<GovtDepartmentDesignationOfficerNew, Long> implements IGovtDepartmentDesignationOfficerNewDAO {

	public GovtDepartmentDesignationOfficerNewDAO(){
		super(GovtDepartmentDesignationOfficerNew.class);
	}
	
	public List<Object[]> getDeptAndDesignationNames(Long govtDepDesigOffcrId){
		
	    	  StringBuilder sb = new StringBuilder();
	    	  
	    	  sb.append(" select model.govtDepartmentDesignation.govtDepartmentDesignationId,model.govtDepartmentDesignation.designationName," +
	    	  		" model.govtDepartment.govtDepartmentId,model.govtDepartment.departmentName   from GovtDepartmentDesignationOfficerNew model where " +
	    	  		" model.isDeleted = 'N' ");
	    	  
	    	  if(govtDepDesigOffcrId != null && govtDepDesigOffcrId.longValue() >0l){
	    		  sb.append(" model.govtDepartmentDesignationOfficerId = :govtDepDesigOffcrId " );
	    	  }
	    	  Query query = getSession().createQuery(sb.toString());
	    	  
	    	  if(govtDepDesigOffcrId != null && govtDepDesigOffcrId.longValue() >0l){
	    		  query.setParameter("govtDepDesigOffcrId", govtDepDesigOffcrId);  
	    	  }
	    	  
	    	  return query.list();
	      }
}
