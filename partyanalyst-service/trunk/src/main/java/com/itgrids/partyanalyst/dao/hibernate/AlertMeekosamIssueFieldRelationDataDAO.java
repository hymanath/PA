package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertMeekosamIssueFieldRelationDataDAO;
import com.itgrids.partyanalyst.model.AlertMeekosamIssueFieldRelationData;

public class AlertMeekosamIssueFieldRelationDataDAO extends
		GenericDaoHibernate<AlertMeekosamIssueFieldRelationData, Long>
		implements IAlertMeekosamIssueFieldRelationDataDAO {
	public AlertMeekosamIssueFieldRelationDataDAO() {
		super(AlertMeekosamIssueFieldRelationData.class);
		
	}
}
