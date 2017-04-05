package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGovtOfficerNewDAO;
import com.itgrids.partyanalyst.model.GovtOfficerNew;

public class GovtOfficerNewDAO extends GenericDaoHibernate<GovtOfficerNew, Long> implements IGovtOfficerNewDAO {

	public GovtOfficerNewDAO(){
		super(GovtOfficerNew.class);
	}
}
