package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmDepartmentDesignationHierarchyDAO;
import com.itgrids.model.PmDepartmentDesignationHierarchy;

@Repository
public class PmDepartmentDesignationHierarchyDAO extends GenericDaoHibernate<PmDepartmentDesignationHierarchy, Long> implements IPmDepartmentDesignationHierarchyDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	PmDepartmentDesignationHierarchyDAO(){
		super(PmDepartmentDesignationHierarchy.class);
	}
	
	public List<Object[]> getSubDesignationDetailsForParentDeptDesignations(List<Long> deptDesignationIdsList,List<Long> deptIdsList, String actionType){
		if(deptDesignationIdsList != null && deptDesignationIdsList.size()>0){
			StringBuilder str = new StringBuilder();
		
			/*str.append(" select distinct model.subPmDepartmentDesignation.pmDepartmentDesignationId,"
					  + " model.subPmDepartmentDesignation.pmOfficerDesignation.designation, model.subPmDepartmentDesignation.pmDepartment.department" +
					"  from PmDepartmentDesignationHierarchy model " );*/
			str.append(" select distinct model.subPmDepartmentDesignation.pmOfficerDesignation.pmOfficerDesignationId,"
					  + " model.subPmDepartmentDesignation.pmOfficerDesignation.designation " +
					"  from PmDepartmentDesignationHierarchy model where model.isActive='Y' " );
			
			if(deptDesignationIdsList != null && deptDesignationIdsList.size() >0){
				str.append(" and model.pmDepartmentDesignationId in (:deptDesignationIdsList) ");
			}
			if(deptIdsList != null && deptIdsList.size() >0){
				str.append(" and model.subPmDepartmentDesignation.pmDepartmentId in (:deptIdsList)  ");
			}
			//str.append(" and model.isActive='Y' and  model.subPmDepartmentDesignation.isDeleted='N' order by  model.subPmDepartmentDesignation.pmOfficerDesignation.designation ");
			//str.append(" and model.actionType =:actionType ");
			str.append(" and    model.subPmDepartmentDesignation.isDeleted='N' order by  model.orderNo ");
			Query query = getSession().createQuery(str.toString());
			if(deptDesignationIdsList != null && deptDesignationIdsList.size() >0){
				query.setParameterList("deptDesignationIdsList", deptDesignationIdsList);
			}
			if(deptIdsList != null && deptIdsList.size() >0){
				query.setParameterList("deptIdsList", deptIdsList);
			}
			//query.setParameter("actionType", actionType);
			return query.list();
		}
		return  null;
	}
}


