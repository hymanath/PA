package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.AppointmentBasicInfoVO;
import com.itgrids.partyanalyst.dto.AppointmentCandidateVO;
import com.itgrids.partyanalyst.dto.AppointmentVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.impl.VoterAddressVO;

public interface IAppointmentService {
	public ResultStatus saveAppointment(final AppointmentVO appointmentVO,final Long loggedUserId);
	public List<IdNameVO> getAppointmentStatusList();
	public List<IdNameVO> getAppCandidateDesigList();
	public List<IdNameVO> getAppointmentPriorityList();
	public List<IdNameVO> getAppmntLblStatusList();
	public List<AppointmentBasicInfoVO> getAppointmentUsersDtlsByUserId(Long userId);
	public ResultStatus createAppointmentLeble(String labelName,String insertedBy,String date);
    public List<AppointmentBasicInfoVO> getLabelDtslByDate(String date,Long appntmntUsrId);
	public List<IdNameVO> getVillageWard(Long mandalId);
	public List<IdNameVO> getConstituenciesForADistrict(Long distId);
	public ResultStatus deleteAppointmentLabel(Long appointmentLabelId);
	public List<AppointmentBasicInfoVO> getAppointmentsCandidateDetails(Long candidateDsgntnId,Long appntmntPrrtyId,Long appntmntSttsId,String currentMonth,String anyDate);
	public List<IdNameVO> getTotalAppointmentStatus();
	public List<IdNameVO> getTotalAppointmentStatusForToday();
	public List<AppointmentCandidateVO> searchApptRequestedMembers(String searchType,String searchValue);
	public VoterAddressVO getMemberDetails(String candidateType,Long id);
}
