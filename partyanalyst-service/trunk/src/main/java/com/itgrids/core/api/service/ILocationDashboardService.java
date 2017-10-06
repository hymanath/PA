package com.itgrids.core.api.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.BenefitCandidateVO;
import com.itgrids.partyanalyst.dto.BoothInchargesVO;
import com.itgrids.partyanalyst.dto.CandidateDetailsForConstituencyTypesVO;
import com.itgrids.partyanalyst.dto.CommitteeBasicVO;
import com.itgrids.partyanalyst.dto.ConstituencyCadreVO;
import com.itgrids.partyanalyst.dto.ElectionInformationVO;
import com.itgrids.partyanalyst.dto.GrivenceStatusVO;
import com.itgrids.partyanalyst.dto.KeyValueVO;
import com.itgrids.partyanalyst.dto.LocationVO;
import com.itgrids.partyanalyst.dto.LocationVotersVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.MeetingsVO;
import com.itgrids.partyanalyst.dto.NominatedPostDetailsVO;
import com.itgrids.partyanalyst.dto.NominatedPostDashboardVO;
import com.itgrids.partyanalyst.dto.ToursBasicVO;

public interface ILocationDashboardService {
	public List<CandidateDetailsForConstituencyTypesVO> getCandidateAndPartyInfoForConstituency(Long locationValue,Long locationTypeId,List<Long> representativTypeIds);
	public List<LocationVotersVO> getVotersAndcadreAgeWiseCount(Long locationTypeId,Long locationValue,Long publicationDateId);
	public List<LocationVotersVO> getVotersAndCadreCasteWiseCount(String type, Long locationTypeId,Long locationValue,
			Long publicationDateId);
	//public List<LocationVotersVO> getCasteGroupNAgeWiseVoterNCadreCounts(Long constituencyId,Long publicationDateId);
	public List<LocationVotersVO> getCasteNAgeWiseVoterNCadreCounts(Long locationTypeId,Long locationValue, Long publicationDateId,
			Long casteGroupId, Long casteId);
	public List<KeyValueVO> getEnrollmentYearWiseCadres();
	public List<LocationVotersVO> getEnrollmentYearAgeGroupWiseCadres(Long locationTypeId,Long locationValue,Long enrollmentYearId);
	
	//meetings
	public List<LocationVotersVO> getLocationWiseMeetingsCount(Long locationTypeId, List<Long> locationValues,String fromDateStr,String toDateStr);
	
	//committees
	public CommitteeBasicVO getLocationWiseCommitteesCount(String locationType,Long locationId,Long enrollmentId);
	
	public List<BasicVO> getEnrollmentIds();
	public List<BasicVO> getElectionTypes();
	public List<BasicVO> getPublications();
	
	public List<MeetingsVO> getLevelWiseMeetingStatusCounts(String fromDate,String toDate,Long locationTypeId,List<Long> locationValues,String year);
	public List<KeyValueVO> getNominatedPostStatusWiseCount(Long locationTypeId,List<Long> locationValuesList,String fromDateStr, String toDateStr,String year);
	public List<KeyValueVO> getNominatedPostApplicationStatusWiseCount(Long locationTypeId,List<Long> locationValuesList,String fromDateStr, String toDateStr,String year);
	public List<KeyValueVO> getPositionWiseMemberCount(List<Long> locationValues,String fromDateStr, String toDateStr,Long locationTypeId,String year);
	public List<ToursBasicVO> getLocationWiseTourMembersComplainceDtls(final Long locationTypeId,final List<Long> locationValues,final String fromDateStr,final String toDateStr,String year);
	public List<BenefitCandidateVO> getGovtSchemeWiseBenefitMembersCount(final Long locationType, final Long locationValue,final Long publicationDateId);
	public List<BenefitCandidateVO> getMandalWiseBenefitMembersCount(final Long locationType, final Long locationValue,final Long govtSchemeId,final Long publicationDateId);
	public List<ConstituencyCadreVO> getLocationTypeWiseCadreCount(final Long locationTypeId,final List<Long> locationValues,final String year);
	public List<ConstituencyCadreVO> getAgeRangeGenerAndCasteGroupByCadreCount(Long locationTypeId, Long locationValue,Long enrollmentYearId);
	public GrivenceStatusVO getLocationWiseInsuranceStatusCounts(String fromDateStr,String toDateStr,Long locationTypeId,List<Long> locationValues,String year);
    public List<GrivenceStatusVO> getGrivenceTrustStatusCounts(String fromDateStr,String toDateStr,Long locationTypeId,List<Long> locationValues,String year);
    public List<BasicVO> getLocationWiseActivitysStatus(String fromDateStr,String toDateStr,String year,List<Long> locationValues,Long locationTypeId);
    public List<LocationWiseBoothDetailsVO> getAllDistricts(Long stateId);
	public List<LocationWiseBoothDetailsVO> getAllConstituenciesByDistrict(Long districtId);
	public List<ElectionInformationVO> getElectionInformationLocationWise(String fromDate, String toDate, Long locationTypeId,Long locationValue, List<Long> electionScopeIds,List<Long> partyIds,List<Long> electionYrs,List<String> subTypes);
	public BoothInchargesVO getBoothAssignInchargeCount(String fromDateStr, String toDateStr, Long locationTypeId,Long locationValue,
			List<Long> committeeEnrollmentYearsIdsLst);
	public List<BoothInchargesVO> getBoothCommitteeInchargesCount(Long locationId,Long locationValue,List<Long> boothCommitteeEnrollmentYearsIdsLst,String fromDateStr,String toDateStr);
	public List<LocationWiseBoothDetailsVO> getAllParlimentsForLocationDashBoard();
	public List<LocationWiseBoothDetailsVO> getAllConstituencyByParlimentId(Long parliamentIds);
	public List<LocationVotersVO> getVotersCastGroupWiseCount(Long locationTypeId,Long locationValue,Long publicationDateId);
	public NominatedPostDashboardVO getAllNominatedStatusListLevelWiseData(Long boardLevelId, List<Long> levelValues, Long levelId);
	public List<NominatedPostDetailsVO> getLocationWiseNominatedPostCandidateAgeRangeAndCasteCategorDetails(List<Long> locationValues,Long levelId,List<Long> statusIdsList,String type);
	public NominatedPostDetailsVO getAreaWiseDashboardCandidatesCountView(Long levelId,List<Long> levelVals,List<Long> statusIds);
	public List<NominatedPostDetailsVO> getLocationWiseNominatedPostAnalysisDetails(List<Long> locationValues,Long boardLevelId,Long searchLvlId,String type,List<Long> statusIds);
	public LocationVO getAllLocationWiseCount(List<Long> levelValues,Long levelId,Long publicationDateId );
	public CandidateDetailsForConstituencyTypesVO getPartyWiseMPandMLACandidatesCount(List<Long> electionIds,List<Long> electionScopeIds,Long loactionTypeId,Long loctionValue);
	public KeyValueVO getElectionYears(List<String> subTypes);
	
	public List<GrivenceStatusVO> getConstituencyWiseInsuranceWiseIssueTypeCounts(String fromDateStr, String toDateStr, Long locationTypeId,List<Long> locationValues,String year);
	public List<GrivenceStatusVO> getGrivenceDetails(String fromDateStr, String toDateStr,Long locationTypeId,List<Long> locationValues,String year) ;
	public List<GrivenceStatusVO> getLevelWiseGrievanceCounts(String fromDateStr, String toDateStr,Long locationTypeId,List<Long> locationValues,String year);
}