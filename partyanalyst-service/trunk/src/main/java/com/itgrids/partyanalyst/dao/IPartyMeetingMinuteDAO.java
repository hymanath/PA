package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.CommitteeInputVO;
import com.itgrids.partyanalyst.dto.CoreDashboardMomDetailsVO;
import com.itgrids.partyanalyst.model.PartyMeetingMinute;
import com.itgrids.partyanalyst.model.UserAddress;

public interface IPartyMeetingMinuteDAO extends GenericDao<PartyMeetingMinute,Long>{
	public List<Object[]> getPartyMeetingsMinutsDetlsByCadreIds(Long partyMeetingTypeId);
	public List<Object[]> getMinuteDetailsForAMeeting(Long partyMeetingId,String accessType,List<Long> accessValue);
	public Integer updateMeetingPoint(Long minuteId,String minuteText,Long updatedBy,Date updateTime,Long levelId,Long levelValue,String isActionable,Long statusId,UserAddress userAddressId,Long isGovtParty);	
	public Integer deleteMeetingMinutePoint(Long minuteId,Long updatedBy,Date updateTime);
	public List<Object[]> getMinuteDetailsForMeetings(List<Long> partyMeetingIds);
	public List<Long> getMOMHavingMeetings(List<Long> partyMeetingIds);
	public List<Object[]> getPartyMeetingMinuteRetrieveDetails(Long minuteId);
	public List<Object[]> getMinuteDetailsForMeetingsList(List<Long> partymeetingIdsList,String accessType,List<Long> accessValues);
	public List<Object[]> getPartyMeetingMomPointsByUserAccessLevel(Long userAccessLevelId,List<Long> userAccessLevelValues,Integer monthId,Integer yearId,String type,Long partyMeetingId);
	public List<Object[]> getPartyMeetingMomDtls(Long userAccessLevelId,List<Long> userAccessLevelValues,Integer monthId,Integer yearId,String type);
	public Long getMomCreatedByYourLocation(Long userAccessLevelId,List<Long> userAccessLevelValues,Integer monthId,Integer yearId,String type);
	public Long getMomAssignedToYourLocation(Long userAccessLevelId,List<Long> userAccessLevelValues,Integer monthId,Integer yearId);
	public List<Object[]> getTotalMomDetailsByLocation(Long userAccessLevelId,List<Long> userAccessLevelValues,Integer monthId,Integer yearId,Set<Long> momIdSet);
	public List<Object[]> getMomCreationLocation(Long userAccessLevelId,List<Long> userAccessLevelValues,Integer monthId,Integer yearId);
	public List<Object[]> getMomAssignedLocation(Set<Long> momIdSet,Integer monthId,Integer yearId);
	public List<Object[]> getMomDetailsByType(Long userAccessLevelId,List<Long> userAccessLevelValues,Integer monthId,Integer yearId,String type,String momType,Set<Long> momIdSet);
	public List<Long> getPartyMeetingLevelByRegionScope(Long userAccessLevelId);
	public List<Object[]> getPartyMeetingMomAssignDtls(Long userAccessLevelId,
			List<Long> userAccessLevelValues, Integer monthId, Integer yearId,
			String type);
	public Date getPartMeetingMonthYear(Long partyMeetingId);
	public List<Object[]> getMOMTypesCountDetails(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> partyMeetingTypeValues,String momType);
	public List<Object[]> getMOMActionTypeCountDetails(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> partyMeetingTypeValues);
	public List<Object[]> getMOMTypesByLevelTypeDetails(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,String momType,String levelType,List<Long> partyMeetingTypeValues);
	public List<Object[]> getMOMActionTypeDetailsByLevelType(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,String levelType,List<Long> partyMeetingTypeValues);
	public List<Object[]> getMOMStatusDetailsByLevelType(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,String levelType);
	public List<Object[]> getMOMTypesCountDetails(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Long partyMeetingMainTypeId,Long partyMeetingTypeId,Date fromDate,Date toDate);
	public List<Object[]> getMOMStatusCntDetails(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Long partyMeetingMainTypeId,Long partyMeetingTypeId,Date fromDate,Date toDate);
	public List<Object[]> getLocationWiseMOMTypesCountDetails(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> partyMeetingTypeValues,String momType,CommitteeInputVO committeeBO);
	public List<Object[]> getPartyMeetingDetails(CoreDashboardMomDetailsVO momDetailsVO,Long userAccessLevelId,List<Long> userAccessLevelValues);
	public List<Object[]> getPartyMeetingMOMDetails(CoreDashboardMomDetailsVO momDetailsVO,Long userAccessLevelId,List<Long> userAccessLevelValues);
	public List<Object[]> getPartyMeetingMOMDocumentsDetails(CoreDashboardMomDetailsVO momDetailsVO,Long userAccessLevelId,List<Long> userAccessLevelValues);
	public List<Object[]> getMomNotUpdtedCountDetails(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> partyMeetingTypeValues,String momType);
	public List<Object[]> getMOMUpdatedByLevelTypeDetails(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,String momType,String levelType,List<Long> partyMeetingTypeValues);
	public List<Object[]> getPartyMeetingMOMCountDetails(CoreDashboardMomDetailsVO momDetailsVO,Long userAccessLevelId,List<Long> userAccessLevelValues);
}
