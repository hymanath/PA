package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreGhmcDriveUsersDAO;
import com.itgrids.partyanalyst.model.CadreGhmcDriveUsers;



public class CadreGhmcDriveUsersDAO  extends GenericDaoHibernate<CadreGhmcDriveUsers, Long> implements ICadreGhmcDriveUsersDAO{

	public CadreGhmcDriveUsersDAO() {
		super(CadreGhmcDriveUsers.class);
	}
	
	public List<Long> getGHMCDriveUsers(){
		
		Query query = getSession().createQuery("select distinct model.userId from CadreGhmcDriveUsers model");		
		return query.list();
		
	}
}
