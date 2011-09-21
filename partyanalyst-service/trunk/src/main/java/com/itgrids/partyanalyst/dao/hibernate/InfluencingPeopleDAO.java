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
	
	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInConstituency(Long userId,
			Long constituencyId) {
		Object[] params = {userId,constituencyId};
		return getHibernateTemplate().find("select count(model.influencingPeopleId) from InfluencingPeople model where "+
				"model.registration.registrationId = ? and model.userAddress.constituency.constituencyId = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInAssemblyConstituencies(Long userId,List<Long> constituencyIds){
		
		Query queryObject = getSession().createQuery("select count(model.influencingPeopleId) from InfluencingPeople model where "+
				"model.registration.registrationId = ? and model.userAddress.constituency.constituencyId in (:constituencyIds)");
		queryObject.setParameter(0,userId);
		queryObject.setParameterList("constituencyIds", constituencyIds);
	 return queryObject.list();
	}

	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInDistrict(Long userId,
			Long districtId) {
		Object[] params = {userId,districtId};
		return getHibernateTemplate().find("select count(model.influencingPeopleId) from InfluencingPeople model where "+
				"model.registration.registrationId = ? and model.userAddress.district.districtId = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInLocalBodys(Long userId,
			Long localBodyId,Long constituencyId) {
		Object[] params = {userId,localBodyId,constituencyId};
		return getHibernateTemplate().find("select count(model.influencingPeopleId) from InfluencingPeople model where "+
				"model.registration.registrationId = ? and model.userAddress.localElectionBody.localElectionBodyId = ? "+
				"and model.userAddress.constituency.constituencyId = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInState(Long userId,
			Long stateId) {
		Object[] params = {userId,stateId};
		return getHibernateTemplate().find("select count(model.influencingPeopleId) from InfluencingPeople model where "+
				"model.registration.registrationId = ? and model.userAddress.state.stateId = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInTehsil(Long userId,
			Long tehsilId) {
		Object[] params = {userId,tehsilId};
		return getHibernateTemplate().find("select count(model.influencingPeopleId) from InfluencingPeople model where "+
				"model.registration.registrationId = ? and model.userAddress.tehsil.tehsilId = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInVillage(Long userId,
			Long hamletId) {
		Object[] params = {userId,hamletId};
		return getHibernateTemplate().find("select count(model.influencingPeopleId) from InfluencingPeople model where "+
				"model.registration.registrationId = ? and model.userAddress.hamlet.hamletId = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInWard(Long userId, Long wardId) {
		Object[] params = {userId,wardId};
		return getHibernateTemplate().find("select count(model.influencingPeopleId) from InfluencingPeople model where "+
				"model.registration.registrationId = ? and model.userAddress.ward.constituencyId = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInConstituenciesByDistrict(
			Long userId, Long districtId) {
		Object[] params = {userId,districtId};
		return getHibernateTemplate().find("select count(model.influencingPeopleId),model.userAddress.constituency.constituencyId from InfluencingPeople model where "+
				"model.registration.registrationId = ? and model.userAddress.district.districtId = ? and "+
				"model.userAddress.constituency is not null group by model.userAddress.constituency.constituencyId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInAssemblyConstituenciesGroupByConstituency(Long userId,List<Long> constituencyIds){
		
		Query queryObject = getSession().createQuery("select count(model.influencingPeopleId),model.userAddress.constituency.constituencyId from InfluencingPeople model where "+
				"model.registration.registrationId = ? and model.userAddress.constituency.constituencyId in (:constituencyIds) and "+
				"model.userAddress.constituency is not null group by model.userAddress.constituency.constituencyId");
		queryObject.setParameter(0,userId);
		queryObject.setParameterList("constituencyIds", constituencyIds);
	 return queryObject.list();
	}

	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInDistrictsByState(Long userId,
			Long stateId) {
		Object[] params = {userId,stateId};
		return getHibernateTemplate().find("select count(model.influencingPeopleId),model.userAddress.district.districtId from InfluencingPeople model where "+
				"model.registration.registrationId = ? and model.userAddress.state.stateId = ? and "+
				"model.userAddress.district is not null group by model.userAddress.district.districtId",params);
	}

	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInLocalBodysByConstituency(
			Long userId, Long constituencyId) {
		Object[] params = {userId,constituencyId};
		return getHibernateTemplate().find("select count(model.influencingPeopleId),model.userAddress.localElectionBody.localElectionBodyId from InfluencingPeople model where "+
				"model.registration.registrationId = ? and model.userAddress.constituency.constituencyId = ? and "+
				"model.userAddress.localElectionBody is not null group by model.userAddress.localElectionBody.localElectionBodyId",params);
	}

	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInTehsilsByConstituency(
			Long userId, Long constituencyId) {
		Object[] params = {userId,constituencyId};
		return getHibernateTemplate().find("select count(model.influencingPeopleId),model.userAddress.tehsil.tehsilId from InfluencingPeople model where "+
				"model.registration.registrationId = ? and model.userAddress.constituency.constituencyId = ? and "+
				"model.userAddress.tehsil is not null group by model.userAddress.tehsil.tehsilId",params);
	}

	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInVillagesByTehsil(Long userId,
			Long tehsilId) {
		Object[] params = {userId,tehsilId};
		return getHibernateTemplate().find("select count(model.influencingPeopleId),model.userAddress.hamlet.hamletId from InfluencingPeople model where "+
				"model.registration.registrationId = ? and model.userAddress.tehsil.tehsilId = ? and "+
				"model.userAddress.hamlet is not null group by model.userAddress.hamlet.hamletId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInBoothsByTehsil(Long userId,
			Long tehsilId) {
		Object[] params = {userId,tehsilId};
		return getHibernateTemplate().find("select count(model.influencingPeopleId),model.userAddress.booth.boothId from InfluencingPeople model where "+
				"model.registration.registrationId = ? and model.userAddress.tehsil.tehsilId = ? and "+
				"model.userAddress.booth is not null group by model.userAddress.booth.boothId",params);
	}

	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInWardsByLocalBody(Long userId,
			Long localBodyId,Long constituencyId) {
		Object[] params = {userId,localBodyId,constituencyId};
		return getHibernateTemplate().find("select count(model.influencingPeopleId),model.userAddress.ward.constituencyId from InfluencingPeople model where "+
				"model.registration.registrationId = ? and model.userAddress.localElectionBody.localElectionBodyId = ? and "+
				"model.userAddress.ward is not null and model.userAddress.constituency.constituencyId = ? group by model.userAddress.ward.constituencyId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInBoothsByLocalBody(Long userId,
			Long localBodyId,Long constituencyId) {
		Object[] params = {userId,localBodyId,constituencyId};
		return getHibernateTemplate().find("select count(model.influencingPeopleId),model.userAddress.booth.boothId from InfluencingPeople model where "+
				"model.registration.registrationId = ? and model.userAddress.localElectionBody.localElectionBodyId = ? and "+
				"model.userAddress.booth is not null and model.userAddress.constituency.constituencyId = ? group by model.userAddress.booth.boothId",params);
	}

	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleDetailsInConstituency(Long userId,
			Long constituencyId) {
		Object[] params = {userId,constituencyId};
		return getHibernateTemplate().find("select model.influencingPeopleId,model.firstName,model.lastName,model.middleName,model.influencingScope,"+
				"model.influencingScopeValue,model.caste,model.occupation,model.phoneNo,model.gender,model.email,model.fatherOrSpouseName,"+
				"model.influencingPeoplePosition.influencingPeoplePositionId,model.influencingPeoplePosition.position from InfluencingPeople model where "+
				"model.registration.registrationId = ? and model.userAddress.constituency is not null and model.userAddress.constituency.constituencyId = ? "+
				"and model.userAddress.tehsil is not null order by model.userAddress.tehsil.tehsilId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleAddressInConstituency(Long userId,
			Long constituencyId) {
		Object[] params = {userId,constituencyId};
		return getHibernateTemplate().find("select model.userAddress from InfluencingPeople model where "+
				"model.registration.registrationId = ? and model.userAddress.constituency is not null and model.userAddress.constituency.constituencyId = ? "+
				"and model.userAddress.tehsil is not null order by model.userAddress.tehsil.tehsilId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleDetailsInConstituencyByLocalBody(Long userId,
			Long constituencyId) {
		Object[] params = {userId,constituencyId};
		return getHibernateTemplate().find("select model.influencingPeopleId,model.firstName,model.lastName,model.middleName,model.influencingScope,"+
				"model.influencingScopeValue,model.caste,model.occupation,model.phoneNo,model.gender,model.email,model.fatherOrSpouseName,"+
				"model.influencingPeoplePosition.influencingPeoplePositionId,model.influencingPeoplePosition.position from InfluencingPeople model where "+
				"model.registration.registrationId = ? and model.userAddress.constituency is not null and model.userAddress.constituency.constituencyId = ? "+
				"and model.userAddress.localElectionBody is not null order by model.userAddress.localElectionBody.localElectionBodyId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleAddressInConstituencyByLocalBody(Long userId,
			Long constituencyId) {
		Object[] params = {userId,constituencyId};
		return getHibernateTemplate().find("select model.userAddress from InfluencingPeople model where "+
				"model.registration.registrationId = ? and model.userAddress.constituency is not null and model.userAddress.constituency.constituencyId = ? "+
				"and model.userAddress.localElectionBody is not null order by model.userAddress.localElectionBody.localElectionBodyId",params);
	}

	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleDetailsInDistrict(Long userId,
			Long districtId) {
		Object[] params = {userId,districtId};
		return getHibernateTemplate().find("select model.influencingPeopleId,model.firstName,model.lastName,model.middleName,model.influencingScope,"+
				"model.influencingScopeValue,model.caste,model.occupation,model.phoneNo,model.gender,model.email,model.fatherOrSpouseName,"+
				"model.influencingPeoplePosition.influencingPeoplePositionId,model.influencingPeoplePosition.position "+
				"from InfluencingPeople model where model.registration.registrationId = ? and model.userAddress.district.districtId = ? "+
				"and model.userAddress.constituency is not null order by model.userAddress.constituency.constituencyId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleDetailsInAssemblyConstituencies(Long userId,List<Long> constituencyIds){
		
		Query queryObject = getSession().createQuery("select model.influencingPeopleId,model.firstName,model.lastName,model.middleName,model.influencingScope,"+
				"model.influencingScopeValue,model.caste,model.occupation,model.phoneNo,model.gender,model.email,model.fatherOrSpouseName,"+
				"model.influencingPeoplePosition.influencingPeoplePositionId,model.influencingPeoplePosition.position "+
				"from InfluencingPeople model where model.registration.registrationId = ? and model.userAddress.constituency.constituencyId in (:constituencyIds) "+
				"and model.userAddress.constituency is not null order by model.userAddress.constituency.constituencyId");
		queryObject.setParameter(0,userId);
		queryObject.setParameterList("constituencyIds", constituencyIds);
	 return queryObject.list();
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleAddressInDistrict(Long userId,
			Long districtId) {
		Object[] params = {userId,districtId};
		return getHibernateTemplate().find("select model.userAddress "+
				"from InfluencingPeople model where model.registration.registrationId = ? and model.userAddress.district.districtId = ? "+
				"and model.userAddress.constituency is not null order by model.userAddress.constituency.constituencyId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleAddressInAssemblyConstituencies(Long userId,List<Long> constituencyIds){
		
		Query queryObject = getSession().createQuery("select model.userAddress "+
				"from InfluencingPeople model where model.registration.registrationId = ? and model.userAddress.constituency.constituencyId in (:constituencyIds) "+
				"and model.userAddress.constituency is not null order by model.userAddress.constituency.constituencyId");
		queryObject.setParameter(0,userId);
		queryObject.setParameterList("constituencyIds", constituencyIds);
	 return queryObject.list();
	}

	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleDetailsInLocalBodys(Long userId,
			Long localBodyId,Long constituencyId) {
		Object[] params = {userId,localBodyId,constituencyId};
		return getHibernateTemplate().find("select model.influencingPeopleId,model.firstName,model.lastName,model.middleName,model.influencingScope,"+
				"model.influencingScopeValue,model.caste,model.occupation,model.phoneNo,model.gender,model.email,model.fatherOrSpouseName,"+
				"model.influencingPeoplePosition.influencingPeoplePositionId,model.influencingPeoplePosition.position from InfluencingPeople model where "+
				"model.registration.registrationId = ? and model.userAddress.localElectionBody is not null and model.userAddress.localElectionBody.localElectionBodyId = ? "+
				"and model.userAddress.ward is not null and model.userAddress.constituency.constituencyId = ? order by model.userAddress.ward.constituencyId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleAddressInLocalBodys(Long userId,
			Long localBodyId,Long constituencyId) {
		Object[] params = {userId,localBodyId,constituencyId};
		return getHibernateTemplate().find("select model.userAddress from InfluencingPeople model where "+
				"model.registration.registrationId = ? and model.userAddress.localElectionBody is not null and model.userAddress.localElectionBody.localElectionBodyId = ? "+
				"and model.userAddress.ward is not null and model.userAddress.constituency.constituencyId = ? order by model.userAddress.ward.constituencyId",params);
	}

	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleDetailsInState(Long userId,
			Long stateId) {
		Object[] params = {userId,stateId};
		return getHibernateTemplate().find("select model.influencingPeopleId,model.firstName,model.lastName,model.middleName,model.influencingScope,"+
				"model.influencingScopeValue,model.caste,model.occupation,model.phoneNo,model.gender,model.email,model.fatherOrSpouseName,"+
				"model.influencingPeoplePosition.influencingPeoplePositionId,model.influencingPeoplePosition.position from InfluencingPeople model where "+
				"model.registration.registrationId = ? and model.userAddress.state is not null and model.userAddress.state.stateId = ? "+
				"and model.userAddress.district is not null order by model.userAddress.district.districtId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleAddressInState(Long userId,
			Long stateId) {
		Object[] params = {userId,stateId};
		return getHibernateTemplate().find("select model.userAddress from InfluencingPeople model where "+
				"model.registration.registrationId = ? and model.userAddress.state is not null and model.userAddress.state.stateId = ? "+
				"and model.userAddress.district is not null order by model.userAddress.district.districtId",params);
	}

	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleDetailsInTehsil(Long userId,
			Long tehsilId) {
		Object[] params = {userId,tehsilId};
		return getHibernateTemplate().find("select model.influencingPeopleId,model.firstName,model.lastName,model.middleName,model.influencingScope,"+
				"model.influencingScopeValue,model.caste,model.occupation,model.phoneNo,model.gender,model.email,model.fatherOrSpouseName,"+
				"model.influencingPeoplePosition.influencingPeoplePositionId,model.influencingPeoplePosition.position from InfluencingPeople model where "+
				"model.registration.registrationId = ? and model.userAddress.tehsil is not null and model.userAddress.tehsil.tehsilId = ? "+
				"and model.userAddress.hamlet is not null order by model.userAddress.hamlet.hamletId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleAddressInTehsil(Long userId,
			Long tehsilId) {
		Object[] params = {userId,tehsilId};
		return getHibernateTemplate().find("select model.userAddress from InfluencingPeople model where "+
				"model.registration.registrationId = ? and model.userAddress.tehsil is not null and model.userAddress.tehsil.tehsilId = ? "+
				"and model.userAddress.hamlet is not null order by model.userAddress.hamlet.hamletId",params);
	}

	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleDetailsInVillage(Long userId,
			Long hamletId) {
		Object[] params = {userId,hamletId};
		return getHibernateTemplate().find("select model.influencingPeopleId,model.firstName,model.lastName,model.middleName,model.influencingScope,"+
				"model.influencingScopeValue,model.caste,model.occupation,model.phoneNo,model.gender,model.email,model.fatherOrSpouseName,"+
				"model.influencingPeoplePosition.influencingPeoplePositionId,model.influencingPeoplePosition.position from InfluencingPeople model where "+
				"model.registration.registrationId = ? and model.userAddress.hamlet is not null and model.userAddress.hamlet.hamletId = ? ",params);
				
	}
	
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleAddressInVillage(Long userId,
			Long hamletId) {
		Object[] params = {userId,hamletId};
		return getHibernateTemplate().find("select model.userAddress from InfluencingPeople model where "+
				"model.registration.registrationId = ? and model.userAddress.hamlet is not null and model.userAddress.hamlet.hamletId = ? ",params);
				
	}

	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleDetailsInWard(Long userId, Long wardId) {
		Object[] params = {userId,wardId};
		return getHibernateTemplate().find("select model.influencingPeopleId,model.firstName,model.lastName,model.middleName,model.influencingScope,"+
				"model.influencingScopeValue,model.caste,model.occupation,model.phoneNo,model.gender,model.email,model.fatherOrSpouseName,"+
				"model.influencingPeoplePosition.influencingPeoplePositionId,model.influencingPeoplePosition.position from InfluencingPeople model where "+
				"model.registration.registrationId = ? and model.userAddress.ward is not null and model.userAddress.ward.constituencyId = ? ",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleDetailsInBooth(Long userId, Long boothId) {
		Object[] params = {userId,boothId};
		return getHibernateTemplate().find("select model.influencingPeopleId,model.firstName,model.lastName,model.middleName,model.influencingScope,"+
				"model.influencingScopeValue,model.caste,model.occupation,model.phoneNo,model.gender,model.email,model.fatherOrSpouseName,"+
				"model.influencingPeoplePosition.influencingPeoplePositionId,model.influencingPeoplePosition.position from InfluencingPeople model where "+
				"model.registration.registrationId = ? and model.userAddress.booth is not null and model.userAddress.booth.boothId = ? ",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleAddressInWard(Long userId, Long wardId) {
		Object[] params = {userId,wardId};
		return getHibernateTemplate().find("select model.userAddress from InfluencingPeople model where "+
				"model.registration.registrationId = ? and model.userAddress.ward is not null and model.userAddress.ward.constituencyId = ? ",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleAddressInBooth(Long userId, Long boothId) {
		Object[] params = {userId,boothId};
		return getHibernateTemplate().find("select model.userAddress from InfluencingPeople model where "+
				"model.registration.registrationId = ? and model.userAddress.booth is not null and model.userAddress.booth.boothId = ? ",params);
	}

	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleCountByInfluencingScope(Long userId) {
		return getHibernateTemplate().find("select count(model.influencingPeopleId),model.influencingScope from InfluencingPeople model where "+
				"model.registration.registrationId = ? group by model.influencingScope order by model.influencingScope",userId);
	}

	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleCountByInfluencingScope(Long userId,
			String influencingScope) {
		Object[] params = {userId,influencingScope};
		return getHibernateTemplate().find("select count(model.influencingPeopleId),model.influencingScopeValue from InfluencingPeople model where "+
				"model.registration.registrationId = ? and model.influencingScope = ? group by model.influencingScopeValue",params);
	}

	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleDetailsByInfluencingScope(Long userId,
			String influencingScope, String scopeValueId) {
		Object[] params = {userId,influencingScope,scopeValueId};
		return getHibernateTemplate().find("select model.influencingPeopleId,model.firstName,model.lastName,model.middleName,model.influencingScope,"+
				"model.influencingScopeValue,model.caste,model.occupation,model.phoneNo,model.gender,model.email,model.fatherOrSpouseName,"+
				"model.influencingPeoplePosition.influencingPeoplePositionId,model.influencingPeoplePosition.position from InfluencingPeople model where "+
				"model.registration.registrationId = ? and model.influencingScope = ? and model.influencingScopeValue = ? order by model.influencingScope",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleAddressByInfluencingScope(Long userId,
			String influencingScope, String scopeValueId) {
		Object[] params = {userId,influencingScope,scopeValueId};
		return getHibernateTemplate().find("select model.userAddress from InfluencingPeople model where "+
				"model.registration.registrationId = ? and model.influencingScope = ? and model.influencingScopeValue = ? order by model.influencingScope",params);
	}

	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleDetailsByInfluencingScope(Long userId,
			String influencingScope) {
		Object[] params = {userId,influencingScope};
		return getHibernateTemplate().find("select model.influencingPeopleId,model.firstName,model.lastName,model.middleName,model.influencingScope,"+
				"model.influencingScopeValue,model.caste,model.occupation,model.phoneNo,model.gender,model.email,model.fatherOrSpouseName,"+
				"model.influencingPeoplePosition.influencingPeoplePositionId,model.influencingPeoplePosition.position from InfluencingPeople model where "+
				"model.registration.registrationId = ? and model.influencingScope = ? order by model.influencingScope",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleAddressByInfluencingScope(Long userId,
			String influencingScope) {
		Object[] params = {userId,influencingScope};
		return getHibernateTemplate().find("select model.userAddress from InfluencingPeople model where "+
				"model.registration.registrationId = ? and model.influencingScope = ? order by model.influencingScope",params);
	}

	@SuppressWarnings("unchecked")
	public List getInfluencingPeopleNameAndMobileNOByIds(List<Long> infIds) {
		Query queryObject = getSession().createQuery("select model.firstName,model.lastName,model.phoneNo from InfluencingPeople model where model.influencingPeopleId in (:infIds)");
		queryObject.setParameterList("infIds", infIds);
		return queryObject.list();
	}

	@SuppressWarnings("unchecked")
	public List getInfluencingPeopleNameAndMobileNOByIds(String infIds) {
		return getHibernateTemplate().find("select model.firstName,model.lastName,model.phoneNo from InfluencingPeople model where model.influencingPeopleId in ("+infIds+")");
	}

	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInBoothsByWard(Long userId,
			Long wardId) {
		Object[] params = {userId,wardId};
		return getHibernateTemplate().find("select count(model.influencingPeopleId),model.userAddress.booth.boothId from InfluencingPeople model where "+
				"model.registration.registrationId = ? and model.userAddress.ward.constituencyId = ? and "+
				"model.userAddress.booth is not null group by model.userAddress.booth.boothId",params);
	}

	@SuppressWarnings("unchecked")
	public List getInfluencingPersonDetailsById(Long influencingPersonId) {
		return getHibernateTemplate().find("select model.influencingPeopleId,model.firstName,model.middleName,model.lastName,model.party.shortName,"+
				"model.caste,model.occupation,model.phoneNo,model.gender,model.email,model.fatherOrSpouseName,model.influencingPeoplePosition."+
				"position,model.influencingScope,model.influencingScopeValue from InfluencingPeople model where model.influencingPeopleId = ?",influencingPersonId);
				
	}

	@SuppressWarnings("unchecked")
	public List getInfluencingPersonLocationDetailsById(Long influencingPersonId) {
		return getHibernateTemplate().find("select model.userAddress from InfluencingPeople model where model.influencingPeopleId = ?",influencingPersonId);
	}

		
}
