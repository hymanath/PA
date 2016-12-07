package com.itgrids.cardprint.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.cardprint.dao.IUserPrintVendorDAO;
import com.itgrids.cardprint.model.UserPrintVendor;

public class UserPrintVendorDAO extends GenericDaoHibernate<UserPrintVendor, Long> implements IUserPrintVendorDAO {

	public UserPrintVendorDAO(){
		super(UserPrintVendor.class);
	}
    
	public List<Long> getPrintVendorIdByUserId(Long userId){
		
		Query query = getSession().createQuery("" +
	    " select model.printVendorId " +
	    " from  UserPrintVendor model where model.userId = :userId ");
		
		query.setParameter("userId",userId);
		return query.list();
	}
}
