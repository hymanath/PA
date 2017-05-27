package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMeekosamIssueFieldDAO;
import com.itgrids.partyanalyst.model.MeekosamIssueField;

public class MeekosamIssueFieldDAO extends
		GenericDaoHibernate<MeekosamIssueField, Long> implements
		IMeekosamIssueFieldDAO {
	public MeekosamIssueFieldDAO() {
		super(MeekosamIssueField.class);
		
	}
}
