package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMeekosamIssueTypeDAO;
import com.itgrids.partyanalyst.model.MeekosamIssueType;

public class MeekosamIssueTypeDAO extends
		GenericDaoHibernate<MeekosamIssueType, Long> implements
		IMeekosamIssueTypeDAO {
	public MeekosamIssueTypeDAO(){
		super(MeekosamIssueType.class);
	}
}
