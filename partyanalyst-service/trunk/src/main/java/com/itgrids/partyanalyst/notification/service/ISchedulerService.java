package com.itgrids.partyanalyst.notification.service;

import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.dto.ResultStatus;

public interface ISchedulerService {
	
	public ResultStatus deleteSearchEngineAccessedURLsFromUserTracking(Date fromDate,Date toDate);
	
	public List<String> getAllSearchEngineIPAddresses();
	
	public void saveDailyWmCorrectedMobileNUmbers(Date fromDate);
	
	//public void prepareDatForCardPrinting(String prevDate);
	//public void prepareDatForCardPrintingForZebra(String prevDate);
	//public void prepareDatForCardPrintingForMax(String prevDate);
	//public void fillDataForCardPrinting(String prevDate , String type);
	public void schedularForCardPrintDataFilling(String prevDate);
	public void runSchuduler(List<Long> districtIds);
	public Long updateTdpCadreAgerangeInfoDetails(Long enrollmentYearId);
	public Long updateTdpCadreCasteInfoDetails(Long enrollmentYearId);
	public Long updateTdpCadreInfoDetails(Long enrollmentYearId);
	public String updateTrainingCampSpeakersDetails();
	
	public void changeApptStatusToAttended(Date fromDate,Long attendedStatusId,Long fixedStatusId);
	public void sendPdfReport();
	public void runTheJobForEveryDayToSendEmployeeAttendance();
	public void runTheJobForEveryDayToSendEmpAttendanceDeptWise();
	
	public ResultStatus pushDataToPartyMeetingStatusTable();
	public ResultStatus pushSourceOfRegistrationIntoIntermediateTable();
	public ResultStatus pushTrainginCampDataLocationWiseByCommitteeLevel();
	public ResultStatus sendSmsDetailsOfAlert();
}
