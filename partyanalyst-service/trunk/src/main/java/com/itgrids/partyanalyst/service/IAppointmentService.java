package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.AppHistoryVO;
import com.itgrids.partyanalyst.dto.AppointmentBasicInfoVO;
import com.itgrids.partyanalyst.dto.AppointmentCandidateVO;
import com.itgrids.partyanalyst.dto.AppointmentCountDetailsVO;
import com.itgrids.partyanalyst.dto.AppointmentCountVO;
import com.itgrids.partyanalyst.dto.AppointmentCountsVO;
import com.itgrids.partyanalyst.dto.AppointmentDetailsVO;
import com.itgrids.partyanalyst.dto.AppointmentInputVO;
import com.itgrids.partyanalyst.dto.AppointmentMemberInputVO;
import com.itgrids.partyanalyst.dto.AppointmentMembersDataVO;
import com.itgrids.partyanalyst.dto.AppointmentScheduleVO;
import com.itgrids.partyanalyst.dto.AppointmentSlotsVO;
import com.itgrids.partyanalyst.dto.AppointmentStatusFlowVO;
import com.itgrids.partyanalyst.dto.AppointmentStatusVO;
import com.itgrids.partyanalyst.dto.AppointmentUpdateStatusVO;
import com.itgrids.partyanalyst.dto.AppointmentVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.LabelStatusVO;
import com.itgrids.partyanalyst.dto.LocationInputVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.StatusTrackingVO;
import com.itgrids.partyanalyst.service.impl.VoterAddressVO;

public interface IAppointmentService {
	public ResultStatus saveAppointment(final AppointmentVO appointmentVO,final Long loggedUserId);
	public List<IdNameVO> getAppointmentStatusList();
	public List<IdNameVO> getAppCandidateDesigList();
	public List<IdNameVO> getAppointmentPriorityList();
	public List<IdNameVO> getAppmntLblStatusList();
	public List<AppointmentBasicInfoVO> getAppointmentUsersDtlsByUserId(Long userId);
	public ResultStatus createAppointmentLeble(String labelName,Long insertedBy,String fromDate,String toDate,Long aptUserId);
    public List<LabelStatusVO> getLabelDtslByDate(String date,Long appntmntUsrId,Long stutsId);
	public List<IdNameVO> getVillageWard(Long mandalId);
	public List<IdNameVO> getConstituenciesForADistrict(Long distId);
	public ResultStatus deleteAppointmentLabel(Long appointmentLabelId,String remarks,Long userId);
	public List<AppointmentBasicInfoVO> getAppointmentsCandidateDetails(Long candidateDsgntnId,Long appntmntPrrtyId,Long appntmntSttsId,String currentMonth,String anyDate);
	public List<IdNameVO> getTotalAppointmentStatus();
	public List<IdNameVO> getTotalAppointmentStatusForToday();
	public List<AppointmentCandidateVO> searchApptRequestedMembers(String searchType,String searchValue,Long aptUserId);
	public VoterAddressVO getMemberDetails(String candidateType,Long id);
	public List<AppointmentBasicInfoVO> getAllAppointmentDetails(int startIndex,int maxIndex,Long aptUserId);
	public List<AppointmentDetailsVO> getAppointmentsBySearchCriteria(Long designationId,Long priorityId,Long statusId,Long districtId,Long constituencyid,Long appointmentlabelId,String fromDateStr,String toDateStr,Long selUserID,
			Long cndTypeId,Long dateTypeValue,Long apptUserID);
	public ResultStatus addAppointmentstoLabel(final Long apptLabelId,final List<Long> appointmentIds,final Long loggerUserId);	
	public List<AppointmentVO> getAppointmentsOfALableForUpdate(Long lableId);
	public LabelStatusVO getLabelAndStatuswiseCountsOfAppointments();
	public LabelStatusVO getStatusWiseCountsOfAppointments(Long aptUserId);
	public List<AppointmentScheduleVO>  getAppointmentSearchDetails(AppointmentInputVO inputVo);
	public List<AppointmentDetailsVO> viewAppointmentsOfALable(Long labelId,String callFrom,Long apptUserId,String labelName);
	public AppointmentSlotsVO getTimeSlotsDetails(long appointmentLabelId);
	public List<IdNameVO> getAppointmentLabels(Long aptUserId);
	public List<IdNameVO> getAppointmentsLabelStatus();
	public ResultStatus updateAppointmentsLabelStatus(Long labelId,Long labelstatusId);
	public ResultStatus updateMemberAppointmentsStatus(Long apptId,Long statusId);
	 public ResultStatus setTimeSlotForAppointment(Long appointmentId,String dateStr,String fromTime,String toTime,Long registrationId,String type,Long timeSlotId,String commentTxt,Long apptUserID);
	public List<AppointmentDetailsVO> getViewAppointmentsOfALable(Long labelId);
	public ResultStatus updateAppointmentStatus(AppointmentUpdateStatusVO inputVO,Long userId);
	public ResultStatus sendSmsForAppointment(AppointmentUpdateStatusVO inputVO);
	public List<IdNameVO> getAppointmentCreatedUsers();
	public ResultStatus updateAllAppointmentStatusByType(AppointmentUpdateStatusVO statusinputVo,AppointmentInputVO inputVo,Long userId);
	public ResultStatus deleteAppointmentsOfLabel(List<Long> ids,Long labelId,Long registrationId);
	//public  List<AppointmentCandidateVO> advancedSearchApptRequestedMembers(String searchType,String searchValue);
	public List<LocationWiseBoothDetailsVO> getMandalMunicCorpDetailsOfConstituencies(List<Long> constituencyIds,Long locationScopeId);

