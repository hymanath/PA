package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

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
	public List<InfluencingPeople> findByStateId(Long stateId,Long registrationId){
		Object[] params = {stateId, registrationId};
		return getHibernateTemplate().find("from InfluencingPeople model where model.userAddress.state.stateId = ? and model.registration.registrationId = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<InfluencingPeople> findByDistrictId(Long districtId,Long registrationId){
		Object[] params = {districtId, registrationId};
		return getHibernateTemplate().find("from InfluencingPeople model where model.userAddress.district.districtId = ? and model.registration.registrationId = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<InfluencingPeople> findByConstituencyId(Long constituencyId,Long registrationId){
		Object[] params = {constituencyId, registrationId};
		return getHibernateTemplate().find("from InfluencingPeople model where model.userAddress.constituency.constituencyId = ? and model.registration.registrationId = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	/*public List<Object[]> getDetailsByInfluencingPersonId(Long influencingPersonId){
		return getHibernateTemplate().find("select model.firstName,model.userAddress.district.districtName from InfluencingPeople model where model.influencingPeopleId = ?", influencingPersonId);
	}*/
	public List<Object[]> getDetailsByInfluencingPersonId(Long influencingPersonId){
		return getHibernateTemplate().find("select model.firstName, model.middleName, model.lastName, model.fatherOrSpouseName, model.gender,"+
					"model.phoneNo, model.email, model.occupation, model.party.partyId, model.caste, model.influencingPeoplePosition.influencingPeoplePositionId, " +
					"model.influencingScope,model.influencingScopeValue,model.userAddress.userAddressId from "+
					"InfluencingPeople model where model.influencingPeopleId = ?", influencingPersonId);
	}
	
	public Integer deleteInfluencingPeopleById(Long influencingPeopleId){
		Query queryObject = getSession().createQuery("delete from InfluencingPeople model where model.influencingPeopleId = ?");
		queryObject.setParameter(0, influencingPeopleId);
		return queryObject.executeUpdate();
		
	}
}
