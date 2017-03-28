package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.INominatedPostPositionDAO;
import com.itgrids.partyanalyst.model.NominatedPostPosition;

public class NominatedPostPositionDAO extends GenericDaoHibernate<NominatedPostPosition, Long> implements INominatedPostPositionDAO{

	public NominatedPostPositionDAO() {
		super(NominatedPostPosition.class);
		// TODO Auto-generated constructor stub
	}

}