	public  List<AppointmentCandidateVO> advancedSearchApptRequestedMembers(String searchType,String searchValue,LocationInputVO locationVo);

	public List<IdNameVO> getConstituenciesByDistrict(Long districtId);
	public List<IdNameVO> getAllMandalsByConstituencyID(Long constituencyID);
	public List<IdNameVO> getPanchayatDetailsByMandalId(Long tehsilId,String type);
	 public List<IdNameVO> getDistrictsList();

	public List<IdNameVO> getAllCandidateTypes();
	public List<IdNameVO> getAppCandidateDesigListByType(Long typeId);
	public  List<IdNameVO> getAppointmentStatusOverview(Long appointmentUserId);
	public List<IdNameVO> getApointmentStatusOvrviwforCandidte(Long apointmntcandidteId,Long apptUserID);
	public List<AppointmentStatusVO> getAppointmentStatusCounts(Long aptUserId);
	public List<AppHistoryVO> getAppointmentHistoryForCandidate(Long appointmentCandidateId,Long apptUserID);
	public ResultStatus sendSms(AppointmentUpdateStatusVO inputVO);
	public AppointmentCountsVO getCandidCountsByStates(String startDateString,String endDateString,Long appointmentUserId);
	public List<AppointmentCountVO> getPublicRepresentativeWiseAppointmentCnt(Long appointmentUserId);
	public List<AppointmentCountVO> getCommitteeLevelAppointments(Long appointmentUserId);
	public List<AppointmentCountVO> getLevelWiseCount(Long levelId,Long aptUserId);
	public List<IdNameVO> getAppointmentStatusByUserId(Long userId);
	public List<StatusTrackingVO> getAppointmentStatusTrackingDetails(Long appointmentId);
	public List<IdNameVO> getUpdatedStatusForaAppointment(Long userTypeId,Long currentStatusId);
	public List<AppointmentSlotsVO>  getTimeSlotsForADayByAppytUserId(Long apptUserId,String dateStr);
	public List<AppointmentStatusFlowVO> getApplicationContextWiseSatuses();
	public List<AppointmentScheduleVO> getAllScheduledApptsByDate(Long apptUserId,String dateStr);
	public List<StatusTrackingVO> getAppointmentStatusCommentsTrackingDetails(Long appointmentId);
	public ResultStatus saveDesignationForOtherCandidate(String designation,Long candidateTypeId);
	public void getDesignationsForCadre(List<Long> tdpCadreIds,List<AppointmentCandidateVO> finalList);
	public AppointmentStatusFlowVO getLoginUserAppointmentUserType(Long userId);
	public AppointmentScheduleVO setPreferDatesToAppointment(List<Long> aptmnts,AppointmentScheduleVO apptvo);
	
	public boolean checkIsValidForSendingSMS(Long appointmentStatusId);
	public List<IdNameVO> getSMSEnablingDetailsForAllStatus();
	public List<AppointmentMembersDataVO> getAppointmentMembersByScheduleType(AppointmentMemberInputVO inputVO);
	public ResultStatus updateAppointmentReason(Long appointmentId,String reason,Long userId);
	public AppointmentScheduleVO getRescheduledAppsCounts(Long appUserId);
	public List<AppointmentScheduleVO> getRescheduledAppointmentsDetails(Long apptUserId);
	public List<AppointmentScheduleVO> getRescheduledMembersApptDetails(Long apptUserId);
	
	public List<AppointmentScheduleVO> overviewSummaryOfRescheduledCandidates(Long apptUserId);
	public List<AppointmentScheduleVO> getApptRescheduledDetialsByCandidate(Long apptUserId,Long appointmentCandidateId);
	public  List<AppointmentCandidateVO> advancedSearchRequestedMembers(String searchType,String searchValue,LocationInputVO inputVo);
	public  List<AppointmentCandidateVO> searchRequestedMembers(String searchType,String searchValue);
	public List<AppointmentCandidateVO> getNewCadreSearchBySearchType(String searchType,Long searchValue,String locationType,Long locationVal,String gender);
	public String savingAppointCandRelaDetails(Long appointmntId,Long appntCandidateId);
	public List<AppointmentCountDetailsVO> getAppointmentCandidateDetails(String fromDateStr,String toDateStr,Long userId);
	public AppointmentCountDetailsVO getAppointmentCandidateCountDeatils(Long userId);
	public ResultStatus checkMemberWalkInForToday(final String memberShipId,final String date , final String uniqueId,final Long loginUserId,final Long tabPrimaryKey,final String isCheckedStatus);
}
