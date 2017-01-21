package com.itgrids.partyanalyst.dao.hibernate;


import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVerificationConversationDAO;
import com.itgrids.partyanalyst.model.VerificationConversation;

public class VerificationConversationDAO extends GenericDaoHibernate<VerificationConversation, Long> implements IVerificationConversationDAO {

	public VerificationConversationDAO(){
		super(VerificationConversation.class);
	}

	public Long getLastestVerificationConversionIdbyAlertId(Long alertId){
		StringBuilder queryStr = new StringBuilder();
		 queryStr.append(" select model.verificationConversationId  from VerificationConversation model " +
				 		 " where model.updatedTime = (select max(model1.updatedTime) from VerificationConversation model1) " +
				 		 " and model.isDeleted='N' " +
				 		 " and model.alert.alertId=:alertId ");
		 Query query = getSession().createQuery(queryStr.toString());
		 query.setParameter("alertId", alertId);
		 return (Long)query.uniqueResult();
	}
}
