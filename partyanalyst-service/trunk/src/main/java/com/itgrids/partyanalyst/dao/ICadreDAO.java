package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Map;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Cadre;
/**
 * 
 * @author Narender Akula
 *
 */
public interface ICadreDAO extends GenericDao<Cadre, Long>{
	public List<Cadre> findByProperty(String propertyName, Object value);
	public List<Cadre> findByUserID(Long userID);
	public Long findTotalCadresByUserID(Long userID);
	public List findCadresByLevels(Long userID);
	public List findStatesByCountryID(String countryID);
	public List findDistrictsByStateID(String stateID);
	public List findMandalsByDistrictID(String districtID);
	public List findVillagesByTehsilID(String tehsilID);
	public List findCadreSizeStateWise(Long userID); //findTotalStateZeroSizeCadres
	public List findCadreSizeDistrictWise(Long userID);//findTotalDistrictZeroSizeCadres
	public List findCadreSizeMandalWise(Long userID);//findTotalMandalZeroSizeCadres
	public List findCadreSizeVillageWise(Long userID);//findTotalVillageZeroSizeCadres
	//Ajax call methods
	public List findStateCadresByCountry(Long countryID, Long userID);
	public List findDistCadresByState(Long stateID, Long userID);
	public List findMandalCadresByDist(Long districtID, Long userID);
	public List findVillageCadresByMandal(Long mandalID, Long userID);
	public List findCadresByVillage(Long villageID, Long userID);
	public List findMandalCadresByMandals(String mandalIDs, Long userID);
	//public List findMandalsByConstituencyID(String constituencyId);
	
	public List<Cadre> findCadresByCadreLevel(String cadreLevel, Long userID);

}
