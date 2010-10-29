package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.InfluencingPeople;

public interface IInfluencingPeopleDAO extends GenericDao<InfluencingPeople, Long> {
	
	public List<InfluencingPeople> findByHamletId(Long hamletId);
	public List<InfluencingPeople> findByTehsils(String tehsilIds);	
	public List<Object[]> getDetailsByInfluencingPersonId(Long influencingPersonId);
	public Integer deleteInfluencingPeopleById(Long influencingPeopleId);
	public List<InfluencingPeople> findByStateId(Long stateId,Long registrationId);
	public List<InfluencingPeople> findByDistrictId(Long districtId,Long registrationId);
	public List<InfluencingPeople> findByConstituencyId(Long constituencyId,Long registrationId);

}
