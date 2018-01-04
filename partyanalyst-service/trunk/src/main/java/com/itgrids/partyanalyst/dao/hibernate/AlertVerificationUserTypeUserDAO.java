package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAlertVerificationUserTypeUserDAO;
import com.itgrids.partyanalyst.model.AlertVerificationUserTypeUser;

public class AlertVerificationUserTypeUserDAO extends GenericDaoHibernate<AlertVerificationUserTypeUser, Long> implements IAlertVerificationUserTypeUserDAO {

	public AlertVerificationUserTypeUserDAO(){
		super(AlertVerificationUserTypeUser.class);
	}
   public Long getAlertVerificationUserTypeId(Long userId){
	   Query query = getSession().createQuery("select model.alertVerificationUserTypeId from AlertVerificationUserTypeUser model where model.userId=:userId");
	    query.setParameter("userId", userId);
	    return (Long)query.uniqueResult();
   }
   public List<Object[]> getAlertVerificationUserByUserType(Long verificationUserTypeId){
	   Query query = getSession().createQuery("select distinct model.userId,model.displayName " +
	   		"  from AlertVerificationUserTypeUser model " +
	   		"  where model.displayName != null and model.displayName != '' " +
	   		"  and model.alertVerificationUserTypeId=:verificationUserTypeId");
	    query.setParameter("verificationUserTypeId", verificationUserTypeId);
	    return query.list();
   }
}
