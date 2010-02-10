/**
 * 
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;
import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.Census;

/**
 * @author Sujatha
 *
 */
public interface ICensusDAO extends GenericDao<Census, Long> {
	
	public Set<Census> findByStateIdAndYear(Long stateId, int year);

	public Set<Census> findByDistrictIdAndYear(Long districtId, int year);
	
	public Set<Census> findByTehsilIdAndYear(Long tehsilId, int year);
	
	public Set<Census> findByTownshipIdAndYear(Long townshipId, int year);
	
	public Set<Census> findByWardIdAndYear(Long wardId, int year);
	
	public List<Census> findByYearAndTehsilIDs(Long year, String tehsilIDs);
	
	public List<Census> findByYearAndTownshipIDs(Long year, String townshipIDs);
	
	public List findCastWiseVotersForMandal(Long mandalID);
	
}
