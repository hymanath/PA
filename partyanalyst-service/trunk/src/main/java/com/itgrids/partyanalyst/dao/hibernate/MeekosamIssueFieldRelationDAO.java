package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMeekosamIssueFieldRelationDAO;
import com.itgrids.partyanalyst.model.MeekosamIssueFieldRelation;

public class MeekosamIssueFieldRelationDAO extends
		GenericDaoHibernate<MeekosamIssueFieldRelation, Long> implements
		IMeekosamIssueFieldRelationDAO {
	public MeekosamIssueFieldRelationDAO() {
		super(MeekosamIssueFieldRelation.class);
		
	}
}
