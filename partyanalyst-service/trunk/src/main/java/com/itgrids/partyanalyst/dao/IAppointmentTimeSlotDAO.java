/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */
package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AppointmentTimeSlot;


public interface IAppointmentTimeSlotDAO extends GenericDao<AppointmentTimeSlot, Long>{
	public List<Object[]> getAppointmentConfirmDates(List<Long> appointmentIds);
	public List<Object[]> getAppointmentConfirmDates(Date date,Long apptUserId,Long apptStatusId);
	public AppointmentTimeSlot getAppointmentTimeSlotByAppointmentId(Long appointmentId);
	public List<Object[]> getAppointmentDetails(Date fromDate,Date toDate,Long tdpCadreId);
	public List<Object[]> getAppointmentList(List<Long> appointmentIds);
	public List<Object[]> getLeaderAppointmentDetails(Date fromDate,Date toDate,Long tdpCadreId);
}