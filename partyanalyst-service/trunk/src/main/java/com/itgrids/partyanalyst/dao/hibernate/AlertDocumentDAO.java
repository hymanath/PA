package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAlertDocumentDAO;
import com.itgrids.partyanalyst.model.AlertDocument;

public class AlertDocumentDAO extends GenericDaoHibernate<AlertDocument, Long>
		implements IAlertDocumentDAO {

	public AlertDocumentDAO(){
		super(AlertDocument.class);
	}
	
	public List<Object[]> getDocumentsForAlert(Long alertId){
		Query query = getSession().createQuery(" select model.alertDocumentId,model.documentPath,model.documentName " +
				" from AlertDocument model " +
				" where model.isDeleted='N' and model.alertId=:alertId ");
		query.setParameter("alertId", alertId);
		return query.list();
	}
	public int deleteDocument(Long docId){
		Query query = getSession().createQuery(" delete from AlertDocument alertDocument where alertDocument.alertDocumentId = :docId");
		query.setParameter("docId", docId);
		return query.executeUpdate();
	}
	
}
