package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.BloodDonationAgeGroup;

public interface IBloodDonationAgeGroupDAO extends
		GenericDao<BloodDonationAgeGroup, Long> {
	
	public List<BloodDonationAgeGroup> getAllAgeGroups();

}
