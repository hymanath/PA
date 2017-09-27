package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.CommitteeInputVO;
import com.itgrids.partyanalyst.model.PartyMeetingStatus;

public interface IPartyMeetingStatusDAO extends GenericDao<PartyMeetingStatus,Long> {

	public List<Object[]> getPartyMeetingCountLevelWise(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> partyMeetingsTypeValues,String countType);
	public List<Object[]> getPartyMeetingCountLocationWiseByUserAccess(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> partyMeetingsTypeValues);
	
	public List<Object[]> getLocationWiseMeetingsStatusCountByLocIds(CommitteeInputVO inputBO);
	public List<Object[]> getTopPoorMeetingLocations(CommitteeInputVO committeeBO);
	public List<Object[]> getPartyMeetingCntDetailstLevelWiseByUserAccessLevel(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> partyMeetingTypeValues,String levelType);
   
	public int deleteAllRecords();
	public int setPrimaryKeyAutoIncrementToOne();
	public int insertPartyofficeAndIvrStatus();
	public int insertPartyofficeAndIvrStatus1();
	public int updatePartyMeetingStatus1();
	public int updatePartyMeetingStatus2();
	public int updatePartyMeetingStatus3();
	public int updatePartyMeetingStatus4();
	public int updatePartyMeetingStatus5();
	public int updatePartyMeetingStatus6();
	public int updatePartyMeetingStatus7();
	public int updatePartyMeetingStatus8();
	public int updatePartyMeetingStatus9();
	public int updatePartyMeetingStatus10();
	public int updatePartyMeetingStatus11();
	public int setInsertedDate(Date currentDateTime);
	public Date getMeetingLastUpdatedTime();
   public List<Long> getPartyMeetingCount(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> partyMeetingTypeValues,String meetingStatus,List<Long> PartyMeetingLevelIds,String isComment,Long locationId,String locationType);
   public List<Object[]> getPartyMeetingComulativeCommentDetails(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> partyMeetingTypeValues,String meetingStatus,List<Long> PartyMeetingLevelIds,String isComment,Long locationId,String locationType,String reportType,String type);
   
   public PartyMeetingStatus getObjectByPartyMeetingId(Long partyMeetingId);
   public List<Object[]> getLocationWiseMeetings(List<Long> locationValues,Long locationTypeId,Date startDate,Date endDate);
  //getLocationWiseMeetings(String locationType,Long constituencyId);
   public List<Object[]> getLevelWiseMeetingStatusCount(Date fromDate,Date toDate,Long locationTypeId,List<Long> locationValues,String year);
   public List<Object[]> getLocationWiseMeetingsDeatils(List<Long> locationValues,Long locationTypeId,Date fromDate,Date toDate);
}