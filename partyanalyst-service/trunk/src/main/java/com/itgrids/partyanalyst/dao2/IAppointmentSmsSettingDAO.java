package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AppointmentSmsSetting;

public interface IAppointmentSmsSettingDAO extends GenericDao<AppointmentSmsSetting,Long>{

	public List<Object[]> getContentTypeAndSmsContent(Long appointmentStatusId);
	
	public String checkIsValidForSendingSMS(Long appointmentStatusId);
	public List<Object[]> getSMSEnablingDetailsForAllStatus();
}
