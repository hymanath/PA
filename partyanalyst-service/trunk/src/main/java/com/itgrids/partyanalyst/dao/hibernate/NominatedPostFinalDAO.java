package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.INominatedPostFinalDAO;
import com.itgrids.partyanalyst.model.NominatedPostFinal;

public class NominatedPostFinalDAO extends GenericDaoHibernate<NominatedPostFinal, Long> implements INominatedPostFinalDAO{

	public NominatedPostFinalDAO() {
		super(NominatedPostFinal.class);
		// TODO Auto-generated constructor stub
	}

}
