package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationOfficerDAO;
import com.itgrids.partyanalyst.model.GovtDepartmentDesignationOfficer;

public class GovtDepartmentDesignationOfficerDAO extends GenericDaoHibernate<GovtDepartmentDesignationOfficer, Long> implements IGovtDepartmentDesignationOfficerDAO{

	public GovtDepartmentDesignationOfficerDAO() {
		super(GovtDepartmentDesignationOfficer.class);
		
	}
	
	public List<Long> getDesignationOfficerIds(Long levelId,Long levelValue,Long designationId){
		Query query = getSession().createQuery("select distinct model.govtDepartmentDesignationOfficerId" +
											" from GovtDepartmentDesignationOfficer model" +
											" where model.govtDepartmentLevelId = :levelId" +
											" and model.levelValue = :levelValue" +
											" and model.govtDepartmentDesignationId = :designationId");
		query.setParameter("levelId", levelId);
		query.setParameter("levelValue", levelValue);
		query.setParameter("designationId", designationId);
		return query.list();
	}
	
}
