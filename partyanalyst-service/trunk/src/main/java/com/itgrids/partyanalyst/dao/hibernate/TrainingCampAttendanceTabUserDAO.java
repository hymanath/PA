package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITrainingCampAttendanceTabUserDAO;
import com.itgrids.partyanalyst.model.TrainingCampAttendanceTabUser;
import com.itgrids.partyanalyst.model.TrainingCampSchedule;

public class TrainingCampAttendanceTabUserDAO extends GenericDaoHibernate<TrainingCampAttendanceTabUser,Long> implements ITrainingCampAttendanceTabUserDAO{

	public TrainingCampAttendanceTabUserDAO()
	{
		super(TrainingCampAttendanceTabUser.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<TrainingCampSchedule> getTrainingCampSchedulesOfAttendanceTabUser(Long attendanceTabUserId)
	{
		Query query = getSession().createQuery("SELECT model.trainingCampSchedule FROM TrainingCampAttendanceTabUser model where model.attendanceTabUser.attendanceTabUserId = :attendanceTabUserId and model.attendanceTabUser.isEnabled = 'Y'");
		query.setParameter("attendanceTabUserId",attendanceTabUserId);
		return query.list();
	}
}
