package com.itgrids.core.api.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.LocationVotersVO;

public interface ILocationWiseCasteInfoService {
	
	public List<LocationVotersVO> getVotersAndCadreCasteWiseCount(Long locationTypeId,Long locationValue, Long publicationDateId, Long EnrollmentYearId,Long casteGroupId, String assendingType);
	public List<LocationVotersVO> getVotersCastGroupWiseCount(Long locationTypeId,Long locationValue,Long publicationDateId,Long EnrollmentYearId);
	public List<LocationVotersVO> getCasteNAgeWiseVoterNCadreCounts(Long locationTypeId,Long locationValue, Long publicationDateId,Long casteGroupId, Long casteId,Long enrollemntyearId);
	public List<LocationVotersVO> getVotersAndcadreAgeWiseCount(Long locationTypeId,Long locationValue, Long publicationDateId, Long enrollmentId);
	public List<LocationVotersVO> getCasteWiseCadreCounts(Long locationTypeId,List<Long> LocationValue, List<Long> enrollmentYearIds);
	public List<LocationVotersVO> getLocationWiseVoterNCadreCounts(Long locationTypeId,List<Long> locationValue,Long casteId,Long enrollmentYearId,Long publicationDateId);

}
