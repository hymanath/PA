package com.itgrids.core.api.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.LocationVotersVO;

public interface ILocationWiseCasteInfoService {
	
	public List<LocationVotersVO> getVotersAndCadreCasteWiseCount(Long locationTypeId,Long locationValue, Long publicationDateId,Long EnrollmentYearId,Long casteGroupId);
	public List<LocationVotersVO> getVotersCastGroupWiseCount(Long locationTypeId,Long locationValue,Long publicationDateId,Long EnrollmentYearId);

}
