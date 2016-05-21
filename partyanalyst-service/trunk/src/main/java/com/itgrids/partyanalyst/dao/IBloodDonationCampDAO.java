package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.BloodDonationCamp;

public interface IBloodDonationCampDAO extends GenericDao<BloodDonationCamp, Long> {
	
	public Object[] getCampDates(Long campId);
}
