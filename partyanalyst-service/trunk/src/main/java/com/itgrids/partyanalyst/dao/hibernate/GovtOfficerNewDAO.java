package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGovtOfficerNewDAO;
import com.itgrids.partyanalyst.model.GovtOfficerNew;

public class GovtOfficerNewDAO extends GenericDaoHibernate<GovtOfficerNew, Long> implements IGovtOfficerNewDAO {

	public GovtOfficerNewDAO(){
		super(GovtOfficerNew.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getOfficerDetailsByOfficerId (Long officerId){
		return getSession().createQuery(" select  distinct model.mobileNo from GovtOfficerNew model where model.govtOfficerId ="+officerId+" and " +
				"  model.mobileNo is not null ").list();
	}
}
