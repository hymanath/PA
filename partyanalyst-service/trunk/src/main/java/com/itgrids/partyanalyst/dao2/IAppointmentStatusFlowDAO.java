package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AppointmentStatusFlow;

public interface IAppointmentStatusFlowDAO extends GenericDao<AppointmentStatusFlow,Long>{

	public List<Object[]> getUpdatedStatusForaAppointment(Long userTypeId,Long currentStatusId);
	public List<Object[]> getApplicationContextWiseSatuses();
}
