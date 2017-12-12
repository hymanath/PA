package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMomPriorityDAO;
import com.itgrids.partyanalyst.model.MomPriority;

public class MomPriorityDAO extends GenericDaoHibernate<MomPriority, Long> implements IMomPriorityDAO {

	public MomPriorityDAO() {
		super(MomPriority.class);
	}

}
