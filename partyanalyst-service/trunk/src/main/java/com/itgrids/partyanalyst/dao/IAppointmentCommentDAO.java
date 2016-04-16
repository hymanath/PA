package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;


import com.itgrids.partyanalyst.model.AppointmentComment;

public interface IAppointmentCommentDAO extends GenericDao<AppointmentComment, Long>{

	public List<Object[]> getAppointmentCommentsForViewHistory(List<Long> appointmentIds);
}
