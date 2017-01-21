package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertTrackingDocumentsDAO;
import com.itgrids.partyanalyst.model.AlertTrackingDocuments;

public class AlertTrackingDocumentsDAO extends GenericDaoHibernate<AlertTrackingDocuments, Long> implements  IAlertTrackingDocumentsDAO {
	public AlertTrackingDocumentsDAO() {
		super(AlertTrackingDocuments.class);
		
	}
}
