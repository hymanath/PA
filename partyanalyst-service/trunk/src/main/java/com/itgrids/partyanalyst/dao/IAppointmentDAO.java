package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Appointment;

public interface IAppointmentDAO extends GenericDao<Appointment, Long>{
	public List<Object[]> getTotalAppointmentStatus();

}
