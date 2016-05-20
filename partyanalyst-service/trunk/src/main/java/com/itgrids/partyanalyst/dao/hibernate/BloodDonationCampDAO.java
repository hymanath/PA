package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IBloodDonationCampDAO;
import com.itgrids.partyanalyst.model.BloodDonationCamp;

public class BloodDonationCampDAO extends GenericDaoHibernate<BloodDonationCamp, Long> implements
		IBloodDonationCampDAO {
	public BloodDonationCampDAO(){
		super(BloodDonationCamp.class);
	}

}
