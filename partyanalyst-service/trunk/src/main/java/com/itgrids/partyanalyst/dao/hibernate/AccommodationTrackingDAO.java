package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAccommodationTrackingDAO;
import com.itgrids.partyanalyst.model.AccommodationTracking;

public class AccommodationTrackingDAO extends GenericDaoHibernate<AccommodationTracking, Long> implements IAccommodationTrackingDAO{

	public AccommodationTrackingDAO() {
		super(AccommodationTracking.class);
		
	}

}
