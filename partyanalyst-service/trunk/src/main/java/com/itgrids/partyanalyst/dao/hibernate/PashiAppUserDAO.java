package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Criteria;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPashiAppUserDAO;
import com.itgrids.partyanalyst.model.PashiAppUser;

public class PashiAppUserDAO extends GenericDaoHibernate<PashiAppUser, Long> implements IPashiAppUserDAO {

	public PashiAppUserDAO() {
		super(PashiAppUser.class);
	}
	
	
	  @SuppressWarnings("unchecked")
	  public List<PashiAppUser> checkUserPassword(String userName, String password) 
	  {
	    Object[] parameters = {userName, password};
	    return getHibernateTemplate().find("select model from PashiAppUser model where model.userName = ? " +
	    		" and model.password = ? and model.isDeleted = 'N' ",parameters);
	  }


	//@SuppressWarnings("unchecked")
//	public String checkUserPassword(String userName, String password) 
	//{
		
		/*	
		 * Object[] parameters = {userName, password};
			return getHibernateTemplate().find("select model.userName from PashiAppUser model where " +
				" model.userName = ? and model.password = ? and model.isDeleted = 'N' ",parameters);
		*/	
		
		/*
			Query query = getSession().createQuery("select model.userName from PashiAppUser model where " +
				" model.userName = ? and model.password = ? and model.isDeleted = 'N' ");
		
		  	query.setParameter(0, userName);
	      	query.setParameter(1, password);
	       	// query.setFirstResult(0);
	     	// query.setMaxResults(100);
	      return (String) query.uniqueResult();
	      */
		
		/*
			Object[] parameters = {userName, password};
			Query query = getSession().createQuery("select model.userName, model.password from PashiAppUser model where " +
				" model.userName = :srinu1 and model.password = :srinu2 and model.isDeleted = 'N' ");
		
			query.setParameter("srinu1", userName);
			query.setParameter("srinu2", password);
	    
	     // query.setFirstResult(0);
	     // query.setMaxResults(100);
	      
			return query.list();
	        */
		
		//Criteria aa = getSession().createCriteria(PashiAppUser.class);
	//}
	
	
	
}
