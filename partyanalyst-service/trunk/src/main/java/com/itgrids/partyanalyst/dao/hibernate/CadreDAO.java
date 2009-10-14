package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Map;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreDAO;
import com.itgrids.partyanalyst.model.Cadre;
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
	

	public Long findTotalCadresByUserID(Long userID){
		Query query = getSession().createQuery("SELECT count(*) FROM Cadre model WHERE model.registration.registrationId = ?" );
		query.setLong(0, userID);
		Long totalCadres = (Long) query.uniqueResult();
		return totalCadres;
	}
	
	@SuppressWarnings("unchecked")
	public List findCadresByLevels(Long userID){
		List totalCadresByLevel = getHibernateTemplate().find("SELECT model.cadreLevel.level,count(model.cadreLevel.level) " +
				"FROM Cadre model WHERE model.registration.registrationId = ? group by model.cadreLevel.level " +
				"order by model.cadreLevel.cadreLevelID", userID); 
		return totalCadresByLevel;
	}
	@SuppressWarnings("unchecked")
	public List findStatesByCountryID(String countryIDs){
		System.out.println("CadreDAO.findStatesByCountryID:::::::::::::::::::::::::"+countryIDs);
		List totalStates = getHibernateTemplate().find("Select model.stateId, model.stateName from State model " +
												"where model.country.countryId in(" + countryIDs + ")"); 
		return totalStates;
	}
	@SuppressWarnings("unchecked")
	public List findDistrictsByStateID(String stateIDs){
		List totalDistricts = getHibernateTemplate().find("Select model.districtId, model.districtName from District model " +
												"where model.state.stateId in(" + stateIDs + ")"); 
		return totalDistricts;
	}
	@SuppressWarnings("unchecked")
	public List findMandalsByDistrictID(String districtIDs){
		List totalMandals = getHibernateTemplate().find("Select model.tehsilId, model.tehsilName from Tehsil model " +
				"where model.district.districtId in(" + districtIDs + ")"); 
		return totalMandals;
	}
	@SuppressWarnings("unchecked")
	public List findVillagesByTehsilID(String tehsilIDs){
		/*List totalVillages = getHibernateTemplate().find("model.stateId, model.stateName from State model " +
				"where model.country.countryId in(" + tehsilIDs + ")"); */
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List findTotalStateZeroSizeCadres(String stateIDs, Long userID){
		List  results = getHibernateTemplate().find("Select model.state.stateId, count(model.state)from Cadre model " +
				"where model.registration.registrationId = ? and model.state.stateId in("+ stateIDs + ")", userID); 
		return results;
	}
	@SuppressWarnings("unchecked")
	public List findTotalDistrictZeroSizeCadres(String districtIDs, Long userID){
		System.out.println("District:::::::::::Narender::::::::::::::::"+districtIDs);
		List  results = getHibernateTemplate().find("Select model.district.districtId, count(model.district)from Cadre model " +
				"where model.registration.registrationId = ? and model.district.districtId in("+ districtIDs + ")", userID); 
		return results;
	}

	@SuppressWarnings("unchecked")
	public List findTotalMandalZeroSizeCadres(String mandalIDs, Long userID){
		List  results = getHibernateTemplate().find("Select model.tehsil.tehsilId, count(model.tehsil)from Cadre model " +
				"where model.registration.registrationId = ? and model.tehsil.tehsilId in("+ mandalIDs + ")", userID); 
		return results;
	}
}
