package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.INominatedPostReferDetailsDAO;
import com.itgrids.partyanalyst.model.NominatedPostReferDetails;

public class NominatedPostReferDetailsDAO extends GenericDaoHibernate<NominatedPostReferDetails, Long> implements INominatedPostReferDetailsDAO{

	public NominatedPostReferDetailsDAO() {
		super(NominatedPostReferDetails.class);
		// TODO Auto-generated constructor stub
	}

}
