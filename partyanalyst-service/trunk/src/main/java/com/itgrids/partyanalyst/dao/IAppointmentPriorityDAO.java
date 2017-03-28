package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AppointmentPriority;

public interface IAppointmentPriorityDAO extends GenericDao<AppointmentPriority, Long> {
	public List<Object[]> getAppointmentPriorityList();

}
