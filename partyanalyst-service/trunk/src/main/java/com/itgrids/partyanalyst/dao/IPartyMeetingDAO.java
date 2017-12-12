package com.itgrids.partyanalyst.dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.CommitteeInputVO;
import com.itgrids.partyanalyst.dto.PartyMeetingsInputVO;
import com.itgrids.partyanalyst.model.PartyMeeting;

public interface IPartyMeetingDAO extends GenericDao<PartyMeeting,Long>{
	//public List<Object[]> getAllMeetings(Long meetingType,Long locationLevel,Long stateId,Long districtId,Long constituencyId,Long mandalId,Long townId,Long divisonId,Long villageId,Long wardId,Date startDate,Date endDate);
	public List<Object[]> getAllMeetings(Long meetingType,Long locationLevel,List<Long> stateList,List<Long> districtList,List<Long> constituencyList,List<Long> mandalList,List<Long> townList,List<Long> divisonList,List<Long> villageList,List<Long> wardList,Date startDate,Date endDate,Long meetingLevel);
	public List<Object[]> getPartyMeetingDetailsByMeetingIdList(List<Long> patyMeetingIdsList,Date toDayDate);
	
	//public List<Long> getPartyMeetingIdsByLevelAndLocation(Long partyMeetingLevelId,Date fromDate,Date toDate,Long partyMeetingTypeId,Long locationLevelId,Long locationValue);
	public List<Long> getPartyMeetingIdsByLevelAndLocation(Long partyMeetingLevelId,Date fromDate,Date toDate,Long partyMeetingTypeId,Long locationLevelId,List<Long> stateList,List<Long> districtList,List<Long> constituencyList,List<Long> mandalList,List<Long> townList,List<Long> divisonList,List<Long> villageList,List<Long> wardList);
	
	public BigInteger getLocationWiseTotalMeetingsCount(Long committeeLevelId,List<Long> committeeLevelValueList,Date fromDate,Date toDate);
	public List<Object[]> getMontlyWiseMeetingsDetails(Long committeeLevelId,List<Long> committeeLevelValueList,Date fromDate,Date toDate,List<String> searchDatesList,int firstRecord,int maxResult);
	public Object[] getMeetingNameByMeetingId(Long meetingId);
	public List<Object[]> getLevelWiseMeetingDetails(Date startDate,Date endDate,String level,List<Long> levelValues);
	public Integer updateConductedDetails(Long meetingId,String isConducted,String remarks,Date cdDate);
	 public Integer updateConductedStatus(Long meetingId,String isConducted,Long userId,Date presentDate);
	 public Integer updateConductedDate(Long meetingId,Date conductedDate,Long userId,Date presentDate);
	 public Integer updateConductedReason(Long meetingId,String remarks,Long userId,Date presentDate);
	 
	public List<Object[]> getTotalPlannedPartyMeetings(Date startDate,Date endDate,List<Long> locationIds,StringBuilder locationsPart);
	public List<Object[]> getConductedNotConductedPartyMeetingsByDPO(Date startDate,Date endDate,List<Long> locationIds,StringBuilder locationsPart);
	public List<Object[]> getConductedNotConductedPartyMeetingsByIVR(Date startDate,Date endDate,List<Long> locationIds,StringBuilder locationsPart);
	public List<Object[]> getConductedPartyMeetingsByAttendance(Date startDate,Date endDate,List<Long> locationIds,StringBuilder locationsPart);
	
	public List<Object[]> getMeetingDetailsForALevelByLocationId(int month,int year,List<Long> partyMeetingLevelIds,List<Long> locationIds,StringBuilder locationsPart);
	public List<Object[]> getMeetingDetailsForALevelByLocationIdByIVR(int month,int year,List<Long> partyMeetingLevelIds,List<Long> locationIds,StringBuilder locationsPart);
	
	public List<Object[]> getPartyMeetingOverAllCountByUserAccessLevel(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> partyMeetingTypeValues);
	public List<Object[]> getPartyMeetingOverAllCountLocationWiseByUserAccessLevel(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> partyMeetingTypeValues);

