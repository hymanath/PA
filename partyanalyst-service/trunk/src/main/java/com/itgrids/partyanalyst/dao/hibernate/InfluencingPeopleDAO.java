package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IInfluencingPeopleDAO;
import com.itgrids.partyanalyst.model.InfluencingPeople;

public class InfluencingPeopleDAO extends GenericDaoHibernate<InfluencingPeople, Long> implements IInfluencingPeopleDAO{

	public InfluencingPeopleDAO(){
		super(InfluencingPeople.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<InfluencingPeople> findByHamletId(Long hamletId){
		return getHibernateTemplate().find("from InfluencingPeople model where model.hamlet.hamletId = ?",hamletId);
	}
	
	@SuppressWarnings("unchecked")
	public List<InfluencingPeople> findByTehsils(String tehsilIds){
		return getHibernateTemplate().find("from InfluencingPeople model where model.hamlet.township.tehsil.tehsilId in ("+tehsilIds+")");
	}
	
	@SuppressWarnings("unchecked")
	public List<InfluencingPeople> findByStateId(Long stateId){
		return getHibernateTemplate().find("from InfluencingPeople model where model.userAddress.state.stateId = ?",stateId);
	}
	
	@SuppressWarnings("unchecked")
	public List<InfluencingPeople> findByDistrictId(Long districtId){
		return getHibernateTemplate().find("from InfluencingPeople model where model.userAddress.district.districtId = ?",districtId);
	}
	
	@SuppressWarnings("unchecked")
	public List<InfluencingPeople> findByConstituencyId(Long constituencyId){
		return getHibernateTemplate().find("from InfluencingPeople model where model.userAddress.constituency.constituencyId = ?",constituencyId);
	}
	
	
	
	
	
	
	
}
