package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITrainingCampAttendanceTabUserDAO;
import com.itgrids.partyanalyst.model.TrainingCampAttendanceTabUser;

public class TrainingCampAttendanceTabUserDAO extends GenericDaoHibernate<TrainingCampAttendanceTabUser,Long> implements ITrainingCampAttendanceTabUserDAO{

	public TrainingCampAttendanceTabUserDAO()
	{
		super(TrainingCampAttendanceTabUser.class);
	}
}
