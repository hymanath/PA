package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtDepartmentDAO;
import com.itgrids.partyanalyst.model.GovtDepartment;

public class GovtDepartmentDAO extends GenericDaoHibernate<GovtDepartment, Long> implements IGovtDepartmentDAO{

	public GovtDepartmentDAO() {
		super(GovtDepartment.class);
		
	}
	public List<Object[]> getAllDepartment(){
		return getHibernateTemplate().find("select model.govtDepartmentId,model.departmentName from GovtDepartment  model order by model.departmentName ");
	}
	public List<Long> getGovtDepartmentIdsOfNewsDept(List<Long> newsDepartmentIds){
		
		Query query = getSession().createQuery(" select model.govtDepartmentId from GovtDepartment model " +
				" where model.cnpGovtDepartmentId in (:newsDepartmentIds) ");
		
		query.setParameterList("newsDepartmentIds", newsDepartmentIds);
		
		return query.list();
	}
}
