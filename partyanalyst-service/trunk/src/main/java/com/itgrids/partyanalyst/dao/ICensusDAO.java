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
	
	@SuppressWarnings("unchecked")
	public List findCastWiseVotersForMandal(Long mandalID);
	
	@SuppressWarnings("unchecked")
	public List findCensusDetailsForAMandal(Long stateId,Long districtId,Long tehsilId,int year,String Level);
	
	public List findAllRevenueVillagesInfoInMandal(Long year, Long mandalId, String levels);
	
	public List findMandalWiseCensusDetails(Long stateId,Long districtId,Long tehsilId,Long year,String level);
	
	public List findCensusDetailsForAPartialMandal(Long stateId,Long districtId,Long year,String level,String villageIds);
	
	public List<Object[]> findTownshipWiseCensusDetails(Long stateId,Long districtId,Long townshipId,Long year,String level);
	
	public List findCensusDetailsForAPartialTown(Long stateId,Long districtId,Long year,String level,String wardIds);
	
	public List<Object[]> findMandalWiseCompleteCensusDetails(Long stateId,Long districtId,Long tehsilId,Long year,String level);
	
	public List<Object[]> findCompleteCensusDetailsForAPartialMandal(Long stateId,Long districtId,Long year,String level,String villageIds);
	
	public List<Object[]> findCompleteCensusDetailsForAPartialTown(Long stateId,Long districtId,Long year,String level,String wardIds);
	
	public List<Object[]> findTownshipWiseCompleteCensusDetails(Long stateId,Long districtId,Long townshipId,Long year,String level);
	
	public List<Object[]> getCensusDetailsOfAState(Long stateId, Long year);
	
    public List<Object[]> getDistrictPopulationForDifferentYears(Long districtId , List<Long> years);
    
    public List<Object[]> getStatePopulationForDifferentYears(Long stateId , List<Long> years);

}
