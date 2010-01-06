package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.SelectOptionVO;
import java.util.List;
/**
 * 
 * @author Narender Akula
 *
 */
public interface IRegionServiceData {
	public List<SelectOptionVO> getDistrictsByStateID(Long stateID);	
	public List<SelectOptionVO> getConstituenciesByDistrictID(Long districtID);
	public List<SelectOptionVO> getMandalsByConstituencyID(Long constituencyID);
	public List<SelectOptionVO> getStateDistrictByConstituencyID(Long constituencyID);
	public List<SelectOptionVO> getStateDistrictByDistrictID(Long constituencyID);
	public List<SelectOptionVO> getStatesByCountry(Long countryID);
	public List<SelectOptionVO> getStatesByCountryFromBooth(Long countryID);
	public List<SelectOptionVO> getDistrictsByStateIDFromBooth(Long stateID);
	public List<SelectOptionVO> getConstituenciesByDistrictIDFromBooth(Long districtID);
	public List<SelectOptionVO> getMandalsByConstituencyIDFromBooth(Long constituencyID);
	public List<SelectOptionVO> getStateByParliamentConstituencyID(Long constituencyID);
	public Long getLatestParliamentElectionYear(Long stateID);
	public List<SelectOptionVO> getTownshipsHamletsWards(String type, Long id);
}
