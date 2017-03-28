package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertActionTypeDocument;
import com.itgrids.partyanalyst.model.AlertActionTypeDocument;

public class AlertActionTypeDocumentDAO extends GenericDaoHibernate<AlertActionTypeDocument, Long> implements
		IAlertActionTypeDocument {

	public AlertActionTypeDocumentDAO(){
		super(AlertActionTypeDocument.class);
	}
}
