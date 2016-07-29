package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.INominatedPostAgeRangeDAO;
import com.itgrids.partyanalyst.model.NominatedPostAgeRange;

public class NominatedPostAgeRangeDAO extends GenericDaoHibernate<NominatedPostAgeRange, Long> implements INominatedPostAgeRangeDAO{

	public NominatedPostAgeRangeDAO() {
		super(NominatedPostAgeRange.class);
		// TODO Auto-generated constructor stub
	}

}
