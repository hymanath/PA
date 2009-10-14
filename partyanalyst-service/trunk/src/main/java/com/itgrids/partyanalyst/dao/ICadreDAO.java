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
	public List findTotalStateZeroSizeCadres(String stateIDs, Long userID);
	public List findTotalDistrictZeroSizeCadres(String districtIDs, Long userID);
	public List findTotalMandalZeroSizeCadres(String mandalIDs, Long userID);
	
	//public Object findDistCadresByState(Long stateID, Long userID);

}
