package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AppointmentStatus;

public interface IAppointmentStatusDAO extends GenericDao<AppointmentStatus, Long> {
	public List<Object[]> getAppointmentStatusList();
	public List<Object[]> getAllAppointmentStatus();
	 public List<String> getAllStatus();
	 
   public List<Object[]> getStatusDetailsByIds(List<Long> statusIds);

}
