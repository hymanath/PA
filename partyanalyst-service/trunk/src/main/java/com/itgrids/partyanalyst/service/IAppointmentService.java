package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.AppointmentBasicInfoVO;
import com.itgrids.partyanalyst.dto.AppointmentVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

public interface IAppointmentService {
	public ResultStatus saveAppointment(final AppointmentVO appointmentVO,final Long loggedUserId);
	public List<IdNameVO> getAppointmentStatusList();
	public List<IdNameVO> getAppCandidateDesigList();
	public List<IdNameVO> getAppointmentPriorityList();
	public List<IdNameVO> getAppmntLblStatusList();
	public List<AppointmentBasicInfoVO> getAppointmentUsersDtlsByUserId(Long userId);
	public ResultStatus createAppointmentLeble(String labelName,String date);
    public List<AppointmentBasicInfoVO> getLabelDtslByDate(String date);
}
