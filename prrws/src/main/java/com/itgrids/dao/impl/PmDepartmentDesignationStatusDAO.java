package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmDepartmentDesignationStatusDAO;
import com.itgrids.model.PmDepartmentDesignationStatus;

@Repository
public class PmDepartmentDesignationStatusDAO extends GenericDaoHibernate<PmDepartmentDesignationStatus, Long>
		implements IPmDepartmentDesignationStatusDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	PmDepartmentDesignationStatusDAO(){
		super(PmDepartmentDesignationStatus.class);
	}
	
	/*public List<Object[]> getLoginUserAccessStatusWiseDeptDesignations(List<Long> deptDesigIdsList ,Long statusId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct  model.pmDepartmentDesignation.pmOfficerDesignation.pmOfficerDesignationId," +
				"model.pmDepartmentDesignation.pmOfficerDesignation.designation from PmDepartmentDesignationStatus model where " +
				" model.pmDepartmentDesignation.isDeleted='N' ");
		if(statusId != null && statusId.longValue() >0l){
			sb.append(" and model.pmStatusId = :statusId ");
		}
		Query query = getSession().createQuery(sb.toString());
		if(statusId != null && statusId.longValue() >0l){
			query.setParameter("statusId", statusId);
		}
		return query.list();
	}*/
}
