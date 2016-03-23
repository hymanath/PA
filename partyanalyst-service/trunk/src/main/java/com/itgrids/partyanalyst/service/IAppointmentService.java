package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.AppointmentBasicInfoVO;
import com.itgrids.partyanalyst.dto.AppointmentCandidateVO;
import com.itgrids.partyanalyst.dto.AppointmentDetailsVO;
import com.itgrids.partyanalyst.dto.AppointmentVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.LabelStatusVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.impl.VoterAddressVO;

public interface IAppointmentService {
	public ResultStatus saveAppointment(final AppointmentVO appointmentVO,final Long loggedUserId);
	public List<IdNameVO> getAppointmentStatusList();
	public List<IdNameVO> getAppCandidateDesigList();
	public List<IdNameVO> getAppointmentPriorityList();
	public List<IdNameVO> getAppmntLblStatusList();
	public List<AppointmentBasicInfoVO> getAppointmentUsersDtlsByUserId(Long userId);
	public ResultStatus createAppointmentLeble(String labelName,Long insertedBy,String date);
    public List<LabelStatusVO> getLabelDtslByDate(String date,Long appntmntUsrId);
	public List<IdNameVO> getVillageWard(Long mandalId);
	public List<IdNameVO> getConstituenciesForADistrict(Long distId);
	public ResultStatus deleteAppointmentLabel(Long appointmentLabelId,String remarks);
	public List<AppointmentBasicInfoVO> getAppointmentsCandidateDetails(Long candidateDsgntnId,Long appntmntPrrtyId,Long appntmntSttsId,String currentMonth,String anyDate);
	public List<IdNameVO> getTotalAppointmentStatus();
	public List<IdNameVO> getTotalAppointmentStatusForToday();
	public List<AppointmentCandidateVO> searchApptRequestedMembers(String searchType,String searchValue);
	public VoterAddressVO getMemberDetails(String candidateType,Long id);
	public List<AppointmentBasicInfoVO> getAllAppointmentDetails(int startIndex,int maxIndex);
	public List<AppointmentDetailsVO> getAppointmentsBySearchCriteria(Long designationId,Long priorityId,Long statusId,Long districtId,Long constituencyid,Long appointmentlabelId);
	public ResultStatus addAppointmentstoLabel(final Long apptLabelId,final List<Long> appointmentIds,final Long loggerUserId);	
	public List<AppointmentVO> getAppointmentsOfALableForUpdate(Long lableId);
	public LabelStatusVO getLabelAndStatuswiseCountsOfAppointments();
	public LabelStatusVO getStatusWiseCountsOfAppointments();
}
