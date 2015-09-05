package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITrainingCampAttendanceDAO;
import com.itgrids.partyanalyst.model.TrainingCampAttendance;

public class TrainingCampAttendanceDAO extends GenericDaoHibernate<TrainingCampAttendance,Long> implements ITrainingCampAttendanceDAO{

	public TrainingCampAttendanceDAO()
	{
		super(TrainingCampAttendance.class);
	}
	
	public List<Object[]> getCompletedCounts(List<Long> batchIds){
		Query query = getSession().createQuery(" select model.trainingCampBatch.trainingCampBatchId, count(distinct model.attendance.tdpCadre.tdpCadreId) " +
				" from TrainingCampAttendance model " +
				" where model.trainingCampBatch.trainingCampBatchId in (:batchIds) ");
		query.setParameterList("batchIds", batchIds);
		return query.list();
	}
}
