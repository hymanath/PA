package com.itgrids.core.api.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CandidateDetailsForConstituencyTypesVO;
import com.itgrids.partyanalyst.dto.CommitteeBasicVO;
import com.itgrids.partyanalyst.dto.KeyValueVO;
import com.itgrids.partyanalyst.dto.LocationVotersVO;
import com.itgrids.partyanalyst.model.TdpCommitteeEnrollment;


public interface ILocationDashboardService {
	public CandidateDetailsForConstituencyTypesVO getCandidateAndPartyInfoForConstituency(Long constituencyId);
	public List<LocationVotersVO> getVotersAndcadreAgeWiseCount(Long constituencyId,Long publicationDateId);
	public List<LocationVotersVO> getVotersAndCadreCasteWiseCount(String type,Long constituencyId,Long publicationDateId);
	public List<LocationVotersVO> getCasteGroupNAgeWiseVoterNCadreCounts(Long constituencyId,Long publicationDateId);
	public List<LocationVotersVO> getCasteNAgeWiseVoterNCadreCounts(Long constituencyId,Long publicationDateId,Long casteGroupId,Long casteId);
	public List<KeyValueVO> getEnrollmentYearWiseCadres();
	public List<LocationVotersVO> getEnrollmentYearAgeGroupWiseCadres(Long constituencyId,Long enrollmentYearId);
	
	//meetings
	public List<LocationVotersVO> getLocationWiseMeetingsCount(String locationType,Long constituencyId);
	
	//committees
	public CommitteeBasicVO getLocationWiseCommitteesCount(String locationType,Long locationId,Long enrollmentId);
	
	public List<TdpCommitteeEnrollment> getEnrollmentIds();
}
