package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationHierarchyDAO;
import com.itgrids.partyanalyst.model.GovtDepartmentDesignationHierarchy;

public class GovtDepartmentDesignationHierarchyDAO extends GenericDaoHibernate<GovtDepartmentDesignationHierarchy, Long> implements IGovtDepartmentDesignationHierarchyDAO {

	public GovtDepartmentDesignationHierarchyDAO(){
		super(GovtDepartmentDesignationHierarchy.class);
	}
	
	public List<Long> getParentDepartment(Long designationId){
		Query query = getSession().createQuery(" select model.parentDesignationId "
				+ " from GovtDepartmentDesignationHierarchy model "
				+ " where model.isDeleted='N' and model.subDesignationId=:designationId ");
		query.setParameter("designationId", designationId);
		return query.list();
	}
	
	public List<Long> getOldParentDepartment(Long designationId){
		Query query = getSession().createQuery(" select model.parentDesignationId "
				+ " from GovtDepartmentDesignationMapping model "
				+ " where model.isDeleted='N' and model.govtDepartmentDesignationId=:designationId  " +
				" order by model.orderNo  ");
		query.setParameter("designationId", designationId);
		return query.list();
	}
	
	public List<Long> getChildLocationDesignations(Long designationId){
		Query query = getSession().createQuery(" select model.subDesignationId "
				+ " from GovtDepartmentDesignationHierarchy model "
				+ " where model.isDeleted='N' and model.parentDesignationId=:designationId "
				+ " order by orderNo ");
		query.setParameter("designationId", designationId);
		return query.list();
	}
	public List<Object[]> getChildDesigData(List<Long> parentGovtDeptDesigIdList, Long childGovtDeptDesigId2){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.parentDesignation.govtDepartmentDesignationId," +
						" model.subDesignation.govtDepartmentDesignationId " +
						" from GovtDepartmentDesignationHierarchy model " +
						" where" +
						" model.parentDesignation.govtDepartmentDesignationId in (:parentGovtDeptDesigIdList) " +
						" and model.subDesignation.govtDepartmentDesignationId = :childGovtDeptDesigId2" +
						" and  model.isDeleted = 'N' " +
						" and model.isImmediate = 'Y' "); 
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("childGovtDeptDesigId2", childGovtDeptDesigId2);
		query.setParameterList("parentGovtDeptDesigIdList", parentGovtDeptDesigIdList);
		return query.list();
	}
}
