package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IRepresentativeLevelDAO;
import com.itgrids.partyanalyst.model.RepresentativeLevel;

public class RepresentativeLevelDAO extends GenericDaoHibernate<RepresentativeLevel, Long> implements IRepresentativeLevelDAO{

	public RepresentativeLevelDAO( ) {
		super(RepresentativeLevel.class);
	}

}
