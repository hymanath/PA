package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AppointmentLabelStatus;

public interface IAppointmentLabelStatusDAO extends GenericDao<AppointmentLabelStatus, Long> {
	public List<Object[]> getAppmntLblStatusList();

}
