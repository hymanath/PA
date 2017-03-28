package com.itgrids.partyanalyst.dao;

import java.util.Date;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PartyMeetingStatusTemp;

public interface IPartyMeetingStatusTempDAO extends GenericDao<PartyMeetingStatusTemp,Long> {
	
	public int deleteAllRecordsFromTemp();
	public int setPrimaryKeyAutoIncrementToOneToTemp();
	public int insertPartyofficeAndIvrStatusToTemp();
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
	public int insertDataToPartyMeetingStatusFromTemp();
}
