package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtDepartmentIssueTypeDAO;
import com.itgrids.partyanalyst.model.GovtDepartmentIssueType;

public class GovtDepartmentIssueTypeDAO extends GenericDaoHibernate<GovtDepartmentIssueType, Long> implements IGovtDepartmentIssueTypeDAO{

	public GovtDepartmentIssueTypeDAO() {
		super(GovtDepartmentIssueType.class);
		
	}

	public List<Object[]> getRelatedDepartmentsForIssueType(Long issueType){
		Query query = getSession().createQuery("select distinct model.govtDepartment.govtDepartmentId," +
										" concat(model.govtDepartment.departmentName,'-',model.areaType)" +
										" from GovtDepartmentIssueType model" +
										" where model.alertIssueType.alertIssueTypeId = :issueType");
		query.setParameter("issueType", issueType);
		return query.list();
	}
}
