package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IForgetPasswordAccessKeyDAO;
import com.itgrids.partyanalyst.model.ForgetPasswordAccessKey;


public class ForgetPasswordAccessKeyDAO extends GenericDaoHibernate<ForgetPasswordAccessKey,Long> implements IForgetPasswordAccessKeyDAO {
	
	public ForgetPasswordAccessKeyDAO() {
		super(ForgetPasswordAccessKey.class);
	}
	
	public ForgetPasswordAccessKey getModelByRandomNumber(String accessKey){
		Query query=getSession().createQuery(" select model from ForgetPasswordAccessKey model " +
				" where model.accessKey = :accessKey and model.isValidated='N'");
		query.setParameter("accessKey", accessKey);
		
		return (ForgetPasswordAccessKey) query.uniqueResult();
		
	}
	
	public int updateIsValidated(String accessKey){
		Query query=getSession().createQuery(" update ForgetPasswordAccessKey model set model.isValidated='Y' " +
				" where model.accessKey = :accessKey ");
		query.setParameter("accessKey", accessKey);
		
		return query.executeUpdate();
	}
	
}
