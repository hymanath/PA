package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtDepartmentRelationDAO;
import com.itgrids.partyanalyst.model.GovtDepartmentRelation;

public class GovtDepartmentRelationDAO extends GenericDaoHibernate<GovtDepartmentRelation, Long> implements IGovtDepartmentRelationDAO{

	public GovtDepartmentRelationDAO() {
		super(GovtDepartmentRelation.class);
	}
	
	public List<Object[]> getAllMainDepartments(){
		
		Query query = getSession().createQuery(" SELECT distinct model.parentGovtDepartment.govtDepartmentId,model.parentGovtDepartment.departmentName " +
				" FROM GovtDepartmentRelation model " +
				" WHERE model.isDeleted = 'N' ");
		
		return query.list();
		
	}
	
	public List<Object[]> getSubDeptsForParentDept(Long parentDeptId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.subGovtDepartment.govtDepartmentId," +
				" model.subGovtDepartment.departmentName" +
				" from GovtDepartmentRelation model" +
				" where model.isDeleted = 'N' ");
		if(parentDeptId != null && parentDeptId.longValue() > 0l){
			sb.append(" and model.parentGovtDepartment.govtDepartmentId = :parentDeptId ");
		}
		
	   Query query = getSession().createQuery(sb.toString());
	   if(parentDeptId != null && parentDeptId.longValue() > 0l)
		   query.setParameter("parentDeptId", parentDeptId);
	   
	   return query.list();
	}

}
