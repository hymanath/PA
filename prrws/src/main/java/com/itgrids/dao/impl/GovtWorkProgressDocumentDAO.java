package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IGovtWorkProgressDocumentDAO;
import com.itgrids.model.GovtWorkProgressDocument;

@Repository
public class GovtWorkProgressDocumentDAO extends GenericDaoHibernate<GovtWorkProgressDocument, Long> implements IGovtWorkProgressDocumentDAO{

	public GovtWorkProgressDocumentDAO() {
		super(GovtWorkProgressDocument.class);
	}
	
	public List<Object[]> getStatusDocumentsOfGovtWork(Long workId){
		//0-statusTypeId,1-govtStatusId,2-documentId,3-path,4-mobileAppUserId,5-username,6-insertedTime
		Query query = getSession().createQuery(" select model.govtWorkProgress.govtWorkStatus.statusTypeId,model.govtWorkProgress.govtWorkStatusId,"
				+ " model.document.documentId,model.document.path,model.document.mobileAppUser.mobileAppUserId,model.document.mobileAppUser.username,"
				+ " date(model.document.insertedTime) "
				+ " from GovtWorkProgressDocument model"
				+ " where model.govtWorkProgress.govtWorkId=:workId ");
		query.setParameter("workId", workId);
		return query.list();
	}
}
