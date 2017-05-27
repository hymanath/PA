package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMeekosamIssueFieldTypeDAO;
import com.itgrids.partyanalyst.model.MeekosamIssueFieldType;

public class MeekosamIssueFieldTypeDAO extends
		GenericDaoHibernate<MeekosamIssueFieldType, Long> implements
		IMeekosamIssueFieldTypeDAO {
	public MeekosamIssueFieldTypeDAO() {
		super(MeekosamIssueFieldType.class);
		
	}
}
