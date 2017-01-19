package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertDocumentDAO;
import com.itgrids.partyanalyst.model.AlertDocument;

public class AlertDocumentDAO extends GenericDaoHibernate<AlertDocument, Long>
		implements IAlertDocumentDAO {

	public AlertDocumentDAO(){
		super(AlertDocument.class);
	}
}
