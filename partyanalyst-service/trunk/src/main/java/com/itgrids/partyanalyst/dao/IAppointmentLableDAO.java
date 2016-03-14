package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AppointmentLable;

public interface IAppointmentLableDAO extends GenericDao<AppointmentLable, Long> {
	
	public List<Object[]> getLabelDtslByDate(Date date);

}
