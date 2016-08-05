package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IVolunteerDesginationDAO;
import com.itgrids.partyanalyst.model.VolunteerDesgination;

public class VolunteerDesginationDAO extends GenericDaoHibernate<VolunteerDesgination, Long> implements IVolunteerDesginationDAO{
	public VolunteerDesginationDAO(){
		super(VolunteerDesgination.class);
	}
}
