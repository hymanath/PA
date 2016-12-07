package com.itgrids.cardprint.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.cardprint.dao.IUserPrintVendorDAO;
import com.itgrids.cardprint.model.UserPrintVendor;

public class UserPrintVendorDAO extends GenericDaoHibernate<UserPrintVendor, Long> implements IUserPrintVendorDAO {

	public UserPrintVendorDAO(){
		super(UserPrintVendor.class);
	}

}
