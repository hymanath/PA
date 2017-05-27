package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMeekosamIssueFieldDataDAO;
import com.itgrids.partyanalyst.model.MeekosamIssueFieldData;

public class MeekosamIssueFieldDataDAO extends
		GenericDaoHibernate<MeekosamIssueFieldData, Long> implements
		IMeekosamIssueFieldDataDAO {
	public MeekosamIssueFieldDataDAO() {
		super(MeekosamIssueFieldData.class);
		
	}
}
