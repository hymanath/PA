package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAlertClarificationDocumentDAO;
import com.itgrids.partyanalyst.model.AlertClarificationDocument;

public class AlertClarificationDocumentDAO extends GenericDaoHibernate<AlertClarificationDocument, Long> implements IAlertClarificationDocumentDAO{

	public AlertClarificationDocumentDAO() {
		super(AlertClarificationDocument.class);
	}
   public List<Object[]> getAlertAttachments(Long alertId){
	   Query query = getSession().createQuery("select model.clarificationDocumentPath " +
	   		" from AlertClarificationDocument model " +
	   		" where model.alert.alertId = :alertId" +
	   		" and model.isDeleted = 'N' and model.alert.isDeleted = 'N'");
	   query.setParameter("alertId", alertId);
	   return query.list();
   }
}
