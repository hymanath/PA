package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreDAO;
import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.Hamlet;


/**
 * 
 * @author Narender Akula
 *
 */
public class CadreDAO extends GenericDaoHibernate<Cadre, Long> implements ICadreDAO{

	public CadreDAO() {
		super(Cadre.class);
	}

	@SuppressWarnings("unchecked")
	public List<Cadre> findByProperty(String propertyName, Object value) {
		List<Cadre>  results = getHibernateTemplate().find("from Cadre where " + propertyName+"=?", value);
		return results;
	}

	@SuppressWarnings("unchecked")
	public List<Cadre> findByUserID(Long userID){
		List<Cadre>  results = getHibernateTemplate().find("from Cadre model where model.registration.registrationId=?", userID);
		return results;
	}
	

	public Long findTotalCadresByUserID(Long userID, String cadreType){
		Query query = getSession().createQuery("SELECT count(*) FROM Cadre model WHERE model.registration.registrationId = ? and model.memberType = ?" );
		query.setLong(0, userID);
		query.setString(1, cadreType);
		Long totalCadres = (Long) query.uniqueResult();
		return totalCadres;
	}
	
	public Long findTotalCadresByUserIDInALocation(Long userID, String cadreType, String model, String idToCompare, Long locationId){
		Query query = getSession().createQuery("SELECT count(*) FROM Cadre model WHERE model.registration.registrationId = ? and model.memberType = ? and model.currentAddress."+model+"."+idToCompare+" = ? " );
		query.setLong(0, userID);
		query.setString(1, cadreType);
		query.setLong(2, locationId);
		Long totalCadres = (Long) query.uniqueResult();
		return totalCadres;
	}
	
	public List findTotalCadresByUserIdBasedOnCadreLevel(Long userID, String cadreType, String model, String idToCompare, Long locationId)
	{		
		Object[] params = {userID, cadreType, locationId};
		List totalCadresByLevel = getHibernateTemplate().find("SELECT model.cadreLevel.level,count(model.cadreId) " +
				"FROM Cadre model WHERE model.registration.registrationId = ? and model.memberType = ? and model.currentAddress."+model+"."+idToCompare+" = ? "+
				"group by model.cadreLevel.level order by model.cadreLevel.cadreLevelID", params); 
		return totalCadresByLevel;
		
	}
	/*ref
	 * 
	 * @SuppressWarnings("unchecked")
	public List findCadreByPropertyValueAndCadreIds(String propertyObject,
			String propertyField, Long propertyValue, List<Long> cadreIds) {
		Query queryObject = getSession().createQuery("select model.cadreId from Cadre model where model."+propertyObject+"."+propertyField+" = ? and "+
		          "model.cadreId in (:cadreIds)");
		queryObject.setParameter(0, propertyValue);
        queryObject.setParameterList("cadreIds", cadreIds);
        return queryObject.list();
	}
	 * 
	 */
	
	@SuppressWarnings("unchecked")
	public List findCadresByLevels(Long userID, String cadreType){
		Object[] params = {userID, cadreType};
		List totalCadresByLevel = getHibernateTemplate().find("SELECT model.cadreLevel.level,count(model.cadreLevel.level) " +
				"FROM Cadre model WHERE model.registration.registrationId = ? and model.memberType = ? group by model.cadreLevel.level " +
				"order by model.cadreLevel.cadreLevelID", params); 
		return totalCadresByLevel;
	}
	@SuppressWarnings("unchecked")
	public List findStatesByCountryID(String countryIDs){
		System.out.println("CadreDAO.findStatesByCountryID:::::::::::::::::::::::::"+countryIDs);
		List totalStates = getHibernateTemplate().find("Select model.stateId, model.stateName from State model " +
												"where model.country.countryId in(" + countryIDs + ") order by model.stateName"); 
		return totalStates;
	}
	@SuppressWarnings("unchecked")
	public List findDistrictsByStateID(String stateIDs){
		List totalDistricts = getHibernateTemplate().find("Select model.districtId, model.districtName from District model " +
												"where model.state.stateId in(" + stateIDs + ") order by model.districtName"); 
		return totalDistricts;
	}
	@SuppressWarnings("unchecked")
	public List findMandalsByDistrictID(String districtIDs){
		List totalMandals = getHibernateTemplate().find("Select model.tehsilId, model.tehsilName from Tehsil model " +
				"where model.district.districtId in(" + districtIDs + ") order by model.tehsilName"); 
		return totalMandals;
	}
	
	@SuppressWarnings("unchecked")
	public List findVillagesByTehsilID(String tehsilIDs){
		List totalVillages = getHibernateTemplate().find("Select model.townshipId, model.townshipName from Township model " +
					"where model.tehsil.tehsilId in(" + tehsilIDs + ") order by model.townshipName");
		return totalVillages;
	}
	@SuppressWarnings("unchecked")
	public List findHamletsByTehsilIds(String tehsilIds) {
		
		return getHibernateTemplate().find("Select model.hamletId, model.hamletName from Hamlet model " +
				"where model.township.tehsil.tehsilId in(" + tehsilIds + ")");
	}
	
	@SuppressWarnings("unchecked")
	public List findCadreSizeStateWise(Long userID){
		List  results = getHibernateTemplate().find("Select model.currentAddress.state.stateId, count(model.currentAddress.state.stateId)from Cadre model " +
				"where model.registration.registrationId = ? group by model.currentAddress.state.stateId", userID); 
		return results;
	}
	
	/*@SuppressWarnings("unchecked")
	public List findCadreSizeDistrictWise(Long userID){
		List  results = getHibernateTemplate().find("Select model.district.districtId, count(model.district)from Cadre model " +
				"where model.registration.registrationId = ? group by model.district", userID); 
		return results;
	}*/
	
	@SuppressWarnings("unchecked")
	public List findCadreSizeDistrictWise(Long userID){
		List  results = getHibernateTemplate().find("Select model.currentAddress.district.districtId, count(model.currentAddress.district.districtId)from Cadre model " +
				"where model.registration.registrationId = ? and model.currentAddress.district.districtId is not null group by model.currentAddress.district.districtId", userID); 
		return results;
	}

