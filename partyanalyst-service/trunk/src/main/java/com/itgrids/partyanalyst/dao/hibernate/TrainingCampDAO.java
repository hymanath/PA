package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITrainingCampDAO;
import com.itgrids.partyanalyst.model.TrainingCamp;

public class TrainingCampDAO extends GenericDaoHibernate<TrainingCamp, Long> implements ITrainingCampDAO{

	public TrainingCampDAO() {
		super(TrainingCamp.class);
	}
    
	public List<Object[]> getAllCamps()
	{
 	  Query query = getSession().createQuery("select distinct model.trainingCampId,model.campName from TrainingCamp model order by model.campName asc");
	  return query.list();
	}
}
