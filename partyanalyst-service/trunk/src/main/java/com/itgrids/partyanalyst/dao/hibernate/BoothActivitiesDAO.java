package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.IBoothActivitiesDAO;
import com.itgrids.partyanalyst.model.BoothActivities;

public class BoothActivitiesDAO extends GenericDaoHibernate<BoothActivities, Long> implements IBoothActivitiesDAO{

	public BoothActivitiesDAO()
	{
		super(BoothActivities.class);
	}
}
