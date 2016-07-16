package com.itgrids.partyanalyst.dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

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
}
