package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserGallaryDAO;
import com.itgrids.partyanalyst.model.UserGallary;

public class UserGallaryDAO extends GenericDaoHibernate<UserGallary, Long> implements IUserGallaryDAO{
	
	public UserGallaryDAO(){
		super(UserGallary.class);
	}
}