	/*@SuppressWarnings("unchecked")
	public List findCadreSizeMandalWise(Long userID){
		List  results = getHibernateTemplate().find("Select model.tehsil.tehsilId, count(model.tehsil.tehsilId)from Cadre model " +
				"where model.registration.registrationId = ? group by model.tehsil.tehsilId", userID); 
		return results;
	}*/
	
	@SuppressWarnings("unchecked")
	public List findCadreSizeMandalWise(Long userID){
		List  results = getHibernateTemplate().find("Select model.currentAddress.tehsil.tehsilId, count(model.currentAddress.tehsil.tehsilId)from Cadre model " +
				"where model.registration.registrationId = ? and model.currentAddress.tehsil.tehsilId is not null group by model.currentAddress.tehsil.tehsilId", userID); 
		return results;
	}
	
	public List findCadreSizeLocalElectionBodywise(Long userId) {
		List  results = getHibernateTemplate().find("Select model.currentAddress.localElectionBody.localElectionBodyId, count(model.currentAddress.localElectionBody.localElectionBodyId)from Cadre model " +
				"where model.registration.registrationId = ? and model.currentAddress.localElectionBody.localElectionBodyId is not null group by model.currentAddress.localElectionBody.localElectionBodyId", userId); 
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List findCadreSizeHamletWise(Long userID){
		List  results = getHibernateTemplate().find("Select model.currentAddress.hamlet.hamletId, count(model.currentAddress.hamlet.hamletId)from Cadre model " +
					"where model.registration.registrationId = ? and model.currentAddress.hamlet.hamletId is not null group by model.currentAddress.hamlet.hamletId", userID); 
		return results;
		
	}
	
	public List findCadreSizeConstituencywise(Long userId) {
		List  results = getHibernateTemplate().find("Select model.currentAddress.constituency.constituencyId, count(model.currentAddress.constituency.constituencyId)from Cadre model " +
				"where model.registration.registrationId = ? and model.currentAddress.constituency.constituencyId is not null group by model.currentAddress.constituency.constituencyId", userId); 
		return results;
	}
	
	/*@SuppressWarnings("unchecked")
	public List findCadreSizeVillageWise1(String villageIDs, Long userID){
		try{
			List  results = getHibernateTemplate().find("Select model.village.townshipId, count(model.village.townshipId)from Cadre model " +
					"where model.registration.registrationId = ? and model.village.townshipId in("+ villageIDs + ") group by model.village.townshipId", userID); 
			return results;
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			return new ArrayList();
		}
		
	}*/
	@SuppressWarnings("unchecked")
	public List findCadreSizeVillageWise(Long userID){
		List  results = getHibernateTemplate().find("Select model.village.townshipId, count(model.village.townshipId)from Cadre model " +
					"where model.registration.registrationId = ? group by model.village.townshipId", userID); 
		return results;
		
	}
	
	public List findCadreSizeWardswise(Long userId) {
		List  results = getHibernateTemplate().find("Select model.currentAddress.ward.constituencyId, count(model.currentAddress.ward.constituencyId)from Cadre model " +
				"where model.registration.registrationId = ? and model.currentAddress.ward.constituencyId is not null group by model.currentAddress.ward.constituencyId", userId); 
		return results;
	}
	
	public List findCadreSizeBoothwise(Long userId) {
		List  results = getHibernateTemplate().find("Select model.currentAddress.booth.boothId, count(model.currentAddress.booth.boothId)from Cadre model " +
				"where model.registration.registrationId = ? and model.currentAddress.booth.boothId is not null and model.currentAddress.localElectionBody.localElectionBodyId is not null group by model.currentAddress.booth.boothId", userId); 
		return results;
	}

	/*public List findStateCadresByCountry(Long countryID, Long userID, String cadreType){
		Object[] params = {userID,cadreType};
		List  results = getHibernateTemplate().find("Select model.state.stateId, model.state.stateName, count(model.state.stateId)from Cadre model " +
				"where model.registration.registrationId = ? group by model.state.stateId order by model.state.stateName", params); 
		return results;
	}*/
	/*@SuppressWarnings("unchecked")
	public List findDistCadresByState(Long stateID, Long userID, String cadreType){
		Object[] params = {userID,stateID, cadreType};
		List  results = getHibernateTemplate().find("Select model.district.districtId, model.district.districtName, count(model.district.districtId)from Cadre model " +
				"where model.registration.registrationId = ? and model.state.stateId=? and model.memberType = ? group by model.district.districtId order by model.district.districtName", params); 
		return results;
	}*/
	
	@SuppressWarnings("unchecked")
	public List findDistCadresByState(Long stateID, Long userID, String cadreType){
		Object[] params = {userID,stateID, cadreType};
		List  results = getHibernateTemplate().find("Select model.currentAddress.district.districtId, model.currentAddress.district.districtName, count(model.currentAddress.district.districtId)from Cadre model " +
				"where model.registration.registrationId = ? and model.currentAddress.state.stateId=? and model.memberType = ? group by model.currentAddress.district.districtId order by model.currentAddress.district.districtName", params); 
		return results;
	}
	
	/*public List findConstituencyCadresByDist(Long districtID, Long userID, String cadreType){
		Object[] params = {userID,districtID, cadreType};
		List  results = getHibernateTemplate().find("Select model.constituency.constituencyId, model.constituency.name, count(model.constituency.constituencyId)from Cadre model " +
				"where model.registration.registrationId = ? and model.district.districtId=? and model.memberType = ? group by model.constituency.constituencyId order by model.constituency.name", params); 
		return results;
	}*/
	public List findMandalCadresByConstituency(Long constituencyID, Long userID, String cadreType){
		Object[] params = {userID,constituencyID, cadreType};
		List  results = getHibernateTemplate().find("Select model.currentAddress.tehsil.tehsilId, model.currentAddress.tehsil.tehsilName, count(model.currentAddress.tehsil.tehsilId)from Cadre model " +
				"where model.registration.registrationId = ? and model.currentAddress.constituency.constituencyId=? and model.memberType = ? group by model.currentAddress.tehsil.tehsilId order by model.currentAddress.tehsil.tehsilName", params); 
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List findMandalCadresByDist(Long districtID, Long userID, String cadreType){
		Object[] params = {userID,districtID, cadreType};
		List  results = getHibernateTemplate().find("Select model.tehsil.tehsilId, model.tehsil.tehsilName, count(model.tehsil.tehsilId)from Cadre model " +
				"where model.registration.registrationId = ? and model.district.districtId=? and model.memberType = ? group by model.tehsil.tehsilId order by model.tehsil.tehsilName", params); 
		return results;
	}
	@SuppressWarnings("unchecked")
	public List findVillageCadresByMandal(Long mandalID, Long userID, String cadreType){
		Object[] params = {userID,mandalID, cadreType};
		List  results = getHibernateTemplate().find("Select model.currentAddress.hamlet.hamletId, model.currentAddress.hamlet.hamletName, count(model.currentAddress.hamlet.hamletId) from Cadre model " +
				"where model.registration.registrationId = ? and model.currentAddress.tehsil.tehsilId=? and model.memberType = ? group by model.currentAddress.hamlet.hamletId order by model.currentAddress.hamlet.hamletName", params); 
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cadre> findCadresByVillage(Long villageID, Long userID, String cadreType){
		Object[] params = {userID,villageID,cadreType};
		List<Cadre>  results = getHibernateTemplate().find("from Cadre model " +
				"where model.registration.registrationId = ? and model.currentAddress.hamlet.hamletId=? and model.memberType = ?", params); 
		return results;
	}
	/*
	public List findMandalCadresByMandals(String mandalIDs, Long userID, String cadreType){
		Object[] params = {cadreType};
		List result = getHibernateTemplate().find("Select model.tehsil.tehsilId, model.tehsil.tehsilName, count(model.tehsil.tehsilId) from Cadre model " +
				"where model.registration.registrationId = "+userID+" and model.tehsil.tehsilId in(" + mandalIDs + ") and model.memberType = ?" +
						" group by model.tehsil.tehsilId order by model.tehsil.tehsilName",params);
		return result;
	}*/
	

	@SuppressWarnings("unchecked")
	public List<Cadre> findCadresByCadreLevel(String cadreLevel, Long userID, String cadreType){
		Object[] params = {userID,cadreLevel, cadreType};
		List<Cadre>  results = getHibernateTemplate().find("from Cadre model " +
				"where model.registration.registrationId = ? and model.cadreLevel.level=? and model.memberType = ?", params); 
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cadre> findCadresByCadreLevelByUserIDInALocation(String cadreLevel, Long userID, String cadreType, String model, String idToCompare, Long locationId){
		Object[] params = {userID,cadreLevel, cadreType, locationId};
		List<Cadre>  results = getHibernateTemplate().find("from Cadre model WHERE model.registration.registrationId = ? and model.cadreLevel.level=? and model.memberType = ? and model.currentAddress."+model+"."+idToCompare+" = ? ", params);		
		return results;
	}

	public List getUserAccessStates(Long userID) {
		List results = getHibernateTemplate().find("select distinct model.state.stateId, model.state.stateName from Cadre model " +
				"where model.registration.registrationId = ? order by model.state.stateName", userID);
		return results;
	}

	public List getUserAccessDistricts(Long userID) {
		List results = getHibernateTemplate().find("select distinct model.district.districtId, model.district.districtName from Cadre model " +
				"where model.registration.registrationId = ? order by model.district.districtName", userID);
		return results;
	}

	public List getUserAccessMLAConstituencies(Long userID) {
		List results = getHibernateTemplate().find("select distinct model.constituency.constituencyId, model.constituency.name from Cadre model " +
				"where model.registration.registrationId = ? and model.constituency.electionScope.electionType.electionType='Assembly' order by model.constituency.name", userID);
		return results;
	}

	public List getUserAccessMandals(Long userID) {
		List results = getHibernateTemplate().find("select distinct model.tehsil.tehsilId, model.tehsil.tehsilName from Cadre model " +
				"where model.registration.registrationId = ? order by model.tehsil.tehsilName", userID);
		return results;
	}

	@SuppressWarnings("unchecked")
	public List getMobileNosByState(Long userID, Long stateID) {
		Long[] ids = {userID, stateID};
		List results = getHibernateTemplate().find("select  model.mobile, model.firstName, model.lastName from Cadre model " +
				"where model.registration.registrationId = ? and model.state.stateId=?", ids);
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List getMobileNosByDistrict(Long userID, Long districtID) {
		Long[] ids = {userID, districtID};
		List results = getHibernateTemplate().find("select  model.mobile, model.firstName, model.lastName from Cadre model " +
				"where model.registration.registrationId = ? and model.district.districtId=?", ids);
		return results;
	}

	@SuppressWarnings("unchecked")
	public List getMobileNosByConstituency(Long userID, Long constituencyId) {
		Long[] ids = {userID, constituencyId};
		List results = getHibernateTemplate().find("select  model.mobile, model.firstName, model.lastName from Cadre model " +
				"where model.registration.registrationId = ? and model.constituency.constituencyId=?", ids);
		return results;
	}

	@SuppressWarnings("unchecked")
	public List getMobileNosByMandal(Long userID, Long mandalID) {
		Long[] ids = {userID, mandalID};
		List results = getHibernateTemplate().find("select  model.mobile, model.firstName, model.lastName from Cadre model " +
				"where model.registration.registrationId = ? and model.tehsil.tehsilId=?", ids);
		return results;
	}

	@SuppressWarnings("unchecked")
	public List getMobileNosByVillage(Long userID, Long villageID) {
		Object[] ids = {userID, villageID};
		List results = getHibernateTemplate().find("select  model.mobile, model.firstName, model.lastName from Cadre model " +
				"where model.registration.registrationId = ? and model.village.townshipId=?", ids);
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List getMobileNosByHamlet(Long userID, Long hamletID) {
		Long[] ids = {userID, hamletID};
		List results = getHibernateTemplate().find("select  model.mobile, model.firstName, model.lastName from Cadre model " +
				"where model.registration.registrationId = ? and model.hamlet.hamletId=?", ids);
		return results;
	}


	@SuppressWarnings("unchecked")
	public List getMobileNosByCadreLevel(Long userID, Long level) {
		Long[] ids = {userID, level};
		List results = getHibernateTemplate().find("select  model.mobile, model.firstName, model.lastName from Cadre model " +
				"where model.registration.registrationId = ? and model.cadreLevel.cadreLevelID=?", ids);
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cadre> findByCadreIDs(String cadreIDs){
		List<Cadre>  results = getHibernateTemplate().find("from Cadre model where model.cadreId in ("+ cadreIDs+")");
		return results;
	}

	@SuppressWarnings("unchecked")
	public List<Cadre> findCadresByDistrict(Long districtID, Long userID){
		Object[] params = {userID,districtID};
		List<Cadre>  results = getHibernateTemplate().find("from Cadre model " +
				"where model.registration.registrationId = ? and model.currentAddress.district.districtId=?", params); 
		return results;
	}
	@SuppressWarnings("unchecked")
	public List<Cadre> findCadresByConstituency(Long constituencyID, Long userID){
		Object[] params = {userID,constituencyID};
		List<Cadre>  results = getHibernateTemplate().find("from Cadre model " +
				"where model.registration.registrationId = ? and model.currentAddress.constituency.constituencyId=?", params); 
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cadre> findCadresByMandal(Long mandalID, Long userID){
		Object[] params = {userID,mandalID};
		List<Cadre>  results = getHibernateTemplate().find("from Cadre model " +
				"where model.registration.registrationId = ? and model.currentAddress.tehsil.tehsilId=?", params); 
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cadre> findCadresByState(Long stateID, Long userID){
		Object[] params = {userID,stateID};
		List<Cadre>  results = getHibernateTemplate().find("from Cadre model " +
				"where model.registration.registrationId = ? and model.currentAddress.state.stateId = ?", params); 
		return results;
	}

	@SuppressWarnings("unchecked")
	public List getCadreSizeByHamlet(String revenueVillageIDs, Long userID, String cadreType){
		Object[] params = {userID,cadreType};
		List  results = getHibernateTemplate().find("Select model.currentAddress.hamlet.hamletId, model.currentAddress.hamlet.hamletName, " +
				"count(model.currentAddress.hamlet.hamletId) from Cadre model " +
				"where model.registration.registrationId = ?  and model.currentAddress.township.townshipId in (" + revenueVillageIDs +") and model.memberType = ?"+
				"group by model.currentAddress.hamlet.hamletId order by model.currentAddress.hamlet.hamletName", params); 
		return results;
	}
	/*@SuppressWarnings("unchecked")
	public List<Cadre> findCadresByVillage(Long villageID, Long userID){
		Object[] params = {userID,villageID};
		List<Cadre>  results = getHibernateTemplate().find("from Cadre model " +
				"where model.registration.registrationId = ? and model.village.townshipId=?", params); 
		return results;
	}*/
	/*@SuppressWarnings("unchecked")
	public List<Cadre> findCadresByHamlet(Long hamletID, Long userID, String cadreType){
		Object[] params = {userID,hamletID,cadreType};
		List<Cadre>  results = getHibernateTemplate().find("from Cadre model " +
				"where model.registration.registrationId = ? and model.hamlet.hamletId=? and model.memberType = ?", params); 
		return results;
	}*/
	
	@SuppressWarnings("unchecked")
	public List findHamletsByRVs(String townships){
		List totalHamlets = getHibernateTemplate().find("Select model.hamletId, model.hamletName from Hamlet model " +
					"where model.township.townshipId  in(" + townships + ") order by model.hamletName");
		return totalHamlets;
	}
	
	/*@SuppressWarnings("unchecked")
	public List findCadreSizeHamletWise(Long userID){
		List  results = getHibernateTemplate().find("Select model.hamlet.hamletId, count(model.hamlet.hamletId)from Cadre model " +
					"where model.registration.registrationId = ? group by model.hamlet.hamletId", userID); 
		return results;
		
	}*/	
	
	@SuppressWarnings("unchecked")
	public List findCadreDetailsByLevelAndProperty(Long userId,
			String propertyColumnOne, String propertyColumnTwo,
			String propertyColumnId, Long propertyColumnValue, String query) {
		Object[] params = {userId,propertyColumnValue};
		return getHibernateTemplate().find("select model.cadreId from Cadre model where model.registration.registrationId = ?"+
				" and model."+propertyColumnOne+"."+propertyColumnTwo+"."+propertyColumnId+" = ?" + query ,params);
	}

	@SuppressWarnings("unchecked")
	public List findCadreIdsByUserAndMemberType(Long userId,String memberType) {
		
		Object[] params = {userId,memberType};
		return getHibernateTemplate().find("select model.cadreId from Cadre model where model.registration.registrationId = ?"+
				" and model.memberType = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List findCadreIdsByMemberTypeAndCadreList(String memberType,
			List<Long> cadreIds, String query) {
		Query queryObject = getSession().createQuery("select model.cadreId from Cadre model where model.memberType = ? and "+
				"model.cadreId in (:cadreIds) "+query);
		queryObject.setParameter(0, memberType);
		queryObject.setParameterList("cadreIds", cadreIds);
		return queryObject.list();
	}

	@SuppressWarnings("unchecked")
	public List findCadreByPropertyValueAndCadreIds(String propertyObject,
			String propertyField, Long propertyValue, List<Long> cadreIds) {
		Query queryObject = getSession().createQuery("select model.cadreId from Cadre model where model."+propertyObject+"."+propertyField+" = ? and "+
		          "model.cadreId in (:cadreIds)");
		queryObject.setParameter(0, propertyValue);
        queryObject.setParameterList("cadreIds", cadreIds);
        return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List findCadreByPropertyValueListAndCadreIds(String propertyObject,
			String propertyField, List<Long> propertyValue, List<Long> cadreIds, String query) {
		Query queryObject = getSession().createQuery("select model.cadreId from Cadre model where model."+propertyObject+"."+propertyField+" in (:propertyValue) and "+
                  "model.cadreId in (:cadreIds)"+query);
		queryObject.setParameterList("propertyValue", propertyValue);
		queryObject.setParameterList("cadreIds", cadreIds);
		return queryObject.list();
	}

	@SuppressWarnings("unchecked")
	public List findCadreByPropertyValueAndUser(Long userId,
			String propertyObject, String propertyField, Long propertyValue) {
		Object[] params = {userId,propertyValue};
		return getHibernateTemplate().find("select model.cadreId from Cadre model where model.registration.registrationId = ?"+
				" and model."+propertyObject+"."+propertyField+" = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List findCadreByPropertyValueListAndUser(Long userId,
			String propertyObject, String propertyField,
			List<Long> propertyValue, String query) {
		Query queryObject = getSession().createQuery("select model.cadreId from Cadre model where model.registration.registrationId = ?"+
				" and model."+propertyObject+"."+propertyField+" in (:propertyValue)"+query);
		queryObject.setParameter(0, userId);
        queryObject.setParameterList("propertyValue", propertyValue);
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List findCadreByPartyWorkingCommitteeAndCadreIds(
			Long partyCommiteeId, List<Long> cadreIds) {
		Query queryObject = getSession().createQuery("select model.cadreId from Cadre model where model.designation.partyWorkingCommittee.partyWorkingCommitteeId = ? and "+
		        "model.cadreId in (:cadreIds)");
		queryObject.setParameter(0, partyCommiteeId);
		queryObject.setParameterList("cadreIds", cadreIds);
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List findCadreByPartyWorkingCommitteeListAndCadreIds(
			List<Long> partyCommiteeId, List<Long> cadreIds) {
		Query queryObject = getSession().createQuery("select model.cadreId from Cadre model where model.designation.partyWorkingCommittee.partyWorkingCommitteeId in (:partyCommiteeId) and "+
		        "model.cadreId in (:cadreIds)");
		queryObject.setParameterList("partyCommiteeId", partyCommiteeId);
		queryObject.setParameterList("cadreIds", cadreIds);
		return queryObject.list();
	}


	@SuppressWarnings("unchecked")
	public List findCadreByPartyWorkingCommitteeAndUser(Long userId,
			Long partyCommiteeId) {
		Object[] params = {userId,partyCommiteeId};
		return getHibernateTemplate().find("select model.cadreId from Cadre model where model.registration.registrationId = ?"+
				" and model.designation.partyWorkingCommittee.partyWorkingCommitteeId = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List findCadreByPartyWorkingCommitteeListAndUser(Long userId,
			List<Long> partyCommiteeId) {
		Query queryObject = getSession().createQuery("select model.cadreId from Cadre model where model.registration.registrationId = ?"+
				" and model.designation.partyWorkingCommittee.partyWorkingCommitteeId in (:partyCommiteeId)");
		queryObject.setParameter(0, userId);
		queryObject.setParameterList("partyCommiteeId", partyCommiteeId);
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List findCadreByPartyWorkingCommitteeDesignationAndCadreIds(
			Long partyCommiteeDesigId, List<Long> cadreIds) {
		Query queryObject = getSession().createQuery("select model.cadreId from Cadre model where model.designation.partyWkgCommitteeDesignationId = ? and "+
              "model.cadreId in (:cadreIds)");
		queryObject.setParameter(0, partyCommiteeDesigId);
		queryObject.setParameterList("cadreIds", cadreIds);
		return queryObject.list();
	}

	@SuppressWarnings("unchecked")
	public List findCadreByPartyWorkingCommitteeDesignationAndUser(Long userId,
			Long partyCommiteeDesigId) {
		Object[] params = {userId,partyCommiteeDesigId};
		return getHibernateTemplate().find("select model.cadreId from Cadre model where model.registration.registrationId = ?"+
				" and model.designation.partyWkgCommitteeDesignationId = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List findCadreByUserAndCadreLevel(Long userId, Long levelId, String query) {
		Object[] params = {userId,levelId};
		return getHibernateTemplate().find("select model.cadreId from Cadre model where model.registration.registrationId = ?"+
				" and model.cadreLevel.cadreLevelID = ? "+query,params);
	}

	@SuppressWarnings("unchecked")
	public List findCadreByPartyWorkingCommitteeDesignationListAndCadreIds(
			List<Long> partyCommiteeDesigId, List<Long> cadreIds) {
		Query queryObject = getSession().createQuery("select model.cadreId from Cadre model where model.designation.partyWkgCommitteeDesignationId in (:partyCommiteeDesigId) and "+
              "model.cadreId in (:cadreIds)");
		queryObject.setParameterList("partyCommiteeDesigId", partyCommiteeDesigId);
		queryObject.setParameterList("cadreIds", cadreIds);
		return queryObject.list();
	}

	@SuppressWarnings("unchecked")
	public List findCadreByPartyWorkingCommitteeDesignationListAndUser(
			Long userId, List<Long> partyCommiteeDesigId) {
		Query queryObject = getSession().createQuery("select model.cadreId from Cadre model where model.registration.registrationId = ?"+
				" and model.designation.partyWkgCommitteeDesignationId in (:partyCommiteeDesigId)");
		queryObject.setParameter(0, userId);
		queryObject.setParameterList("partyCommiteeDesigId", partyCommiteeDesigId);
		return queryObject.list();
	}

	public Integer deleteByCadreId(Long cadreId) {
		Query queryObject = getSession().createQuery("delete from Cadre model where model.cadreId = ?");
		queryObject.setParameter(0, cadreId);
		return queryObject.executeUpdate();
		
	}
	
	public List findConstituencyCadresByDist(Long districtID, Long userID, String cadreType){
		Object[] params = {userID,districtID, cadreType};
		List  results = getHibernateTemplate().find("Select model.currentAddress.constituency.constituencyId, model.currentAddress.constituency.name, count(model.currentAddress.constituency.constituencyId)from Cadre model " +
				"where model.registration.registrationId = ? and model.currentAddress.district.districtId=? and model.memberType = ? group by model.currentAddress.constituency.constituencyId order by model.currentAddress.constituency.name", params); 
		return results;
	}
	
	public List findConstituencyCadresByParliamentConst(Long districtID, Long userID, String cadreType){
		Object[] params = {userID,districtID, cadreType};
		List  results = getHibernateTemplate().find("Select model.currentAddress.constituency.constituencyId, model.currentAddress.constituency.name, count(model.currentAddress.constituency.constituencyId)from Cadre model " +
				"where model.registration.registrationId = ? and model.currentAddress.parliamentConstituency.constituencyId=? and model.memberType = ? group by model.currentAddress.constituency.constituencyId order by model.currentAddress.constituency.name", params); 
		return results;
	}
	
	public List findLocalElectionBodiesCadresByConst(Long constituencyId,
			Long userID, String cadreType) {
		Object[] params = {constituencyId,userID, cadreType};
		List  results = getHibernateTemplate().find("Select model.currentAddress.localElectionBody.localElectionBodyId, model.currentAddress.localElectionBody.name, count(model.currentAddress.localElectionBody.localElectionBodyId), model.currentAddress.localElectionBody.electionType.electionType from Cadre model " +
				"where model.currentAddress.constituency.constituencyId=? and model.registration.registrationId = ? and model.memberType = ? group by model.currentAddress.localElectionBody.localElectionBodyId order by model.currentAddress.localElectionBody.name", params); 
		return results;
	}
	

	public List findMandalCadresByMandals(String mandalIDs, Long userID, String cadreType){
		Object[] params = {cadreType};
		List result = getHibernateTemplate().find("Select model.currentAddress.tehsil.tehsilId, model.currentAddress.tehsil.tehsilName, count(model.currentAddress.tehsil.tehsilId) from Cadre model " +
				"where model.registration.registrationId = "+userID+" and model.currentAddress.tehsil.tehsilId in(" + mandalIDs + ") and model.memberType = ?" +
						" group by model.currentAddress.tehsil.tehsilId order by model.currentAddress.tehsil.tehsilName",params);
		return result;
	}

	@SuppressWarnings("unchecked")
	public List findHamletCadresByMandal(Long mandalID, Long userID,
			String cadreType) {
		Object[] params = {mandalID,userID, cadreType};
		List  results = getHibernateTemplate().find("Select model.currentAddress.hamlet.hamletId, model.currentAddress.hamlet.hamletName, count(model.currentAddress.hamlet.hamletId) from Cadre model " +
				"where model.currentAddress.tehsil.tehsilId=? and model.registration.registrationId = ? and model.memberType = ? group by model.currentAddress.hamlet.hamletId order by model.currentAddress.hamlet.hamletName", params); 
		return results;
		
	}
	
	@SuppressWarnings("unchecked")
	public List findTownshipCadresByMandal(Long mandalID, Long userID,
			String cadreType) {
		Object[] params = {mandalID,userID, cadreType};
		List  results = getHibernateTemplate().find("Select model.currentAddress.township.townshipId, model.currentAddress.township.townshipName, count(model.currentAddress.township.townshipId) from Cadre model " +
				"where model.currentAddress.tehsil.tehsilId=? and model.registration.registrationId = ? and model.memberType = ? group by model.currentAddress.township.townshipId order by model.currentAddress.township.townshipName", params); 
		return results;
		
	}

	/*public List findCadresByWard(Long localElectionBodyId, Long userID,
			String cadreType) {
		Object[] params = {localElectionBodyId,userID, cadreType};
		List  results = getHibernateTemplate().find("Select model.currentAddress.ward.constituencyId, model.currentAddress.ward.name, count(model.currentAddress.ward.constituencyId) from Cadre model " +
				"where model.currentAddress.localElectionBody.localElectionBodyId=? and model.registration.registrationId = ? and model.memberType = ? group by model.currentAddress.ward.constituencyId order by model.currentAddress.ward.name", params); 
		return results;
	}*/
	
	public List findCadresByWard(Long localElectionBodyId, Long userID,
			String cadreType) {
		Object[] params = {localElectionBodyId,userID, cadreType};
		List  results = getHibernateTemplate().find("Select model.currentAddress.ward, count(model.currentAddress.ward.constituencyId) from Cadre model " +
				"where model.currentAddress.localElectionBody.localElectionBodyId=? and model.registration.registrationId = ? and model.memberType = ? group by model.currentAddress.ward.constituencyId order by model.currentAddress.ward.name", params); 
		return results;
	}
	
	public List findCadresByBoothInWard(Long wardId, Long userId,
			String cadreType) {
		Object[] params = {wardId,userId, cadreType};
		List  results = getHibernateTemplate().find("Select model.currentAddress.booth.boothId, model.currentAddress.booth.partNo, model.currentAddress.booth.location,  count(model.currentAddress.booth.boothId) from Cadre model " +
				"where model.currentAddress.ward.constituencyId=? and model.registration.registrationId = ? and model.memberType = ? group by model.currentAddress.booth.boothId", params); 
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cadre> findCadresByHamlet(Long hamletID, Long userID, String cadreType){
		Object[] params = {userID,hamletID,cadreType};
		List<Cadre>  results = getHibernateTemplate().find("from Cadre model " +
				"where model.registration.registrationId = ? and model.currentAddress.hamlet.hamletId=? and model.memberType = ?", params); 
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cadre> findCadresByTownship(Long townshipId, Long userID, String cadreType){
		Object[] params = {userID,townshipId,cadreType};
		List<Cadre>  results = getHibernateTemplate().find("from Cadre model " +
				"where model.registration.registrationId = ? and model.currentAddress.township.townshipId = ? and model.memberType = ?", params); 
		return results;
	}

	@SuppressWarnings("unchecked")
	public List<Cadre> getCadresByWard(Long wardId, Long userID, String cadreType) {
		Object[] params = {userID,wardId,cadreType};
		List<Cadre>  results = getHibernateTemplate().find("from Cadre model " +
				"where model.registration.registrationId = ? and model.currentAddress.ward.constituencyId=? and model.memberType = ?", params); 
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List findStateCadresByCountry(Long countryID, Long userID, String cadreType){
		Object[] params = {userID,cadreType};
		List  results = getHibernateTemplate().find("Select model.currentAddress.state.stateId, model.currentAddress.state.stateName, count(model.currentAddress.state.stateId)from Cadre model " +
				"where model.registration.registrationId = ? and model.memberType = ? group by model.currentAddress.state.stateId order by model.currentAddress.state.stateName", params); 
		return results;
	}

	@SuppressWarnings("unchecked")
	public List findCadreSizeBoothwiseInMandal(Long userId) {
		List  results = getHibernateTemplate().find("Select model.currentAddress.booth.boothId, count(model.currentAddress.booth.boothId)from Cadre model " +
				"where model.registration.registrationId = ? and model.currentAddress.booth.boothId is not null and model.currentAddress.tehsil.tehsilId is not null group by model.currentAddress.booth.boothId", userId); 
		return results;
	}

	@SuppressWarnings("unchecked")
	public List findBoothCadresByLocalElectionBody(Long localElectionBodyId,
			Long userID, String cadreType) {
		Object[] params = {localElectionBodyId,userID, cadreType};
		List  results = getHibernateTemplate().find("Select model.currentAddress.booth.boothId, model.currentAddress.booth.partNo, model.currentAddress.booth.location, count(model.currentAddress.booth.boothId) from Cadre model " +
				"where model.currentAddress.localElectionBody.localElectionBodyId=? and model.registration.registrationId = ? and model.memberType = ? and model.currentAddress.localElectionBody.localElectionBodyId is not null group by model.currentAddress.booth.boothId", params); 
		return results;
			}

	public List findBoothCadresByMandal(Long mandalID, Long userID,
			String cadreType) {
		Object[] params = {mandalID,userID, cadreType};
		List  results = getHibernateTemplate().find("Select model.currentAddress.booth.boothId, model.currentAddress.booth.partNo, model.currentAddress.booth.location, count(model.currentAddress.booth.boothId) from Cadre model " +
				"where model.currentAddress.tehsil.tehsilId=? and model.registration.registrationId = ? and model.memberType = ? and model.currentAddress.tehsil.tehsilId is not null group by model.currentAddress.booth.boothId", params); 
		return results;

	}

	@SuppressWarnings("unchecked")
	public List<Cadre> findCadresByBooth(Long boothId, Long userID,
			String cadreType) {
		Object[] params = {boothId,userID,cadreType};
		List<Cadre>  results = getHibernateTemplate().find("from Cadre model " +
				"where model.currentAddress.booth.boothId=? and model.registration.registrationId = ? and model.memberType = ? and model.currentAddress.booth.boothId is not null", params); 
		return results;
	}

	@SuppressWarnings("unchecked")
	public List<Cadre> findCadreDetailsNotAssignedToBooth(Long tehsilId,Long userId, String cadreType) {
		Object[] params = {tehsilId,userId,cadreType};
		List<Cadre>  results = getHibernateTemplate().find("from Cadre model " +
				"where model.currentAddress.tehsil.tehsilId = ? and model.registration.registrationId = ? and model.memberType = ? " +
				"and model.currentAddress.booth.boothId is null and model.currentAddress.tehsil.tehsilId is not null", params); 
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cadre> findCadreDetailsNotAssignedToBoothInLocalBody(
			Long localBodyId, Long userId, String cadreType) {
		Object[] params = {localBodyId,userId,cadreType};
		List<Cadre>  results = getHibernateTemplate().find("from Cadre model " +
				"where model.currentAddress.localElectionBody.localElectionBodyId = ? and model.registration.registrationId = ? and model.memberType = ? " +
				"and model.currentAddress.booth.boothId is null and model.currentAddress.localElectionBody.localElectionBodyId is not null", params); 
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cadre> findCadreDetailsNotAssignedToBoothInWard(Long wardId, Long userId, String cadreType) {
		Object[] params = {wardId,userId,cadreType};
		List<Cadre>  results = getHibernateTemplate().find("from Cadre model " +
				"where model.currentAddress.ward.constituencyId = ? and model.registration.registrationId = ? and model.memberType = ? " +
				"and model.currentAddress.booth.boothId is null and model.currentAddress.ward.constituencyId is not null", params); 
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cadre> findCadreDetailsAssignedToBoothInWard(Long wardId, Long userId, String cadreType) {
		Object[] params = {wardId,userId,cadreType};
		List<Cadre>  results = getHibernateTemplate().find("from Cadre model " +
				"where model.currentAddress.ward.constituencyId = ? and model.registration.registrationId = ? and model.memberType = ? " +
				"and model.currentAddress.booth is not null and model.currentAddress.ward.constituencyId is not null", params); 
		return results;
	}
	
	public List findCadresNotAssignedToBooth(Long userId, String cadreType) {
		Object[] params = {userId,cadreType};
		return getHibernateTemplate().find("select count(model.cadreId) from Cadre model where model.currentAddress.booth.boothId is null" +
				" and model.registration.registrationId = ? and model.memberType = ?", params);
	}

	public List findVillageLevelCadresContByMandal(Long mandalId, Long userId,
			String cadreType) {
		Object[] params = {mandalId,userId,cadreType};
		return getHibernateTemplate().find("select count(model.cadreId) from Cadre model where model.currentAddress.tehsil.tehsilId is not null" +
				" and model.tehsil.tehsilId = ? and model.registration.registrationId = ? and model.memberType = ?", params);
	}

	public List findLocalBodyLevelCadresContByMandal(Long localBodyId,
			Long userId, String cadreType) {
		Object[] params = {localBodyId,userId,cadreType};
		return getHibernateTemplate().find("select count(model.cadreId) from Cadre model where model.currentAddress.localElectionBody.localElectionBodyId is not null" +
				" and model.localElectionBody.localElectionBodyId = ? and model.registration.registrationId = ? and model.memberType = ? group by model.currentAddress.localElectionBody.localElectionBodyId", params);
	}

	public List findCadresNotAssignedToBoothByTehsil(Long tehsilId,
			Long userId, String cadreType) {
		Object[] params = {tehsilId,userId,cadreType};
		return getHibernateTemplate().find("select count(model.cadreId) from Cadre model where model.currentAddress.booth.boothId is null" +
				" and model.currentAddress.tehsil.tehsilId = ? and model.registration.registrationId = ? and model.memberType = ? and model.currentAddress.tehsil.tehsilId is not null", params);
	}

	public List findCadresNotAssignedToBoothLocalElectionBody(Long localBodyId,
			Long userId, String cadreType) {
		Object[] params = {localBodyId, userId,cadreType};
		return getHibernateTemplate().find("select count(model.cadreId) from Cadre model where model.currentAddress.booth.boothId is null" +
				" and model.currentAddress.localElectionBody.localElectionBodyId = ? and model.registration.registrationId = ? and model.memberType = ? and model.currentAddress.localElectionBody.localElectionBodyId is not null", params);
	}

	@SuppressWarnings("unchecked")
	public List findCadresNotAssignedToBoothInWard(Long wardId, Long userId,
			String cadreType) {
		Object[] params = {wardId, userId,cadreType};
		return getHibernateTemplate().find("select count(model.cadreId) from Cadre model where model.currentAddress.booth.boothId is null" +
				" and model.currentAddress.ward.constituencyId = ? and model.registration.registrationId = ? and model.memberType = ? and model.currentAddress.ward.constituencyId is not null", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findActiveCadreForSMS(Long registrationId,String cadreType,String searchCriteria,String sortOption,String order,Integer startIndex,Integer maxResult)
	{
		StringBuffer queryBuffer = new StringBuffer("select model.cadreId,model.firstName,model.lastName,model.mobile,model.currentAddress.userAddressId ,"); 
		queryBuffer.append("model.memberType, model.education.qualification, model.occupation.occupation, model.casteCategory.category,model.cadreLevel.level ");
		queryBuffer.append("from Cadre model where model.registration.registrationId = "+registrationId+" "+cadreType+" "+searchCriteria+" order by "+sortOption+" " +order);
		
		Query queryObject = getSession().createQuery(queryBuffer.toString());
		queryObject.setFirstResult(startIndex);
		queryObject.setMaxResults(maxResult);
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findNormalCadreForSMS(Long registrationId,String cadreType,String searchCriteria,String sortOption,String order,Integer startIndex,Integer maxResult)
	{
		StringBuffer queryBuffer = new StringBuffer("select model.cadreId,model.firstName,model.lastName,model.mobile,model.currentAddress.userAddressId ,"); 
		queryBuffer.append("model.memberType, model.education.qualification, model.occupation.occupation, model.casteCategory.category ");
		queryBuffer.append("from Cadre model where model.registration.registrationId = "+registrationId+" "+cadreType+" "+searchCriteria+" order by "+sortOption+" " +order);
		
		Query queryObject = getSession().createQuery(queryBuffer.toString());
		queryObject.setFirstResult(startIndex);
		queryObject.setMaxResults(maxResult);
		return queryObject.list();
	}
	
	public List<Object[]> findNormalCadre(Long registrationId)
	{
		StringBuffer queryBuffer = new StringBuffer("select model.cadreId ");
		queryBuffer.append("from Cadre model where model.registration.registrationId = "+registrationId+" and model.memberType = 'Normal'" );
		
		Query queryObject = getSession().createQuery(queryBuffer.toString());
		
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> findCadreForSMS(Long registrationId,String cadreType,String searchCriteria,String SocailStatus,String genderStr,String mobileStr,String cadreNameStr,String roleStr, String sortOption,String order,Integer startIndex,Integer maxResult)
	{
		StringBuffer queryBuffer = new StringBuffer("select model.cadreId ");
		queryBuffer.append("from Cadre model where model.registration.registrationId = "+registrationId+" "+cadreType+" "+searchCriteria+" "+SocailStatus+" "+genderStr+" "+mobileStr+" "+cadreNameStr+" "+roleStr+" "+" order by "+sortOption+" " +order);
		
		Query queryObject = getSession().createQuery(queryBuffer.toString());
		
		queryObject.setFirstResult(startIndex);
		queryObject.setMaxResults(maxResult);
		
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> findTotalCadreCountForSms(Long registrationId,String cadreType,String searchCriteria,String SocailStatus,String genderStr,String mobileStr,String cadreNameStr,String roleStr)
	{
		StringBuffer queryBuffer = new StringBuffer("select count(model.cadreId) ");
		queryBuffer.append("from Cadre model where model.registration.registrationId = "+registrationId+" "+cadreType+" "+searchCriteria+" "+SocailStatus+" "+genderStr+" "+mobileStr+" "+cadreNameStr+" "+roleStr);
		
		Query queryObject = getSession().createQuery(queryBuffer.toString());
		
		return queryObject.list();
	}
	
	public Integer updateCadreImage(Long cadreId,String imageName)
	{
		StringBuilder query = new StringBuilder();
		query.append("update Cadre model set model.image = ? where model.cadreId = ?");
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter(0, imageName);
		queryObject.setParameter(1, cadreId);	
		
		return queryObject.executeUpdate();	
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getMobileNosOfCadre(List<Long> cadreIdsList)
	{
		Query queryObject = getSession().createQuery("select model.mobile from Cadre model where model.cadreId in (:IdsList) and model.mobile is not null");
		queryObject.setParameterList("IdsList", cadreIdsList);
		
		return queryObject.list();
	}
}
