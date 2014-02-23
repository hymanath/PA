package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.IVoterBoothActivitiesDAO;
import com.itgrids.partyanalyst.model.VoterBoothActivities;

public class VoterBoothActivitiesDAO extends GenericDaoHibernate<VoterBoothActivities,Long> implements IVoterBoothActivitiesDAO{

	public VoterBoothActivitiesDAO()
	{
		super(VoterBoothActivities.class);
	}
}
