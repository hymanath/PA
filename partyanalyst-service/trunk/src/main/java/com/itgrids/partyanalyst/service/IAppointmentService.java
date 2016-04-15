package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.AppHistoryVO;
import com.itgrids.partyanalyst.dto.AppointmentBasicInfoVO;
import com.itgrids.partyanalyst.dto.AppointmentCandidateVO;
import com.itgrids.partyanalyst.dto.AppointmentDetailsVO;
import com.itgrids.partyanalyst.dto.AppointmentInputVO;
import com.itgrids.partyanalyst.dto.AppointmentScheduleVO;
import com.itgrids.partyanalyst.dto.AppointmentSlotsVO;
import com.itgrids.partyanalyst.dto.AppointmentStatusVO;
import com.itgrids.partyanalyst.dto.AppointmentUpdateStatusVO;
import com.itgrids.partyanalyst.dto.AppointmentVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.LabelStatusVO;
import com.itgrids.partyanalyst.dto.LocationInputVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
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
	public ResultStatus deleteAppointmentLabel(Long appointmentLabelId,String remarks);
	public List<AppointmentBasicInfoVO> getAppointmentsCandidateDetails(Long candidateDsgntnId,Long appntmntPrrtyId,Long appntmntSttsId,String currentMonth,String anyDate);
	public List<IdNameVO> getTotalAppointmentStatus();
	public List<IdNameVO> getTotalAppointmentStatusForToday();
	public List<AppointmentCandidateVO> searchApptRequestedMembers(String searchType,String searchValue);
	public VoterAddressVO getMemberDetails(String candidateType,Long id);
	public List<AppointmentBasicInfoVO> getAllAppointmentDetails(int startIndex,int maxIndex,Long aptUserId);
	public List<AppointmentDetailsVO> getAppointmentsBySearchCriteria(Long designationId,Long priorityId,Long statusId,Long districtId,Long constituencyid,Long appointmentlabelId,String fromDateStr,String toDateStr,Long selUserID,
			Long cndTypeId,Long dateTypeValue);
	public ResultStatus addAppointmentstoLabel(final Long apptLabelId,final List<Long> appointmentIds,final Long loggerUserId);	
	public List<AppointmentVO> getAppointmentsOfALableForUpdate(Long lableId);
	public LabelStatusVO getLabelAndStatuswiseCountsOfAppointments();
	public LabelStatusVO getStatusWiseCountsOfAppointments(Long aptUserId);
	public List<AppointmentScheduleVO>  getAppointmentSearchDetails(AppointmentInputVO inputVo);
	public List<AppointmentDetailsVO> viewAppointmentsOfALable(Long labelId,String callFrom);
	public AppointmentSlotsVO getTimeSlotsDetails(long appointmentLabelId);
	public List<IdNameVO> getAppointmentLabels(Long aptUserId);
	public List<IdNameVO> getAppointmentsLabelStatus();
	public ResultStatus updateAppointmentsLabelStatus(Long labelId,Long labelstatusId);
	public ResultStatus updateMemberAppointmentsStatus(Long apptId,Long statusId);
	 public ResultStatus setTimeSlotForAppointment(Long appointmentId,String dateStr,String fromTime,String toTime,Long registrationId,String type,Long timeSlotId);
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
	public  List<IdNameVO> getAppointmentStatusOverview();
	public List<IdNameVO> getApointmentStatusOvrviwforCandidte(Long apointmntcandidteId);
	public List<AppointmentStatusVO> getAppointmentStatusCounts(Long aptUserId);
	public List<AppHistoryVO> getAppointmentHistoryForCandidate(Long appointmentCandidateId);
	public ResultStatus sendSms(AppointmentUpdateStatusVO inputVO);
}
