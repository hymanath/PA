package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationDAO;
import com.itgrids.partyanalyst.model.GovtDepartmentDesignation;

public class GovtDepartmentDesignationDAO extends GenericDaoHibernate<GovtDepartmentDesignation, Long> implements IGovtDepartmentDesignationDAO{

	public GovtDepartmentDesignationDAO() {
		super(GovtDepartmentDesignation.class);
		
	}

	public List<Object[]> getDesignationsForDepartment(Long govtDepartmentId,Long levelId){
		Query query = getSession().createQuery("select distinct model.govtDepartmentDesignationId," +
												" model.designationName" +
												" from GovtDepartmentDesignation model" +
												" where model.govtDepartment.govtDepartmentId = :govtDepartmentId" +
												" and model.govtDepartmentLevel.govtDepartmentLevelId = :levelId");
		query.setParameter("govtDepartmentId", govtDepartmentId);
		query.setParameter("levelId", levelId);
		return query.list();
	}
	
	public String getDepartmentDetails(Long designationId){
		Query query = getSession().createQuery(" select model.designationName "
				+ " from GovtDepartmentDesignationNew model where model.govtDepartmentDesignationId=:designationId ");
		query.setParameter("designationId", designationId);
		return (String)query.uniqueResult();
	}
}
