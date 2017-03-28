package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IOccasionTypeDAO;
import com.itgrids.partyanalyst.model.LeaderOccasion;
import com.itgrids.partyanalyst.model.OccasionType;

public class OccasionTypeDAO extends GenericDaoHibernate<OccasionType, Long> implements IOccasionTypeDAO{

	public OccasionTypeDAO() {
		super(OccasionType.class);
		
	}

}