	public List<Object[]> getLocationWiseMeetingsCountByLocIds(CommitteeInputVO inputBO);
	public List<Object[]> getTopPoorMeetingLocations(CommitteeInputVO committeeBO);
	
	public List<Object[]> getNoOfMeetingsByPartyMeetingTypeIds(PartyMeetingsInputVO inputVO);
	public List<Object[]> getPartyMeetingCommentsDtls(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> partyMeetingTypeIds,String meetingStatus,List<Long> PartyMeetingLevelIds,String isComment,Long locationId,String locationType,String meetingLevelType);
	public List<Object[]> getPartyMeetingComulativeCount(List<Long> partyMeetingsIds,String reportType,String countType);
	
	public List<Object[]> getDistrictByMeetingId(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> partyMeetingTypeValues,String meetingStatus,List<Long> PartyMeetingLevelIds,String isComment);
	public List<Object[]> getConstituencyByMeetingId(Long districtId,Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> partyMeetingTypeValues,String meetingStatus,List<Long> PartyMeetingLevelIds,String isComment);
	public List<Object[]> getMandalByMeetingId(Long constituencyId,Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> partyMeetingTypeValues,String meetingStatus,List<Long> PartyMeetingLevelIds,String isComment);
	public List<Object[]> getTownDivisionByMeetingId(Long constituencyId,Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> partyMeetingTypeValues,String meetingStatus,List<Long> PartyMeetingLevelIds,String isComment);
	public List<Object[]> getFinalAllMeetings(Long meetingType,Long locationLevel,List<Long> stateList,List<Long> districtList,List<Long> constituencyList,List<Long> mandalList,
			List<Long> townList,List<Long> divisonList,List<Long> villageList,List<Long> wardList,Date startDate,Date endDate,Long meetingLevel,String status);
	public List<Object[]> getThirdPartyStatusDetails(List<Long> partyMeetingIds);
	public List<Object[]> totalMeetingsInConstituencyLevelWise(int month , int year);
	public List<Object[]> notConductedMeetingsInConstituencyLevelWise(int month , int year);
	public List<Object[]> notConductedMeetingsInConstituency(int month , int year);
	public List<Object[]> getConstWiseNotConductedPartyMeetings(int month , int year);
	public List<Object[]> getConstInchargeTeluguNames();
	public String getPartyMeetingName(Long partyMeetingId);
	public List<Long> getPartyMeetingIdList();
	public List<Object[]> plannedMeetingIdAndName(PartyMeetingsInputVO inputVO,Long locationId,Set<Long> locationValuesSet);
	public List<Object[]> getCustomPartyMeetingsMainTypeOverViewData(Date startDate, Date endDate);
	public List<Object[]> getMultiLocationWiseMeetingGroupsData(Long partyMeetnMainTypId,Long userAccessLevelId,Set<Long> locationValuesSet,Date fromDate,Date toDate,Long stateId);
	public Long getPlannedCount(Long partyMeetnMainTypId,Long userAccessLevelId,Set<Long> locationValuesSet,
			Date fromDate,Date toDate,Long stateId,Long partyMeetingLevelId,Long partyMeetngGrpId);
	
	public List<Object[]> getTotalMeetingsCounts(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Long partyMeetingMainTypeId,Long partyMeetingLevelId,
			Long meetingGrpId,Date fromDate,Date toDate);
	public List<Object[]> getTotalConductedMeetingsCounts(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Long partyMeetingMainTypeId,Long partyMeetingLevelId,
			Long meetingGrpId,Date fromDate,Date toDate);
	public List<Object[]> getTotalAttendedMembers(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Long partyMeetingMainTypeId,Long partyMeetingLevelId,
			Long meetingGrpId,Date fromDate,Date toDate);
	public List<Object[]> getTotalInvitedMembers(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Long partyMeetingMainTypeId,Long partyMeetingLevelId,
			Long meetingGrpId,Date fromDate,Date toDate);
	
