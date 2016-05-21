package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.BloodDonation;

public interface IBloodDonationDAO extends GenericDao<BloodDonation, Long> {
	
	public Object[] getCadreDetailsOfRegistered(String memberShipId);
	public List<Object[]> getBleedingCadreDetails(List<Long> statusIds,Long campId);

}
