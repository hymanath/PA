package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVerificationDocumentsDAO;
import com.itgrids.partyanalyst.model.VerificationDocuments;

public class VerificationDocumentsDAO extends GenericDaoHibernate<VerificationDocuments, Long> implements IVerificationDocumentsDAO {

	public VerificationDocumentsDAO(){
		super(VerificationDocuments.class);
	}

	public List<Object[]> getAlertDocumentByAlertId(Long alertId){
		 StringBuilder queryStr = new StringBuilder();
		 queryStr.append(" select " +
		 		" model.alertVerificationUserType.alertVerificationUserTypeId," +//0
		 		" model.documentPath," +//1
		 		" date(model.updatedTime)," +//2
		 		" time(model.updatedTime) " +//3
		 		" from VerificationDocuments model " +//6
		 		" where " +
		 		" model.verificationConversation.alertId=:alertId " +
		 		" and model.isDeleted='N' and model.verificationConversation.alert.isDeleted='N' ");
		   Query query = getSession().createQuery(queryStr.toString());
		  query.setParameter("alertId", alertId);
		  return query.list();
	}
}
