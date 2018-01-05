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
	
	public List<Object[]> getSubDesignationDetailsForParentDeptDesignations(List<Long> deptDesignationIdsList){
		if(deptDesignationIdsList != null && deptDesignationIdsList.size()>0){
			StringBuilder str = new StringBuilder();
		
			str.append(" select distinct model.subPmDepartmentDesignation.pmDepartmentDesignationId,"
					  + " model.subPmDepartmentDesignation.pmOfficerDesignation.designation" +
					"  from PmDepartmentDesignationHierarchy model " );
			if(deptDesignationIdsList != null && deptDesignationIdsList.size() >0){
				str.append(" where model.pmDepartmentDesignationId in (:deptDesignationIdsList) ");
			}
			str.append(" and model.isActive='Y' and  model.subPmDepartmentDesignation.isDeleted='N' order by  model.subPmDepartmentDesignation.pmOfficerDesignation.designation ");
			Query query = getSession().createQuery(str.toString());
			if(deptDesignationIdsList != null && deptDesignationIdsList.size() >0){
				query.setParameterList("deptDesignationIdsList", deptDesignationIdsList);
			}
			return query.list();
		}
		return  null;
	}
}


