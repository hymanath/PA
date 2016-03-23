package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.LabelAppointment;

public interface ILabelAppointmentDAO extends GenericDao<LabelAppointment, Long> {
	
	public List<Object[]> getLableDetailsWithStatusWiseCounts(Date labelDate,Long userId);
	public List<Object[]> getAppointmentsOfALableForUpdate(Long lableId);
}
