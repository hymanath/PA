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
}
