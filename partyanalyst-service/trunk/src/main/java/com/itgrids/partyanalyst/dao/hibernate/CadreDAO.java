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
	public List findCadreSizeStateWise(Long userID){
		List  results = getHibernateTemplate().find("Select model.state.stateId, count(model.state)from Cadre model " +
				"where model.registration.registrationId = ? group by model.state", userID); 
		return results;
	}
	@SuppressWarnings("unchecked")
	public List findCadreSizeDistrictWise(Long userID){
		List  results = getHibernateTemplate().find("Select model.district.districtId, count(model.district)from Cadre model " +
				"where model.registration.registrationId = ? group by model.district", userID); 
		return results;
	}

	@SuppressWarnings("unchecked")
	public List findCadreSizeMandalWise(Long userID){
		List  results = getHibernateTemplate().find("Select model.tehsil.tehsilId, count(model.tehsil.tehsilId)from Cadre model " +
				"where model.registration.registrationId = ? group by model.tehsil.tehsilId", userID); 
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
	

	public List findStateCadresByCountry(Long countryID, Long userID, String cadreType){
		Object[] params = {userID,cadreType};
		List  results = getHibernateTemplate().find("Select model.state.stateId, model.state.stateName, count(model.state.stateId)from Cadre model " +
				"where model.registration.registrationId = ? group by model.state.stateId order by model.state.stateName", params); 
		return results;
	}
	@SuppressWarnings("unchecked")
	public List findDistCadresByState(Long stateID, Long userID, String cadreType){
		Object[] params = {userID,stateID, cadreType};
		List  results = getHibernateTemplate().find("Select model.district.districtId, model.district.districtName, count(model.district.districtId)from Cadre model " +
				"where model.registration.registrationId = ? and model.state.stateId=? and model.memberType = ? group by model.district.districtId order by model.district.districtName", params); 
		return results;
	}

	public List findConstituencyCadresByDist(Long districtID, Long userID, String cadreType){
		Object[] params = {userID,districtID, cadreType};
		List  results = getHibernateTemplate().find("Select model.constituency.constituencyId, model.constituency.name, count(model.constituency.constituencyId)from Cadre model " +
				"where model.registration.registrationId = ? and model.district.districtId=? and model.memberType = ? group by model.constituency.constituencyId order by model.constituency.name", params); 
		return results;
	}
	public List findMandalCadresByConstituency(Long constituencyID, Long userID, String cadreType){
		Object[] params = {userID,constituencyID, cadreType};
		List  results = getHibernateTemplate().find("Select model.tehsil.tehsilId, model.tehsil.tehsilName, count(model.tehsil.tehsilId)from Cadre model " +
				"where model.registration.registrationId = ? and model.constituency.constituencyId=? and model.memberType = ? group by model.tehsil.tehsilId order by model.tehsil.tehsilName", params); 
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
		List  results = getHibernateTemplate().find("Select model.village.townshipId, model.village.townshipName, count(model.village.townshipId), model.village.townshipType from Cadre model " +
				"where model.registration.registrationId = ? and model.tehsil.tehsilId=? and model.memberType = ? group by model.village.townshipId order by model.village.townshipName", params); 
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cadre> findCadresByVillage(Long villageID, Long userID, String cadreType){
		Object[] params = {userID,villageID,cadreType};
		List<Cadre>  results = getHibernateTemplate().find("from Cadre model " +
				"where model.registration.registrationId = ? and model.village.townshipId=? and model.memberType = ?", params); 
		return results;
	}
	
	public List findMandalCadresByMandals(String mandalIDs, Long userID, String cadreType){
		Object[] params = {cadreType};
		List result = getHibernateTemplate().find("Select model.tehsil.tehsilId, model.tehsil.tehsilName, count(model.tehsil.tehsilId) from Cadre model " +
				"where model.registration.registrationId = "+userID+" and model.tehsil.tehsilId in(" + mandalIDs + ") and model.memberType = ?" +
						" group by model.tehsil.tehsilId order by model.tehsil.tehsilName",params);
		return result;
	}
	

	@SuppressWarnings("unchecked")
	public List<Cadre> findCadresByCadreLevel(String cadreLevel, Long userID, String cadreType){
		Object[] params = {userID,cadreLevel, cadreType};
		List<Cadre>  results = getHibernateTemplate().find("from Cadre model " +
				"where model.registration.registrationId = ? and model.cadreLevel.level=? and model.memberType = ?", params); 
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
				"where model.registration.registrationId = ? and model.district.districtId=?", params); 
		return results;
	}
	@SuppressWarnings("unchecked")
	public List<Cadre> findCadresByConstituency(Long constituencyID, Long userID){
		Object[] params = {userID,constituencyID};
		List<Cadre>  results = getHibernateTemplate().find("from Cadre model " +
				"where model.registration.registrationId = ? and model.constituency.constituencyId=?", params); 
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cadre> findCadresByMandal(Long mandalID, Long userID){
		Object[] params = {userID,mandalID};
		List<Cadre>  results = getHibernateTemplate().find("from Cadre model " +
				"where model.registration.registrationId = ? and model.tehsil.tehsilId=?", params); 
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cadre> findCadresByState(Long stateID, Long userID){
		Object[] params = {userID,stateID};
		List<Cadre>  results = getHibernateTemplate().find("from Cadre model " +
				"where model.registration.registrationId = ? and model.state.stateId=?", params); 
		return results;
	}

	@SuppressWarnings("unchecked")
	public List getCadreSizeByHamlet(String revenueVillageIDs, Long userID, String cadreType){
		Object[] params = {userID,cadreType};
		List  results = getHibernateTemplate().find("Select model.hamlet.hamletId, model.hamlet.hamletName, " +
				"count(model.hamlet.hamletId) from Cadre model " +
				"where model.registration.registrationId = ?  and model.village.townshipId in (" + revenueVillageIDs +") and model.memberType = ?"+
				"group by model.hamlet.hamletId order by model.hamlet.hamletName", params); 
		return results;
	}
	/*@SuppressWarnings("unchecked")
	public List<Cadre> findCadresByVillage(Long villageID, Long userID){
		Object[] params = {userID,villageID};
		List<Cadre>  results = getHibernateTemplate().find("from Cadre model " +
				"where model.registration.registrationId = ? and model.village.townshipId=?", params); 
		return results;
	}*/
	@SuppressWarnings("unchecked")
	public List<Cadre> findCadresByHamlet(Long hamletID, Long userID, String cadreType){
		Object[] params = {userID,hamletID,cadreType};
		List<Cadre>  results = getHibernateTemplate().find("from Cadre model " +
				"where model.registration.registrationId = ? and model.hamlet.hamletId=? and model.memberType = ?", params); 
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List findHamletsByRVs(String townships){
		List totalHamlets = getHibernateTemplate().find("Select model.hamletId, model.hamletName from Hamlet model " +
					"where model.township.townshipId  in(" + townships + ") order by model.hamletName");
		return totalHamlets;
	}
	
	@SuppressWarnings("unchecked")
	public List findCadreSizeHamletWise(Long userID){
		List  results = getHibernateTemplate().find("Select model.hamlet.hamletId, count(model.hamlet.hamletId)from Cadre model " +
					"where model.registration.registrationId = ? group by model.hamlet.hamletId", userID); 
		return results;
		
	}
	
}
