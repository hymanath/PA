package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IVolunteersCadreDetailsDAO;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.VolunteersCadreDetails;

public class VolunteersCadreDetailsDAO extends GenericDaoHibernate<VolunteersCadreDetails, Long> implements IVolunteersCadreDetailsDAO{
	public VolunteersCadreDetailsDAO(){
		super(VolunteersCadreDetails.class);
	}
}
