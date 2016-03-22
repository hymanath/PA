package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AppointmentLabel;

public interface IAppointmentLabelDAO extends GenericDao<AppointmentLabel, Long> {
	
	public List<Object[]> getLabelDtslByDate(Date date,Long appntmntUsrId);
	public Integer deleteAppointmentLabel(Long appointmentLabelId,String remarks);

}
