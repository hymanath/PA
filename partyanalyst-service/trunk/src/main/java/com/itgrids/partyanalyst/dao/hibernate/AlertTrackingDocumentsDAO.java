package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAlertTrackingDocumentsDAO;
import com.itgrids.partyanalyst.model.AlertTrackingDocuments;

public class AlertTrackingDocumentsDAO extends GenericDaoHibernate<AlertTrackingDocuments, Long> implements  IAlertTrackingDocumentsDAO {
	public AlertTrackingDocumentsDAO() {
		super(AlertTrackingDocuments.class);
		
	}
	
	public List<Object[]> getDocumentsForAlertTracking(List<Long> alertTrackingIds){
		
		Query query = getSession().createQuery("select model.alertTrackingId,model.alertTrackingDocumentsId,model.path " +
				" from AlertTrackingDocuments model " +
				" where model.isDeleted='N' and model.alertTrackingId in (:alertTrackingIds) ");
		
		query.setParameterList("alertTrackingIds", alertTrackingIds);
		
		return query.list();
	}
}
