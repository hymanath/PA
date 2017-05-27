package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGovtDepartmentMeekosamIssueTypeDAO;
import com.itgrids.partyanalyst.model.GovtDepartmentMeekosamIssueType;

public class GovtDepartmentMeekosamIssueTypeDAO extends
		GenericDaoHibernate<GovtDepartmentMeekosamIssueType, Long> implements
		IGovtDepartmentMeekosamIssueTypeDAO {
	public GovtDepartmentMeekosamIssueTypeDAO() {
		super(GovtDepartmentMeekosamIssueType.class);
		
	}
}
