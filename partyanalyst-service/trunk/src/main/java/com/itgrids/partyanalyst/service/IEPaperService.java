/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on January 20,2010
 */
package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.EPaperVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Constituency;

public interface IEPaperService {
	
	public List<EPaperVO> getEPapers(String accessType , Long accessValue);	
	public List<EPaperVO> getEPapersForDistrict(Long districtId);
	public List<SelectOptionVO> getDistrictsForState(Long stateId);
	public Long getStateIdFromConstitunecyByAccessValue(Long accessValue);
	public Long getStateIdFromDistrictByDistrictId(Long districtId);
}
