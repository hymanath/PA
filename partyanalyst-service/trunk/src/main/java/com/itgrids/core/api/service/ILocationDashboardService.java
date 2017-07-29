package com.itgrids.core.api.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.AlertOverviewVO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.BenefitCandidateVO;
import com.itgrids.partyanalyst.dto.CandidateDetailsForConstituencyTypesVO;
import com.itgrids.partyanalyst.dto.CommitteeBasicVO;
import com.itgrids.partyanalyst.dto.ConstituencyCadreVO;
import com.itgrids.partyanalyst.dto.GrivenceStatusVO;
import com.itgrids.partyanalyst.dto.InsuranceStatusCountsVO;
import com.itgrids.partyanalyst.dto.KeyValueVO;
import com.itgrids.partyanalyst.dto.LocationVotersVO;
import com.itgrids.partyanalyst.dto.ToursBasicVO;

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
	
	public List<BasicVO> getEnrollmentIds();
	
	public List<AlertOverviewVO> getLevelWiseMeetingStatusCounts(String fromDate,String toDate,Long locationId,Long locationValue);
	public List<KeyValueVO> getNominatedPostStatusWiseCount(Long locationTypeId,List<Long> locationValuesList,String fromDateStr, String toDateStr,String year);
	public List<KeyValueVO> getNominatedPostApplicationStatusWiseCount(Long locationTypeId,List<Long> locationValuesList,String fromDateStr, String toDateStr,String year);
	public List<KeyValueVO> getPositionWiseMemberCount(Long constituencyId,String fromDateStr, String toDateStr);
	public List<ToursBasicVO> getLocationWiseTourMembersComplainceDtls(final String locationType,final Long locationValue,final String fromDateStr,final String toDateStr);
	public List<BenefitCandidateVO> getGovtSchemeWiseBenefitMembersCount(final String locationType, final Long locationValue);
	public List<BenefitCandidateVO> getMandalWiseBenefitMembersCount(final String locationType, final Long locationValue,final Long govtSchemeId);
	public List<ConstituencyCadreVO> getLocationTypeWiseCadreCount(final String locationType,final Long locationValue);
	public List<ConstituencyCadreVO> getAgeRangeGenerAndCasteGroupByCadreCount(final String locationType, final Long locationValue,final Long enrollmentYearId);
	public InsuranceStatusCountsVO getLocationWiseInsuranceStatusCounts(String fromDateStr,String toDateStr,Long locationId,Long locationValue);
    public List<GrivenceStatusVO> getGrivenceTrustStatusCounts(String fromDateStr,String toDateStr,Long locationId,Long locationValue);
    public List<BasicVO> getLocationWiseActivitysStatus(String fromDateStr,String toDateStr,String year,List<Long> locationValues,Long locationTypeId);
}
