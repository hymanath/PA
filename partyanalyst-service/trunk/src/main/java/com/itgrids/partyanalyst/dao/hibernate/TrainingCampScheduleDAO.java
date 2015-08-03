package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITrainingCampScheduleDAO;
import com.itgrids.partyanalyst.model.TrainingCampSchedule;

public class TrainingCampScheduleDAO extends GenericDaoHibernate<TrainingCampSchedule, Long> implements ITrainingCampScheduleDAO{

	public TrainingCampScheduleDAO() {
		super(TrainingCampSchedule.class);
		// TODO Auto-generated constructor stub
	}

}
