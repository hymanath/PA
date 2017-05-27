package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMeekosamIssueFieldRelationDataDAO;
import com.itgrids.partyanalyst.model.MeekosamIssueFieldRelationData;

public class MeekosamIssueFieldRelationDataDAO extends
		GenericDaoHibernate<MeekosamIssueFieldRelationData, Long> implements
		IMeekosamIssueFieldRelationDataDAO {
	public MeekosamIssueFieldRelationDataDAO() {
		super(MeekosamIssueFieldRelationData.class);
		
	}
}