	public List<Object[]> getLocationWiseTotalMeetingsCounts(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Long partyMeetingMainTypeId,Long partyMeetingLevelId,
			Long meetingGrpId,Date fromDate,Date toDate);
	public List<Object[]> getLocationWiseTotalConductedMeetingsCounts(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Long partyMeetingMainTypeId,Long partyMeetingLevelId,
			Long meetingGrpId,Date fromDate,Date toDate);
	public List<Object[]> getLocationWiseTotalAttendedMembers(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Long partyMeetingMainTypeId,Long partyMeetingLevelId,
			Long meetingGrpId,Date fromDate,Date toDate);
	public List<Object[]> getLocationWiseTotalInvitedMembers(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Long partyMeetingMainTypeId,Long partyMeetingLevelId,
			Long meetingGrpId,Date fromDate,Date toDate);
	public List<Long> getPartyMeetingLevelIdsByAccessLevel(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Long partyMeetingMainTypeId,Long meetingGrpId,Date fromDate,Date toDate);
	
	public List<Object[]> getPartyMeetingSessionWiseDtls(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,
			List<Long> partyMeetingTypeIds,String meetingStatus,List<Long> PartyMeetingLevelIds,String isComment,Long locationId,String locationType,String meetingLevelType);
	public List<Object[]> getPartyMeetingsInvitedMembersBySessions(List<Long> sessionIds);
	public List<Object[]> getPartyMeetingsAttendedMembersBySessions(List<Long> sessionIds);
	public List<Object[]> getPartyMeetingsImagesCountsByMeetings(List<Long> meetingIds);
	public List<Object[]> getPartyMeetingsImagesCoveredByMeetings(List<Long> meetingIds);
	public List<Object[]> meetingsBaseTotalCountForDiffLevels(PartyMeetingsInputVO inputVO,Long locationId,Set<Long> locationValuesSet,List<Long> locLevelIdList);

	 public List<Object[]> getCadrePartyMeetngDeatils(Date fromDate,Date toDate,List<Long> meetigLevelId,int startIndex,int maxIndex);
	 public Long getCadrePartyMeetngDeatilsCount(Date fromDate,Date toDate,List<Long> meetigLevelId);
	 public List<Object[]> getPartyMeetingDetailsByPartyMeetingId(Long patyMeetingId);
	 public Integer updatePartyMeetingDetails(Long meetingId);
	 public List<Object[]> getPartyMeetingIdsLevelwise(Date startDate,Date endDate,String level,List<Long> levelValues,Set<Long> levelIds);
	 public List<Object[]> getMeetingTypeWiseTotalMeetings(Long locationLevel,List<Long> locationId,Date fromDate,Date toDate);
	 public List<Object[]> getMeetingLevelWiseTotalMeetings(Long locationLevel,List<Long> locationIds,Date fromDate,Date toDate);
	 public List<Object[]> getBelowLevelMeetingConductedCount(Long locationLevel,List<Long> locationIds,Date fromDate,Date toDate, String areaType);
	 public Long getAccessLevelMeetingConductedCount(Long locationLevel,List<Long> locationIds,Date fromDate,Date toDate);
	 public List<Object[]> getCommitteeMeetingsDetails(Long locationLevel,List<Long> locationIds,Date fromDate,Date toDate);
	 public List<Object[]> getLocationWiseStateMeetings(List<Long> locationValues, Long locationTypeId, Date startDate,Date endDate, Long partyMeetingMainTypeId,String type,Long partyMeetingTypeId);
	 public List<Object[]> getPartyMeetingDetailsByUserAccessLevel(Long userAccessLevelId,List<Long> userAccessLevelValues,Integer monthId,Integer yearId);
	 public Integer updateMOMConductedDate(Long meetingId,Date conductedDate,Long userId,Date presentDate);
	 public Integer updateMOMConductedStatus(Long meetingId,String isConducted,Long userId,Date presentDate);
	 public Integer updateMOMConductedReason(Long meetingId,String remarks,Long userId,Date presentDate);
}
