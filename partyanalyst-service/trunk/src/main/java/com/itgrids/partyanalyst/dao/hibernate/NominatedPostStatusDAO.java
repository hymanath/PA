package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.INominatedPostStatusDAO;
import com.itgrids.partyanalyst.model.NominatedPostStatus;

public class NominatedPostStatusDAO extends GenericDaoHibernate<NominatedPostStatus, Long> implements INominatedPostStatusDAO{

	public NominatedPostStatusDAO() {
		super(NominatedPostStatus.class);
		// TODO Auto-generated constructor stub
	}

}
