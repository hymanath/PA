package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.ConstituencyBoothInfoVO;
import com.itgrids.partyanalyst.dto.RegionalMappingInfoVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.excel.booth.BoothInfo;

import java.util.List;
import java.util.Set;
/**
 * 
 * @author Narender Akula
 *
 */
public interface IRegionServiceData {
	public List<SelectOptionVO> getStatesByCountry(Long countryID);
	public List<SelectOptionVO> getDistrictsByStateID(Long stateID);	
	public List<SelectOptionVO> getConstituenciesByDistrictID(Long districtID);	
	public List<SelectOptionVO> getSubRegionsInConstituency(Long constituencyId, String year, String scope);
	public List<SelectOptionVO> getHamletsOrWards(Long locationId, String year);
	public List<SelectOptionVO> getLocalElectionBodiesOfADistrict(Long districtId);	
	public String getLocalBodyElectionTypeInConstituency(Long constituencyId);
	public List<SelectOptionVO> getWardsInALocalElectionBody(Long localElectionBodyId);
	public List<SelectOptionVO> getWardsInALocalElectionBody(Long localElectionBodyId,Long constituencyId,String year);	
	public List<SelectOptionVO> getMandalsByConstituencyID(Long LocationId);
	public List<SelectOptionVO> getConstituenciesByAreaTypeInDistrict(Long districtId, String areaType);
	public List<SelectOptionVO> getStateDistrictByConstituencyID(Long constituencyID);
	public List<SelectOptionVO> getStateDistrictByDistrictID(Long constituencyID);
	public List<SelectOptionVO> getStatesByCountryFromBooth(Long countryID);
	public List<SelectOptionVO> getDistrictsByStateIDFromBooth(Long stateID);
	public List<SelectOptionVO> getConstituenciesByDistrictIDFromBooth(Long districtID);
	public List<SelectOptionVO> getMandalsByConstituencyIDFromBooth(Long constituencyID);
	public List<SelectOptionVO> getStateByParliamentConstituencyID(Long constituencyID);
	public Long getLatestParliamentElectionYear(Long stateID);
	public List<SelectOptionVO> getTownshipsHamletsWards(String type, Long id);
	public List<SelectOptionVO> getAllParliamentConstituencies(Long electionScopeId, Long countryId);
	public List<SelectOptionVO> getAllConstituenciesByElectionTypeInState(Long electionTypeId, Long stateId);
	public List<SelectOptionVO> getBoothsInTehsilOrMunicipality(Long tehsilId, Long year, Long constituencyId);
	public List<SelectOptionVO> getboothsInWard(Long wardId, Long year, Long constituencyId);
	public List<SelectOptionVO> getboothDetailsInWard(Long wardId, Long year,Long constituencyId);
	public List<SelectOptionVO> getBoothsInLocalBodysByConstituencyId(Long localBodyId, Long year, Long constituencyId);
	
	public List<SelectOptionVO> getTehsilsInAConstituency(Long constituencyId);
	public List<SelectOptionVO> getLocalElectionBodiesInConstituency(Long constituencyId, String year);
	public List<SelectOptionVO> getHamletsInATehsil(Long tehsilId);
	public List<BoothInfo> getBoothCompleteDetails(String areaType, String boothIds);
	public List<SelectOptionVO> getBoothsInTehsilByConstituency(Long tehsilId,Long year, Long constituencyId);
	public List<SelectOptionVO> getBoothsInLocalBodysByConstituency(Long localBodyId,Long year, Long constituencyId);
	public Set<RegionalMappingInfoVO> getLocalBodiesInDistAndConst(Long districtId, Long constituencyId, String year);
	public Set<RegionalMappingInfoVO> getWardsInLocalBodyAndConst(Long localBodyId, Long constituencyId, String year);
	public Set<RegionalMappingInfoVO> getboothsInLocalBodiesAndConst(Long localBodyId, Long constituencyId, String year);
	public Set<RegionalMappingInfoVO> getboothsInWardsAndConst(Long wardId, Long constituencyId, String year);
	public List<SelectOptionVO> getAllRegionScopesForModule(String module, Long stateId);
	public String getConstituencyAreaType(Long constituencyId);  
	
	public String getRegionNameByRegionId(Long regionId,String regionType);
	public List<SelectOptionVO> getParliamentConstituenciesByDistrict(Long district);	
	
	public Boolean checkForHamletsAvailability(Long locationId);
	public Boolean checkForAreaRuralType(Long locationId);
	
	public List<SelectOptionVO> getStatesByCountryForSearch(Long countryID);
	public List<SelectOptionVO> getUserStateList(String accessType,Long accessValue);
	public List<SelectOptionVO> getProblemTypesByRegionScopeId(Long regionScopeId);
	
}
