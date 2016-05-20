package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IBloodDonationAgeGroupDAO;
import com.itgrids.partyanalyst.model.BloodDonationAgeGroup;

public class BloodDonationAgeGroupDAO extends GenericDaoHibernate<BloodDonationAgeGroup, Long> implements IBloodDonationAgeGroupDAO {

	public BloodDonationAgeGroupDAO(){
		super(BloodDonationAgeGroup.class);
	}
}
