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
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.KeyValueVO;
import com.itgrids.partyanalyst.dto.LocationVO;
import com.itgrids.partyanalyst.dto.LocationVotersVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.MeetingsVO;
import com.itgrids.partyanalyst.dto.NominatedPostDashboardVO;
import com.itgrids.partyanalyst.dto.NominatedPostDetailsVO;
import com.itgrids.partyanalyst.dto.ToursBasicVO;

public interface ILocationDashboardService {
	public List<CandidateDetailsForConstituencyTypesVO> getCandidateAndPartyInfoForConstituency(Long locationValue,Long locationTypeId,List<Long> representativTypeIds,List<Long> roleIds,List<Long> committeeIds,List<Long> enrollmentYears,Long basicCommoteeId,Long enrollmentId);
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
	
	public List<BasicVO> getEnrollmentIds(List<Long> publicationDateIds);
	public List<BasicVO> getElectionTypes();
	public List<BasicVO> getPublications();
	
	public List<MeetingsVO> getLevelWiseMeetingStatusCounts(String fromDate,String toDate,Long locationTypeId,List<Long> locationValues,String year);
	public List<KeyValueVO> getNominatedPostStatusWiseCount(Long locationTypeId,List<Long> locationValuesList,String fromDateStr, String toDateStr,String year);
	public List<KeyValueVO> getNominatedPostApplicationStatusWiseCount(Long locationTypeId,List<Long> locationValuesList,String fromDateStr, String toDateStr,String year);
	public List<KeyValueVO> getPositionWiseMemberCount(List<Long> locationValues,String fromDateStr, String toDateStr,Long locationTypeId,String year);
	public List<ToursBasicVO> getLocationWiseTourMembersComplainceDtls(final Long locationTypeId,final List<Long> locationValues,final String fromDateStr,final String toDateStr,String year,final Long stateId);
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
	public NominatedPostDashboardVO getAllNominatedStatusListLevelWiseData(Long boardLevelId, List<Long> levelValues, Long levelId,String fromDateStr, String toDateStr,String year);
	public List<NominatedPostDetailsVO> getLocationWiseNominatedPostCandidateAgeRangeAndCasteCategorDetails(List<Long> locationValues,Long levelId,List<Long> statusIdsList,String type,String fromDateStr, String toDateStr,String year);
	public NominatedPostDetailsVO getAreaWiseDashboardCandidatesCountView(Long levelId,List<Long> levelVals,List<Long> statusIds,String fromDateStr, String toDateStr,String year);
	public List<NominatedPostDetailsVO> getLocationWiseNominatedPostAnalysisDetails(List<Long> locationValues,Long boardLevelId,Long searchLvlId,String type,List<Long> statusIds,String fromDateStr, String toDateStr,String year);
	public LocationVO getAllLocationWiseCount(List<Long> levelValues,Long levelId,Long publicationDateId );
	public CandidateDetailsForConstituencyTypesVO getPartyWiseMPandMLACandidatesCount(List<Long> electionIds,List<Long> electionScopeIds,Long loactionTypeId,Long loctionValue);
	public KeyValueVO getElectionYears(List<String> subTypes);
	public List<ElectionInformationVO> getElectionInformationLocationWiseVoterShare(String fromDate, String toDate, Long locationTypeId,Long locationValue, List<Long> electionScopeIds,List<Long> partyIds,List<Long> electionYrs,List<String> subTypes,String withAllaince);
	public List<GrivenceStatusVO> getConstituencyWiseInsuranceWiseIssueTypeCounts(String fromDateStr, String toDateStr, Long locationTypeId,List<Long> locationValues,String year);
	public List<GrivenceStatusVO> getLocationWiseGrivenceTrustIssueTypesCounts(String fromDateStr, String toDateStr, Long locationTypeId,List<Long> locationValues,String year);
	public List<GrivenceStatusVO> getGrivenceDetails(String fromDateStr, String toDateStr,Long locationTypeId,List<Long> locationValues,String year) ;
	public List<GrivenceStatusVO> getLevelWiseGrievanceCounts(String fromDateStr, String toDateStr,Long locationTypeId,List<Long> locationValues,String year);
	public List<ElectionInformationVO> getLocationWiseElectionResults(List<Long> electionScopeId,List<String> subTypeList,Long lelevlId,List<Long> levelValue,List<Long> year,List<Long> partyIdsList,Long constituencyId,String withAllaince);
	public  List<ElectionInformationVO>  getElectionDetailsData(List<Long> electionYears,Long locationTypeId,List<Long>locationValues,Long electionId,List<String> subTypes,List<Long> partyIds,List<Long> electionScopeIds,String withAllaince);
	public List<CandidateDetailsForConstituencyTypesVO> getPartyWiseMPandMLACandidatesCountDetials(List<Long> electionIds,List<Long> electionScopeIds,Long loactionTypeId,Long loctionValue,List<Long> partyId,Long districtId,List<String> electionYears);
	public List<LocationWiseBoothDetailsVO> getAllParliamentConstituencyByAllLevels(Long loactionTypeId,List<Long> locationValues);
	public List<MeetingsVO> getAreaWisePartyMeetingsDetails(Long locationScopeId,List<Long> locationValues,String startDate,String endDate,Long meetingLevelId,Long meetingTypeId,Long meetingMainTypeId,String searchFor);
	//Grievance
	public GrivenceStatusVO getGrivenceOverviewDtls(String fromDateStr, String toDateStr,Long locationTypeId,List<Long> locationValues,String year,Long stateId);
	public List<GrivenceStatusVO> getGrivenceComplaintCountDepartmentWise(String fromDateStr, String toDateStr,Long locationTypeId,List<Long> locationValues,String year,Long stateId);
	public GrivenceStatusVO getGrivenceFinancialSupportDtls(String fromDateStr, String toDateStr,Long locationTypeId,List<Long> locationValues,String year,Long stateId);
	public List<GrivenceStatusVO> getLocationWiseTypeOfIssueGrivenceComplaintCount(String fromDateStr, String toDateStr,Long locationTypeId,List<Long> locationValues,String year,Long stateId);
	//Insurance
	public GrivenceStatusVO getInsuranceOverviewDetails(String fromDateStr, String toDateStr,Long locationTypeId,List<Long> locationValues,String year,Long stateId);
	public List<GrivenceStatusVO> getLocationWiseInsuranceIssueTypeComplaintCount(String fromDateStr, String toDateStr,Long locationTypeId,List<Long> locationValues,String year,Long stateId);
	//Trust education
	public GrivenceStatusVO getTrustEducationOverviewDetails(String fromDateStr, String toDateStr,Long locationTypeId,List<Long> locationValues,String year,Long stateId);
	public List<GrivenceStatusVO> getLocationWiseTrustEducationComplaintCount(String fromDateStr, String toDateStr,Long locationTypeId,List<Long> locationValues,String year,Long stateId);
	public GrivenceStatusVO getTrustEducationSubjectForDetails(String fromDateStr, String toDateStr, Long locationTypeId,List<Long> locationValues, String year, Long stateId);
	
	public List<ElectionInformationVO> getElectionYearWisePartyDetails(List<Long> electionScopeIdsLst,List<String> subTypes);
	public List<KeyValueVO> getLocationWiseElectionDetails(String locationType);
	public List<ConstituencyCadreVO> getCategoryWiseGenderCount(Long locationScopeId,List<Long> locationValuesList,List<Long> enrollmentYearIdsList);
	public List<IdNameVO> getElectionYearWisePartyList(List<Long> electionScopeIdsLst,List<String> subTypes,List<Long> yearList);
	}
