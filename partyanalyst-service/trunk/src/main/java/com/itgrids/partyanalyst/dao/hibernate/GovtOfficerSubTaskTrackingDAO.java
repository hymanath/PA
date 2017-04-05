package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGovtOfficerSubTaskTrackingDAO;
import com.itgrids.partyanalyst.model.GovtOfficerSubTaskTracking;

public class GovtOfficerSubTaskTrackingDAO extends GenericDaoHibernate<GovtOfficerSubTaskTracking, Long> implements IGovtOfficerSubTaskTrackingDAO{

	public GovtOfficerSubTaskTrackingDAO() {
		super(GovtOfficerSubTaskTracking.class);
		
	}
	
	
}
