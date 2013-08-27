package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IInfluencingPeopleDAO;
import com.itgrids.partyanalyst.dto.InfluencingPeopleVO;
import com.itgrids.partyanalyst.dto.SMSSearchCriteriaVO;
import com.itgrids.partyanalyst.model.InfluencingPeople;
import com.itgrids.partyanalyst.utils.IConstants;

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
	public List<InfluencingPeople> findByStateId(Long stateId,Long userId){
		Object[] params = {stateId, userId};
		return getHibernateTemplate().find("from InfluencingPeople model where model.userAddress.state.stateId = ? and model.user.userId = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<InfluencingPeople> findByDistrictId(Long districtId,Long userId){
		Object[] params = {districtId, userId};
		return getHibernateTemplate().find("from InfluencingPeople model where model.userAddress.district.districtId = ? and model.user.userId = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<InfluencingPeople> findByConstituencyId(Long constituencyId,Long userId){
		Object[] params = {constituencyId, userId};
		return getHibernateTemplate().find("from InfluencingPeople model where model.userAddress.constituency.constituencyId = ? and model.user.userId = ?",params);
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
				"model.user.userId = ? and model.userAddress.constituency.constituencyId = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInAssemblyConstituencies(Long userId,List<Long> constituencyIds){
		
		Query queryObject = getSession().createQuery("select count(model.influencingPeopleId) from InfluencingPeople model where "+
				"model.user.userId = ? and model.userAddress.constituency.constituencyId in (:constituencyIds)");
		queryObject.setParameter(0,userId);
		queryObject.setParameterList("constituencyIds", constituencyIds);
	 return queryObject.list();
	}

	/*@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInDistrict(Long userId,
			Long districtId) {
		Object[] params = {userId,districtId};
		return getHibernateTemplate().find("select count(model.influencingPeopleId) from InfluencingPeople model where "+
				"model.user.userId = ? and model.userAddress.district.districtId = ? ",params);
	}*/
	
	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInDistrict(Long userId,
			Long districtId) {
		//Object[] params = {userId,districtId};
		Query queryObject = getSession().createQuery("select count(model.influencingPeopleId) from InfluencingPeople model where "+
				"model.user.userId =:userId and model.userAddress.constituency.constituencyId in(select model1.constituencyId from Constituency model1 where model1.district.districtId =:districtId) and model.userAddress.district.districtId =:districtId");
		queryObject.setParameter("userId", userId);
		queryObject.setParameter("districtId", districtId);
		return queryObject.list();
	}

	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInLocalBodys(Long userId,
			Long localBodyId,Long constituencyId) {
		Object[] params = {userId,localBodyId,constituencyId};
		return getHibernateTemplate().find("select count(model.influencingPeopleId) from InfluencingPeople model where "+
				"model.user.userId = ? and model.userAddress.localElectionBody.localElectionBodyId = ? "+
				"and model.userAddress.constituency.constituencyId = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInState(Long userId,
			Long stateId) {
		Object[] params = {userId,stateId};
		return getHibernateTemplate().find("select count(model.influencingPeopleId) from InfluencingPeople model where "+
				"model.user.userId = ? and model.userAddress.state.stateId = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInTehsil(Long userId,
			Long tehsilId) {
		Object[] params = {userId,tehsilId};
		return getHibernateTemplate().find("select count(model.influencingPeopleId) from InfluencingPeople model where "+
				"model.user.userId = ? and model.userAddress.tehsil.tehsilId = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInVillage(Long userId,
			Long hamletId) {
		Object[] params = {userId,hamletId};
		return getHibernateTemplate().find("select count(model.influencingPeopleId) from InfluencingPeople model where "+
				"model.user.userId = ? and model.userAddress.hamlet.hamletId = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInWard(Long userId, Long wardId) {
		Object[] params = {userId,wardId};
		return getHibernateTemplate().find("select count(model.influencingPeopleId) from InfluencingPeople model where "+
				"model.user.userId = ? and model.userAddress.ward.constituencyId = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInConstituenciesByDistrict(
			Long userId, Long districtId) {
		Object[] params = {userId,districtId};
		return getHibernateTemplate().find("select count(model.influencingPeopleId),model.userAddress.constituency.constituencyId from InfluencingPeople model where "+
				"model.user.userId = ? and model.userAddress.district.districtId = ? and "+
				"model.userAddress.constituency is not null group by model.userAddress.constituency.constituencyId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInAssemblyConstituenciesGroupByConstituency(Long userId,List<Long> constituencyIds){
		
		Query queryObject = getSession().createQuery("select count(model.influencingPeopleId),model.userAddress.constituency.constituencyId from InfluencingPeople model where "+
				"model.user.userId = ? and model.userAddress.constituency.constituencyId in (:constituencyIds) and "+
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
				"model.user.userId = ? and model.userAddress.state.stateId = ? and "+
				"model.userAddress.district is not null group by model.userAddress.district.districtId",params);
	}

	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInLocalBodysByConstituency(
			Long userId, Long constituencyId) {
		Object[] params = {userId,constituencyId};
		return getHibernateTemplate().find("select count(model.influencingPeopleId),model.userAddress.localElectionBody.localElectionBodyId from InfluencingPeople model where "+
				"model.user.userId = ? and model.userAddress.constituency.constituencyId = ? and "+
				"model.userAddress.localElectionBody is not null group by model.userAddress.localElectionBody.localElectionBodyId",params);
	}

	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInTehsilsByConstituency(
			Long userId, Long constituencyId) {
		Object[] params = {userId,constituencyId};
		return getHibernateTemplate().find("select count(model.influencingPeopleId),model.userAddress.tehsil.tehsilId from InfluencingPeople model where "+
				"model.user.userId = ? and model.userAddress.constituency.constituencyId = ? and "+
				"model.userAddress.tehsil is not null group by model.userAddress.tehsil.tehsilId",params);
	}

	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInVillagesByTehsil(Long userId,
			Long tehsilId) {
		Object[] params = {userId,tehsilId};
		return getHibernateTemplate().find("select count(model.influencingPeopleId),model.userAddress.hamlet.hamletId from InfluencingPeople model where "+
				"model.user.userId = ? and model.userAddress.tehsil.tehsilId = ? and "+
				"model.userAddress.hamlet is not null group by model.userAddress.hamlet.hamletId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInBoothsByTehsil(Long userId,
			Long tehsilId) {
		Object[] params = {userId,tehsilId};
		return getHibernateTemplate().find("select count(model.influencingPeopleId),model.userAddress.booth.boothId from InfluencingPeople model where "+
				"model.user.userId = ? and model.userAddress.tehsil.tehsilId = ? and "+
				"model.userAddress.booth is not null group by model.userAddress.booth.boothId",params);
	}

	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInWardsByLocalBody(Long userId,
			Long localBodyId,Long constituencyId) {
		Object[] params = {userId,localBodyId,constituencyId};
		return getHibernateTemplate().find("select count(model.influencingPeopleId),model.userAddress.ward.constituencyId from InfluencingPeople model where "+
				"model.user.userId = ? and model.userAddress.localElectionBody.localElectionBodyId = ? and "+
				"model.userAddress.ward is not null and model.userAddress.constituency.constituencyId = ? group by model.userAddress.ward.constituencyId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInBoothsByLocalBody(Long userId,
			Long localBodyId,Long constituencyId) {
		Object[] params = {userId,localBodyId,constituencyId};
		return getHibernateTemplate().find("select count(model.influencingPeopleId),model.userAddress.booth.boothId from InfluencingPeople model where "+
				"model.user.userId = ? and model.userAddress.localElectionBody.localElectionBodyId = ? and "+
				"model.userAddress.booth is not null and model.userAddress.constituency.constituencyId = ? group by model.userAddress.booth.boothId",params);
	}

	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleDetailsInConstituency(Long userId,
			Long constituencyId) {
		Object[] params = {userId,constituencyId};
		return getHibernateTemplate().find("select model.influencingPeopleId,model.firstName,model.lastName,model.middleName,model.influencingScope,"+
				"model.influencingScopeValue,model.caste,model.occupation,model.phoneNo,model.gender,model.email,model.fatherOrSpouseName,"+
				"model.influencingPeoplePosition.influencingPeoplePositionId,model.influencingPeoplePosition.position from InfluencingPeople model where "+
				"model.user.userId = ? and model.userAddress.constituency is not null and model.userAddress.constituency.constituencyId = ? "+
				"and model.userAddress.tehsil is not null order by model.userAddress.tehsil.tehsilId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleAddressInConstituency(Long userId,
			Long constituencyId) {
		Object[] params = {userId,constituencyId};
		return getHibernateTemplate().find("select model.userAddress from InfluencingPeople model where "+
				"model.user.userId = ? and model.userAddress.constituency is not null and model.userAddress.constituency.constituencyId = ? "+
				"and model.userAddress.tehsil is not null order by model.userAddress.tehsil.tehsilId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleDetailsInConstituencyByLocalBody(Long userId,
			Long constituencyId) {
		Object[] params = {userId,constituencyId};
		return getHibernateTemplate().find("select model.influencingPeopleId,model.firstName,model.lastName,model.middleName,model.influencingScope,"+
				"model.influencingScopeValue,model.caste,model.occupation,model.phoneNo,model.gender,model.email,model.fatherOrSpouseName,"+
				"model.influencingPeoplePosition.influencingPeoplePositionId,model.influencingPeoplePosition.position from InfluencingPeople model where "+
				"model.user.userId = ? and model.userAddress.constituency is not null and model.userAddress.constituency.constituencyId = ? "+
				"and model.userAddress.localElectionBody is not null order by model.userAddress.localElectionBody.localElectionBodyId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleAddressInConstituencyByLocalBody(Long userId,
			Long constituencyId) {
		Object[] params = {userId,constituencyId};
		return getHibernateTemplate().find("select model.userAddress from InfluencingPeople model where "+
				"model.user.userId = ? and model.userAddress.constituency is not null and model.userAddress.constituency.constituencyId = ? "+
				"and model.userAddress.localElectionBody is not null order by model.userAddress.localElectionBody.localElectionBodyId",params);
	}

	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleDetailsInDistrict(Long userId,
			Long districtId) {
		Object[] params = {userId,districtId};
		return getHibernateTemplate().find("select model.influencingPeopleId,model.firstName,model.lastName,model.middleName,model.influencingScope,"+
				"model.influencingScopeValue,model.caste,model.occupation,model.phoneNo,model.gender,model.email,model.fatherOrSpouseName,"+
				"model.influencingPeoplePosition.influencingPeoplePositionId,model.influencingPeoplePosition.position "+
				"from InfluencingPeople model where model.user.userId = ? and model.userAddress.district.districtId = ? "+
				"and model.userAddress.constituency is not null order by model.userAddress.constituency.constituencyId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleDetailsInAssemblyConstituencies(Long userId,List<Long> constituencyIds){
		
		Query queryObject = getSession().createQuery("select model.influencingPeopleId,model.firstName,model.lastName,model.middleName,model.influencingScope,"+
				"model.influencingScopeValue,model.caste,model.occupation,model.phoneNo,model.gender,model.email,model.fatherOrSpouseName,"+
				"model.influencingPeoplePosition.influencingPeoplePositionId,model.influencingPeoplePosition.position "+
				"from InfluencingPeople model where model.user.userId = ? and model.userAddress.constituency.constituencyId in (:constituencyIds) "+
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
				"from InfluencingPeople model where model.user.userId = ? and model.userAddress.district.districtId = ? "+
				"and model.userAddress.constituency is not null order by model.userAddress.constituency.constituencyId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleAddressInAssemblyConstituencies(Long userId,List<Long> constituencyIds){
		
		Query queryObject = getSession().createQuery("select model.userAddress "+
				"from InfluencingPeople model where model.user.userId = ? and model.userAddress.constituency.constituencyId in (:constituencyIds) "+
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
				"model.user.userId = ? and model.userAddress.localElectionBody is not null and model.userAddress.localElectionBody.localElectionBodyId = ? "+
				"and model.userAddress.ward is not null and model.userAddress.constituency.constituencyId = ? order by model.userAddress.ward.constituencyId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleAddressInLocalBodys(Long userId,
			Long localBodyId,Long constituencyId) {
		Object[] params = {userId,localBodyId,constituencyId};
		return getHibernateTemplate().find("select model.userAddress from InfluencingPeople model where "+
				"model.user.userId = ? and model.userAddress.localElectionBody is not null and model.userAddress.localElectionBody.localElectionBodyId = ? "+
				"and model.userAddress.ward is not null and model.userAddress.constituency.constituencyId = ? order by model.userAddress.ward.constituencyId",params);
	}

	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleDetailsInState(Long userId,
			Long stateId) {
		Object[] params = {userId,stateId};
		return getHibernateTemplate().find("select model.influencingPeopleId,model.firstName,model.lastName,model.middleName,model.influencingScope,"+
				"model.influencingScopeValue,model.caste,model.occupation,model.phoneNo,model.gender,model.email,model.fatherOrSpouseName,"+
				"model.influencingPeoplePosition.influencingPeoplePositionId,model.influencingPeoplePosition.position from InfluencingPeople model where "+
				"model.user.userId = ? and model.userAddress.state is not null and model.userAddress.state.stateId = ? "+
				"and model.userAddress.district is not null order by model.userAddress.district.districtId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleAddressInState(Long userId,
			Long stateId) {
		Object[] params = {userId,stateId};
		return getHibernateTemplate().find("select model.userAddress from InfluencingPeople model where "+
				"model.user.userId = ? and model.userAddress.state is not null and model.userAddress.state.stateId = ? "+
				"and model.userAddress.district is not null order by model.userAddress.district.districtId",params);
	}

	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleDetailsInTehsil(Long userId,
			Long tehsilId) {
		Object[] params = {userId,tehsilId};
		return getHibernateTemplate().find("select model.influencingPeopleId,model.firstName,model.lastName,model.middleName,model.influencingScope,"+
				"model.influencingScopeValue,model.caste,model.occupation,model.phoneNo,model.gender,model.email,model.fatherOrSpouseName,"+
				"model.influencingPeoplePosition.influencingPeoplePositionId,model.influencingPeoplePosition.position from InfluencingPeople model where "+
				"model.user.userId = ? and model.userAddress.tehsil is not null and model.userAddress.tehsil.tehsilId = ? "+
				"and model.userAddress.hamlet is not null order by model.userAddress.hamlet.hamletId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleAddressInTehsil(Long userId,
			Long tehsilId) {
		Object[] params = {userId,tehsilId};
		return getHibernateTemplate().find("select model.userAddress from InfluencingPeople model where "+
				"model.user.userId = ? and model.userAddress.tehsil is not null and model.userAddress.tehsil.tehsilId = ? "+
				"and model.userAddress.hamlet is not null order by model.userAddress.hamlet.hamletId",params);
	}

	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleDetailsInVillage(Long userId,
			Long hamletId) {
		Object[] params = {userId,hamletId};
		return getHibernateTemplate().find("select model.influencingPeopleId,model.firstName,model.lastName,model.middleName,model.influencingScope,"+
				"model.influencingScopeValue,model.caste,model.occupation,model.phoneNo,model.gender,model.email,model.fatherOrSpouseName,"+
				"model.influencingPeoplePosition.influencingPeoplePositionId,model.influencingPeoplePosition.position from InfluencingPeople model where "+
				"model.user.userId = ? and model.userAddress.hamlet is not null and model.userAddress.hamlet.hamletId = ? ",params);
				
	}
	
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleAddressInVillage(Long userId,
			Long hamletId) {
		Object[] params = {userId,hamletId};
		return getHibernateTemplate().find("select model.userAddress from InfluencingPeople model where "+
				"model.user.userId = ? and model.userAddress.hamlet is not null and model.userAddress.hamlet.hamletId = ? ",params);
				
	}

	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleDetailsInWard(Long userId, Long wardId) {
		Object[] params = {userId,wardId};
		return getHibernateTemplate().find("select model.influencingPeopleId,model.firstName,model.lastName,model.middleName,model.influencingScope,"+
				"model.influencingScopeValue,model.caste,model.occupation,model.phoneNo,model.gender,model.email,model.fatherOrSpouseName,"+
				"model.influencingPeoplePosition.influencingPeoplePositionId,model.influencingPeoplePosition.position from InfluencingPeople model where "+
				"model.user.userId = ? and model.userAddress.ward is not null and model.userAddress.ward.constituencyId = ? ",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleDetailsInBooth(Long userId, Long boothId) {
		Object[] params = {userId,boothId};
		return getHibernateTemplate().find("select model.influencingPeopleId,model.firstName,model.lastName,model.middleName,model.influencingScope,"+
				"model.influencingScopeValue,model.caste,model.occupation,model.phoneNo,model.gender,model.email,model.fatherOrSpouseName,"+
				"model.influencingPeoplePosition.influencingPeoplePositionId,model.influencingPeoplePosition.position from InfluencingPeople model where "+
				"model.user.userId = ? and model.userAddress.booth is not null and model.userAddress.booth.boothId = ? ",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleAddressInWard(Long userId, Long wardId) {
		Object[] params = {userId,wardId};
		return getHibernateTemplate().find("select model.userAddress from InfluencingPeople model where "+
				"model.user.userId = ? and model.userAddress.ward is not null and model.userAddress.ward.constituencyId = ? ",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleAddressInBooth(Long userId, Long boothId) {
		Object[] params = {userId,boothId};
		return getHibernateTemplate().find("select model.userAddress from InfluencingPeople model where "+
				"model.user.userId = ? and model.userAddress.booth is not null and model.userAddress.booth.boothId = ? ",params);
	}

	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleCountByInfluencingScope(Long userId) {
		return getHibernateTemplate().find("select count(model.influencingPeopleId),model.influencingScope from InfluencingPeople model where "+
				"model.user.userId = ? group by model.influencingScope order by model.influencingScope",userId);
	}

	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleCountByInfluencingScope(Long userId,
			String influencingScope) {
		Object[] params = {userId,influencingScope};
		return getHibernateTemplate().find("select count(model.influencingPeopleId),model.influencingScopeValue from InfluencingPeople model where "+
				"model.user.userId = ? and model.influencingScope = ? group by model.influencingScopeValue",params);
	}

	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleDetailsByInfluencingScope(Long userId,
			String influencingScope, String scopeValueId) {
		Object[] params = {userId,influencingScope,scopeValueId};
		return getHibernateTemplate().find("select model.influencingPeopleId,model.firstName,model.lastName,model.middleName,model.influencingScope,"+
				"model.influencingScopeValue,model.caste,model.occupation,model.phoneNo,model.gender,model.email,model.fatherOrSpouseName,"+
				"model.influencingPeoplePosition.influencingPeoplePositionId,model.influencingPeoplePosition.position from InfluencingPeople model where "+
				"model.user.userId = ? and model.influencingScope = ? and model.influencingScopeValue = ? order by model.influencingScope",params);
	}
	
	
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleDetailsByInfluencingSelScope(Long userId,
			String influencingScope, String scopeValueId,int startIndex,int maxIndex) {
		Object[] params = {userId,influencingScope,scopeValueId};
		Query query = getSession().createQuery("select model.influencingPeopleId,model.firstName,model.lastName,model.middleName,model.influencingScope,"+
				"model.influencingScopeValue,model.caste,model.occupation,model.phoneNo,model.gender,model.email,model.fatherOrSpouseName,"+
				"model.influencingPeoplePosition.influencingPeoplePositionId,model.influencingPeoplePosition.position from InfluencingPeople model where "+
				"model.user.userId = :userId and model.influencingScope = :influencingScope and model.influencingScopeValue = :scopeValueId order by model.influencingScope");
		query.setParameter("userId", userId);
		query.setParameter("influencingScope", influencingScope);
		query.setParameter("scopeValueId", scopeValueId);
		query.setFirstResult(startIndex);
		query.setMaxResults(maxIndex);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleAddressByInfluencingScope(Long userId,
			String influencingScope, String scopeValueId) {
		Object[] params = {userId,influencingScope,scopeValueId};
		return getHibernateTemplate().find("select model.userAddress from InfluencingPeople model where "+
				"model.user.userId = ? and model.influencingScope = ? and model.influencingScopeValue = ? order by model.influencingScope",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleAddressByInfluencingSelScope(Long userId,
			String influencingScope, String scopeValueId,int startIndex,int maxIndex) {
		Object[] params = {userId,influencingScope,scopeValueId};
		Query query = getSession().createQuery("select model.userAddress from InfluencingPeople model where "+
				"model.user.userId = :userId and model.influencingScope = :influencingScope and model.influencingScopeValue = :scopeValueId order by model.influencingScope");
		query.setParameter("userId", userId);
		query.setParameter("influencingScope", influencingScope);
		query.setParameter("scopeValueId", scopeValueId);
		query.setFirstResult(startIndex);
		query.setMaxResults(maxIndex);
		return query.list();
	}


	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleDetailsByInfluencingScope(Long userId,
			String influencingScope) {
		Object[] params = {userId,influencingScope};
		return getHibernateTemplate().find("select model.influencingPeopleId,model.firstName,model.lastName,model.middleName,model.influencingScope,"+
				"model.influencingScopeValue,model.caste,model.occupation,model.phoneNo,model.gender,model.email,model.fatherOrSpouseName,"+
				"model.influencingPeoplePosition.influencingPeoplePositionId,model.influencingPeoplePosition.position from InfluencingPeople model where "+
				"model.user.userId = ? and model.influencingScope = ? order by model.influencingScope",params);
	}
	
	
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleDetailsByInfluencingSelScope(Long userId,
			String influencingScope,int startIndex,int maxindex) {
		Query query = getSession().createQuery("select model.influencingPeopleId,model.firstName,model.lastName,model.middleName,model.influencingScope,"+
				"model.influencingScopeValue,model.caste,model.occupation,model.phoneNo,model.gender,model.email,model.fatherOrSpouseName,"+
				"model.influencingPeoplePosition.influencingPeoplePositionId,model.influencingPeoplePosition.position from InfluencingPeople model where "+
				"model.user.userId = :userId and model.influencingScope = :influencingScope order by model.influencingScope");
		query.setFirstResult(startIndex);
		query.setMaxResults(maxindex);
		query.setParameter("userId", userId);
		query.setParameter("influencingScope", influencingScope);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleAddressByInfluencingScope(Long userId,
			String influencingScope) {
		Object[] params = {userId,influencingScope};
		return getHibernateTemplate().find("select model.userAddress from InfluencingPeople model where "+
				"model.user.userId = ? and model.influencingScope = ? order by model.influencingScope",params);
	}

	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleAddressByInfluencingSelScope(Long userId,
			String influencingScope,int startIndex,int maxIndex) {
		Query query = getSession().createQuery("select model.userAddress from InfluencingPeople model where "+
				"model.user.userId = :userId and model.influencingScope = :influencingScope order by model.influencingScope");
		query.setParameter("userId", userId);
		query.setParameter("influencingScope", influencingScope);
		query.setFirstResult(startIndex);
		query.setMaxResults(maxIndex);
		return query.list();
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
				"model.user.userId = ? and model.userAddress.ward.constituencyId = ? and "+
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
	
	
	public List<InfluencingPeople> getInfluencePeopleBySearch(
			InfluencingPeopleVO influencingPeopleVO, String queryString) {
		
		Query query = getSession().createQuery(queryString);
		
		query.setParameter("userId", influencingPeopleVO.getUserId());
		
		return query.list();
	}

	/**
	 * This Method is used To get The Count Of Voters based on voterId
	 * @author Prasad Thiragabathina
	 * @param Long voterId
	 * @return List<Long>
	 */
	public List<Long> getinfluencingPeopleVoterId(Long voterId) {
		Query query = getSession().createQuery("select count(model.voter.voterId) from InfluencingPeople model where model.voter.voterId = ? ");
		query.setParameter(0, voterId);
		return query.list();
	}
	/**
	 * This Method is used to get the details of the influencing people weather this voter addes as a influencing people r not
	 * @author Prasad Thiragabathina
	 * @param List<Long> voterIds
	 * @param Long userId
	 * @return List<Voter>
	 */
	public List<Long> findInfluencingPeopleDetails(List<Long> voterIds,
			Long userId) {
		String queryString = "select model.voter.voterId from InfluencingPeople model where model.voter.voterId in (:voterIds) and model.user.userId = :userId ";
		Query query = getSession().createQuery(queryString);
		query.setParameter("userId", userId);
		query.setParameterList("voterIds", voterIds);
		return query.list();
	}
	
	
	public List<Long> getInfluencingPeopleCountByLocation(Long userId,List<String> locationValue,String type)
	{
			if(type.equalsIgnoreCase("panchayat"))
				/*type = "BOOTH";*/
				type =IConstants.VILLAGE;
			if(type.equalsIgnoreCase("hamlet"))
				type =IConstants.VILLAGE;
			
		Query query = getSession().createQuery("select count(model.influencingPeopleId) from InfluencingPeople model where model.user.userId=:userId and model.influencingScopeValue in(:locationValue) and model.influencingScope = :type");
		query.setParameterList("locationValue", locationValue);
		query.setParameter("userId", userId);
		query.setParameter("type", type);
		return query.list();
		
	}
	
	public List<Long> getInfluencingPeopleCountInHamlets(Long userId,List<Long> locationValue)
	{
		Query query = getSession().createQuery("select count(model.influencingPeopleId) from InfluencingPeople model where model.user.userId=:userId and model.userAddress.hamlet.hamletId in(:locationValue) ");
		query.setParameterList("locationValue", locationValue);
		query.setParameter("userId", userId);
		return query.list();
	}
	
	public List<InfluencingPeople> getInfluencingPeopleVoterIDs(Long userId,List<String> locationValue,String type,Integer startIndex,
			Integer maxRecords)
	{
	
		if(type.equalsIgnoreCase("panchayat"))
			/*type = "BOOTH";*/
			type =IConstants.VILLAGE;
		if(type.equalsIgnoreCase("hamlet"))
			type =IConstants.VILLAGE;
		Query query = getSession().createQuery("from InfluencingPeople model where model.user.userId=:userId and model.influencingScopeValue in(:locationValue) and model.influencingScope = :type");
		query.setParameterList("locationValue", locationValue);
		query.setParameter("userId", userId);
		query.setParameter("type", type);
		query.setFirstResult(startIndex);
		query.setMaxResults(maxRecords);
		return query.list();
		
	}
	
	
	
	public List getVotersMobileDetailsByConstituencyId(Long userId,String locationValue,String type)
	{
	Query query = getSession().createQuery("select model.phoneNo from InfluencingPeople model where model.user.userId=:userId and model.influencingScopeValue =:locationValue and model.influencingScope = :type");
	query.setParameter("locationValue", locationValue);
	query.setParameter("userId", userId);
	query.setParameter("type", type);
	return query.list();

	}

	public List<Long> getInfluencingPeopleCount(Long userId , List<Long> ids,String type,Long constituencyId,String partNo)
	{
		
		StringBuilder queryString = new StringBuilder();
		queryString.append("select count(model.influencingPeopleId) from InfluencingPeople model " +
				"where model.user.userId = :userId ");
		if(type.equalsIgnoreCase("constituency"))
		{
			queryString.append("and model.userAddress.constituency.constituencyId in (:ids) ");
		}
		else if(type.equalsIgnoreCase("mandal"))
		{
			queryString.append("and model.userAddress.tehsil.tehsilId in (:ids) ");
		}
		/*else if(type.equalsIgnoreCase("panchayat"))
		{
			queryString.append("and model.userAddress.hamlet.hamletId in (:ids) ");
		}*/
		else if(type.equalsIgnoreCase("booth"))
		{
			queryString.append("and model.userAddress.booth.partNo = :partNo and model.userAddress.booth.constituency.constituencyId = :constituencyId ");
		}
		else if(type.equalsIgnoreCase("hamlet"))
		{
			queryString.append("and model.userAddress.hamlet.hamletId in (:ids) ");
		}
		else if(type.equalsIgnoreCase("MUNCIPALITY/CORPORATION"))
		{
			queryString.append("and model.userAddress.ward.localElectionBody.localElectionBodyId in (:ids) ");
		}
		else if(type.equalsIgnoreCase("ward"))
		{
			queryString.append("and model.userAddress.ward.constituencyId in (:ids) ");
		}
		else if(type.equalsIgnoreCase("customWard"))
		{
			queryString.append("and model.userAddress.ward.constituencyId in (:ids) ");
		}
		Query query = getSession().createQuery(queryString.toString());
		
		query.setParameter("userId", userId);
				
		if(type.equalsIgnoreCase("booth")){
			query.setParameter("constituencyId", constituencyId);
			query.setParameter("partNo", partNo);
		}else{
			query.setParameterList("ids",ids);
		}
		return query.list();
	}
	
	public List<InfluencingPeople> getAllDetailsOfInfluencingPeople(Long userId,List<Long> ids,String type,Integer startIndex,
			Integer maxRecords,String columnName,String order)
	{
		StringBuilder queryString = new StringBuilder();
		queryString.append("select model from InfluencingPeople model " +
				"where model.user.userId = :userId ");
		if(type.equalsIgnoreCase("constituency"))
		{
			queryString.append("and model.userAddress.constituency.constituencyId in (:ids) ");
		}
		else if(type.equalsIgnoreCase("mandal"))
		{
			queryString.append("and model.userAddress.tehsil.tehsilId in (:ids) ");
		}
		else if(type.equalsIgnoreCase("panchayat"))
		{
			queryString.append("and model.userAddress.hamlet.hamletId in (:ids) ");
		}
		else if(type.equalsIgnoreCase("booth"))
		{
			queryString.append("and model.userAddress.booth.boothId in (:ids) ");
		}
		else if(type.equalsIgnoreCase("hamlet"))
		{
			queryString.append("and model.userAddress.hamlet.hamletId in (:ids) ");
		}
		else if(type.equalsIgnoreCase("MUNCIPALITY/CORPORATION"))
		{
			queryString.append("and model.userAddress.ward.localElectionBody.localElectionBodyId in (:ids) ");
		}
		else if(type.equalsIgnoreCase("ward"))
		{
			queryString.append("and model.userAddress.ward.constituencyId in (:ids) ");
		}
		else if(type.equalsIgnoreCase("customWard"))
		{
			queryString.append("and model.userAddress.ward.constituencyId in (:ids)");
		}
		if(columnName.equalsIgnoreCase("voterIDCardNo")||columnName.equalsIgnoreCase("age")||columnName.equalsIgnoreCase("gender")||columnName.equalsIgnoreCase("houseNo")||columnName.equalsIgnoreCase("relativeName"))
			queryString.append("order by model.voter."+columnName+" "+order);
		else
		queryString.append("order by model."+columnName+" "+order);
		Query query = getSession().createQuery(queryString.toString());
		
		query.setParameter("userId", userId);
		query.setParameterList("ids",ids);
		query.setFirstResult(startIndex);
		query.setMaxResults(maxRecords);
		
		return query.list();
	}
		
	
	public String getPartyIdUsingVoterId(Long voterId){
		Query query = getSession().createQuery("select model.party.shortName from InfluencingPeople model where model.voter.voterId = :voterId)");
		query.setParameter("voterId", voterId);
			return  (String) query.uniqueResult();
	}
	
	/**
	 * This DAO is used to get the influencing people based on user and type
	 * @param Long userId
	 * @param String type
	 * @return List<InfluencingPeople>
	 * @date 18-07-2013
	 */
	public List<InfluencingPeople> getInfluencingPeopleByUserAndAccessType(Long userId,String type)
	{
		StringBuffer  queryString = new StringBuffer();
		queryString.append("from InfluencingPeople model where model.user.userId = :userId " +
				"  and model.influencingScope = :type");
		
		Query query = getSession().createQuery(queryString.toString());
		query.setParameter("userId", userId);
		query.setParameter("type", type);
		return query.list();
	}
	
	/**
	 * This DAO is used to get the constituenceys List 
	 * @param String type
	 * @param Long userId
	 * @return List<String>
	 * @date 19-07-2013
	 */
	@SuppressWarnings("unchecked")
	public List<String> getSelInfluencingScopeValues(Long userId ,String type)
	{
		Object[] parms = {userId,type};
		return getHibernateTemplate().find("select distinct model.influencingScopeValue from InfluencingPeople model " +
				" where model.user.userId = ? and  model.influencingScope = ? ",parms);
	}
	
	public Long getTotalCountForInfluencingPeople(Long userId,String scope)
	{
		Query query = getSession().createQuery("select count(*) from InfluencingPeople model where " +
				" model.user.userId = :userId and  model.influencingScope = :scope ");
		query.setParameter("userId", userId);
		query.setParameter("scope", scope);
		return (Long)query.uniqueResult();
	}

	public List<Object[]> getInfluencingPeopleDetailsToSendSMS(String query,SMSSearchCriteriaVO searchVO){
		Query queryObj = getSession().createQuery(query);
	//	queryObj.setParameter("userId", searchVO.getUserId());
		queryObj.setFirstResult(searchVO.getStartIndex());
		queryObj.setMaxResults(searchVO.getMaxRecords());
		
		return queryObj.list();
	}
}
